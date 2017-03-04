package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JProgressBar;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjprogressbarbuilder extends Jcmabstractbuilder
{     
    public static void main(String...args)
    {
        new Jcmjprogressbarbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jprogressbar", JProgressBar.class);
    }     
    
    public Jcmjprogressbarbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
