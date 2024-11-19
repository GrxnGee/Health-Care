package myproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.CardLayout;

public class Home extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static String username;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private CardLayout cardLayout;
    private static String gender;
    private Integer age;
    private static String bmi;
    private static String pbl;
    private static String bmi_b;
    private static String tdee;
    private static String bmr;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home(username, gender);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Home(String username, String gender) {
        Home.username = username;
        String gender1 = retrieveGenderFromDatabase(username);
        age = retrieveAgeFromDatabase(username);
       
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 865, 735);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        contentPane.add(panel, "Home");
        panel.setLayout(null);
        JLabel lblNewLabel = new JLabel(username);
        lblNewLabel.setBounds(10, 10, 138, 24);
        panel.add(lblNewLabel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setToolTipText("");
        lblNewLabel.setLabelFor(this);
        lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/image/circle-user (1).png")));
     // กำหนดค่าเริ่มต้นให้ CardLayout
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        // เพิ่มพาเนลล์ลงใน contentPane พร้อมกับชื่อที่ไม่ซ้ำกัน
        contentPane.add(panel, "Home");
       
		
       
		

        // กำหนดพาเนลที่ต้องการแสดงเป็นค่าเริ่มต้น
        cardLayout.show(contentPane, "Home");
        
        JLabel lblNewLabel_1 = new JLabel("Health Care");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_1.setBounds(340, 36, 132, 64);
        panel.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("คำนวณ BMI และคำนวณ BMR");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(150, 223, 213));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(157, 159, 500, 50);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BMI_BMR bmi = new BMI_BMR(username, gender);
                bmi.setVisible(true);
             
            }
        });
        
        
        JButton btnNewButton_1 = new JButton("ช่วยจัดหาอาหารที่เหมาะต่อสุขภาพ");
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(150, 223, 213));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1.setBounds(157, 246, 500, 50);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                FoodSuggest frame = new FoodSuggest(username,gender); // Corrected the class name
    			frame.setVisible(true);
              
            }
        });
        
        
        JButton btnNewButton_2 = new JButton("คำนวณแอลกอฮอล์");
        btnNewButton_2.setBackground(new Color(150, 223, 213));
        btnNewButton_2.setForeground(new Color(255, 255, 255));
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_2.setBounds(157, 335, 500, 50);
        panel.add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AlcoholCal alc = new AlcoholCal(username);
               alc.setVisible(true);
            }
        });
        
        JButton btnNewButton_3 = new JButton("คำนวณวันครบรอบประจำเดือน");
        btnNewButton_3.setBackground(new Color(150, 223, 213));
        btnNewButton_3.setForeground(new Color(255, 255, 255));
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_3.setBounds(157, 423, 500, 50);
        panel.add(btnNewButton_3);
        
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Monthly mon = new Monthly(username);
                mon.setVisible(true);
            }
        });
        JButton btnNewButton_4 = new JButton("คำนวณวันครบกำหนดคลอด");
        btnNewButton_4.setForeground(new Color(255, 255, 255));
        btnNewButton_4.setBackground(new Color(150, 223, 213));
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_4.setBounds(157, 505, 500, 50);
        panel.add(btnNewButton_4);
        
        JButton btnNewButton_7 = new JButton("โรคยอดฮิต");
        btnNewButton_7.setForeground(new Color(255, 255, 255));
        btnNewButton_7.setBackground(new Color(150, 223, 213));
        btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_7.setBounds(626, 24, 151, 24);
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Alldiseases dis = new Alldiseases(username);
                dis.setVisible(true);
            }
        });
        panel.add(btnNewButton_7);
        
        JButton btnNewButton_7_1 = new JButton("อาหารเพื่อสุขภาพ");
        btnNewButton_7_1.setForeground(Color.WHITE);
        btnNewButton_7_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_7_1.setBackground(new Color(150, 223, 213));
        btnNewButton_7_1.setBounds(626, 58, 151, 24);
        btnNewButton_7_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SearchingFood food = new SearchingFood(username);
                food.setVisible(true);
            }
        });
        panel.add(btnNewButton_7_1);
        
        
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                PregnancyCalculatorGUI preg = new PregnancyCalculatorGUI(username, gender);
                preg.setVisible(true);
            }
        });
        if (!"Female".equals(gender1)) {
            btnNewButton_3.setVisible(false); // ปิดปุ่ม
            btnNewButton_4.setVisible(false); // ปิดปุ่ม
        } else {
        	 btnNewButton_3.setVisible(true); // เปิดปุ่ม
             btnNewButton_4.setVisible(true); // เปิดปุ่ม
        }
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        contentPane.add(panel_1, "Edit");
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("แก้ไขข้อมูล");
        lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_2.setIcon(new ImageIcon(Home.class.getResource("/image/user.png")));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_2.setBounds(336, 78, 184, 70);
        panel_1.add(lblNewLabel_2);
        
        textField = new JTextField(username); 
        textField.setBounds(349, 236, 232, 41);
        panel_1.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setText(String.valueOf(age));
        textField_1.setColumns(10);
        textField_1.setBounds(349, 315, 134, 41);
        panel_1.add(textField_1);
        
        JLabel lblNewLabel_3 = new JLabel("ชื่อบัญชี");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(222, 239, 79, 27);
        panel_1.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel("อายุ");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_1.setBounds(222, 318, 65, 27);
        panel_1.add(lblNewLabel_3_1);
        
        JButton btnNewButton_5 = new JButton("บันทึกข้อมูล");  
        btnNewButton_5.setForeground(new Color(255, 255, 255));
        btnNewButton_5.setBackground(new Color(150, 223, 213));
        btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_5.setBounds(349, 500, 134, 41);
        panel_1.add(btnNewButton_5);
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = textField.getText();
                int newAge = Integer.parseInt(textField_1.getText());

                updateUserInfoInDatabase(username, newName, newAge);
            }
        });

        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        contentPane.add(panel_2, "All");
        panel_2.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("ข้อมูลสุขภาพของคุณ");
        lblNewLabel_4.setBackground(new Color(255, 255, 255));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_4.setBounds(300, 10, 223, 32);
        panel_2.add(lblNewLabel_4);
        
		textField_2 = new JTextField(bmi);
        textField_2.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_2.setBounds(347, 125, 105, 37);
        panel_2.add(textField_2);
        textField_2.setColumns(10);
       
        
        textField_3 = new JTextField(bmi_b);
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_3.setColumns(10);
        textField_3.setBounds(595, 125, 140, 35);
        panel_2.add(textField_3);
        
        
        JLabel data = new JLabel("อยู่ในเกณท์");
        data.setBackground(new Color(255, 255, 255));
        data.setFont(new Font("Tahoma", Font.PLAIN, 16));
        data.setBounds(620, 90, 105, 37);
        panel_2.add(data);
        textField_4 = new JTextField(bmr);
        textField_4.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_4.setColumns(10);
        textField_4.setBounds(347, 190, 105, 37);
        panel_2.add(textField_4);
        
        
        textField_5 = new JTextField(tdee);
        textField_5.setHorizontalAlignment(SwingConstants.CENTER);
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_5.setColumns(10);
        textField_5.setBounds(347, 255, 105, 37);
        panel_2.add(textField_5);
        
        textField_6 = new JTextField(pbl);;
        textField_6.setHorizontalAlignment(SwingConstants.CENTER);
        textField_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField_6.setColumns(10);
        textField_6.setBounds(347, 320, 105, 37);
        panel_2.add(textField_6);
        
        JButton btnNewButton_6 = new JButton("พิมพ์"); //ยังไม่ได้ทำ
        btnNewButton_6.setForeground(new Color(255, 255, 255));
        btnNewButton_6.setBackground(new Color(150, 223, 213));
        btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_6.setBounds(347, 510, 121, 48);
        btnNewButton_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveResultsToFile();
            }
        });
        panel_2.add(btnNewButton_6);
        
        JLabel lblNewLabel_5 = new JLabel("ค่า BMI");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(87, 129, 80, 28);
        panel_2.add(lblNewLabel_5);
        
        JLabel lblNewLabel_5_1 = new JLabel("ค่า BMR");
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_5_1.setBounds(87, 199, 80, 28);
        panel_2.add(lblNewLabel_5_1);
        
        
        JLabel lblNewLabel_5_3 = new JLabel("พลังงานที่ใช้ในแต่ละวัน");
        lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_5_3.setBounds(87, 255, 195, 28);
        panel_2.add(lblNewLabel_5_3);
        
            
        JLabel lblNewLabel_5_4 = new JLabel("ระดับแอลกอฮอล์ในเลือด ");
        lblNewLabel_5_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_5_4.setBounds(87, 324, 211, 28);
        panel_2.add(lblNewLabel_5_4);
        
        JLabel lblNewLabel_6 = new JLabel("กิโลแคลอรี่");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_6.setBounds(471, 130, 98, 28);
        panel_2.add(lblNewLabel_6);
        
        JLabel lblNewLabel_6_1 = new JLabel("กิโลแคลอรี่");
        lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_6_1.setBounds(471, 190, 98, 28);
        panel_2.add(lblNewLabel_6_1);
        
        JLabel lblNewLabel_6_2 = new JLabel("กิโลแคลอรี่");
        lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_6_2.setBounds(471, 255, 98, 28);
        panel_2.add(lblNewLabel_6_2);
        
        JLabel lblNewLabel_6_3 = new JLabel("mmol/l");
        lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_6_3.setBounds(471, 320, 98, 28);
        panel_2.add(lblNewLabel_6_3);
        
       
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(150, 223, 213));
        setJMenuBar(menuBar);
        menuBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
                JMenu menu = new JMenu("เมนู");
                menuBar.add(menu);
                menu.setFont(new Font("Tahoma", Font.PLAIN, 16));
                JMenuItem homeItem = new JMenuItem("หน้าหลัก");
                homeItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
                menu.add(homeItem);
                
                        JMenuItem editInfoItem = new JMenuItem("แก้ไขข้อมูลส่วนตัว"); // "Edit Personal Information" in Thai
                        editInfoItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        menu.add(editInfoItem);
                        
                                JMenuItem viewResultsItem = new JMenuItem("ผลการคำนวณทั้งหมด"); // "View All Results" in Thai
                                viewResultsItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
                                menu.add(viewResultsItem);
                                
                                        JMenuItem exitItem = new JMenuItem("ออกจากระบบ"); // "Exit" in Thai
                                        exitItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
                                        menu.add(exitItem);
                                        
                                        homeItem.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                // สลับไปที่หน้า Home
                                                cardLayout.show(contentPane, "Home");
                                            }
                                        });
                                        editInfoItem.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                // สลับไปที่พาเนล "Edit"
                                                cardLayout.show(contentPane, "Edit");
                                            }
                                        });

                                        viewResultsItem.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                // สลับไปที่พาเนล "All"
                                                cardLayout.show(contentPane, "All");
                                                retrieveAllResultsFromDatabase(username);
                                            }
                                        });
                                                        
            exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Add code to handle "ออกจากระบบ" option
                 System.exit(0);
          }
        });

    }
    private void saveResultsToFile() {
    	// สร้าง JFileChooser เพื่อให้ผู้ใช้เลือกไฟล์
        JFileChooser fileChooser = new JFileChooser();
        
        // แสดงหน้าต่างเลือกไฟล์
        int userSelection = fileChooser.showSaveDialog(null);
        
        // ตรวจสอบว่าผู้ใช้เลือกไฟล์หรือไม่
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // ดึงไฟล์ที่ถูกเลือก
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
            	// เขียนผลลัพธ์ลงในไฟล์
                writer.write("ค่า BMI: " + textField_2.getText() + "  "+" อยู่ในเกณท์ "+ textField_3.getText() +"\n");
                writer.write("ค่า BMR: " + textField_4.getText() +"  "+ "กิโลแคลอรี่"+ "\n");
                writer.write("พลังงานที่ใช้ในแต่ละวัน: " +textField_5.getText()+ "  " + "กิโลแคลอรี่" + "\n" );
                writer.write("ระดับแอลกอฮอล์ในเลือด: " +textField_6.getText()+"  "+ "mmol/l"+ "\n" );
             

                JOptionPane.showMessageDialog(null, "Results saved successfully.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving results to file.");
            }
        }
    }
    
    private void retrieveAllResultsFromDatabase(String inputUsername) {
        String servername = "localhost";
        String username = "root";
        String dbname = "user_db";
        Integer portnumber = 3306;
        String password = "";

        // กำหนด URL ของ MySQL Database
        String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT BMI, BMR, BMI_b, PBL, TDEE FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, inputUsername);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // ดึงข้อมูลที่ต้องการแสดง
                        String bmi = resultSet.getString("BMI");
                        String bmr = resultSet.getString("BMR");
                        String bmi_b = resultSet.getString("BMI_b");
                        String pbl = resultSet.getString("PBL");
                        String tdee = resultSet.getString("TDEE");
                        

                        // แสดงข้อมูลบน UI
                        textField_2.setText(bmi);
                        textField_3.setText(bmi_b);
                        textField_4.setText(bmr);
                        textField_5.setText(tdee);
                        textField_6.setText(pbl);
                    } else {
                        // กรณีไม่พบข้อมูล
                        JOptionPane.showMessageDialog(null, "No information found");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateUserInfoInDatabase(String inputUsername, String newName, int newAge) {
        String servername = "localhost";
        String username = "root";
        String dbname = "user_db";
        int portnumber = 3306;
        String password = "";

        // กำหนด URL ของ MySQL Database
        String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "UPDATE users SET username = ?, age = ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, newAge);
                preparedStatement.setString(3, inputUsername);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                	 
                    JOptionPane.showMessageDialog(null, "Data saved successfully");
                    
                } else {
                	
                    JOptionPane.showMessageDialog(null, "An error occurred while saving data.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    private Integer retrieveAgeFromDatabase(String inputUsername) {
        String servername = "localhost";
        String username = "root";
        String dbname = "user_db";
        Integer portnumber = 3306;
        String password = "";

        // กำหนด URL ของ MySQL Database
        String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT age FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, inputUsername); // ใส่ค่า inputUsername ใน statement
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int age = resultSet.getInt("age");
                        
                        return age;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // หากไม่พบข้อมูลอายุ หรือเกิดข้อผิดพลาด
    }
   
    private String retrieveGenderFromDatabase(String inputUsername) {
        String servername = "localhost";
        String username = "root";
        String dbname = "user_db";
        Integer portnumber = 3306;
        String password = "";

        // กำหนด URL ของ MySQL Database
        String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT gender FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, inputUsername);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("gender");
                    } else {
                        // จัดการกรณีที่ไม่พบข้อมูล
                        // คุณสามารถโยน exception หรือส่งค่าเริ่มต้น
                        throw new SQLException("No information found");
                    }
                }
            }
        } catch (SQLException e) {
            // จัดการข้อผิดพลาด (เช่น บันทึก log)
            e.printStackTrace();
            // ส่งค่าเพศเริ่มต้นหรือโยน exception ตามการออกแบบของคุณ
            return "Unknown"; // เปลี่ยนเป็นค่าเริ่มต้นที่เหมาะสม
        }
    }
    }