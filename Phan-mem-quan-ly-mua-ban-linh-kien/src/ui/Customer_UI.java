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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import entity.Customer;
import entity.Emp;

public class Customer_UI extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnTitle, pnCustomer, pnTableCustomer, pnSearch;
	private JLabel lblTitle, lblCustomerName, lblPhoneNumber, lblSearch;
	private JTextField txtCustomerName, txtPhoneNumber, txtCustomerId;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack, btnSearch;
	private JTable tableCustomer;
	private DefaultTableModel modelCustomer;
	private ArrayList<Customer> list;
	private JLabel lblCustomerId;
	private Emp curAccount;
	private JTextField txtSearch;
	private JTextField txtAddress;

	public Customer_UI(Emp account) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		curAccount = account;

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 40);
		getContentPane().add(pnTitle);

		lblTitle = new JLabel("Quản lý khách hàng");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnTitle.add(lblTitle);

		pnCustomer = new JPanel();
		pnCustomer.setBackground(new Color(255, 255, 255));
		pnCustomer.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnCustomer.setBounds(0, 50, 1386, 157);
		getContentPane().add(pnCustomer);
		pnCustomer.setLayout(null);

		lblCustomerId = new JLabel("Mã");
		lblCustomerId.setBounds(10, 28, 103, 13);
		pnCustomer.add(lblCustomerId);

		txtCustomerId = new JTextField();
		txtCustomerId.setEnabled(false);
		txtCustomerId.setEditable(false);
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(123, 26, 178, 19);
		pnCustomer.add(txtCustomerId);

		lblCustomerName = new JLabel("Tên khách hàng");
		lblCustomerName.setBounds(10, 61, 103, 13);
		pnCustomer.add(lblCustomerName);

		lblPhoneNumber = new JLabel("Số điện thoại");
		lblPhoneNumber.setBounds(382, 61, 103, 13);
		pnCustomer.add(lblPhoneNumber);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(123, 58, 178, 19);
		pnCustomer.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(495, 58, 208, 19);
		pnCustomer.add(txtPhoneNumber);

		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(10, 125, 100, 21);
		pnCustomer.add(btnAdd);

		btnRemove = new JButton("xóa");
		btnRemove.setBounds(150, 125, 100, 21);
		pnCustomer.add(btnRemove);

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(290, 125, 100, 21);
		pnCustomer.add(btnUpdate);

		btnClear = new JButton("Xóa trắng");
		btnClear.setBounds(430, 125, 100, 21);
		pnCustomer.add(btnClear);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(570, 125, 100, 21);
		pnCustomer.add(btnBack);

		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setBounds(10, 94, 103, 13);
		pnCustomer.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(123, 91, 362, 19);
		pnCustomer.add(txtAddress);

		pnSearch = new JPanel();
		pnSearch.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearch.setBackground(Color.WHITE);
		pnSearch.setBounds(0, 207, 1386, 50);
		getContentPane().add(pnSearch);
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

		pnTableCustomer = new JPanel();
		pnTableCustomer.setBackground(new Color(255, 255, 255));
		pnTableCustomer.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnTableCustomer.setBounds(0, 259, 1386, 404);
		getContentPane().add(pnTableCustomer);

		String[] customerColumnName = { "STT", "Tên khách hàng", "Số điện thoại", "Địa chỉ" };
		modelCustomer = new DefaultTableModel(customerColumnName, 0);
		pnTableCustomer.setLayout(new BorderLayout(0, 0));
		tableCustomer = new JTable(modelCustomer);
		JScrollPane spTableCustomer = new JScrollPane(tableCustomer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spTableCustomer.getViewport().setBackground(Color.WHITE);
		pnTableCustomer.add(spTableCustomer);

		btnAdd.addActionListener(this);
		btnBack.addActionListener(this);
		btnClear.addActionListener(this);
		btnRemove.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnSearch.addActionListener(this);
		tableCustomer.addMouseListener(this);

		loadCustomerList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		} else if (o.equals(btnAdd)) {
			if (isEmpty(txtCustomerName) || isEmpty(txtPhoneNumber) || isEmpty(txtAddress)) {
				JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin");
			} else {
				CustomerDAO.getInstance().createCustomer(txtCustomerName.getText(), txtPhoneNumber.getText(),
						txtAddress.getText());
				JOptionPane.showMessageDialog(null, "Tạo khách hàng mới thành công");
			}
			loadCustomerList();
		} else if (o.equals(btnRemove)) {
			int row = tableCustomer.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng cần xóa");
			} else {
				Customer customer = list.get(row);
				String notifyMsg = "Bạn có chắc xóa khách hàng " + customer.getCustomerName();
				int select = JOptionPane.showConfirmDialog(null, notifyMsg, "Thông báo", JOptionPane.YES_NO_OPTION);
				if (select == JOptionPane.YES_OPTION) {
					int result = CustomerDAO.getInstance().deleteCustomer(customer.getCustomerId());
					if (result != 0) {
						modelCustomer.removeRow(row);
						clear();
						JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại");
					}
				}
			}
		} else if (o.equals(btnClear)) {
			clear();
		} else if (o.equals(btnUpdate)) {
			int row = tableCustomer.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng cần cập nhật ");
			} else {
				CustomerDAO.getInstance().updateCustomer(Integer.parseInt(txtCustomerId.getText()),
						txtCustomerName.getText(), txtPhoneNumber.getText(), txtAddress.getText());
				JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
				loadCustomerList();
			}
		} else if (o.equals(btnSearch)) {
			list = CustomerDAO.getInstance().getListCustomerBySearch(txtSearch.getText());
			load(list);
		}
	}

	private void loadCustomerList() {
		list = CustomerDAO.getInstance().getListCustomer();
		load(list);
	}

	private void clear() {
		txtCustomerId.setText("");
		txtPhoneNumber.setText("");
		txtCustomerName.setText("");
		txtPhoneNumber.setText("");
		txtAddress.setText("");
	}

	private void load(ArrayList<Customer> list) {
		modelCustomer.getDataVector().removeAllElements();
		modelCustomer.fireTableDataChanged();
		int index = 0;
		for (Customer curCustomer : list) {
			index++;
			String[] row = { Integer.toString(index), curCustomer.getCustomerName(),
					curCustomer.getCustomerPhoneNumber(), curCustomer.getAddress() };
			modelCustomer.addRow(row);
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
		int row = tableCustomer.getSelectedRow();
		Customer customer = list.get(row);
		txtCustomerId.setText(Integer.toString(customer.getCustomerId()));
		txtCustomerName.setText(customer.getCustomerName());
		txtPhoneNumber.setText(customer.getCustomerPhoneNumber());
		txtAddress.setText(customer.getAddress());
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
