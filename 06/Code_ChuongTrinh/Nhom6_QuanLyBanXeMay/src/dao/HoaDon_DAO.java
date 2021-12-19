package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dbconnection.DatabaseConnection;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {

	public HoaDon_DAO() {
	}

	public ArrayList<HoaDon> getAll() throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();

		Connection conn = null;

		conn = DatabaseConnection.getConnection();

		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma";

		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();

		return list;
	}

	/***
	 * Kiểm tra hóa đơn có tồn tại trong dữ liệu hay chưa
	 * @param conn
	 * @param maHD
	 * @return true nếu đã tồn tại, false nếu chưa tôn tại
	 * @throws SQLException
	 */
	private boolean kiemTraHD(Connection conn, String maHD) throws SQLException {
		String query = "select * from HoaDon where maHD = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, maHD);
		if (st.executeQuery().next())
			return true;
		
		return false;
	}
	
	public String themHoaDon(HoaDon hd) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		if(kiemTraHD(conn, hd.getMaHD()))
			return "Mã hóa đơn bị trùng!";

		String query = "select * from KhachHang where ma = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st = conn.prepareStatement(query);
		st.setString(1, hd.getKhachHang().getMaKH());

		if (!st.executeQuery().next())
			return "Mã khách hàng không tồn tại!";

		query = "insert into HoaDon values (?, ?, ?, ?)";
		st = conn.prepareStatement(query);
		st.setString(1, hd.getMaHD());
		st.setString(2, hd.getKhachHang().getMaKH());
		st.setString(3, hd.getNhanVien().getMaNV());
		st.setDate(4, Date.valueOf(hd.getNgayLap()));

		st.execute();

		conn.close();
		
		return null;
	}

	public boolean xoaHoaDon(String maHD) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from HoaDon where maHD = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, maHD);
		if (!st.executeQuery().next())
			return false;

		query = "delete from HoaDon where maHD = ?";
		st = conn.prepareStatement(query);
		st.setString(1, maHD);
		st.execute();
		
		conn.close();
		
		return true;
	}

	public boolean suaHoaDon(String maHDCu, HoaDon hdMoi) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		if(!kiemTraHD(conn, maHDCu))
			return false;
		
		String query = "update HoaDon set maKH = ?, maNV = ?, ngayLap = ? where maHD = ?";
		
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, hdMoi.getKhachHang().getMaKH());
		pst.setString(2, hdMoi.getNhanVien().getMaNV());
		pst.setDate(3, Date.valueOf(hdMoi.getNgayLap()));
		pst.setString(4, maHDCu);
		
		pst.execute();
		
		conn.close();
		
		return true;
	}

	public HoaDon timTheoMaHD(String ma) throws SQLException {
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma "
				+ "where maHD = ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, ma);
		
		ResultSet result = pst.executeQuery();

		HoaDon hd = null;
		if (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(11);
			String hoTenNV = result.getString(12);
			boolean gtNV = result.getBoolean(13);
			String diaChiNV = result.getString(14);
			String sdtNV = result.getString(15);
			String emailNV = result.getString(16);
			boolean quanLyVien = result.getBoolean(17);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			hd = new HoaDon(maHD, nv, kh, ngayLap);

		}

		conn.close();
		return hd;
	}

	public ArrayList<HoaDon> timTheoMaKH(String ma) throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma "
				+ "where a.ma like ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, '%' + ma + '%');
		
		ResultSet result = pst.executeQuery();

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();
		return list;
	}

	public ArrayList<HoaDon> timTheoMaNV(String ma) throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma "
				+ "where c.ma like ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, '%' + ma + '%');
		
		ResultSet result = pst.executeQuery();

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();
		return list;
	}
	
	public void xuatHoaDon(String maHD, File outFile) throws SQLException, IOException {
		HoaDon hd = timTheoMaHD(maHD);
		
		File fileInput = new File("Data/MauHoaDon.xlsx");
		
		FileInputStream fis = new FileInputStream(fileInput);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		XSSFFont font = workbook.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeight(13);
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		
		XSSFCell cell = sheet.getRow(4).createCell(1); cell.setCellStyle(cellStyle);
		cell.setCellValue(hd.getMaHD()); 
		XSSFCell cell1 = sheet.getRow(5).createCell(1); cell1.setCellStyle(cellStyle);
		cell1.setCellValue(hd.getKhachHang().getHoTenKH()); 
		XSSFCell cell2 = sheet.getRow(6).createCell(1); cell2.setCellStyle(cellStyle);
		cell2.setCellValue(hd.getNhanVien().getHoTenNV());
		XSSFCell cell3 = sheet.getRow(7).createCell(1); cell3.setCellStyle(cellStyle);
		cell3.setCellValue(hd.getNgayLap().toString());
		
		XSSFCellStyle cellStyle2 = sheet.getRow(9).getCell(0).getCellStyle();
		
		ArrayList<ChiTietHD> list = new CTHD_DAO().timTheoMaHD(maHD);
		int i = 10;
		for(ChiTietHD item : list) {
			XSSFRow row = sheet.createRow(i);
			
			XSSFCell c = row.createCell(0); c.setCellStyle(cellStyle2);
			c.setCellValue(item.getXeMay().getMaXe());
			XSSFCell c1 = row.createCell(1); c1.setCellStyle(cellStyle2);
			c1.setCellValue(item.getXeMay().getTenXe());
			XSSFCell c2 = row.createCell(2); c2.setCellStyle(cellStyle2);
			c2.setCellValue(item.getDonGia());
			XSSFCell c3 = row.createCell(3); c3.setCellStyle(cellStyle2);
			c3.setCellValue(item.getSoLuong());
			
			i++;
		}
		
		sheet.createRow(i);
		
		workbook.write(new FileOutputStream(outFile));
		
		workbook.close();
		
	}

}
