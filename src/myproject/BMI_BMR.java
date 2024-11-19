package myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BMI_BMR extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	
	
	private static String username;
    private static String gender;
    
    
    
	String[] activityChoices = {"เลือกกิจกรรม", "ออกกำลังกายทุกวัน","ออกกำลังกายนานๆครั้ง", "ออกกำลังกายน้อยมาก","ไม่ออกกำลังกายเลย"};
	private double activityMultiplier = 0.0;
	private Connection connection;
	CalBmiBmr cal= new CalBmiBmr();
	CalBMR calBMRInstance = new CalBMR();



	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(activityChoices);
	public BMI_BMR(String username, String gender) {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ส่วนสูง");
		lblNewLabel.setBounds(88, 181, 50, 29);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));



		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBackground(Color.WHITE);
		textField.setBounds(160, 178, 159, 39);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("เซนติเมตร");
		lblNewLabel_1.setBounds(341, 181, 86, 29);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_2 = new JLabel("น้ำหนัก");
		lblNewLabel_2.setBounds(88, 261, 52, 29);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBackground(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setBounds(160, 256, 159, 39);
		getContentPane().add(textField_1);



		JLabel lblNewLabel_1_1 = new JLabel("กิโลกรัม");
		lblNewLabel_1_1.setBounds(341, 261, 74, 29);
		getContentPane().add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_2_1 = new JLabel("กิจกรรม");
		lblNewLabel_2_1.setBounds(88, 322, 52, 29);
		getContentPane().add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabel lblNewLabel_6 = new JLabel();
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
 		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
 		lblNewLabel_6.setBounds(536, 487, 137, 45);
 		getContentPane().add(lblNewLabel_6);

 		JComboBox<String> comboBoxAct = new JComboBox<>(comboBoxModel);
		comboBoxAct.setBounds(160, 325, 205, 22);
		getContentPane().add(comboBoxAct);

		comboBoxAct.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(526, 203, 147, 110);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_5 = new JLabel();
		lblNewLabel_1_5.setBounds(0, 10, 147, 40);
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel();
		lblNewLabel_1_6.setBounds(0, 60, 147, 40);
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_4 = new JLabel();
 		lblNewLabel_4.setBounds(526, 361, 147, 39);
 		getContentPane().add(lblNewLabel_4);
 		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
 		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
 		lblNewLabel_4.setBackground(Color.WHITE);

 		JButton btnNewButton = new JButton("คำนวณ");
		btnNewButton.addActionListener(new ActionListener() {

			private String age;

			public void actionPerformed(ActionEvent e) {

				CalBMR EnCal = new CalBMR();

				String InputTall =  textField.getText();
				String InputWeight = textField_1.getText();
				Float TallD, WeightD;
				TallD = Float.valueOf(InputTall);
				WeightD = Float.valueOf(InputWeight);

				double BMI = cal.calBMI(TallD, WeightD);
				String BMIresult = String.format("%.2f", BMI);
				String Status = "";

				if(BMI > 30) {
					Status = "อ้วนมาก";
				}
				else if(BMI >= 25 & BMI <= 29.9) {
					Status = "อ้วน";
				}
				else if(BMI >= 23 & BMI <= 24.9) {
					Status = "น้ำหนักมาตรฐาน";
				}
				else if(BMI >= 18.5 & BMI <= 22.9) {
					Status = "น้ำหนักสมส่วน";
				}
				else {
					Status = "ต่ำกว่าเกณฑ์";
				}
				
				
				String Activity = (String) comboBoxAct.getSelectedItem();
				//********************************************************
				if (Activity == "ออกกำลังกายทุกวัน") {
					activityMultiplier = 1.725;
				} else if (Activity == "ออกกำลังกายนานๆครั้ง") {
					activityMultiplier = 1.375;
				} else if (Activity == "ออกกำลังกายน้อยมาก") {
					activityMultiplier = 1.55;
				} else if (Activity =="ไม่ออกกำลังกายเลย") {
					activityMultiplier = 1.9;
				}
				//ปรับตรงนี้หน่อยนะค่ามันงงพอไม่ออกกำลังกายมันดันใช้พลังงานเยอะกว่าเพื่อนอ่ะ
				//********************************************************
				lblNewLabel_1_5.setText(BMIresult);
				lblNewLabel_1_6.setText(Status);

				String inputUsername = username;  // แทนที่ด้วยการรับค่าจากการเข้าสู่ระบบ
				String servername = "localhost";
				String username = "root";
				String password = "";
				String dbname = "user_db";
				int portnumber = 3306;

				String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;

				try {
				    connection = DriverManager.getConnection(jdbcUrl, username, password);
				} catch (SQLException ee) {
				    ee.printStackTrace();
				}

				// ตรวจสอบ gender
				String queryGender = "SELECT gender FROM users WHERE username = ?";
				String Gender = "";
				try (PreparedStatement st1 = connection.prepareStatement(queryGender)) {
					st1.setString(1, inputUsername);
					ResultSet rs1 = st1.executeQuery();

					if (rs1.next()) {
						Gender = rs1.getString("gender");
					} else {
						JOptionPane.showMessageDialog(null, "Error username not found", "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
					ex.printStackTrace();
				}

				// ตรวจสอบ age
				int Oldint = 0;
				String queryOld = "SELECT age FROM users WHERE username = ?";

				try (PreparedStatement st2 = connection.prepareStatement(queryOld)) {
					st2.setString(1, inputUsername);
					ResultSet rs2 = st2.executeQuery();

					if (rs2.next()) {
						Oldint = rs2.getInt("age");
					} else {
						JOptionPane.showMessageDialog(null, "Error username not found", "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
					ex.printStackTrace();
				}

				double BMR = cal.calBMR(Gender, Oldint, TallD, WeightD);
				
				
				cal.calBMR(Gender, Oldint, TallD, WeightD);
				EnCal.calTDEE(Gender, Oldint, TallD, WeightD,activityMultiplier);


				lblNewLabel_4.setText(String.format("%.2f", BMR));

				//*******************************เราเอามาดูค่าที่ออกมาเฉยๆลบทิ้งได้*********************************
				System.out.print(Gender + " " + Oldint);
				System.out.print(activityMultiplier);
				System.out.print(" "+ cal.getBMR());
				System.out.print(EnCal.calTDEE(Gender, Oldint, TallD, WeightD,activityMultiplier));
				//*********************************************************************************
				String Energy = String.format("%.2f", EnCal.getTDEE());
				
				lblNewLabel_6.setText(Energy);

			}
		});
		
		btnNewButton.setBounds(196, 401, 101, 39);
		getContentPane().add(btnNewButton);
		btnNewButton.setBackground(new Color(150, 223, 213));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_3 = new JLabel("คำนวณ BMI และคำนวณ BMR");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_3.setBounds(209, 56, 413, 47);
		getContentPane().add(lblNewLabel_3);
 		
		

		JLabel lblNewLabel_1_2 = new JLabel("ผลการคำนวณ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(558, 113, 121, 29);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("BMI");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_3.setBounds(526, 164, 50, 29);
		getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("BMR");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_3_1.setBounds(526, 322, 50, 29);
		getContentPane().add(lblNewLabel_1_3_1);

		
		JLabel lblNewLabel_1_1_1 = new JLabel("กิโลแคลอรี่");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(701, 371, 80, 29);
		getContentPane().add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 849, 45);
		getContentPane().add(panel);
		panel.setBackground(new Color(150, 223, 213));
		panel.setLayout(null);

		
				 JButton btnNewButton_2 = new JButton("ย้อนกลับ");
				 btnNewButton_2.setBackground(new Color(255, 255, 255));
				 btnNewButton_2.setBounds(21, 10, 101, 35);
				 panel.add(btnNewButton_2);
				 btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
				 
				 JButton btnNewButton_1 = new JButton("บันทึก");
				 btnNewButton_1.setBackground(new Color(150, 223, 213));
				 btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				 btnNewButton_1.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						    String newBMI = lblNewLabel_1_5.getText();
						    String newBMI_b = lblNewLabel_1_6.getText();
						    double newBMR = Double.parseDouble(lblNewLabel_4.getText());
						    String newTDEE = lblNewLabel_6.getText();

						    updateBMI_BMR_BMI_b(username, newBMI, newBMR, newBMI_b, newTDEE);
						}

					});
				 btnNewButton_1.setBounds(354, 589, 105, 39);
				 getContentPane().add(btnNewButton_1);
				 
				 JLabel lblNewLabel_1_1_1_1 = new JLabel("กิโลแคลอรี่");
				 lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
				 lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				 lblNewLabel_1_1_1_1.setBounds(701, 487, 80, 29);
				 getContentPane().add(lblNewLabel_1_1_1_1);
				 
				 JLabel lblNewLabel_5 = new JLabel("พลังงานที่ใช้ในแต่ละวัน");
				 lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
				 lblNewLabel_5.setBounds(526, 429, 159, 45);
				 getContentPane().add(lblNewLabel_5);
				 
				 		
				 		
				 		
				 btnNewButton_2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	  dispose();
	                Home frame = new Home(username, gender);
	                frame.setVisible(true);
	            }
	        });
	   


		setTitle("BMI_BMR");
		setSize(865, 735);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void updateBMI_BMR_BMI_b(String inputUsername, String newBMI, double newBMR, String newBMI_b, String newTDEE) {
	    String servername = "localhost";
	    String username = "root";
	    String dbname = "user_db";
	    int portnumber = 3306;
	    String password = "";

	    // กำหนด URL ของ MySQL Database
	    String jdbcUrl = "jdbc:mysql://" + servername + ":" + portnumber + "/" + dbname;
	    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
	        String query = "UPDATE users SET BMI = ?, BMR = ?, BMI_b = ? ,TDEE = ? WHERE username = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, newBMI);
	            preparedStatement.setDouble(2, newBMR);
	            preparedStatement.setString(3, newBMI_b); // บันทึกค่า BMI_b
	            preparedStatement.setString(4, newTDEE);
	            preparedStatement.setString(5, inputUsername);

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
			BMI_BMR frame = new BMI_BMR(username,gender);
			frame.setVisible(true);
		});
	}
}

