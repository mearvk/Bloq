package apml.munction;

import apml.system.bodi.Bodi;

import java.util.HashMap;
import java.util.Map;

//

public class Registrar
{
	public Map<Integer, Object> registrants = new HashMap();

	public Registrar()
	{
		Bodi.context("munction").put("//registrar", this);
	}

	//

	public Reference register(Integer GroupID, Integer Type, String MunchkinFileName, String ClassName, String PackageName, String SourceCode)
	{
		return null;
	}

	//

	public Reference register(Integer GroupID, Integer Type, String MunchkinFileName, String ClassName, String PackageName, byte[] Bytecode)
	{
		return null;
	}
}
