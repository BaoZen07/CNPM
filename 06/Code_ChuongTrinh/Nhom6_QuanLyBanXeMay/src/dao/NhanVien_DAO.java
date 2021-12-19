
package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dbconnection.DatabaseConnection;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class NhanVien_DAO {
	private ArrayList<NhanVien> dsNV;
	private Connection conn;

	public NhanVien_DAO() {
		dsNV = new ArrayList<NhanVien>();

	}

	public ArrayList<NhanVien> getDsNV() {
		return dsNV;
	}

	public void setDsNV(ArrayList<NhanVien> dsNV) {
		this.dsNV = dsNV;
	}

	public void getAll() throws SQLException {
		conn = DatabaseConnection.getConnection();
		String query = "select * from NhanVien";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maNV = result.getString("ma");
			String hoTen = result.getString("hoTen");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String diaChi = result.getString("diaChi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean quanLyVien = result.getBoolean("quanLyVien");

			NhanVien nv = new NhanVien(maNV, hoTen, gioiTinh, diaChi, sdt, email, quanLyVien);
			if (!dsNV.contains(nv)) {
				dsNV.add(nv);
			}
		}
		conn.close();
	}

	public boolean xoaNhanVien(String maNV) throws SQLException {
		conn = DatabaseConnection.getConnection();

		String query = "select * from NhanVien where ma = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, maNV);
		if (!st.executeQuery().next())
			return false;

		query = "delete from NhanVien where ma = ?";
		st = conn.prepareStatement(query);
		st.setString(1, maNV);
		st.execute();

		conn.close();

		return true;
	}

	public boolean themNhanVien(NhanVien nv) throws SQLException {
		conn = DatabaseConnection.getConnection();
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?)";
		// Statement st = conn.createStatement();

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, nv.getMaNV());
		stmt.setString(2, nv.getHoTenNV());
		stmt.setBoolean(3, nv.isGioiTinh());
		stmt.setString(4, nv.getDiaChi());
		stmt.setString(5, nv.getSDT());
		stmt.setString(6, nv.getEmail());
		stmt.setBoolean(7, nv.isQuanLyVien());
		int n = stmt.executeUpdate();
		if (n > 0)
			return true;

		conn.close();

		return false;
	}

	public NhanVien timTheoMaNhanVien(String maNV) throws SQLException {
		conn = DatabaseConnection.getConnection();

		String query = "select * from NhanVien where ma = '" + maNV + "'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		NhanVien nv = null;
		if (result.next()) {
			String ma = result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			boolean isManager = result.getBoolean("quanLyVien");

			nv = new NhanVien(ma, hoTen, gioiTinh, diaChi, sdt, email, isManager);

		}
		conn.close();
		return nv;
	}

	public ArrayList<NhanVien> timTheoTenNhanVien(String tenNV) throws SQLException {
		conn = DatabaseConnection.getConnection();
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();

		String query = "select * from NhanVien where hoTen like " + "N'%" + tenNV + "%'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			String ma = result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			boolean isManager = result.getBoolean("quanLyVien");

			NhanVien nv = new NhanVien(ma, hoTen, gioiTinh, diaChi, sdt, email, isManager);
			dsNV.add(nv);

		}
		conn.close();
		if (dsNV.size() > 0)
			return dsNV;
		else
			return null;

	}

	public boolean suaTTNhanVien(NhanVien nv) throws SQLException {
		conn = DatabaseConnection.getConnection();
		try {
			String sql = "update NhanVien set hoTen = ?, gioiTinh = ?,diaChi = ?,sdt = ?,email = ?,quanLyVien = ? where ma = '"
					+ nv.getMaNV() + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nv.getHoTenNV());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setString(3, nv.getDiaChi());
			stmt.setString(4, nv.getSDT().toString());
			stmt.setString(5, nv.getEmail().toString());
			stmt.setBoolean(6, nv.isQuanLyVien());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		conn.close();
		return false;
	}

	public ArrayList<NhanVien> layDanhSach() {
		return dsNV;
	}

	@Override
	public String toString() {
		return "DanhSachNhanVien{" + "dsNV=" + dsNV + '}';
	}

}