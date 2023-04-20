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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import dao.ProductDAO;
import entity.Emp;
import entity.Product;
import ui.subUI.createProduct;
import javax.swing.JComboBox;

public class Product_UI extends JFrame implements ActionListener, MouseListener {
	private JPanel pnTitle, pnProductManage, pnSearch, pnTableProduct;
	private JLabel lblNewLabel, lblProductId, lblTenLinhKien, lblMFG, lblMFGer, lblSearch;
	private JTextField txtProductId, txtProductName, txtMFG, txtMFGer, txtSearch;
	private JTable tableProduct;
	private DefaultTableModel modelProduct;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack, btnSearch;
	private JScrollPane spProduct;
	private ArrayList<Product> productList;
	private Emp curAccount;
	private JComboBox cbCategory;
	private JLabel lblCategory;

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
		lblProductId.setBounds(10, 20, 74, 13);
		pnProductManage.add(lblProductId);

		txtProductId = new JTextField();
		txtProductId.setEditable(false);
		txtProductId.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProductId.setBounds(105, 17, 193, 19);
		pnProductManage.add(txtProductId);
		txtProductId.setColumns(10);

		lblTenLinhKien = new JLabel("Tên linh kiện");
		lblTenLinhKien.setBounds(10, 43, 74, 13);
		pnProductManage.add(lblTenLinhKien);

		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProductName.setBounds(105, 40, 193, 19);
		pnProductManage.add(txtProductName);
		txtProductName.setColumns(10);

		lblMFG = new JLabel("Ngày sản xuất");
		lblMFG.setBounds(373, 23, 100, 13);
		pnProductManage.add(lblMFG);

		txtMFG = new JTextField();
		txtMFG.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMFG.setBounds(483, 20, 193, 19);
		pnProductManage.add(txtMFG);
		txtMFG.setColumns(10);

		lblMFGer = new JLabel("Hàng sản xuất");
		lblMFGer.setBounds(373, 46, 100, 13);
		pnProductManage.add(lblMFGer);

		txtMFGer = new JTextField();
		txtMFGer.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMFGer.setBounds(483, 46, 193, 19);
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
		btnSearch.setBounds(249, 16, 85, 21);
		pnSearch.add(btnSearch);

		cbCategory = new JComboBox();
		cbCategory.addItem("CPU");
		cbCategory.addItem("RAM");
		cbCategory.addItem("VGA");
		cbCategory.addItem("Mainboard");
		cbCategory.addItem("Nguồn máy tính");
		cbCategory.addItem("Ổ cứng");
		cbCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				productList = ProductDAO.getInstance().getListProduct(cbCategory.getSelectedIndex() + 1);
				loadProductList();
			}
		});

		cbCategory.setBounds(435, 17, 120, 21);
		pnSearch.add(cbCategory);

		lblCategory = new JLabel("Loại");
		lblCategory.setBounds(380, 21, 45, 13);
		pnSearch.add(lblCategory);

		pnTableProduct = new JPanel();
		pnTableProduct.setBounds(0, 232, 1386, 431);
		getContentPane().add(pnTableProduct);
		pnTableProduct.setLayout(new BorderLayout(0, 0));

		String[] productColumnName = { "STT", "Mã linh kiện", "Tên linh kiện", "Ngày sản xuất", "Hãng sản xuất",
				"Số lượng", "Đơn giả" };
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

		loadProductList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		} else if (o.equals(btnAdd)) {
			createProduct createProductDialog = new createProduct();
			createProductDialog.setVisible(true);
			createProductDialog.addComponentListener(new ComponentListener() {
				public void componentHidden(ComponentEvent e) {
					loadProductList();
				}

				@Override
				public void componentResized(ComponentEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void componentMoved(ComponentEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void componentShown(ComponentEvent e) {
					// TODO Auto-generated method stub

				}
			});
		} else if (o.equals(btnClear)) {
			clear();
		} else if (o.equals(btnSearch)) {
			String productName = txtSearch.getText();
			productList = ProductDAO.getInstance().getProductByName(productName, cbCategory.getSelectedIndex() + 1);
			modelProduct.getDataVector().removeAllElements();
			modelProduct.fireTableDataChanged();
			load(productList);
		} else if (o.equals(btnRemove)) {
			int row = tableProduct.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn linh kiện cần xóa");
			} else {
				String productName = modelProduct.getValueAt(row, 2).toString();
				String notifyMsg = "Bạn có chắc xóa nhân viên " + productName;
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
				JOptionPane.showMessageDialog(null, "Bạn cần chọn linh kiện để cập nhật trước");
			} else {
				try {
					int productId = Integer.parseInt(modelProduct.getValueAt(row, 1).toString());
					String productName = modelProduct.getValueAt(row, 2).toString();
					String mfg = modelProduct.getValueAt(row, 3).toString();
					java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(mfg);
					String mfger = modelProduct.getValueAt(row, 4).toString();
					int qty = Integer.parseInt(modelProduct.getValueAt(row, 5).toString());
					BigDecimal price = BigDecimal
							.valueOf(Double.parseDouble(modelProduct.getValueAt(row, 6).toString()));
					createProduct createProductDialog = new createProduct(productId, productName, date, mfger, qty,
							price);
					createProductDialog.setVisible(true);
					createProductDialog.addComponentListener(new ComponentListener() {
						public void componentHidden(ComponentEvent e) {
							loadProductList();
						}

						@Override
						public void componentResized(ComponentEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void componentMoved(ComponentEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void componentShown(ComponentEvent e) {
							// TODO Auto-generated method stub

						}
					});
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void loadProductList() {
		productList = ProductDAO.getInstance().getListProduct(cbCategory.getSelectedIndex() + 1);
		modelProduct.getDataVector().removeAllElements();
		modelProduct.fireTableDataChanged();
		load(productList);
	}

	private void load(ArrayList<Product> list) {
		int serial = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		for (Product product : list) {
			modelProduct.addRow(new Object[] { serial++, product.getProductId(), product.getProductName(),
					sdf.format(product.getMfg()), product.getMfger(), product.getQty(), product.getPrice() });
		}
	}

	public void clear() {
		txtProductId.setText("");
		txtProductName.setText("");
		txtMFG.setText("");
		txtMFGer.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableProduct.getSelectedRow();
		txtProductId.setText(tableProduct.getValueAt(row, 1).toString());
		txtProductName.setText(tableProduct.getValueAt(row, 2).toString());
		txtMFG.setText(tableProduct.getValueAt(row, 3).toString());
		txtMFGer.setText(tableProduct.getValueAt(row, 4).toString());
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
