package apml.system.bndi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Max Rupplin
 */
public class Bndi 
{
    public static Map<String, Bndicontext> contexts = new HashMap();
    //public static Set<String> contexts = new HashSet();
    
    public static void main(String[] args)
    {        
        Object one = new Object(); 
        Object two = new Object();
        Object three = new Object();
        
        Bndi.setcontext("//apml/tests/");
        
        Bndi.context("//apml/tests/").put("one",one);
        Bndi.context("//apml/tests/").put("two",two);
        Bndi.context("//apml/tests/").put("three",three);
        
        Object one1 = Bndi.context("//apml/tests/").pull(one.hashCode());
        Object one2 = Bndi.context("//apml/tests/").pull(one);
        Object one3 = Bndi.context("//apml/tests/").pull("one");
        
        Object two1 = Bndi.context("//apml/tests/").pull(two.hashCode());
        Object two2 = Bndi.context("//apml/tests/").pull(two);
        Object two3 = Bndi.context("//apml/tests/").pull("two");

        Object three1 = Bndi.context("//apml/tests/").pull(three.hashCode());
        Object three2 = Bndi.context("//apml/tests/").pull(three);
        Object three3 = Bndi.context("//apml/tests/").pull("three");             
    }
    
    public static Map<String, Bndicontext> getcontexts(String context)
    {
        return Bndi.contexts;
    }       
    
    public static void setcontext(String context)
    {
        Bndi.contexts.put(context, new Bndicontext(context));
    }          
    
    public static Bndicontext context(String context)
    {       
        return contexts.get(context);
    } 
}

