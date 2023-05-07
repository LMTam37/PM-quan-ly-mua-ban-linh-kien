package ui.panelView;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.EmpManageDAO;
import entity.Emp;

public class PnEmp extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnTitle, pnEmp, pnTableEmp, pnSearch;
	private JLabel lblRole, lblTitle, lblUsername, lblEmpName, lblPassword, lblSearch;
	private JTextField txtUsername, txtEmpName, txtEmpId;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack, btnSearch;
	private JTable tableEmp;
	private DefaultTableModel modelEmp;
	private JComboBox<String> cbRole;
	private ArrayList<Emp> list;
	private JLabel lblEmpId;
	private JPasswordField txtPassword;
	private JTextField txtSearch;

	public PnEmp(Emp account) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 40);
		add(pnTitle);

		lblTitle = new JLabel("Quản lý nhân viên");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnTitle.add(lblTitle);

		pnEmp = new JPanel();
		pnEmp.setBackground(new Color(255, 255, 255));
		pnEmp.setBorder(
				new TitledBorder(null, "Thông tin tài khoản", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnEmp.setBounds(0, 50, 1386, 165);
		add(pnEmp);
		pnEmp.setLayout(null);

		lblEmpId = new JLabel("Mã");
		lblEmpId.setBounds(10, 28, 103, 13);
		pnEmp.add(lblEmpId);

		txtEmpId = new JTextField();
		txtEmpId.setEnabled(false);
		txtEmpId.setEditable(false);
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
		lblRole.setBounds(382, 94, 83, 13);
		pnEmp.add(lblRole);

		cbRole = new JComboBox<String>();
		cbRole.setBounds(495, 90, 93, 21);

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

		btnBack = new JButton("Thoát");
		btnBack.setBounds(570, 132, 100, 21);
		pnEmp.add(btnBack);

		lblPassword = new JLabel("Mật khẩu");
		lblPassword.setBounds(10, 94, 103, 13);
		pnEmp.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(123, 91, 178, 19);
		pnEmp.add(txtPassword);

		pnSearch = new JPanel();
		pnSearch.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearch.setBackground(Color.WHITE);
		pnSearch.setBounds(0, 217, 1386, 50);
		add(pnSearch);
		pnSearch.setLayout(null);

		lblSearch = new JLabel("Tìm theo tên");
		lblSearch.setBounds(10, 22, 103, 13);
		pnSearch.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(123, 19, 178, 19);
		pnSearch.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("Tìm");
		btnSearch.setBounds(311, 18, 85, 21);
		pnSearch.add(btnSearch);

		pnTableEmp = new JPanel();
		pnTableEmp.setBackground(new Color(255, 255, 255));
		pnTableEmp.setBorder(
				new TitledBorder(null, "Danh sách tài khoản", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnTableEmp.setBounds(0, 267, 1386, 396);
		add(pnTableEmp);

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
		btnSearch.addActionListener(this);
		tableEmp.addMouseListener(this);

		loadAccountList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			if (isEmpty(txtUsername) || isEmpty(txtEmpName) || isEmpty(txtPassword)) {
				JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin");
			} else if (txtPassword.getPassword().length < 8) {
				JOptionPane.showMessageDialog(null, "Mật khẩu ít nhất phải có 8 ký tự");
				txtPassword.requestFocus();
			} else if(!EmpManageDAO.getInstance().isExistsUsername(txtUsername.getText())){
				JOptionPane.showMessageDialog(null, "Tên tài khoản này đã tồn tại");
			}
			else {
				boolean role = true;
				if (cbRole.getSelectedIndex() == 1) {
					role = false;
				} else {
					role = true;
				}
				EmpManageDAO.getInstance().createEmp(txtUsername.getText(), txtEmpName.getText(),
						String.valueOf(txtPassword.getPassword()), role);
				JOptionPane.showMessageDialog(null, "Tạo tài khoản mới thành công");
			}
			loadAccountList();
		} else if (o.equals(btnRemove)) {
			int row = tableEmp.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần xóa");
			} else {
				Emp emp = list.get(row);
				String notifyMsg = "Bạn có chắc xóa nhân viên " + emp.getEmpName();
				int select = JOptionPane.showConfirmDialog(null, notifyMsg, "Thông báo", JOptionPane.YES_NO_OPTION);
				if (select == JOptionPane.YES_OPTION) {
					int result = EmpManageDAO.getInstance().deleteEmp(emp.getEmpId());
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
			if (txtPassword.getPassword().length < 8) {
				txtPassword.requestFocus();
				JOptionPane.showMessageDialog(null, "Mật khẩu ít nhất phải có 8 ký tự");
			} else {
				int row = tableEmp.getSelectedRow();
				boolean role;
				if (cbRole.getSelectedIndex() == 0) {
					role = true;
				} else {
					role = false;
				}
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần cập nhật ");
				} else {
					EmpManageDAO.getInstance().updateEmp(Integer.parseInt(txtEmpId.getText()), txtUsername.getText(),
							txtEmpName.getText(), new String(txtPassword.getPassword()), role);
					JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công");
					loadAccountList();
				}
			}
		} else if (o.equals(btnSearch)) {
			list = EmpManageDAO.getInstance().getListEmpBySearch(txtSearch.getText());
			load(list);
		}
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	private void loadAccountList() {
		list = EmpManageDAO.getInstance().getListEmp();
		load(list);
	}

	private void clear() {
		txtEmpId.setText("");
		txtEmpName.setText("");
		txtUsername.setText("");
		txtEmpName.setText("");
		txtPassword.setText("");
		cbRole.setSelectedIndex(0);
	}

	private void load(ArrayList<Emp> list) {
		modelEmp.getDataVector().removeAllElements();
		modelEmp.fireTableDataChanged();
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

	private boolean isEmpty(JTextField txt) {
		if (txt.getText().trim().equals("")) {
			txt.requestFocus();
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableEmp.getSelectedRow();
		Emp emp = list.get(row);
		int role;
		if (emp.isRole()) {
			role = 0;
		} else {
			role = 1;
		}
		txtEmpId.setText(Integer.toString(emp.getEmpId()));
		txtUsername.setText(emp.getUsername());
		txtEmpName.setText(emp.getEmpName());
		txtPassword.setText(emp.getPassword());
		cbRole.setSelectedIndex(role);
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
