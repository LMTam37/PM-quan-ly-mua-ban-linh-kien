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
	private JButton btnCreateBill, btnLogin, btnLogOut, btnSystemManagement;
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
		btnCreateBill.setBounds(56, 80, 316, 150);
		pnLeft.add(btnCreateBill);
		btnCreateBill.setFont(new Font("Tahoma", Font.BOLD, 30));

		btnSystemManagement = new JButton("Quản trị hệ thống");
		btnSystemManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSystemManagement.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSystemManagement.setBackground(Color.WHITE);
		btnSystemManagement.setBounds(56, 270, 316, 150);
		pnLeft.add(btnSystemManagement);

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
		btnSystemManagement.addActionListener(this);
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
		} else if (o.equals(btnSystemManagement)) {
			this.setVisible(false);
			new SystemManagement(curAccount).setVisible(true);
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
			btnSystemManagement.setEnabled(false);
			btnLogin.setEnabled(true);
			btnLogOut.setEnabled(false);
			return false;
		}
	}

	public void switchBtnByRole() {
		if (isLogin()) {
			if (curAccount.isRole() == true) {
				btnCreateBill.setEnabled(true);
				btnSystemManagement.setEnabled(false);
			} else {
				btnCreateBill.setEnabled(false);
				btnSystemManagement.setEnabled(true);
			}
		}
	}
}
