package entity;

import java.io.Serializable;
import java.util.Objects;

public class sanPham implements Serializable {
	private static final long serialVersionUID = 1L;
	private String maSP, tenSP, tinhTrang, nhaSX;
	private int ngaySX, soLuong;
	private double donGia;

	public sanPham(String maSP, String tenSP, String tinhTrang, String nhaSX, int ngaySX, int soLuong, double donGia) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.tinhTrang = tinhTrang;
		this.nhaSX = nhaSX;
		this.ngaySX = ngaySX;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		sanPham other = (sanPham) obj;
		return Objects.equals(maSP, other.maSP);
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getNhaSX() {
		return nhaSX;
	}

	public void setNhaSX(String nhaSX) {
		this.nhaSX = nhaSX;
	}

	public int getNgaySX() {
		return ngaySX;
	}

	public void setNgaySX(int ngaySX) {
		this.ngaySX = ngaySX;
	}

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

	@Override
	public String toString() {
		return "sanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", tinhTrang=" + tinhTrang + ", nhaSX=" + nhaSX
				+ ", ngaySX=" + ngaySX + ", soLuong=" + soLuong + ", donGia=" + donGia + "]";
	}

}
