package btlmodel;

import java.time.LocalDate;

public class benhAnmodel {
private static String maBN;
private static String maXN;
private String chieucao;
private String cannang;
private String huyetap;
private LocalDate ngaylap;
private LocalDate ngayhen;
private String nhommau;
private String tieusu;
private String maBA;
public benhAnmodel(String maBA,String maBN,  String chieucao, String cannang, String huyetap, String nhommau, String tieusu) {
	super();
	this.maBA= maBA;
	this.maBN = maBN;
	
	this.chieucao = chieucao;
	this.cannang = cannang;
	this.huyetap = huyetap;
	
	this.nhommau = nhommau;
	this.tieusu = tieusu;
}
public benhAnmodel(String mabn2, String maxn2) {
	maBN=mabn2;
	maXN=maxn2;
}
public String getMaBA() {
	return maBA;
}
public void setMaBA(String maBA) {
	this.maBA = maBA;
}
public static String getMaBN() {
	return maBN;
}
public void setMaBN(String maBN) {
	this.maBN = maBN;
}
public static String getMaXN() {
	return maXN;
}
public void setMaXN(String maXN) {
	this.maXN = maXN;
}
public String getChieucao() {
	return chieucao;
}
public void setChieucao(String chieucao) {
	this.chieucao = chieucao;
}
public String getCannang() {
	return cannang;
}
public void setCannang(String cannang) {
	this.cannang = cannang;
}
public String getHuyetap() {
	return huyetap;
}
public void setHuyetap(String huyetap) {
	this.huyetap = huyetap;
}
public LocalDate getNgaylap() {
	return ngaylap;
}
public void setNgaylap(LocalDate ngaylap) {
	this.ngaylap = ngaylap;
}
public LocalDate getNgayhen() {
	return ngayhen;
}
public void setNgayhen(LocalDate ngayhen) {
	this.ngayhen = ngayhen;
}
public String getNhommau() {
	return nhommau;
}
public void setNhommau(String nhommau) {
	this.nhommau = nhommau;
}
public String getTieusu() {
	return tieusu;
}
public void setTieusu(String tieusu) {
	this.tieusu = tieusu;
}

}
