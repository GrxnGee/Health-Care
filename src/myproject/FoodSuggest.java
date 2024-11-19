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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class FoodSuggest extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelPanel1 = new JLabel("", SwingConstants.CENTER);
    private JLabel labelPanel2 = new JLabel("", SwingConstants.CENTER);
    private JLabel labelPanel3 = new JLabel("", SwingConstants.CENTER);
    private JLabel labelPanel4 = new JLabel("", SwingConstants.CENTER);
    private String bmi_b; // Added to store BMI status
    private String bmi;
    private static String username;
    private static String gender;

    public FoodSuggest(String username, String gender) {
        this.username = username; // Set the instance variable
        this.gender = gender; // Set the instance variable

        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 255));
        setTitle("FoodSuggest");
        setSize(865, 735);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNewLabel_1_1 = new JLabel("ช่วยจัดหาอาหารที่เหมาะต่อสุขภาพ");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(203, 79, 419, 53);
        getContentPane().add(lblNewLabel_1_1);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));

        JLabel lblNewLabel_1_1_1 = new JLabel("อาหารที่คุณควรรับประทาน");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1_1.setBounds(10, 339, 289, 42);
        getContentPane().add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("BMI ของคุณคือ");
        lblNewLabel_1_1_2.setBounds(372, 143, 118, 24);
        getContentPane().add(lblNewLabel_1_1_2);
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNewLabel_1_2 = new JLabel("");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setBackground(Color.WHITE);
        lblNewLabel_1_2.setBounds(645, 179, 184, 156);
        getContentPane().add(lblNewLabel_1_2);
        JLabel adviceLabel = new JLabel("");
        adviceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        adviceLabel.setBounds(24, 391, 583, 32);
        getContentPane().add(adviceLabel);
        BMIResult result = loadBMI_BMR_BMI_b(username);

        if (result != null) {
            bmi = result.getBMI();
            bmi_b = result.getBMI_b();
            if (Double.parseDouble(bmi) > 30) {
                bmi_b = "อ้วนมาก";
                adviceLabel.setText("คำแนะนำ: ลดปริมาณอาหารที่กินในแต่ละวันเล็กน้อยประมาณ 200 - 300 กิโลแคลอรี");
                setPanelText("ข้าวกล้อง 80kcal ", " แอปเปิ้ล 53kcal", "ผักต่างๆ", "นมถั่วเหลืองไม่มีน้ำตาล55kcal ");
            } else if (Double.parseDouble(bmi) >= 25 && Double.parseDouble(bmi) <= 29.9) {
                bmi_b = "อ้วน";
                adviceLabel.setText("คำแนะนำ:ลดพลังงานจากอาหารในแต่ละวันเล็กน้อยประมาณ 200 - 300 กิโลแคลอรี");
                setPanelText("แกงส้ม 28kcal ", "ส้ม 49kcal", "ผักต่างๆ", "แกงจืด 90kcal ");
            } else if (Double.parseDouble(bmi) >= 23 && Double.parseDouble(bmi) <= 24.9) {
                bmi_b = "น้ำหนักมาตรฐาน";
                adviceLabel.setText("คำแนะนำ: ปริมาณพลังงานจากอาหารที่กินในแต่ละวันเฉลี่ยไม่ควรน้อยกว่า 1,000 - 1,200 กิโลแคลอรี");
                setPanelText("แกงส้มผักรวม 120kcal ", "ฝรั่ง 53kcal", "ข้าวแกงกะหรี่่ไก่ 389kcal", "นมจืด 150kcal");
            } else if (Double.parseDouble(bmi) >= 18.5 && Double.parseDouble(bmi) <= 22.9) {
                bmi_b = "น้ำหนักสมส่วน";
                adviceLabel.setText("คำแนะนำ: ควรอาหารให้ได้สัดส่วนพอเหมาะรวมกันไม่น้อยกว่า 400 กรัมต่อวัน");
                setPanelText("แกงส้ม 28kcal ", "กีวี่ 56kcal", "ผักต่างๆ", "นมจืด 150kcal");
            } else {
                bmi_b = "ต่ำกว่าเกณฑ์";
                adviceLabel.setText("คำแนะนำ:ควรเพิ่มปริมาณการกินอาหารประมาณ 300-500 กิโลแคลอรี");
                setPanelText("ยำไข่ต้ม 150kcal", "ขนมหม้อแกง 179kcal ", "ผักต่างๆ", "นมจืด 150kcal");
            }

        }

        JLabel lblNewLabel_1_1_2_1 = new JLabel(bmi);
        lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_2_1.setBounds(374, 179, 88, 24);
        getContentPane().add(lblNewLabel_1_1_2_1);

        JLabel lblNewLabel_1_1_2_1_1 = new JLabel(bmi_b);
        lblNewLabel_1_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_2_1_1.setBounds(334, 214, 172, 32);
        getContentPane().add(lblNewLabel_1_1_2_1_1);

        JPanel panel_5 = new JPanel();
        panel_5.setBackground(new Color(150, 223, 213));
        panel_5.setBounds(0, 0, 851, 53);
        getContentPane().add(panel_5);
        panel_5.setLayout(null);

        JButton btnNewButton = new JButton("ย้อนกลับ");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBounds(10, 10, 107, 38);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home frame = new Home(username, gender);
                frame.setVisible(true);
            }
        });
        panel_5.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("เพิ่มเติม");
        btnNewButton_1.setBackground(new Color(192, 192, 192));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_1.setBounds(692, 71, 106, 32);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SortFood food = new SortFood(username);
               food.setVisible(true);
            }
        });
        getContentPane().add(btnNewButton_1);
        
        
        JPanel panel = new JPanel();
        panel.setBounds(24, 454, 160, 110);
        getContentPane().add(panel);
        panel.setLayout(null);
        labelPanel1.setBounds(0, 29, 160, 54);
        panel.add(labelPanel1);
        labelPanel1.setBackground(new Color(255, 255, 255));
        labelPanel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(237, 454, 160, 110);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        labelPanel2.setBounds(0, 31, 160, 54);
        panel_1.add(labelPanel2);
        labelPanel2.setBackground(new Color(255, 255, 255));
        labelPanel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(447, 454, 160, 110);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);
        labelPanel3.setBounds(0, 31, 160, 54);
        panel_2.add(labelPanel3);
        labelPanel3.setBackground(new Color(255, 255, 255));
        labelPanel3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(645, 454, 160, 110);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);
        labelPanel4.setBounds(0, 30, 160, 54);
        panel_3.add(labelPanel4);
        labelPanel4.setBackground(new Color(255, 255, 255));
        labelPanel4.setFont(new Font("Tahoma", Font.PLAIN, 18));
    }

    private class BMIResult {
        private String bmi;
        private String bmi_b;

        public BMIResult(String bmi, String bmi_b) {
            this.bmi = bmi;
            this.bmi_b = bmi_b;
        }

        public String getBMI() {
            return bmi;
        }

        public String getBMI_b() {
            return bmi_b;
        }
    }

    private BMIResult loadBMI_BMR_BMI_b(String username) {
        String servername = "localhost";
        String dbname = "user_db";
        Integer portnumber = 3306;
        String password = "";

        // กำหนด URL ของ MySQL Database
        String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;
        String query = "SELECT BMI, BMI_b FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "root", password);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String bmi = resultSet.getString("BMI");
                    String bmi_b = resultSet.getString("BMI_b");
                    return new BMIResult(bmi, bmi_b);
                } else {
                    // จัดการกรณีที่ไม่พบข้อมูล
                    // คุณสามารถโยน exception หรือส่งค่าเริ่มต้น
                    throw new SQLException("No information found");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while loading BMI, BMR, and BMI_b:\n" + e.getMessage());
            e.printStackTrace();
        }

        return null; // Return null if no data found
    }

    private void setPanelText(String text1, String text2, String text3, String text4) {
        labelPanel1.setText(text1);
        labelPanel2.setText(text2);
        labelPanel3.setText(text3);
        labelPanel4.setText(text4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FoodSuggest frame = new FoodSuggest(username, gender);
            frame.setVisible(true);
        });
    }
}
