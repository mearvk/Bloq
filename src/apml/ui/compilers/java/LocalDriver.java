package apml.ui.compilers.java;

/**
 * Simple driver class for Uicompiler instantiation and running; the primary starting point for running Bloq UI system
 * 
 * @author Max Rupplin
 * @since  04.30.2017
 * @version Bloq 1.0
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
