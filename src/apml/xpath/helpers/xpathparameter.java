/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.xpath.helpers;

import apml.helpers.parser;
import java.io.File;
import javax.xml.xpath.XPathExpression;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public class xpathparameter
{   
    public Document document;
    public File xmlfile;
    public NodeList attrs;
    public Object obj;     
    
    public apml.helpers.parser parser;
    
    public NodeList n0000_count;         //nodes0  
    public NodeList n0001_tagname;       //nodes1
    public NodeList n0002_autostart;     //nodes2
    public NodeList n0003_classname;     //nodes3
    public NodeList n0004_id;            //nodes4
    public NodeList n0005_init;          //nodes5
    public NodeList n0006_package;       //nodes6
    public NodeList n0007_run;           //nodes7
    public NodeList n0008_start;         //nodes8
    public NodeList n0009_implements;    //nodes9
    public NodeList n0010_listeners;     //nodes10
    public NodeList n0011_objects;       //nodes11
    
    public String s0000_count;
    public String s0001_tagname;   
    public String s0002_autostart;
    public String s0003_classname;   
    public String s0004_id;
    public String s0005_init;   
    public String s0006_package;
    public String s0007_run;   
    public String s0008_start;
    public String s0009_implements;
    public String s0010_listeners;
    public String s0011_objects;
    
    public XPathExpression e0000_count; 
    public XPathExpression e0001_tagname;    
    public XPathExpression e0002_autostart;
    public XPathExpression e0003_classname;    
    public XPathExpression e0004_id;
    public XPathExpression e0005_init;    
    public XPathExpression e0006_package;
    public XPathExpression e0007_run;    
    public XPathExpression e0008_start;
    public XPathExpression e0009_implements;
    public XPathExpression e0010_listeners;
    public XPathExpression e0011_objects;
    
    /**
     * 
     */
    public xpathparameter()
    {
        
    }
    
    /**
     * 
     * @param parser
     */
    public xpathparameter(parser parser)
    {   
        this.parser = parser;        
        this.document = parser.document;
    }    
    
    /**
     * 
     * @param document 
     */
    public xpathparameter(Document document)
    {   
        this.document = document;
    }    
    
    /**
     * 
     * @param xmlfile
     * @param expression1
     * @param expression2
     * @param sexpression1
     * @param sexpression2
     * @param obj
     * @param document 
     */
    public xpathparameter(File xmlfile, XPathExpression expression1, XPathExpression expression2, String sexpression1, String sexpression2, Object obj, Document document)
    {        
        this.document = document;
        this.xmlfile = null;
        this.n0001_tagname = null;
        this.attrs = null;
        this.obj = obj;                            
        this.s0001_tagname = sexpression1;        
        this.s0002_autostart = sexpression2;
        this.e0001_tagname = expression1;                      
        this.e0002_autostart = expression2;          
    }   
}
