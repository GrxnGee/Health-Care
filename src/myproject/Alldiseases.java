package myproject;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class Alldiseases extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] search = {"โรคอ้วนและน้ำหนักตัวเกิน","โรคระบบทางเดินหายใจและปอด","โรคเบาหวาน","โรคหลอดเลือดสมองและหัวใจ","โรคมะเร็ง","โรคความดันโลหิต","โรค COVID-19",
			"โรคตับแข็ง","โรคไต","วัณโรคที่มากับอากาศ","โรคปอดเรื้อรัง","โรคระบบกล้ามเนื้อ เส้นเอ็นอักเสบ","โรคภูมิแพ้","โรคหัวใจขาดเลือด","โรคกระเพาะอาหาร","อหิวาตกโรคง","โรคหอบ หืด",
			"โรคอาหารเป็นพิษ","โรคอุจจาระร่วง","โรคอ้วนลงพุง","โรคไทรอยด์เป็นพิษ",
		"โรคเกาต์","โรคข้อเข่าเสื่อม","โรคกระดูกพรุน"};
	private static String username;
	private static String gender;
	private JTextField textField;
	String filePath = "D:/Alldiseases/product.txt";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alldiseases frame = new Alldiseases(username);
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
	public Alldiseases(String username) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alldiseases");
		setBounds(100, 100, 450, 300);
		setSize(865, 735);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
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
		
		JButton btnNewButton = new JButton("ย้อนกลับ");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 10, 109, 29);
		 btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	  dispose();
	                Home frame = new Home(username, gender);
	                frame.setVisible(true);
	            }
	        });
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("โรคที่เกี่ยวกับสุขภาพในไทย");
		lblNewLabel.setBounds(278, 2, 273, 39);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPane.setBounds(10, 100, 831, 598);
		contentPane.add(textPane);
		displayDataFromFile(textPane);
		// Wrap the JTextPane in a JScrollPane
				JScrollPane scrollPane = new JScrollPane(textPane);
				scrollPane.setBounds(10, 100, 831, 598);
				contentPane.add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(236, 49, 291, 29);
		contentPane.add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		
		
		JButton searchButton = new JButton("ค้นหา");
		searchButton.setBounds(537, 49, 75, 31);
		contentPane.add(searchButton);
		searchButton.setBackground(new Color(150, 225, 223));
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = textField.getText();
		        if (searchTerm != null && !searchTerm.isEmpty()) {
		            Map<String, List<String>> matchingFileResults = searchAndCollectResult(searchTerm);

		            if (!matchingFileResults.isEmpty()) {
		                displayResult(matchingFileResults, textPane);
		            } else {
		                textPane.setText("ไม่พบผลลัพธ์ที่ตรงกับการค้นหา.");
		            }
		        } else {
		            textPane.setText(""); // ล้างข้อความที่อาจจะแสดงไว้ก่อนหน้า
		            // แสดงข้อมูลจากไฟล์ หรือทำอย่างไรตามที่คุณต้องการ
		            displayDataFromFile(textPane);
		        }
		    }
		});


	}
	private void displayDataFromFile(JTextPane resultTextPane) {
	    // อ่านข้อมูลจากไฟล์ และนำเข้าข้อมูลลงใน resultTextPane
	    try (Scanner scanner = new Scanner(new File(filePath))) {
	        StringBuilder fileData = new StringBuilder();
	        while (scanner.hasNextLine()) {
	            fileData.append(scanner.nextLine()).append("\n");
	        }
	        resultTextPane.setText(fileData.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

		private void displayResult(Map<String, List<String>> results, JTextPane resultTextPane) {
		    if (!results.isEmpty()) {
		        StringBuilder resultText = new StringBuilder();

		        for (Map.Entry<String, List<String>> entry : results.entrySet()) {
		            resultText.append(entry.getKey()).append(":\n");
		            for (String line : entry.getValue()) {
		                resultText.append(line).append("\n");
		            }
		            resultText.append("\n".repeat(2)); // ให้ห่างกัน 2 บรรทัด
		        }

		        resultTextPane.setText(resultText.toString());
		    } else {
		        resultTextPane.setText("ไม่พบผลลัพธ์ที่ตรงกับการค้นหา.");
		    }
		}



		
		private Map<String, List<String>> searchAndCollectResult(String searchTerm) {
		    Map<String, List<String>> matchingResults = new HashMap<>();

		    for (String item : search) {
		        if (item.contains(searchTerm)) {
		            matchingResults.put(item, new ArrayList<>());

		            // เพิ่มข้อมูลที่เกี่ยวกับโรคนั้น ๆ ใน List ที่อยู่ใน Map
		            switch (item) {
		                case "โรคเบาหวาน":
		                    matchingResults.get(item).add("โรคเบาหวานเป็นโรคที่เกิดจากการเพิ่มปริมาณน้ำตาลในเลือด");
		                    matchingResults.get(item).add("สาเหตุทางกรรมพันธุ์, พฤติกรรมการบริโภค, และขาดการออกกำลังกายเป็นปัจจัยที่เสี่ยง");
		                    matchingResults.get(item).add("คนที่เป็นโรคเบาหวานมักไม่มีอาการแสดงออกชัดเจนในระยะแรก");
		                    matchingResults.get(item).add("การดูแลรักษาโรคเบาหวาน เพื่อลดความเสี่ยงและควบคุมระดับน้ำตาลในเลือด");
		                    break;
		                case "โรคอ้วนและน้ำหนักตัวเกิน":
		                    matchingResults.get(item).add("แนวโน้มการบริโภคที่ง่ายและสะดวกขึ้นทำให้โรคอ้วนเป็นอีกหนึ่งโรคที่คนไทยสมัยนี้เป็นกันมาก");
		                    matchingResults.get(item).add("รวมถึงการใช้ชีวิตที่ต้องคร่ำเคร่ง ทำงานตลอดเวลา จนไม่มีเวลาให้กับการออกกำลังกาย");
		                    matchingResults.get(item).add("ผลการจัดอันดับประเทศที่มีประชากรน้ำหนักตัวเกินมาตรฐานโดยองค์การอนามัยโลก ประจำปี 2559 พบว่าประเทศไทยมีประชากรที่มีน้ำหนักเกินมาตรฐานมากถึง 32.2%");
		                    matchingResults.get(item).add("โดยที่ผู้ป่วยอาจไม่ได้เสียชีวิตจากโรคนี้โดยตรง แต่มักจะเสียชีวิตจากโรคแทรกซ้อน เช่น เบาหวาน ความดัน ภาวะหยุดหายใจเฉียบพลัน");
		                    break;
		                case "โรคระบบทางเดินหายใจและปอด":
		                    matchingResults.get(item).add("เกิดได้จากหลายสาเหตุ เช่น มลภาวะในอากาศ การหายใจเอาละอองสารเคมีเข้าสู่ร่างกายเป็นระยะเวลานาน");
		                    matchingResults.get(item).add("แต่ปัจจัยเสี่ยงที่สำคัญ ได้แก่ การสูบบุหรี่ โดยเฉพาะการสูบบุหรี่ต่อเนื่องติดต่อกันเกิน 10-20 ปี");
		                    matchingResults.get(item).add("จะทำให้เพิ่มแนวโน้มในการเป็นโรคนี้ได้มากถึงร้อยละ 90 โดยจะนำไปสู่ภาวะหายใจล้มเหลวจนเสียชีวิต อีกทั้งเป็นโรคที่มีค่าใช้จ่ายสูงในการรักษา");
		                   
		                    break;
		                case "โรคหลอดเลือดสมองและหัวใจ":
		                    matchingResults.get(item).add("รูปแบบพฤติกรรมการบริโภคอาหารของคนไทยได้เปลี่ยนแปลงไปจากเดิมอย่างมาก");
		                    matchingResults.get(item).add("สังเกตได้จากอาหารยอดฮิตส่วนใหญ่มักประกอบไปด้วยแป้ง น้ำตาล และไขมันเป็นหลัก");
		                    matchingResults.get(item).add("อีกทั้งมีรสชาติจัดจ้าน หวานจัด เค็มจัด เผ็ดจัด รวมถึงสาเหตุอื่น ๆ อย่างเช่น การไม่ออกกำลังกาย การสูบบุหรี่จัด ความคร่ำเคร่งในการทำงาน เป็นต้น");
		                    matchingResults.get(item).add("ทำให้คนไทยเป็นโรคที่เกี่ยวกับหัวใจและหลอดเลือดกันมากขึ้น ซึ่งผู้ป่วยส่วนใหญ่มักจะเสียชีวิตฉับพลัน");
		                    break;
		                case "โรคมะเร็ง":
		                    matchingResults.get(item).add("เป็นโรคที่ครองแชมป์เพชฌฆาตคร่าชีวิตคนไทยมานานหลายปี ทั้งมะเร็งตับ มะเร็งปอด มะเร็งมดลูก มะเร็งลำไส้ มะเร็งเต้านม");
		                    matchingResults.get(item).add("ซึ่งในแต่ละปีจะมีคนไทยที่เสียชีวิตจากโรคนี้มากถึง 50,000 คน");
		                    matchingResults.get(item).add("ละยังคงมีแนวโน้มเพิ่มสูงขึ้นเรื่อย ๆ โรคมะเร็งเหล่านี้ล้วนแล้วแต่มีสาเหตุจาการใช้ชีวิตประจำวันแบบผิด ๆ ของคนยุคใหม่");
		                 
		                    break;
		                case "โรคความดันโลหิต":
		                    matchingResults.get(item).add("เกิดขึ้นได้จากหลายสาเหตุ ทั้งจากการถ่ายทอดทางพันธุกรรม พฤติกรรมการใช้ชีวิต หรือการละเลยการดูแลสภาวะสุขภาพ");
		                    matchingResults.get(item).add("เช่น การดื่มแอลกอฮอล์ การสูบบุหรี่ การนอนดึก ขาดการออกกำลังกาย การกินอาหารที่มีรสชาติเค็มจัดติดต่อกันเป็นเวลานาน");
		                    matchingResults.get(item).add("โดยโรคความดันโลหิตมักเป็นภัยเงียบที่แฝงมาจากโรคอื่น ๆ อาทิ โรคหัวใจ หลอดเลือดในสมอง โรคเบาหวาน โรคอ้วน เป็นต้น");
		                  
		                    break;
		                case " โรค COVID-19 ":
		                    matchingResults.get(item).add("โรค COVID-19 เกิดจากเชื้อโคโรนาไวรัส สายพันธุ์ใหม่ 2019 เชื่อว่ามีพาหะเป็นค้างคาว");
		                    matchingResults.get(item).add("โดยโรคนี้จะก่อให้เกิดโรคทางเดินหายใจอักเสบในคน และติดต่อกันได้ผ่านการสัมผัสสารคัดหลั่งของผู้ป่วย ");
		                    matchingResults.get(item).add("อาการจะคล้ายๆ อาการของไข้หวัด คือ มีไข้สูง ไอ จาม มีน้ำมูก เจ็บคอ แน่นหน้าอก เหนื่อยหอบ");
		                    matchingResults.get(item).add("และหากมีอาการหนักจะมีภาวะปอดบวม ปอดอักเสบขั้นรุนแรง เสี่ยงต่อการเสียชีวิตได้");
		                    break;
		                case "วัณโรคที่มากับอากาศ":
		                    matchingResults.get(item).add("โรคปอดอักเสบเป็นโรคระบบทางเดินหายใจชนิดเดียวกันซึ่งเกิดจากเชื้อแบคทีเรียชื่อว่า มายโคแบคทีเรียม ทูเบอร์คูโลสิส (Mycobacterium tuberculosis)");
		                    matchingResults.get(item).add("ซึ่งมีแนวโน้มของผู้ป่วยรายใหม่ เพิ่มสูงขึ้นถึงปีละ 70%");
		                    matchingResults.get(item).add("โรคนี้เป็นโรคเรื้อรังและสามารถติดต่อสู่คนอื่นได้ง่ายโดยเฉพาะจากสารคัดหลั่งต่าง ๆ ที่ออกจากร่างกาย");
		                    matchingResults.get(item).add("และผู้ที่เป็นนั้นมักจะทรุดลงได้เร็วถ้ามีการสูบุหรี่, ดืมเครื่องดื่มแอลกฮอล์จัด หรือในผู้ที่เป็นโรคร้ายก็จะมี");
		                    matchingResults.get(item).add("วัณโรคเป็นโรคแทรกซ้อนได้ง่าย ๆ อาการของผู้ที่เป็นวัณโรคจะไอแห้งติดต่อกันเกิน 3 สัปดาห์ และมีเลือดปนออกมาด้วย");
		                    break;
		                case "โรคปอดเรื้อรัง":
		                    matchingResults.get(item).add("โรคปอดเรื้อรังสามารถกลายเป็นโรคถุงลมโป่งพองที่เกิดจากเซลล์เม็ดเลือดขาวชนิดต่าง ๆ เข้ามาอยู่ในชั้นเยื่อบุและชั้นใต้เยื่อบุมากขึ้น");
		                    matchingResults.get(item).add("ต่อมผลิตเมือกที่อยู่ใต้ชั้นเยื่อบุจะมีขนาดใหญ่ขึ้นและผลิตเมือกเข้าสู่หลอดลมจนทำให้เซลล์ที่ทำหน้าที่กวัดกวาดสิ่งสกปรกโดนเมือกเคลือบ");
		                    matchingResults.get(item).add("แล้วนำพาเอาเมือกจากจุดอื่นๆ เข้าสู่หลอดลมอย่างล้นหลามและถุงลมก็จะถูกทำลายจนหายไป");
		                    matchingResults.get(item).add("สาเหตุหลักเกิดจากการสูบบุหรี่, การหายใจเอาละอองสารเคมีเข้าไปนานๆ จนเกิดการสะสม, มลภาวะในอากาศและโรคทางพันธุกรรมบางชนิดก็ทำให้เกิดโรคนี้ได้");
		                    matchingResults.get(item).add("มีอาการหน้าอกบวมปูด, เหนื่อยง่าย แค่เดินก็เหนื่อยได้, หายใจมีเสียงถ้ารักษาหรือดูแลตัวเองไม่ดีก็อาจจะเกิดภาวะหายใจล้มเหลวจนเสียชีวิต");
		                    matchingResults.get(item).add("ควรดูแลตัวเอง ด้วยการงดสูบบุหรี่เพื่อตัวเองและคนในบ้าน, หลีกเลี่ยงการสูดดมควันพิษต่างๆ จากมลภาวะรอบข้างก็ด้วย");
		                   break;
		                case "โรคภูมิแพ้":
		                    matchingResults.get(item).add("ภูมิแพ้ที่มีหลากหลายชนิด สาเหตุมาจากอาการแพ้ต่อสารต่างๆแม้กระทั่งอากาศและอาหารที่ทำให้เกิดการอักเสบเรื้อรังต่อเยื่อบุต่างๆในร่างกาย ");
		                    matchingResults.get(item).add("ซึ่งอาจเกิดจากพันธุกรรมและสภาพแวดล้อมรอบตัวเรามีหลากหลายอาการเพราะอาการแพ้อาจเกิดแทบจะทุกส่วนของร่างกายทั้งการจาม, หายใจลำบากในเวลากลางคืน");
		                    matchingResults.get(item).add("ส่วนใหญ่อาการจะเกิดเมื่อเจอสิ่งแปลกปลอมเข้าสู่ร่างกายเพราะในปัจจุบันไม่ว่าจะอาหารหรืออากาศก็ล้วนแล้วแต่มีสิ่งแปลกปลอมเจือปนด้วยกันทั้งนั้น");
		                    matchingResults.get(item).add("คนที่เป็นโรคนี้จึงมีอัตราเพิ่มสูงขึ้นในทุกปีในเบื้องต้นควรดูแลตัวเองโดยการนอนหลับและตื่นให้เป็นเวลา, ออกกำลังกาย, รับประทานอาหารที่สดใหม่เสมอ, ที่นอน ");
		                    matchingResults.get(item).add("หมอนและผ้าห่มต้องซักให้สะอาดอยู่ตลอด หลีกเลี่ยงอาหารที่ก่อให้เกิดอาการ เช่น อาหารทะเล, ขนมปัง, นมวัว เป็นต้น และเมื่อมีอาการให้หายาต้าน \"ฮีสตามีน\" "
		                    +" เช่น คลอเฟนนิรามินก็จะแก้อาการเบื้อต้นได้แต่ถ้าอาการหนักขึ้นก็ให้หยุดยาแล้วพบแพทย์ทันที");
		                    break;
		                case "โรคระบบกล้ามเนื้อ เส้นเอ็นอักเสบ":
		                    matchingResults.get(item).add("ไม่ว่าจะเกิดจากการก้มดูโทรศัพท์, การทำงานหน้าจอคอม หรือการเล่นกีฬาและจะปวดรุนแรงมากขึ้นเมื่อมีการถูกกระตุ้น");
		                    matchingResults.get(item).add("ถ้าในคนปกติไม่มีอาการปวดแต่คนที่เป็นจะปวดและเมื่อเกิดอาการปวดจริงจังก็จะปวดมากกว่าคนปกติถึง 2 เท่า");
		                    matchingResults.get(item).add("เมื่อเป็นแล้วอาการเริ่มหนักขึ้นก็จะพ่วงเอาสารพัดโรคทางจิตมาด้วย คือ โรคซึมเศร้า, วิตกกังวล, นอนไม่หลับ และอ่อนเพลีย");
		                    matchingResults.get(item).add("ผู้ที่เป็นสังเกตได้ง่ายๆ คือจะมีอาการบวมตามข้อมือ ข้อเท้า, ไวต่อเสียงและแสง, ปวดศีรษะมากในช่วงเช้า, ปัสสาวะบ่อย,"+
		                    " ปวดท้องเรื้อรังและท้องเสียง่ายติดต่อกันเป็นเวลานาน 3 เดือน และถ้ามีอาการปวดหัวรุนแรงขึ้นก็ให้รีบไปพบแพทย์ทันที");
		                    break;
		                case "โรคไต":
		                    matchingResults.get(item).add("พูดถึงโรคที่คนไทยเสียชีวิตมากที่สุดจะไม่พูดถึงโรคนี้ไม่ได้ โรคไต โรคที่กินชีวิตของหนุ่มสาวให้จากไปก่อนวัยอันควร");
		                    matchingResults.get(item).add("เกิดขึ้นมาจากพฤติกรรมการกินเช่นกันคนที่กินเค็มมากๆ เสี่ยงกว่าคนที่ไม่ค่อยกินเค็ม คนที่บริโภคอาหารสำเร็จรูปบ่อยๆ ก็จะเสี่ยงมากกว่าคนที่กินอาหารปรุงสุก");
		                    matchingResults.get(item).add("ความเค็มมีโซเดียมและโพแทสเซียมสูง ทำให้ระบบไตทำงานน้อยลง ทำให้เกิดอาการอื่น ๆ ตามมาเช่นกล้ามเนื้ออ่อนแรง หัวใจเต้นเร็วผิดปกติ");
		                    matchingResults.get(item).add("การป้องกันโรคนี้เน้นหนักไปที่การกิน หลีกเลี่ยงการกินอาหารรสจัดหรือสำเร็จรูปจะช่วยป้องกันโรคนี้ได้ดีที่สุด");
		                    break;
		                case "โรคตับแข็ง":
		                    matchingResults.get(item).add("เป็นภาวะที่เกิดขึ้นของโรคตับซึ่งทำให้เกิดการทำลายตับและการอักเสบ เมื่อมีการอักเสบเกิดขึ้นซ้ำๆ");
		                    matchingResults.get(item).add("จะทำให้เนื้อเยื่อที่ดีของตับถูกทำลายเกิดแผลเป็นและพังผืดขึ้นมาแทนที่เนื้อตับจึงสูญเสียความยืดหยุ่นและกลายเป็นตับแข็งในที่สุด “ตับแข็ง” ไม่ดื่มก็เป็นได้");
		                    matchingResults.get(item).add("สาเหตุการเกิดโรคตับแข็ง นอกจากการดื่มเหล้า มีการติดเชื้อไวรัสตับอักเสบบี, ซี, ดี การรับประทานยาหรือสมุนไพรบางชนิดติดต่อกันเป็นเวลานาน ภาวะไขมันเกาะตับ\n"
		                    		+ "ภาวะดีซ่านจากท่อน้ำดีอุดตัน ตับอักเสบจากภูมิต้านทานตนเองที่ทำงานไม่ปกติ ภาวะหัวใจล้มเหลวหลายครั้งติดต่อกัน การได้รับสารพิษที่มีผลต่อตับ");
		                    matchingResults.get(item).add("ภาวะตับแข็งยังอาจส่งผลต่อการไหลเวียนของเลือดทำให้ความดันในเส้นเลือดสูงขึ้นส่งผลให้ผู้ป่วยมีภาวะเกล็ดเลือดต่ำเลือดออกง่าย"
		                    		+" ม้ามโต ขาบวม มีน้ำในช่องท้อง มีการเปลี่ยนแปลงตามผิวหนัง คือ มีจุดเล็กๆ แดงๆ เกิดขึ้น");
		                    break;
		                case "โรคไทรอยด์เป็นพิษ":
		                    matchingResults.get(item).add("คือภาวะที่ต่อมไทรอยด์สร้างฮอร์โมนออกมามากเกินไป ทำให้ระบบเผาผลาญทำงานมากขึ้น ");
		                    matchingResults.get(item).add("ซึ่งเป็นสาเหตุที่ทำให้น้ำหนักลดลงอย่างรวดเร็วแบบผิดปกติ หัวใจเต้นเร็วหรือเต้นผิดปกติ");
		                    matchingResults.get(item).add("หงื่อออกง่าย และหงุดหงิด ฉุนเฉียว เป็นต้น ซึ่งโรคไทรอยด์อาจแบ่งได้เป็น 2 ชนิด คือ \n ");
		                    matchingResults.get(item).add("ไฮเปอร์ไทรอยด์ คือโรคไทรอยด์ที่มีระดับการทำงานของต่อมไทรอยด์มากเกินไป ระดับฮอร์โมนไทรอยด์ในเลือดสูงเกินไป มีการทำงานของเซลล์ในร่างกายเร็วกว่าปกติ\n");
		                    matchingResults.get(item).add("ไฮโปไทรอยด์ คือโรคไทรอยด์ที่มีระดับการทำงานของต่อมไทรอยด์ที่น้อยเกินไป ระดับฮอร์โมนไทรอยด์ในเลือดต่ำเกินไป มีการทำงานของเซลล์ในร่างกายช้ากว่าปกติอาการที่ส่งผลต่อร่างกายคือ \n"
		                    +" น้ำหนักเพิ่มขึ้นอย่างรวดเร็ว ขี้หนาว ง่วงนอน อ่อนเพลีย ผมร่วง ผิวแห้ง ซึมเศร้า เป็นตะคริวง่าย หัวใจเต้นช้า ท้องผูก รอบตาบวม หน้าบวม ตัวบวม ต่อมไทรอยด์โต");
		                    break;
		                case "โรคเกาต์":
		                    matchingResults.get(item).add("มักพบในชายสูงอายุมากกว่าหญิง ทำให้เกิดอาการปวดตามข้อ ซึ่งเกิดจากการมีกรดยูริกสะสมในร่างกายเป็นจำนวนมาก โดยเฉพาะตามข้อ");
		                    matchingResults.get(item).add("ซึ่งคนแต่ละวัยมีระดับกรดยูริกในเลือดที่แตกต่างกัน เช่น ผู้หญิงวัยก่อนหมดประจำเดือนจะมีระดับกรดยูริกในเลือดสูง");
		                    matchingResults.get(item).add("กว่าคนในวัยอื่นๆ หรือการรับประทานอาหารที่มีสารพิวรีนสูง เช่น เนื้อเป็ด เนื้อไก่ เครื่องในสัตว์ ถั่วต่างๆ ก็เป็นสาเหตุสำคัญในการเกิดกรดยูริกในร่างกายมากเกินไป");
		               
		                    break;
		                case "โรคข้อเข่าเสื่อม":
		                    matchingResults.get(item).add("มักพบในผู้หญิงสูงอายุมากกว่าชายถึง 2 เท่า เกิดการใช้ข้อเข่ามานาน การรับน้ำหนักตัวที่มากเกินไป");
		                    matchingResults.get(item).add("อาการที่พบคือ การเจ็บปวดของข้อและข้อบวม อาการข้อขัด หรือรูปร่างขาโก่งผิดปกติ");
		                    matchingResults.get(item).add("เหยียดขาได้ไม่สุด โดยเฉพาะคนที่เล่นกีฬาหนักๆ หรือคนที่มีน้ำหนักตัวมากอาจเป็นตัวส่งเสริมให้ข้อเข่าเสื่อมเร็วยิ่งขึ้น");
		                    
		                    break;
		                case "โรคกระดูกพรุน":
		                    matchingResults.get(item).add("โรคนี้มักเกิดกับผู้หญิงสูงอายุ โดยเฉพาะวัยหมดประจำเดือน เป็นภาวะที่กระดูกมีความหนาแน่นน้อยลง");
		                    matchingResults.get(item).add("หายใจลำบาก หน้ามือ ใจสั่น คลื่นไส้บ่อย ๆ เหนื่อยง่าย เหงื่อออกเยอะ ชีพจรเต้นเร็ว หรือช้าเร็วสลับกัน");
		                    matchingResults.get(item).add("สาเหตุของการเกิดโรคนี้ยังคงเป็นพฤติกรรมการบริโภคอาหารที่เต็มไปด้วยไขมันเลว ป็นโรคความดันโลหิตสูงมาก่อน ");
		                    matchingResults.get(item).add("ดื่มเหล้า สูบบุหรี่ น้ำหนักมากเกินไปและยังถ่ายทอดจากพันธุกรรมอีกด้วยวิธีที่ดีที่สุดในการป้องกันโรคนี้คือการดูแลสุขภาพอนามัยใส่ใจในการกินนั่นเอง");
		                    break;
		                case "โรคอ้วนลงพุง":
		                    matchingResults.get(item).add("โรคร้ายที่สัมพันธ์กับปาก เน้นความอร่อยและความอยากเป็นหลัก จนทำให้กินอาหารปริมาณมากจนเกินจากความต้องการพื้นฐานของร่างกาย");
		                    matchingResults.get(item).add("ส่งผลให้เกิดไขมันสะสม โดยเฉพาะอย่างยิ่งกับเมนูอาหารจำพวกของทอด ของมัน ขนมเบเกอรี และเครื่องดื่มที่มีน้ำตาลสูง");
		                    matchingResults.get(item).add("ซึ่งโรคอ้วนลงพุงนอกจากจะทำให้สูญเสียความมั่นใจแล้ว ยังมีโอกาสเสี่ยงเกิดโรคแทรกซ้อนมากมาย");
		                   
		                    break;
		                case "โรคอาหารเป็นพิษ":
		                    matchingResults.get(item).add("เป็นโรคทางเดินอาหารที่พบได้บ่อยมาก มักเกิดขึ้นอย่างรวดเร็วหลังทานอาหาร หรือดื่มน้ำที่ปนเปื้อนเชื้อโรคเข้าไป");
		                    matchingResults.get(item).add("ผู้ป่วยจะมีไข้ต่ำ ๆ อาเจียน คลื่นไส้ อาจมีอุจจาระร่วงด้วย หากเป็นหนักก็อาจถ่ายเป็นมูกเลือด");
		                    matchingResults.get(item).add("ถ้าเป็นมากต้องได้รับน้ำเกลือเสริม อาจดื่มหรือให้ทางเส้นเลือดแล้วแต่ความรุนแรงของอาการ");
		                    matchingResults.get(item).add("อาจให้กินยา แก้ไข้หวัด เสริมด้วย มักพบเชื้อในอาหารที่ปรุงสุก ๆ ดิบ ๆ");
		                    break;
		                case "โรคอุจจาระร่วง":
		                    matchingResults.get(item).add("โรคอุจจาระร่วง เป็นโรคติดต่อทางอาหารและน้ำที่สำคัญที่สุด เกิดจากเชื้อโรคได้หลายชนิด");
		                    matchingResults.get(item).add("ไม่ว่าจะเป็นเชื้อแบคทีเรีย เชื้อไวรัส โปรโตซัว และหนอนพยาธิ สามารถติดต่อได้โดยการรับประทานอาหารหรือดื่มน้ำที่มีเชื้อปนเปื้อนเข้าไป");
		                    matchingResults.get(item).add("ผู้ป่วยจะถ่ายอุจจาระเหลวมากกว่า 3 ครั้งต่อวัน หรือถ่ายเป็นน้ำหรือมูกปนเลือด หากไม่ได้รับการรักษาที่ถูกต้องอย่างทันท่วงที");
		                    matchingResults.get(item).add("ร่างกายจะสูญเสียน้ำ และเกลือแร่ จนอาจทำให้ผู้ป่วยเกิดภาวะช็อก หมดสติ และเสียชีวิตได้");
		                    break;
		                case "อหิวาตกโรค":
		                    matchingResults.get(item).add("เป็นโรคติดต่อที่มีสาเหตุมาจากมีเชื้อแบคทีเรีย Vibrio Cholerae เข้าสู่ร่างกาย ");
		                    matchingResults.get(item).add("ผ่านการทานอาหารหรือน้ำที่มีเชื้ออหิวาตกโรคปะปนอยู่ เช่น อาหารที่มีแมลงวันตอม และอาหารสุก ๆ ดิบ ๆ");
		                    matchingResults.get(item).add("เชื้อแบคทีเรียจะเข้าไปอยู่ในบริเวณลำไส้ และสร้างสารพิษที่ทำปฏิกิริยากับเยื่อบุผนังลำไส้ ทำให้ถ่ายออกมาเป็นจำนวนมาก");
		                  
		                    break;
		                case "โรคหอบ หืด":
		                    matchingResults.get(item).add("ผู้ป่วยโรคหอบ หืด หากได้รับสารอาหาร หรือโภชนาการที่ไม่ดีต่อสุขภาพอย่างต่อเนื่อง");
		                    matchingResults.get(item).add("ก็อาจส่งผลให้อาการของโรครุนแรงขึ้นได้ โดยอาหารที่ควรเลี่ยง");
		                    matchingResults.get(item).add("มีดังนี้\r\n"
		                    		+ "\r\n"
		                    		+ "อาหารที่ก่อให้เกิดการแพ้\r\n"
		                    		+ "อาหารที่มีแคลอรี่สูง\r\n"
		                    		+ "น้ำอัดลมเครื่องดื่มแอลกอฮอล์ ");
		                   break;
		                case "โรคกระเพาะอาหาร":
		                    matchingResults.get(item).add("ผู้ป่วยโรคกระเพาะต้องระมัดระวังในการเลือกรับประทานอาหารมากเป็นพิเศษเพราะอาหารหลายชนิดทำให้กระเพาะอาหารระคายเคืองได้");
		                    matchingResults.get(item).add("จนเกิดอาการปวดท้อง ท้องอืด แสบร้อนกลางอก หรืออาหารไม่ย่อยตามมา");
		                    matchingResults.get(item).add("โดยอาการเหล่านี้มักเป็นในช่วงท้องว่าง ก่อนมื้ออาหาร ตอนกลางคืน ขณะนอนหลับ หรือเกิดภายหลังรับประทานยาที่มีฤทธิ์ระคายเคืองต่อผิวกระเพาะอาหาร ");
		                    matchingResults.get(item).add("ในบางครั้งอาการปวดท้องดังกล่าวอาจดีขึ้นได้ชั่วขณะเมื่อดื่มนม รับประทานอาหาร หรือ ใช้ยาลดกรด");
		                    break;

		                case "โรคหัวใจขาดเลือด":
		                    matchingResults.get(item).add("อาการที่บ่งบอกว่าคุณอาจจะเป็นโรคนี้คือ มีอาการจุกแน่นบริเวณหน้าอกผิดปกติ");
		                    matchingResults.get(item).add("หายใจลำบาก หน้ามือ ใจสั่น คลื่นไส้บ่อย ๆ เหนื่อยง่าย เหงื่อออกเยอะ ชีพจรเต้นเร็ว หรือช้าเร็วสลับกัน");
		                    matchingResults.get(item).add("สาเหตุของการเกิดโรคนี้ยังคงเป็นพฤติกรรมการบริโภคอาหารที่เต็มไปด้วยไขมันเลว ป็นโรคความดันโลหิตสูงมาก่อน ");
		                    matchingResults.get(item).add("ดื่มเหล้า สูบบุหรี่ น้ำหนักมากเกินไปและยังถ่ายทอดจากพันธุกรรมอีกด้วยวิธีที่ดีที่สุดในการป้องกันโรคนี้คือการดูแลสุขภาพอนามัยใส่ใจในการกินนั่นเอง");
		                    break;
		                default:
		                    // กรณีไม่เข้า case ไหนเลย
		                    matchingResults.get(item).add("ข้อมูลไม่พร้อมในขณะนี้");
		            }
		        }
		    }

		    return matchingResults;
		}
}



