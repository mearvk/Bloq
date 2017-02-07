package apml.compilers.java.codemodel;

import apml.helpers.Filegrepper;
import apml.modeling.Apmlimplement;
import apml.modeling.Apmllistener;
import apml.modeling.Apmlmodelfile;
import apml.modeling.Apmlobject;
import apml.xpath.helpers.Xpathparameter;
import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Element;

/**
 *
 * @author Max Rupplin
 */
public class Apmlmodelpopulator 
{
    public ArrayList<Apmlmodelfile> apmlfiles;   
    public String defaultpackage;
    
    public static void main(String...args) 
    {                          
        try
        {     
            //
            Apmlmodelpopulator populator = new Apmlmodelpopulator();
            
            //
            File apmlfile = new File("");
                        
            //
            populator.getapmlmodelfiles(apmlfile,"//apml");
            
            //
            populator.getapmlmodelfiles(apmlfile,"//definitions");
            
            //
            populator.getapmlmodelfiles(apmlfile,"//dynamiclistener"); 
            
            //
            populator.getapmlmodelfiles(apmlfile,"//listener");

            //
            populator.getapmlmodelfiles(apmlfile,"//subscriber");            
            
            //          
            populator.getapmlmodelfiles(apmlfile,"//system");   
            
            //         
            populator.getapmlmodelfiles(apmlfile,"//driver");   
            
            //            
            populator.getapmlmodelfiles(apmlfile,"//compiler");  
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.err);
        }
    }       
    
    /**
     * 
     */
    public Apmlmodelpopulator()
    {
        
    }
    
    /**
     * 
     * @param sysobj 
     */
    public Apmlmodelpopulator(String sysobj)
    {
        //this.apmltag = sysobj;
    }  
    
    /**
     * 
     * @param apmlfile
     * @param sysobj
     * @throws java.lang.Exception
     */
    public Apmlmodelpopulator(File apmlfile, String sysobj) throws Exception
    {
        this.apmlfiles = this.getapmlmodelfiles(apmlfile, sysobj);
    }     
    
    /**
     * 
     * @param apmlfile The file that contains the APML/XML system or the system specification
     * @param apmltag The APML/XML tag that describes the system, subsystem or system elements we care to model in Apmlmodelpopulator objects
     * @return 
     * @throws Exception 
     */    
    public ArrayList<Apmlmodelfile> getapmlmodelfiles(File apmlfile, String apmltag) throws Exception
    {   
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();                    
        
        try
        {
            //            
            Xpathparameter xparam = new Xpathparameter(apmltag,apmlfile);
                        
            //for each nodelist of type //system, //compiler, etc    
            for(int i=0;i<xparam.getnodecount();i++)
            {
                switch(apmltag)
                {
                    case "//apml": 
                        return doapml(xparam, apmltag);
                    
                    case "//definitions": 
                        return dodefinitions(xparam, apmltag);
                        
                    case "//dynamiclistener": 
                        return dodynamiclistener(xparam, apmltag);                        
                        
                    case "//listener": 
                        return dolistener(xparam, apmltag);  
                        
                    case "//subscriber": 
                        return dosubscriber(xparam, apmltag);                         
                    
                    case "//system": 
                        return dosystem(xparam, apmltag);                        
                }
            }                
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
             
        return modelfiles;
    }          
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @throws Exception 
     */
    private ArrayList<Apmlmodelfile> doapml(Xpathparameter xparam, String apmltag) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index)+"/apml";}               catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.defaultpackage=this.getdefaultpackage(xparam, index);}                catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){e.printStackTrace(System.err);}                                                                                    
            try{modelfile.packagė=this.getpackagename(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmlimplementš=this.getimplements(xparam, index);}    catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmllisteners=this.getlisteners(xparam, index);}      catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmlobjects=this.getobjects(xparam, index);}          catch(Exception e){e.printStackTrace(System.err);} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @throws Exception 
     */
    private ArrayList<Apmlmodelfile> dodefinitions(Xpathparameter xparam, String apmltag) throws Exception
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.defaultpackage=this.getdefaultpackage(xparam, index)+"/definitions";} catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.packagė=this.getpackagename(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){e.printStackTrace(System.err);}                                                
            try{modelfile.apmlimplementš=this.getimplements(xparam, index);}                    catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                      catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }
    
    /**
     * 
     * @param xparam
     * @param apmltag 
     */
    private ArrayList<Apmlmodelfile> dodynamiclistener(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.defaultpackage=this.getdefaultpackage(xparam, index)+"/dynamics";}    catch(Exception e){e.printStackTrace(System.err);}                                                
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.packagė=this.getpackagename(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){e.printStackTrace(System.err);}                        
            //try{modelfile.apmlimplementš=this.getimplements(xparam, index);}    catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmllisteners=this.getlisteners(xparam, index);}      catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmlobjects=this.getobjects(xparam, index);}          catch(Exception e){e.printStackTrace(System.err);} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }     
    
    /**
     * 
     * @param xparam
     * @param apmltag 
     */    
    private ArrayList<Apmlmodelfile> dolistener(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
                            
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.defaultpackage=this.getdefaultpackage(xparam, index)+"/listeners";}   catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){e.printStackTrace(System.err);}                        
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.packagė=this.getpackagename(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){e.printStackTrace(System.err);}                        
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){e.printStackTrace(System.err);}            
            //try{modelfile.apmlimplementš=this.getimplements(xparam, index);}    catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmllisteners=this.getlisteners(xparam, index);}      catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmlobjects=this.getobjects(xparam, index);}          catch(Exception e){e.printStackTrace(System.err);} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
    
    /**
     * 
     * @param xparam
     * @param apmltag 
     */    
    private ArrayList<Apmlmodelfile> dosubscriber(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();
                              
            try{modelfile.autostart=this.getautostarttag(xparam, index);}                       catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.classname=this.getclassname(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}           
            try{modelfile.defaultpackage=this.getdefaultpackage(xparam, index)+"/subscribers";} catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.id=this.getidtag(xparam, index);}                                     catch(Exception e){e.printStackTrace(System.err);}                        
            try{modelfile.init=this.getinittag(xparam, index);}                                 catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.packagė=this.getpackagename(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.run=this.getruntag(xparam, index);}                                   catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.start=this.getstarttag(xparam, index);}                               catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                        catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.tagname=this.gettagname(xparam, index);}                              catch(Exception e){e.printStackTrace(System.err);}                                                 
            //try{modelfile.apmlimplementš=this.getimplements(xparam, index);}    catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmllisteners=this.getlisteners(xparam, index);}      catch(Exception e){e.printStackTrace(System.err);}
            //try{modelfile.apmlobjects=this.getobjects(xparam, index);}          catch(Exception e){e.printStackTrace(System.err);} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    }    
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @return 
     */
    private ArrayList<Apmlmodelfile> dosystem(Xpathparameter xparam, String apmltag)
    {
        ArrayList<Apmlmodelfile> modelfiles = new ArrayList();        
        for(int index=0; index<xparam.getnodecount(); index++)
        {
            Apmlmodelfile modelfile=new Apmlmodelfile();

            try{modelfile.autostart=this.getautostarttag(xparam, index);}                   catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.bndi=this.getbndi(xparam, index);}                                    catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.classname=this.getclassname(xparam, index);}                      catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.defaultpackage=this.getdefaultpackage(xparam, index)+"/systems";} catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.id=this.getidtag(xparam, index);}                                 catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.init=this.getinittag(xparam, index);}                             catch(Exception e){e.printStackTrace(System.err);}            
            try{modelfile.packagė=this.getpackagename(xparam, index);}                      catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.run=this.getruntag(xparam, index);}                               catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.start=this.getstarttag(xparam, index);}                           catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.superclass=this.getsuperclass(xparam, index);}                    catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.tagname=this.gettagname(xparam, index);}                          catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.apmlimplementš=this.getimplements(xparam, index);}                catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.apmllisteners=this.getlisteners(xparam, index);}                  catch(Exception e){e.printStackTrace(System.err);}
            try{modelfile.apmlobjects=this.getobjects(xparam, index);}                      catch(Exception e){e.printStackTrace(System.err);} 
            
            modelfiles.add(modelfile);
        }       
        
        return modelfiles;
    } 

     /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getbndi(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0013_defaultpackage==null || xparam.n0013_defaultpackage.item(0)==null) 
            return xparam.getdefaultpackagename();                    
        
        return xparam.n0013_defaultpackage.item(0).getNodeValue();        
    }  
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getdefaultpackage(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0013_defaultpackage==null || xparam.n0013_defaultpackage.item(0)==null) 
            return xparam.getdefaultpackagename();                    
        
        return xparam.n0013_defaultpackage.item(0).getNodeValue();        
    }    
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getsuperclass(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0012_superclass==null || xparam.n0012_superclass.item(0)==null) 
            throw new NullPointerException("No tag name found");                    
        
        return xparam.n0012_superclass.item(0).getNodeName();
    }
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String gettagname(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0001_tagname==null || xparam.n0001_tagname.item(0)==null) 
            throw new NullPointerException("No tag name found");                    
        
        return xparam.n0001_tagname.item(0).getNodeName();
    }
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getautostarttag(Xpathparameter xparam, Integer index)
    {        
        if(xparam.n0002_autostart==null || xparam.n0002_autostart.item(0)==null) 
            throw new NullPointerException("No autostart tag found");
                
        return xparam.n0002_autostart.item(0).getNodeName();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
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
            e.printStackTrace(System.err);
        }
        
        return retval;
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getidtag(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0004_id==null || xparam.n0004_id.item(0)==null)
            throw new NullPointerException("No id tag found");
                
        return xparam.n0004_id.item(0).getNodeValue();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getinittag(Xpathparameter xparam, Integer index) 
    {
        if(xparam.n0005_init==null || xparam.n0005_init.item(0)==null)
            throw new NullPointerException("No init tag found");
            
        return xparam.n0005_init.item(0).getNodeName();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getpackagename(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0006_package==null || xparam.n0006_package.item(0)==null)
            throw new NullPointerException("No package tag found");
        
        return xparam.n0006_package.item(0).getNodeValue();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getruntag(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0007_run==null || xparam.n0007_run.item(0)==null)
            throw new NullPointerException("No run tag found"); 
        
        return xparam.n0007_run.item(0).getNodeName();
    }    
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getstarttag(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0008_start==null || xparam.n0008_start.item(0)==null)
            throw new NullPointerException("No start tag found"); 
                
        return xparam.n0008_start.item(0).getNodeName();
    }    
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private ArrayList<Apmlimplement> getimplements(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0009_implements==null || xparam.n0009_implements.item(0)==null)
            throw new NullPointerException("No implements tag(s) found"); 
        
        ArrayList<Apmlimplement> objects = new ArrayList<>();               
        for(int i=0; i<xparam.n0009_implements.getLength(); i++)  
        {
            Element element = (Element)xparam.n0009_implements.item(i);            
            
            Apmlimplement object = new Apmlimplement();            
            
            object.alias            = element.getAttribute("alias");
            object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
            object.classname        = element.getAttribute("class");
            object.extension        = element.getAttribute("extends");
            object.startable        = element.getAttribute("start").equalsIgnoreCase("true");
                   
            objects.add(object);
        }                       
        
        return objects;
    }     
    
    /**
     * handle all direct child nodes (objects) for a given system
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private ArrayList<Apmllistener> getlisteners(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0010_listeners==null || xparam.n0010_listeners.item(0)==null)
            throw new NullPointerException("No listener tag(s) found"); 
        
        ArrayList<Apmllistener> objects = new ArrayList<>();               
        for(int i=0; i<xparam.n0010_listeners.getLength(); i++)  
        {
            Element element = (Element)xparam.n0010_listeners.item(i);            
            
            Apmllistener object = new Apmllistener();            
            
            object.alias            = element.getAttribute("alias");
            object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
            object.classname        = element.getAttribute("class");
            object.extension        = element.getAttribute("extends");
            object.startable        = element.getAttribute("start").equalsIgnoreCase("true");
                   
            objects.add(object);
        }                       
        
        return objects;
    }        
    
    /**
     * handle all direct child nodes (objects) for a given system
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private ArrayList<Apmlobject> getobjects(Xpathparameter xparam, Integer index)
    {
        if(xparam.n0011_objects==null || xparam.n0011_objects.item(0)==null)
            throw new NullPointerException("No object tag(s) found"); 
        
        ArrayList<Apmlobject> objects = new ArrayList<>();               
        for(int i=0; i<xparam.n0011_objects.getLength(); i++)  
        {
            Element element = (Element)xparam.n0011_objects.item(i);            
            
            Apmlobject object = new Apmlobject();            
            
            object.alias            = element.getAttribute("alias");
            object.autostartable    = element.getAttribute("autostart").equalsIgnoreCase("true");
            object.classname        = element.getAttribute("class");
            object.extension        = element.getAttribute("extends");
            object.startable        = element.getAttribute("start").equalsIgnoreCase("true");
                   
            objects.add(object);
        }                       
        
        return objects;
    }          
}