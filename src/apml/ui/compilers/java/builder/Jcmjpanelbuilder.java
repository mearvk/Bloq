package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjpanelbuilder extends Jcmabstractbuilder
{   
    public Class _class = JPanel.class;
    
    public static void main(String...args)
    {
        //new Jcmjpanelbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jpanel", JPanel.class);
    }     
    
    public Jcmjpanelbuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
