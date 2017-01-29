package apml.compilers.codemodel;

//import apml.xml.handlers.StdXmlHandler;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.io.Serializable;

public class codemodelcompiler 
{       
    //StdXmlHandler handler;
   
    
    public static void main(String[] args) throws Exception
    {   
        File input_file = new File("/home/oem/Desktop/echoserver.xml");
        File output_file = new File("/home/oem/Desktop/echoserver.java");
        
        //codemodelcompiler test_compiler = new codemodelcompiler(input_file);        
        
        String java_version = "n/a";
        String apml_version = "n/a"; 
        
        try
        {                                                
            //test_compiler = null;
            //compile_source(input_file, output_file);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    public codemodelcompiler(File file)
    {
        //this.handler = new StdXmlHandler(file);
    }   

    public codemodelcompiler(Object handler)
    {
        //this.handler = handler;                
    }
    
    public void compile_source(File apml_input_file, File java_output_file) throws Exception
    {
        /*
        this.handler.parse(apml_input_file); //move XML to Java DOM representation   
        
        this.handler.check(apml_input_file); //check APML is well formed                    
        
        this.handler.compile(this.handler); //compile XML to Java source code
        
        this.handler.write(this.handler, java_output_file); //write out Java source code
        */
    }    
    
    public void compile_source(File apml_input_file, File java_output_file, String apmlVersion, String javaVersion) throws Exception
    {
        /*
        this.handler.parse(apml_input_file); //move XML to Java DOM representation   
        
        this.handler.check(apml_input_file); //check APML is well formed                    
        
        this.handler.compile(this.handler); //compile XML to Java source code
        
        this.handler.write(this.handler, java_output_file); //write out Java source code
        */
    }
    
    public void compile(Object xml_handler) throws Exception //
    {
        /*
        String _package = "apml.out.src";
        
        JCodeModel code_model = new JCodeModel();

        JPackage jp = code_model._package(_package);

        //
        for(String element: xml_handler.getList())
        {
            JDefinedClass jc; 

            jc = jp._class("GeneratedClass");
            jc._implements(Serializable.class);
            jc.javadoc().add("A JCodeModel example.");
            jc.constructor(JMod.PUBLIC).javadoc().add("Creates a new " + jc.name() + ".");
            jc.field(JMod.STATIC | JMod.FINAL, Long.class, "serialVersionUID", JExpr.lit(1L)); 

            JFieldVar quantity = jc.field(JMod.PRIVATE, Integer.class, "quantity");

            JMethod getter = jc.method(JMod.PUBLIC, quantity.type(), "getQuantity");
            getter.body()._return(quantity);
            getter.javadoc().add("Returns the quantity.");
            getter.javadoc().addReturn().add(quantity.name());

            // Add set method
            JMethod setter = jc.method(JMod.PUBLIC, code_model.VOID, "setQuantity");
            setter.param(quantity.type(), quantity.name());
            setter.body().assign(JExpr._this().ref(quantity.name()), JExpr.ref(quantity.name()));
            setter.javadoc().add("Set the quantity.");
            setter.javadoc().addParam(quantity.name()).add("the new quantity"); 

            //codeModel.build(new File("/home/oem/Desktop"))
        }         

        // Generate the code
        code_model.build(new File("/home/oem/Desktop"));    
        */
    }
}



class SystemHandler
{
    public void doIt()
    {
        
    }    
}

class ObjectHandler
{
    public void doIt()
    {
        
    }    
}

class ListenerHandler
{
    public void doIt()
    {
        
    }    
}

class SubscriberHandler
{
    public void doIt()
    {
        
    }
}
