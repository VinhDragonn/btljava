package demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Connect.SQLbtl;
import Connect.ketnoi;
import DAO.DAOphongbenh;
import DAO.DAOttBN;
import Data.DataGiuongBenh;
import Data.DataLoaiGiuong;
import Data.DataTenPhongBenh;
import Demo.Form_PhongBenh;
import btlmodel.phongbenhmodel;
import btlmodel.ttbenhnhanmodel;
import btlmodel.xetnghiemmodel;
import controller.PhongBenhcontroller;
import controller.TTBenhNhancontroller;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class PhongBenhView extends JPanel {

	private static final long serialVersionUID = 1L;
	public static int dem =0;
	private javax.swing.JTable table;
	 private javax.swing.JButton them;
	    private javax.swing.JButton capnhat;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JComboBox loaigiuong;
	    private javax.swing.JTextField ngaynhapvien;
	    private javax.swing.JTextField ngayxuatvien;
	    private javax.swing.JComboBox phongbenh;
	    private javax.swing.JComboBox sogiuong;
	    private ArrayList<phongbenhmodel> list = new ArrayList<phongbenhmodel>();
	  DefaultTableModel defaultTableModel;
	  private JButton xoa;
	  private JLabel lblNewLabel;
	  private JLabel lblNewLabel_1;
	  private JTextField textField;
	  private JTextField themma1;
	    
	    
	public PhongBenhView() {
		 list = new ArrayList<phongbenhmodel>();
initComponents();
        String[] columns = {"Mã bệnh nhân","Phòng bệnh","Số giường","Loại giường","Ngày vào","Ngày ra"};
        defaultTableModel = new DefaultTableModel(columns, 0);
        table.setModel(defaultTableModel);  
        setLayout(null);
        add(jLabel3);
        add(jLabel2);
        add(jLabel4);
        add(jLabel5);
        add(jLabel6);
        add(jLabel1);
        add(phongbenh);
        add(loaigiuong);
        add(ngaynhapvien);
        add(sogiuong);
        add(them);
        add(xoa);
        add(capnhat);
        add(ngayxuatvien);
        add(lblNewLabel);
        add(jPanel1);
        
        lblNewLabel_1 = new JLabel("Nhập thông tin vào ô bên dưới để tìm");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(768, 510, 242, 13);
        add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		timkiem();
        	}
        });
        textField.setBounds(770, 533, 206, 19);
        add(textField);
        textField.setColumns(10);
        
        themma1 = new JTextField();
        themma1.setText("");
        themma1.setBounds(200, 103, 180, 30);
        add(themma1);
        loadtable();
        resetActionPerformed();
        
	}
	 
	protected void timkiem() {
		 String a = textField.getText();
		 list = DAOphongbenh.getconStance().tim2(a);
		loadArr();
		
	}
	private void loadArr() {
		 DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		 model_table.setRowCount(0);
		 for (phongbenhmodel a : list) {
			 model_table.addRow(new Object[]{a.getMa() + "",a.getPhong() + "",  a.getSogiuonng()+"",a.getLoaigiuonng(),
		    		  a.getNgaynhap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
		    		  a.getNgayxuat().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))});
		}
		 
		
	}
	private void loadtable() {
		 defaultTableModel.setRowCount(0);
	        Connection con;
	        CallableStatement cs;
	        ResultSet rs;
	        con = SQLbtl.getConnection();
	        try {
	            cs = con.prepareCall("{call GetAllphong_benh}");
	            rs = cs.executeQuery();
	           
	            while (rs.next()) {                
	                Object[] dt = {rs.getString("id_benh_nhan"),rs.getString("tenphongbenh"),
	                	rs.getString("sogiuongbenh"),rs.getString("loaigiuong"),
rs.getDate("ngay_nhap_vien").getDate()+"/"+(rs.getDate("ngay_nhap_vien").getMonth()+1)+"/"+(rs.getDate("ngay_nhap_vien").getYear()+1900),
rs.getDate("ngay_xuat_vien").getDate()+"/"+(rs.getDate("ngay_xuat_vien").getMonth()+1)+"/"+(rs.getDate("ngay_xuat_vien").getYear()+1900)}; 
	                defaultTableModel.addRow(dt);
	            }
	        } catch (SQLException ex) {
	        	Logger.getLogger(PhongBenhView.class.getName()).log(Level.SEVERE, null, ex);
	            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
		
	}
	private void initComponents() {
		PhongBenhcontroller action = new PhongBenhcontroller(this);
		themma1 = new  JTextField();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel2.setBounds(25, 160, 168, 33);
	        jLabel3 = new javax.swing.JLabel();
	        jLabel3.setBounds(25, 220, 168, 34);
	        jLabel4 = new javax.swing.JLabel();
	        jLabel4.setBounds(25, 400, 168, 34);
	        jLabel5 = new javax.swing.JLabel();
	        jLabel5.setBounds(25, 340, 168, 34);
	        phongbenh = new javax.swing.JComboBox();
	        phongbenh.setBounds(200, 160, 180, 30);
	        jLabel6 = new javax.swing.JLabel();
	        jLabel6.setBounds(25, 280, 168, 34);
	        sogiuong = new javax.swing.JComboBox();
	        sogiuong.setBounds(200, 220, 79, 36);
	        loaigiuong = new javax.swing.JComboBox();
	        loaigiuong.setBounds(200, 280, 180, 30);
	        ngaynhapvien = new javax.swing.JTextField();
	        ngaynhapvien.setBounds(200, 340, 180, 30);
	        ngayxuatvien = new javax.swing.JTextField();
	        ngayxuatvien.setBounds(200, 400, 180, 30);
	        jPanel1 = new javax.swing.JPanel();
	        jPanel1.setBounds(570, 100, 750, 390);
	        jScrollPane1 = new javax.swing.JScrollPane();
	        table = new javax.swing.JTable();
	        them = new javax.swing.JButton();
	        them.setText("Thêm");
	        them.setFont(new Font("Tahoma", Font.BOLD, 13));
	        them.setBounds(25, 461, 150, 40);
	        capnhat = new javax.swing.JButton();
	        capnhat.setText("Cập nhật");
	        capnhat.setFont(new Font("Tahoma", Font.BOLD, 13));
	        capnhat.setBounds(224, 461, 150, 40);
	        jLabel1 = new javax.swing.JLabel();
	        jLabel1.setBounds(25, 100, 139, 34);
	        setBackground(new java.awt.Color(255, 255, 255));
	        jLabel2.setText("Phòng bệnh:");
	        jLabel3.setText("Giường số:");
	        jLabel4.setText("Ngày xuất viện:");
	        jLabel5.setText("Ngày nhập viện:");
	        jLabel6.setText("Loại giường:");
	        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	        jPanel1.setLayout(null);

	        table.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null}
	            },
	            new String [] {
	                "Title 1", "Title 2", "Title 3", "Title 4"
	            }
	        ));
	        table.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                tableMouseClicked();
	            }
	        });
	        jScrollPane1.setViewportView(table);
	        phongbenh.addItem("Phòng vip1");
	        phongbenh.addItem("Phòng vip2");
	        phongbenh.addItem("Phòng thường");
	        sogiuong.addItem("1");
	        sogiuong.addItem("2");
	        sogiuong.addItem("3");
	        sogiuong.addItem("4");
	        sogiuong.addItem("5");
	        loaigiuong.addItem("Giường vip");
	        loaigiuong.addItem("Giường cơ bản");   
	        jPanel1.add(jScrollPane1);
	        jScrollPane1.setBounds(30, 30, 700, 350);
