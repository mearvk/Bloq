package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;

import apml.compilers.Stdcompiler;
import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

/**
 * 
 * @author Max Rupplin
 * @since 02.02.2017
 * @version 1.00
 * @serial 0x888fe8 /mr /ss /ok
 */
public class Jcmcompiler extends Stdcompiler
{        
    protected final Integer hash = 0x888fe8;
       
    public Apmlmodelfiles apmlmodels;        
    public Jcmmodelfiles jcmmodels;   
    public Jcmfilehelper files;
    
    public static void main(String...args) 
    {                          
        try
        {            
            // [0] Please a New Instance
            Jcmcompiler compiler = new Jcmcompiler();
            
            //compiler.generateapmlmodelfiles();
            //compiler.quicktobytecode();
            //compiler.generatejavamodelfiles();
            //compiler.compiletosource();
                    
            // [1] Please kindly do Create apml model files
            compiler.generateapmlmodelfiles(compiler.files.apmlxmlinputfile, "//apml");
            compiler.generateapmlmodelfiles(compiler.files.apmlxmlinputfile, "//dynamiclistener");
            compiler.generateapmlmodelfiles(compiler.files.apmlxmlinputfile, "//listener");    
            compiler.generateapmlmodelfiles(compiler.files.apmlxmlinputfile, "//object");   
            compiler.generateapmlmodelfiles(compiler.files.apmlxmlinputfile, "//subscriber");
            compiler.generateapmlmodelfiles(compiler.files.apmlxmlinputfile, "//system");
              
            // [2] Please kindly do Create temp. byte code for JCM finalization
            compiler.quicktobytecode(compiler.apmlmodels.apml);
            compiler.quicktobytecode(compiler.apmlmodels.dynamiclisteners);
            compiler.quicktobytecode(compiler.apmlmodels.listeners);
            compiler.quicktobytecode(compiler.apmlmodels.objects);
            compiler.quicktobytecode(compiler.apmlmodels.subscribers);
            compiler.quicktobytecode(compiler.apmlmodels.systems);
            
            // [3] Please kindly do Create Java CodeModel files for output processing
            compiler.generatejavamodelfiles(compiler.apmlmodels.apml, "//apml");
            compiler.generatejavamodelfiles(compiler.apmlmodels.dynamiclisteners, "//dynamiclistener");
            compiler.generatejavamodelfiles(compiler.apmlmodels.listeners, "//listener");
            compiler.generatejavamodelfiles(compiler.apmlmodels.objects, "//object");
            compiler.generatejavamodelfiles(compiler.apmlmodels.subscribers, "//subscriber");
            compiler.generatejavamodelfiles(compiler.apmlmodels.systems, "//system");    
            
            // [4] Please kindly do Output Java files to output folder
            compiler.compiletosource(compiler.jcmmodels.apml);
            compiler.compiletosource(compiler.jcmmodels.dynamiclisteners);                             
            compiler.compiletosource(compiler.jcmmodels.listeners);                    
            compiler.compiletosource(compiler.jcmmodels.objects);
            compiler.compiletosource(compiler.jcmmodels.subscribers);                    
            compiler.compiletosource(compiler.jcmmodels.systems);   
                        
            // [5] Please kindly do move Jar file to output folder
            compiler.writeapmlbackingjartodisk();
            
            // [6] Please kindly call JVM garbage collector [thanx[']]
            System.gc();
        }
        catch(Exception e)
        {
            
        }
    }    
    
    //
    public Jcmcompiler()
    {
        this.files = new Jcmfilehelper();
        this.apmlmodels = new Apmlmodelfiles();
        this.jcmmodels = new Jcmmodelfiles();
        
        try
        {
            this.files.sourcedir = new File(files.basedirurl+files.srcdirurl);            
            if(!this.files.sourcedir.exists())
                this.files.sourcedir.mkdirs();  
            
            this.files.builddir = new File(files.basedirurl+files.builddirurl);            
            if(!this.files.builddir.exists())
                this.files.builddir.mkdirs();            
            
            this.files.manifestfiledir = new File(files.basedirurl+files.manifestdirurl);            
            if(!this.files.manifestfiledir.exists())
                this.files.manifestfiledir.mkdirs();   
            
            this.files.apmlxmlinputfile = new File(files.apmlinurl);
            if(!this.files.apmlxmlinputfile.exists())
                throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");     
        }
        catch(Exception e)
        {
            //
        }
    }          
    
