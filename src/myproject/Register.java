package myproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_2;
    private JTextField textField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register");
        setBounds(100, 100, 865, 735);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
		panel.setBounds(0, 0, 851, 39);
		getContentPane().add(panel);
		panel.setBackground(new Color(150, 223, 213));
		panel.setLayout(null);

        textField = new JTextField();
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField.setBounds(327, 276, 236, 38);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(327, 340, 236, 38);
        contentPane.add(passwordField);

        rdbtnNewRadioButton = new JRadioButton("Male");
        rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
        rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rdbtnNewRadioButton.setBounds(332, 465, 103, 21);
        contentPane.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_2 = new JRadioButton("Female");
        rdbtnNewRadioButton_2.setBackground(new Color(255, 255, 255));
        rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rdbtnNewRadioButton_2.setBounds(437, 458, 126, 35);
        contentPane.add(rdbtnNewRadioButton_2);

        JLabel lblNewLabel = new JLabel("ชื่อบัญชี");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(206, 269, 86, 50);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("รหัสผ่าน");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(206, 333, 86, 50);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("อายุ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(242, 390, 40, 50);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("เพศ");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(242, 450, 40, 50);
        contentPane.add(lblNewLabel_3);

        JButton btnNewButton = new JButton("สมัครสมาชิก");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(150, 223, 213));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(455, 556, 151, 38);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        contentPane.add(btnNewButton);

        JLabel lblNewLabel_4 = new JLabel("สมัครสมาชิก");
        lblNewLabel_4.setIcon(new ImageIcon(Register.class.getResource("/image/Screenshot 2024-01-11 181613.png")));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblNewLabel_4.setBounds(267, 33, 329, 229);
        contentPane.add(lblNewLabel_4);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_1.setColumns(10);
        textField_1.setBackground(Color.LIGHT_GRAY);
        textField_1.setBounds(327, 391, 108, 38);
        contentPane.add(textField_1);
        
        JButton btnNewButton_1 = new JButton("เข้าสู่ระบบ");
        btnNewButton_1.setBackground(new Color(150, 223, 213));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_1.setBounds(207, 556, 151, 38);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose(); // ปิดหน้าปัจจุบัน
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                
            }
        });
        contentPane.add(btnNewButton_1);

        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton_2.setSelected(false);
            }
        });

        rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(false);
            }
        });
    }

    public boolean registerUser() {
        String username = textField.getText();
        char[] password = passwordField.getPassword();
        int age = Integer.parseInt(textField_1.getText());
        String gender = (rdbtnNewRadioButton.isSelected()) ? "Male" : "Female";

        // check empty fields
        if (username.trim().equals("") || String.valueOf(password).trim().equals("") || age == 0 || gender.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        }

        // check if the username already exists
        if (!checkUsername(username)) {
            // insert data into the database
            PreparedStatement ps;
            String query = "INSERT INTO users (username, password, age, gender) VALUES (?, ?, ?, ?)";
            try {
                ps = connetData.getConnection().prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, String.valueOf(password));
                ps.setInt(3, age);
                ps.setString(4, gender);

                // execute the query
                int result = ps.executeUpdate();

                if (result > 0) {
                	Locale.setDefault(new Locale("th", "TH")); 
                    JOptionPane.showMessageDialog(null, "Account created successfully");
                    // ทำการเปลี่ยนไปหน้า home หรือทำอะไรต่อตามที่คุณต้องการ
                    // เช่น ให้ลงทะเบียนเสร็จแล้วเปิดหน้า home
                    Home home = new Home(username, gender);
                    home.setVisible(true);
                    // ปิดหน้าลงทะเบียน
                    dispose();
                } else {
                	Locale.setDefault(new Locale("th", "TH"));
                    JOptionPane.showMessageDialog(null,"Make sure your information is correct.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(connetData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    public boolean checkUsername(String username) {
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `users` WHERE `username` = ?";

        try {
            st = connetData.getConnection().prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                username_exist = true;
                Locale.setDefault(new Locale("th", "TH")); 
                JOptionPane.showMessageDialog(null, "This account is already logged in. Please change again.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username_exist;
    }
}
