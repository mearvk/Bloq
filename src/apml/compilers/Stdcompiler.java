/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers;

/**
 *
 * @author oem
 */
public abstract class Stdcompiler 
{
    public String defaultpackagename = "apml.default";
    
    public abstract void compiletosource();
    
    public void setdefaultpackagename(String pkgname)
    {
        this.defaultpackagename = pkgname;
    }
}
