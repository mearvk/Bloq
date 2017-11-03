package apml.munction;


import apml.system.bodi.Bodi;

import java.sql.*;

public class Munction //pronounced "munchkin"
{
	//

	public static Registrar registrar = new Registrar();
	public static Persistor persistor = new Persistor();
	public static Munction instance = new Munction();
	public Integer METHOD = 0x00;
	public Integer FUNCTION = 0x01;
	public Integer CLASS = 0x02;

	//
	public Integer DATA = 0x03;
	public Integer OBJECT = 0x04;
	public Integer SYSTEM = 0x05;
	public Connection connect = null;

	//
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;

	//

	public Munction()
	{
		//bodi

		Bodi.context("munction").put("//munction", this);
	}

	//

	public Reference init(String hostname, String dbname, String username, String password)
	{
		try
		{
			connect = DriverManager.getConnection("jdbc:mysql://" + hostname + "/" + dbname + "?user=" + username + "&password=" + password);

			Bodi.context("munction").put("//connect", connect);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//

		Reference reference = new Reference();

		reference.hostname = hostname;

		reference.username = username;

		reference.password = password;

		reference.dbname = dbname;

		//

		return reference;
	}

	//

	public Reference init(String dbname, String username, String password)
	{
		try
		{
			connect = DriverManager.getConnection("jdbc:mysql://localhost/" + dbname + "?user=" + username + "&password=" + password);

			Bodi.context("munction").put("//connect", connect);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//

		Reference reference = new Reference();

		reference.username = username;

		reference.password = password;

		reference.dbname = dbname;

		//

		return reference;
	}

	//

	public Reference register(Integer GroupID, Integer Type, String MunchkinFileName, String ClassName, String PackageName, byte[] Bytecode)
	{
		return this.registrar.register(GroupID, Type, MunchkinFileName, ClassName, PackageName, Bytecode);
	}

	//

	public Reference register(Integer GroupID, Integer Type, String MunchkinFileName, String ClassName, String PackageName, String SourceCode)
	{
		return this.registrar.register(GroupID, Type, MunchkinFileName, ClassName, PackageName, SourceCode);
	}

	//

	public Reference call(Integer CallCode, Integer AuthKey)
	{
		if (connect == null)
			return null;

		//

		PreparedStatement preparedStatement;

		ResultSet resultSet;

		try
		{
			preparedStatement = connect.prepareStatement("SELECT * FROM REGISTERED WHERE CALLCODE = ? AND AUTHKEY = ?");

			preparedStatement.setInt(CallCode, 1);

			preparedStatement.setInt(AuthKey, 2);

			//

			resultSet = preparedStatement.executeQuery();

			//
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return new Reference();
	}
}
