package apml.xml.handlers;

import apml.xml.handlers.apmltaghandler;
import apml.xpath.helpers.xpathparameter;
import apml.xpath.helpers.xpathquick;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class sourcefilenamer implements RenamesFileByClass, RenamesFilesByClass, RenamesFileByIncr, RenamesFilesByIncr, RenamesFileById, RenamesFilesById
{    
    protected final Integer hash = 0x888fe8;
    
    protected NodeList nodestorename;
    
    protected String apmltag = null;
    protected String apmlobject = null;    
    
    protected String rulesURL = "/home/oem/Desktop/apml/rules/rules.xml";
    protected String apmlURL = "/home/oem/Desktop/apml/apml/echoserver.xml";
    protected String srcURL = "/home/oem/Desktop/apml/src/Test.java";
    
    protected String rulesDIR = "/home/oem/Desktop/apml/rules/";
    protected String apmlDIR = "/home/oem/Desktop/apml/apml/";        
    protected String srcDIR = "/home/oem/Desktop/apml/src/";     
    
    protected File rulesfile = new File(rulesURL);
    protected File apmlfile = new File(apmlURL);
    protected File sourcefile = new File(srcURL);    
    protected File newfile = null;    
   
    public File[] sourcefiles = null;
    
    protected static final Integer PARATOR_INCREMENT = 1;
    protected static final Integer PARATOR_CLASS = 2;
    protected static final Integer PARATOR_ID = 3;               
    
    /**
     * 
     * @param sysobj 
     * @throws java.lang.Exception 
     */
    public sourcefilenamer(String sysobj) throws Exception
    {
        this.apmltag = sysobj;
        
        this.sourcefiles = this.getoutputfiles(sysobj);
    }
    
    /**
     * 
     * @param nodes 
     */
    public sourcefilenamer(NodeList nodes)
    {
        this.nodestorename = nodes;
    }
    
    /**
     * 
     * @param nodes
     * @param sysobj 
     */
    public sourcefilenamer(NodeList nodes, String sysobj)
    {
         this.nodestorename = nodes;
    }    
    
    /**
     * 
     */
    public sourcefilenamer()
    {
        
    }    
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {                        
        sourcefilenamer namer = new sourcefilenamer();
        
        String rulesURL = "/home/oem/Desktop/apml/rules/rules.xml";
        String apmlURL = "/home/oem/Desktop/apml/apml/echoserver.xml";
        String srcURL = "/home/oem/Desktop/apml/src/Test.java";
        
        String rulesDIR = "/home/oem/Desktop/apml/rules/";
        String apmlDIR = "/home/oem/Desktop/apml/apml/";        
        String srcDIR = "/home/oem/Desktop/apml/src/";
        
        for(int i=0;i<10;i++)
        {
            String name=null;
            
            try 
            {
                name = namer.getoutputfile(new File(rulesURL), new File(apmlURL), new File(srcURL)).getName();
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.err.println(name);
            
            try
            {
                try (FileWriter writer = new FileWriter(new File(srcDIR+name))) 
                {
                    writer.write("i%");
                    writer.flush();
                }
            }
            catch(Exception ex)
            {
                Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public File[] getoutputfiles() throws Exception
    {           
        if(this.apmltag==null) 
            throw new InvalidParameterException("String this.apmltag == null");        
        
        try
        {                                            
            int rule = this.getrule(rulesfile);            
            
            switch(rule)
            {
                case 1: return getnames(apmlfile, sourcefile, PARATOR_INCREMENT); 
                case 2: return getnames(apmlfile, sourcefile, PARATOR_CLASS);
                case 3: return getnames(apmlfile, sourcefile, PARATOR_ID);
                
                default: break;
            }            
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        throw new Exception("Unable to return named files for APML file:"+apmlfile);
    }
    
    /**
     * 
     * @param sysobj
     * @return 
     * @throws java.lang.Exception 
     */
    public File getoutputfile(String sysobj) throws Exception
    {                  
        if(sysobj==null) 
            throw new InvalidParameterException("String sysobject == null");
        
        this.apmltag = sysobj;        
        
        try
        {                                
            int rule = this.getrule(rulesfile);            
            
            switch(rule)
            {
                case 1: return getname(apmlfile, sourcefile, PARATOR_INCREMENT);
                case 2: return getname(apmlfile, sourcefile, PARATOR_CLASS);
                case 3: return getname(apmlfile, sourcefile, PARATOR_ID);
                
                default: break;
            }            
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new Exception("Unable to return named file for APML file: "+apmlfile);
    }    
    
    /**
     * 
     * @param sysobj
     * @param apmlfile
     * @return 
     * @throws java.lang.Exception 
     */
    public File[] getoutputfiles(String sysobj, String apmlfile) throws Exception
    {               
        if(sysobj==null) 
            throw new InvalidParameterException("String sysobject == null");
        
        this.apmltag = sysobj;        
        
        try
        {                                
            int rule = this.getrule(rulesfile);            
            
            switch(rule)
            {
                case 1: return getnames(new File(apmlfile), sourcefile, PARATOR_INCREMENT);
                case 2: return getnames(new File(apmlfile), sourcefile, PARATOR_CLASS);
                case 3: return getnames(new File(apmlfile), sourcefile, PARATOR_ID);
                
                default: break;
            }            
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new Exception("Unable to return named files for APML file:"+apmlfile);
    }    
    
    /**
     * 
     * @param rulesfile
     * @param apmlfile
     * @param sourcefile
     * @return 
     * @throws java.lang.Exception 
     */
    public File[] getoutputfiles(File rulesfile, File apmlfile, File sourcefile) throws Exception
    {                
        if(this.apmltag==null) 
            throw new InvalidParameterException("String this.apmltag == null");                
        
        try
        {                                
            int rule = this.getrule(rulesfile);            
            
            switch(rule)
            {
                case 1: return getnames(apmlfile, sourcefile, PARATOR_INCREMENT);
                case 2: return getnames(apmlfile, sourcefile, PARATOR_CLASS);
                case 3: return getnames(apmlfile, sourcefile, PARATOR_ID);
                
                default: break;
            }            
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new Exception("Unable to return named files for APML file:"+apmlfile);
    }        
    
    /**
     * 
     * @param sysobj
     * @return 
     * @throws java.lang.Exception 
     */
    public File[] getoutputfiles(String sysobj) throws Exception
    {   
        if(sysobj==null) 
            throw new InvalidParameterException("String sysobject == null");
        
        this.apmltag = sysobj;                            
        
        try
        {                                
            int rule = this.getrule(rulesfile);            
            
            switch(rule)
            {
                case 1: return getnames(apmlfile, sourcefile, PARATOR_INCREMENT);
                case 2: return getnames(apmlfile, sourcefile, PARATOR_CLASS);
                case 3: return getnames(apmlfile, sourcefile, PARATOR_ID);
                
                default: break;
            }            
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new Exception("Unable to return named files for APML file:"+apmlfile);
    }    
    
    /**
     * 
     * @param rulesfile
     * @param apmlfile
     * @param sourcefile
     * @return 
     * @throws java.lang.Exception 
     */
    public File getoutputfile(File rulesfile, File apmlfile, File sourcefile) throws Exception
    {                  
        if(this.apmltag==null) 
            throw new InvalidParameterException("String sysobject == null");
        
        try
        {                                
            int rule = this.getrule(rulesfile);            
            
            switch(rule)
            {
                case 1: return getname(apmlfile, sourcefile, PARATOR_INCREMENT);
                case 2: return getname(apmlfile, sourcefile, PARATOR_CLASS);
                case 3: return getname(apmlfile, sourcefile, PARATOR_ID);
                
                default: break;
            }            
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        throw new Exception("Unable to return named file for APML file:"+apmlfile);
    }
    
    /**
     * 
     * @param file
     * @return 
     */
    private Integer getrule(File file)
    {
        try
        {             
            //
            apmltaghandler sth = new apmltaghandler();
            DocumentBuilderFactory domfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domfactory.newDocumentBuilder();
            Document document = builder.parse(file);

            //
            XPathFactory xpf;
            XPath xpath;
            XPathExpression expr;

            //
            xpf = XPathFactory.newInstance();
            xpath = xpf.newXPath();
            expr = xpath.compile("//rules/system/compilation/output/rule/text()");
            
            NodeList matches = (NodeList)expr.evaluate(document, XPathConstants.NODESET); 
            
            if(matches.getLength()==1)
            {
                String rule = matches.item(0).getNodeValue().trim();
                
                switch(rule)
                {
                    case "output.parator.increment": return 1;
                    case "output.parator.class": return 2;
                    case "output.parator.id": return 3;
                    
                    default: return 0;
                }
            }                        
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException | DOMException ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @param parator
     * @return 
     */
    private File getname(File apmlfile, File sourcefile, Integer parator) throws Exception
    {
        if(this.apmltag==null) 
            throw new InvalidParameterException("this.apmltag == null");        
        
        try
        {                                     
            switch(parator)
            {
                case 1: return renamefilebyincrement(apmlfile, sourcefile);
                case 2: return renamefilebyclass(apmlfile, sourcefile);
                case 3: return renamefilebyid(apmlfile, sourcefile);
                
                default: break;
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }                
        
        throw new Exception("Unable to return file name");
    }
      
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @param parator
     * @return 
     */
    private File[] getnames(File apmlfile, File sourcefile, Integer parator) throws Exception
    {       
        try
        {                                     
            switch(parator)
            {
                case 1: return renamefilesbyincrement(apmlfile, sourcefile);
                case 2: return renamefilesbyclass(apmlfile, sourcefile);
                case 3: return renamefilesbyid(apmlfile, sourcefile);
                
                default: break;
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(sourcefilenamer.class.getName()).log(Level.SEVERE, null, ex);
        }                
        
        throw new Exception("Unable to return file names");
    }    
    
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @return
     * @throws Exception 
     */
    @Override
    public File renamefilebyclass(File apmlfile, File sourcefile) throws Exception
    {        
        File filename = null;
        String extension = sourcefile.getName().substring(sourcefile.getName().lastIndexOf("."));                                                                
        
        Document document;
        NodeList list = null;
        Object result = null;                
        String sexpr1 = this.apmltag;
        String sexpr2 = this.apmltag+"/@class";        
        XPathExpression expr1;
        XPathExpression expr2;
        xpathparameter xparam;                    
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();        

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        
        document = builder.parse(apmlfile.getPath());
        expr1 = xpath.compile(sexpr1); 
        expr2 = xpath.compile(sexpr2);
        
        
        try
        {
            xparam = new xpathparameter(apmlfile, expr1, expr2, sexpr1, sexpr2, result, document);                
            //xparam = xpathquick.compile(xparam);
            xparam = xpathquick.evaluate(xparam);              
            
            //
            for(int i=0; i<xparam.n0001_tagname.getLength(); i++)
            {
                Node n = xparam.n0001_tagname.item(i);
                
                String s = n.getAttributes().getNamedItem("class").getNodeValue();
                
                if(s.endsWith(".java")) 
                {
                    filename=new File(s);
                }                                
                else 
                {
                    filename=new File(s+".java");
                }  
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
                
        return filename;
    }  
    
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @return
     * @throws Exception 
     */
    @Override
    public File[] renamefilesbyclass(File apmlfile, File sourcefile) throws Exception
    {
        String extension = sourcefile.getName().substring(sourcefile.getName().lastIndexOf("."));                                                                                
        
        Document document;
        NodeList list = null;
        Object result = null;                
        String sexpr1 = this.apmltag;
        String sexpr2 = this.apmltag+"/@class";        
        XPathExpression expr1;
        XPathExpression expr2;
        xpathparameter xparam;                   
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();        

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        
        document = builder.parse(apmlfile.getPath());
        expr1 = xpath.compile(sexpr1); 
        expr2 = xpath.compile(sexpr2);
        
        try
        {
            xparam = new xpathparameter(apmlfile, expr1, expr2, sexpr1, sexpr2, result, document);                
            //xparam = xpathquick.compile(xparam);
            xparam = xpathquick.evaluate(xparam);  
            
            System.err.println();
            
            File[] filenames = new File[xparam.n0001_tagname.getLength()];      
            for(int i=0; i<xparam.n0001_tagname.getLength(); i++)
            {
                Node n = xparam.n0001_tagname.item(i);
                
                String s = n.getAttributes().getNamedItem("class").getNodeValue();
                                
                if(s.endsWith(".java"))  
                {
                    try
                    {
                        filenames[i]=new File(n.getNodeValue());                    
                    }
                    catch(Exception e)
                    {
                        e.addSuppressed(e);
                    }
                }
                else
                {
                    try
                    {
                        filenames[i]=new File(s+".java");                    
                    }
                    catch(Exception e)
                    {
                        e.addSuppressed(e);
                    }                    
                }
            }
            
            return filenames;
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
                
        return null;
    }    
                
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @return
     * @throws Exception 
     */
    @Override
    public File renamefilebyid(File apmlfile, File sourcefile) throws Exception
    {        
        File filename = null;
        String extension = sourcefile.getName().substring(sourcefile.getName().lastIndexOf("."));                                                                
        
        Document document;
        NodeList list = null;
        Object result = null;                
        String sexpr1 = this.apmltag;
        String sexpr2 = this.apmltag+"/@id";        
        XPathExpression expr1;
        XPathExpression expr2;
        xpathparameter xparam;                    
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();        

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        
        document = builder.parse(apmlfile.getPath());
        expr1 = xpath.compile(sexpr1); 
        expr2 = xpath.compile(sexpr2);
                
        try
        {
            xparam = new xpathparameter(apmlfile, expr1, expr2, sexpr1, sexpr2, result, document);                
            //xparam = xpathquick.compile(xparam);
            xparam = xpathquick.evaluate(xparam);              
            
            //
            for(int i=0; i<xparam.n0001_tagname.getLength(); i++)
            {
                Node n = xparam.n0001_tagname.item(i);
                
                String s = n.getAttributes().getNamedItem("id").getNodeValue();
                
                if(s.endsWith(".java"))   
                {
                    filename = new File(s);                                    
                }                    
                else
                {
                    filename = new File(s+".java");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
                
        return filename;
    }       
    
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @return
     * @throws Exception 
     */
    @Override
    public File[] renamefilesbyid(File apmlfile, File sourcefile) throws Exception
    {        
        File[] files = new File[1000];        
        String extension = sourcefile.getName().substring(sourcefile.getName().lastIndexOf("."));                                                                
        
        Document document;
        NodeList list = null;
        Object result = null;                
        String sexpr1 = this.apmltag;
        String sexpr2 = this.apmltag+"/@id";        
        XPathExpression expr1;
        XPathExpression expr2;
        xpathparameter xparam;                    
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();        

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        
        document = builder.parse(apmlfile.getPath());
        expr1 = xpath.compile(sexpr1); 
        expr2 = xpath.compile(sexpr2);
        
        try
        {
            xparam = new xpathparameter(apmlfile, expr1, expr2, sexpr1, sexpr2, result, document);                
            //xparam = xpathquick.compile(xparam);
            xparam = xpathquick.evaluate(xparam);              
            
            for(int i=0; i<xparam.n0001_tagname.getLength(); i++)
            {
                Node node = xparam.n0001_tagname.item(i);                
                String nodeval = node.getAttributes().getNamedItem("id").getNodeValue();
                
                if(nodeval.endsWith(".java"))
                {
                    files[i] = new File(nodeval);                
                }                    
                else
                {
                    files[i] = new File(nodeval+".java");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
                
        return files;
    }       
    
    /**
     * 
     * @param apmlfile
     * @param sourcefile
     * @return 
     */
    @Override
    public File renamefilebyincrement(File apmlfile, File sourcefile)
    {       
        File[] files = new File[1000];
        
        String extension = sourcefile.getName().substring(sourcefile.getName().lastIndexOf("."));                
        String format = sourcefile.getName().substring(0,sourcefile.getName().lastIndexOf("."))+"%03d.java";

        if(new File(sourcefile.getParent()+File.separator+String.format(format, 0)).exists())
        {
            for(int i=000;i<=999;i++) 
            {
                File _f = new File(sourcefile.getParent()+File.separator+String.format(format, i));
                    
                if(!_f.exists())
                {
                    files[i] = new File(String.format(format, i));
                }                    
            }                
        }
               
        return files[0];
    }     
    
    //
    @Override
    public File[] renamefilesbyincrement(File apmlfile, File sourcefile)
    {      
        File[] files = new File[1000];
        
        String extension = sourcefile.getName().substring(sourcefile.getName().lastIndexOf("."));                
        String format = sourcefile.getName().substring(0,sourcefile.getName().lastIndexOf("."))+"%03d.java";

        if(new File(sourcefile.getParent()+File.separator+String.format(format, 0)).exists())
        {
            for(int i=000;i<=999;i++) 
            {
                File _f = new File(sourcefile.getParent()+File.separator+String.format(format, i));
                    
                if(!_f.exists())
                {
                    files[i] = new File(String.format(format, i));
                }                    
            }                
        }
        
        return files;
    }       
    
}

/**
 * 
 * @author Max Rupplin
 */
interface RenamesFileByClass
{
    public File renamefilebyclass(File file1, File file2) throws Exception;
}

/**
 * 
 * @author Max Rupplin
 */
interface RenamesFilesByClass
{
    public File[] renamefilesbyclass(File file1, File file2) throws Exception;
}

/**
 * 
 * @author Max Rupplin
 */
interface RenamesFileByIncr
{
    public File renamefilebyincrement(File file1, File file2) throws Exception;
}

/**
 * 
 * @author Max Rupplin
 */
interface RenamesFilesByIncr
{
    public File[] renamefilesbyincrement(File file1, File file2) throws Exception;
}

/**
 * 
 * @author Max Rupplin
 */
interface RenamesFileById
{
    public File renamefilebyid(File file1, File file2) throws Exception;
}

/**
 * 
 * @author Max Rupplin
 */
interface RenamesFilesById
{
    public File[] renamefilesbyid(File apmlfile, File sourcefile) throws Exception;
}