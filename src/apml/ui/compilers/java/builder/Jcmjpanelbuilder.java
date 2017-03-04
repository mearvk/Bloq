package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JPanel;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjpanelbuilder extends Jcmabstractbuilder
{   
    public static void main(String...args)
    {
        new Jcmjpanelbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jpanel", JPanel.class);
    }     
    
    public Jcmjpanelbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
