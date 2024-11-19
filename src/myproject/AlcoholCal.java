package myproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AlcoholCal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private static String username;
    private static String gender;

    public AlcoholCal(String username) {
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
        setTitle("AlcoholCal");
        JLabel lblNewLabel_3 = new JLabel("คำนวณระดับแอลกอฮอล์ในเลือด");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel_3.setBounds(207, 66, 413, 47);
        getContentPane().add(lblNewLabel_3);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 849, 47);
        getContentPane().add(panel);
        panel.setBackground(new Color(150, 223, 213));
        panel.setLayout(null);

        JButton btnNewButton_2 = new JButton("ย้อนกลับ");
        btnNewButton_2.setBackground(new Color(255, 255, 255));
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_2.setBounds(27, 10, 93, 37);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home frame = new Home(username,gender );
                frame.setVisible(true);
            }
        });
        panel.add(btnNewButton_2);

        JLabel lblNewLabel_1 = new JLabel("กิโลกรัม");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(491, 0, 68, 29);
        getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField.setColumns(10);
        textField.setBackground(Color.WHITE);
        textField.setBounds(334, 148, 147, 36);
        getContentPane().add(textField);

        JLabel lblNewLabel = new JLabel("น้ำหนัก");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(274, 155, 50, 29);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("จำนวน");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(274, 218, 50, 29);
        getContentPane().add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_1.setColumns(10);
        textField_1.setBackground(Color.WHITE);
        textField_1.setBounds(334, 211, 147, 36);
        getContentPane().add(textField_1);

        JLabel lblNewLabel_1_1 = new JLabel("แก้ว");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(491, 218, 50, 29);
        getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_2_1 = new JLabel("Percent Solution");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1.setBounds(177, 281, 147, 29);
        getContentPane().add(lblNewLabel_2_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_2.setColumns(10);
        textField_2.setBackground(Color.WHITE);
        textField_2.setBounds(334, 274, 147, 36);
        getContentPane().add(textField_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("%");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1.setBounds(491, 281, 50, 29);
        getContentPane().add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_2_1_1 = new JLabel("Potential Blood Level:");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1_1.setBounds(149, 417, 174, 29);
        getContentPane().add(lblNewLabel_2_1_1);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3.setColumns(10);
        textField_3.setBackground(Color.WHITE);
        textField_3.setBounds(334, 417, 147, 29);
        getContentPane().add(textField_3);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Mg/dL");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1_1.setBounds(501, 417, 68, 29);
        getContentPane().add(lblNewLabel_1_1_1_1);

        JButton btnNewButton = new JButton("คำนวณ");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBackground(new Color(150, 223, 213));
        btnNewButton.setBounds(362, 337, 89, 29);
        getContentPane().add(btnNewButton);

        JLabel lblNewLabel_1_1_2 = new JLabel("กิโลกรัม");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_2.setBounds(491, 155, 68, 29);
        getContentPane().add(lblNewLabel_1_1_2);

        String[] WhichDrink = { "เบียร์", "สุรา", "ไวน์" };
        JComboBox comboBox = new JComboBox(WhichDrink);
        comboBox.setBounds(551, 286, 100, 22);
        getContentPane().add(comboBox);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNewLabel_1_1_3 = new JLabel("ระบุประเภทที่ดื่ม");
        lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_3.setBounds(663, 281, 112, 29);
        getContentPane().add(lblNewLabel_1_1_3);

        // โค้ดปุ่ม "บันทึก"
        JButton btnNewButton_1 = new JButton("บันทึก");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String newPBL =  textField_3.getText();

				    updatePBL(username, newPBL);
            }
        });


        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_1.setBackground(new Color(150, 223, 213));
        btnNewButton_1.setBounds(361, 514, 90, 47);
        getContentPane().add(btnNewButton_1);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ToCalAlcohol CalAl = new ToCalAlcohol();

                // แก้ไขทีนี้
                String queryGender = "SELECT `gender` FROM `users` WHERE `username` = ?";
                try (Connection connection = connetData.getConnection();
                     PreparedStatement st1 = connection.prepareStatement(queryGender)) {

                    // ให้ username เป็นตัวแปรที่รับมาจาก parameter ของ constructor
                    st1.setString(1, username);
                    
                    ResultSet rs1 = st1.executeQuery();
                    if (rs1.next()) {
                        gender = rs1.getString("gender");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error username/password", "เกิดข้อผิดพลาด",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }


                String WhichDrink = (String) comboBox.getSelectedItem();
                String InputWeight = textField.getText();
                String GlassCount = textField_1.getText();
                String Percent = textField_2.getText();

                int WeightInt = Integer.valueOf(InputWeight);
                int GlassInt = Integer.valueOf(GlassCount);
                int PercentInt = Integer.valueOf(Percent);

                CalAl.CalAlInBlood(WhichDrink, gender, WeightInt, GlassInt, PercentInt);

                String Result = String.format("%.2f", CalAl.GetCalResult());
                textField_3.setText(Result);
            }
        });

        setTitle("AlcoholCal");
        setSize(865, 735);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void updatePBL(String inputUsername, String newPBL) {
	    String servername = "localhost";
	    String username = "root";
	    String dbname = "user_db";
	    int portnumber = 3306;
	    String password = "";

	    // กำหนด URL ของ MySQL Database
	    String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;
	    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
	        String query = "UPDATE users SET PBL = ? WHERE username = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, newPBL);
	           
	            preparedStatement.setString(2, inputUsername);

	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(null, "Data saved successfully");
	            } else {
	                JOptionPane.showMessageDialog(null, "No records updated. Username not found.");
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "An error occurred while updating data:\n" + e.getMessage());
	        e.printStackTrace();
	    }
	}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlcoholCal frame = new AlcoholCal(username);
            frame.setVisible(true);
        });
    }
}
