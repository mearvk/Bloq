package apml.ui.compilers.java.builders;

import apml.ui.compilers.java.Uiparameter;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JMethod;

import java.io.File;

import java.util.ArrayList;

import java.util.Iterator;

import javax.swing.JComboBox;

import javax.xml.xpath.XPathConstants;

import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjcomboboxbuilder extends Jcmabstractbuilder
{       
    public Class _class = JComboBox.class;
    
    public static void main(String...args)
    {
        //new Jcmjcomboboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcombobox", JComboBox.class);
    }     
    
    public Jcmjcomboboxbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
    
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
    
    @Override
    public ArrayList<JCodeModel> build()
    {
        this.basebuild();                
        
        if(this.storage.size()==0) return null;
        
        try
        {         
            for(int index=0; index<this.nodes.getLength(); index++)
            {                                
                Uiparameter uip = this.storage.get(index);                                                                                       
                
                this.additems(uip);
            }
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }        
        
        return this.jcodemodels;
    }
    
    public void additems(Uiparameter uip)
    {
        try
        {
            uip.constructor1.body().directStatement("\n");
            uip.constructor2.body().directStatement("\n");
            
            uip.constructor1.body().directStatement("/*---------------------- combobox items ---------------------*/\n");
            uip.constructor2.body().directStatement("/*---------------------- combobox items ---------------------*/\n");
            
            NodeList nodes =  (NodeList)xpath.evaluate("./item", this.nodes.item(uip.index), XPathConstants.NODESET); 
            
            for(int i=0; i<nodes.getLength(); i++)
            {
                Element element = (Element)nodes.item(i);
                
                if(element.getAttribute("name")==null) continue;                
                
                Iterator iterator = uip.jdc.constructors();
                
                while(iterator.hasNext())
                {
                    JMethod constructor;
                    
                    constructor = (JMethod)iterator.next();                    
                    
                    constructor.body().directStatement("this.addItem(\""+element.getAttribute("name")+"\");\n");
                }                
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
}
