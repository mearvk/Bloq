package apml.compilers.java.codemodel;

import apml.helpers.Filegrepper;

import apml.modeling.Apmlimplement;

import apml.modeling.Apmllistener;

import apml.modeling.Apmlmodelfile;

import apml.modeling.Apmlobject;

import apml.xpath.helpers.Xpathparameter;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.logging.FileHandler;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public final class Apmlmodelpopulator 
{
    private final Integer hash = 0x00888fe8;  
    
    public final String loggingfileurl = "/home/oem/Desktop/Apml/output/logging/Apmlmodelpopulator.txt";        
        
    protected static final Logger LOGGER = Logger.getLogger(Apmlmodelpopulator.class.getName());    
    
    public ArrayList<Apmlmodelfile> apmlfiles;   
    
    public String defaultpackage;
    
    public static void main(String...args) 
    {                          
        try
        {     
            Apmlmodelpopulator populator = new Apmlmodelpopulator();
            
            File apmlfile = new File("");
                        
            populator.getapmlmodelfiles(apmlfile,"//apml");   
            
            populator.getapmlmodelfiles(apmlfile,"//definitions");            
            
            populator.getapmlmodelfiles(apmlfile,"//dynamiclistener");             
            
            populator.getapmlmodelfiles(apmlfile,"//listener");
            
            populator.getapmlmodelfiles(apmlfile,"//subscriber");                        
            
            populator.getapmlmodelfiles(apmlfile,"//system");               
            
            populator.getapmlmodelfiles(apmlfile,"//driver");               
            
            populator.getapmlmodelfiles(apmlfile,"//compiler");  
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }       
    
    public Apmlmodelpopulator()
    {
        try
        {
            LOGGER.addHandler(new FileHandler(this.loggingfileurl));
            
            LOGGER.setUseParentHandlers(false);
        }
        catch(IOException | SecurityException exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }
    
    public Apmlmodelpopulator(String sysobj)
    {
        try
        {
            LOGGER.addHandler(new FileHandler(this.loggingfileurl));
            
            LOGGER.setUseParentHandlers(false);
        }
        catch(IOException | SecurityException exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }  
    
    public Apmlmodelpopulator(File apmlfile, String sysobj) throws Exception
    {
        this.apmlfiles = this.getapmlmodelfiles(apmlfile, sysobj);
    }     
      
    public ArrayList<Apmlmodelfile> getapmlmodelfiles(File apmlfile, String apmltag) throws Exception
    {                          
        try
        {
            Xpathparameter xparam = new Xpathparameter(apmltag, apmlfile);          
               
            for(int i=0;i<xparam.getnodecount();i++)
            {
            
                switch(apmltag)
                {
                    case "//apml": 
                        return doapmltags(xparam, apmltag);
                    
                    case "//definitions": 
                        return dodefinitiontags(xparam, apmltag);
                        
                    case "//dynamiclistener": 
                        return dodynamiclistenertags(xparam, apmltag);                        
                        
                    case "//listener": 
                        return dolistenertags(xparam, apmltag); 
                        
                    case "//object": 
                        return doobjecttags(xparam, apmltag);                        
                        
                    case "//subscriber": 
                        return dosubscribertags(xparam, apmltag);                         
                    
                    case "//system": 
                        return dosystemtags(xparam, apmltag);   
                        
                    default: return null;
                }
            }                
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }      
        
        return null;
    }          
    
    private ArrayList<Apmlmodelfile> doapmltags(Xpathparameter xparam, String apmltag) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.sourcedir=this.getsourcedir(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.defaultdir=this.getdefaultdir(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.builddir=this.getbuilddir(xparam, index);}                            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                                                    
            try{modelfile.packagename=this.getpackagename(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmlimplementš=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
    
    private ArrayList<Apmlmodelfile> dodefinitiontags(Xpathparameter xparam, String apmltag) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
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
            try{modelfile.apmlimplementš=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }

    private ArrayList<Apmlmodelfile> dodynamiclistenertags(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
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
     
    private ArrayList<Apmlmodelfile> dolistenertags(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
                            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
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
   
    private ArrayList<Apmlmodelfile> doobjecttags(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
                            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}         
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
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
  
    private ArrayList<Apmlmodelfile> dosubscribertags(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
                              
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
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

    private ArrayList<Apmlmodelfile> dosystemtags(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)  //show two system tags 
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();        //show two apml files for each system

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                       
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
            try{modelfile.apmlimplementš=this.getimplements(xparam, index);}                    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    } 

    private String getbndi(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0014_bndi==null || xparam.n0014_bndi.item(0)==null) 
            return "//unbound";                    
        
        return xparam.n0014_bndi.item(index).getNodeValue();        
    }  
    
    private String getdefaultdir(Xpathparameter xparam, Integer index)
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

    private String getsourcedir(Xpathparameter xparam, Integer index)
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

    private String getbuilddir(Xpathparameter xparam, Integer index)
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

    private String getsuperclass(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0012_superclass==null || xparam.n0012_superclass.item(0)==null) 
            throw new NullPointerException("No tag name found");                    
        
        return xparam.n0012_superclass.item(index).getNodeName();
    }

    private String gettagname(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0001_tagname==null || xparam.n0001_tagname.item(0)==null) 
            throw new NullPointerException("No tag name found");                    
        
        return xparam.n0001_tagname.item(index).getNodeName();
    }

    private String getautostarttag(Xpathparameter xparam, Integer index)
    {        
        if(xparam.n0002_autostart==null || xparam.n0002_autostart.item(0)==null) 
            throw new NullPointerException("No autostart tag found");
                
        return xparam.n0002_autostart.item(0).getNodeName();
    }

    private String getclassname(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0003_classname==null || xparam.n0003_classname.item(0)==null) 
            throw new NullPointerException("No class tag found");
              
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

    private String getidtag(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0004_id==null || xparam.n0004_id.item(0)==null)
            throw new NullPointerException("No id tag found");
                
        return xparam.n0004_id.item(0).getNodeValue();
    }

    private String getinittag(Xpathparameter xparam, Integer index) 
    {
        if(xparam.n0005_init==null || xparam.n0005_init.item(0)==null)
            throw new NullPointerException("No init tag found");
            
        return xparam.n0005_init.item(0).getNodeName();
    }

    private String getpackagename(Xpathparameter xparam, Integer index)
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

    private String getruntag(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0007_run==null || xparam.n0007_run.item(0)==null)
            throw new NullPointerException("No run tag found"); 
        
        return xparam.n0007_run.item(0).getNodeName();
    }    

    private String getstarttag(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0008_start==null || xparam.n0008_start.item(0)==null)
            throw new NullPointerException("No start tag found"); 
                
        return xparam.n0008_start.item(0).getNodeName();
    }    

    private ArrayList<Apmlimplement> getimplements(Xpathparameter xparam, Integer index)
    {
        ArrayList<Apmlimplement> implementz = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./implements", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<nodes.getLength(); i++)  
            {
                Element element = (Element)nodes.item(i);            

                Apmlimplement object = new Apmlimplement();            

                object.alias            = element.getAttribute("alias");
                object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
                object.classname        = element.getAttribute("class");
                object.extension        = element.getAttribute("extends");
                
                String xpathstring = "(./implements["+(i+1)+"]/ancestor::package/@default)[last()]";
                
                object.packagename      = (String)xparam.xpath.evaluate(xpathstring, xparam.n0001_tagname.item(index), XPathConstants.STRING);
                object.startable        = element.getAttribute("start").equalsIgnoreCase("true");

                implementz.add(object);
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }                       
        
        return implementz;
    }     

    private ArrayList<Apmllistener> getlisteners(Xpathparameter xparam, Integer index)
    {
        ArrayList<Apmllistener> listeners = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./listener", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<nodes.getLength(); i++)  
            {
                Element element = (Element)nodes.item(i);            

                Apmllistener object = new Apmllistener();            

                object.alias            = element.getAttribute("alias");
                object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
                object.classname        = element.getAttribute("class");
                object.extension        = element.getAttribute("extends");
                
                String xpathstring = "(./listener["+(i+1)+"]/ancestor::package/@default)[last()]";
                
                object.packagename      = (String)xparam.xpath.evaluate(xpathstring, xparam.n0001_tagname.item(index), XPathConstants.STRING);
                object.startable        = element.getAttribute("start").equalsIgnoreCase("true");

                listeners.add(object);
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }                       
        
        return listeners;
    }        

    private ArrayList<Apmlobject> getobjects(Xpathparameter xparam, Integer index)
    {      
        ArrayList<Apmlobject> objects = new ArrayList<>();
        
        try
        {
            NodeList nodes = (NodeList)xparam.xpath.evaluate("./object", xparam.n0001_tagname.item(index), XPathConstants.NODESET);                                       
                        
            for(int i=0; i<nodes.getLength(); i++)  
            {
                Element element = (Element)nodes.item(i);            

                Apmlobject object = new Apmlobject();            

                object.alias            = element.getAttribute("alias");
                object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
                object.classname        = element.getAttribute("class");
                object.extension        = element.getAttribute("extends");
                
                String nearestpackagedefaultvalue = "(./object["+(i+1)+"]/ancestor::package/@default)[last()]";
                
                object.packagename      = (String)xparam.xpath.evaluate(nearestpackagedefaultvalue, xparam.n0001_tagname.item(index), XPathConstants.STRING);
                object.startable        = element.getAttribute("start").equalsIgnoreCase("true");

                objects.add(object);
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }                       
        
        return objects;
    }          
}