/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java.builders;

import com.sun.codemodel.JCodeModel;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author oem
 */
public class Jcmjfilechooserbuilder extends Jcmabstractbuilder
{
    protected final Integer hash = 0x888fe8;
    
    public Class _class = Frame.class;
    
    public static void main(String...args)
    {
        //new Jcmframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jframe", JFrame.class);
    }    
    
    public Jcmjfilechooserbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);        
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }    
    
    @Override
    public ArrayList<JCodeModel> build()
    {
        return super.build();        
    }    
}
