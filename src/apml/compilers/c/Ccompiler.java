/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.c;

import apml.compilers.Bloqabstractapmlmanager;
import apml.compilers.Bloqabstractfileguardian;
import apml.compilers.Bloqabstractoutputmanager;
import apml.compilers.Stdabstractcompiler;

/**
 *
 * @author max rupplin
 */
public class Ccompiler extends Stdabstractcompiler 
{

    @Override
    public void setapmlfiles(Bloqabstractfileguardian fileguardian)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void settempfiles(Bloqabstractapmlmanager apmlmanager)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setoutputfiles(Bloqabstractapmlmanager apmlmanager)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setsourcefiles(Bloqabstractoutputmanager astmanager)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}
