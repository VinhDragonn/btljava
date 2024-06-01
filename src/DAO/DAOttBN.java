package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Connect.SQLbtl;
import LLLL.THU;
import btlmodel.benhAnmodel;
import btlmodel.ttbenhnhanmodel;
import dao.DAOBTL;

public class DAOttBN implements DAOinter<ttbenhnhanmodel>{
	public static  DAOttBN getconStance() {
		return new DAOttBN();
	}
	@Override
	public int insert(ttbenhnhanmodel t) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "INSERT INTO thong_tin_bnN( id_benh_nhan, ho_ten  , gioi_tinh,dia_chi , so_cmnd"
					+ ",so_dien_thoai,ngay_sinh,di_ung_thuoc) "
					+ "VALUES ( ?,?,?,?,?,?,?,? ) ";
			java.sql.Date sqlDate = java.sql.Date.valueOf(t.getNgaysinh());
			PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getId());
		st.setString(2, t.getHoten());	
		st.setBoolean(3, t.isGioitinh());	
		st.setString(4, t.getDiachi());	
	
		st.setString(5, t.getSocmnd());
		st.setString(6, t.getSdt());
		st.setDate(7, sqlDate);
		st.setBoolean(8, t.isDiung());	
		  
			int kq = st.executeUpdate();
			return kq;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int update(ttbenhnhanmodel t,String a) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "update thong_tin_bnN \n  set id_benh_nhan=?, ho_ten =?, gioi_tinh=?,dia_chi=? "
					+ " ,so_cmnd=?,so_dien_thoai=?,ngay_sinh=?,di_ung_thuoc=?"
					+ " where id_benh_nhan=? ";
			PreparedStatement st = con.prepareStatement(sql);
			java.sql.Date sqlDate = java.sql.Date.valueOf(t.getNgaysinh());
			st.setString(1, t.getId());	
			st.setString(2, t.getHoten());	
			st.setBoolean(3, t.isGioitinh());	
			st.setString(4, t.getDiachi());	
			st.setString(5, t.getSocmnd());
			st.setString(6, t.getSdt());
			st.setDate(7, sqlDate);
			st.setBoolean(8, t.isDiung());	
			st.setString(9, a);
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
			DAOBenhAn.getconStance().delete(a);
			DAOXN.getconStance().delete(a);
			String sql = "delete from thong_tin_bnN \n where id_benh_nhan= ? ";
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
	public ArrayList<ttbenhnhanmodel> selectall() {
		ArrayList<ttbenhnhanmodel> list = new ArrayList<ttbenhnhanmodel>();
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "select*  from   thong_tin_bnN \n  ";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			
			String mabn = rs.getString("id_benh_nhan");
			String hoten = rs.getString("ho_ten");
			boolean gt = rs.getBoolean("gioi_tinh");
			String dc = rs.getString("dia_chi");
			int cmnd = rs.getInt("so_cmnd");
			String sdt = rs.getString("so_dien_thoai");
			Date ngay = rs.getDate("ngay_sinh");
			boolean diung = rs.getBoolean("di_ung_thuoc");
			ttbenhnhanmodel K = new ttbenhnhanmodel(mabn, hoten, hoten, dc, sdt, diung, gt, ngay.toLocalDate());
			list.add(K);
		}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ttbenhnhanmodel selectByid(ttbenhnhanmodel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ttbenhnhanmodel> selectbyCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ttbenhnhanmodel tim(String a) {
	    Connection con = SQLbtl.getConnection();
	    ttbenhnhanmodel z = null;
	    try {
	        String sql = "select * from thong_tin_bnN where id_benh_nhan = ?";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, a);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            String mabn = rs.getString("id_benh_nhan");
	            String cmnd = rs.getString("so_cmnd");
	            String hoten = rs.getString("ho_ten");
	            String diachi = rs.getString("dia_chi");
	            String sdt = rs.getString("so_dien_thoai");
	            Date ngay = rs.getDate("ngay_sinh");
	            boolean diung = rs.getBoolean("di_ung_thuoc");
	            boolean gioitinh = rs.getBoolean("gioi_tinh");
	            
	            
	            z = new ttbenhnhanmodel(mabn, hoten, diachi, cmnd, sdt, diung, gioitinh, ngay.toLocalDate());
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
	public ArrayList<ttbenhnhanmodel> tim2(String a) {
	    ArrayList<ttbenhnhanmodel> list = new ArrayList<ttbenhnhanmodel>();
	    Connection con = SQLbtl.getConnection();
	    try {
	    	String sql = "SELECT * FROM thong_tin_bnN WHERE id_benh_nhan LIKE ? or ho_ten LIKE ? OR dia_chi LIKE ? "
	    			+ "OR CONVERT(varchar, ngay_sinh, 103) LIKE ? OR so_dien_thoai LIKE ?"
	    			+ " OR so_cmnd LIKE ? ";
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
	            String hoten = rs.getString("ho_ten");
	            boolean gt = rs.getBoolean("gioi_tinh");
	            String dc = rs.getString("dia_chi");
	            String cmnd = rs.getString("so_cmnd");
	            String sdt = rs.getString("so_dien_thoai");
	            Date ngay = rs.getDate("ngay_sinh");
	            boolean diung = rs.getBoolean("di_ung_thuoc");
	            ttbenhnhanmodel K = new ttbenhnhanmodel(mabn, hoten, dc, cmnd, sdt, diung, gt, ngay.toLocalDate());
	            list.add(K);
	        }
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	    }
	    return list;
	}


	

}
