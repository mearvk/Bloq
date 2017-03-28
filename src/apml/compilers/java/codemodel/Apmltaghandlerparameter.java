package apml.compilers.java.codemodel;

import apml.modeling.Apmlmodelfile;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JDefinedClass;

import com.sun.codemodel.JPackage;

/**
 *
 * @author Max Rupplin
 * @since 03.28.2017
 */
public class Apmltaghandlerparameter
{
    private final Integer hash = 0x00888fe8;  
    
    JCodeModel jcodemodel;
    
    JDefinedClass classfile;
    
    JPackage jpackage; 
    
    Apmlmodelfile apmlmodelfile;
            
    public Apmltaghandlerparameter(JCodeModel jcodemodel, JPackage jpackage, JDefinedClass classfile, Apmlmodelfile apmlmodelfile)
    {
        this.jcodemodel = jcodemodel;
        
        this.classfile = classfile;
        
        this.jpackage = jpackage;
        
        this.apmlmodelfile = apmlmodelfile;
    }            
}
