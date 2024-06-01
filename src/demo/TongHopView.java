package demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Connect.SQLbtl;
import btlmodel.TongHopModel;
import btlmodel.benhAnmodel;

import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TongHopView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextArea area;
    private DefaultTableModel defaultTableModel;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTable tblAllBenhAn;
    private JButton in_1;
    private ArrayList<TongHopModel> list;
    private JTextField textField;

    public TongHopView() {
        list = new ArrayList<>();
        initComponents();
        String[] columns = {"Mã B.Án", "Họ Tên", "Gtính", "Địa chỉ", "Ngsinh", "ĐiệnThoại", "Dị ứng", "Cnặng", "Chcao", "HÁp", "Nh máu", "Loại", "K Quả", "Tiền xét nghiệm"};
        defaultTableModel = new DefaultTableModel(columns, 0);
        setLayout(null);
        tblAllBenhAn.setModel(defaultTableModel);
        add(jPanel3);
        add(in_1);

        JLabel lblNewLabel = new JLabel("Nhập thông tin vào ô bên dưới để tìm");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(27, 35, 241, 13);
        add(lblNewLabel);

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timkiem();
            }
        });
        textField.setBounds(25, 60, 213, 19);
        add(textField);
        textField.setColumns(10);
        loadTable();
    }

    protected void timkiem() {
        list.clear(); // Clear the list before adding new search results
        Connection con = SQLbtl.getConnection();
        String a = textField.getText();
        String sql = "SELECT c.id_benh_an, ho_ten, gioi_tinh, dia_chi, ngay_sinh, so_cmnd, so_dien_thoai, " +
                "di_ung_thuoc, can_nang, chieu_cao, huyet_ap, nhom_mau, id_ten_loai_xet_nghiem, ket_qua, tien " +
                "FROM thong_tin_bnN a " +
                "JOIN xet_nghiem b ON a.id_benh_nhan = b.id_benh_nhan " +
                "JOIN benh_an c ON a.id_benh_nhan = c.id_benh_nhan " +
                "WHERE  a.id_benh_nhan LIKE ? or ho_ten LIKE ? or dia_chi LIKE ? or  so_cmnd LIKE ? or so_dien_thoai LIKE ?"
                + "or nhom_mau LIKE ? or id_ten_loai_xet_nghiem LIKE ? or ket_qua LIKE ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + a + "%");
            st.setString(2, "%" + a + "%");
            st.setString(3, "%" + a + "%");
            st.setString(4, "%" + a + "%");
            st.setString(5, "%" + a + "%");
            st.setString(6, "%" + a + "%");
            st.setString(7, "%" + a + "%");
            st.setString(8, "%" + a + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("id_benh_an");
                String hoten = rs.getString("ho_ten");
                boolean gt = rs.getBoolean("gioi_tinh");
                String dc = rs.getString("dia_chi");
                Date ngay = rs.getDate("ngay_sinh");
                String sdt = rs.getString("so_dien_thoai");
                boolean diung = rs.getBoolean("di_ung_thuoc");
                String cannang = rs.getString("can_nang");
                String chieucao = rs.getString("chieu_cao");
                String huyetap = rs.getString("huyet_ap");
                String nhommau = rs.getString("nhom_mau");
                String lxn = rs.getString("id_ten_loai_xet_nghiem");
                String ketqua = rs.getString("ket_qua");
                String tien = rs.getString("tien");
                TongHopModel z = new TongHopModel(ma, hoten, gt, dc, ngay.toLocalDate(), sdt, diung, cannang, chieucao, huyetap, nhommau, lxn, ketqua, tien);
                list.add(z);
            }
            loadArr();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadArr() {
        DefaultTableModel model_table = (DefaultTableModel) tblAllBenhAn.getModel();
        model_table.setRowCount(0); 
        for (TongHopModel tongHopModel : list) {
            model_table.addRow(new Object[]{
                    tongHopModel.getMa(), tongHopModel.getHoten(), tongHopModel.isGt() ? "Nam" : "Nữ", tongHopModel.getDc(),
                    tongHopModel.getNgaysinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), tongHopModel.getSdt(), tongHopModel.isDiung() ? "Có" : "Không",
                    tongHopModel.getCannang(), tongHopModel.getChieucao(), tongHopModel.getHuyetap(), tongHopModel.getNhommau(), tongHopModel.getLoaiXN(),
                    tongHopModel.getKetqua(), tongHopModel.getTien()
            });
        }
    }

    private void loadTable() {
        defaultTableModel.setRowCount(0);
        Connection con;
        CallableStatement cs;
        ResultSet rs;
        con = SQLbtl.getConnection();
        try {
            cs = con.prepareCall("{call GetAll}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Object[] dt = {rs.getString("id_benh_an"), rs.getString("ho_ten"), rs.getBoolean("gioi_tinh") ? "Nam" : "Nữ",
                        rs.getString("dia_chi"),
                        rs.getDate("ngay_sinh").getDate() + "/" + (rs.getDate("ngay_sinh").getMonth() + 1) + "/" + (rs.getDate("ngay_sinh").getYear() + 1900),
                        rs.getString("so_dien_thoai"), rs.getBoolean("di_ung_thuoc") ? "Có" : "Không",
                        rs.getString("can_nang"), rs.getString("chieu_cao"), rs.getString("huyet_ap"),
                        rs.getString("nhom_mau"), rs.getString("id_ten_loai_xet_nghiem"), rs.getString("ket_qua"),
                        rs.getString("tien")
                };
                defaultTableModel.addRow(dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHopView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {
        area = new JTextArea();
        jPanel3 = new JPanel();
        jPanel3.setBounds(10, 138, 1340, 486);
        jScrollPane1 = new JScrollPane();
        tblAllBenhAn = new JTable();
        tblAllBenhAn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your code here if you need to handle table row clicks
            }
        });

        setBackground(new Color(255, 255, 255));

        jPanel3.setBackground(new Color(204, 204, 204));
        jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Bệnh án", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 2, 24))); // NOI18N

        tblAllBenhAn.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(tblAllBenhAn);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                                .addContainerGap())
        );

        in_1 = new JButton();
        in_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        in_1.setText("In dữ liệu");
        in_1.setBounds(278, 35, 171, 44);
        in_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    in();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
	protected void in() throws SQLException {
		 Connection con;
	        CallableStatement cs;
	        ResultSet rs;	        
	        con = SQLbtl.getConnection();
	            cs = con.prepareCall("{call GetAll}");
	            rs = cs.executeQuery();
	            area.append
     	        ("\t BỆNH VIỆN ĐA KHOA LÝ LONG HOÀNG \n\n"+
     	        		"\t\t Thông tin bệnh nhân \n");
	            while (rs.next()) {    
	            	 area.append(	     	         	               
	     	                "\n\n Mã bệnh nhân: "+rs.getString("id_benh_an")+		          
	     	                "\n Chiều cao: "+rs.getString("chieu_cao")+"cm"+
	     	                "\n Cân nặng: "+rs.getString("can_nang")+"kg"+     	        
	     	                "\n Huyết áp: "+rs.getString("huyet_ap")+"mmHg"+
	     	                "\n Nhóm máu: "+rs.getString("nhom_mau")+
	     	               "\n Loại: "+rs.getString("id_ten_loai_xet_nghiem")+
	     	                "\n Kết quả: "+rs.getString("ket_qua")+
	     	                "\n Giá tiền: "+rs.getString("tien")	     	               
	     	        );
	     	        
	            }
	            try {
     	            Boolean in = area.print();
     	            if (in) {
     	                JOptionPane.showMessageDialog(null, "In xong");
     	                area.setText("");
     	            }
     	            else{
     	                JOptionPane.showMessageDialog(null, "Đang in ......");
     	            }
     	        } catch (PrinterException ex) {
     	            Logger.getLogger(BenhAnview.class.getName()).log(Level.SEVERE, null, ex);
     	        }
	}
}
