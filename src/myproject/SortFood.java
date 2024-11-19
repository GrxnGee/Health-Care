package myproject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;


public class SortFood extends JFrame {
	private JTable table;
	private static String username;
	private static String gender;

	public SortFood(String username) {
		setTitle("Food");
		setSize(865, 735);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 849, 39);
		getContentPane().add(panel);
		panel.setBackground(new Color(150, 223, 213));
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("หน้าหลัก");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 10, 100, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    	 Home frame = new Home(username, gender );
	                frame.setVisible(true);
		    }
		});
		panel.add(btnNewButton_1);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		String[] columnHeaders = {"", "", ""};
		DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);
		model.addRow(new Object[]{"ชื่ออาหาร", "แคลลอรี่", "ประเถท"});
		table = new JTable(model);
		table.setRowHeight(30); 
		table.setBounds(10, 107, 829, 578);
		getContentPane().add(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 107, 829, 578); // ระบุตำแหน่งและขนาดของ JScrollPane
		getContentPane().add(scrollPane); // เพิ่ม JScrollPane เข้าไปใน container ของ JFrame ของคุณ





		String[] foodTypes = {"ของคาว", "ของหวาน", "ทั้งหมด"};
		JComboBox comboBox = new JComboBox(foodTypes);
		comboBox.setBounds(425, 53, 163, 30);
		getContentPane().add(comboBox);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));


		JButton btnNewButton = new JButton("ค้านหา");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				model.addRow(new Object[]{"ชื่ออาหาร", "แคลลอรี่", "ประเถท"});
				String selectedFtype = (String) comboBox.getSelectedItem();
				if (selectedFtype.equals("ทั้งหมด")) {
					try {
						String SQLAllFood = "SELECT `F_Name`, `F_Cal`, `F_Type` FROM `Food`";
						PreparedStatement stAllFood = connetData.getConnection().prepareStatement(SQLAllFood);
						ResultSet rsAllFood = stAllFood.executeQuery();

						while (rsAllFood.next()) {
							String Fname = rsAllFood.getString("F_Name");
							String Fcal = rsAllFood.getString("F_Cal");
							String Ftype = rsAllFood.getString("F_Type");

							model.addRow(new Object[]{Fname, Fcal, Ftype});
						}

					} catch (SQLException ex) {
						Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error retrieving food data", "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
				    try {
				        String SQLSelectedType = "SELECT `F_Name`, `F_Cal`, `F_Type` FROM `Food` WHERE `F_Type` = ?";
				        PreparedStatement stSelectedType = connetData.getConnection().prepareStatement(SQLSelectedType);
				        stSelectedType.setString(1, selectedFtype);

				        ResultSet rsSelectedType = stSelectedType.executeQuery();

				        while (rsSelectedType.next()) {
				            String Fname = rsSelectedType.getString("F_Name");
				            String Fcal = rsSelectedType.getString("F_Cal");
				            String Ftype = rsSelectedType.getString("F_Type");

				            model.addRow(new Object[]{Fname, Fcal, Ftype});
				        }

				    } catch (SQLException ex) {
				        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Error retrieving food data", "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
				    }
				}


			}
		});
		btnNewButton.setBounds(600, 53, 94, 30);
		getContentPane().add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));




		setVisible(true);
	}


	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new SortFood(username));
	}
}