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

public class ChucNang_UI extends JFrame implements ActionListener{

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
	private JButton btnLpHan, btnThngTinHa, btnThngTinLinhKien;
	private JPanel panel, panel_1, panel_2;
	private JLabel lblNewLabel, lblNewLabel_1;
    private JLayeredPane layeredPane;
    private JTextField textField_7;
	/**
	 * Launch the application.
	 */
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

	public ChucNang_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 524);
		getContentPane().setLayout(null);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_main = new JPanel();
		panel_main.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_main.setBounds(10, 10, 357, 467);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		
		JLabel lblNewLabel_main = new JLabel("Bảng điều khiển chính");
		lblNewLabel_main.setBounds(27, 10, 307, 56);
		panel_main.add(lblNewLabel_main);
		lblNewLabel_main.setForeground(new Color(255, 0, 0));
		lblNewLabel_main.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		
		btnLpHan = new JButton("Lập hóa đơn");
		btnLpHan.setBounds(20, 76, 316, 80);
		panel_main.add(btnLpHan);
		btnLpHan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		
		
		btnThngTinHa = new JButton("Thông tin hóa đơn");
		btnThngTinHa.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThngTinHa.setBounds(18, 171, 316, 80);
		panel_main.add(btnThngTinHa);
		
		
		btnThngTinLinhKien = new JButton("Thông tin sản phẩm");
		btnThngTinLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThngTinLinhKien.setBounds(18, 261, 316, 80);
		panel_main.add(btnThngTinLinhKien);
		
		JPanel panel_1_main = new JPanel();
		panel_1_main.setBorder(new LineBorder(new Color(255, 0, 128), 5));
		panel_1_main.setBounds(438, 10, 422, 467);
		contentPane.add(panel_1_main);
		panel_1_main.setLayout(null);
		
		JLabel lblNewLabel_1_main = new JLabel("TÀI KHOẢN:");
		lblNewLabel_1_main.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_main.setBounds(42, 110, 128, 32);
		panel_1_main.add(lblNewLabel_1_main);
		
		textField = new JTextField();
		textField.setBounds(185, 110, 194, 40);
		panel_1_main.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("MẬT KHẨU:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(42, 209, 128, 32);
		panel_1_main.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(185, 209, 194, 40);
		panel_1_main.add(textField_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ĐĂNG NHẬP ĐỂ SỬ DỤNG");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(100, 34, 241, 32);
		panel_1_main.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("Đăng Nhập");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(251, 280, 128, 50);
		panel_1_main.add(btnNewButton);
		
		contentPane.setVisible(true);
		btnLpHan.addActionListener(this);
		btnThngTinHa.addActionListener(this);
		btnThngTinLinhKien.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLpHan)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new TaoDon_UI().setVisible(true);
		} else if (o.equals(btnThngTinHa)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new DSDon_UI().setVisible(true);
		}else if(o.equals(btnThngTinLinhKien)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new LinhKien_UI().setVisible(true);
		}
		
	}
}
