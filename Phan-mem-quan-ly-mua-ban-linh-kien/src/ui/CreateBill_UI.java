package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import dao.CreateBillDAO;
import dao.ProductDAO;
import entity.Bill;
import entity.BillDetail;
import entity.Product;

public class CreateBill_UI extends JFrame implements ActionListener {

	private JPanel pnHeader, pnBill, pnCustomerInfo, pnProduct, pnProductInfo, pnProductList, pnOrderList;
	private JLabel lblTieuDe, lblBillid, lblBillDate, lblCustomeName, lblEmpName, lblTotal, lblDiscount, lblTotalDue,
			lblSubTotalCurrency, lblTotalDueCurrency, lblPercent, lblProductName, lblQty;
	private JButton btnPay, btnBack, btnSearch, btnAdd, btnRemove;
	private JSpinner discountPercent, qtySpinner;
	private JTextField txtBillid, txtBillDate, txtCustomerName, txtSubTotal, txtTotalDue, txtProductName, txtEmpName;
	private JTable orderListTable, productListTable;
	private DefaultTableModel orderListModel, productListModel;
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<BillDetail> orderList = new ArrayList<BillDetail>();
	private BigDecimal billSubtotal;
	private BigDecimal billTotalDue;
	private JTextField txtPhoneNumber;
	private JLabel lblAddress;
	private JTextField txtAddress;
	public static void main(String[] args) {
		new CreateBill_UI().setVisible(true);
	}

	public CreateBill_UI() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		pnHeader = new JPanel();
		pnHeader.setBackground(new Color(0, 128, 255));
		pnHeader.setBounds(0, 0, 1386, 40);
		getContentPane().add(pnHeader);
		pnHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTieuDe = new JLabel("Tạo đơn hàng");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnHeader.add(lblTieuDe);

		pnBill = new JPanel();
		pnBill.setBounds(0, 46, 698, 617);
		getContentPane().add(pnBill);
		pnBill.setLayout(new BorderLayout());

