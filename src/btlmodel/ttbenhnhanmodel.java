package btlmodel;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JTextField;

public class ttbenhnhanmodel {
	private String hoten;
	private String diachi;
	private String noisinh;
	private String socmnd;
	private String sdt;
	protected String id;
	private boolean diung;
	private boolean gioitinh;
	private LocalDate ngaysinh=null;
	
	public ttbenhnhanmodel(String id,String hoten, String diachi,  String socmnd, String sdt,
			 boolean diung, boolean gioiTinh, LocalDate sinh) {
		this.hoten = hoten;
		this.diachi = diachi;
		
		this.socmnd = socmnd;
		this.sdt=sdt;
		this.id = id;
		this.diung= diung;
		this.gioitinh = gioiTinh;
		this.ngaysinh = sinh;
		
	}


	public ttbenhnhanmodel(String mabn) {
		 id=mabn;
	}


	public String getHoten() {
		return hoten;
	}


	public void setHoten(String hoten) {
		this.hoten = hoten;
	}


	public String getDiachi() {
		return diachi;
	}


	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}


	public String getNoisinh() {
		return noisinh;
	}


	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}


	public String getSocmnd() {
		return socmnd;
	}


	public void setSocmnd(String socmnd) {
		this.socmnd = socmnd;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isDiung() {
		return diung;
	}


	public void setDiung(boolean diung) {
		this.diung = diung;
	}


	public boolean isGioitinh() {
		return gioitinh;
	}


	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}


	public LocalDate getNgaysinh() {
		return ngaysinh;
	}


	public void setNgaysinh(LocalDate ngaysinh) {
		this.ngaysinh = ngaysinh;
	}


	
	
}
