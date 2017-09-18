package apml.ui.compilers.java.builders;

import com.sun.codemodel.JCodeModel;

import javax.swing.*;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Jcmframebuilder extends Jcmabstractbuilder
{
    protected final Integer hash = 0x00888FE8;

    public Jcmframebuilder(File file)
    {
        super(file, "//frame", JFrame.class);

        this.apml = file;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }    
    
    @Override
    public ArrayList<JCodeModel> build()
    {
        return super.build();
    }
}