		pnCustomerInfo = new JPanel();
		pnCustomerInfo.setBackground(new Color(255, 255, 255));
		pnCustomerInfo.setBorder(
				new TitledBorder(null, "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBill.add(pnCustomerInfo, BorderLayout.NORTH);
		pnCustomerInfo.setPreferredSize(new Dimension(500, 170));
		pnCustomerInfo.setLayout(null);

		lblBillid = new JLabel("Mã hóa đơn:");
		lblBillid.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBillid.setBounds(49, 25, 85, 13);
		pnCustomerInfo.add(lblBillid);

		txtBillid = new JTextField();
		txtBillid.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtBillid.setEditable(false);
		txtBillid.setBounds(144, 22, 126, 19);
		pnCustomerInfo.add(txtBillid);
		txtBillid.setColumns(10);

		lblBillDate = new JLabel("Ngày mua:");
		lblBillDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBillDate.setBounds(49, 115, 85, 13);
		pnCustomerInfo.add(lblBillDate);

		txtBillDate = new JTextField();
		txtBillDate.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtBillDate.setEditable(false);
		txtBillDate.setBounds(144, 112, 96, 19);
		pnCustomerInfo.add(txtBillDate);
		txtBillDate.setColumns(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String dateFormated = formatter.format(LocalDate.now());
		txtBillDate.setText(dateFormated);

		lblCustomeName = new JLabel("Tên khách hàng");
		lblCustomeName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCustomeName.setBounds(49, 55, 85, 13);
		pnCustomerInfo.add(lblCustomeName);

		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCustomerName.setBounds(144, 51, 126, 19);
		pnCustomerInfo.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		lblEmpName = new JLabel("Tên nhân viên");
		lblEmpName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpName.setBounds(290, 25, 85, 13);
		pnCustomerInfo.add(lblEmpName);

		txtEmpName = new JTextField();
		txtEmpName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEmpName.setColumns(10);
		txtEmpName.setBounds(385, 22, 126, 19);
		pnCustomerInfo.add(txtEmpName);

		lblTotal = new JLabel("Tổng tiền:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTotal.setBounds(49, 145, 85, 13);
		pnCustomerInfo.add(lblTotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtSubTotal.setEditable(false);
		txtSubTotal.setBounds(144, 141, 96, 19);
		pnCustomerInfo.add(txtSubTotal);
		txtSubTotal.setColumns(10);

		lblTotalDue = new JLabel("Tiền cần trả:");
		lblTotalDue.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTotalDue.setBounds(290, 145, 85, 13);
		pnCustomerInfo.add(lblTotalDue);

		txtTotalDue = new JTextField();
		txtTotalDue.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTotalDue.setEditable(false);
		txtTotalDue.setBounds(385, 141, 96, 19);
		pnCustomerInfo.add(txtTotalDue);
		txtTotalDue.setColumns(10);

		lblDiscount = new JLabel("Giảm giá:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDiscount.setBounds(290, 118, 85, 13);
		pnCustomerInfo.add(lblDiscount);

		discountPercent = new JSpinner();
		discountPercent.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		discountPercent.setFont(new Font("Tahoma", Font.BOLD, 10));
		discountPercent.setBounds(385, 115, 35, 20);
		discountPercent.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					DecimalFormat df = new DecimalFormat("#,###.##");
					billTotalDue = billSubtotal
							.multiply(new BigDecimal(100 - Double.parseDouble(discountPercent.getValue().toString())))
							.divide(new BigDecimal(100));
					txtTotalDue.setText(df.format(billTotalDue));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		pnCustomerInfo.add(discountPercent);

		btnPay = new JButton("Thanh toán");
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPay.setBounds(549, 111, 139, 21);
		pnCustomerInfo.add(btnPay);

		btnBack = new JButton("Thoát");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBack.setBounds(549, 141, 139, 21);
		pnCustomerInfo.add(btnBack);

		lblSubTotalCurrency = new JLabel("VND");
		lblSubTotalCurrency.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSubTotalCurrency.setBounds(491, 145, 30, 13);
		pnCustomerInfo.add(lblSubTotalCurrency);

		lblTotalDueCurrency = new JLabel("VND");
		lblTotalDueCurrency.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTotalDueCurrency.setBounds(250, 145, 30, 13);
		pnCustomerInfo.add(lblTotalDueCurrency);

		lblPercent = new JLabel("%");
		lblPercent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPercent.setBounds(425, 119, 19, 13);
		pnCustomerInfo.add(lblPercent);
		
		JLabel lblPhoneNumber = new JLabel("Số điện thoại");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPhoneNumber.setBounds(290, 55, 85, 13);
		pnCustomerInfo.add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(385, 51, 126, 19);
		pnCustomerInfo.add(txtPhoneNumber);
		
		lblAddress = new JLabel("Tên khách hàng");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAddress.setBounds(49, 85, 85, 13);
		pnCustomerInfo.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAddress.setColumns(10);
		txtAddress.setBounds(144, 80, 367, 19);
		pnCustomerInfo.add(txtAddress);

		pnOrderList = new JPanel();
		pnOrderList.setBackground(new Color(255, 255, 255));
		pnOrderList.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách linh kiện đã chọn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnBill.add(pnOrderList);
		pnOrderList.setLayout(new BorderLayout(0, 0));

		String[] orderColumnName = { "STT", "Mã linh kiện", "Tên linh kiện", "Đơn giá", "Số lượng", "Thành tiền" };
		orderListModel = new DefaultTableModel(orderColumnName, 0);
		orderListTable = new JTable(orderListModel);
		JScrollPane spOrderList = new JScrollPane(orderListTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spOrderList.getViewport().setBackground(Color.WHITE);
		pnOrderList.add(spOrderList);

		pnProduct = new JPanel();
		pnProduct.setBounds(704, 46, 682, 607);
		getContentPane().add(pnProduct);
		pnProduct.setLayout(new BorderLayout(0, 0));

		pnProductInfo = new JPanel();
		pnProductInfo.setBackground(new Color(255, 255, 255));
		pnProductInfo.setBorder(
				new TitledBorder(null, "Thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnProduct.add(pnProductInfo, BorderLayout.NORTH);
		pnProductInfo.setPreferredSize(new Dimension(400, 110));
		pnProductInfo.setLayout(null);

		lblProductName = new JLabel("Tên linh kiện");
		lblProductName.setBounds(52, 30, 82, 13);
		pnProductInfo.add(lblProductName);

		lblQty = new JLabel("Số lượng");
		lblQty.setBounds(52, 69, 65, 13);
		pnProductInfo.add(lblQty);

		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProductName.setBounds(144, 27, 140, 19);
		pnProductInfo.add(txtProductName);
		txtProductName.setColumns(10);

		qtySpinner = new JSpinner();
		qtySpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		qtySpinner.setFont(new Font("Tahoma", Font.BOLD, 10));
		qtySpinner.setBounds(144, 66, 56, 20);
		pnProductInfo.add(qtySpinner);

		btnSearch = new JButton("Tìm");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSearch.setBounds(501, 17, 140, 21);
		pnProductInfo.add(btnSearch);

		btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAdd.setBounds(501, 48, 140, 21);
		pnProductInfo.add(btnAdd);

		btnRemove = new JButton("Xóa");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemove.setBounds(501, 79, 140, 21);
		pnProductInfo.add(btnRemove);

		pnProductList = new JPanel();
		pnProduct.add(pnProductList, BorderLayout.CENTER);
		pnProductList.setLayout(new BorderLayout(0, 0));

		String[] productColumnName = { "STT", "Mã linh kiện", "Tên linh kiện", "Ngày sản xuất", "Nhà sản xuất",
				"Đơn giá", "Số lượng", };
		productListModel = new DefaultTableModel(productColumnName, 0);
		productListTable = new JTable(productListModel);
		JScrollPane spProductList = new JScrollPane(productListTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spProductList.getViewport().setBackground(Color.WHITE);
		pnProductList.add(spProductList);

		btnPay.addActionListener(this);
		btnBack.addActionListener(this);
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnSearch.addActionListener(this);
		productListTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				addProduct();
			}
		});
		setProductList();
		useBill();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI().setVisible(true);
		} else if (o.equals(btnAdd)) {
			addProduct();
		} else if (o.equals(btnRemove)) {
			int row = orderListTable.getSelectedRow();
			if (row != -1) {
				int serial = Integer.parseInt(productListModel.getValueAt(row, 0).toString());
				BillDetail billDetail = orderList.get(serial - 1);
				if (billDetail.getQty() > 1) {
					billDetail.setQty(billDetail.getQty() - 1);
				} else {
					orderList.remove(billDetail);
				}
				orderListModel.getDataVector().removeAllElements();
				orderListModel.fireTableDataChanged();
				loadOrder(orderList);
			} else {
				JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cần xóa");
			}
		} else if (o.equals(btnSearch)) {
			productList = ProductDAO.getInstance().getProductByName(txtProductName.getText());
			loadTableProduct();
		} else if (o.equals(btnPay)) {
			int billId = Integer.parseInt(txtBillid.getText());
			CreateBillDAO.getInstance().payBill(billId,Integer.parseInt(discountPercent.getValue().toString()), billTotalDue, txtCustomerName.getText(), txtPhoneNumber.getText(), txtAddress.getText());
			for(BillDetail curBillDetail : orderList) {
				CreateBillDAO.getInstance().payBillDetail(billId,curBillDetail);
			}
			JOptionPane.showMessageDialog(null, "Tạo đơn thành công");
			orderListModel.getDataVector().removeAllElements();
			orderListModel.fireTableDataChanged();
			orderList.removeAll(orderList);
			useBill();
		}

	}

	public void setProductList() {
		productList = ProductDAO.getInstance().getListProduct();
		loadTableProduct();
	}

	public void loadTableProduct() {
		productListModel.getDataVector().removeAllElements();
		productListModel.fireTableDataChanged();
		loadProduct(productList);
	}

	private void loadProduct(ArrayList<Product> list) {
		int serial = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		DecimalFormat df = new DecimalFormat("#,###.##");
		for (Product product : list) {
			productListModel.addRow(new Object[] { serial++, product.getProductId(), product.getProductName(),
					sdf.format(product.getMfg()), product.getMfger(), df.format(product.getPrice()),
					product.getQty() });
		}
	}

	private void loadOrder(ArrayList<BillDetail> list) {
		int serial = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		DecimalFormat df = new DecimalFormat("#,###.##");
		BigDecimal subTotal = new BigDecimal(0);
		for (BillDetail billDetail : list) {
			BigDecimal total = billDetail.getPrice().multiply(new BigDecimal(billDetail.getQty()));
			orderListModel.addRow(new Object[] { serial++, billDetail.getProductId(), billDetail.getProductName(),
					df.format(billDetail.getPrice()), billDetail.getQty(), df.format(total) });
			subTotal = subTotal.add(total);
		}
		billSubtotal = subTotal;
		txtSubTotal.setText(df.format(subTotal));
		billTotalDue = subTotal
				.multiply(new BigDecimal(100 - Double.parseDouble(discountPercent.getValue().toString())))
				.divide(new BigDecimal(100));
		txtTotalDue.setText(df.format(billTotalDue));
	}

	private void useBill() {
		CreateBillDAO.getInstance().createBill();
		txtBillid.setText(Integer.toString(CreateBillDAO.getInstance().getNewBill()));
	}

	private BillDetail getBillDetail() {
		int row = productListTable.getSelectedRow();
		int serial = Integer.parseInt(productListModel.getValueAt(row, 0).toString());
		Product product = productList.get(serial - 1);
		return new BillDetail(product.getProductId(), product.getProductName(), 1, product.getPrice());
	}

	private void addProduct() {
		BillDetail newBillDetail = getBillDetail();
		if (orderList.contains(newBillDetail)) {
			BillDetail temp = orderList.get(orderList.indexOf(newBillDetail));
			temp.setQty(temp.getQty() + 1);
		} else {
			orderList.add(newBillDetail);
		}
		orderListModel.getDataVector().removeAllElements();
		orderListModel.fireTableDataChanged();
		loadOrder(orderList);
	}
}
