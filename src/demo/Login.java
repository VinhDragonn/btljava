package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import DAO.DAOlogin;
import btlmodel.LoginModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField_1;
    private JTextField txtChaCTi;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
    	getContentPane().setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 380);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblNewLabel = new JLabel("Tên đăng nhập");    
         lblNewLabel.setBounds(101, 44, 103, 20);
         contentPane.add(lblNewLabel);
         JLabel lblNewLabel_1 = new JLabel("Mật khẩu");      
         lblNewLabel_1.setBounds(103, 86, 62, 16);
         contentPane.add(lblNewLabel_1);
         JButton btnNewButton = new JButton("Đăng nhập");
         btnNewButton.setForeground(new Color(0, 0, 0));
         btnNewButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 String username = textField.getText();
                 String password = new String(passwordField_1.getPassword());
                 LoginModel s = new LoginModel(username, password);
                 LoginModel z  =DAOlogin.getconStance().selectByid(s);                
                 if (z!=null) {
                     dispose(); 
                    Demo a= new Demo();
                     a.setVisible(true); 
                 } else {
                     JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác");
                }
             }
         }
             );
         btnNewButton.setBounds(214, 128, 113, 21);
         contentPane.add(btnNewButton);
         
 JLabel jLabel4 = new JLabel();
 jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/21563b128a7fa20.jpg")));
 jLabel4.setBounds(0, 0, 786, 333);
        contentPane.add(jLabel4);  
        textField = new JTextField();
        textField.setBounds(214, 46, 113, 16);
        contentPane.add(textField);
        textField.setColumns(10);
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(214, 85, 113, 19);
        contentPane.add(passwordField_1); 
  
    }
}
