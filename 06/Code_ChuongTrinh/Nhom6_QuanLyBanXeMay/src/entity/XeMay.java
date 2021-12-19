package entity;

public class XeMay {
	private String maXe;
	private String tenXe;
	private String loaiXe;
	private String hangXe;
	private int dungTich;
	private String mauXe;
	private String nuocSanXuat;
	private int soLuongTon;
	private double donGia;
	public String getMaXe() {
		return maXe;
	}
	public void setMaXe(String maXe) {
		this.maXe = maXe;
	}
	public String getTenXe() {
		return tenXe;
	}
	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}
	public String getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}
	public String getHangXe() {
		return hangXe;
	}
	public void setHangXe(String hangXe) {
		this.hangXe = hangXe;
	}
	public int getDungTich() {
		return dungTich;
	}
	public void setDungTich(int dungTich) {
		this.dungTich = dungTich;
	}
	public String getMauXe() {
		return mauXe;
	}
	public void setMauXe(String mauXe) {
		this.mauXe = mauXe;
	}
	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public XeMay() {
		super();
	}
	public XeMay(String maXe) {
		this.maXe = maXe;
	}
	public XeMay(String maXe, String tenXe, String loaiXe, String hangXe, int dungTich, String mauXe,
			String nuocSanXuat, int soLuongTon, double donGia) {
		super();
		this.maXe = maXe;
		this.tenXe = tenXe;
		this.loaiXe = loaiXe;
		this.hangXe = hangXe;
		this.dungTich = dungTich;
		this.mauXe = mauXe;
		this.nuocSanXuat = nuocSanXuat;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
	}
	@Override
	public String toString() {
		return "XeMay [maXe=" + maXe + ", tenXe=" + tenXe + ", loaiXe=" + loaiXe + ", hangXe=" + hangXe + ", dungTich="
				+ dungTich + ", mauXe=" + mauXe + ", nuocSanXuat=" + nuocSanXuat + ", soLuongTon=" + soLuongTon + ",donGia=" + donGia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maXe == null) ? 0 : maXe.hashCode());
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
		XeMay other = (XeMay) obj;
		if (maXe == null) {
			if (other.maXe != null)
				return false;
		} else if (!maXe.equals(other.maXe))
			return false;
		return true;
	}
}
