package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connect.SQLbtl;
import btlmodel.ttbenhnhanmodel;
import btlmodel.xetnghiemmodel;

public class DAOXN implements DAOinter<xetnghiemmodel>{
	public static  DAOXN getconStance() {
		return new DAOXN();
	}
	@Override
	public int insert(xetnghiemmodel t) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "INSERT INTO xet_nghiem (id_benh_nhan, id_ten_loai_xet_nghiem, ket_qua, ngay_xet_nghiem,BHYT,tien) "
		            + "VALUES (?, ?, ?, ?, ?,?)";

			java.sql.Date sqlDate = java.sql.Date.valueOf(t.getNgay());
			PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getMa1());
		st.setString(2, t.getLxn());	
		st.setString(3, t.getKq());	
		st.setDate(4, sqlDate);
			
		st.setBoolean(5, t.isBhyt());	
		st.setString(6, t.getTien());	
		  
			int kq = st.executeUpdate();
			return kq;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(xetnghiemmodel t,String a) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "update xet_nghiem  set id_benh_nhan=?,id_ten_loai_xet_nghiem=?, ket_qua=?,ngay_xet_nghiem=? "
					+ " , BHYT=?,tien=?"
					+ " where id_benh_nhan=? ";
			PreparedStatement st = con.prepareStatement(sql);
			java.sql.Date sqlDate = java.sql.Date.valueOf(t.getNgay());
			
			st.setString(1, t.getMa1());	
			st.setString(2, t.getLxn());	
			st.setString(3, t.getKq());	
			st.setDate(4, sqlDate);
			
			st.setBoolean(5, t.isBhyt());
			st.setString(6, t.getTien());
			st.setString(7, a);	
			int kq = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String a) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "delete from xet_nghiem \n where id_benh_nhan= ? ";
		PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a);
			int kq = st.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<xetnghiemmodel> selectall() {
		return null;
	}

	@Override
	public xetnghiemmodel selectByid(xetnghiemmodel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<xetnghiemmodel> selectbyCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public xetnghiemmodel tim(String a) {
		 Connection con = SQLbtl.getConnection();
		    xetnghiemmodel z = null;
		    try {
		        String sql = "select * from xet_nghiem where id_benh_nhan = ?";
		        PreparedStatement st = con.prepareStatement(sql);
		        st.setString(1, a);
		        ResultSet rs = st.executeQuery();
		        if (rs.next()) {
		            String mabn = rs.getString("id_benh_nhan");
		            String lxn = rs.getString("id_ten_loai_xet_nghiem");
		            String ketqua = rs.getString("ket_qua");
		          
		          
		            Date ngay = rs.getDate("ngay_xet_nghiem");
		            Boolean bhyt = rs.getBoolean("BHYT");
		            String tien = rs.getString("tien");
		            
		            
		            z = new xetnghiemmodel(mabn, lxn, ketqua, ngay.toLocalDate(), bhyt,tien);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return z;
	}
	
	@Override
	public ArrayList<xetnghiemmodel> tim2(String a) {
		 Connection con = SQLbtl.getConnection();
		   ArrayList<xetnghiemmodel>   list = new ArrayList<xetnghiemmodel>();
		   try {
		    	String sql = "SELECT * FROM xet_nghiem WHERE id_benh_nhan LIKE ? or id_ten_loai_xet_nghiem LIKE ? "
		    			+ "OR ket_qua LIKE ? "
		    			+ "	   OR CONVERT(varchar, ngay_xet_nghiem, 103) LIKE ? OR BHYT LIKE ? OR tien LIKE ? ";
		        PreparedStatement st = con.prepareStatement(sql);
		        
		        st.setString(1, "%" + a + "%");
		        st.setString(2, "%" + a + "%");
		        st.setString(3, "%" + a + "%");
		        st.setString(4, "%" + a + "%");
		        st.setString(5, "%" + a + "%");
		        st.setString(6, "%" + a + "%");
		        
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		        	  String mabn = rs.getString("id_benh_nhan");
			            String lxn = rs.getString("id_ten_loai_xet_nghiem");
			            String ketqua = rs.getString("ket_qua");			          			          
			            Date ngay = rs.getDate("ngay_xet_nghiem");
			            Boolean bhyt = rs.getBoolean("BHYT");
			            String tien = rs.getString("tien");			            		            
			           xetnghiemmodel z = new xetnghiemmodel(mabn, lxn, ketqua, ngay.toLocalDate(), bhyt,tien);
		            list.add(z);
		        }
		    } catch (SQLException e) {
		        
		        e.printStackTrace();
		    }
		    return list;
	}
	

}
