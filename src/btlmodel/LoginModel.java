package btlmodel;

public class LoginModel {
private String tk;
private String mk;
public LoginModel(String tk, String mk) {
	super();
	this.tk = tk;
	this.mk = mk;
}
public String getTk() {
	return tk;
}
public void setTk(String tk) {
	this.tk = tk;
}
public String getMk() {
	return mk;
}
public void setMk(String mk) {
	this.mk = mk;
}

}
