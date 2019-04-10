package com.readGmail;
import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class MysqlConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	static Connection con = null;

    public static Connection getConnectionObj() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = (Connection) DriverManager.getConnection("jdbc:mysql://35.199.31.139:3306/InputDaDatabase","root",""); //ajaxtest
//            System.out.println("Connection Established");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/inputdadatabase","root","password"); //ajaxtest
            return con;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

}
