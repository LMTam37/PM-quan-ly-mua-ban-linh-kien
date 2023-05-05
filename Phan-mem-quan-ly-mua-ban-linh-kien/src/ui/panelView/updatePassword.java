package ui.panelView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.EmpManageDAO;
import entity.Emp;

public class updatePassword extends JDialog implements ActionListener {
	private JButton btnUpdatePassword, btnCancel;
	private JLabel lblCurPassword, lblNewPassword, lblConfirmPassword;
	private JPasswordField txtCurPassword, txtNewPassword, txtConfirmPassword;
	private Emp curAccount;

	public updatePassword(Emp account) {
		setSize(260, 230);
		setLayout(null);
		setLocationRelativeTo(null);
		curAccount = account;

		lblCurPassword = new JLabel("Mật khẩu hiện tai");
		lblCurPassword.setBounds(10, 5, 120, 13);
		add(lblCurPassword);

		txtCurPassword = new JPasswordField();
		txtCurPassword.setBounds(140, 5, 96, 19);
		add(txtCurPassword);
		txtCurPassword.setColumns(10);

		lblNewPassword = new JLabel("Mật khẩu mới");
		lblNewPassword.setBounds(10, 56, 120, 13);
		add(lblNewPassword);

		lblConfirmPassword = new JLabel("xác nhận mật khẩu");
		lblConfirmPassword.setBounds(10, 115, 120, 13);
		add(lblConfirmPassword);

		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(140, 56, 96, 19);
		add(txtNewPassword);
		txtNewPassword.setColumns(10);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(140, 112, 96, 19);
		add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);

		btnUpdatePassword = new JButton("xác nhận");
		btnUpdatePassword.setBounds(10, 159, 85, 21);
		add(btnUpdatePassword);

		btnCancel = new JButton("hũy");
		btnCancel.setBounds(151, 159, 85, 21);
		add(btnCancel);

		btnUpdatePassword.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnUpdatePassword)) {
			if (isEmpty(txtCurPassword) || isEmpty(txtNewPassword) || isEmpty(txtConfirmPassword)) {
				JOptionPane.showMessageDialog(null, "Hãy nhập vào đủ thông tin");
			} else if (!new String(txtCurPassword.getPassword()).equals(curAccount.getPassword())) {
				JOptionPane.showMessageDialog(null, "Mật khẩu hiện tại không đúng");
			} else if (!Arrays.equals(txtNewPassword.getPassword(), txtConfirmPassword.getPassword())) {
				JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp hãy nhập lại");
			} else if (txtNewPassword.getPassword().length < 8) {
				JOptionPane.showMessageDialog(null, "Mật khẩu ít nhất phải có 8 ký tự");
			} else {
				EmpManageDAO.getInstance().updateEmpPassword(curAccount.getEmpId(),
						new String(txtNewPassword.getPassword()));
				JOptionPane.showMessageDialog(null, "Mật khẩu mới đã được cập nhật");
				this.setVisible(false);
			}
		} else if (o.equals(btnCancel)) {
			this.setVisible(false);
		}
	}

	public boolean isEmpty(JTextField txt) {
		if (txt.getText().equals("")) {
			txt.requestFocus();
			return true;
		}
		return false;
	}
}
