package apml.compilers.java.codemodel;

import apml.helpers.Filegrepper;

import apml.modeling.Apmlimplement;

import apml.modeling.Apmllistener;

import apml.modeling.Apmlmodelfile;

import apml.modeling.Apmlobject;

import apml.modeling.Apmlsubscriber;

import apml.modeling.Apmlsystem;

import apml.system.bodi.Bodi;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.Set;

import java.util.logging.FileHandler;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 * @since 03.28.2017 
 */
public final class Bloqapmlpopulator 
{
    private final Integer hash = 0x00888fe8;  
    
    /*--------------------------------------------------------------------------*/
    
    public ArrayList<Apmlmodelfile> apmlfiles;      
        
    /*--------------------------------------------------------------------------*/
    
    protected static final Logger LOGGER = Logger.getLogger(Bloqapmlpopulator.class.getName());
    
    /*--------------------------------------------------------------------------*/
    
    public static void main(String...args) 
    {                          
        try
        {     
            Bloqapmlpopulator populator = new Bloqapmlpopulator();
            
            File apmlfile = new File("");
                        
            populator.getapmlmodelfiles(apmlfile,"//apml");   
            
            populator.getapmlmodelfiles(apmlfile,"//callback");  
            
            populator.getapmlmodelfiles(apmlfile,"//compiler");  
            
            populator.getapmlmodelfiles(apmlfile,"//definitions");            
            
            populator.getapmlmodelfiles(apmlfile,"//dynamiclistener");             
            
            populator.getapmlmodelfiles(apmlfile,"//listener");
            
            populator.getapmlmodelfiles(apmlfile,"//subscriber");                        
            
            populator.getapmlmodelfiles(apmlfile,"//system");               
            
            populator.getapmlmodelfiles(apmlfile,"//driver");                                       
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }      
    
    public Bloqapmlpopulator()
    {
        Bodi.setcontext("system");
        
        try
        {
            Bodi.context("system").put("apmlmodelpopulator", this); 
        }
        catch(Exception e)
        {
            
        }
        
        /*----------------------------------------------------------------------*/
        
        Bloqfileguardian fileguardian = (Bloqfileguardian)Bodi.context("system").pull("bloqfileguardian");
        
        try
        {
            LOGGER.addHandler(new FileHandler(fileguardian.loggingfileurl+fileguardian.loggingfilename));
            
            LOGGER.setUseParentHandlers(false);            
        }
        catch(IOException e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }       
      
    public ArrayList<Apmlmodelfile> getapmlmodelfiles(File apmlfile, String apmltag) throws Exception
    {                          
        Bloqxpathparameter xparam = new Bloqxpathparameter(apmltag, apmlfile);          
                                       
        for(int i=0; i<xparam.getnodecount(); i++)
        {                            
            switch(apmltag) //would probably need a 'parent' reference is all these cases given how it's been implemented
            {
                case "//apml":              return doapmltags(xparam);
                    
                case "//callback":          return docallbacktags(xparam);
                
                case "//definitions":       return dodefinitiontags(xparam);
                        
                case "//dynamiclistener":   return dodynamiclistenertags(xparam);
                        
                case "//listener":          return dolistenertags(xparam);
                        
                case "//object":            return doobjecttags(xparam);
                        
                case "//subscriber":        return dosubscribertags(xparam);
                    
                case "//system":            return dosystemtags(xparam);
            }
        }
        
        return null;
    }          
    
    private ArrayList<Apmlmodelfile> doapmltags(Bloqxpathparameter xparam) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.apmlimplements=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                        
            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                        
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}    
                                    
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmlsystems=this.getsystems(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
                        
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }        
    
    private ArrayList<Apmlmodelfile> docallbacktags(Bloqxpathparameter xparam) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.apmlimplements=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                        
            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                                                    
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
                        
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;        
    }
    
