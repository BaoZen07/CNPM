package entity;

import java.util.Objects;

public class BaoHanh {
	private String MaBH;
	private String MaXe;
	private String Tenxe;
	private String ThongtinBH;
	
	public BaoHanh() {
	}
	
	public BaoHanh(String maBH) {
		this.MaBH = maBH;
	}
	public BaoHanh(String maBH, String maXe, String tenxe, String thongtinBH) {
		super();
		MaBH = maBH;
		MaXe = maXe;
		Tenxe = tenxe;
		ThongtinBH = thongtinBH;
	}
	public String getMaBH() {
		return MaBH;
	}
	public void setMaBH(String maBH) {
		MaBH = maBH;
	}
	public String getMaXe() {
		return MaXe;
	}
	public void setMaXe(String maXe) {
		MaXe = maXe;
	}
	public String getTenxe() {
		return Tenxe;
	}
	public void setTenxe(String tenxe) {
		Tenxe = tenxe;
	}
	public String getThongtinBH() {
		return ThongtinBH;
	}
	public void setThongtinBH(String thongtinBH) {
		ThongtinBH = thongtinBH;
	}
	@Override
	public int hashCode() {
		return Objects.hash(MaBH, MaXe, Tenxe, ThongtinBH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaoHanh other = (BaoHanh) obj;
		return Objects.equals(MaBH, other.MaBH) && Objects.equals(MaXe, other.MaXe)
				&& Objects.equals(Tenxe, other.Tenxe) && Objects.equals(ThongtinBH, other.ThongtinBH);
	}
	@Override
	public String toString() {
		return "BaoHanh [MaBH=" + MaBH + ", MaXe=" + MaXe + ", Tenxe=" + Tenxe + ", ThongtinBH=" + ThongtinBH + "]";
	}
	
	
	
	
	
}
