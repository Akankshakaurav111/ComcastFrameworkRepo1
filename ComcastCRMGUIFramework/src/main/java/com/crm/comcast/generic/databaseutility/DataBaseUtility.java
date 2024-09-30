package com.crm.comcast.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility 
{
	Connection conn;
public void getDbConnection(String url, String username, String password) throws SQLException
{
	try 
	{
	Driver driver=new Driver();
	DriverManager.registerDriver(driver);
    conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
	}
	catch (Exception e) 
	{
		
	}
}
public void closeConnection() throws SQLException
{
	try {
	conn.close();
	}
	catch (Exception e) {
	}
}
public ResultSet excuteSelectQuery(String querry) throws SQLException
{
	ResultSet result=null;
	try {
	Statement stat = conn.createStatement();
     result = stat.executeQuery(querry);
	}
	catch (Exception e) 
	{
		
	}
	return result;
}
public int executeNonSelectQuery(String querry) throws SQLException
{
	int result=0;
	try {
	Statement stat = conn.createStatement();
	 result = stat.executeUpdate(querry);
}
	catch (Exception e) {
	}
    return result;

}
}
