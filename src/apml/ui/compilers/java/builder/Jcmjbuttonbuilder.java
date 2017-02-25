package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import org.w3c.dom.Element;

/**
 *
 * @author max rupplin
 */
public class Jcmjbuttonbuilder 
{
    Element xml;
            
    public Jcmjbuttonbuilder(Element xml)
    {
        this.xml = xml;
    }
    
    public JCodeModel build()
    {
        //would prefer to go right to jcm from here
        xml.getAttribute("setAction");                                                
                                
        xml.getAttribute("setBorderpainted");
                
        xml.getAttribute("setDisabledicon");
                
        xml.getAttribute("setEnabled");                
                
        xml.getAttribute("setIcon");
               
        xml.getAttribute("setLabel");
                
        xml.getAttribute("setLayout");
                
        xml.getAttribute("setMargin");

        xml.getAttribute("setModel");
                
        xml.getAttribute("setName");                                                
                
        xml.getAttribute("setPressedIcon");
                
        xml.getAttribute("setRolloverIcon");                
                
        xml.getAttribute("setText");  
        
        return null;
    }
}
