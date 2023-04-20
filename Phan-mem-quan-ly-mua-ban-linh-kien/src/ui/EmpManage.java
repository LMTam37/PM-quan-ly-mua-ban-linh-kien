package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.EmpManageDAO;
import entity.Emp;
import ui.subUI.createAccount;

public class EmpManage extends JFrame implements ActionListener, MouseListener {

	private JPanel pnTitle, pnEmp, pnTableEmp;
	private JLabel lblRole, lblTitle, lblUsername, lblEmpName;
	private JTextField txtUsername;
	private JTextField txtEmpName;
	private JTable tableEmp;
	private DefaultTableModel modelEmp;
	private JButton btnAdd, btnRemove, btnUpdate, btnFind, btnClear, btnRefresh, btnBack;
	private JComboBox cbRole;
	private ArrayList<Emp> list;
	private JTextField txtEmpId;
	private JLabel lblEmpId;
	private Emp curAccount;
	

	public EmpManage(Emp account) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		curAccount = account;
		
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

		lblEmpId = new JLabel("Mã");
		lblEmpId.setBounds(10, 28, 103, 13);
		pnEmp.add(lblEmpId);

		txtEmpId = new JTextField();
		txtEmpId.setColumns(10);
		txtEmpId.setBounds(123, 26, 178, 19);
		pnEmp.add(txtEmpId);

		lblUsername = new JLabel("Tài khoản");
		lblUsername.setBounds(10, 58, 103, 13);
		pnEmp.add(lblUsername);

		lblEmpName = new JLabel("Tên nhân viên");
		lblEmpName.setBounds(382, 61, 103, 13);
		pnEmp.add(lblEmpName);

		txtUsername = new JTextField();
		txtUsername.setBounds(123, 55, 178, 19);
		pnEmp.add(txtUsername);
		txtUsername.setColumns(10);

		txtEmpName = new JTextField();
		txtEmpName.setColumns(10);
		txtEmpName.setBounds(495, 58, 208, 19);
		pnEmp.add(txtEmpName);

		lblRole = new JLabel("Vai trò");
		lblRole.setBounds(10, 94, 83, 13);
		pnEmp.add(lblRole);

		cbRole = new JComboBox();
		cbRole.setBounds(123, 90, 93, 21);

		cbRole.addItem("Nhân viên");
		cbRole.addItem("Quản lý");
		pnEmp.add(cbRole);

		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(10, 132, 100, 21);
		pnEmp.add(btnAdd);

		btnRemove = new JButton("xóa");
		btnRemove.setBounds(150, 132, 100, 21);
		pnEmp.add(btnRemove);

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(290, 132, 100, 21);
		pnEmp.add(btnUpdate);

		btnClear = new JButton("Xóa trắng");
		btnClear.setBounds(430, 132, 100, 21);
		pnEmp.add(btnClear);

		btnFind = new JButton("Tìm theo ID");
		btnFind.setBounds(570, 132, 103, 21);
		pnEmp.add(btnFind);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(860, 132, 100, 21);
		pnEmp.add(btnBack);

		btnRefresh = new JButton("Làm mới");
		btnRefresh.setBounds(715, 132, 100, 21);
		pnEmp.add(btnRefresh);

