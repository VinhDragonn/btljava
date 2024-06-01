package demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DAOlogin;
import btlmodel.LoginModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Form_adminView extends JDialog {

	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Form_adminView.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Form_adminView.password = password;
	}

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField textField;
	private  static JPasswordField passwordField;
	private  static String username ;
	private  static String password ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Form_adminView dialog = new Form_adminView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form_adminView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(123, 10, 114, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tài khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(24, 13, 62, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khấu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(24, 53, 62, 13);
		contentPanel.add(lblNewLabel_1);
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 51, 114, 19);
		contentPanel.add(passwordField);
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
                String password = new String(passwordField.getPassword());
                LoginModel s = new LoginModel(username, password);
                DAOlogin.getconStance().insert(s);
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                
			}
		});
		btnNewButton.setBounds(133, 100, 85, 21);
		contentPanel.add(btnNewButton);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
