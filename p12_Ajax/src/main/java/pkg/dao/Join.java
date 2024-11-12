package pkg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Join {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// DB 접속 담당 메서드(=> DBCP, JNDI 사용)
	public void mtdGetConn() throws Exception {
		Context init = new InitialContext();
		DataSource dataSource = (DataSource)init.lookup("java:comp/env/jdbc/dbcpConn");
		conn = dataSource.getConnection();
		
		System.out.println("DBCP 접속 OK!");
	}
	
	// 입력받고 서버로 전달받은 데이터(=userId)를
	// 사용하여 아이디 중복확인용 메서드
	public int chkUserId(String userId) {
		int rtnCnt = 0;
		
		try {
			mtdGetConn();
			System.out.println("userId : " + userId);
			String sql = "select count(*) from member ";
			sql += "where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			
			rtnCnt = rs.getInt(1);
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return rtnCnt;
	}
	
}





