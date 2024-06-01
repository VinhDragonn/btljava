package demo;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.AncestorListener;

import Demo.Form_Quan_Ly_TaiKhoan;

import javax.swing.event.AncestorEvent;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class Demo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private  JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo frame = new Demo();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Demo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\OneDrive\\Documents\\Downloads\\pngtree-hospital-icon-design-illustration-png-image_2171135 (1).jpg"));
		contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    XetNghiemView a = new XetNghiemView(); 
        BenhAnview c = new BenhAnview();
        PhongBenhView d  = new PhongBenhView();
        TTBenhNhanView b = new TTBenhNhanView(); 
        TongHopView e  = new TongHopView();
        tk f = new tk(null, rootPaneCheckingEnabled);
	    JTabbedPane tabbedPane = new JTabbedPane();
	   
    tabbedPane.addTab("Bệnh nhân",b);
     tabbedPane.addTab("Xét nghiệm",a);
	    tabbedPane.addTab("Thể trạng",c);
	    tabbedPane.addTab("Phòng bệnh",d);
	   tabbedPane.addTab("Tổng hợp hồ sơ",e);
	    tabbedPane.setBounds(0, 60, 1500, 1000);
	    contentPane.add(tabbedPane);		
		setTitle("Bệnh viện");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 0, 1378, 882);
	    
	    JPanel   jPanel2 = new JPanel();
	 jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chức năng y sĩ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24)));
	    panel = new JPanel();
	    panel.setBounds(0, 60, 1400, 1000);
	    contentPane.add(panel);
	    
	    JLabel lblNewLabel = new JLabel("Chức năng");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    lblNewLabel.setBounds(10, 28, 145, 32);
	    contentPane.add(lblNewLabel);
	    
	    JButton btnQunLTi = new JButton("Quản lý tài khoản");
	    btnQunLTi.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		tk objForm_QuanLy_TaiKhoan = new tk(null, rootPaneCheckingEnabled);
	            objForm_QuanLy_TaiKhoan.setVisible(true);
	    	}
	    });
	    btnQunLTi.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btnQunLTi.setBounds(1104, 10, 219, 32);
	    contentPane.add(btnQunLTi);
	   
	}
}