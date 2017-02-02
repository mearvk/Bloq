package apml.compilers.codemodel;

import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Max Rupplin
 */
public class Jcmcompiler extends Object
{
    protected final Integer hash = 0x888fe8;
    
    public ArrayList<Apmlmodelfile> apmlmodelfiles_compiler;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_definitions;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_driver;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_system;
    
    public ArrayList<JCodeModel> jcmmodelfiles_compiler;
    public ArrayList<JCodeModel> jcmmodelfiles_definitions;
    public ArrayList<JCodeModel> jcmmodelfiles_driver;
    public ArrayList<JCodeModel> jcmmodelfiles_system;    
    
    protected File manifestfile;
    protected File manifestdir;    
    protected File outputdir;   
    protected File apmlxmlfile;
    
    protected static final String APMLIN = "/home/oem/Desktop/apml/apml/echoserver.xml";
    protected static final String SOURCEOUTDIR = "/home/oem/Desktop/apml/output";
    protected static final String MANIFESTDIR = "/home/oem/Desktop/apml/output/manifest";
    protected static final String MANIFESTFILE = "/home/oem/Desktop/apml/output/manifest/manifest.txt";
    
    public Jcmcompiler()
    {
        try
        {
            this.outputdir = new File(Jcmcompiler.SOURCEOUTDIR);            
            if(!this.outputdir.exists())
                this.outputdir.mkdirs();   
            
            this.manifestdir = new File(Jcmcompiler.MANIFESTDIR);            
            if(!this.manifestdir.exists())
                this.manifestdir.mkdirs();   
            
            this.apmlxmlfile = new File(Jcmcompiler.APMLIN);
            if(!this.apmlxmlfile.exists())
                throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");     
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }    

    public static void main(String...args) 
    {                          
        try
        {           
            Jcmcompiler compiler = new Jcmcompiler();            
            
            //compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//compiler");
            //compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//definitions");
            //compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//driver");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//system");
            
            //compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_compiler, "//compiler");
            //compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_definitions, "//definitions");
            //compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_driver, "//driver");           
            compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_system, "//system");            
            
            //compiler.writejcmtodisk(compiler.jcmmodelfiles_compiler);
            //compiler.writejcmtodisk(compiler.jcmmodelfiles_definitions);
            //compiler.writejcmtodisk(compiler.jcmmodelfiles_driver);
            compiler.writejcmtodisk(compiler.jcmmodelfiles_system);                                         
                    
            //compiler.writeallsourcetodisk();
            
            //
            System.gc();
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }  
    
    //@gitmecall("_-jgp://dosystemcall('gitme>1.2') ");
    public ArrayList<Apmlmodelfile> getapmlmodelfiles(File apmlxmlfile, String apmltag)
    {        
        Apmlmodelpopulator apmlmodelpopulator = new Apmlmodelpopulator();
        ArrayList<Apmlmodelfile> apmlmodels_genericfiles = null;
        
        try
        {
            switch(apmltag)
            {
                case "//compiler": 
                    this.apmlmodelfiles_compiler = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;
                    
                case "//definitions":
                    this.apmlmodelfiles_definitions = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
                    
                case "//driver":
                    this.apmlmodelfiles_driver = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
                    
                case "//system":
                    this.apmlmodelfiles_system = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        return apmlmodels_genericfiles;
    }    
    
    public ArrayList<JCodeModel> getsourcecodemodelfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Jcmmodelpopulator jcmmodelpopulator = new Jcmmodelpopulator();
        ArrayList<JCodeModel> jcmmodels_genericfiles = null;
                
        try
        {
            switch(apmltag)
            {
                case "//compiler": 
                    this.jcmmodelfiles_compiler = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//definitions":
                    this.jcmmodelfiles_definitions = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//driver":
                    this.jcmmodelfiles_driver = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//system":
                    this.jcmmodelfiles_system = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
            }                     
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        return jcmmodels_genericfiles;
    }    
    
    public void writeallsourcetodisk()
    {
        try
        {
            this.writejcmtodisk(this.jcmmodelfiles_compiler);
            this.writejcmtodisk(this.jcmmodelfiles_definitions);
            this.writejcmtodisk(this.jcmmodelfiles_driver);
            this.writejcmtodisk(this.jcmmodelfiles_system);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    public void writejcmtodisk(ArrayList<JCodeModel> jcmmodels)
    {        
        for(int i=0; i<jcmmodels.size(); i++)
        {
            try
            {
                Iterator<JPackage> itr_pkg = jcmmodels.get(i).packages();
                                
                for(;itr_pkg.hasNext();) //do every package
                {
                    JPackage jpackage = itr_pkg.next();
                                    
                    Iterator<JDefinedClass> itr_cls = jpackage.classes();
                    
                    for(;itr_cls.hasNext();) //do every class
                    {                    
                        JDefinedClass cls = itr_cls.next();
                                             
                        String fullname = cls.fullName();                                                
                        String packagename = cls.getPackage().name();
                        //String filename = new Filegrepper().getpackagenameaspathname(fullname);
                        
                        
                        jcmmodels.get(i).build(new File(SOURCEOUTDIR));
                    }
                }
            }
            catch(Exception e){e.printStackTrace(System.err);}                       
        }
    }
}
