/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java.builders;

import com.sun.codemodel.JCodeModel;

import javax.swing.*;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author oem
 */
public class Jcmjfilechooserbuilder extends Jcmabstractbuilder
{
    protected final Integer hash = 0x888fe8;

    public Jcmjfilechooserbuilder(File apml)
    {
        super(apml, "//jfilechooser", JFileChooser.class);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }    
    
    @Override
    public ArrayList<JCodeModel> build()
    {
        return super.build();        
    }    
}
