package apml.ui.compilers.java.builders;

import com.sun.codemodel.JCodeModel;

import javax.swing.*;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author max rupplin
 */
public class Jcmjappletbuilder extends Jcmabstractbuilder {
    protected final Integer hash = 0x00888FE8;

    public Jcmjappletbuilder(File apml)
    {
        super(apml, "//japplet", JApplet.class);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }

    @Override
    public ArrayList<JCodeModel> build() {
        return super.build();
    }
}
