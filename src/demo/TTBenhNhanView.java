/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.beans.Statement;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Connect.SQLbtl;
import Connect.ketnoi;
import DAO.DAOttBN;
import Demo.Form_ThongTinBenhNhan;
import Demo.Form_Xetnghiem;
import btlmodel.ttbenhnhanmodel;
import controller.TTBenhNhancontroller;
import controller.btlcontroller;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class TTBenhNhanView extends javax.swing.JPanel {
    static DefaultTableModel defaultTableModel;
    public TTBenhNhanView() {
    	TTBenhNhancontroller action = new TTBenhNhancontroller(this);

        initComponents();
        String[] columns = {"ID","Họ tên","Giới tính","Địa chỉ","Số CMND","Điện thoại","Ngày sinh","Dị ứng"};
      
        defaultTableModel = new DefaultTableModel(columns, 0);
        table.setModel(defaultTableModel);
        setLayout(null);
        loadtable();
        add(jLabel5);
        add(jLabel11);
        add(jLabel7);
        add(jLabel4);
        add(nam);
        add(nu);
        add(jLabel3);
        add(jLabel8);
        add(jLabel2);
        add(jLabel9);
        add(codiung);
        add(khongdiung);
        add(ngaysinh);
        add(sdt);
        add(diachi);
        add(hotenbenhnhan);
        add(cmnd);
        add(id);
        add(jScrollPane1);
        add(lblNewLabel);
        them = new javax.swing.JButton();
        them.setFont(new Font("Tahoma", Font.BOLD, 13));
        them.setText("Thêm");
        them.setBounds(25, 570, 150, 40);
        add(them);
                
                xoa= new JButton();
                xoa.setFont(new Font("Tahoma", Font.BOLD, 13));
                xoa.setText("Xóa");
                xoa.setBounds(500, 516, 150, 40);
                add(xoa);
                        capnhat = new javax.swing.JButton();
                        capnhat.setFont(new Font("Tahoma", Font.BOLD, 13));
                        capnhat.setText("Cập nhật");
                        capnhat.setBounds(200, 570, 150, 40);
                        add(capnhat);
                                
                                textField = new JTextField();
                                textField.addKeyListener(new KeyAdapter() {
                                	@Override
                                	public void keyReleased(KeyEvent e) {
                                		timkiem( );
                                	}
                                });
                                textField.setBounds(690, 537, 210, 20);
                                add(textField);
                                textField.setColumns(10);
                                
                                JLabel lblNewLabel_1 = new JLabel("Nhập thông tin vào ô bên dưới để tìm");
                                lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
                                lblNewLabel_1.setForeground(new Color(0, 0, 0));
                                lblNewLabel_1.setBounds(690, 510, 230, 20);
                                add(lblNewLabel_1);
                                
//                                capnhat.addActionListener(new java.awt.event.ActionListener() {
//                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
//                                      sua();
//                                    }			
//                                });
//                xoa.addActionListener(new ActionListener() {
//                	public void actionPerformed(ActionEvent e) {
//                		xoa();
//                	}
//                });
//                them.addActionListener( new java.awt.event.ActionListener() {
//                    public void actionPerformed(java.awt.event.ActionEvent evt) {
//                        them();
//                    }
//                });
                them.addActionListener(action);
                xoa.addActionListener(action);
                capnhat.addActionListener(action);
        lamlaiActionPerformed();
        loadtable();
       
    }
    protected void timkiem() {   
		list.clear();
		String a =  textField.getText();	
		list=DAOttBN.getconStance().tim2( a);
                LoadARRtoTable();           		
	}
	private void LoadARRtoTable() {
		 DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		 model_table.setRowCount(0);
		 for (ttbenhnhanmodel ttbenhnhanmodel : list) {
			 model_table.addRow(new Object[]{ttbenhnhanmodel.getId() + "",ttbenhnhanmodel.getHoten() + "", 
					 ttbenhnhanmodel.isGioitinh()? "Nam" : "Nữ",ttbenhnhanmodel.getDiachi()+"",ttbenhnhanmodel.getSocmnd(),
							 ttbenhnhanmodel.getSdt(), 
							 ttbenhnhanmodel.getNgaysinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
							 ttbenhnhanmodel.isDiung()? "Có" : "Không"});
		}		
	}
	
	@SuppressWarnings("unchecked")
	
    public  void loadtable() {
    	defaultTableModel.setRowCount(0);
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        con = SQLbtl.getConnection();
        try {
            cs = con.prepareCall("{call GetAllTTBNN}");
            rs = cs.executeQuery();
            while (rs.next()) {                
            	 model_table.addRow( new Object[]   {rs.getString("id_benh_nhan"),rs.getString("ho_ten"),
                		rs.getBoolean("gioi_tinh") ? " Nam ": " Nữ",rs.getString("dia_chi"),
                				rs.getString("so_cmnd"),rs.getString("so_dien_thoai"),
  rs.getDate("ngay_sinh").getDate()+"/"+(rs.getDate("ngay_sinh").getMonth()+1)+"/"+(rs.getDate("ngay_sinh").getYear()+1900),
  rs.getBoolean("di_ung_thuoc") ? "Có" : " Không"
                						}); 
              //  defaultTableModel.addRow(dt);
            }
        } catch (SQLException ex) {
           Logger.getLogger(ttbenhnhanmodel.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}


	private void initComponents() {
		list = new ArrayList<ttbenhnhanmodel>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        diachi = new javax.swing.JTextField();
        diachi.setBounds(200, 280, 180, 30);
        ngaysinh = new javax.swing.JTextField();
        ngaysinh.setBounds(200, 400, 180, 30);
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setBounds(25, 220, 140, 35);
        nam = new javax.swing.JRadioButton();
        nam.setBounds(139, 467, 53, 21);
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setBounds(25, 460, 93, 35);
        nu = new javax.swing.JRadioButton();
        nu.setBounds(203, 467, 53, 21);
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(25, 280, 140, 35);
        cmnd = new javax.swing.JTextField();
        cmnd.setBounds(200, 160, 180, 30);
        jLabel7 = new javax.swing.JLabel();
        jLabel7.setBounds(25, 160, 155, 35);
        sdt = new javax.swing.JTextField();
        sdt.setBounds(200, 340, 180, 30);
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setBounds(25, 340, 110, 35);
        codiung = new javax.swing.JRadioButton();
        codiung.setBounds(139, 520, 46, 21);
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setBounds(25, 510, 90, 35);
        khongdiung = new javax.swing.JRadioButton();
        khongdiung.setBounds(203, 520, 64, 21);
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setBounds(25, 400, 140, 35);
        hotenbenhnhan = new javax.swing.JTextField();
        hotenbenhnhan.setBounds(200, 220, 180, 30);
        jLabel11 = new javax.swing.JLabel();
        jLabel11.setBounds(25, 100, 110, 37);
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBounds(500, 100, 800, 390);
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Họ tên bệnh nhân:");

        nam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(nam);
        nam.setText("Nam");

        jLabel4.setText("Giới tính:");

        nu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(nu);
        nu.setText("Nữ");
        jLabel5.setText("Địa chỉ:");  
        jLabel7.setText("Số chứng minh nhân dân:");        
      jLabel8.setText("Số điện thoại:");

        codiung.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(codiung);
        codiung.setText("Có");

        jLabel9.setText("Dị ứng thuốc:");

        khongdiung.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(khongdiung);
        khongdiung.setText("Không");

        jLabel2.setText("Ngày sinh:");

        jLabel11.setText("Mã bệnh nhân:");

        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "H\u1ECD t\u00EAn", "Gi\u1EDBi t\u00EDnh", "\u0110\u1ECBa ch\u1EC9", "N\u01A1i sinh", "S\u1ED1 CMND", "\u0110i\u1EC7n tho\u1EA1i", "Ng\u00E0y sinh", "D\u1ECB \u1EE9ng", "Tr\u1EA1ng th\u00E1i"
        	}
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        id = new JTextField();
        id.setBounds(203, 104, 180, 30);
        
        lblNewLabel = new JLabel("Thông tin bệnh nhân");
        lblNewLabel.setBounds(460, 20, 280, 50);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
	public void capnhat() {
		  id.setEditable(true);
		    ttbenhnhanmodel a = layThongTinCoBan();
		    if (a == null) {
		        return;
		    }
		    lamlaiActionPerformed();   
		    String z = table.getValueAt(row, 0).toString();
		    DAOttBN.getconStance().update(a,z);
		    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		    model_table.setValueAt(a.getHoten(), row, 1);
		    model_table.setValueAt(a.isGioitinh() ? "Nam" : "Nữ", row, 2);
		    model_table.setValueAt(a.getDiachi(), row, 3);
		    model_table.setValueAt(a.getSocmnd(), row, 4);
		    model_table.setValueAt(a.getSdt(), row, 5);
		    model_table.setValueAt(a.getNgaysinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), row, 6);
		    model_table.setValueAt(a.isDiung() ? "Có" : "Không", row, 7);
		
	}
	public void thucHienThemThiSinh() {
		 ttbenhnhanmodel a = layThongTinCoBan();
	    	//list.add(a);
	 	    if (a == null) {
	 	        return;
	 	    }
	 	  
	 	   DAOttBN.getconStance().insert(a);
	 	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	  model_table.addRow(new Object[]{a.getId() + "",a.getHoten() + "", a.isGioitinh()? "Nam" : "Nữ", a.getDiachi(),a.getSocmnd(),a.getSdt(), 
			  a.getNgaysinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), a.isDiung()? "Có" : "Không"});
	 	   
	  lamlaiActionPerformed();
		
	}
	public void sua() {
	    id.setEditable(true);
	    ttbenhnhanmodel a = layThongTinCoBan();
	    if (a == null) {
	        return;
	    }
	    lamlaiActionPerformed();   
	    String z = table.getValueAt(row, 0).toString();
	    DAOttBN.getconStance().update(a,z);
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    model_table.setValueAt(a.getHoten(), row, 1);
	    model_table.setValueAt(a.isGioitinh() ? "Nam" : "Nữ", row, 2);
	    model_table.setValueAt(a.getDiachi(), row, 3);
	    model_table.setValueAt(a.getSocmnd(), row, 4);
	    model_table.setValueAt(a.getSdt(), row, 5);
	    model_table.setValueAt(a.getNgaysinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), row, 6);
	    model_table.setValueAt(a.isDiung() ? "Có" : "Không", row, 7);
	}

	private void lamlaiActionPerformed() {//GEN-FIRST:event_lamlaiActionPerformed  
        id.setText("");
        diachi.setText("");
        ngaysinh.setText("");  
        cmnd.setText("");
        sdt.setText("");
        hotenbenhnhan.setText("");
        buttonGroup2.clearSelection();
        buttonGroup1.clearSelection();      
    }
    public boolean kiemTraTenHopLe(String ten) {
	    return !ten.matches(".*\\d.*");
	}
    private ttbenhnhanmodel layThongTinCoBan () {
         String Hoten = hotenbenhnhan.getText().trim();
         String Id =  id.getText().trim();
        String Diachi = diachi.getText().trim();
        String Socmnd = cmnd.getText().trim();
        String Sdt = sdt.getText().trim();
        boolean diung =  codiung.isSelected();
        boolean KOdiung =  khongdiung.isSelected();
	    boolean gioiTinh = nam.isSelected();
	    boolean gioiTinh1 = nu.isSelected();
	    if(Hoten.length()==0 || Id.length()==0|| Socmnd.length()==0|| Sdt.length()==0 || Diachi.length()==0 ) {
	    	JOptionPane.showMessageDialog(null,"Có dữ liệu bạn chưa nhập");
	    	return null;
	    }
	    Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(Socmnd);
        Matcher matcher1 = pattern.matcher(Sdt);
        try {
        	 int num1 = Integer.parseInt(Socmnd);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "CMND ko được chứa chữ");
			cmnd.setText("");
			return null;
		}
        try {
       	 int num1 = Integer.parseInt(Sdt);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "SDT ko được chứa chữ");
			sdt.setText("");
			return null;
		}
        
	    String dateStr = ngaysinh.getText().trim();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate ngay = null;
	    try {
	        ngay = LocalDate.parse(dateStr, formatter);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Ngày sinh không theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        ngaysinh.requestFocus();
	        ngaysinh.selectAll();
	        ngaysinh.setText("");
	        return null;
	    }
       
	    if (!kiemTraTenHopLe(Hoten)) {
	        JOptionPane.showMessageDialog(this, "Tên không được chứa số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        hotenbenhnhan.requestFocus();
	        hotenbenhnhan.selectAll();
	        hotenbenhnhan.setText("");
	        return null;
	    }

	    return new ttbenhnhanmodel(Id,Hoten, Diachi, Socmnd, Sdt, diung, gioiTinh,ngay);
          
        } 
    private void them() {
    	 ttbenhnhanmodel a = layThongTinCoBan();
    	//list.add(a);
 	    if (a == null) {
 	        return;
 	    }
 	  
 	   DAOttBN.getconStance().insert(a);
 	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
  model_table.addRow(new Object[]{a.getId() + "",a.getHoten() + "", a.isGioitinh()? "Nam" : "Nữ", a.getDiachi(),a.getSocmnd(),a.getSdt(), 
		  a.getNgaysinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), a.isDiung()? "Có" : "Không"});
 	   
  lamlaiActionPerformed();
	}
    
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
    	id.setEditable(false);
         row = table.getSelectedRow();
        id.setText( table.getValueAt(row, 0)+"");
        hotenbenhnhan.setText(table.getValueAt(row, 1)+"");
      String gioitinh = (table.getValueAt(row, 2)+"").trim();
        diachi.setText((String) table.getValueAt(row, 3));
        cmnd.setText( table.getValueAt(row, 4)+"");
        sdt.setText(table.getValueAt(row, 5)+"");
        if (gioitinh.equals("Nam")) {
            nam.setSelected(true);
        } else {
            nu.setSelected(true);
        }
        ngaysinh.setText((String) table.getValueAt(row, 6));                 
        String diung = (table.getValueAt(row, 7)+"").trim();
        if (diung.equals("Có")) {
            codiung.setSelected(true);
        } else {
            khongdiung.setSelected(true);
        }
        loadtable() ;
    }
    public void xoatable() {
    	
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	int rowCount = model.getRowCount();
    	for (int i = rowCount - 1; i >= 0; i--) {
    	    model.removeRow(i);
    	}
    }
    
	public void xoa() {
		id.setEditable(true);
			DefaultTableModel model = (DefaultTableModel) table.getModel(); 
			
			String a = table.getValueAt(row, 0).toString();
			if (row != -1) { 
			    model.removeRow(row); 			   
			    	 DAOttBN.getconStance().delete(a);	
			    	 lamlaiActionPerformed();
			}				
	}

    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JTextField cmnd;
    private javax.swing.JRadioButton codiung;
    private javax.swing.JTextField ngaysinh;
    private javax.swing.JTextField diachi;
    private javax.swing.JButton capnhat;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton khongdiung;
    private javax.swing.JRadioButton nam;
    private javax.swing.JTextField hotenbenhnhan;
    private javax.swing.JRadioButton nu;
    private javax.swing.JTextField sdt;
    private javax.swing.JTable table;
    private javax.swing.JButton them;
    private JButton xoa;
    private  int row;
    private ArrayList<ttbenhnhanmodel> list;
    private JTextField id;
    private JLabel lblNewLabel;
    private JTextField textField;
	
	
}
