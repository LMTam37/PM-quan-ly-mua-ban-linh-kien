package ui;

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

public class Feature_UI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JButton btnCreateBill, btnBills, btnProduct, btnEmpManage,btnLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feature_UI frame = new Feature_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Feature_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 524);
		getContentPane().setLayout(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnLeft.setBounds(10, 10, 357, 467);
		contentPane.add(pnLeft);
		pnLeft.setLayout(null);

		JLabel lblLeftTitle = new JLabel("Bảng điều khiển chính");
		lblLeftTitle.setBounds(27, 0, 307, 56);
		pnLeft.add(lblLeftTitle);
		lblLeftTitle.setForeground(new Color(0, 128, 255));
		lblLeftTitle.setFont(new Font("Dialog", Font.BOLD, 26));

		btnCreateBill = new JButton("Lập hóa đơn");
		btnCreateBill.setBounds(18, 76, 316, 80);
		pnLeft.add(btnCreateBill);
		btnCreateBill.setFont(new Font("Dialog", Font.BOLD, 20));

		btnBills = new JButton("Thông tin hóa đơn");
		btnBills.setFont(new Font("Dialog", Font.BOLD, 20));
		btnBills.setBounds(18, 173, 316, 80);
		pnLeft.add(btnBills);

		btnProduct = new JButton("Thông tin sản phẩm");
		btnProduct.setFont(new Font("Dialog", Font.BOLD, 20));
		btnProduct.setBounds(18, 270, 316, 80);
		pnLeft.add(btnProduct);

		 btnEmpManage = new JButton("Quản lý nhân viên");
		btnEmpManage.setFont(new Font("Dialog", Font.BOLD, 20));
		btnEmpManage.setBounds(18, 367, 316, 80);
		pnLeft.add(btnEmpManage);

		JPanel pnRight = new JPanel();
		pnRight.setBorder(new LineBorder(new Color(0, 128, 255), 5));
		pnRight.setBounds(438, 10, 422, 467);
		contentPane.add(pnRight);
		pnRight.setLayout(null);

		JLabel lblUserName = new JLabel("TÀI KHOẢN:");
		lblUserName.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblUserName.setBounds(42, 115, 128, 32);
		pnRight.add(lblUserName);

		txtUserName = new JTextField();
		txtUserName.setBounds(185, 110, 194, 40);
		pnRight.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblPassword = new JLabel("MẬT KHẨU:");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPassword.setBounds(42, 215, 128, 32);
		pnRight.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(185, 209, 194, 40);
		pnRight.add(txtPassword);

		JLabel lblRightTitle = new JLabel("ĐĂNG NHẬP ĐỂ SỬ DỤNG");
		lblRightTitle.setForeground(new Color(0, 128, 255));
		lblRightTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRightTitle.setBounds(100, 34, 241, 32);
		pnRight.add(lblRightTitle);

		btnLogin = new JButton("Đăng Nhập");
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin.setBounds(234, 280, 145, 50);
		pnRight.add(btnLogin);

		contentPane.setVisible(true);
		btnCreateBill.addActionListener(this);
		btnBills.addActionListener(this);
		btnProduct.addActionListener(this);
		btnEmpManage.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreateBill)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new CreateBill_UI().setVisible(true);
		} else if (o.equals(btnBills)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new BillList_UI().setVisible(true);
		} else if (o.equals(btnProduct)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new Product_UI().setVisible(true);
		} else if (o.equals(btnEmpManage)) {
			this.setVisible(false);
			new Emp().setVisible(true);
		}

	}
}
