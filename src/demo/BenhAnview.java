package demo;

import java.awt.print.PrinterException;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Connect.SQLbtl;
import Connect.ketnoi;
import DAO.DAOXN;
import DAO.DAOBenhAn;
import DAO.DAOttBN;
import Demo.Form_ThongTinBenhNhan;
import btlmodel.benhAnmodel;
import btlmodel.phongbenhmodel;
import btlmodel.ttbenhnhanmodel;
import btlmodel.xetnghiemmodel;
import controller.BenhAncontroller;
import controller.TTBenhNhancontroller;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class BenhAnview extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	 DefaultTableModel defaultTableModel;
	 private ArrayList<benhAnmodel> list;
	    private static int dem=0;
	    private javax.swing.JTextArea area;
	    private javax.swing.JTextField cannang;
	    private javax.swing.JTextField chieucao;
	    private javax.swing.JTextField huyetap;
	    private javax.swing.JButton them;
	    private javax.swing.JButton capnhat;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel16;
	    private javax.swing.JLabel jLabel17;
	    private javax.swing.JLabel jLabel18;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JLabel jLabel8;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextField nhommau;
	    private javax.swing.JTable table;
	    private JTextField mabenhan;
	    private JTextField tieusu;
	    private JLabel lblNewLabel;
	    private JLabel lblMBnhn;
	    private JLabel lblTiuS;
	    private JButton xoa;
	    private JLabel lblNewLabel_1;
	    private JTextField textField;
	    private JTextField themma1;
	    public BenhAnview() {
	    	
	        initComponents();
	        String[] columns = {"Mã bệnh án","Mã bệnh nhân","Chiều cao","Cân nặng","Huyết áp","Nhóm máu","Tiểu sử bệnh"};
	        defaultTableModel = new DefaultTableModel(columns, 0);
	        table.setModel(defaultTableModel);
	        setLayout(null);
	        refresh();
	        add(jLabel1);
	        add(lblMBnhn);
	        add(jLabel5);
	        add(jLabel4);
	        add(jLabel6);
	        add(jLabel8);
	        add(lblTiuS);
	        add(cannang);
	        add(tieusu);
	        add(chieucao);
	        add(mabenhan);
	        add(huyetap);
	        add(nhommau);
	        add(jLabel17);
	        add(jLabel16);
	        add(jLabel18);
	        add(them);
	        add(xoa);
	        add(capnhat);
	        add(jScrollPane1);
	        add(lblNewLabel);
	        list = new ArrayList<benhAnmodel>();
	        lblNewLabel_1 = new JLabel("Nhập thông tin vào ô bên dưới để tìm");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	        lblNewLabel_1.setBounds(782, 510, 233, 13);
	        add(lblNewLabel_1);
	        
	        textField = new JTextField();
	        textField.addKeyListener(new KeyAdapter() {
	        	@Override
	        	public void keyReleased(KeyEvent e) {
	        		timkiem();
	        	}
	        });
	        textField.setBounds(780, 528, 210, 19);
	        add(textField);
	        textField.setColumns(10);
	        
	        themma1 = new JTextField();
	        themma1.setText("");
	        themma1.setBounds(200, 160, 180, 30);
	        add(themma1);
	        loadtable();
	  
	    }
	   
		protected void timkiem() {
	    	list.clear();
	    	String a = textField.getText();
	    	list = DAOBenhAn.getconStance().tim2(a);
	    			
		loadArr();
			
		}
		private void loadArr() {
			 DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			 model_table.setRowCount(0);
			 for (benhAnmodel a : list) {
				  model_table.addRow(new Object[]{
				    		 a.getMaBA(),
				    		a.getMaBN() + "",  a.getChieucao()+"",a.getCannang(),
				    		a.getHuyetap() , a.getNhommau(),a.getTieusu()});
			}
			
		}
		private void loadtable() {
	    	defaultTableModel.setRowCount(0);
	        Connection con;
	        CallableStatement cs;
	        ResultSet rs;
	        con = SQLbtl.getConnection();
	        try {
	            cs = con.prepareCall("{call GetAllBenhAn()}");
	            rs = cs.executeQuery();
	            while (rs.next()) {              
	                Object[] dt = {rs.getString("id_benh_an"),rs.getString("id_benh_nhan"),
	                		rs.getString("chieu_cao"),rs.getString("can_nang"),
	                		rs.getString("huyet_ap"),

rs.getString("nhom_mau"),rs.getString("tieu_su")}; 
	                defaultTableModel.addRow(dt);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(BenhAnview.class.getName()).log(Level.SEVERE, null, ex);
	            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
			
		}
		
	   
	    private void initComponents() {
	    	BenhAncontroller action = new BenhAncontroller(this);
	    	 area = new javax.swing.JTextArea();
	    	 themma1 = new JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel1.setBounds(25, 160, 104, 38);
	        jLabel4 = new javax.swing.JLabel();
	        jLabel4.setBounds(25, 220, 169, 37);
	        jLabel5 = new javax.swing.JLabel();
	        jLabel5.setBounds(25, 280, 72, 37);
	        jLabel6 = new javax.swing.JLabel();
	        jLabel6.setBounds(25, 340, 126, 37);
	        jLabel8 = new javax.swing.JLabel();
	        jLabel8.setBounds(25, 400, 169, 37);
	        chieucao = new javax.swing.JTextField();
	        chieucao.setBounds(200, 220, 180, 30);
	        cannang = new javax.swing.JTextField();
	        cannang.setBounds(200, 280, 180, 30);
	        huyetap = new javax.swing.JTextField();
	        huyetap.setBounds(200, 340, 180, 30);
	        nhommau = new javax.swing.JTextField();
	        nhommau.setBounds(200, 400, 180, 30);
	        jLabel16 = new javax.swing.JLabel();
	        jLabel16.setBounds(390, 232, 30, 13);
	        jLabel17 = new javax.swing.JLabel();
	        jLabel17.setBounds(390, 292, 45, 13);
	        jLabel18 = new javax.swing.JLabel();
	        jLabel18.setBounds(390, 352, 72, 13);
	        them = new javax.swing.JButton();
	        them.setFont(new Font("Tahoma", Font.BOLD, 13));
	        them.setText("Thêm");
	        them.setBounds(10, 526, 150, 40);
	        capnhat = new javax.swing.JButton();
	        capnhat.setFont(new Font("Tahoma", Font.BOLD, 13));
	        capnhat.setText("Cập nhật");
	        capnhat.setBounds(200, 526, 150, 40);

	        setBackground(new java.awt.Color(255, 255, 255));

	        jLabel1.setText("Mã bệnh nhân:");

	        jLabel4.setText("Chiều cao:");

	        jLabel5.setText("Cân nặng:");

	        jLabel6.setText("Huyết áp:");

	        jLabel8.setText("Nhóm máu:");

	        jLabel16.setText("cm");

	        jLabel17.setText("kg");

	        jLabel18.setText("mmHg");
//	        them.addActionListener(new java.awt.event.ActionListener() {
//	            public void actionPerformed(java.awt.event.ActionEvent evt) {
//	                them();
//	            }
//	        });
	        them.addActionListener(action);
//	        capnhat.addActionListener(new java.awt.event.ActionListener() {
//	            public void actionPerformed(java.awt.event.ActionEvent evt) {
//	               capnhat();
//	            }
//	        });
	        capnhat.addActionListener(action);
	        lblMBnhn = new JLabel();
	        lblMBnhn.setBounds(25, 100, 120, 38);
	        lblMBnhn.setText("Mã bệnh án:");
	        
	        mabenhan = new JTextField();
	        mabenhan.setBounds(200, 105, 180, 30);
	        
	        xoa = new JButton();
	        xoa.setFont(new Font("Tahoma", Font.BOLD, 13));
	        xoa.setText("Xóa");
	        xoa.setBounds(590, 510, 150, 40);
//	        xoa.addActionListener(new ActionListener() {
//	        	public void actionPerformed(ActionEvent e) {
//	        		xoa();
//	        	}
//	        });
	        xoa.addActionListener(action);
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jScrollPane1.setBounds(590, 100, 700, 390);
	        table = new javax.swing.JTable();
	        
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
	        	                tableMouseClicked(evt);
	        	            }
	        	        });
	        	        jScrollPane1.setViewportView(table);
	        
	        lblTiuS = new JLabel();
	        lblTiuS.setBounds(25, 460, 123, 37);
	        lblTiuS.setText("Tiểu sử:");
	        
	        tieusu = new JTextField();
	        tieusu.setBounds(200, 460, 180, 30);
	        
	        lblNewLabel = new JLabel("Thể trạng");
	        lblNewLabel.setBounds(460, 20, 280, 50);
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    }
		public void xoa() {
			themma1.setEnabled(true);
			mabenhan.setEnabled(true);
	    	DefaultTableModel model = (DefaultTableModel) table.getModel(); 
			int selectedRowIndex = table.getSelectedRow(); 
			String a = table.getValueAt(selectedRowIndex, 0).toString();
			if (selectedRowIndex != -1) { 
				DAOBenhAn.getconStance().delete(a);
			    model.removeRow(selectedRowIndex); 
			}	
			refresh();
		}
		private void xoatable() {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
	    	int rowCount = model.getRowCount();
	    	for (int i = rowCount - 1; i >= 0; i--) {
	    	    model.removeRow(i);
	    	}
			
		}
		public void capnhat() {
			 themma1.setEnabled(true);
			    mabenhan.setEnabled(true);
			 benhAnmodel a = layttcoban();
			    if (a == null) {
			        return;
			    }
			    refresh();
			    int i_row = table.getSelectedRow();
			    String z = table.getValueAt(i_row, 0).toString();
			    DAOBenhAn.getconStance().update(a,z);
			    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			    model_table.setValueAt(a.getMaBA(), i_row, 0);
			    model_table.setValueAt(a.getMaBN(), i_row, 1);
			    model_table.setValueAt(a.getChieucao(), i_row, 2);
			    model_table.setValueAt(a.getCannang(), i_row, 3);
			    model_table.setValueAt(a.getHuyetap(), i_row, 4);  
			    model_table.setValueAt(a.getNhommau(), i_row, 5);
			    model_table.setValueAt(a.getTieusu(), i_row, 6);	 
			   
		}
		
		private benhAnmodel layttcoban () {
			String Mabenhan = mabenhan.getText().trim();
			String Mabenhan1 = themma1.getText();
	    //	String Mabenhnhan = mabenhnhan.getText().trim();	             
	        String Chieucao = chieucao.getText().trim();       
	        String Cannang = cannang.getText().trim();       
	        String Huyetap = huyetap.getText().trim();       	        
	        String Nhommau = nhommau.getText().trim();
	        String Tieusu = tieusu.getText().trim();
if(Mabenhan.length()==0 ||Chieucao.length()==0||Cannang.length()==0||Huyetap.length()==0||Nhommau.length()==0||Tieusu.length()==0) {
	JOptionPane.showMessageDialog(null, "Có dữ liệu bạn chưa nhập");
	return null;
}
try {
	 int num1 = Integer.parseInt(Chieucao);
} catch (Exception e) {
	JOptionPane.showMessageDialog(null, "Chiều cao ko được chứa chữ");
	chieucao.setText("");
	return null;
}
try {
	 int num1 = Integer.parseInt(Cannang);
} catch (Exception e) {
	JOptionPane.showMessageDialog(null, "Cân nặng ko được chứa chữ");
	cannang.setText("");
	return null;
}
try {
	 int num1 = Integer.parseInt(Huyetap);
} catch (Exception e) {
	JOptionPane.showMessageDialog(null, "Huyết áp ko được chứa chữ");
	huyetap.setText("");
	return null;
}
 
 benhAnmodel a = new benhAnmodel(Mabenhan, Mabenhan1, Chieucao, Cannang, Huyetap,Nhommau,Tieusu);
	  return a;
	        }
		
	    protected void them() {
	    	benhAnmodel a = layttcoban();
    	    if (a == null) {
    	        return;
    	    }
    	    DAOBenhAn.getconStance().insert(a);
    	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
     model_table.addRow(new Object[]{
    		 a.getMaBA(),
    		a.getMaBN() + "",  a.getChieucao()+"",a.getCannang(),
    		a.getHuyetap() , a.getNhommau(),a.getTieusu()});
     refresh();
			
		}
	    private void refresh() {
	    	themma1.setText("");
	    	mabenhan.setText("");
	        chieucao.setText("");
	        cannang.setText("");
	        huyetap.setText("");	       
	        nhommau.setText("");
	       tieusu.setText("");
	    }

	    private void tableMouseClicked(java.awt.event.MouseEvent evt) {
	    	themma1.setEnabled(false);
	    	mabenhan.setEnabled(false);
	            int row = table.getSelectedRow();	         	         
	            mabenhan.setText(table.getValueAt(row, 0)+"");
	            themma1.setText(table.getValueAt(row, 1)+"");
	         //   mabenhnhan.setText(table.getValueAt(row, 1)+"");	           
	            chieucao.setText(table.getValueAt(row, 2)+"");
	            cannang.setText(table.getValueAt(row, 3)+"");
	            huyetap.setText(table.getValueAt(row, 4)+"");	                      	            
	            nhommau.setText(table.getValueAt(row, 5)+"");
	           tieusu.setText(table.getValueAt(row, 6)+"");       
	    }
		public void thucHienThemThiSinh() {
			benhAnmodel a = layttcoban();
    	    if (a == null) {
    	        return;
    	    }
    	    DAOBenhAn.getconStance().insert(a);
    	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
     model_table.addRow(new Object[]{
    		 a.getMaBA(),
    		a.getMaBN() + "",  a.getChieucao()+"",a.getCannang(),
    		a.getHuyetap() , a.getNhommau(),a.getTieusu()});
     refresh();
			
			
		}
}
