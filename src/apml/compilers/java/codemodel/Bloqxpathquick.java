/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.java.codemodel;

import apml.xpath.helpers.*;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Max Rupplin
 */
public class Bloqxpathquick
{  
    protected final Integer hash = 0x888fe8;
    
    /**
     * 
     * @param param
     * @return
     * @throws Exception 
     */
    public static Xpathparameter evaluate(Xpathparameter param) throws Exception
    {  
        
        if(param.e0001_tagname!=null)
            param.n0001_tagname = (NodeList)param.e0001_tagname.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.e0002_autostart!=null)
            param.n0002_autostart = (NodeList)param.e0002_autostart.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.e0003_classname!=null)
            param.n0003_classname = (NodeList)param.e0003_classname.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.e0004_id!=null)
            param.n0004_id = (NodeList)param.e0004_id.evaluate(param.document, XPathConstants.NODESET);

        if(param.e0005_init!=null)
            param.n0005_init = (NodeList)param.e0005_init.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.e0006_package!=null)
            param.n0006_package = (NodeList)param.e0006_package.evaluate(param.document, XPathConstants.NODESET);  
        
        if(param.e0007_run!=null)
            param.n0007_run = (NodeList)param.e0007_run.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.e0008_start!=null)
            param.n0008_start = (NodeList)param.e0008_start.evaluate(param.document, XPathConstants.NODESET);           
        
        if(param.e0009_implements!=null)
            param.n0009_implements = (NodeList)param.e0009_implements.evaluate(param.document, XPathConstants.NODESET);  
        
        if(param.e0010_listeners!=null)
            param.n0010_listeners = (NodeList)param.e0010_listeners.evaluate(param.document, XPathConstants.NODESET);
        
        if(param.e0011_objects!=null)
            param.n0011_objects = (NodeList)param.e0011_objects.evaluate(param.document, XPathConstants.NODESET);          
        
        return param;
    }    
    
    /**
     * 
     * @param param
     * @return
     * @throws Exception 
     */
    public static int count(Xpathparameter param) throws Exception
    {        
        return ((Double)param.e0000_count.evaluate(param.document, XPathConstants.NUMBER)).intValue();
    }
}