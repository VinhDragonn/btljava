package demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connect.SQLbtl;

import DAO.DAOlogin;
import btlmodel.LoginModel;
import btlmodel.ttbenhnhanmodel;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class tk extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	 DefaultTableModel defaultTableModel;
	public tk(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setAlwaysOnTop(true);
        initComponents();
        String[] columns = {"Tên tài khoản","Mật khẩu"};
        defaultTableModel = new DefaultTableModel(columns, 0);
        table.setModel(defaultTableModel);
        loadtable();
	}
	 private void loadtable() {
		 defaultTableModel.setRowCount(0);
         Connection con;
         CallableStatement cs;
         ResultSet rs;
         con = SQLbtl.getConnection();
         try {
             cs = con.prepareCall("{call GetAllTK}");
             rs = cs.executeQuery();
             while (rs.next()) {                
                 Object[] dt = {rs.getString("tk"),rs.getString("pass")}; 
                 defaultTableModel.addRow(dt);
             }
         } catch (SQLException ex) {
            Logger.getLogger(ttbenhnhanmodel.class.getName()).log(Level.SEVERE, null, ex);
         }
		
	}
	private void initComponents() {

	        jScrollPane1 = new javax.swing.JScrollPane();
	        table = new javax.swing.JTable();
	        them = new javax.swing.JButton();
	        xoa = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        doimk = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setTitle("Quản lý tài khoản bệnh viện\n\n");

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
	        jScrollPane1.setViewportView(table);

	        them.setText("Thêm ADMIN");
	        them.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                them();
	            }
	        });

	        xoa.setText("Xóa Tài Khoản");
	        xoa.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	               xoa();
	            }
	        });

	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
	        jLabel1.setText("Quản lý tài khoản");

	        doimk.setText("Đổi mật khẩu");
	        doimk.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	               doimk();
	            }
	        });

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 597, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(layout.createSequentialGroup()
	        					.addComponent(them)
	        					.addGap(31)
	        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        						.addGroup(layout.createSequentialGroup()
	        							.addComponent(xoa)
	        							.addGap(18)
	        							.addComponent(doimk, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
	        						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))))
	        			.addGap(0, 0, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
	        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(them)
	        				.addComponent(xoa)
	        				.addComponent(doimk))
	        			.addGap(18)
	        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        getContentPane().setLayout(layout);

	        pack();
	    }
	 protected void doimk() {
		int row = table.getSelectedRow();
		String tk = table.getValueAt(row, 0).toString();
		String mk = table.getValueAt(row, 1).toString();
	String z=	JOptionPane.showInputDialog("Nhập mật khẩu muốn đổi");
		LoginModel a = new LoginModel(tk, mk);
		DAOlogin.getconStance().update(a, z);
		table.setValueAt(z, row, 1);
		
		
	}
	protected void xoa() {
		int row = table.getSelectedRow();
        if (row >= 0 && row < table.getRowCount()) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn xóa?");
            if (result == JOptionPane.YES_OPTION) {
                Connection con;
                CallableStatement cstmt = null;
                ResultSet rs = null;

                con = SQLbtl.getConnection();
                try {
                    cstmt = con.prepareCall("{call deleteTK(?)}");
                    cstmt.setString(1, table.getValueAt(row, 0) + "");
                    int i = cstmt.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Đã xóa thành công");
                        loadtable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi khi xóa");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(tk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
		
	}
	protected void them() {
		dispose(); 
        Form_adminView a= new Form_adminView();
         a.setVisible(true);
         
	}
	public static void main(String args[]) {
	        /* Set the Nimbus look and feel */
	        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	         */
	        try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(tk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(tk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(tk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(tk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        //</editor-fold>

	        /* Create and display the dialog */
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                tk dialog = new tk(new javax.swing.JFrame(), true);
	                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
	                    @Override
	                    public void windowClosing(java.awt.event.WindowEvent e) {
	                        System.exit(0);
	                    }
	                });
	                dialog.setVisible(true);
	            }
	        });
	    }

	    // Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JButton them;
	    private javax.swing.JButton doimk;
	    private javax.swing.JButton xoa;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable table;
	    private Form_adminView b;
}
