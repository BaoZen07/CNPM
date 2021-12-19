package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DatabaseConnection;

public class ThongKeDoanhThu_DAO {
	public ThongKeDoanhThu_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<DoanhThuObject> thongKeTheoThang(int thang, int nam) throws SQLException {		
		Connection conn = DatabaseConnection.getConnection();
		
		ArrayList<DoanhThuObject> list = new ArrayList<DoanhThuObject>();
		
		String query = "select * from ThongKeTheoThang(?, ?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, thang);
		pst.setInt(2, nam);
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			DoanhThuObject doanhThuObject = new DoanhThuObject();
			doanhThuObject.setMaNV(rs.getString("maNV"));
			doanhThuObject.setMaNV(rs.getString("tenNV"));
			doanhThuObject.setSoXeDaBan(rs.getInt("soXeDaBan"));
			doanhThuObject.setDoanhThu(rs.getDouble("doanhThu"));
			list.add(doanhThuObject);
		}
		
		conn.close();
		return list;
	}
	
	public ArrayList<DoanhThuObject> thongKeTheoNam(int nam) throws SQLException {		
		Connection conn = DatabaseConnection.getConnection();
		
		ArrayList<DoanhThuObject> list = new ArrayList<DoanhThuObject>();
		
		String query = "select * from ThongKeTheoNam(?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, nam);
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			DoanhThuObject doanhThuObject = new DoanhThuObject();
			doanhThuObject.setMaNV(rs.getString("maNV"));
			doanhThuObject.setMaNV(rs.getString("tenNV"));
			doanhThuObject.setSoXeDaBan(rs.getInt("soXeDaBan"));
			doanhThuObject.setDoanhThu(rs.getDouble("doanhThu"));
			list.add(doanhThuObject);
		}
		
		conn.close();
		return list;
	}

}
