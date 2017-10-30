package apml.munction;

import apml.system.bodi.Bodi;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Registrar
{
	public Registrar()
	{
		Bodi.context("munction").put("//registrar", this);
	}

	//

	public Reference register(Integer Type, String MunchkinFileName, String ClassName, String PackageName, String SourceCode)
	{
		Connection connect = (Connection)Bodi.context("munction").pull("//connect");

		if(connect==null) return null;

		//

		PreparedStatement preparedStatement;

		try
		{
			preparedStatement = connect.prepareStatement("INSERT INTO REGISTERED (TYPE, FILENAME, CLASSNAME, PACKAGENAME, SOURCECODE) VALUES (?,?,?,?,?);");

			preparedStatement.setInt(1, Type);

			preparedStatement.setString(2, MunchkinFileName);

			preparedStatement.setString(3, ClassName);

			preparedStatement.setString( 4, PackageName);

			preparedStatement.setString( 5, SourceCode);

			//

			preparedStatement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//

		return new Reference();
	}

	//

	public Reference register(Integer Type, String MunchkinFileName, String ClassName, String PackageName, byte[] Bytecode)
	{
		Connection connect = (Connection)Bodi.context("munction").pull("//connect");

		if(connect==null) return null;

		//

		PreparedStatement preparedStatement;

		try
		{
			preparedStatement = connect.prepareStatement("INSERT INTO REGISTERED (TYPE, FILENAME, CLASSNAME, PACKAGENAME, BYTECODE) VALUES (?,?,?,?,?);");

			preparedStatement.setInt(1, Type);

			preparedStatement.setString(2, MunchkinFileName);

			preparedStatement.setString(3, ClassName);

			preparedStatement.setString( 4, PackageName);

			preparedStatement.setBytes( 5, Bytecode);

			//

			preparedStatement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//

		return new Reference();
	}
}
