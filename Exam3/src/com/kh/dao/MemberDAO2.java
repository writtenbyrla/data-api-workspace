package com.kh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO2 {

	public Connection getConnection() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "kh";
		String pw = "kh";

		return DriverManager.getConnection(url,id,pw);

	}
	
	public boolean isEmailExist(String email) throws Exception{
		
		System.out.println("DAO email : " + email);
		String sql = "select email from members where email=?";
		
		Connection conn = this.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("DAO : " + rs.next()); // true
		
		return rs.next();
		
	}
	
	
	
}
