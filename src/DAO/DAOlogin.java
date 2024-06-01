package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connect.SQLbtl;
import LLLL.THU;
import MODEL.KHACHHANG;
import btlmodel.LoginModel;

public class DAOlogin implements DAOinter<LoginModel>{
	public static  DAOlogin getconStance() {
		return new DAOlogin();
	}
	@Override
	public int insert(LoginModel t) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "INSERT INTO Login (tk, pass) "
		            + "VALUES (?, ?)";
			
			PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getTk());
		st.setString(2, t.getMk());
		
			int kq = st.executeUpdate();
			return kq;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(LoginModel t, String a) {
		Connection con = SQLbtl.getConnection();
		String sql = " update  Login set pass=? where tk=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, t.getTk());
			int i = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delete(String a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<LoginModel> selectall() {
		Connection con =SQLbtl.getConnection();
		ArrayList<LoginModel> kq = new ArrayList<LoginModel>();
		try {
			String sql = " select * from Login ";
			PreparedStatement st = con.prepareStatement(sql);
			
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String tk = rs.getString("tk");
				String pass = rs.getString("pass");
				LoginModel K = new LoginModel(tk,pass);
				kq.add(K);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kq;
	}

	@Override
	public LoginModel selectByid(LoginModel t) {
		Connection con = SQLbtl.getConnection();
		LoginModel kq = null;
		try {
			String sql = " select * from Login  where tk=? and pass=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTk());
			st.setString(2, t.getMk());
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String tk= rs.getString("tk");
				String pass = rs.getString("pass");
				kq = new LoginModel(tk,pass);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kq;
	}

	@Override
	public ArrayList<LoginModel> selectbyCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LoginModel tim(String a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<LoginModel> tim() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<LoginModel> tim2(String a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