    /**
     * Quickly generate bytecode [.class] files for JCM reference in compiling final Java Source files.
     * 
     * @param apmlmodelfiles Ready, whole APML files for reference; ok, go.
     */
    public void quicktobytecode(ArrayList<Apmlmodelfile> apmlmodelfiles) 
    {
        for(Apmlmodelfile model: apmlmodelfiles) 
        {
            try
            {   
                JCodeModel jmodel;                
                Runtime runtime;
                
                String sourcepackagedir = new Filegrepper().getpackagenameaspathname(model.packagename);
                String buildpackagedir = new Filegrepper().getpackagenameaspathname(model.packagename);
                String pathname = new Filegrepper().getpackagenameaspathname(model.packagename)+"/";
                String javac = "javac "+files.basedirurl+files.tempsrcdirurl+pathname+model.classname+".java -d "+files.basedirurl+files.builddirurl;
                
                new File(files.basedirurl+files.tempsrcdirurl).mkdirs();
                new File(files.basedirurl+files.tempsrcdirurl+sourcepackagedir).mkdirs();               
                
                new File(files.basedirurl+files.builddirurl).mkdirs();
                new File(files.basedirurl+files.builddirurl+buildpackagedir).mkdirs();
                   
                jmodel = new JCodeModel();
                jmodel._package(model.packagename)._class(model.classname);
                jmodel.build(new File(files.basedirurl+files.tempsrcdirurl));
                
                runtime = Runtime.getRuntime();                                                
                runtime.exec(javac);
            }
            catch(Exception e)
            {
                
            }
            finally
            {
                System.gc();
            }
        }
    }
    
    //
    public ArrayList<Apmlmodelfile> generateapmlmodelfiles(File apmlxmlfile, String apmltag)
    {        
        Apmlmodelpopulator apmlmodelpopulator = new Apmlmodelpopulator();
        ArrayList<Apmlmodelfile> apmlmodelfiles = null;
        
        try
        {
            switch(apmltag)
            {                    
                case "//apml":
                    this.apmlmodels.apml = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break; 
                    
                case "//definitions":
                    this.apmlmodels.definitions = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break; 
                    
                case "//dynamiclistener":
                    this.apmlmodels.dynamiclisteners = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;    
                    
                case "//listener":
                    this.apmlmodels.listeners = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;  
                    
                case "//object":
                    this.apmlmodels.objects = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                      
                    
                case "//subscriber":
                    this.apmlmodels.subscribers = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
                    
                case "//system":
                    this.apmlmodels.systems = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
            }            
        }
        catch(Exception e)
        {
            
        }
        
        return apmlmodelfiles;
    }    
    
    //
    public ArrayList<JCodeModel> generatejavamodelfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Jcmmodelpopulator jcmmodelpopulator = new Jcmmodelpopulator();
        ArrayList<JCodeModel> jcmmodels_genericfiles = null;
                
        try
        {
            switch(apmltag)
            {
                case "//apml": 
                    this.jcmmodels.apml = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//definitions": 
                    this.jcmmodels.definitions = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//dynamiclistener":
                    this.jcmmodels.dynamiclisteners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//listener":
                    this.jcmmodels.listeners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;   
                    
                case "//object":
                    this.jcmmodels.objects = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                     
                    
                case "//subscriber":
                    this.jcmmodels.subscribers = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//system":
                    this.jcmmodels.systems = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
            }                     
        }
        catch(Exception e)
        {
            
        }
        
        return jcmmodels_genericfiles;
    }    
    
    //
    public void writeallsourcetodisk()
    {
        try
        {
            this.compiletosource(this.jcmmodels.apml);
            this.compiletosource(this.jcmmodels.definitions);
            this.compiletosource(this.jcmmodels.dynamiclisteners);            
            this.compiletosource(this.jcmmodels.listeners);
            this.compiletosource(this.jcmmodels.objects);
            this.compiletosource(this.jcmmodels.subscribers);
            this.compiletosource(this.jcmmodels.systems);
            
            try
            {
                if(new File(files.apmloutjarurl).exists())
                {
                    Files.copy( new File(files.apmlinjarurl).toPath(), new File(files.apmloutjarurl).toPath(), REPLACE_EXISTING );
                }
                else new File(files.apmloutjarurl).mkdirs();
                {
                    Files.copy( new File(files.apmlinjarurl).toPath(), new File(files.apmloutjarurl).toPath(), REPLACE_EXISTING );                
                }
            }
            catch(Exception e){e.printStackTrace(System.err);}            
        }
        catch(Exception e)
        {
            
        }
    }   
        
    //
    public void compiletosource(ArrayList<JCodeModel> jcmmodels)
    {        
        for(int i=0; i<jcmmodels.size(); i++)
        {
            try
            {                                
                Iterator<JPackage> packages = jcmmodels.get(i).packages();
                                
                while(packages.hasNext()) //do every package
                {                    
                    JPackage jpackage = packages.next();                            
                                    
                    Iterator<JDefinedClass> classes = jpackage.classes();
                    
                    String pname = jpackage.name();
                    
                    while(classes.hasNext()) //do every class
                    {                                              
                        String cname = classes.next().name();
                        
                        jcmmodels.get(i).build(new File(files.basedirurl+files.srcdirurl), System.err);           
                    }
                }
            }
            catch(Exception e)
            {
                
            }                       
        }                
    }  

    //    
    public void writeapmlbackingjartodisk() throws Exception
    {
        if(new File(files.apmloutjarurl).exists())
        {
            Files.copy(new File(files.apmlinjarurl).toPath(),new File(files.apmloutjarurl).toPath(),REPLACE_EXISTING);
        }
        else new File(files.apmloutjarurl).mkdirs();
        {
            Files.copy(new File(files.apmlinjarurl).toPath(),new File(files.apmloutjarurl).toPath(),REPLACE_EXISTING);                
        }        
    }

    //
    @Override
    public void compiletosource() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
