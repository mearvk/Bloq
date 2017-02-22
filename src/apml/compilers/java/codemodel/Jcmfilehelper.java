/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.java.codemodel;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class Jcmfilehelper 
{
    public File manifestfile;
    public File manifestfiledir;    
    public File sourcedir;   
    public File apmlxmlinputfile;
    
    public static final String apmlinjarurl = "/home/oem/NetBeansProjects/APML/dist/APML.jar";
    public static final String apmloutjarurl = "/home/oem/Desktop/apml/output/libs/APML.jar";
    public static final String apmlinurl = "/home/oem/NetBeansProjects/APML/src/apml/examples/echoserver/server/echoserver.xml";
    
    public static final String basedirurl = "/home/oem/Desktop/apml/output/echo/";
    public static final String builddirurl = "build/";
    public static final String srcdirurl = "source/";    
    public static final String tempsrcdirurl = "temp/";
    
    public static final String manifestdirurl = "manifest/";
    public static final String manifestfileurl = "/home/oem/Desktop/apml/output/manifest/manifest.txt";    
}
