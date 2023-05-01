package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.AccountDAO;
import entity.Emp;

public class Feature_UI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnCreateBill, btnBills, btnProduct, btnEmpManage, btnLogin, btnLogOut, btnCustomer;
	private JLabel lblRightTitle, lblUserName, lblPassword;
	private Emp curAccount;

	public Feature_UI(Emp account) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 524);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		curAccount = account;

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnLeft = new JPanel();
		pnLeft.setBackground(new Color(0, 128, 255));
		pnLeft.setBorder(new LineBorder(new Color(0, 128, 255), 3));
		pnLeft.setBounds(10, 10, 435, 467);
		contentPane.add(pnLeft);
		pnLeft.setLayout(null);

		JLabel lblLeftTitle = new JLabel("Bảng điều khiển chính");
		lblLeftTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftTitle.setBounds(10, 0, 415, 56);
		pnLeft.add(lblLeftTitle);
		lblLeftTitle.setForeground(new Color(255, 255, 255));
		lblLeftTitle.setFont(new Font("Tahoma", Font.BOLD, 26));

		btnCreateBill = new JButton("Lập hóa đơn");
		btnCreateBill.setBackground(new Color(255, 255, 255));
		btnCreateBill.setBounds(56, 66, 316, 56);
		pnLeft.add(btnCreateBill);
		btnCreateBill.setFont(new Font("Tahoma", Font.BOLD, 20));

		btnBills = new JButton("Thông tin hóa đơn");
		btnBills.setBackground(new Color(255, 255, 255));
		btnBills.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBills.setBounds(56, 148, 316, 56);
		pnLeft.add(btnBills);

		btnProduct = new JButton("Thông tin sản phẩm");
		btnProduct.setBackground(new Color(255, 255, 255));
		btnProduct.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnProduct.setBounds(56, 230, 316, 56);
		pnLeft.add(btnProduct);

		btnEmpManage = new JButton("Quản lý nhân viên");
		btnEmpManage.setBackground(new Color(255, 255, 255));
		btnEmpManage.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEmpManage.setBounds(56, 312, 316, 56);
		pnLeft.add(btnEmpManage);

		btnCustomer = new JButton("Quản lý khách hàng");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCustomer.setBackground(Color.WHITE);
		btnCustomer.setBounds(56, 394, 316, 56);
		pnLeft.add(btnCustomer);

		JPanel pnRight = new JPanel();
		pnRight.setBackground(new Color(0, 128, 255));
		pnRight.setBorder(new LineBorder(new Color(0, 128, 255), 3));
		pnRight.setBounds(473, 10, 435, 467);
		contentPane.add(pnRight);
		pnRight.setLayout(null);

		lblUserName = new JLabel("tài khoản");
		lblUserName.setForeground(new Color(255, 255, 255));
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserName.setBounds(42, 115, 128, 32);
		pnRight.add(lblUserName);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUserName.setBounds(185, 110, 194, 40);
		pnRight.add(txtUserName);
		txtUserName.setColumns(10);

		lblPassword = new JLabel("mật khẩu");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(42, 215, 128, 32);
		pnRight.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBounds(185, 209, 194, 40);
		pnRight.add(txtPassword);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(42, 316, 172, 50);
		pnRight.add(btnLogin);

		lblRightTitle = new JLabel("Đăng nhập");
		lblRightTitle.setForeground(new Color(255, 255, 255));
		lblRightTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblRightTitle.setBounds(10, 0, 443, 56);
		pnRight.add(lblRightTitle);

		btnLogOut = new JButton("Đăng xuất");
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogOut.setBounds(253, 316, 172, 50);
		pnRight.add(btnLogOut);

		contentPane.setVisible(true);
		btnCreateBill.addActionListener(this);
		btnBills.addActionListener(this);
		btnProduct.addActionListener(this);
		btnEmpManage.addActionListener(this);
		btnCustomer.addActionListener(this);
		btnLogin.addActionListener(this);
		btnLogOut.addActionListener(this);
		switchBtnByRole();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreateBill)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new CreateBill_UI(curAccount).setVisible(true);
		} else if (o.equals(btnBills)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new BillList_UI(curAccount).setVisible(true);
		} else if (o.equals(btnProduct)) {
			this.setVisible(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			new Product_UI(curAccount).setVisible(true);
		} else if (o.equals(btnEmpManage)) {
			this.setVisible(false);
			new EmpManage(curAccount).setVisible(true);
		} else if (o.equals(btnCustomer)) {
			this.setVisible(false);
			new Customer_UI(curAccount).setVisible(true);
		} else if (o.equals(btnLogin)) {
			if (isEmpty(txtUserName, lblUserName) || isEmpty(txtPassword, lblPassword)) {
			} else {
				Emp emp = AccountDAO.getInstance().login(txtUserName.getText(), txtPassword.getText());
				if (emp.getEmpId() == 0) {
					JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không đúng");
				} else {
					curAccount = emp;
					switchBtnByRole();
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
				}
			}
		} else if (o.equals(btnLogOut)) {
			curAccount = new Emp();
			JOptionPane.showMessageDialog(null, "Đăng xuất thành công");
			isLogin();
		}
	}

	public boolean isEmpty(JTextField txt, JLabel lbl) {
		if (txt.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy nhập vào " + lbl.getText());
			txt.requestFocus();
			return true;
		}
		return false;
	}

	public boolean isLogin() {
		if (curAccount.getEmpId() != 0) {
			txtUserName.setText(curAccount.getUsername());
			txtUserName.setEnabled(false);
			txtPassword.setEnabled(false);
			btnLogin.setEnabled(false);
			btnLogOut.setEnabled(true);
			return true;
		} else {
			txtUserName.setText("");
			txtPassword.setText("");
			txtUserName.setEnabled(true);
			txtPassword.setEnabled(true);
			btnCreateBill.setEnabled(false);
			btnBills.setEnabled(false);
			btnProduct.setEnabled(false);
			btnEmpManage.setEnabled(false);
			btnCustomer.setEnabled(false);
			btnLogin.setEnabled(true);
			btnLogOut.setEnabled(false);
			return false;
		}
	}

	public void switchBtnByRole() {
		if (isLogin()) {
			if (curAccount.isRole() == true) {
				btnCreateBill.setEnabled(true);
				btnBills.setEnabled(false);
				btnProduct.setEnabled(false);
				btnEmpManage.setEnabled(false);
				btnCustomer.setEnabled(false);
			} else {
				btnCreateBill.setEnabled(false);
				btnBills.setEnabled(true);
				btnProduct.setEnabled(true);
				btnEmpManage.setEnabled(true);
				btnCustomer.setEnabled(true);
			}
		}
	}
}
