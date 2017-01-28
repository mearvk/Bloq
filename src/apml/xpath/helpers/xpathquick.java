/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.xpath.helpers;

import java.security.InvalidParameterException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;

public class xpathquick
{
    /**
     * 
     * @param param
     * @return
     * @throws Exception 
     */
    public static xpathparameter compile(xpathparameter param) throws Exception
    {        
        if(param.xmlfile==null) throw new Exception("No XML file found.");        
        
        if(param.document==null) throw new Exception("No XML Document found.");
        
        if(param.sexpr1==null) throw new Exception("No XPath String to match with.");
        
        if(param.expr1==null) throw new Exception("No XPath XPathExpression to match with.");       
        
        param.expr1 = XPathFactory.newInstance().newXPath().compile(param.sexpr1);
        
        param.expr2 = XPathFactory.newInstance().newXPath().compile(param.sexpr2);
        
        return param;
    }
    
    /**
     * 
     * @param param
     * @return
     * @throws Exception 
     */
    public static xpathparameter evaluate(xpathparameter param) throws Exception
    {  
        if(param.expr1==null && param.expr2==null)
            throw new Exception("XPathParameter.evaluate :: Unable to evaluate expression; both expr1 and expr2 are null.");
        
        if(param.expr1!=null)
            param.nodes1 = (NodeList)param.expr1.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.expr2!=null)
            param.nodes2 = (NodeList)param.expr2.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.expr3!=null)
            param.nodes3 = (NodeList)param.expr3.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.expr4!=null)
            param.nodes4 = (NodeList)param.expr4.evaluate(param.document, XPathConstants.NODESET);

        if(param.expr5!=null)
            param.nodes5 = (NodeList)param.expr5.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.expr6!=null)
            param.nodes6 = (NodeList)param.expr6.evaluate(param.document, XPathConstants.NODESET);  
        
        if(param.expr7!=null)
            param.nodes7 = (NodeList)param.expr7.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.expr8!=null)
            param.nodes8 = (NodeList)param.expr8.evaluate(param.document, XPathConstants.NODESET);           
        
        return param;
    }    
    
    /**
     * 
     * @param expr1
     * @param param
     * @return
     * @throws Exception 
     */
    public static xpathparameter evaluate(XPathExpression expr1, xpathparameter param) throws Exception
    {                        
        return param;
    }
    
    /**
     * 
     * @param param
     * @return
     * @throws Exception 
     */
    public static int count(xpathparameter param) throws Exception
    {
        if(param==null) 
            throw new InvalidParameterException("XPathQuick::count: XPathParameter param was null; returning.");
        if(param.expr0==null) 
            throw new InvalidParameterException("XPathQuick::count: XPathParameter param.expr0 was null; returning.");
        
        try
        {            
            int count = ((Double)param.expr0.evaluate(param.document, XPathConstants.NUMBER)).intValue();

            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }   
        
        return -1;
    }
}