		pnTableEmp = new JPanel();
		pnTableEmp.setBackground(new Color(255, 255, 255));
		pnTableEmp.setBorder(
				new TitledBorder(null, "Danh sách tài khoản", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnTableEmp.setBounds(0, 225, 1386, 438);
		getContentPane().add(pnTableEmp);

		String[] empColumnName = { "STT", "Tên đăng nhập", "Tên nhân viên", "Loại nhân viên" };
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
		btnFind.addActionListener(this);
		btnRefresh.addActionListener(this);
		tableEmp.addMouseListener(this);

		loadAccountList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		} else if (o.equals(btnAdd)) {
			new createAccount().setVisible(true);
		} else if (o.equals(btnRemove)) {
			int row = tableEmp.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần xóa");
			} else {
				String username = modelEmp.getValueAt(row, 1).toString();
				String notifyMsg = "Bạn có chắc xóa nhân viên " + username;
				int select = JOptionPane.showConfirmDialog(null, notifyMsg, "Thông báo", JOptionPane.YES_NO_OPTION);
				if (select == JOptionPane.YES_OPTION) {
					int result = EmpManageDAO.getInstance().deleteEmp(username);
					if (result != 0) {
						modelEmp.removeRow(row);
						clear();
						JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại");
					}
				}
			}
		} else if (o.equals(btnClear)) {
			clear();
		} else if (o.equals(btnUpdate)) {
			int row = tableEmp.getSelectedRow();
			String username = modelEmp.getValueAt(row, 1).toString();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần cập nhật ");
			} else {
				Object response = JOptionPane.showInputDialog(null, "Chọn thông tin bạn muốn cập nhật", "Thông báo",
						JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Tên đăng nhập", "Tên nhân viên", "Mật khẩu" }, "Tên đăng nhập");
				if (response.equals("Tên đăng nhập")) {
					updateEmpUsername(row, username);
				} else if (response.equals("Tên nhân viên")) {
					updateEmpName(row, username);
				} else if (response.equals("Mật khẩu")) {
					updateEmpPassword(row, username);
				}
			}
		} else if (o.equals(btnRefresh)) {
			modelEmp.getDataVector().removeAllElements();
			modelEmp.fireTableDataChanged();
			loadAccountList();
		} else if(o.equals(btnFind)) {
			int id = Integer.parseInt(txtEmpId.getText());
			Emp emp = new Emp(id, "", "", "", true);
			int row = list.indexOf(emp);
			if(row != -1) {				
				tableEmp.getSelectionModel().setSelectionInterval(row, row);
				tableEmp.scrollRectToVisible(tableEmp.getCellRect(row, row, rootPaneCheckingEnabled));
			}else {
				JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên có id là " + id);
			}
		}
	}

	private void loadAccountList() {
		list = EmpManageDAO.getInstance().getListEmp();
		modelEmp.getDataVector().removeAllElements();
		modelEmp.fireTableDataChanged();
		load(list);
	}

	private void clear() {
		txtEmpName.setText("");
		txtUsername.setText("");
		cbRole.setSelectedIndex(0);
	}

	private void load(ArrayList<Emp> list) {
		int index = 0;
		for (Emp emp : list) {
			index++;
			String role = "";
			if (emp.isRole()) {
				role = "Nhân viên";
			} else {
				role = "Quản lý";
			}
			String[] row = { Integer.toString(index), emp.getUsername(), emp.getEmpName(), role };
			modelEmp.addRow(row);
		}
	}

	private void updateEmpName(int row, String username) {
		String notifyMsg = "Nhập vào tên mới cho nhân viên " + username;
		String input = JOptionPane.showInputDialog(null, notifyMsg);
		if (input.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên cần cập nhật không được để trống");
		} else {
			EmpManageDAO.getInstance().updateEmpName(list.get(row).getEmpId(), input);
			notifyMsg = "Cập nhật thành công tên mới của " + username + " là " + input;
			JOptionPane.showMessageDialog(null, notifyMsg);
			modelEmp.setValueAt(input, row, 2);
		}
	}

	private void updateEmpUsername(int row, String username) {
		String notifyMsg = "Nhập vào tên mới cho nhân viên " + username;
		String input = JOptionPane.showInputDialog(null, notifyMsg);
		if (input.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên đăng nhập cần cập nhật không được để trống");
		} else {
			EmpManageDAO.getInstance().updateEmpUsername(list.get(row).getEmpId(), input);
			notifyMsg = "Cập nhật thành công Tên đăng nhập mới của " + username + " là " + input;
			JOptionPane.showMessageDialog(null, notifyMsg);
			modelEmp.setValueAt(input, row, 1);
		}

	}

	private void updateEmpPassword(int row, String username) {
		String notifyMsg = "Nhập vào mật khẩu mới cho nhân viên " + username;
		String input = JOptionPane.showInputDialog(null, notifyMsg);
		if (input.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Mật khẩu cần cập nhật không được để trống");
		} else {
			EmpManageDAO.getInstance().updateEmpPassword(list.get(row).getEmpId(), input);
			notifyMsg = "Cập nhật thành công mật khẩu mới cho " + username;
			JOptionPane.showMessageDialog(null, notifyMsg);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableEmp.getSelectedRow();
		String username = modelEmp.getValueAt(row, 1).toString();
		Emp emp = list.get(row);
		txtEmpId.setText(Integer.toString(emp.getEmpId()));
		txtUsername.setText(emp.getUsername());
		txtEmpName.setText(emp.getEmpName());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
