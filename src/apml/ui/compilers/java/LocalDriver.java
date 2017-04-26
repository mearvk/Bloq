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
