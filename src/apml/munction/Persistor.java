package apml.munction;

import apml.system.bodi.Bodi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Persistor
{
	public Reference register(Integer GroupID, Integer Type, String MunchkinFileName, String ClassName, String PackageName, String SourceCode)
	{
		Connection connect = (Connection) Bodi.context("munction_analysis").pull("//connect");

		if (connect == null)
			return null;

		//

		PreparedStatement preparedStatement;

		ResultSet resultSet = null;

		Reference reference = null;

		//

		try
		{
			preparedStatement = connect.prepareStatement("INSERT INTO REGISTERED (GROUPID, TYPE, FILENAME, CLASSNAME, PACKAGENAME, SOURCECODE) VALUES (?,?,?,?,?);");

			preparedStatement.setInt(1, GroupID);

			preparedStatement.setInt(2, Type);

			preparedStatement.setString(3, MunchkinFileName);

			preparedStatement.setString(4, ClassName);

			preparedStatement.setString(5, PackageName);

			preparedStatement.setString(6, SourceCode);

			//

			preparedStatement = connect.prepareStatement("SELECT CALLCODE, AUTHKEY FROM REGISTERED WHERE GROUPID = ? AND FILENAME = ? AND CLASSNAME = ? AND PACKAGENAME = ? AND SOURCECODE = ?");

			//

			resultSet = preparedStatement.executeQuery();

			//

			reference = new Reference();

			reference.call_code = resultSet.getInt("CALLCODE");

			reference.auth_key = resultSet.getInt("AUTHKEY");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return reference;
	}

	//

	public Reference register(Integer GroupID, Integer Type, String MunchkinFileName, String ClassName, String PackageName, byte[] Bytecode)
	{
		Connection connect = (Connection) Bodi.context("munction_analysis").pull("//connect");

		if (connect == null)
			return null;

		//

		PreparedStatement preparedStatement;

		ResultSet resultSet = null;

		Reference reference = null;

		//

		try
		{
			preparedStatement = connect.prepareStatement("INSERT INTO REGISTERED (GROUPID, TYPE, FILENAME, CLASSNAME, PACKAGENAME, BYTECODE) VALUES (?,?,?,?,?);");

			preparedStatement.setInt(1, GroupID);

			preparedStatement.setInt(2, Type);

			preparedStatement.setString(3, MunchkinFileName);

			preparedStatement.setString(4, ClassName);

			preparedStatement.setString(5, PackageName);

			preparedStatement.setBytes(6, Bytecode);

			//

			preparedStatement.execute();

			//

			preparedStatement = connect.prepareStatement("SELECT CALLCODE, AUTHKEY FROM REGISTERED WHERE GROUPID = ? AND FILENAME = ? AND CLASSNAME = ? AND PACKAGENAME = ? AND BYTECODE = ?");

			//

			resultSet = preparedStatement.executeQuery();

			//

			reference = new Reference();

			reference.call_code = resultSet.getInt("CALLCODE");

			reference.auth_key = resultSet.getInt("AUTHKEY");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return reference;
	}
}
