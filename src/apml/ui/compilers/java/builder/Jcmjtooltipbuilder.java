package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JToolTip;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtooltipbuilder extends Jcmabstractbuilder
{   
    public static void main(String...args)
    {
        new Jcmjtooltipbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtooltip", JToolTip.class);
    }     
    
    public Jcmjtooltipbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
