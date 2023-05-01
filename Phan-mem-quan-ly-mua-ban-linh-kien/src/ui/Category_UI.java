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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CategoryDAO;
import entity.Category;
import entity.Emp;

public class Category_UI extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnTitle, pnCategory, pnSearch, pnTableCategory;
	private JLabel lblNewLabel, lblCategoryId, lblCategoryName, lblSearch;
	private JTextField txtCategoryId, txtCategoryName, txtSearch;
	private JTable tableCategory;
	private DefaultTableModel modelCategory;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack, btnSearch;
	private JScrollPane spCategory;
	private ArrayList<Category> categoryList;
	private Emp curAccount;

	public Category_UI(Emp account) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Thong Tin San Pham");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		curAccount = account;

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 40);
		getContentPane().add(pnTitle);

		lblNewLabel = new JLabel("Quản lý Loại linh kiện");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnTitle.add(lblNewLabel);

		pnCategory = new JPanel();
		pnCategory.setBackground(new Color(255, 255, 255));
		pnCategory.setBorder(
				new TitledBorder(null, "Thông tin linh kiện", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnCategory.setBounds(0, 50, 1386, 117);
		getContentPane().add(pnCategory);
		pnCategory.setLayout(null);

		lblCategoryId = new JLabel("Mã loại");
		lblCategoryId.setBounds(10, 20, 100, 13);
		pnCategory.add(lblCategoryId);

		txtCategoryId = new JTextField();
		txtCategoryId.setEditable(false);
		txtCategoryId.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCategoryId.setBounds(120, 17, 170, 19);
		pnCategory.add(txtCategoryId);
		txtCategoryId.setColumns(10);

		lblCategoryName = new JLabel("Tên linh kiện");
		lblCategoryName.setBounds(10, 52, 100, 13);
		pnCategory.add(lblCategoryName);

		txtCategoryName = new JTextField();
		txtCategoryName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCategoryName.setBounds(120, 49, 170, 19);
		pnCategory.add(txtCategoryName);
		txtCategoryName.setColumns(10);

		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(10, 86, 100, 21);
		pnCategory.add(btnAdd);

		btnRemove = new JButton("Xóa");
		btnRemove.setBounds(140, 86, 100, 21);
		pnCategory.add(btnRemove);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBounds(270, 86, 100, 21);
		pnCategory.add(btnUpdate);

		btnClear = new JButton("Xóa trắng");
		btnClear.setBounds(400, 86, 100, 21);
		pnCategory.add(btnClear);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(530, 86, 100, 21);
		pnCategory.add(btnBack);

		pnSearch = new JPanel();
		pnSearch.setBorder(new TitledBorder(null, "Tìm kiếm ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearch.setBackground(new Color(255, 255, 255));
		pnSearch.setBounds(0, 175, 1386, 47);
		getContentPane().add(pnSearch);
		pnSearch.setLayout(null);

		lblSearch = new JLabel("Tìm linh kiện theo tên");
		lblSearch.setBounds(10, 20, 134, 13);
		pnSearch.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(143, 17, 96, 19);
		pnSearch.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("Tìm");
		btnSearch.setBounds(280, 16, 85, 21);
		pnSearch.add(btnSearch);

		pnTableCategory = new JPanel();
		pnTableCategory.setBounds(0, 232, 1386, 431);
		getContentPane().add(pnTableCategory);
		pnTableCategory.setLayout(new BorderLayout(0, 0));

		String[] CategoryColumnName = { "STT", "Mã loại", "Tên loại", };
		modelCategory = new DefaultTableModel(CategoryColumnName, 0);
		tableCategory = new JTable(modelCategory);
		spCategory = new JScrollPane(tableCategory, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spCategory.getViewport().setBackground(Color.WHITE);
		pnTableCategory.add(spCategory);

		spCategory.setViewportView(tableCategory);

		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClear.addActionListener(this);
		btnBack.addActionListener(this);
		btnSearch.addActionListener(this);
		tableCategory.addMouseListener(this);

		loadAllcategoryList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		} else if (o.equals(btnAdd)) {
			if (isEmpty(txtCategoryName)) {
				JOptionPane.showMessageDialog(null, "Tên loại linh kiện không được để trống");
			} else {
				CategoryDAO.getInstance().addCategory(txtCategoryName.getText());
				loadAllcategoryList();
				JOptionPane.showMessageDialog(null, "Tạo loại linh kiện thành công");
			}
		} else if (o.equals(btnClear)) {
			clear();
		} else if (o.equals(btnSearch)) {
			String categoryName = txtSearch.getText();
			categoryList = CategoryDAO.getInstance().getCategoryByName(categoryName);
			load(categoryList);
		} else if (o.equals(btnRemove)) {
			int row = tableCategory.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại linh kiện cần xóa");
			} else {
				Category category = categoryList.get(row);
				String notifyMsg = "Bạn có chắc xóa loại linh kiện " + category.getCategoryName();
				int select = JOptionPane.showConfirmDialog(null, notifyMsg, "Thông báo", JOptionPane.YES_NO_OPTION);
				if (select == JOptionPane.YES_OPTION) {
					int result = CategoryDAO.getInstance()
							.removeCategory(Integer.parseInt(modelCategory.getValueAt(row, 1).toString()));
					if (result != 0) {
						modelCategory.removeRow(row);
						clear();
						JOptionPane.showMessageDialog(null, "Xóa loại linh kiện thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Xóa loại linh kiện thất bại");
					}
				}
			}
		} else if (o.equals(btnUpdate)) {
			int row = tableCategory.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại linh kiện cần cập nhật");
			} else {
				Category category = categoryList.get(row);
				if (isEmpty(txtCategoryName)) {
					JOptionPane.showMessageDialog(null, "Tên loại linh kiện không được để trống");
				} else {
					int result = CategoryDAO.getInstance().updateCategory(category.getCategoryId(),
							txtCategoryName.getText());
					if (result != 0) {
						JOptionPane.showMessageDialog(null, "Cập nhật loại linh kiện thành công");
						loadAllcategoryList();
					} else {
						JOptionPane.showMessageDialog(null, "Cập nhật loại linh kiện thất bại");
					}
				}
			}
		}
	}

	private void loadAllcategoryList() {
		categoryList = CategoryDAO.getInstance().getListCategory();
		load(categoryList);
	}

	private void load(ArrayList<Category> list) {
		modelCategory.getDataVector().removeAllElements();
		modelCategory.fireTableDataChanged();
		int serial = 1;
		for (Category curCategory : list) {
			modelCategory.addRow(new Object[] { serial++, curCategory.getCategoryId(), curCategory.getCategoryName() });
		}
	}

	public void clear() {
		txtCategoryId.setText("");
		txtCategoryName.setText("");
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
		int row = tableCategory.getSelectedRow();
		Category tempCategory = categoryList.get(row);
		txtCategoryId.setText(Integer.toString(tempCategory.getCategoryId()));
		txtCategoryName.setText(tempCategory.getCategoryName());
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
