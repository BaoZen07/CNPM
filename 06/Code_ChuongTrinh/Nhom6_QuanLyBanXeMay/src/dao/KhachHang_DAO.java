package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DatabaseConnection;
import entity.KhachHang;

public class KhachHang_DAO {
	private Connection con;
	private ArrayList<KhachHang> dsKH;
	
	public KhachHang_DAO() {
	}
	public ArrayList<KhachHang> getDsKH() {
		return dsKH;
	}
	public List<KhachHang> getAll() throws SQLException {
		con = DatabaseConnection.getConnection();

		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String query = "select * from KhachHang";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maKH =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String diaChi = result.getString("diaChi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			dskh.add(kh);
		}
		con.close();
		return dskh;

	}

	
	public boolean themKhachHang(KhachHang kh) throws SQLException {
		con = DatabaseConnection.getConnection();
		String sql = "insert into KhachHang values(?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTenKH());
			stmt.setString(4, kh.getDiaChiKH());
			stmt.setBoolean(3, kh.isGioiTinhKH());
			stmt.setString(6, kh.getEmailKH());
			stmt.setString(5, kh.getSdtKH());
			
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
		con.close();
		return false;
	}
	

	
	public boolean xoaKhachHang(KhachHang kh) throws SQLException {
		con = DatabaseConnection.getConnection();
		try {
			String sql = "delete from KhachHang where ma = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
				
		}catch (SQLException e) {
			throw new SQLException(e);
		}
		con.close();
		return false;
	}
	
	public KhachHang timKHTheoMa(String maKH) throws SQLException {
		Connection con = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang where ma = '" + maKH + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String ma =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);
			con.close();
			return kh;
		} else {
			con.close();
			return null;
		}
	}
	
	public boolean suaTTKhachHang(KhachHang kh) throws SQLException {
		con = DatabaseConnection.getConnection();
		
			String sql = "update KhachHang set ma = ?, hoTen = ?, gioiTinh = ?, diaChi = ?, sdt = ?, email = ? where ma = '" + kh.getMaKH() + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTenKH());
			stmt.setString(3, kh.getDiaChiKH());
			stmt.setBoolean(4, kh.isGioiTinhKH());
			stmt.setString(5, kh.getEmailKH());
			stmt.setString(6, kh.getSdtKH());
			
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
			con.close();	
			return false;
		
	}
	
	public ArrayList<KhachHang> layDanhSach() {
		return dsKH;
	}

	@Override
	public String toString() {
		return "DanhSachKhachHang [dsKH=" + dsKH + "]";
	}
	
	public KhachHang timTheoMa(String ma) throws SQLException{
		con = DatabaseConnection.getConnection();
		
		KhachHang kh;
		String query = "select * from KhachHang where ma = '" + ma + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		if (result.next()) {
			String maa =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			kh = new KhachHang(maa, hoTen, gioiTinh, diaChi, sdt, email);
			con.close();
			return kh;
		} else {
			con.close();
			return null;
		}
	}
	
	public List<KhachHang> timTheoTenKhachHang(String tenKH) throws SQLException{
		con = DatabaseConnection.getConnection();
		
		List<KhachHang> dskh = new ArrayList<KhachHang>();
			String query = "select * from KhachHang where hoTen like " + "N'%" + tenKH + "%'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()) {
				String maa =  result.getString("ma");
				String hoTen = result.getString("hoTen");
				String diaChi = result.getString("diaChi");
				boolean gioiTinh = result.getBoolean("gioiTinh");
				String email = result.getString("email");
				String sdt = result.getString("sdt");
				
				KhachHang kh = new KhachHang(maa, hoTen, gioiTinh, diaChi, sdt, email);
				dskh.add(kh);	
			}
			
			
			if(dskh.size() > 0) {
				con.close();
				return dskh;
			}
				
			else {
				con.close();
				return null;
			}

		
	}
	
	
}
