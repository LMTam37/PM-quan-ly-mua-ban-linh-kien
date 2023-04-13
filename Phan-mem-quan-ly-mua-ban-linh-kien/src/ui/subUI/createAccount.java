package ui.subUI;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import dao.EmpManageDAO;
import entity.Emp;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class createAccount extends JDialog implements ActionListener {
	private JLabel lblEmpUsername, lblEmpName, lblPassword, lblConfirmPassword, lblRole;
	private JTextField txtEmpUserName, txtEmpName;
	private JPasswordField txtPassword, txtConfirmPassword;
	private JButton btnCreate, btnCancel;
	private JComboBox cbRole;

	public createAccount() {
		setTitle("Tạo tài khoản");
		setSize(350, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel pnMain = new JPanel();
		getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.setLayout(null);

		lblEmpUsername = new JLabel("Tên đăng nhập");
		lblEmpUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmpUsername.setBounds(10, 30, 148, 13);
		pnMain.add(lblEmpUsername);
		lblEmpName = new JLabel("Tên nhân viên");
		lblEmpName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmpName.setBounds(10, 70, 148, 13);
		pnMain.add(lblEmpName);

		lblPassword = new JLabel("Mật khẩu");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(10, 110, 148, 13);
		pnMain.add(lblPassword);

		lblConfirmPassword = new JLabel("Xác nhận mật khẩu");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(10, 150, 148, 13);
		pnMain.add(lblConfirmPassword);

		lblRole = new JLabel("Vai trò");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRole.setBounds(10, 190, 148, 13);
		pnMain.add(lblRole);

		txtEmpUserName = new JTextField();
		txtEmpUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmpUserName.setBounds(168, 27, 158, 19);
		pnMain.add(txtEmpUserName);
		txtEmpUserName.setColumns(10);

		txtEmpName = new JTextField();
		txtEmpName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmpName.setBounds(168, 67, 158, 19);
		pnMain.add(txtEmpName);
		txtEmpName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds(168, 107, 158, 19);
		pnMain.add(txtPassword);
		txtPassword.setColumns(10);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtConfirmPassword.setBounds(168, 147, 158, 19);
		pnMain.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);

		cbRole = new JComboBox();
		cbRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbRole.addItem("Nhân viên");
		cbRole.addItem("Quản lý");
		cbRole.setBounds(168, 186, 158, 21);
		pnMain.add(cbRole);

		btnCreate = new JButton("Tạo");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCreate.setBounds(50, 256, 93, 21);
		pnMain.add(btnCreate);

		btnCancel = new JButton("Hủy");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancel.setBounds(200, 256, 93, 21);
		pnMain.add(btnCancel);

		btnCreate.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	public Emp returnData() {
		boolean role = true;
		if (cbRole.getSelectedIndex() == 1) {
			role = false;
		} else {
			role = false;
		}
		Emp emp = new Emp(txtEmpUserName.getText(), txtEmpName.getText(), String.valueOf(txtPassword.getPassword()),
				role);
		return emp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreate)) {
			if (isEmpty(txtEmpUserName) || isEmpty(txtEmpName) || isEmpty(txtPassword)) {
				JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin");
			} else if (!String.valueOf(txtConfirmPassword.getPassword())
					.equals(String.valueOf(txtPassword.getPassword()))) {
				JOptionPane.showMessageDialog(null, "Các mật khẩu đã nhập không khớp", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				txtConfirmPassword.requestFocus();
				txtConfirmPassword.setText("");
			} else if (txtPassword.getPassword().length < 8) {
				JOptionPane.showMessageDialog(null, "Mật khẩu ít nhất phải có 8 ký tự");
				txtPassword.requestFocus();
			} else {
				Emp emp = returnData();
				EmpManageDAO.getInstance().createEmp(emp.getUsername(), emp.getEmpName(), emp.getPassword(), emp.isRole());
				JOptionPane.showMessageDialog(null, "Tạo tài khoản mới thành công");
				this.setVisible(false);
			}
		} else if (o.equals(btnCancel)) {
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		new createAccount().setVisible(true);
	}

	private boolean isEmpty(JTextField txt) {
		if (txt.getText().trim().equals("")) {
			txt.requestFocus();
			return true;
		}
		return false;
	}

}
