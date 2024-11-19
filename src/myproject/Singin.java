package myproject;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Singin extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Singin frame = new Singin();
                    frame.setVisible(true);
                    frame.setTitle("Health Care");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Singin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 865, 735);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton btnNewButton = new JButton("เข้าสู่ระบบ");
        btnNewButton.setBounds(310, 418, 234, 39);
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(150, 223, 213));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose(); // ปิดหน้าปัจจุบัน
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                
            }
        });

        JButton btnNewButton_1 = new JButton("สมัครสมาชิก");
        btnNewButton_1.setBounds(310, 498, 234, 39);
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(150, 223, 213));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose(); // ปิดหน้าปัจจุบัน
                Register registerFrame = new Register();
                registerFrame.setVisible(true);
               
            }
        });

        // ตั้งค่า font สำหรับปุ่ม
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 18); // เปลี่ยน Tahoma เป็น font ที่รองรับภาษาไทย
        btnNewButton.setFont(thaiFont);
        btnNewButton_1.setFont(thaiFont);
        contentPane.setLayout(null);
        contentPane.add(btnNewButton);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Singin.class.getResource("/image/logo.png")));
        lblNewLabel.setBounds(150, 49, 503, 252);
        contentPane.add(lblNewLabel);
    }
}
