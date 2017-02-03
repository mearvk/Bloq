package apml.compilers.codemodel;

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
 * @since 02.02.2017
 * @version 1.0
 */
public class Jcmcompiler extends Object
{
    protected final Integer hash = 0x888fe8;
    
    public ArrayList<Apmlmodelfile> apmlmodelfiles_apml;    
    public ArrayList<Apmlmodelfile> apmlmodelfiles_definitions;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_dynamiclisteners;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_listeners;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_subscribers;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_systems;    
    
    public ArrayList<JCodeModel> jcmmodelfiles_apml;
    public ArrayList<JCodeModel> jcmmodelfiles_definitions;
    public ArrayList<JCodeModel> jcmmodelfiles_dynamiclisteners;
    public ArrayList<JCodeModel> jcmmodelfiles_listeners;
    public ArrayList<JCodeModel> jcmmodelfiles_subscribers;
    public ArrayList<JCodeModel> jcmmodelfiles_systems;    
    
    protected File manifestfile;
    protected File manifestdir;    
    protected File outputdir;   
    protected File apmlxmlfile;
    
    protected static final String APMLIN = "/home/oem/Desktop/apml/apml/echoserver.xml";
    protected static final String SOURCEOUTDIR = "/home/oem/Desktop/apml/output";
    protected static final String MANIFESTDIR = "/home/oem/Desktop/apml/output/manifest";
    protected static final String MANIFESTFILE = "/home/oem/Desktop/apml/output/manifest/manifest.txt";
    
    public static void main(String...args) 
    {                          
        try
        {           
            Jcmcompiler compiler = new Jcmcompiler();            
            
            //
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//apml");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//dynamiclistener");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//listener");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//system");                        
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//subscriber");
            
                   
            //
            compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_apml, "//apml");
            compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_dynamiclisteners, "//dynamiclistener");            
            compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_listeners, "//listener");
            compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_subscribers, "//subscriber");
            compiler.getsourcecodemodelfiles(compiler.apmlmodelfiles_systems, "//system");
            

            //
            compiler.writejcmtodisk(compiler.jcmmodelfiles_apml);
            compiler.writejcmtodisk(compiler.jcmmodelfiles_systems);                    
            compiler.writejcmtodisk(compiler.jcmmodelfiles_listeners);                    
            compiler.writejcmtodisk(compiler.jcmmodelfiles_subscribers);                    
            compiler.writejcmtodisk(compiler.jcmmodelfiles_dynamiclisteners);
            
            //
            System.gc();
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }    
    
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
    
    //@gitmecall("_-jgp://dosystemcall('gitme>1.2') ");
    public ArrayList<Apmlmodelfile> getapmlmodelfiles(File apmlxmlfile, String apmltag)
    {        
        Apmlmodelpopulator apmlmodelpopulator = new Apmlmodelpopulator();
        ArrayList<Apmlmodelfile> apmlmodels_genericfiles = null;
        
        try
        {
            switch(apmltag)
            {                    
                case "//apml":
                    this.apmlmodelfiles_apml = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break; 
                    
                case "//definitions":
                    this.apmlmodelfiles_definitions = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break; 
                    
                case "//dynamiclistener":
                    this.apmlmodelfiles_dynamiclisteners = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;    
                    
                case "//listener":
                    this.apmlmodelfiles_listeners = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;   
                    
                case "//subscriber":
                    this.apmlmodelfiles_subscribers = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
                    
                case "//system":
                    this.apmlmodelfiles_systems = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
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
                case "//apml": 
                    this.jcmmodelfiles_apml = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//definitions": 
                    this.jcmmodelfiles_definitions = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//dynamiclistener":
                    this.jcmmodelfiles_dynamiclisteners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//listener":
                    this.jcmmodelfiles_listeners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;   
                    
                case "//subscriber":
                    this.jcmmodelfiles_subscribers = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//system":
                    this.jcmmodelfiles_systems = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
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
            this.writejcmtodisk(this.jcmmodelfiles_apml);
            this.writejcmtodisk(this.jcmmodelfiles_definitions);
            this.writejcmtodisk(this.jcmmodelfiles_dynamiclisteners);            
            this.writejcmtodisk(this.jcmmodelfiles_listeners);
            this.writejcmtodisk(this.jcmmodelfiles_subscribers);
            this.writejcmtodisk(this.jcmmodelfiles_systems);
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
