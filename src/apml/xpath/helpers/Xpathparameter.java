package apml.xpath.helpers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author max rupplin
 */
public final class Xpathparameter
{   
    protected final Integer hash = 0x888fe8;
    
    public Document document;
    public File apmlfile;
    public NodeList attrs;
    public Object obj;     
    
    public apml.helpers.Parser parser;
    
    public Long n0000_count;
    public NodeList n0001_tagname;
    public NodeList n0002_autostart;
    public NodeList n0003_classname;
    public NodeList n0004_id;       
    public NodeList n0005_init;     
    public NodeList n0006_package;  
    public NodeList n0007_run;      
    public NodeList n0008_start;    
    public NodeList n0009_implements;
    public NodeList n0010_listeners; 
    public NodeList n0011_objects;   
    public NodeList n0012_superclass;
    public NodeList n0013_defaultpackage;
    public NodeList n0014_bndi;
    public NodeList n0015_subscribers; 
    
    public String s0000_count;
    public String s0001_tagname;   
    public String s0002_autostart;
    public String s0003_classname;   
    public String s0004_id;
    public String s0005_init;   
    public String s0006_package;
    public String s0007_run;   
    public String s0008_start;
    public String s0009_implements;
    public String s0010_listeners;
    public String s0011_objects;
    public String s0012_superclass;
    public String s0013_defaultpackage;
    public String s0014_bodi;
    public String s0015_subscribers;
    
    public XPathExpression e0000_count; 
    public XPathExpression e0001_tagname;    
    public XPathExpression e0002_autostart;
    public XPathExpression e0003_classname;    
    public XPathExpression e0004_id;
    public XPathExpression e0005_init;    
    public XPathExpression e0006_package;
    public XPathExpression e0007_run;    
    public XPathExpression e0008_start;
    public XPathExpression e0009_implements;
    public XPathExpression e0010_listeners;
    public XPathExpression e0011_objects;
    public XPathExpression e0012_superclass;
    public XPathExpression e0013_defaultpackage;
    public XPathExpression e0014_bndi;
    public XPathExpression e0015_subscribers;
    
    public XPath xpath;
    
    /**
     * 
     * @param apmltag
     * @param apmlfile
     */
    public Xpathparameter(String apmltag, File apmlfile)
    {
        this.xpath = XPathFactory.newInstance().newXPath();
        
        try
        {
            this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apmlfile);
        }
        catch(ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace(System.err);
        }       
        
        this.s0000_count            = "count("+apmltag+")";
        this.s0001_tagname          = apmltag; 
        this.s0002_autostart        = apmltag+"/@autostart";
        this.s0003_classname        = apmltag+"/@class"; 
        this.s0004_id               = apmltag+"/@id";
        this.s0005_init             = apmltag+"/@init";
        this.s0006_package          = apmltag+"/@package";
        this.s0007_run              = apmltag+"/@run";
        this.s0008_start            = apmltag+"/@start";   
        this.s0009_implements       = apmltag+"/implements";
        this.s0010_listeners        = apmltag+"/listener"; 
        this.s0011_objects          = apmltag+"/object";     
        this.s0012_superclass       = apmltag+"/@extends";
        this.s0013_defaultpackage   = "//system/package/@default";
        this.s0014_bodi             = apmltag+"/@bodi";
        this.s0015_subscribers      = apmltag+"/listener/subscriber";
        
