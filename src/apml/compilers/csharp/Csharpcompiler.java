package apml.compilers.csharp;

import apml.compilers.Standardabstractapmlcompiler;
import apml.compilers.java.codemodel.Bloqinputmanager;
import apml.compilers.java.codemodel.Bloqoutputmanager;

/**
 * @author max rupplin
 */
public class Csharpcompiler extends Standardabstractapmlcompiler
{

	@Override
	public void setapmlfiles(apml.compilers.java.codemodel.Bloqfileguardian fileguardian)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void settempfiles(apml.compilers.java.codemodel.Bloqinputmanager apmlmanager)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setoutputfiles(apml.compilers.java.codemodel.Bloqinputmanager apmlmanager)
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
		//
	}
}
