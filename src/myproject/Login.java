package myproject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.Locale;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

  
    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setBounds(100, 100, 865, 735);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBackground(new Color(192, 192, 192));
        textField.setBounds(310, 393, 244, 35);
        contentPane.add(textField);
        textField.setColumns(10);
        
        Locale locale = new Locale("th", "TH");
        textField.setLocale(locale);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18)); // หรือเลือก Font ที่รองรับภาษาไทย

        JLabel lblNewLabel = new JLabel("ชื่อบัญชี");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(209, 386, 91, 40);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("รหัสผ่าน");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(204, 454, 95, 40);
        contentPane.add(lblNewLabel_1);
        

        JButton btnNewButton = new JButton("เข้าสู่ระบบ");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(150, 223, 213));
        btnNewButton.setBounds(473, 563, 142, 35);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement st;
                ResultSet rs;

                String username = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";
                try {
                    st = connetData.getConnection().prepareStatement(query);
                    st.setString(1, username);
                    st.setString(2, password);
                    rs = st.executeQuery();
                    if (rs.next()) {
                        // เรียกเมทอดเพื่อดึง username จากฐานข้อมูลและกำหนดให้กับ JLabel
                        String user = retrieveUsernameFromDatabase(username);
                        // สร้างหน้า Home และส่ง username ไปด้วย
                        EventQueue.invokeLater(() -> {
                            new Home(username, user).setVisible(true);
                            System.out.print(username);
                        });

                        // ปิดหน้า Login (ถ้าต้องการ)
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error username/password", "เกิดข้อผิดพลาด",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }

			private String retrieveUsernameFromDatabase(String username) {
				// TODO Auto-generated method stub
				return null;
			}
        });

 
           
        contentPane.add(btnNewButton);
        // ตั้งค่า font สำหรับปุ่ม
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 18); // เปลี่ยน Tahoma เป็น font ที่รองรับภาษาไทย
        btnNewButton.setFont(thaiFont);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(192, 192, 192));
        passwordField.setBounds(309, 461, 245, 35);
        contentPane.add(passwordField);
        
        passwordField.setLocale(locale);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18)); // หรือเลือก Font ที่รองรับภาษาไทย

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/image/Screenshot 2024-01-11 181002.png")));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblNewLabel_2.setBounds(284, 60, 300, 250);
        contentPane.add(lblNewLabel_2);
        
        JButton btnNewButton_1 = new JButton("สมัครสมาชิก");
        btnNewButton_1.setBackground(new Color(150, 223, 213));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose(); // ปิดหน้าปัจจุบัน
                Register registerFrame = new Register();
                registerFrame.setVisible(true);
        	}
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBounds(228, 563, 142, 35);
        contentPane.add(btnNewButton_1);
    }
}
