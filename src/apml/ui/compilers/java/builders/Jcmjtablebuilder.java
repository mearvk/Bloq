/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java.builders;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author oem
 */
public class Jcmjtablebuilder extends Jcmabstractbuilder
{
    public static void main(String...args)
    {
        //new Jcmjtablebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtable", JTable.class);
    }     
    
    public Jcmjtablebuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }      
}
