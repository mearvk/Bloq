package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmframebuilder extends Jcmabstractbuilder
{                            
    public static void main(String...args)
    {
        new Jcmframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jframe", JFrame.class);
    }    
    
    public Jcmframebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }    
    
    @Override
    public ArrayList<JCodeModel> build(String tagname, Class classname)
    {
        return super.build(tagname, classname);        
    }
}
