package btlmodel;
import java.time.LocalDate;
public class xetnghiemmodel {
	private 
	String ma1;
	private String lxn;
	private String kq; 
	private LocalDate ngay;
	private String ghichu1;
	private String tien;
	private boolean bhyt;
	public xetnghiemmodel(String ma1, String lxn, String kq, LocalDate ngay,boolean bhyt,String tien) {
		this.ma1 = ma1;
		this.lxn = lxn;
		this.kq = kq;
		this.ngay = ngay;
		
		this.tien= tien;
		this.bhyt = bhyt;
	}
	public boolean isBhyt() {
		return bhyt;
	}
	public void setBhyt(boolean bhyt) {
		this.bhyt = bhyt;
	}
	public String getTien() {
		return tien;
	}
	public void setTien(String tien) {
		this.tien = tien;
	}
	public xetnghiemmodel(String maXN) {
		ma1=maXN;
	}
	public String getMa1() {
		return ma1;
	}
	public void setMa1(String ma1) {
		this.ma1 = ma1;
	}
	public String getLxn() {
		return lxn;
	}
	public void setLxn(String lxn) {
		this.lxn = lxn;
	}
	public String getKq() {
		return kq;
	}
	public void setKq(String kq) {
		this.kq = kq;
	}
	public LocalDate getNgay() {
		return ngay;
	}
	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}
	public String getGhichu1() {
		return ghichu1;
	}
	public void setGhichu1(String ghichu1) {
		this.ghichu1 = ghichu1;
	}
}
