package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ProductDAO;
import entity.Emp;
import entity.Product;
import javax.swing.JComboBox;

public class Product_UI extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pnTitle, pnProductManage, pnSearch, pnTableProduct;
	private JLabel lblNewLabel, lblProductId, lblProductName, lblMFG, lblMFGer, lblSearch, lblQty, lblPrice,
			lblCategory, lblCategorySearch;
	private JTextField txtProductId, txtProductName, txtMFGer, txtSearch;
	private JDateChooser dcMFG;
	private JSpinner spinnerQty, spinnerPrice;
	private JTable tableProduct;
	private DefaultTableModel modelProduct;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack, btnSearch;
	private JScrollPane spProduct;
	private ArrayList<Product> productList;
	private JComboBox<String> cbCategory, cbCategorySearch;
	private Emp curAccount;

	public Product_UI(Emp account) {
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

		lblNewLabel = new JLabel("Quản lý linh kiện");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnTitle.add(lblNewLabel);

		pnProductManage = new JPanel();
		pnProductManage.setBackground(new Color(255, 255, 255));
		pnProductManage.setBorder(
				new TitledBorder(null, "Thông tin linh kiện", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnProductManage.setBounds(0, 50, 1386, 117);
		getContentPane().add(pnProductManage);
		pnProductManage.setLayout(null);

		lblProductId = new JLabel("Mã linh kiện");
		lblProductId.setBounds(10, 20, 100, 13);
		pnProductManage.add(lblProductId);

		txtProductId = new JTextField();
		txtProductId.setEditable(false);
		txtProductId.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProductId.setBounds(120, 17, 170, 19);
		pnProductManage.add(txtProductId);
		txtProductId.setColumns(10);

		lblProductName = new JLabel("Tên linh kiện");
		lblProductName.setBounds(10, 52, 100, 13);
		pnProductManage.add(lblProductName);

		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProductName.setBounds(120, 49, 170, 19);
		pnProductManage.add(txtProductName);
		txtProductName.setColumns(10);

		lblMFG = new JLabel("Ngày sản xuất");
		lblMFG.setBounds(320, 23, 100, 13);
		pnProductManage.add(lblMFG);

		dcMFG = new JDateChooser();
		dcMFG.setFont(new Font("Tahoma", Font.BOLD, 10));
		dcMFG.setBounds(430, 20, 170, 19);
		pnProductManage.add(dcMFG);

		lblMFGer = new JLabel("Hàng sản xuất");
		lblMFGer.setBounds(630, 23, 100, 13);
		pnProductManage.add(lblMFGer);

		txtMFGer = new JTextField();
		txtMFGer.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMFGer.setBounds(740, 20, 170, 19);
		pnProductManage.add(txtMFGer);
		txtMFGer.setColumns(10);

		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(10, 86, 100, 21);
		pnProductManage.add(btnAdd);

		btnRemove = new JButton("Xóa");
		btnRemove.setBounds(140, 86, 100, 21);
		pnProductManage.add(btnRemove);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setBounds(270, 86, 100, 21);
		pnProductManage.add(btnUpdate);

		btnClear = new JButton("Xóa trắng");
		btnClear.setBounds(400, 86, 100, 21);
		pnProductManage.add(btnClear);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(530, 86, 100, 21);
		pnProductManage.add(btnBack);

		lblQty = new JLabel("Số lượng");
		lblQty.setBounds(630, 52, 100, 13);
		pnProductManage.add(lblQty);

		spinnerQty = new JSpinner();
		spinnerQty.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerQty.setFont(new Font("Tahoma", Font.BOLD, 10));
		spinnerQty.setBounds(740, 49, 170, 19);
		pnProductManage.add(spinnerQty);

		lblPrice = new JLabel("Đơn giá");
		lblPrice.setBounds(940, 52, 100, 13);
		pnProductManage.add(lblPrice);

		spinnerPrice = new JSpinner();
		spinnerPrice.setModel(new SpinnerNumberModel(0d, 0d, null, 0.01d));
		spinnerPrice.setFont(new Font("Tahoma", Font.BOLD, 10));
		spinnerPrice.setBounds(1050, 49, 170, 19);
		pnProductManage.add(spinnerPrice);

		lblCategory = new JLabel("Loại");
		lblCategory.setBounds(320, 52, 100, 13);
		pnProductManage.add(lblCategory);

		cbCategory = new JComboBox<String>();
		cbCategory.addItem("CPU");
		cbCategory.addItem("RAM");
		cbCategory.addItem("VGA");
		cbCategory.addItem("Mainboard");
		cbCategory.addItem("Nguồn máy tính");
		cbCategory.addItem("Ổ cứng");
		cbCategory.setBounds(430, 49, 170, 21);
		pnProductManage.add(cbCategory);

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
		btnSearch.setBounds(458, 16, 85, 21);
		pnSearch.add(btnSearch);

		cbCategorySearch = new JComboBox<String>();
		cbCategorySearch.addItem("Tất cả");
		cbCategorySearch.addItem("CPU");
		cbCategorySearch.addItem("RAM");
		cbCategorySearch.addItem("VGA");
		cbCategorySearch.addItem("Mainboard");
		cbCategorySearch.addItem("Nguồn máy tính");
		cbCategorySearch.addItem("Ổ cứng");
		cbCategorySearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				instanceLoadList();
			}
		});

		cbCategorySearch.setBounds(314, 15, 120, 21);
		pnSearch.add(cbCategorySearch);

		lblCategorySearch = new JLabel("Loại");
		lblCategorySearch.setBounds(259, 19, 45, 13);
		pnSearch.add(lblCategorySearch);

		pnTableProduct = new JPanel();
		pnTableProduct.setBounds(0, 232, 1386, 431);
		getContentPane().add(pnTableProduct);
		pnTableProduct.setLayout(new BorderLayout(0, 0));

		String[] productColumnName = { "STT", "Mã linh kiện", "Tên linh kiện", "Loại linh kiện", "Ngày sản xuất",
				"Hãng sản xuất", "Số lượng", "Đơn giả" };
		modelProduct = new DefaultTableModel(productColumnName, 0);
		tableProduct = new JTable(modelProduct);
		spProduct = new JScrollPane(tableProduct, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spProduct.getViewport().setBackground(Color.WHITE);
		pnTableProduct.add(spProduct);

		spProduct.setViewportView(tableProduct);

		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClear.addActionListener(this);
		btnBack.addActionListener(this);
		btnSearch.addActionListener(this);
		tableProduct.addMouseListener(this);

		loadAllProductList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		} else if (o.equals(btnAdd)) {
			ProductDAO.getInstance().addProduct(txtProductName.getText(), cbCategory.getSelectedIndex() + 1,
					new Date(dcMFG.getDate().getTime()), txtMFGer.getText(), (Integer) spinnerQty.getValue(),
					new BigDecimal(spinnerPrice.getValue().toString()));
			instanceLoadList();
			JOptionPane.showMessageDialog(null, "Tạo linh kiện thành công");
		} else if (o.equals(btnClear)) {
			clear();
		} else if (o.equals(btnSearch)) {
			String productName = txtSearch.getText();
			productList = ProductDAO.getInstance().getProductByName(productName, cbCategorySearch.getSelectedIndex());
			load(productList);
		} else if (o.equals(btnRemove)) {
			int row = tableProduct.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn linh kiện cần xóa");
			} else {
				String productName = modelProduct.getValueAt(row, 2).toString();
				String notifyMsg = "Bạn có chắc xóa linh kiện " + productName;
				int select = JOptionPane.showConfirmDialog(null, notifyMsg, "Thông báo", JOptionPane.YES_NO_OPTION);
				if (select == JOptionPane.YES_OPTION) {
					int result = ProductDAO.getInstance()
							.removeProduct(Integer.parseInt(modelProduct.getValueAt(row, 1).toString()));
					if (result != 0) {
						modelProduct.removeRow(row);
						clear();
						JOptionPane.showMessageDialog(null, "Xóa linh kiện thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Xóa linh kiện thất bại");
					}
				}
			}
		} else if (o.equals(btnUpdate)) {
			int row = tableProduct.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn linh kiện cần cập nhật");
			} else {
				ProductDAO.getInstance().updateProduct(Integer.parseInt(txtProductId.getText()),
						txtProductName.getText(), cbCategory.getSelectedIndex() + 1,
						new Date(dcMFG.getDate().getTime()), txtMFGer.getText(), (Integer) spinnerQty.getValue(),
						new BigDecimal(spinnerPrice.getValue().toString()));
				instanceLoadList();
				JOptionPane.showMessageDialog(null, "Cập nhật linh kiện thành công");
			}
		}
	}

	public void instanceLoadList() {
		if (cbCategorySearch.getSelectedIndex() == 0) {
			loadAllProductList();
		} else {
			loadProductList();
		}
	}

	public void loadProductList() {
		productList = ProductDAO.getInstance().getListProductByCategory(cbCategorySearch.getSelectedIndex());
		load(productList);
	}

	private void loadAllProductList() {
		productList = ProductDAO.getInstance().getListProduct();
		load(productList);
	}

	private void load(ArrayList<Product> list) {
		modelProduct.getDataVector().removeAllElements();
		modelProduct.fireTableDataChanged();
		int serial = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		for (Product curProduct : list) {
			modelProduct.addRow(new Object[] { serial++, curProduct.getProductId(), curProduct.getProductName(),
					curProduct.getCategory(), sdf.format(curProduct.getMfg()), curProduct.getMfger(),
					curProduct.getQty(), curProduct.getPrice() });
		}
	}

	public void clear() {
		txtProductId.setText("");
		txtProductName.setText("");
		dcMFG.setDate(null);
		txtMFGer.setText("");
		spinnerQty.setValue(Integer.parseInt("0"));
		spinnerPrice.setValue(Integer.parseInt("0"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableProduct.getSelectedRow();
		Product tempProduct = productList.get(Integer.parseInt(tableProduct.getValueAt(row, 0).toString()) - 1);
		txtProductId.setText(Integer.toString(tempProduct.getProductId()));
		txtProductName.setText(tempProduct.getProductName());
		dcMFG.setDate(tempProduct.getMfg());
		cbCategory.setSelectedItem(tempProduct.getCategory());
		txtMFGer.setText(tempProduct.getMfger());
		spinnerQty.setValue(tempProduct.getQty());
		spinnerPrice.setValue(tempProduct.getPrice());
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
