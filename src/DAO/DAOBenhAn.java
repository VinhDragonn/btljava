package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connect.SQLbtl;
import MODEL.KHACHHANG;
import btlmodel.benhAnmodel;
import btlmodel.ttbenhnhanmodel;
import btlmodel.xetnghiemmodel;

public class DAOBenhAn implements DAOinter<benhAnmodel>{
	public static  DAOBenhAn getconStance() {
		return new DAOBenhAn();
	}
	@Override
	public int insert(benhAnmodel t) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "INSERT INTO BENH_AN (id_benh_an  , chieu_cao  , can_nang , "
					+ "huyet_ap , tieu_su,nhom_mau,id_benh_nhan) "
		            + "VALUES (?, ?, ?, ?, ?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getMaBA());
		st.setString(2, t.getChieucao());
		st.setString(3, t.getCannang());
		st.setString(4, t.getHuyetap());	
		st.setString(5, t.getTieusu());		
		
		st.setString(6, t.getNhommau());	
		st.setString(7, t.getMaBN());	
		
			int kq = st.executeUpdate();
			return kq;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(benhAnmodel t,String a) {
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "update BENH_AN  \n  set chieu_cao =?, can_nang =?,huyet_ap=? "
					+ " ,tieu_su=?,nhom_mau=?"
				
					+ " where id_benh_an =? ";
			PreparedStatement st = con.prepareStatement(sql);
			
		
		st.setString(1, t.getChieucao());
		st.setString(2, t.getCannang());
		st.setString(3, t.getHuyetap());	
		st.setString(4, t.getTieusu());		
		st.setString(5, t.getNhommau());	
			
	
		st.setString(6, a);
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
			String sql = "delete from  BENH_AN  \n where id_benh_an= ? ";
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
	public ArrayList<benhAnmodel> selectall() {
		ArrayList<benhAnmodel> list = new ArrayList<benhAnmodel>();
		Connection con = SQLbtl.getConnection();
		try {
			String sql = "select*  from  BENH_AN  \n  ";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			
			String mabn = rs.getString("id_benh_nhan");
			String maxn = rs.getString("id_xet_nghiem");
			benhAnmodel K = new benhAnmodel(mabn, maxn);
			list.add(K);
		}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public benhAnmodel selectByid(benhAnmodel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<benhAnmodel> selectbyCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public benhAnmodel tim(String a) {
		Connection con = SQLbtl.getConnection();
	    benhAnmodel z = null;
	    try {
	        String sql = "select * from   BENH_AN where id_benh_an = ?";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, a);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            String ma = rs.getString("id_benh_an");
	            String mabn = rs.getString("id_benh_nhan");
	            String chieucao = rs.getString("chieu_cao");
	            String cannang = rs.getString("can_nang");
	            String huyetap= rs.getString("huyet_ap");
	            String nhommau = rs.getString("nhom_mau");
	            String tieusu = rs.getString("tieu_su");
	           	          	                   
	            z = new benhAnmodel(ma, mabn, chieucao, cannang, huyetap, nhommau, tieusu);
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
	public ArrayList<benhAnmodel> tim2(String a) {
		Connection con = SQLbtl.getConnection();
		ArrayList<benhAnmodel> list = new ArrayList<benhAnmodel>();
		 try {
		    	String sql = "SELECT * FROM BENH_AN WHERE id_benh_an LIKE ? or id_benh_nhan LIKE ? "
		    			+ "OR chieu_cao LIKE ? "
		    			+ "	  OR can_nang LIKE ? OR huyet_ap LIKE ? "
		    			+ "or nhom_mau LIKE ? or tieu_su LIKE ? ";
		        PreparedStatement st = con.prepareStatement(sql);
		      
		        st.setString(1, "%" + a + "%");
		        st.setString(2, "%" + a + "%");
		        st.setString(3, "%" + a + "%");
		        st.setString(4, "%" + a + "%");
		        st.setString(5, "%" + a + "%");
		        st.setString(6, "%" + a + "%");
		        st.setString(7, "%" + a + "%");
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		        
		       
		        String ma = rs.getString("id_benh_an");
	            String mabn = rs.getString("id_benh_nhan");
	            String chieucao = rs.getString("chieu_cao");
	            String cannang = rs.getString("can_nang");
	            String huyetap= rs.getString("huyet_ap");
	            String nhommau = rs.getString("nhom_mau");
	            String tieusu = rs.getString("tieu_su");
		          	                   
	            benhAnmodel   z = new benhAnmodel(ma, mabn, chieucao, cannang, huyetap, nhommau, tieusu);
	            list.add(z);
		        }
		           
		        
		    } catch (SQLException e) {
		        
		        e.printStackTrace();
		    }
		    return list;
	} 
	
}
