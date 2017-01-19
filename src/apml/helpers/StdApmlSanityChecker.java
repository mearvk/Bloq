/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.helpers;

import apml.xml.handlers.StdXmlHandler;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class StdApmlSanityChecker
{
    public StdXmlHandler handler;
            
    public StdApmlSanityChecker(StdXmlHandler handler)
    {
        this.handler = handler;
    }
            
    public void check(File file) 
    {
        System.err.println("Sanity checker running...");
        
        try
        {
            //check that we have APML as root tag
            if(!handler.getRootName().equalsIgnoreCase("APML"))
                throw new Exception("Root node not normal at this hour.");

            //check that we have at least one system tag within APML root
            //if(!handler.getRoot().getChildNodes().item(0).toString().equalsIgnoreCase("system"))
            //    throw new Exception("Root does not equal containment at this time.");

            //check that we have zero or more definition tags with APML root    
            //if(!handler.getRoot().getChildNodes().item(0).toString().equalsIgnoreCase("definitions"))
            //    throw new Exception("Root does not equal containment at this time.");            

            //check that we have exactly one APML to source code compiler
            //if(!handler.getRoot().getChildNodes().item(0).toString().equalsIgnoreCase("compiler"))
            //    throw new Exception("Root does not equal containment at this time.");             

            //check that we have exactly one APML driver   
            //if(!handler.getRoot().getChildNodes().item(0).toString().equalsIgnoreCase("driver"))
            //    throw new Exception("Root does not equal containment at this time.");                         
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        System.err.println("Sanity checker complete...");
    }    
}