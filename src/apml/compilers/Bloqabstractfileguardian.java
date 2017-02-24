/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers;

import java.io.File;

/**
 *
 * @author oem
 */
public abstract class Bloqabstractfileguardian 
{
    public File manifestfile;
    
    public File manifestfiledir;    
    
    public File sourceoutdir;   
    
    public File buildoutdir;
    
    public File apmlxmlinputfile;
    
    public final String apmlinjarurl = "";
    
    public final String apmloutjarurl = "";
    
    public final String apmlinurl = "";
    
    public final String basedirurl = "";
    
    public final String builddirurl = "";
    
    public final String srcdirurl = "";    
    
    public final String tempsrcdirurl = "";
    
    public final String manifestdirurl = "";
    
    public final String manifestfileurl = "";    
}
