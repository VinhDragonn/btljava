package btlmodel;

import java.time.LocalDate;

public class TongHopModel {
private String ma;
private String hoten;
private boolean gt;
private String dc;
private LocalDate ngaysinh;
private String sdt;
private boolean diung;
private String cannang;
private String chieucao;
private String huyetap;
private String nhommau;
private String loaiXN;
private String ketqua;
private String tien;
public TongHopModel(String ma, String hoten, boolean gt, String dc, LocalDate ngaysinh, String sdt, boolean diung,
		String cannang, String chieucao, String huyetap, String nhommau, String loaiXN, String ketqua, String tien) {
	super();
	this.ma = ma;
	this.hoten = hoten;
	this.gt = gt;
	this.dc = dc;
	this.ngaysinh = ngaysinh;
	this.sdt = sdt;
	this.diung = diung;
	this.cannang = cannang;
	this.chieucao = chieucao;
	this.huyetap = huyetap;
	this.nhommau = nhommau;
	this.loaiXN = loaiXN;
	this.ketqua = ketqua;
	this.tien = tien;
}
public String getMa() {
	return ma;
}
public void setMa(String ma) {
	this.ma = ma;
}
public String getHoten() {
	return hoten;
}
public void setHoten(String hoten) {
	this.hoten = hoten;
}
public boolean isGt() {
	return gt;
}
public void setGt(boolean gt) {
	this.gt = gt;
}
public String getDc() {
	return dc;
}
public void setDc(String dc) {
	this.dc = dc;
}
public LocalDate getNgaysinh() {
	return ngaysinh;
}
public void setNgaysinh(LocalDate ngaysinh) {
	this.ngaysinh = ngaysinh;
}
public String getSdt() {
	return sdt;
}
public void setSdt(String sdt) {
	this.sdt = sdt;
}
public boolean isDiung() {
	return diung;
}
public void setDiung(boolean diung) {
	this.diung = diung;
}
public String getCannang() {
	return cannang;
}
public void setCannang(String cannang) {
	this.cannang = cannang;
}
public String getChieucao() {
	return chieucao;
}
public void setChieucao(String chieucao) {
	this.chieucao = chieucao;
}
public String getHuyetap() {
	return huyetap;
}
public void setHuyetap(String huyetap) {
	this.huyetap = huyetap;
}
public String getNhommau() {
	return nhommau;
}
public void setNhommau(String nhommau) {
	this.nhommau = nhommau;
}
public String getLoaiXN() {
	return loaiXN;
}
public void setLoaiXN(String loaiXN) {
	this.loaiXN = loaiXN;
}
public String getKetqua() {
	return ketqua;
}
public void setKetqua(String ketqua) {
	this.ketqua = ketqua;
}
public String getTien() {
	return tien;
}
public void setTien(String tien) {
	this.tien = tien;
}

}
