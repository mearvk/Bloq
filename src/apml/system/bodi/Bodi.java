package apml.system.bodi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Bloq Object Directory Interface (BODI)
 *
 * @version 1.01
 * @author Max Rupplin
 */
public class Bodi 
{
    protected final Integer hash = 0x00888FE8;

    public static Map<String, Bodicontext> contexts = new HashMap();

    //
    public static void main(String[] args) {
        //do this sucker muthertruckr
    }

    //
    public static ArrayList<String> listcontexts(String basecontext)
    {
        Map<String, Bodicontext> bodicontexts = Bodi.contexts;
        
        ArrayList<String> matchingcontexts = new ArrayList();
        
        Integer count = 0;
        
        for(Entry<String, Bodicontext> bodicontext : bodicontexts.entrySet())
        {
            String contextname = bodicontext.getKey();
            
            if(contextname.startsWith(basecontext))
            {
                count = count + 1;
                
                matchingcontexts.add(count+". "+contextname);
            }
        }
        
        matchingcontexts.add("");
        
        return matchingcontexts;
    }

    //
    public static ArrayList<String> listpersistentcontexts(String basecontext) {
        return null;
    }

    //
    private static Map<String, Bodicontext> getcontexts(String context)
    {
        return Bodi.contexts;
    }

    //
    private static Map<String, Bodicontext> getpersistentcontexts(String context) {
        return null;
    }

    //
    public synchronized static void setcontext(String context)
    {                   
        if(contexts.get(context)==null) Bodi.contexts.put(context, new Bodicontext(context));
    }

    //
    public synchronized static void setpersistentcontext(String context) {
        return;
    }

    //
    public synchronized static Boolean removecontext(String context) throws Exception
    {
        if(contexts.get(context)==null) return true;
        
        return Bodi.contexts.remove(context)!=null;
    }

    //
    public synchronized static Boolean removepersistentcontext(String context) throws Exception {
        return false;
    }

    //
    public synchronized static Bodicontext context(String context)
	{
		if (hascontextat(context))
		{
            return Bodi.contexts.get(context);
		}
		else
		{
			Bodi.setcontext(context);

			return Bodi.contexts.get(context);
		}
	}

    //
    public synchronized static Bodicontext persistentcontext(String context) {
        return null;
    }

    //
    public synchronized static Boolean hascontextat(String context) //new at a^t test me
    {
        return contexts.get(context) != null;
    }

    //
    public synchronized static Boolean haspersistentcontextat(String context) //new at a^t test me
    {
        return false;
    }
}