    private ArrayList<Apmlmodelfile> dodefinitiontags(Bloqxpathparameter xparam) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();       
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}    
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                
            
            //try{modelfile.apmlimplements=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }

    private ArrayList<Apmlmodelfile> dodynamiclistenertags(Bloqxpathparameter xparam)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList(); 
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                               
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                   
                        
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }     
     
    private ArrayList<Apmlmodelfile> dolistenertags(Bloqxpathparameter xparam)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();    
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            /*------------------------------------------------------------------*/
            
            try{modelfile.apmlsubscribers=this.getsubscribers(xparam, index);}                         catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                    
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}        
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                            
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                         
            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}           
            
            /*------------------------------------------------------------------*/
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
   
    private ArrayList<Apmlmodelfile> doobjecttags(Bloqxpathparameter xparam)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList(); 
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
            
            try{modelfile.apmlimplements=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}         
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                       
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}  
            
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                                     
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
  
    private ArrayList<Apmlmodelfile> dosubscribertags(Bloqxpathparameter xparam)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();   
        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
                              
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                        
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}          
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}         
            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                               
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    

    private ArrayList<Apmlmodelfile> dosystemtags(Bloqxpathparameter xparam)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();  
        
        for(int index=0; index<xparam.getnodecount(); index++)  //show two system tags 
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();        //show two apml files for each system

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.bodi=this.getbodi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                       
            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}           
            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}          
            
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmlimplements=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    } 
    
    private ArrayList<Apmlsubscriber> getsubscribers(Bloqxpathparameter xparam, Integer index)
    {              
        ArrayList<Apmlsubscriber> subscribers = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./subscriber", xparam.n0001_tagname.item(index), XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++) //for each subscriber under listener.item(index)
            {
                Element element = (Element)nodes.item(i);                        
                        
                Apmlsubscriber subscriber = new Apmlsubscriber();
                
                /*--------------------------------------------------------------*/
                                
                subscriber.id               = element.getAttribute("id");
                
                subscriber.alias            = element.getAttribute("alias");
                
                subscriber.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
                
                subscriber.classname        = element.getAttribute("class");
                
                subscriber.extension        = element.getAttribute("extends");
                
                String xpathstring          = "(./ancestor::package/@default)[last()]";
                
                subscriber.packagename      = (String)xparam.xpath.evaluate(xpathstring, nodes.item(i), XPathConstants.STRING);
                
                subscriber.startable        = element.getAttribute("start").equalsIgnoreCase("true");                
                
                /*--------------------------------------------------------------*/
                               
                subscribers.add(subscriber);
            }        
        }
        catch(Exception e)
        {
            //
        }
        
        return subscribers;
    }
    
    private ArrayList<Apmllistener> getlisteners(Bloqxpathparameter xparam, Integer index)
    {
        ArrayList<Apmllistener> listeners = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./listener", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<nodes.getLength(); i++)  
            {
                Element element = (Element)nodes.item(i);            

                Apmllistener listener = new Apmllistener();   
                
                /*--------------------------------------------------------------*/

                listener.id                 = element.getAttribute("id");
                
                listener.alias              = element.getAttribute("alias");
                
                listener.autostartable      = element.getAttribute("autostart").equalsIgnoreCase("true");
                
                listener.classname          = element.getAttribute("class");
                
                listener.extension          = element.getAttribute("extends");
                
                String xpathstring          = "(./ancestor::package/@default)[last()]";
                
                listener.packagename        = (String)xparam.xpath.evaluate(xpathstring, nodes.item(i), XPathConstants.STRING);
                
                listener.startable          = element.getAttribute("start").equalsIgnoreCase("true");
                
                /*--------------------------------------------------------------*/

                listeners.add(listener);
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }                       
        
        return listeners;
    }        

    private ArrayList<Apmlobject> getobjects(Bloqxpathparameter xparam, Integer index)
    {      
        ArrayList<Apmlobject> objects = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./object", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<nodes.getLength(); i++)  
            {
                Element element = (Element)nodes.item(i);            

                Apmlobject object = new Apmlobject();            

                /*--------------------------------------------------------------*/
                
                object.id               = element.getAttribute("id");
                
                object.alias            = element.getAttribute("alias");
                
                object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
                
                object.classname        = element.getAttribute("class");
                
                object.extension        = element.getAttribute("extends");
                
                String xpathstring      = "(./ancestor::package/@default)[last()]";
                
                object.packagename      = (String)xparam.xpath.evaluate(xpathstring, nodes.item(i), XPathConstants.STRING);
                
                object.startable        = element.getAttribute("start").equalsIgnoreCase("true");
                
                /*--------------------------------------------------------------*/

                objects.add(object);
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }                       
        
        return objects;
    }
    
    private ArrayList<Apmlsystem> getsystems(Bloqxpathparameter xparam, Integer index)
    {      
        ArrayList<Apmlsystem> systems = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./package/system", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<nodes.getLength(); i++)  
            {
                Element element = (Element)nodes.item(i);            

                Apmlsystem system = new Apmlsystem();            

                /*--------------------------------------------------------------*/
                
                system.id               = element.getAttribute("id");
                
                system.alias            = element.getAttribute("alias");
                
                system.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
                
                system.classname        = element.getAttribute("class");
                
                system.extension        = element.getAttribute("extends");
                
                String xpathstring      = "(./ancestor::package/@default)[last()]";
                
                system.packagename      = (String)xparam.xpath.evaluate(xpathstring, nodes.item(i), XPathConstants.STRING);
                
                system.startable        = element.getAttribute("start").equalsIgnoreCase("true");
                
                /*--------------------------------------------------------------*/

                systems.add(system);
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }                       
        
        return systems;
    }    
    
    private ArrayList<Apmlimplement> getimplements(Bloqxpathparameter xparam, Integer index)
    {
        ArrayList<Apmlimplement> retval = new ArrayList<>();   
        
        Set<Apmlimplement> clearer = new HashSet<>();  
                        
        Element inline = ((Element)xparam.n0001_tagname.item(index));
        
        if(inline.getAttribute("implements")!=null)
        {
            Apmlimplement apmlimpl = new Apmlimplement(); 
            
            apmlimpl.classname = inline.getAttribute("implements");

            retval.add(apmlimpl);            
        }                    
        
        try
        {
            NodeList implementstags = (NodeList)xparam.xpath.evaluate("./implements", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<implementstags.getLength(); i++)  
            {
                Apmlimplement implement = new Apmlimplement();                                            

                Element element = (Element)implementstags.item(i);            

                /*--------------------------------------------------------------*/

                implement.id                = element.getAttribute("id");

                implement.alias             = element.getAttribute("alias");

                implement.autostartable     = element.getAttribute("autostart").equalsIgnoreCase("true");

                implement.classname         = element.getAttribute("class");

                implement.extension         = element.getAttribute("extends");                                

                String xpathstring          = "(./ancestor::package/@default)[last()]";

                implement.packagename       = (String)xparam.xpath.evaluate(xpathstring, implementstags.item(i), XPathConstants.STRING);

                implement.startable         = element.getAttribute("start").equalsIgnoreCase("true");

                /*--------------------------------------------------------------*/

                retval.add(implement);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
        
        //clearing tech - stands at acknowledged 
        
        clearer.addAll(retval);
        
        retval.clear();
        
        retval.addAll(clearer);
        
        return retval;
    }    

    private String getbodi(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0014_bndi==null || xparam.n0014_bndi.item(index)==null || xparam.n0004_id==null || xparam.n0004_id.item(index)==null)
        {
            return "//systems/"+xparam.n0001_tagname.item(index).getNodeValue()+"/undefined/"+index;                    
        }
        
        String retval = xparam.n0014_bndi.item(index).getNodeValue();        
        
        if(retval.endsWith("{this.id}"))
        {                        
            return retval.replace("{this.id}", xparam.n0004_id.item(index).getNodeValue().trim());            
        }
        else return retval;        
    }  
    
    private String getdefaultdir(Bloqxpathparameter xparam, Integer index)
    {        
        String dir = "apml.default.unbound";
        
        try
        {
            dir = (String)xparam.xpath.evaluate("(./ancestor::package/@default)[last()]", xparam.n0001_tagname.item(index), XPathConstants.STRING); 
            
            dir = new Filegrepper().getpackagenameaspathname(dir);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return dir;
    }      

    private String getsourcedir(Bloqxpathparameter xparam, Integer index)
    {        
        String dir = "apml.src.unbound";
        
        try
        {
            dir = (String)xparam.xpath.evaluate("(./ancestor::package/@sourcedir)[last()]", xparam.n0001_tagname.item(index), XPathConstants.STRING);              
            
            dir = new Filegrepper().getpackagenameaspathname(dir);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return dir;
    }    

    private String getbuilddir(Bloqxpathparameter xparam, Integer index)
    {        
        String dir = "apml.build.unbound";
        
        try
        {
            dir = (String)xparam.xpath.evaluate("(./ancestor::package/@builddir)[last()]", xparam.n0001_tagname.item(index), XPathConstants.STRING);
            
            dir = new Filegrepper().getpackagenameaspathname(dir);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return dir;
    }    

    private String gettagname(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0001_tagname==null || xparam.n0001_tagname.item(0)==null) 
        {
            throw new NullPointerException("No tag name found");                    
        }
        
        return xparam.n0001_tagname.item(index).getNodeName();
    }

    private String getautostarttag(Bloqxpathparameter xparam, Integer index)
    {        
        if(xparam.n0002_autostart==null || xparam.n0002_autostart.item(0)==null) 
        {
            throw new NullPointerException("No autostart tag found");
        }
                
        return xparam.n0002_autostart.item(0).getNodeName();
    }

    private String getclassname(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0003_classname==null || xparam.n0003_classname.item(0)==null) 
        {
            throw new NullPointerException("No class tag found");
        }
              
        String retval=null;
        
        try
        {            
            retval = new Filegrepper().getclassname(xparam.n0003_classname.item(index).getNodeValue());
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return retval;
    }

    private String getidtag(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0004_id==null || xparam.n0004_id.item(0)==null)
        {
            throw new NullPointerException("No id tag found");
        }
                
        return xparam.n0004_id.item(0).getNodeValue();
    }

    private String getinittag(Bloqxpathparameter xparam, Integer index) 
    {
        if(xparam.n0005_init==null || xparam.n0005_init.item(0)==null)
        {
            throw new NullPointerException("No init tag found");
        }
            
        return xparam.n0005_init.item(0).getNodeName();
    }

    private String getpackagename(Bloqxpathparameter xparam, Integer index)
    {        
        String packagename="apml.unbound.pkg";
        
        try
        {
            packagename = (String)xparam.xpath.evaluate("(./ancestor::package/@default)[last()]", xparam.n0001_tagname.item(index), XPathConstants.STRING);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return packagename;
    }

    private String getruntag(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0007_run==null || xparam.n0007_run.item(0)==null)
        {
            throw new NullPointerException("No run tag found"); 
        }
        
        return xparam.n0007_run.item(0).getNodeName();
    }    

    private String getstarttag(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0008_start==null || xparam.n0008_start.item(0)==null)
        {
            throw new NullPointerException("No start tag found"); 
        }
                
        return xparam.n0008_start.item(0).getNodeName();
    }    
         
    private String getsuperclass(Bloqxpathparameter xparam, Integer index)
    {
        if(xparam.n0012_superclass==null || xparam.n0012_superclass.item(0)==null) 
        {
            throw new NullPointerException("No tag name found");                    
        }
        
        return xparam.n0012_superclass.item(index).getNodeValue();
    }             
}