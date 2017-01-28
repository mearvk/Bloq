/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.helpers;

import java.io.File;

/**
 *
 * @author oem
 */
public class filegrepper 
{
    public String getpackagename(String fullURL) throws Exception
    {
        if(fullURL.replace(".java","").contains(".")) //e.g. abc.def.ghi.Classname.java returns abc.def.ghi
        {
            String stmp = "";
            
            stmp = fullURL.substring(0,fullURL.lastIndexOf(".")).replace(".", File.separator);
            stmp = stmp.substring(0, stmp.lastIndexOf("/")).replace("/", ".");
            
            return stmp;
        }
        
        if(fullURL.replace(".java","").contains("/")) //e.g. abc/def/ghi/Classname.java returns abc.def.ghi
        {
            File ftmp = new File(fullURL.replace(".", File.separator));
            String stmp = ftmp.getPath().toString().replace(File.separator, ".");
            
            return stmp;
        }  
        
        throw new Exception("Could not parse for package name.");
    }
    
    public String getpackagenameaspathname(String fullURL) throws Exception
    {
        if(fullURL.replace(".java","").contains(".")) //e.g. abc.def.ghi.Classname.java returns abc.def.ghi
        {
            String stmp = "";
            
            stmp = fullURL.substring(0,fullURL.lastIndexOf(".")).replace(".", File.separator);
            stmp = stmp.substring(0, stmp.lastIndexOf("/")).replace("/", ".");
            
            return stmp.replace(".",File.separator);
        }
        
        if(fullURL.replace(".java","").contains("/")) //e.g. abc/def/ghi/Classname.java returns abc.def.ghi
        {
            File ftmp = new File(fullURL.replace(".", File.separator));
            String stmp = ftmp.getPath().toString().replace(File.separator, ".");
            
            return stmp.replace(".",File.separator);
        }  
        
        throw new Exception("Could not parse for package name.");
    }    
    
    public String getclassname(String fullURL) throws Exception
    {
        if(fullURL.replace(".java","").contains(".")) //e.g. abc.def.ghi.Classname.java returns Classname
        {            
            String stmp = "";
            
            stmp = fullURL.substring(0,fullURL.lastIndexOf(".")).replace(".", File.separator);
            stmp = stmp.substring(stmp.lastIndexOf("/")+1, stmp.length());
            
            return stmp;
        }
        
        if(fullURL.replace(".java","").contains("/")) //e.g. abc/def/ghi/Classname.java returns Classname
        {
            String stmp = "";
            
            stmp = fullURL.substring(0,fullURL.lastIndexOf(".")).replace(".", File.separator);
            stmp = stmp.substring(stmp.lastIndexOf("/")+1, stmp.length());
            
            return stmp;
        }  
        
        throw new Exception("Could not parse for class name.");
    }            
}
