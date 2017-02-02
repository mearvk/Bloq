package apml.compilers.codemodel;

import apml.helpers.Filegrepper;
import apml.modeling.Apmlimplement;
import apml.modeling.Apmllistener;
import apml.modeling.Apmlmodelfile;
import apml.modeling.Apmlobject;
import apml.xpath.helpers.Xpathparameter;
import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public class Apmlmodelpopulator 
{
    public ArrayList<Apmlmodelfile> apmlfiles;    
    public NodeList nodes;    
    
    public static void main(String...args) 
    {                          
        try
        {     
            //
            Apmlmodelpopulator populator = new Apmlmodelpopulator();
            
            //
            File apmlfile = new File("");
            
            //
            populator.getapmlmodelfiles(apmlfile,"//definitions");
            
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
     * @param nodes 
     */
    public Apmlmodelpopulator(NodeList nodes)
    {
        this.nodes = nodes;
    }
    
    /**
     * 
     * @param nodes
     * @param sysobj 
     */
    public Apmlmodelpopulator(NodeList nodes, String sysobj)
    {
         this.nodes = nodes;
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
                        
            //    
            for(int i=0;i<xparam.getnodecount();i++)
            {
                switch(apmltag)
                {
                    case "//compiler": 
                        modelfiles.add(docompiler(xparam, apmltag));
                        break;
                        
                    case "//definitions": 
                        modelfiles.add(dodefinitions(xparam, apmltag));
                        break;
                        
                    case "//driver": 
                        modelfiles.add(dodriver(xparam, apmltag));
                        break;                        
                    
                    case "//system": 
                        modelfiles.add(dosystem(xparam, apmltag));
                        break;    
                        
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
     */
    private Apmlmodelfile docompiler(Xpathparameter xparam, String apmltag)
    {
        //pull standard compiler template and include it; regard <template> items for custom build order/specs
        return null;
    }       
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @throws Exception 
     */
    private Apmlmodelfile dodefinitions(Xpathparameter xparam, String apmltag) throws Exception
    {
        //look up inside APML if tag existed inside <definitions> and infer from that
        return null;
    }
    
    /**
     * 
     * @param xparam
     * @param apmltag 
     */    
    private Apmlmodelfile dodriver(Xpathparameter xparam, String apmltag)
    {
        //pull standard driver template and include it; regard <template> items for custom build order/specs
        return null;
    }    
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @return 
     */
    private Apmlmodelfile dosystem(Xpathparameter xparam, String apmltag)
    {
        Apmlmodelfile modelfile=new Apmlmodelfile();
        
        try{modelfile.tagname=this.gettagname(xparam);}             catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.autostart=this.getautostarttag(xparam);}      catch(Exception e){e.printStackTrace(System.err);}            
        try{modelfile.classname=this.getclassname(xparam);}         catch(Exception e){e.printStackTrace(System.err);}            
        try{modelfile.id=this.getidtag(xparam);}                    catch(Exception e){e.printStackTrace(System.err);}            
        try{modelfile.init=this.getinittag(xparam);}                catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.packagė=this.getpackagename(xparam);}         catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.run=this.getruntag(xparam);}                  catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.start=this.getstarttag(xparam);}              catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.apmlimplementš=this.getimplements(xparam);}   catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.apmllisteners=this.getlisteners(xparam);}     catch(Exception e){e.printStackTrace(System.err);}
        try{modelfile.apmlobjects=this.getobjects(xparam);}         catch(Exception e){e.printStackTrace(System.err);} 
        
        return modelfile;
    } 
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String gettagname(Xpathparameter xparam)
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
    private String getautostarttag(Xpathparameter xparam)
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
    private String getclassname(Xpathparameter xparam)
    {
        if(xparam.n0003_classname==null || xparam.n0003_classname.item(0)==null) 
            throw new NullPointerException("No class tag found");
              
        String retval=null;
        
        try
        {
            retval = new Filegrepper().getclassname(xparam.n0003_classname.item(0).getNodeValue());
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
    private String getidtag(Xpathparameter xparam)
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
    private String getinittag(Xpathparameter xparam) 
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
    private String getpackagename(Xpathparameter xparam)
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
    private String getruntag(Xpathparameter xparam)
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
    private String getstarttag(Xpathparameter xparam)
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
    private ArrayList<Apmlimplement> getimplements(Xpathparameter xparam)
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
    private ArrayList<Apmllistener> getlisteners(Xpathparameter xparam)
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
    private ArrayList<Apmlobject> getobjects(Xpathparameter xparam)
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