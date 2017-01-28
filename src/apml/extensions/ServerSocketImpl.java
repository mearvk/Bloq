/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.extensions;

/**
 *
 * @author oem
 */
public abstract class ServerSocketImpl 
{
    public void start(){}
    public void onstart(){}
    
    public void restart(){}
    public void onrestart(){}
    
    public void pause(){}
    public void onpause(){}
    
    public void stop(){}
    public void onstop(){}
    
    public void exit(){}
    public void onexit(){}
    
    public void resume(){}
    public void onresume(){}
    
    public void connect(){}
    public void onconnect(){}
    
    public void disconnect(){}
    public void ondisconnect(){}
    
    public void read(){}
    public void onread(){}
    
    public void poll(){}
    public void onpoll(){}
    
    public void write(){}
    public void onwrite(){}
    
    public void event(){}
    public void onevent(){}
    
    public void status(){}
    public void onstatus(){}        
}
