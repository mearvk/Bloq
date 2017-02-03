package apml.helpers;

import java.io.File;

/**
 *
 * @author oem
 */
public class Filegrepper 
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
        fullURL = fullURL.replace(".", "/");
        
        if(fullURL.replace(".java","").contains(".")) //e.g. abc.def.ghi.Classname.java returns Classname
        {            
            String stmp = "";
            
            stmp = fullURL.substring(fullURL.lastIndexOf(".")+1,fullURL.length());
            
            return stmp;
        }
        
        if(fullURL.replace(".java","").contains("/")) //e.g. abc/def/ghi/Classname.java returns Classname
        {
            String stmp = "";
            
            stmp = fullURL.substring(fullURL.lastIndexOf("/")+1,fullURL.length());
            
            return stmp;
        }  
        
        throw new Exception("Could not parse for class name.");
    }            
}
