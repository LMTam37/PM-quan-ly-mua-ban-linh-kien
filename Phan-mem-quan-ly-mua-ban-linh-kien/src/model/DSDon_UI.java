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

public class DSDon_UI extends JFrame implements ActionListener{

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
	private JButton btnLpHan, btnThngTinHa, btnNewButton_back;
	private JPanel panel, panel_1, panel_2;
	private JLabel lblNewLabel, lblNewLabel_1;
    private JLayeredPane layeredPane;
    private JTextField textField_7;
    private JTable table_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DSDon_UI frame = new DSDon_UI();
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
	public DSDon_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 524);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 35, 888, 155);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 868, 192);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Mã Đơn", "Tên KH", "SĐT", "Địa Chỉ", "Ngày Mua", "Tên Nhân Viên"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("THÔNG TIN HÓA ĐƠN");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(326, 0, 261, 44);
		getContentPane().add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 223, 888, 194);
		getContentPane().add(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 868, 192);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "Tên Linh Kiện", "Gía Sản Phẩm", "Số Lượng", "Thành Tiền"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		lblNewLabel_1 = new JLabel("Hóa đơn chi tiết");
		lblNewLabel_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(20, 191, 138, 33);
		getContentPane().add(lblNewLabel_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 0, 128), 2));
		panel_2.setBounds(10, 427, 868, 50);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng tiền cần thanh toán: ");
		lblNewLabel_2.setBounds(10, 10, 238, 23);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 19));
		panel_2.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(255, 0, 0));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textField_2.setBounds(268, 10, 291, 23);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_back = new JButton("Back!");
		btnNewButton_back.setForeground(new Color(0, 0, 128));
		btnNewButton_back.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_back.setBounds(740, 10, 118, 30);
		panel_2.add(btnNewButton_back);
		
		
		
		btnNewButton_back.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnNewButton_back)) {
			
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new ChucNang_UI().setVisible(true);
		}
		
	}
}
