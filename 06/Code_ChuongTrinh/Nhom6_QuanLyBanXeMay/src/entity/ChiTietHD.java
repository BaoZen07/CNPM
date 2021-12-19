package entity;

public class ChiTietHD {
	private int soLuong;
	private double donGia;
	private HoaDon hoaDon;
	private XeMay xeMay;
	
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public XeMay getXeMay() {
		return xeMay;
	}
	public void setXeMay(XeMay xeMay) {
		this.xeMay = xeMay;
	}
	
	public ChiTietHD(int soLuong, double donGia, HoaDon hoaDon, XeMay xeMay) {
		super();
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.hoaDon = hoaDon;
		this.xeMay = xeMay;
	}
	public ChiTietHD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Tính thành tiền của chi tiết hóa đơn
	 * @return thành tiền của chi tiết hóa đơn
	 */
	public double tinhThanhTien() {
		return soLuong * donGia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((xeMay == null) ? 0 : xeMay.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHD other = (ChiTietHD) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (xeMay == null) {
			if (other.xeMay != null)
				return false;
		} else if (!xeMay.equals(other.xeMay))
			return false;
		return true;
	}
	
	

}
