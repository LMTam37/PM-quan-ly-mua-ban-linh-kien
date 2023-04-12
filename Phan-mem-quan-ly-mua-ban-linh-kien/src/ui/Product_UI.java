package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

public class Product_UI extends JFrame implements ActionListener {
	private JPanel pnTitle, pnProductManage, pnSearch, pnTableProduct;
	private JLabel lblNewLabel, lblProductId, lblTenLinhKien, lblMFG, lblProducer, lblSearch;
	private JTextField txtProductId, txtProductName, txtMFG, txtProducer, txtSearch;
	private JTable tableProduct;
	private DefaultTableModel modelProduct;
	private JButton btnAdd, btnRemove, btnUpdate, btnClear, btnBack, btnSearch;
	private JScrollPane spProduct;

	public Product_UI() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Thong Tin San Pham");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 23);
		getContentPane().add(pnTitle);

		lblNewLabel = new JLabel("Quản lý linh kiện");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnTitle.add(lblNewLabel);

		pnProductManage = new JPanel();
		pnProductManage.setBackground(new Color(255, 255, 255));
		pnProductManage.setBorder(
				new TitledBorder(null, "Thông tin linh kiện", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnProductManage.setBounds(0, 22, 1386, 117);
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

		lblProducer = new JLabel("Hàng sản xuất");
		lblProducer.setBounds(373, 46, 100, 13);
		pnProductManage.add(lblProducer);

		txtProducer = new JTextField();
		txtProducer.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProducer.setBounds(483, 46, 193, 19);
		pnProductManage.add(txtProducer);
		txtProducer.setColumns(10);

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
		pnSearch.setBounds(0, 147, 1386, 47);
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

		pnTableProduct = new JPanel();
		pnTableProduct.setBounds(0, 204, 1386, 459);
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
	}

	public static void main(String[] args) {
		new Product_UI().setVisible(true);
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
