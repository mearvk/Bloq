/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.xpath.helpers;

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
    public NodeList attrs;
    public Object obj; 
    
    public Document document;
    public File xmlfile;
    public NodeList nodes1;
    public NodeList nodes2;
    public NodeList nodes3;
    public NodeList nodes4;
    public NodeList nodes5;
    public NodeList nodes6;
    public NodeList nodes7;
    public NodeList nodes8;
    
    public String sexpr0;
    public String sexpr1;   
    public String sexpr2;
    public String sexpr3;   
    public String sexpr4;
    public String sexpr5;   
    public String sexpr6;
    public String sexpr7;   
    public String sexpr8;
    
    public XPathExpression expr0; 
    public XPathExpression expr1;    
    public XPathExpression expr2;
    public XPathExpression expr3;    
    public XPathExpression expr4;
    public XPathExpression expr5;    
    public XPathExpression expr6;
    public XPathExpression expr7;    
    public XPathExpression expr8;
    
    /**
     * 
     */
    public xpathparameter()
    {
        
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
        this.nodes1 = null;
        this.attrs = null;
        this.obj = obj;                            
        this.sexpr1 = sexpression1;        
        this.sexpr2 = sexpression2;
        this.expr1 = expression1;                      
        this.expr2 = expression2;          
    }   
}
