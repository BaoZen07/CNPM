package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dbconnection.DatabaseConnection;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.XeMay;

public class CTHD_DAO {

	private XeMay getXeMay(Connection con, String maXeMay) throws SQLException {
		String query = "select * from XeMay where maXe = '" + maXeMay + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String maxe = result.getString(1);
			String tenxe = result.getString(2);
			String loaixe = result.getString(3);
			String hangxe = result.getString(4);
			int dungtich = result.getInt(5);
			String mauxe = result.getString(6);
			String nuocsx = result.getString(7);
			int slt = result.getInt(8);
			double dongia = result.getDouble(9);

			XeMay xm = new XeMay(maxe, tenxe, loaixe, hangxe, dungtich, mauxe, nuocsx, slt, dongia);
			return xm;
		} else
			return null;
	}

	private HoaDon getHoaDon(Connection con, String maHoaDon) throws SQLException {
		String query = "select * from HoaDon where maHD = '" + maHoaDon + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		String maKH = "";
		String maNV = "";
		LocalDate ngayLap = null;

		if (result.next()) {
			maKH = result.getString(2);
			maNV = result.getString(3);
			ngayLap = LocalDate.parse(result.getDate(4).toString());
		}

		NhanVien nv = getNhanVien(con, maNV);
		KhachHang kh = getKhachHang(con, maKH);

		HoaDon hd = new HoaDon(maHoaDon, nv, kh, ngayLap);

		return hd;
	}

	public KhachHang getKhachHang(Connection conn, String maKH) throws SQLException {
		String query = "select * from KhachHang where ma = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, maKH);
		ResultSet result = stmt.executeQuery();

		if (result.next()) {
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");

			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			return kh;
		} else
			return null;
	}

	public NhanVien getNhanVien(Connection conn, String maNhanVien) throws SQLException {
		String query = "select * from KhachHang where ma = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, maNhanVien);

		ResultSet result = stmt.executeQuery();

		if (result.next()) {
			String maNV = result.getString("ma");
			String hoTen = result.getString("hoTen");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String diaChi = result.getString("diaChi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean quanLyVien = result.getBoolean("quanLyVien");

			NhanVien nv = new NhanVien(maNV, hoTen, gioiTinh, diaChi, sdt, email, quanLyVien);
			return nv;
		} else
			return null;
	}

	public ArrayList<ChiTietHD> timTheoMaHD(String ma) throws SQLException {
		ArrayList<ChiTietHD> dsCTHD = new ArrayList<ChiTietHD>();
		Connection conn = null;

		conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD where maHD = ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, ma);

		ResultSet result = pst.executeQuery();

		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();

		String maHD = "";
		String maXM = "";
		while (result.next()) {
			ArrayList<Object> l = new ArrayList<Object>();
			maHD = result.getString(1);
			maXM = result.getString(2);
			int soLuong = result.getInt(3);
			double donGia = result.getDouble(4);

			l.add(maHD);
			l.add(maXM);
			l.add(soLuong);
			l.add(donGia);

			list.add(l);
		}

		HoaDon hd = getHoaDon(conn, ma);

		for (ArrayList<Object> arrayList : list) {
			ChiTietHD cthd = new ChiTietHD();
			XeMay xm = getXeMay(conn, (String) arrayList.get(1));

			cthd.setHoaDon(hd);
			cthd.setXeMay(xm);
			cthd.setSoLuong((int) arrayList.get(2));
			cthd.setDonGia((double) arrayList.get(3));

			dsCTHD.add(cthd);
		}

		conn.close();
		return dsCTHD;
	}

	public boolean themCTHD(ChiTietHD cthd) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD where maHD = ? and maXeMay = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, cthd.getHoaDon().getMaHD());
		pst.setString(2, cthd.getXeMay().getMaXe());

		ResultSet rs = pst.executeQuery();
		if (rs.next())
			return false;

		query = "insert into ChiTietHD values(?, ?, ?, ?)";
		pst = conn.prepareStatement(query);
		pst.setString(1, cthd.getHoaDon().getMaHD());
		pst.setString(2, cthd.getXeMay().getMaXe());
		pst.setInt(3, cthd.getSoLuong());
		pst.setDouble(4, cthd.getDonGia());

		pst.execute();

		conn.close();
		return true;
	}

	public boolean xoaCTHD(String maHD, String maXM) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD where maHD = ? and maXeMay = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, maHD);
		pst.setString(2, maXM);

		ResultSet rs = pst.executeQuery();

		if (!rs.next())
			return false;

		query = "delete from ChiTietHD where maHD = ? and maXeMay = ?";
		pst = conn.prepareStatement(query);
		pst.setString(1, maHD);
		pst.setString(2, maXM);

		pst.execute();

		conn.close();
		return true;
	}

	public boolean suaCTHD(String maHD, String maXM, ChiTietHD cthdMoi) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD where maHD = ? and maXeMay = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, maHD);
		pst.setString(2, maXM);

		ResultSet rs = pst.executeQuery();

		if (!rs.next())
			return false;
		
		query = "update ChiTietHD set soLuong = ?, donGia = ?";
		pst = conn.prepareStatement(query);
		pst.setInt(1, cthdMoi.getSoLuong());
		pst.setDouble(2, cthdMoi.getDonGia());
		
		pst.execute();
		
		conn.close();
		return true;
	}

}
