package myproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PregnancyCalculatorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lastMenstrualDate;
	private JTextField cycleLengthLabel;
	private JTextField ovulationDate;
	private JTextField to;
	private JTextField intercourseDate;
	private JTextField dueDate;
	private static String username;
    private static String gender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregnancyCalculatorGUI frame = new PregnancyCalculatorGUI(username, gender);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void calculateFunction() {
	    try {
	        // Get data from JTextField
	        String lastMenstrualDateValue = lastMenstrualDate.getText();
	        // Convert the first day of the menstrual period to LocalDate
	        LocalDate lastMenstrualDate = LocalDate.parse(lastMenstrualDateValue, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

	        // Calculate the ovulation date
	        LocalDate calculatedOvulationDate = lastMenstrualDate.plusDays(10);

	        // Calculate the end date of the ovulation period
	        LocalDate calculatedTo = calculatedOvulationDate.plusDays(10);

	        // Calculate the estimated due date (Due Date)
	        LocalDate calculatedDueDate = lastMenstrualDate.plusDays(372); // Add 266 days (or 38 weeks)
	        calculatedDueDate = calculatedDueDate.minusMonths(3); // Subtract 3 months

	        // Calculate the date of the expected intercourse
	        LocalDate calculatedIntercourseDate = lastMenstrualDate.plusDays(15);

	        // Set the calculated dates to the respective JTextField components
	        ovulationDate.setText(formatDate(calculatedOvulationDate));
	        to.setText(formatDate(calculatedTo));
	        intercourseDate.setText(formatDate(calculatedIntercourseDate));
	        dueDate.setText(formatDate(calculatedDueDate));

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        // Handle errors, display messages, etc.
	    }
	}
	private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
	

	/**
	 * Create the frame.
	 * @param gender 
	 * @param username 
	 */
	public PregnancyCalculatorGUI(String username, String gender) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PregnancyCalculatorGUI");
		setBounds(100, 100, 865, 735);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("คำนวณวันครบกำหนดคลอด");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(298, 80, 262, 28);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(150, 223, 213)); // Set the RGB values for the color
		panel.setBounds(0, 0, 851, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("ย้อนกลับ");
		btnNewButton.setBounds(20, 10, 105, 38);
		panel.add(btnNewButton);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
          	  dispose();
              Home frame = new Home(username, gender);
              frame.setVisible(true);
          }
        });
		
		JLabel lblNewLabel_1 = new JLabel("วันแรกที่มีประจำเดือนเดือนล่าสุด");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(101, 176, 226, 28);
		contentPane.add(lblNewLabel_1);
		
		lastMenstrualDate = new JTextField();
		lastMenstrualDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastMenstrualDate.setHorizontalAlignment(SwingConstants.CENTER);
		lastMenstrualDate.setBounds(347, 173, 156, 35);
		contentPane.add(lastMenstrualDate);
		lastMenstrualDate.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("เดือน/วัน/ปี ค.ศ.");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(537, 175, 125, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("เฉลี่ยรอบเดือน\r\n");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(224, 240, 103, 28);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("(22ถึง 44 ปกติ = 28)");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(535, 240, 149, 28);
		contentPane.add(lblNewLabel_1_1_1);
		
		cycleLengthLabel = new JTextField();
		cycleLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cycleLengthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cycleLengthLabel.setColumns(10);
		cycleLengthLabel.setBounds(347, 237, 156, 35);
		contentPane.add(cycleLengthLabel);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("ประมาณวันที่ไข่ตก");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(134, 377, 130, 28);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("ถึง");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1.setBounds(468, 377, 35, 28);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("ประมาณวันที่การปฏิสนธิ");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_2.setBounds(116, 453, 162, 28);
		contentPane.add(lblNewLabel_1_2_1_2);
		
		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("วันครบกำหนดคลอด");
		lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_2_1.setBounds(134, 527, 144, 28);
		contentPane.add(lblNewLabel_1_2_1_2_1);
		
		ovulationDate = new JTextField();
		ovulationDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ovulationDate.setHorizontalAlignment(SwingConstants.CENTER);
		ovulationDate.setColumns(10);
		ovulationDate.setBounds(298, 376, 140, 35);
		contentPane.add(ovulationDate);
		
		to = new JTextField();
		to.setFont(new Font("Tahoma", Font.PLAIN, 16));
		to.setHorizontalAlignment(SwingConstants.CENTER);
		to.setColumns(10);
		to.setBounds(507, 376, 139, 35);
		contentPane.add(to);
		
		intercourseDate = new JTextField();
		intercourseDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		intercourseDate.setHorizontalAlignment(SwingConstants.CENTER);
		intercourseDate.setColumns(10);
		intercourseDate.setBounds(298, 452, 148, 35);
		contentPane.add(intercourseDate);
		
		dueDate = new JTextField();
		dueDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dueDate.setHorizontalAlignment(SwingConstants.CENTER);
		dueDate.setColumns(10);
		dueDate.setBounds(298, 524, 148, 35);
		contentPane.add(dueDate);
		
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("คำนวณ");
		lblNewLabel_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1_1.setBounds(392, 311, 103, 35);
		contentPane.add(lblNewLabel_1_2_1_1_1);
		
		JLabel calculateButton = new JLabel("");
		calculateButton.setOpaque(true);
		calculateButton.setBackground(new Color(150, 223, 213));
		calculateButton.setHorizontalAlignment(SwingConstants.CENTER);
		calculateButton.setBounds(392, 311, 103, 35);
		contentPane.add(calculateButton);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("เดือน/วัน/ปี ค.ศ.");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(681, 377, 111, 28);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("เดือน/วัน/ปี ค.ศ.");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_3.setBounds(468, 453, 130, 28);
		contentPane.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("เดือน/วัน/ปี ค.ศ.");
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_3_1.setBounds(468, 527, 125, 28);
		contentPane.add(lblNewLabel_1_1_3_1);

		calculateButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        calculateFunction();
		    }
		});
	}
}
