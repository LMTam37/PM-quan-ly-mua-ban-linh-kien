package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

public class Emp extends JFrame implements ActionListener{

	private JPanel pnTitle,pnEmp,pnTableEmp;
	private JLabel lblRole, lblTitle, lblAccountName, lblUserName;
	private JTextField txtAccountName;
	private JTextField txtUserName;
	private JTable tableEmp;
	private DefaultTableModel modelEmp;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack;

	public static void main(String[] args) {
		new Emp().setVisible(true);
	}

	public Emp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 42);
		getContentPane().add(pnTitle);

		lblTitle = new JLabel("Quản lý tài khoản");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 10));
		pnTitle.add(lblTitle);

		pnEmp = new JPanel();
		pnEmp.setBackground(new Color(255, 255, 255));
		pnEmp.setBorder(
				new TitledBorder(null, "Thông tin tài khoản", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnEmp.setBounds(0, 51, 1386, 164);
		getContentPane().add(pnEmp);
		pnEmp.setLayout(null);

		lblAccountName = new JLabel("Tài khoản");
		lblAccountName.setBounds(10, 22, 83, 13);
		pnEmp.add(lblAccountName);

		lblUserName = new JLabel("Tên đăng nhập");
		lblUserName.setBounds(10, 58, 83, 13);
		pnEmp.add(lblUserName);

		txtAccountName = new JTextField();
		txtAccountName.setBounds(103, 19, 178, 19);
		pnEmp.add(txtAccountName);
		txtAccountName.setColumns(10);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(103, 55, 178, 19);
		pnEmp.add(txtUserName);

		lblRole = new JLabel("Vai trò");
		lblRole.setBounds(10, 94, 83, 13);
		pnEmp.add(lblRole);

		JComboBox cbRole = new JComboBox();
		cbRole.setBounds(103, 90, 93, 21);

		cbRole.addItem("Nhân viên");
		cbRole.addItem("Quản lý");
		pnEmp.add(cbRole);

		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(10, 132, 100, 21);
		pnEmp.add(btnAdd);

		btnRemove = new JButton("xóa");
		btnRemove.setBounds(150, 132, 100, 21);
		pnEmp.add(btnRemove);

		btnUpdate = new JButton("sửa");
		btnUpdate.setBounds(290, 132, 100, 21);
		pnEmp.add(btnUpdate);

		btnClear = new JButton("Xóa trắng");
		btnClear.setBounds(430, 132, 100, 21);
		pnEmp.add(btnClear);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(570, 132, 100, 21);
		pnEmp.add(btnBack);

		pnTableEmp = new JPanel();
		pnTableEmp.setBackground(new Color(255, 255, 255));
		pnTableEmp.setBorder(new TitledBorder(null, "Danh s\u00E1ch t\u00E0i kho\u1EA3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnTableEmp.setBounds(0, 225, 1386, 438);
		getContentPane().add(pnTableEmp);

		String[] empColumnName = { "STT", "Tài khoản", "Tên đăng nhập", "Loại" };
		modelEmp = new DefaultTableModel(empColumnName, 0);
		pnTableEmp.setLayout(new BorderLayout(0, 0));
		tableEmp = new JTable(modelEmp);
		JScrollPane spTableEmp = new JScrollPane(tableEmp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spTableEmp.getViewport().setBackground(Color.WHITE);
		pnTableEmp.add(spTableEmp);
		
		btnAdd.addActionListener(this);
		btnBack.addActionListener(this);
		btnClear.addActionListener(this);
		btnRemove.addActionListener(this);
		btnUpdate.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI().setVisible(true);
		}
		
	}
}
