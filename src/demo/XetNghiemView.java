package demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Connect.SQLbtl;
import DAO.DAOXN;
import DAO.DAOttBN;
import btlmodel.ttbenhnhanmodel;
import btlmodel.xetnghiemmodel;
import controller.TTBenhNhancontroller;
import controller.XetNghiemcontroller;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class XetNghiemView extends JPanel {
	public XetNghiemView() {
        initComponents();
        String[] columns = {"Mã bệnh nhân", "Loại xét nghiệm", "Kết quả", "Ngày xét nghiệm", "BHYT", "Giá tiền"};
        defaultTableModel = new DefaultTableModel(columns, 0);
        table.setModel(defaultTableModel);
        setLayout(null);
        add(jLabel12);
        add(jLabel3);
        add(jLabel14);
        add(lblNewLabel);
        add(jLabel13);
        add(co);
        add(ko);
        add(ketqua);
        add(loaixetnghiem);
        add(ngayxetnghiem);
        add(jScrollPane2);
        add(them);
        add(xoa);
        add(capnhat);
        add(lblNewLabel_1);

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tim2();
            }
        });

        JLabel lblNewLabel_2 = new JLabel("Nhập thông tin vào ô bên dưới để tìm");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_2.setBounds(690, 505, 230, 20);
        add(lblNewLabel_2);
        textField.setBounds(690, 530, 210, 20);
        add(textField);
        textField.setColumns(10);
        
        themma1 = new JTextField();
        themma1.setText("");
        themma1.setBounds(200, 103, 180, 30);
        add(themma1);

        lamlai();
        loadtable();
      
    }

    protected void tim2() {
        list.clear();
        String a = textField.getText();
        list = DAOXN.getconStance().tim2(a);
        loadArr();
    }

    private void loadArr() {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        model_table.setRowCount(0);
        for (xetnghiemmodel xetnghiemmodel : list) {
            model_table.addRow(new Object[]{
                xetnghiemmodel.getMa1(), xetnghiemmodel.getLxn(), xetnghiemmodel.getKq(),
                xetnghiemmodel.getNgay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                xetnghiemmodel.isBhyt() ? "Có" : "Ko", xetnghiemmodel.getTien()
            });
        }
    }

    @SuppressWarnings("unchecked")
    public void loadtable() {
        defaultTableModel.setRowCount(0);
        Connection con = SQLbtl.getConnection();
        CallableStatement cs;
        ResultSet rs;
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        
        try {
            cs = con.prepareCall("{call GetAllxetnghiem}");
            rs = cs.executeQuery();
            while (rs.next()) {
            	model_table.addRow(new Object[]  {
                    rs.getString("id_benh_nhan"),
                    rs.getString("id_ten_loai_xet_nghiem"),
                    rs.getString("ket_qua"),
                    rs.getDate("ngay_xet_nghiem").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    rs.getBoolean("BHYT") ? "Có" : "Không",
                    rs.getString("tien")
                });
               // defaultTableModel.addRow(dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XetNghiemView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    private void initComponents() {
    	XetNghiemcontroller action = new XetNghiemcontroller(this);
        list = new ArrayList<>();
        jLabel12 = new JLabel("Loại xét nghiệm:");
        jLabel12.setBounds(25, 160, 140, 40);
        jLabel13 = new JLabel("Kết quả:");
        jLabel13.setBounds(25, 220, 91, 40);
        jLabel14 = new JLabel("Ngày xét nghiệm:");
        jLabel14.setBounds(25, 280, 120, 40);
        ngayxetnghiem = new JTextField();
        ngayxetnghiem.setBounds(200, 280, 180, 30);
        loaixetnghiem = new JComboBox<>();
        loaixetnghiem.setBounds(200, 160, 123, 44);
        them = new JButton("Thêm");
        them.setFont(new Font("Tahoma", Font.BOLD, 13));
        them.setBounds(25, 410, 150, 40);
        capnhat = new JButton("Cập nhật");
        capnhat.setFont(new Font("Tahoma", Font.BOLD, 13));
        capnhat.setBounds(214, 410, 150, 40);
        jLabel3 = new JLabel("Mã bệnh nhân:");
        jLabel3.setBounds(25, 100, 113, 34);
        buttonGroup = new ButtonGroup();
        setBackground(new Color(255, 255, 255));

        loaixetnghiem.addItem("Xét nghiệm ADN");
        loaixetnghiem.addItem("Xét nghiệm nước tiểu");
        loaixetnghiem.addItem("Xét nghiệm máu");
        loaixetnghiem.addItem("Khám Xquang");
        loaixetnghiem.addItem("Khám nội soi");

//        them.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                them();
//            }
//        });
them.addActionListener(action);
//        capnhat.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                capnhat();
//            }
//        });
capnhat.addActionListener(action);
        xoa = new JButton("Xóa");
        xoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        xoa.setBounds(500, 510, 150, 40);
//        xoa.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                xoa();
//            }
//        });
xoa.addActionListener(action);
        ketqua = new JTextField();
        ketqua.setBounds(200, 220, 180, 30);

        lblNewLabel = new JLabel("Bảo hiểm y tế");
        lblNewLabel.setBounds(25, 340, 103, 13);

        co = new JRadioButton("Có");
        co.setBounds(172, 336, 47, 21);

        ko = new JRadioButton("Không");
        ko.setBounds(247, 336, 76, 21);

        buttonGroup.add(co);
        buttonGroup.add(ko);

        jScrollPane2 = new JScrollPane();
        jScrollPane2.setBounds(500, 100, 800, 390);
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableMouseClicked();
            }
        });

        table.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Mã bệnh nhân", "Loại xét nghiệm", "Kết quả", "Ngày xét nghiệm", "BHYT", "Giá tiền"}
        ));
        jScrollPane2.setViewportView(table);

        lblNewLabel_1 = new JLabel("Xét nghiệm");
        lblNewLabel_1.setBounds(460, 20, 280, 50);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void thucHienThemThiSinh() {
    	xetnghiemmodel a = laythongtincoban();
       	list.add(a);
    	    if (a == null) {
    	        return;
    	    }
    	   
    	    DAOXN.getconStance().insert(a);
    	    
    	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
     model_table.addRow(new Object[]{a.getMa1() + "",a.getLxn() + "",  a.getKq()+"",
    		  a.getNgay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),a.isBhyt()?"Có":"Không",a.getTien()+""});
    	   lamlai();
		
	}
    public void xoa() {
    	themma1.setEnabled(true);
    	lamlai();
    	DefaultTableModel model = (DefaultTableModel) table.getModel(); 
		int selectedRowIndex = table.getSelectedRow(); 
		String a = table.getValueAt(selectedRowIndex, 0).toString();
System.out.println(selectedRowIndex);
		if (selectedRowIndex != -1) { 
		    model.removeRow(selectedRowIndex); 
		    DAOXN.getconStance().delete(a);
		   
		}
		
	}
	public void capnhat() {
		  themma1.setEnabled(true);
     xetnghiemmodel a = laythongtincoban();
	    if (a == null) {
	        return;
	    }
	    lamlai();
	    int i_row = table.getSelectedRow();
	    String z = table.getValueAt(i_row, 0).toString();
	    DAOXN.getconStance().update(a,z);
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();  
	    model_table.setValueAt(a.getMa1(), i_row, 0);
	    model_table.setValueAt(a.getLxn(), i_row, 1);
	    model_table.setValueAt(a.getKq(), i_row, 2);
	    model_table.setValueAt(a.getNgay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), i_row, 3);
	   
	    model_table.setValueAt(a.isBhyt()?"Có":"Ko", i_row, 4);
	    model_table.setValueAt(a.getTien(), i_row, 5);
	    lamlai();
	  
	}
	
	private void lamlai() {
		themma1.setText("");
        loaixetnghiem.setSelectedIndex(0);
        ketqua.setText("");
        ngayxetnghiem.setText("");
        buttonGroup.clearSelection();
    }
	private xetnghiemmodel laythongtincoban(){
		// String ma1 = ma.getText().trim();
		 String ma =  themma1.getText();
				 
		 String lxn = loaixetnghiem.getSelectedItem().toString();
		 boolean bhyt = co.isSelected();
		 if(lxn.equals("Xét nghiệm ADN") && bhyt) {
			 tien = "4.000.000Đ";
		 }
		 else if(lxn.equals("Xét nghiệm ADN") && !bhyt) {
			 tien = "10.000.000Đ";
		 }
		 else if(lxn.equals("Xét nghiệm nước tiểu") && bhyt ) {
			 tien = "200.000Đ";
		 }
		 else if(lxn.equals("Xét nghiệm nước tiểu") && !bhyt ) {
			 tien = "1.000.000Đ";
		 }
		 else if(lxn.equals("Xét nghiệm máu") && bhyt) {
			 tien = "1.500.000Đ";
		 }
		 else if(lxn.equals("Xét nghiệm máu") && !bhyt) {
			 tien = "3.000.000Đ";
		 }
		 else if(lxn.equals("Khám Xquang") && bhyt ) {
			 tien = "150.000Đ";
		 }
		 else if(lxn.equals("Khám Xquang") && !bhyt ) {
			 tien = "500.000Đ";
		 }
		 else if(lxn.equals("Khám nội soi") && bhyt ) {
			 tien = "680.000Đ";
		 }
		 else if(lxn.equals("Khám nội soi") && !bhyt ) {
			 tien = "1.000.000Đ";
		 }
	        String kq=ketqua.getText().trim();
	       
	        if(ma.length()==0||lxn.length()==0||kq.length()==0) {
	        	JOptionPane.showMessageDialog(null, "Có dữ liệu bạn chưa nhập");
	        	return null;
	        }
	        String dateStr = ngayxetnghiem.getText().trim();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate ngay = null;
		    try {
		        ngay = LocalDate.parse(dateStr, formatter);
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(this, "Ngày sinh không theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        ngayxetnghiem.requestFocus();
		        ngayxetnghiem.selectAll();
		        ngayxetnghiem.setText("");
		        return null;
		    }
		   
		    return new xetnghiemmodel(ma,lxn,kq,ngay,bhyt,tien);
			
	}
	private void them() {
   	xetnghiemmodel a = laythongtincoban();
   	list.add(a);
	    if (a == null) {
	        return;
	    }
	   
	    DAOXN.getconStance().insert(a);
	    
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
 model_table.addRow(new Object[]{a.getMa1() + "",a.getLxn() + "",  a.getKq()+"",
		  a.getNgay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),a.isBhyt()?"Có":"Ko",a.getTien()+""});
	   lamlai();
 
	}
	private void tableMouseClicked() {
		themma1.setEnabled(false);
        int row = table.getSelectedRow();
     //   ma.setText(table.getValueAt(row, 0)+"");
        themma1.setText(table.getValueAt(row, 0)+"");
        this.loaixetnghiem.setSelectedItem(table.getValueAt(row, 1)+"");            
        ketqua.setText(table.getValueAt(row, 2)+"");       
        ngayxetnghiem.setText(table.getValueAt(row, 3)+"");
        String bhyt = (table.getValueAt(row, 4)+"").trim();
        if(bhyt.equals("Có")) {
        	co.setSelected(true);
        }
        else {
        	
        }
    }
    public void xoatable() {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	int rowCount = model.getRowCount();
    	for (int i = rowCount - 1; i >= 0; i--) {
    	    model.removeRow(i);
    	}
    }
    private static final long serialVersionUID = 1L;
	DefaultTableModel defaultTableModel;
   
    private static int dem=0;
    public String tien=null;
    JRadioButton co ;
    
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton them;
    private javax.swing.JButton capnhat;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox loaixetnghiem;
    private javax.swing.JTextField ngayxetnghiem;
    private javax.swing.JTable table;
    private List<xetnghiemmodel> list= new ArrayList();
    private JButton xoa;
    private JTextField ketqua;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel;
    private JRadioButton ko;
    private JTextField textField;
    private JTextField themma1;
}
