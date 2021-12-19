package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.DatabaseConnection;

public class DangNhap_DAO {
	public static boolean laQuanLyVien() throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from NhanVien where ma = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, DatabaseConnection.userName);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			if(rs.getBoolean("quanLyVien"))
				return true;
		}
		
		conn.close();
		
		return false;
	
	}

}
