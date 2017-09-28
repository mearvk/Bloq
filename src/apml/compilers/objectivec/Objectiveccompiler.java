package apml.compilers.objectivec;

import apml.compilers.Standardabstractapmlcompiler;
import apml.compilers.java.codemodel.Bloqfileguardian;
import apml.compilers.java.codemodel.Bloqinputmanager;
import apml.compilers.java.codemodel.Bloqoutputmanager;

/**
 *
 * @author Max Rupplin
 */
public class Objectiveccompiler extends Standardabstractapmlcompiler 
{

    @Override
    public void setapmlfiles(Bloqfileguardian fileguardian)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void settempfiles(Bloqinputmanager apmlmanager)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setoutputfiles(Bloqinputmanager apmlmanager)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setsourcefiles(Bloqoutputmanager astmanager)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void writebytecode(Bloqinputmanager apmlmanager)
	{

	}

}