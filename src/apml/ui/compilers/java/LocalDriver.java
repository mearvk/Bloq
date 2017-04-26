/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java;

/**
 *
 * @author Max Rupplin
 */
public class LocalDriver
{
    public static void main(String...args)
    {
        Uicompiler compiler;
                
        compiler = new Uicompiler();
        
        compiler.dohandleinputfiles(compiler.inputmanager);
        
        compiler.dohandleoutputfiles(compiler.outputmanager);        
    }    
}
