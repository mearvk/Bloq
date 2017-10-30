package apml.munction;


import apml.system.bodi.Bodi;

import java.sql.*;

public class Munction //pronounced "munchkin"
{
	//

	Integer METHOD = 0x00;

	Integer FUNCTION = 0x01;

	Integer CLASS = 0x02;

	Integer DATA = 0x03;

	Integer OBJECT = 0x04;

	Integer SYSTEM = 0x05;

	//

	private Connection connect = null;

	private Statement statement = null;

	private PreparedStatement preparedStatement = null;

	private ResultSet resultSet = null;

	//

	public Munction()
	{
		//bodi

		Bodi.context("munction").put("//munction", this);
	}

	//

	public Reference init(String hostname, String dbname,  String username, String password )
	{
		try
		{
			connect = DriverManager.getConnection("jdbc:mysql://"+hostname+"/"+dbname+"?user="+username+"&password="+password);
		}
		catch(Exception e)
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

	public Reference init(String dbname,  String username, String password )
	{
		try
		{
			connect = DriverManager.getConnection("jdbc:mysql://localhost/"+dbname+"?user="+username+"&password="+password);
		}
		catch(Exception e)
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

	public Reference register(Integer Type, String MunchkinFileName, String ClassName, String PackageName, String SourceCode)
	{
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

	//

	public Reference call(Integer CallCode, Integer AuthKey)
	{
		if(connect==null) return null;

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
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return new Reference();
	}
}
