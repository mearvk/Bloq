/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system.bodi.remote;

/**
 *
 * @author oem
 */
public class SessionStorageError extends Error
{
    public SessionStorageError(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connection");
    }
}
