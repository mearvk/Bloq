/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.xml.handlers;

import apml.helpers.parser;
import apml.modeling.apmlmodelfile;
import apml.xpath.helpers.xpathbuilder;
import apml.xpath.helpers.xpathparameter;
import apml.xpath.helpers.xpathquick;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public class apmlmodelpopulator 
{
    public apmlmodelfile[] apmlfiles = null;
    protected NodeList nodes;    
    protected String apmltag = null;
    
    public static void main(String...args) 
    {                          
        try
        {     
            //
            apmlmodelpopulator populator = new apmlmodelpopulator();
            
            //
            populator.getapmlfiles("//definitions");
            
            //          
            populator.getapmlfiles("//system");   
            
            //         
            populator.getapmlfiles("//driver");   
            
            //            
            populator.getapmlfiles("//compiler");  
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.err);
        }
    }       
    
    /**
     * 
     */
    public apmlmodelpopulator()
    {
        
    }
    
    /**
     * 
     * @param sysobj 
     */
    public apmlmodelpopulator(String sysobj)
    {
        this.apmltag = sysobj;
    }
    
    /**
     * 
     * @param nodes 
     */
    public apmlmodelpopulator(NodeList nodes)
    {
        this.nodes = nodes;
    }
    
    /**
     * 
     * @param nodes
     * @param sysobj 
     */
    public apmlmodelpopulator(NodeList nodes, String sysobj)
    {
         this.nodes = nodes;
    }    
    
    /**
     * 
     * @param apmlfile
     * @param sysobj
     */
    public apmlmodelpopulator(File apmlfile, String sysobj) throws Exception
    {
        this.apmlfiles = this.getapmlfiles(apmlfile, sysobj);
    }     
    
    /**
     * 
     * @param apmlfile The file that contains the APML/XML system or the system specification
     * @param apmltag The APML/XML tag that describes the system, subsystem or system elements we care to model in apmlmodelpopulator objects
     * @return 
     * @throws Exception 
     */    
    public apmlmodelfile[] getapmlfiles(File apmlfile, String apmltag) throws Exception
    {   
        apmlmodelfile[] modelfiles=null;
        xpathparameter xparam;  
        xpathbuilder xbuilder;                        
        
        Document document;
        Integer tagcount;
        NodeList list = null;
        Object result = null;                                
        
        try
        {
            //            
            xbuilder = new xpathbuilder(apmltag, new xpathparameter(new parser(apmlfile.getPath())));
            xparam = xpathquick.evaluate(xbuilder.xparam);
                        
            //
            modelfiles = new apmlmodelfile[tagcount = xpathquick.count(xparam)];    
            for(int i=0; i<tagcount; i++)
            {
                switch(apmltag)
                {
                    case "//definitions": 
                        dodefinitions(xparam, apmltag);
                        break;
                    
                    case "//system": 
                        dosystem(xparam, apmltag);
                        break;                    
                    
                    case "//driver": 
                        dodriver(xparam, apmltag);
                        break;
                    
                    case "//compiler": 
                        docompiler(xparam, apmltag);
                        break;
                }
                
                //modelfiles[i] = new apmlmodelfile();
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
    private void docompiler(xpathparameter xparam, String apmltag)
    {
        //pull standard compiler template and include it; regard <template> items for custom build order/specs
    }       
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @throws Exception 
     */
    private void dodefinitions(xpathparameter xparam, String apmltag) throws Exception
    {
        //look up inside APML if tag existed inside <definitions> and infer from that
    }
    
    /**
     * 
     * @param xparam
     * @param apmltag 
     */    
    private void dodriver(xpathparameter xparam, String apmltag)
    {
        //pull standard driver template and include it; regard <template> items for custom build order/specs
    }    
    
    /**
     * 
     * @param xparam
     * @param apmltag
     * @return 
     */
    private apmlmodelfile dosystem(xpathparameter xparam, String apmltag)
    {
        try{this.gettagname(xparam, nodes);}        catch(Exception e){e.printStackTrace(System.err);}
        try{this.getautostarttag(xparam, nodes);}   catch(Exception e){e.printStackTrace(System.err);}            
        try{this.getclasstag(xparam, nodes);}       catch(Exception e){e.printStackTrace(System.err);}            
        try{this.getidtag(xparam, nodes);}          catch(Exception e){e.printStackTrace(System.err);}            
        try{this.getinittag(xparam, nodes);}        catch(Exception e){e.printStackTrace(System.err);}
        try{this.getpackagename(xparam, nodes);}    catch(Exception e){e.printStackTrace(System.err);}
        try{this.getruntag(xparam, nodes);}         catch(Exception e){e.printStackTrace(System.err);}
        try{this.getstarttag(xparam, nodes);}       catch(Exception e){e.printStackTrace(System.err);}
        try{this.getimplements(xparam, nodes);}     catch(Exception e){e.printStackTrace(System.err);}
        try{this.getlisteners(xparam, nodes);}      catch(Exception e){e.printStackTrace(System.err);}
        try{this.getobjects(xparam, nodes);}        catch(Exception e){e.printStackTrace(System.err);} 
        
        return null;
    }                     

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getobjects(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0011_objects==null || xparam.n0011_objects.item(0)==null) throw new NullPointerException("No object tag(s) found");
            
        return xparam.n0011_objects.item(0).getNodeValue();
    }    
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String gettagname(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0001_tagname==null || xparam.n0001_tagname.item(0)==null) throw new NullPointerException("No tag name found");
            
        return xparam.n0001_tagname.item(0).getNodeValue();
    }
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getautostarttag(xpathparameter xparam, NodeList nodes)
    {        
        if(xparam.n0002_autostart==null || xparam.n0002_autostart.item(0)==null) throw new NullPointerException("No autostart tag found");
                
        return xparam.n0002_autostart.item(0).getNodeValue();        
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getclasstag(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0003_classname==null || xparam.n0003_classname.item(0)==null) throw new NullPointerException("No class tag found");
        
        return xparam.n0003_classname.item(0).getNodeValue();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getidtag(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0004_id==null || xparam.n0004_id.item(0)==null) throw new NullPointerException("No id tag found");
                
        return xparam.n0004_id.item(0).getNodeValue();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getinittag(xpathparameter xparam, NodeList nodes) 
    {
        if(xparam.n0005_init==null || xparam.n0005_init.item(0)==null)throw new NullPointerException("No init tag found");
            
        return xparam.n0005_init.item(0).getNodeValue();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getpackagename(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0006_package==null || xparam.n0006_package.item(0)==null)throw new NullPointerException("No package tag found");
        
        return xparam.n0006_package.item(0).getNodeValue();
    }

    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getruntag(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0007_run==null || xparam.n0007_run.item(0)==null)throw new NullPointerException("No run tag found"); 
        
        return xparam.n0007_run.item(0).getNodeValue();
    }    
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String getstarttag(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0008_start==null || xparam.n0008_start.item(0)==null)throw new NullPointerException("No start tag found"); 
                
        return xparam.n0008_start.item(0).getNodeValue();
    }    
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String[] getimplements(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0009_implements==null || xparam.n0009_implements.item(0)==null)throw new NullPointerException("No implements tag(s) found");
                
        String[] strings = new String[xparam.n0009_implements.getLength()];
                
        for(int i=0; i<xparam.n0009_implements.getLength(); i++)
        {
            strings[i]=xparam.n0009_implements.item(i).getNodeValue();
        }        
        
        return strings;
    }     
    
    /**
     * 
     * @param xparam
     * @param nodes
     * @return 
     */
    private String[] getlisteners(xpathparameter xparam, NodeList nodes)
    {
        if(xparam.n0010_listeners==null || xparam.n0010_listeners.item(0)==null)throw new NullPointerException("No listener tag(s) found"); 
        
        String[] strings = new String[xparam.n0010_listeners.getLength()];
                
        for(int i=0; i<xparam.n0010_listeners.getLength(); i++)
        {
            strings[i]=xparam.n0010_listeners.item(i).getNodeValue();
        }        
        
        return strings;
    }        
   
    
    /**
     * 
     * @param xpathnode The APML/XML system specification
     * @return 
     */
    public apmlmodelfile[] getapmlfiles(String xpathnode)
    {
        return null;
    }    
}