package dao;

public class DoanhThuObject {
	private String maNV;
	private String tenNV;
	private int soXeDaBan;
	private double doanhThu;
	
	
	public DoanhThuObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoanhThuObject(String maNV, String tenNV, int soXeDaBan, double doanhThu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soXeDaBan = soXeDaBan;
		this.doanhThu = doanhThu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public int getSoXeDaBan() {
		return soXeDaBan;
	}
	public void setSoXeDaBan(int soXeDaBan) {
		this.soXeDaBan = soXeDaBan;
	}
	public double getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}
	@Override
	public String toString() {
		return "DoanhThuObject [maNV=" + maNV + ", tenNV=" + tenNV + ", soXeDaBan=" + soXeDaBan + ", doanhThu="
				+ doanhThu + "]";
	}
	
	
}
