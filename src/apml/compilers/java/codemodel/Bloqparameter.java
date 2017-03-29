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
public class Bloqparameter
{
    private final Integer hash = 0x00888fe8;  
    
    public JCodeModel jcodemodel;
    
    public JDefinedClass classref;
    
    public JPackage jpackage; 
    
    public Apmlmodelfile apmlmodelfile;
            
    public Bloqparameter(JCodeModel jcodemodel, JPackage jpackage, JDefinedClass classref, Apmlmodelfile apmlmodelfile)
    {
        this.jcodemodel = jcodemodel;
        
        this.classref = classref;
        
        this.jpackage = jpackage;
        
        this.apmlmodelfile = apmlmodelfile;
    }            
}
