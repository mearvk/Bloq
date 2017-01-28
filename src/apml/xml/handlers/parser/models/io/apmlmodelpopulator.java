/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.xml.handlers.parser.models.io;

import apml.modeling.apmlmodelfile;
import apml.xpath.helpers.xpathparameter;
import apml.xpath.helpers.xpathquick;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
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
        Integer tagcount;
        apmlmodelfile[] modelfiles=null;
        
        Document document;
        NodeList list = null;
        Object result = null;   
        
        String sexpr0 = "count("+apmltag+")";
        String sexpr1 = apmltag; 
        String sexpr2 = apmltag+"/@autostart";
        String sexpr3 = apmltag+"/@class"; 
        String sexpr4 = apmltag+"/@id";
        String sexpr5 = apmltag+"/@init";
        String sexpr6 = apmltag+"/@package";
        String sexpr7 = apmltag+"/@run";
        String sexpr8 = apmltag+"/@start";        
                
        
        XPathExpression expr0 = null;
        XPathExpression expr1 = null;
        XPathExpression expr2 = null;
        XPathExpression expr3 = null;
        XPathExpression expr4 = null;
        XPathExpression expr5 = null;
        XPathExpression expr6 = null;
        XPathExpression expr7 = null;
        XPathExpression expr8 = null;
        
        xpathparameter xparam;                   
     
        //build Document document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();                
        document = builder.parse(apmlfile.getPath());        
        
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();        
        
        try
        {
            //
            xparam = new xpathparameter(document);//apmlfile, expr1, expr2, sexpr1, sexpr2, result, document);                            
                    
            //
            xparam.expr0 = xpath.compile(sexpr0);
            xparam.expr1 = xpath.compile(sexpr1); 
            xparam.expr2 = xpath.compile(sexpr2);
            xparam.expr3 = xpath.compile(sexpr3);
            xparam.expr4 = xpath.compile(sexpr4);
            xparam.expr5 = xpath.compile(sexpr5);            
            xparam.expr6 = xpath.compile(sexpr6);
            xparam.expr7 = xpath.compile(sexpr7);
            xparam.expr8 = xpath.compile(sexpr8);            
            
            //xparam = xpathquick.compile(xparam);
            xparam = xpathquick.evaluate(xparam); 
                        
            //now source model files to out
            modelfiles = new apmlmodelfile[tagcount = xpathquick.count(xparam)];    
            for(int i=0; i<tagcount; i++)
            {
                modelfiles[i] = new apmlmodelfile();

                if(xparam.nodes1!=null && xparam.nodes1.item(0)!=null) modelfiles[i].apml_tagname       = xparam.nodes1.item(0).getNodeValue();
                if(xparam.nodes2!=null && xparam.nodes2.item(0)!=null) modelfiles[i].apml_autostart     = xparam.nodes2.item(0).getNodeValue();            
                if(xparam.nodes3!=null && xparam.nodes3.item(0)!=null) modelfiles[i].apml_classname     = xparam.nodes3.item(0).getNodeName();            
                if(xparam.nodes4!=null && xparam.nodes4.item(0)!=null) modelfiles[i].apml_idname        = xparam.nodes4.item(0).getNodeValue();            
                if(xparam.nodes5!=null && xparam.nodes5.item(0)!=null) modelfiles[i].apml_initializable = xparam.nodes5.item(0).getNodeValue();            
                if(xparam.nodes6!=null && xparam.nodes6.item(0)!=null) modelfiles[i].apml_packagename   = xparam.nodes6.item(0).getNodeValue();                   
                if(xparam.nodes7!=null && xparam.nodes7.item(0)!=null) modelfiles[i].apml_run           = xparam.nodes7.item(0).getNodeValue();            
                if(xparam.nodes8!=null && xparam.nodes8.item(0)!=null) modelfiles[i].apml_start         = xparam.nodes8.item(0).getNodeValue();                 
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
     * @param xpathnode The APML/XML system specification
     * @return 
     */
    public apmlmodelfile[] getapmlfiles(String xpathnode)
    {
        return null;
    }    
}
