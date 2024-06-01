package btlmodel;

import java.time.LocalDate;
import java.util.Date;

public class phongbenhmodel {
private String ma;
private String phong;
private String loaigiuonng;
private String sogiuonng;
private LocalDate ngaynhap;
private LocalDate ngayxuat;
public phongbenhmodel(String ma, String phong, String loaigiuonng, String sogiuonng, LocalDate ngaynhap, LocalDate ngayxuat) {
	super();
	this.ma = ma;
	this.phong = phong;
	this.loaigiuonng = loaigiuonng;
	this.sogiuonng = sogiuonng;
	this.ngaynhap = ngaynhap;
	this.ngayxuat = ngayxuat;
}
public String getMa() {
	return ma;
}
public void setMa(String ma) {
	this.ma = ma;
}
public String getPhong() {
	return phong;
}
public void setPhong(String phong) {
	this.phong = phong;
}
public String getLoaigiuonng() {
	return loaigiuonng;
}
public void setLoaigiuonng(String loaigiuonng) {
	this.loaigiuonng = loaigiuonng;
}
public String getSogiuonng() {
	return sogiuonng;
}
public void setSogiuonng(String sogiuonng) {
	this.sogiuonng = sogiuonng;
}
public LocalDate getNgaynhap() {
	return ngaynhap;
}
public void setNgaynhap(LocalDate ngaynhap) {
	this.ngaynhap = ngaynhap;
}
public LocalDate getNgayxuat() {
	return ngayxuat;
}
public void setNgayxuat(LocalDate ngayxuat) {
	this.ngayxuat = ngayxuat;
}

}
