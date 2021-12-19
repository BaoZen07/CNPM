package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DatabaseConnection;
import entity.BaoHanh;


public class BaoHanh_DAO {
	private Connection con;
	private ArrayList<BaoHanh> dsBH;
	
	public BaoHanh_DAO() {
	}
	public ArrayList<BaoHanh> getDsBH() {
		return dsBH;
	}
	public List<BaoHanh> getAll() throws SQLException {
		con = DatabaseConnection.getConnection();

		List<BaoHanh> dsBH = new ArrayList<BaoHanh>();
		String query = "select * from BaoHanh";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String MaBH =  result.getString("MaBH");
			String MaXe = result.getString("MaXe");
			String Tenxe = result.getString("Tenxe");
			String ThongtinBH = result.getString("ThongtinBH");
			BaoHanh Bh = new BaoHanh(MaBH,MaXe,Tenxe,ThongtinBH);
			dsBH.add(Bh);
		}
		con.close();
		return dsBH;

	}

	
	public boolean themBaoHanh(BaoHanh bh) throws SQLException {
		con = DatabaseConnection.getConnection();
		String sql = "insert into BaoHanh values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bh.getMaBH());
			stmt.setString(2, bh.getMaXe());
			stmt.setString(3, bh.getTenxe());
			stmt.setString(4, bh.getThongtinBH());
			
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
		con.close();
		return false;
	}
	

	
	public boolean xoaBaoHanh(BaoHanh bh) throws SQLException {
		con = DatabaseConnection.getConnection();
		try {
			String sql = "delete from BaoHanh where MaBH = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bh.getMaBH());
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
	
	
	public boolean suaTTBH(BaoHanh bh) throws SQLException {
		con = DatabaseConnection.getConnection();
		
			String sql = "update BaoHanh set MaBH = ?, MaXe = ?, Tenxe = ?, ThongtinBH = ? where MaBH = '" + bh.getMaBH() + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, bh.getMaBH());
			stmt.setString(2, bh.getMaXe());
			stmt.setString(4, bh.getTenxe());
			stmt.setString(3, bh.getThongtinBH());			
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
			con.close();	
			return false;
		
	}
	
	public ArrayList<BaoHanh> layDanhSach() {
		return dsBH;
	}

	@Override
	public String toString() {
		return "DanhSachBaoHanh [dsBH=" + dsBH + "]";
	}
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	public BaoHanh get(int rows) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
