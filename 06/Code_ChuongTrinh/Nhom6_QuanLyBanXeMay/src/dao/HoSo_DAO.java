package dao;

import java.sql.SQLException;
import java.sql.Statement;

import dbconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.NhanVien;

public class HoSo_DAO {
	private NhanVien nhanVien;
	
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setnhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public void getProfile() throws SQLException{
		
		Connection con = DatabaseConnection.getConnection();
		String userName = DatabaseConnection.userName;
		String query = "Select * from NhanVien where ma = '" + userName + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
			
		while(result.next()) {
			String manv = result.getString("ma");
			String hoten = result.getString("hoTen");
			boolean gioitinh = result.getBoolean("gioiTinh");
			String diachi = result.getString("diaChi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean chucvu = result.getBoolean("quanLyVien");
				
			nhanVien = new NhanVien(manv, hoten, gioitinh, diachi, sdt, email, chucvu);
		}
		
		con.close();
	}
	
	public boolean modifiedProfile(NhanVien nv) throws SQLException{
			Connection con = DatabaseConnection.getConnection();
			String userName = DatabaseConnection.userName;
			String query = "update NhanVien set hoTen = ?, gioiTinh = ?,diaChi = ?,sdt = ?,email = ?,quanLyVien = ? where ma = '"
	                + userName + "'" ;
			PreparedStatement stmt = con.prepareStatement(query);
			 stmt.setString(1,nv.getHoTenNV());
	         stmt.setBoolean(2,nv.isGioiTinh());
	         stmt.setString(3,nv.getDiaChi());
	         stmt.setString(4,nv.getSDT().toString());
	         stmt.setString(5,nv.getEmail().toString());
	         stmt.setBoolean(6,nv.isQuanLyVien());
	         int n = stmt.executeUpdate();
	         if(n>0) {
	        	 con.close();
	             return true;
	         }
	    con.close();
		return false;
	}
}
