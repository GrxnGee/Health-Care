package myproject;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Monthly extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputfirst;
    private JLabel resultLabel;
    private JTextField inputlest;
    private JTextField ovulationDate;
	private JTextField to;
	private JTextField textField_4;
    private static String username;
    private static String gender;

   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Monthly frame = new Monthly(username);
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
    public Monthly (String username) {
       
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Monthly");
        setBounds(100, 100, 865, 735);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("วันแรกที่มีประจำเดือนเดือนล่าสุด");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(60, 157, 220, 20);
        contentPane.add(lblNewLabel);

        inputfirst = new JTextField();
        inputfirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inputfirst.setHorizontalAlignment(SwingConstants.CENTER);
        inputfirst.setBounds(371, 150, 150, 35);
        contentPane.add(inputfirst);
        inputfirst.setColumns(10);

        JButton calculateButton = new JButton("คำนวณ");
        calculateButton.setForeground(new Color(255, 255, 255));
        calculateButton.setBackground(new Color(150, 223, 213));
        calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        calculateButton.setBounds(391, 287, 100, 30);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                calculateNextMenstrualCycle();
            }
        });
        contentPane.add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(20, 90, 260, 20);
        contentPane.add(resultLabel);
        
        JLabel lblNewLabel_1 = new JLabel("วันสุดท้ายที่มีประจำเดือนเดือนล่าสุด");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(53, 224, 236, 20);
        contentPane.add(lblNewLabel_1);
        
        inputlest = new JTextField();
        inputlest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inputlest.setHorizontalAlignment(SwingConstants.CENTER);
        inputlest.setColumns(10);
        inputlest.setBounds(371, 217, 150, 35);
        contentPane.add(inputlest);
        
        JLabel lblNewLabel_2 = new JLabel("เดือน-วัน-ปี ค.ศ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(595, 157, 105, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("เดือน-วัน-ปี ค.ศ");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1.setBounds(595, 224, 105, 20);
        contentPane.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_3 = new JLabel("คำนวณวันครบรอบประจำเดือน");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_3.setBounds(290, 69, 319, 41);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("ประมาณวันที่ไข่ตกก่อนมีประจำเดือน");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4.setBounds(53, 367, 245, 30);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("ประมาณวันที่มีประจำเดือน");
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4_1.setBounds(53, 448, 197, 30);
        contentPane.add(lblNewLabel_4_1);
        
        ovulationDate = new JTextField();
        ovulationDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ovulationDate.setHorizontalAlignment(SwingConstants.CENTER);
		ovulationDate.setColumns(10);
		ovulationDate.setBounds(371, 365, 150, 35);
		contentPane.add(ovulationDate);
		
		to = new JTextField();
		to.setFont(new Font("Tahoma", Font.PLAIN, 16));
		to.setHorizontalAlignment(SwingConstants.CENTER);
		to.setColumns(10);
		to.setBounds(371, 528, 150, 35);
		contentPane.add(to);
		 
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_4.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4.setColumns(10);
        textField_4.setBounds(371, 446, 150, 35);
        contentPane.add(textField_4);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("เดือน-วัน-ปี ค.ศ");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1_1.setBounds(595, 372, 105, 20);
        contentPane.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_1_2 = new JLabel("เดือน-วัน-ปี ค.ศ");
        lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1_2.setBounds(595, 535, 105, 20);
        contentPane.add(lblNewLabel_2_1_2);

        
        JLabel lblNewLabel_4_2 = new JLabel("ประมาณวันที่ไข่ตกหลังมีประจำเดือน");
        lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4_2.setBounds(44, 530, 245, 30);
        contentPane.add(lblNewLabel_4_2);
        
        JLabel lblNewLabel_2_1_1_2 = new JLabel("เดือน-วัน-ปี ค.ศ");
        lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2_1_1_2.setBounds(595, 453, 100, 20);
        contentPane.add(lblNewLabel_2_1_1_2);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(150, 223, 213));
        panel.setBounds(0, 0, 851, 49);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton backButtonMonthly = new JButton("ย้อนกลับ");
        backButtonMonthly.setBounds(27, 10, 102, 39);
        panel.add(backButtonMonthly);
        backButtonMonthly.setForeground(new Color(0, 0, 0));
        backButtonMonthly.setBackground(new Color(255, 255, 255));
        backButtonMonthly.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backButtonMonthly.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
          	  dispose();
              Home frame = new Home(username, gender);
              frame.setVisible(true);
          }
        });

    }
   
   

	/**
     * เมทอดสำหรับคำนวณวันครบรอบประจำเดือน
     */
    public void calculateNextMenstrualCycle() {
        try {
            // รับข้อมูลจากผู้ใช้
            String inputDateString = inputfirst.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate inputDate = LocalDate.parse(inputDateString, formatter);

            // คำนวณวันครบรอบประจำเดือน
            LocalDate nextMenstrualCycle = inputDate.plusDays(28);
             // สมมติว่า ระยะเวลาประจำเดือนเฉลี่ย 28 วัน

            // คำนวณวันที่ไข่ตก
            LocalDate ovulationDateValue = nextMenstrualCycle.minusDays(14); // สมมติว่า ระยะเวลาไข่ตกเฉลี่ย 14 วัน
            ovulationDate.setText(ovulationDateValue.format(formatter));

            // คำนวณวันที่มีประจำเดือน
            LocalDate menstruationDate = nextMenstrualCycle.plusDays(14); // สมมติว่า ระยะเวลาประจำเดือนเฉลี่ย 14 วัน
            textField_4.setText(menstruationDate.format(formatter));

            // แสดงว่าไข่ตกหลังมีประจำเดือน 14 วัน
            LocalDate toValue = menstruationDate.plusDays(14);
            to.setText(toValue.format(formatter));

        } catch (Exception ex) {
            // จัดการข้อผิดพลาด (ถ้ามี)
        	
            ovulationDate.setText("Invalid date format");
            textField_4.setText("Invalid date format");
            to.setText("Invalid date format");
        }
    }
}


