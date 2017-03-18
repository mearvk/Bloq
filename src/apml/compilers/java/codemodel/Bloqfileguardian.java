package apml.compilers.java.codemodel;

import apml.compilers.Bloqabstractfileguardian;
import java.io.File;

/**
 *
 * @author max rupplin
 */
public class Bloqfileguardian extends Bloqabstractfileguardian
{
    public File manifestfile = null;
    
    public File manifestfiledir = null;    
    
    public File sourceoutdir = null;   
    
    public File buildoutdir = null;
    
    public File apmlxmlinputfile = null;
    
    public final String apmlinjarurl = "/home/oem/NetBeansProjects/APML/dist/APML.jar";
    
    public final String apmloutjarurl = "/home/oem/Desktop/apml/output/libs/APML.jar";
    
    public final String apmlinurl = "/home/oem/NetBeansProjects/APML/src/apml/examples/echoserver/server/echoserver.xml";
    
    public final String basedirurl = "/home/oem/Desktop/apml/output/echo/";
    
    public final String builddirurl = "build/";
    
    public final String srcdirurl = "source/";    
    
    public final String tempsrcdirurl = "temp/";
    
    public final String manifestdirurl = "manifest/";
    
    public final String manifestfileurl = "/home/oem/Desktop/apml/output/manifest/manifest.txt";    
}
