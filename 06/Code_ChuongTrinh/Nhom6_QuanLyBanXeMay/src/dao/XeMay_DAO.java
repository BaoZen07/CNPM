package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dbconnection.DatabaseConnection;
import entity.XeMay;

public class XeMay_DAO {

	private Connection con;

	public XeMay_DAO() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public List<XeMay> getAll() throws SQLException {
		List<XeMay> dsxm = new ArrayList<XeMay>();

		String query = "select * from XeMay";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maxe = result.getString("maXe");
			String tenxe = result.getString("tenXe");
			String loaixe = result.getString("loaiXe");
			String hangxe = result.getString("hangXe");
			int dungtich = Integer.parseInt(result.getString("dungTich"));
			String mauxe = result.getString("mauXe");
			String nuocsx = result.getString("nuocSX");
			int soluongton = Integer.parseInt(result.getString("soLuongTon"));
			double dongia = result.getDouble("donGia");

			XeMay xm = new XeMay(maxe, tenxe, loaixe, hangxe, dungtich, mauxe, nuocsx, soluongton, dongia);
			dsxm.add(xm);
		}
		return dsxm;
	}

	public boolean themXeMay(XeMay xm) throws SQLException {
		String sql = "insert into XeMay values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, xm.getMaXe());
			stmt.setString(2, xm.getTenXe());
			stmt.setString(3, xm.getLoaiXe());
			stmt.setString(4, xm.getHangXe());
			stmt.setInt(5, xm.getDungTich());
			stmt.setString(6, xm.getMauXe());
			stmt.setString(7, xm.getNuocSanXuat());
			stmt.setInt(8, xm.getSoLuongTon());
			stmt.setDouble(9, xm.getDonGia());

			int n = stmt.executeUpdate();
			if (n > 0) 
				return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Trùng mã xe!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		return false;
	}

	public boolean xoaXeMay(XeMay xm) throws SQLException {
		try {
			String sql = "delete from XeMay where maXe = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, xm.getMaXe());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return false;
	}

	public boolean suaTTXeMay(XeMay xm) throws SQLException {
		try {
			String sql = "update XeMay set tenXe = ?, loaiXe = ?, hangXe = ?, dungTich = ?, mauXe = ?, nuocSX = ?, soLuongTon = ?, donGia = ? where maXe = '"
					+ xm.getMaXe() + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, xm.getTenXe());
			stmt.setString(2, xm.getLoaiXe());
			stmt.setString(3, xm.getHangXe());
			stmt.setInt(4, xm.getDungTich());
			stmt.setString(5, xm.getMauXe());
			stmt.setString(6, xm.getNuocSanXuat());
			stmt.setInt(7, xm.getSoLuongTon());
			stmt.setDouble(8, xm.getDonGia());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return false;
	}

	public XeMay timTheoMa(String mx) throws SQLException {
		XeMay xm;
		String query = "select * from XeMay where maXe = '" + mx + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String maxe = result.getString("maXe");
			String tenxe = result.getString("tenXe");
			String loaixe = result.getString("loaiXe");
			String hangxe = result.getString("hangXe");
			int dungtich = Integer.parseInt(result.getString("dungTich"));
			String mauxe = result.getString("mauXe");
			String nuocsx = result.getString("nuocSX");
			int soluongton = Integer.parseInt(result.getString("soLuongTon"));
			double dongia = result.getDouble("donGia");

			xm = new XeMay(maxe, tenxe, loaixe, hangxe, dungtich, mauxe, nuocsx, soluongton, dongia);
			return xm;
		} else
			return null;
	}

	public List<XeMay> timTheoDungTich(int dungtich) throws SQLException {
		List<XeMay> dsxm = new ArrayList<XeMay>();
		
		String query = "select * from XeMay where dungTich <= " + dungtich;
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maxe = result.getString("maXe");
			String tenxe = result.getString("tenXe");
			String loaixe = result.getString("loaiXe");
			String hangxe = result.getString("hangXe");
			int dt = Integer.parseInt(result.getString("dungTich"));
			String mauxe = result.getString("mauXe");
			String nuocsx = result.getString("nuocSX");
			int soluongton = Integer.parseInt(result.getString("soLuongTon"));
			double dongia = result.getDouble("donGia");

			XeMay xm = new XeMay(maxe, tenxe, loaixe, hangxe, dt, mauxe, nuocsx, soluongton, dongia);
			dsxm.add(xm);
		}
		if (dsxm.size() > 0)
			return dsxm;
		else
			return null;
	}

	public List<XeMay> timTheoHangXe(String HangXe) throws SQLException {
		List<XeMay> dsxm = new ArrayList<XeMay>();
		
		String query = "select * from XeMay where hangXe = '" + HangXe + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maxe = result.getString("maXe");
			String tenxe = result.getString("tenXe");
			String loaixe = result.getString("loaiXe");
			String hangxe = result.getString("hangXe");
			int dungtich = Integer.parseInt(result.getString("dungTich"));
			String mauxe = result.getString("mauXe");
			String nuocsx = result.getString("nuocSX");
			int soluongton = Integer.parseInt(result.getString("soLuongTon"));
			double dongia = result.getDouble("donGia");

			XeMay xm = new XeMay(maxe, tenxe, loaixe, hangxe, dungtich, mauxe, nuocsx, soluongton, dongia);
			dsxm.add(xm);
		}
		if (dsxm.size() > 0)
			return dsxm;
		else
			return null;
	}
}
