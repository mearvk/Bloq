/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.java.codemodel;

//import apml.compilers.Bloqabstractoutputmanager;

import apml.modeling.Apmlmodelfile;
import apml.system.bodi.Bodi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.io.File;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author max rupplin
 */
public class Bloqjcodemodelmanager
{
    private final Integer hash = 0x00888fe8;  
    
    public ArrayList<JCodeModel> apmlmodels;  
    
    public ArrayList<JCodeModel> definitionmodels;
    
    public ArrayList<JCodeModel> dynamiclistenermodels;
    
    public ArrayList<JCodeModel> listenermodels;
    
    public ArrayList<JCodeModel> objectmodels;
    
    public ArrayList<JCodeModel> subscribermodels;
    
    public ArrayList<JCodeModel> systemmodels;
    
    
    public Bloqjcodemodelmanager()
    {
        Bodi.setcontext("system");
        
        Bodi.context("system").put("bloqjcodemodelmanager", this);
    }
    
    public ArrayList<JCodeModel> dosetoutputfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Bloqpopulatorimpl jcmmodelpopulator = new Bloqpopulatorimpl();
        
        ArrayList<JCodeModel> jcmmodels_genericfiles = null;
                
        Bloqjcodemodelmanager jcmmanager = (Bloqjcodemodelmanager)Bodi.context("system").pull("bloqjcodemodelmanager");
        
        try
        {                        
            switch(apmltag)
            {
                case "//apml": jcmmanager.apmlmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//definitions": jcmmanager.definitionmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//dynamiclistener": jcmmanager.dynamiclistenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//listener": jcmmanager.listenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;   
                    
                case "//object": jcmmanager.objectmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                     
                    
                case "//subscriber": jcmmanager.subscribermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//system": jcmmanager.systemmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
            }                     
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return jcmmodels_genericfiles;
    }    
    
    public void dosetsourcefiles(ArrayList<JCodeModel> jcmmodels)
    {        
        Bloqfileguardian fileguardian = (Bloqfileguardian)Bodi.context("system").pull("bloqfileguardian");
        
        for(int i=0; i<jcmmodels.size(); i++)
        {
            try
            {                                
                Iterator<JPackage> packages = jcmmodels.get(i).packages();
                                
                while(packages.hasNext())
                {                    
                    JPackage jpackage = packages.next();                            
                                    
                    Iterator<JDefinedClass> classes = jpackage.classes();
                    
                    String pname = jpackage.name();
                    
                    while(classes.hasNext())
                    {                                              
                        String cname = classes.next().name();
                        
                        jcmmodels.get(i).build(new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.srcextensionurl), System.err);           
                    }
                }
            }
            catch(Exception e)
            {
                /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
            }                       
        }                
    }    
}