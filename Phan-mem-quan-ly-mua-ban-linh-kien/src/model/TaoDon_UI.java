package model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class TaoDon_UI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTable table_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table_2;
	private JButton btnLpHan, btnThngTinHa, btnNewButton_back, btnNewButton_1;
	private JPanel panel, panel_1, panel_2;
	private JLabel lblNewLabel, lblNewLabel_1;
    private JLayeredPane layeredPane;
    private JTextField textField_7;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChucNang_UI frame = new ChucNang_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TaoDon_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 524);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("LẬP ĐƠN HÀNG");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(317, 10, 250, 35);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 0, 128)));
		panel.setBounds(10, 48, 704, 200);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên KH:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 20, 103, 25);
		panel.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(104, 20, 278, 25);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số ĐT:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 55, 103, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa Chỉ:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 91, 103, 25);
		panel.add(lblNewLabel_1_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(104, 56, 278, 25);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(104, 91, 278, 25);
		panel.add(textField_5);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tên NV:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(10, 126, 103, 25);
		panel.add(lblNewLabel_1_2_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(104, 126, 278, 25);
		panel.add(textField_6);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mặt hàng:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(392, 20, 103, 25);
		panel.add(lblNewLabel_1_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(485, 16, 143, 33);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Ngày Lập:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(392, 91, 103, 25);
		panel.add(lblNewLabel_1_3_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(485, 94, 143, 19);
		panel.add(dateChooser);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Số Lượng:");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(392, 59, 103, 25);
		panel.add(lblNewLabel_1_3_2);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(485, 58, 103, 25);
		panel.add(textField_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(732, 55, 166, 193);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 10, 146, 36);
		panel_1.add(btnNewButton);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setBackground(new Color(255, 0, 0));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXa.setBounds(10, 56, 146, 36);
		panel_1.add(btnXa);
		
		JButton btnNewButton_1_1 = new JButton("Lưu");
		btnNewButton_1_1.setBackground(new Color(0, 0, 255));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1.setBounds(10, 102, 146, 36);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Xóa Trắng");
		btnNewButton_1_2.setBackground(new Color(128, 0, 255));
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_2.setBounds(10, 148, 146, 36);
		panel_1.add(btnNewButton_1_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 258, 869, 177);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 849, 199);
		panel_2.add(scrollPane);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"STT", "Mặt Hàng", "Giá", "Số Lượng", "Thành Tiền"
			}
		));
		scrollPane.setViewportView(table_2);
		
		btnNewButton_1 = new JButton("Back !");
		btnNewButton_1.setForeground(new Color(0, 0, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_1.setBounds(724, 445, 154, 32);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnNewButton_1)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new ChucNang_UI().setVisible(true);
		} 
	}
}
