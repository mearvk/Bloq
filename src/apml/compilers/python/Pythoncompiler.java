package apml.compilers.python;

import apml.compilers.Standardabstractapmlcompiler;

import apml.compilers.java.codemodel.Bloqapmlmanager;

import apml.compilers.java.codemodel.Bloqfileguardian;

import apml.compilers.java.codemodel.Bloqjcodemodelmanager;

/**
 *
 * @author max rupplin
 */
public class Pythoncompiler extends Standardabstractapmlcompiler 
{
    @Override
    public void setapmlfiles(Bloqfileguardian fileguardian)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void settempfiles(Bloqapmlmanager apmlmanager)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setoutputfiles(Bloqapmlmanager apmlmanager)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setsourcefiles(Bloqjcodemodelmanager astmanager)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
