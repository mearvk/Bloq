/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.interfaces;

/**
 *
 * @author Max Rupplin 
 */
public interface Apmlsystemelement
{
    public void autostart();
    
    public void init();
    
    public void pausable();
    
    public void restart();
    
    public void run();
    
    public void start();
    
    public void stop();
}