//	        them.addActionListener(new java.awt.event.ActionListener() {
//	            public void actionPerformed(java.awt.event.ActionEvent evt) {
//	               them();
//	            }
//	        });
	        them.addActionListener(action);
//	        capnhat.addActionListener(new java.awt.event.ActionListener() {
//	            public void actionPerformed(java.awt.event.ActionEvent evt) {
//	                capnhat();
//	            }
//	        });
capnhat.addActionListener(action);
	        jLabel1.setText("Mã bệnh nhân:");
	        
	        xoa = new JButton();
	        
	        xoa.setText("Xóa");
	        xoa.setFont(new Font("Tahoma", Font.BOLD, 13));
	        xoa.setBounds(570, 510, 150, 40);
//	        xoa.addActionListener(new ActionListener() {
//	        	public void actionPerformed(ActionEvent e) {
//	        		xoa();
//	        	}
//	        });
	        xoa.addActionListener(action);
	        lblNewLabel = new JLabel("Phòng bệnh");
	        lblNewLabel.setBounds(460, 20, 280, 50);
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    }
	public void xoa() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel(); 
		int selectedRowIndex = table.getSelectedRow(); 
		String a = table.getValueAt(selectedRowIndex, 0).toString();
		if (selectedRowIndex != -1) { 
			DAOphongbenh.getconStance().delete(a);
		    model.removeRow(selectedRowIndex); 
		    list.remove(selectedRowIndex);
		}	
	}
	private void xoatable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
    	int rowCount = model.getRowCount();
    	for (int i = rowCount - 1; i >= 0; i--) {
    	    model.removeRow(i);
    	}
	}
	public void capnhat() {
		themma1.setEditable(true);
		 phongbenhmodel a = layttcoban();
		    if (a == null) {
		        return;
		    }
		    resetActionPerformed();
		    int i_row = table.getSelectedRow();
		    String z  = table.getValueAt(i_row, 0).toString();
		    DAOphongbenh.getconStance().update(a,z);
		    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		    model_table.setValueAt(a.getMa(), i_row, 0);
		    model_table.setValueAt(a.getPhong(), i_row, 1);
		    model_table.setValueAt(a.getSogiuonng(), i_row, 2);
		    model_table.setValueAt(a.getLoaigiuonng(), i_row, 3);
		    model_table.setValueAt(a.getNgaynhap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), i_row, 4);
		    model_table.setValueAt(a.getNgayxuat().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), i_row, 5);	
		    themma1.setEnabled(true);
	}

	    private void resetActionPerformed() {
	    	themma1.setText("");
	        phongbenh.setSelectedIndex(0);
	        sogiuong.setSelectedIndex(0);
	        loaigiuong.setSelectedIndex(0);
	        ngaynhapvien.setText("");
	        ngayxuatvien.setText("");
	    }

	    private void tableMouseClicked() {
	        int row = table.getSelectedRow();
	       themma1.setEnabled(false);
	       themma1.setText(table.getValueAt(row, 0)+"");
	   //     mabenhnhan.setText(table.getValueAt(row, 0)+"");
	       sogiuong.setSelectedItem(table.getValueAt(row, 2).toString().trim());
	        phongbenh.setSelectedItem(table.getValueAt(row, 1).toString().trim());	        
	       
	        loaigiuong.setSelectedItem(table.getValueAt(row, 3).toString().trim());	        
	        ngaynhapvien.setText(table.getValueAt(row, 4)+"");
	        ngayxuatvien.setText(table.getValueAt(row, 5)+"");
	    }

	    private phongbenhmodel layttcoban () {
	     //    String Mabenhnhan = mabenhnhan.getText().trim();
	         String Mabenhnhan1 = themma1.getText();
	         String phongbenh1 = phongbenh.getSelectedItem().toString();
		       String sogiuong1 = sogiuong.getSelectedItem().toString();
		       String loaigiuong1 = loaigiuong.getSelectedItem().toString();
	         String ngaynhap = ngaynhapvien.getText().trim();
	         String ngayxuat = ngayxuatvien.getText().trim();
			    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    LocalDate ngay1 = null;
			    LocalDate ngay2 = null;
			    ArrayList<ttbenhnhanmodel> z =DAOttBN.getconStance().selectall();
//			    int dem=0;
//			    for (ttbenhnhanmodel ttbenhnhanmodel : z) {
//			    	if( ttbenhnhanmodel.getId().equals(Mabenhnhan)) {
//			    		dem=1;
//			    	}
//			    }
//			    if(dem==0) {
//			    	JOptionPane.showMessageDialog(null, "Bạn nhập sai ma benh nhan");
//			    	mabenhnhan.setText("");
//			    	return null;
//			    }		
			    try {
			        ngay1 = LocalDate.parse(ngaynhap, formatter1);			      
			    } catch (Exception e) {
			        JOptionPane.showMessageDialog(this, "Ngày sinh không theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        ngaynhapvien.requestFocus();
			        ngaynhapvien.selectAll();
			        ngaynhapvien.setText("");			       
			       return null;			       
			    }
			    try {			       
			        ngay2 = LocalDate.parse(ngayxuat, formatter2);			     
			    } catch (Exception e) {
			        JOptionPane.showMessageDialog(this, "Ngày không theo định dạng dd/MM/yyyy hoặc sai ngày tháng năm", "Lỗi", JOptionPane.ERROR_MESSAGE);			    
			        ngayxuatvien.requestFocus();
			        ngayxuatvien.selectAll();
			        ngayxuatvien.setText("");
			       return null;
			       
			    }
			   if(ngay1.getYear() > ngay2.getYear()) {
				   JOptionPane.showMessageDialog(this, "Năm nhập ko được lớn hơn năm xuất", "Lỗi", JOptionPane.ERROR_MESSAGE);
				   ngaynhapvien.requestFocus();
			        ngaynhapvien.selectAll();
			        ngaynhapvien.setText("");			        
				return null;
			   }
			   else if( ngay1.getYear()==ngay2.getYear() && (ngay1.getMonthValue() >= ngay2.getMonthValue() )) {
				   JOptionPane.showMessageDialog(this, "Tháng nhập ko được lớn hơn tháng xuất", "Lỗi", JOptionPane.ERROR_MESSAGE);
				   ngaynhapvien.requestFocus();
			        ngaynhapvien.selectAll();
			        ngaynhapvien.setText("");			        
			        return null;
			   }
			  if(phongbenh1.length()==0||sogiuong1.length()==0||loaigiuong1.length()==0) {
				  JOptionPane.showMessageDialog(null, "Có dữ liệu bạn chưa nhập");
				  return null;
			  }
	      
	       phongbenhmodel a = new phongbenhmodel(Mabenhnhan1, phongbenh1, loaigiuong1, sogiuong1, ngay1, ngay2);
	       return a;
	        }
	    private void them() {
	       	phongbenhmodel a = layttcoban();
	    	    if (a == null) {
	    	        return;
	    	    }
	    	   
	    	    list.add(a);
	    	    DAOphongbenh.getconStance().insert(a);
	    	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	     model_table.addRow(new Object[]{a.getMa() + "",a.getPhong() + "",  a.getSogiuonng()+"",a.getLoaigiuonng(),
	    		  a.getNgaynhap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
	    		  a.getNgayxuat().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))});
	     resetActionPerformed();
	     
	    	}
		public void thucHienThemThiSinh() {
			phongbenhmodel a = layttcoban();
    	    if (a == null) {
    	        return;
    	    }
    	   
    	    list.add(a);
    	    DAOphongbenh.getconStance().insert(a);
    	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
     model_table.addRow(new Object[]{a.getMa() + "",a.getPhong() + "",  a.getSogiuonng()+"",a.getLoaigiuonng(),
    		  a.getNgaynhap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    		  a.getNgayxuat().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))});
     resetActionPerformed();
			
		}
}
