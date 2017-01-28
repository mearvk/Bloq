/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.roaster;

import apml.annotations.ApmlListener;
import java.io.File;
import java.io.FileNotFoundException;
//import org.jboss.forge.roaster.Roaster;
//import org.jboss.forge.roaster.model.source.JavaClassSource;
//import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
//import org.jboss.forge.roaster.model.util.Assert;

/**
 *
 * @author oem
 */
public class StdRoasterCompiler 
{
    public static void main(String[] args) throws FileNotFoundException
    {
	File file = new File("/home/oem/Desktop/Output.java");
        
	///JavaInterfaceSource modifiedInterface = Roaster.parse(JavaInterfaceSource.class, file);        
    }
}
