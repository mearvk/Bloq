/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.codemodel;

import apml.modeling.Apmlmodelfile;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

/**
 *
 * @author oem
 */
public class Apmltaghandlerparameter
{
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