        try{this.e0000_count            = xpath.compile(s0000_count);}          catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0001_tagname          = xpath.compile(s0001_tagname);}        catch(Exception e){e.printStackTrace(System.err);} 
        try{this.e0002_autostart        = xpath.compile(s0002_autostart);}      catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0003_classname        = xpath.compile(s0003_classname);}      catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0004_id               = xpath.compile(s0004_id);}             catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0005_init             = xpath.compile(s0005_init);}           catch(Exception e){e.printStackTrace(System.err);}            
        try{this.e0006_package          = xpath.compile(s0006_package);}        catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0007_run              = xpath.compile(s0007_run);}            catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0008_start            = xpath.compile(s0008_start);}          catch(Exception e){e.printStackTrace(System.err);}            
        try{this.e0009_implements       = xpath.compile(s0009_implements);}     catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0010_listeners        = xpath.compile(s0010_listeners);}      catch(Exception e){e.printStackTrace(System.err);} 
        try{this.e0011_objects          = xpath.compile(s0011_objects);}        catch(Exception e){e.printStackTrace(System.err);}   
        try{this.e0012_superclass       = xpath.compile(s0012_superclass);}     catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0013_defaultpackage   = xpath.compile(s0013_defaultpackage);} catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0014_bndi             = xpath.compile(s0014_bodi);}           catch(Exception e){e.printStackTrace(System.err);}
        try{this.e0015_subscribers      = xpath.compile(s0015_subscribers);}    catch(Exception e){e.printStackTrace(System.err);}
        
        try{this.evaluate(this);}catch(Exception e){}
    }
       
    public NodeList requestruntimeevaluation(String sxxx_xxxx)
    {
        try
        {
            return (NodeList)this.xpath.compile(sxxx_xxxx).evaluate(this.document, XPathConstants.NODESET);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        return null;
    }
    
    public String getdefaultpackagename()
    {
        try
        {
            return (String)this.e0013_defaultpackage.evaluate(this.document, XPathConstants.STRING);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        return null;
    }
    
    public Long getnodecount()
    {
        long count=0;
        
        try
        {
            if(this.n0000_count==null)
                throw new Exception("Oops, node n0000_count did not get set.");
            
            count = this.n0000_count;
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }        
        
        return count;
    }
    
    public Xpathparameter evaluate(Xpathparameter param) throws Exception
    {          
        if(param.e0000_count!=null)
            try{param.n0000_count = Math.round((double)param.e0000_count.evaluate(param.document, XPathConstants.NUMBER));}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0001_tagname!=null)
            try{param.n0001_tagname = (NodeList)param.e0001_tagname.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0002_autostart!=null)
            try{param.n0002_autostart = (NodeList)param.e0002_autostart.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0003_classname!=null)
            try{param.n0003_classname = (NodeList)param.e0003_classname.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0004_id!=null)
            try{param.n0004_id = (NodeList)param.e0004_id.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}

        if(param.e0005_init!=null)
            try{param.n0005_init = (NodeList)param.e0005_init.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0006_package!=null)
            try{param.n0006_package = (NodeList)param.e0006_package.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}  
        
        if(param.e0007_run!=null)
            try{param.n0007_run = (NodeList)param.e0007_run.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0008_start!=null)
            try{param.n0008_start = (NodeList)param.e0008_start.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}           
        
        if(param.e0009_implements!=null)
            try{param.n0009_implements = (NodeList)param.e0009_implements.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}  
        
        if(param.e0010_listeners!=null)
            try{param.n0010_listeners = (NodeList)param.e0010_listeners.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}
        
        if(param.e0011_objects!=null)
            try{param.n0011_objects = (NodeList)param.e0011_objects.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}          
      
        if(param.e0012_superclass!=null)
            try{param.n0012_superclass = (NodeList)param.e0012_superclass.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}         
        
        if(param.e0013_defaultpackage!=null)
            try{param.n0013_defaultpackage = (NodeList)param.e0013_defaultpackage.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}         
        
        if(param.e0014_bndi!=null)
            try{param.n0014_bndi = (NodeList)param.e0014_bndi.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}         
        
        if(param.e0015_subscribers!=null)
            try{param.n0015_subscribers = (NodeList)param.e0015_subscribers.evaluate(param.document, XPathConstants.NODESET);}
            catch(Exception e){e.printStackTrace(System.err);}            
        
        return param;
    }      
}
