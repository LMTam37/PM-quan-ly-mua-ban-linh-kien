package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import java.awt.Panel;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import javax.swing.SpinnerNumberModel;

public class CreateBill_UI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnPay, btnClear, btnBack;
	private JTextField txtBillid, txtBillDate, txtCustomerName, txtTotal, txtToPay, txtProductName, txtEmpName;
	private JTable orderListTable, productListTable;
	private DefaultTableModel orderListModel, productListModel;

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

		JPanel pnHeader = new JPanel();
		pnHeader.setBackground(new Color(0, 128, 255));
		pnHeader.setBounds(0, 0, 1386, 40);
		getContentPane().add(pnHeader);
		pnHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTieuDe = new JLabel("Tạo đơn hàng");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnHeader.add(lblTieuDe);

		JPanel pnBill = new JPanel();
		pnBill.setBounds(0, 46, 698, 617);
		getContentPane().add(pnBill);
		pnBill.setLayout(new BorderLayout());

		JPanel pnCustomerInfo = new JPanel();
		pnCustomerInfo.setBackground(new Color(255, 255, 255));
		pnCustomerInfo.setBorder(
				new TitledBorder(null, "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBill.add(pnCustomerInfo, BorderLayout.NORTH);
		pnCustomerInfo.setPreferredSize(new Dimension(500, 170));
		pnCustomerInfo.setLayout(null);

		JLabel lblBillid = new JLabel("Mã hóa đơn:");
		lblBillid.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBillid.setBounds(49, 25, 85, 13);
		pnCustomerInfo.add(lblBillid);

		txtBillid = new JTextField();
		txtBillid.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtBillid.setEditable(false);
		txtBillid.setBounds(144, 22, 96, 19);
		pnCustomerInfo.add(txtBillid);
		txtBillid.setColumns(10);

		JLabel lblBillDate = new JLabel("Ngày mua:");
		lblBillDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBillDate.setBounds(49, 83, 85, 13);
		pnCustomerInfo.add(lblBillDate);

		txtBillDate = new JTextField();
		txtBillDate.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtBillDate.setEditable(false);
		txtBillDate.setBounds(144, 80, 96, 19);
		pnCustomerInfo.add(txtBillDate);
		txtBillDate.setColumns(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String dateFormated = formatter.format(LocalDate.now());
		txtBillDate.setText(dateFormated);

		JLabel lblCustomeName = new JLabel("Tên khách hàng");
		lblCustomeName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCustomeName.setBounds(49, 55, 85, 13);
		pnCustomerInfo.add(lblCustomeName);

		txtCustomerName = new JTextField();
		txtCustomerName.setEnabled(false);
		txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCustomerName.setBounds(144, 51, 96, 19);
		pnCustomerInfo.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		JLabel lblEmpName = new JLabel("Tên nhân viên");
		lblEmpName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmpName.setBounds(290, 55, 85, 13);
		pnCustomerInfo.add(lblEmpName);

		txtEmpName = new JTextField();
		txtEmpName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEmpName.setColumns(10);
		txtEmpName.setBounds(385, 52, 126, 19);
		pnCustomerInfo.add(txtEmpName);

		JLabel lblTotal = new JLabel("Tổng tiền:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTotal.setBounds(49, 112, 85, 13);
		pnCustomerInfo.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTotal.setEditable(false);
		txtTotal.setBounds(144, 109, 96, 19);
		pnCustomerInfo.add(txtTotal);
		txtTotal.setColumns(10);

		JLabel lblTotalPay = new JLabel("Tiền cần trả:");
		lblTotalPay.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTotalPay.setBounds(49, 142, 85, 13);
		pnCustomerInfo.add(lblTotalPay);

		txtToPay = new JTextField();
		txtToPay.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtToPay.setEditable(false);
		txtToPay.setBounds(144, 138, 96, 19);
		pnCustomerInfo.add(txtToPay);
		txtToPay.setColumns(10);

		JLabel lblDiscount = new JLabel("Giảm giá:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDiscount.setBounds(290, 86, 85, 13);
		pnCustomerInfo.add(lblDiscount);

		JSpinner percentSpinner = new JSpinner();
		percentSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		percentSpinner.setFont(new Font("Tahoma", Font.BOLD, 10));
		percentSpinner.setBounds(385, 83, 30, 20);
		pnCustomerInfo.add(percentSpinner);

		btnPay = new JButton("Thanh toán");
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPay.setBounds(549, 25, 139, 21);
		pnCustomerInfo.add(btnPay);

		btnBack = new JButton("Thoát");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBack.setBounds(549, 134, 139, 21);
		pnCustomerInfo.add(btnBack);

		btnClear = new JButton("Xóa trắng");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClear.setBounds(549, 79, 139, 21);
		pnCustomerInfo.add(btnClear);

		JLabel lblTypeCurrency = new JLabel("VND");
		lblTypeCurrency.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTypeCurrency.setBounds(250, 142, 30, 13);
		pnCustomerInfo.add(lblTypeCurrency);

		JLabel lblTypeCurrency_1 = new JLabel("VND");
		lblTypeCurrency_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTypeCurrency_1.setBounds(250, 115, 30, 13);
		pnCustomerInfo.add(lblTypeCurrency_1);

		JLabel lblPercent = new JLabel("%");
		lblPercent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPercent.setBounds(425, 87, 19, 13);
		pnCustomerInfo.add(lblPercent);

		JPanel pnOrderList = new JPanel();
		pnOrderList.setBackground(new Color(255, 255, 255));
		pnOrderList.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch linh ki\u1EC7n \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnBill.add(pnOrderList);
		pnOrderList.setLayout(new BorderLayout(0, 0));

		String[] orderColumnName = { "STT", "Mã linh kiện", "Tên linh kiện", "Ngày sản xuất", "Nhà sản xuất", "Đơn giá",
				"Số lượng", "Thành tiền" };
		orderListModel = new DefaultTableModel(orderColumnName, 0);
		orderListTable = new JTable(orderListModel);
		JScrollPane spOrderList = new JScrollPane(orderListTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spOrderList.getViewport().setBackground(Color.WHITE);
		pnOrderList.add(spOrderList);

		Panel pnProduct = new Panel();
		pnProduct.setBounds(704, 46, 682, 607);
		getContentPane().add(pnProduct);
		pnProduct.setLayout(new BorderLayout(0, 0));

		JPanel pnProductInfo = new JPanel();
		pnProductInfo.setBackground(new Color(255, 255, 255));
		pnProductInfo.setBorder(
				new TitledBorder(null, "Thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnProduct.add(pnProductInfo, BorderLayout.NORTH);
		pnProductInfo.setPreferredSize(new Dimension(400, 110));
		pnProductInfo.setLayout(null);

		JLabel lblProductName = new JLabel("Tên linh kiện");
		lblProductName.setBounds(52, 30, 82, 13);
		pnProductInfo.add(lblProductName);

		JLabel lblQuantity = new JLabel("Số lượng");
		lblQuantity.setBounds(52, 69, 65, 13);
		pnProductInfo.add(lblQuantity);

		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtProductName.setBounds(144, 27, 140, 19);
		pnProductInfo.add(txtProductName);
		txtProductName.setColumns(10);

		JSpinner quantitySpinner = new JSpinner();
		quantitySpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		quantitySpinner.setFont(new Font("Tahoma", Font.BOLD, 10));
		quantitySpinner.setBounds(144, 66, 56, 20);
		pnProductInfo.add(quantitySpinner);

		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setBounds(501, 17, 140, 21);
		pnProductInfo.add(btnNewButton);

		JButton btnThmVon = new JButton("Thêm");
		btnThmVon.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnThmVon.setBounds(501, 48, 140, 21);
		pnProductInfo.add(btnThmVon);

		JButton btnXa = new JButton("Xóa");
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnXa.setBounds(501, 79, 140, 21);
		pnProductInfo.add(btnXa);

		JPanel pnProductList = new JPanel();
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
		btnClear.addActionListener(this);
		btnBack.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnBack)){
			this.setVisible(false);
			new Feature_UI().setVisible(true);
		}
		
	}
}
