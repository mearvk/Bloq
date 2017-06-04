package apml.system.bodi;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import java.util.Map.Entry;

/**
 * Bloq Object Directory Interface (BODI)
 * 
 * @see http://github.com/mearvk/Bloq/wiki
 * @version 1.00
 * @author Max Rupplin
 */
public class Bodi 
{
    protected final Integer hash = 0x00888FE8;
           
    public static Map<String, Bodicontext> contexts = new HashMap();    
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {        
        String context = "//system/tests/bodi/mainmethodtest";
        
        Object one = new Object(); 
        
        Object two = new Object();
        
        Object three = new Object();
        
        Bodi.setcontext(context);
        
        try
        {
            Bodi.context(context).put("one", one);
            
            Bodi.context(context).put(one.hashCode(), one);
            
            Bodi.context(context).put(one, one);
            
            //
            
            Bodi.context(context).put("two", two);            
            
            Bodi.context(context).put(two.hashCode(), two);
            
            Bodi.context(context).put(two, two);
            
            //
            
            Bodi.context(context).put("three", three);
            
            Bodi.context(context).put(three.hashCode(), three);
            
            Bodi.context(context).put(three, three);                        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Object one1 = Bodi.context(context).pull(one.hashCode());
        Object one2 = Bodi.context(context).pull(one);
        Object one3 = Bodi.context(context).pull("one");
        
        Object two1 = Bodi.context(context).pull(two.hashCode());
        Object two2 = Bodi.context(context).pull(two);
        Object two3 = Bodi.context(context).pull("two");

        Object three1 = Bodi.context(context).pull(three.hashCode());
        Object three2 = Bodi.context(context).pull(three);
        Object three3 = Bodi.context(context).pull("three");     

        if(one==one1) System.err.println("Test complete: one==one1 evaluates to true");
        if(one==one2) System.err.println("Test complete: one==one2 evaluates to true");
        if(one==one3) System.err.println("Test complete: one==one3 evaluates to true");
        
        if(two==two1) System.err.println("Test complete: two==two1 evaluates to true");
        if(two==two2) System.err.println("Test complete: two==two2 evaluates to true");
        if(two==two3) System.err.println("Test complete: two==two3 evaluates to true");   
        
        if(three==three1) System.err.println("Test complete: three==three1 evaluates to true");
        if(three==three2) System.err.println("Test complete: three==three2 evaluates to true");
        if(three==three3) System.err.println("Test complete: three==three3 evaluates to true");           
    }
    
    /**
     * 
     * @param basecontext
     * @return 
     */
    public static ArrayList<String> listcontexts(String basecontext)
    {
        Map<String, Bodicontext> bodicontexts = Bodi.contexts;
        
        ArrayList<String> matchingcontexts = new ArrayList();
        
        Integer count = 0;
        
        for(Entry<String, Bodicontext> bodicontext : bodicontexts.entrySet())
        {
            String contextname = bodicontext.getKey();
            
            Bodicontext contextproper = bodicontext.getValue();                                                
            
            if(contextname.startsWith(basecontext))
            {
                count = count + 1;
                
                matchingcontexts.add(count+". "+contextname);
            }
        }
        
        matchingcontexts.add("");
        
        return matchingcontexts;
    }
    
    /**
     * 
     * @param context
     * @return 
     */
    private static Map<String, Bodicontext> getcontexts(String context)
    {
        return Bodi.contexts;
    }       
   
    /**
     * 
     * @param context
     */
    public synchronized static void setcontext(String context)
    {                   
        if(contexts.get(context)==null) Bodi.contexts.put(context, new Bodicontext(context));
    }          
    
    /**
     * 
     * @param context
     * @return
     * @throws Exception 
     */
    public synchronized static Boolean removecontext(String context) throws Exception
    {
        if(contexts.get(context)==null) return true;
        
        return Bodi.contexts.remove(context)!=null;
    }
       
    /**
     * 
     * @param context
     * @return 
     */
    public synchronized static Bodicontext context(String context)
    {     
        return contexts.get(context);
    }  
    
    /**
     * 
     * @param context
     * @return 
     */
    public synchronized static Boolean hascontextat(String context) //new at a^t test me
    {
        return contexts.get(context) == null ? false : true;
    }
}

