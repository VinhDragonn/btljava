package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connect.SQLbtl;
import btlmodel.phongbenhmodel;
import btlmodel.ttbenhnhanmodel;

public class DAOphongbenh implements DAOinter<phongbenhmodel>{
	public static  DAOphongbenh getconStance() {
		return new DAOphongbenh();
	}
	@Override
	public int insert(phongbenhmodel t) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "INSERT INTO phong_benh (id_benh_nhan, 	tenphongbenh , sogiuongbenh , "
					+ "loaigiuong , ngay_nhap_vien,	ngay_xuat_vien) "
		            + "VALUES (?, ?, ?, ?, ?,?)";
			java.sql.Date sqlDate1 = java.sql.Date.valueOf(t.getNgaynhap());
			java.sql.Date sqlDate2 = java.sql.Date.valueOf(t.getNgayxuat());
			PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getMa());
		st.setString(2, t.getPhong());	
		st.setString(3, t.getSogiuonng());	
		st.setString(4, t.getLoaigiuonng());	
		st.setDate(5, sqlDate1);
		st.setDate(6, sqlDate2);
		  
			int kq = st.executeUpdate();
			return kq;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(phongbenhmodel t,String a) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "update phong_benh  \n  set id_benh_nhan=?, tenphongbenh =?, sogiuongbenh =?,loaigiuong=? "
					+ " ,ngay_nhap_vien=?,ngay_xuat_vien=?"
					+ " where id_benh_nhan=? ";
			PreparedStatement st = con.prepareStatement(sql);
			java.sql.Date sqlDate1 = java.sql.Date.valueOf(t.getNgaynhap());
			java.sql.Date sqlDate2 = java.sql.Date.valueOf(t.getNgayxuat());
			st.setString(1, t.getMa());
		st.setString(2, t.getPhong());	
		st.setString(3, t.getSogiuonng());	
		st.setString(4, t.getLoaigiuonng());	
		st.setDate(5, sqlDate1);
		st.setDate(6, sqlDate2);
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
			String sql = "delete from  phong_benh \n where id_benh_nhan= ? ";
		PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a);
			int kq = st.executeUpdate();
			System.out.println("Ban da xoa " + sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<phongbenhmodel> selectall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public phongbenhmodel selectByid(phongbenhmodel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<phongbenhmodel> selectbyCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public phongbenhmodel tim(String a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<phongbenhmodel> tim2(String a) {
		ArrayList<phongbenhmodel> list = new ArrayList<phongbenhmodel>();
		Connection con = SQLbtl.getConnection();
	    try {
	    	String sql = "SELECT * FROM phong_benh WHERE id_benh_nhan LIKE ? or tenphongbenh LIKE ? OR sogiuongbenh LIKE ? "
	    			+ "OR CONVERT(varchar, ngay_nhap_vien, 103) LIKE ? OR loaigiuong LIKE ? "
	    			+ "OR CONVERT(varchar, ngay_xuat_vien, 103) LIKE ?";
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
	            String ten = rs.getString("tenphongbenh");
	           
	            String sg = rs.getString("sogiuongbenh");
	            String lg = rs.getString("loaigiuong");
	           
	            Date ngaynhap = rs.getDate("ngay_nhap_vien");
	            Date ngayxuat = rs.getDate("ngay_xuat_vien");
	          
	           phongbenhmodel K = new phongbenhmodel(mabn, ten, sg, lg, ngaynhap.toLocalDate(), ngayxuat.toLocalDate());
	            list.add(K);
	        }
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	    }
	    return list;
	}
	

}
