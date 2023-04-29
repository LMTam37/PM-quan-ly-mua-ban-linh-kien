package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.BillListDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import entity.Bill;
import entity.BillDetail;
import entity.Emp;
import entity.Product;
import javax.swing.JTextField;

public class BillList_UI extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1363319712600467037L;
	private JPanel pnTitle, pnStatistic, pnBillList, pnBillDetail, pnStatisticIncome;
	private JLabel lblTitle, lblToDate, lblDateFrom, lblCategory, lblProduct, lblIncome;
	private JTable tableBillList, tableBillDetail;
	private DefaultTableModel modelBillList, modelBillDetail;
	private JComboBox<String> cbCategory, cbProduct;
	private JDateChooser dcFromDate, dcToDate;
	private JButton btnStatistic, btnBack;
	private ArrayList<Bill> billList;
	private ArrayList<BillDetail> billDetailList;
	private Emp curAccount;
	private JTextField txtIncome;
	private ArrayList<Product> listProduct;

	public BillList_UI(Emp account) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		curAccount = account;

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 40);
		getContentPane().add(pnTitle);

		lblTitle = new JLabel("Quán lý hóa đơn");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnTitle.add(lblTitle);

		pnStatistic = new JPanel();
		pnStatistic.setLayout(null);
		pnStatistic.setBorder(new TitledBorder(null, "Thống kê", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnStatistic.setBackground(Color.WHITE);
		pnStatistic.setBounds(0, 50, 1386, 109);
		getContentPane().add(pnStatistic);

		lblDateFrom = new JLabel("Từ ngày");
		lblDateFrom.setBounds(58, 81, 74, 13);
		pnStatistic.add(lblDateFrom);

		dcFromDate = new JDateChooser();
		dcFromDate.setDate(new java.util.Date());
		dcFromDate.setBounds(142, 78, 96, 19);
		pnStatistic.add(dcFromDate);

		dcToDate = new JDateChooser();
		dcToDate.setDate(new java.util.Date());
		dcToDate.setBounds(394, 78, 96, 19);
		pnStatistic.add(dcToDate);

		lblToDate = new JLabel("Đến ngày");
		lblToDate.setBounds(311, 81, 74, 13);
		pnStatistic.add(lblToDate);

		btnStatistic = new JButton("Thống kế");
		btnStatistic.setBounds(529, 78, 102, 21);
		pnStatistic.add(btnStatistic);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(664, 78, 102, 21);
		pnStatistic.add(btnBack);

		lblCategory = new JLabel("Loại");
		lblCategory.setBounds(58, 27, 74, 13);
		pnStatistic.add(lblCategory);

		cbCategory = new JComboBox<>(new Vector<>(CategoryDAO.getInstance().getListCategory()));
		cbCategory.setBounds(142, 23, 126, 21);
		pnStatistic.add(cbCategory);

		lblProduct = new JLabel("Linh kiện");
		lblProduct.setBounds(311, 27, 74, 13);
		pnStatistic.add(lblProduct);

		cbProduct = new JComboBox<String>();
		cbProduct.setBounds(395, 23, 206, 21);
		pnStatistic.add(cbProduct);

		loadCbProduct(listProduct = ProductDAO.getInstance().getListProduct());

		cbCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbCategory.getSelectedIndex() == 0) {
					listProduct = ProductDAO.getInstance().getListProduct();
					loadCbProduct(listProduct);
				} else {
					listProduct = ProductDAO.getInstance().getListProductByCategory(cbCategory.getSelectedIndex());
					loadCbProduct(listProduct);
				}
			}
		});

		pnBillList = new JPanel();
		pnBillList.setBackground(new Color(255, 255, 255));
		pnBillList.setBorder(
				new TitledBorder(null, "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBillList.setBounds(0, 167, 1386, 233);
		getContentPane().add(pnBillList);
		pnBillList.setLayout(null);

		String[] billListColumnName = { "STT", "Mã hóa đơn", "Tên khách hàng", "Ngày mua", "Tên nhân viên", "Giảm giá",
				"Thành tiền" };
		modelBillList = new DefaultTableModel(billListColumnName, 0);
		tableBillList = new JTable(modelBillList);

		JScrollPane spBillList = new JScrollPane(tableBillList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spBillList.setBounds(10, 20, 1376, 203);
		spBillList.getViewport().setBackground(Color.WHITE);
		pnBillList.add(spBillList);

		spBillList.setViewportView(tableBillList);

		pnBillDetail = new JPanel();
		pnBillDetail.setBackground(new Color(255, 255, 255));
		pnBillDetail.setLayout(null);
		pnBillDetail.setBorder(
				new TitledBorder(null, "Chi tiết hoa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBillDetail.setBounds(0, 410, 1386, 213);
		getContentPane().add(pnBillDetail);

		String[] billDetailColumnName = { "STT", "Tên linh kiện", "Tên loại", "Số lượng", "Đơn giá" };
		modelBillDetail = new DefaultTableModel(billDetailColumnName, 0);
		tableBillDetail = new JTable(modelBillDetail);
		JScrollPane spBillDetail = new JScrollPane(tableBillDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spBillDetail.setBounds(10, 23, 1366, 180);
		spBillDetail.getViewport().setBackground(Color.WHITE);
		pnBillDetail.add(spBillDetail);

		pnStatisticIncome = new JPanel();
		pnStatisticIncome.setBackground(new Color(255, 255, 255));
		pnStatisticIncome.setBounds(0, 623, 1386, 40);
		getContentPane().add(pnStatisticIncome);
		pnStatisticIncome.setLayout(null);

		lblIncome = new JLabel("Tổng doanh thu");
		lblIncome.setBounds(541, 11, 125, 19);
		pnStatisticIncome.add(lblIncome);

		txtIncome = new JTextField();
		txtIncome.setBounds(676, 11, 204, 19);
		pnStatisticIncome.add(txtIncome);
		txtIncome.setColumns(10);

		btnStatistic.addActionListener(this);
		btnBack.addActionListener(this);
		tableBillList.addMouseListener(this);
		tableBillDetail.addMouseListener(this);

		setTableBillList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		} else if (o.equals(btnStatistic)) {
			Product curProduct = listProduct.get(cbProduct.getSelectedIndex() - 1);
			if (cbCategory.getSelectedIndex() == 0 && cbProduct.getSelectedIndex() == 0) {
				billList = BillListDAO.getInstance().getListBillByDate(new Date(dcFromDate.getDate().getTime()),
						new Date(dcToDate.getDate().getTime()));
				loadTableBill(billList);
			} else if (cbProduct.getSelectedIndex() == 0) {
				billList = BillListDAO.getInstance().statisticByCategory(cbCategory.getSelectedIndex(),
						new Date(dcFromDate.getDate().getTime()), new Date(dcToDate.getDate().getTime()));
				loadTableBill(billList);
			} else {
				billList = BillListDAO.getInstance().statisticByProduct(curProduct.getProductId(),
						new Date(dcFromDate.getDate().getTime()), new Date(dcToDate.getDate().getTime()));
				loadTableBill(billList);
			}
		}
	}

	private void setTableBillList() {
		billList = BillListDAO.getInstance().getListBill();
		loadTableBill(billList);
	}

	private void loadTableBill(ArrayList<Bill> list) {
		modelBillList.getDataVector().removeAllElements();
		modelBillList.fireTableDataChanged();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#,###.##");
		int serial = 0;
		for (Bill bill : list) {
			modelBillList.addRow(new Object[] { Integer.toString(serial++), Integer.toString(bill.getBillId()),
					bill.getCustomerName(), sdf.format(bill.getPurchaseDate()), bill.getEmpName(), bill.getDiscount(),
					df.format(bill.getTotal()) });
		}
		txtIncome.setText(df.format(getTotalIncome()));
	}

	private void setTableBillDetail(int billId) {
		billDetailList = BillListDAO.getInstance().getListBillDetail(billId);
		loadTableBillDetail(billDetailList);
	}

	private void loadTableBillDetail(ArrayList<BillDetail> list) {
		modelBillDetail.getDataVector().removeAllElements();
		modelBillDetail.fireTableDataChanged();
		int serial = 0;
		for (BillDetail billDetail : list) {
			modelBillDetail.addRow(new Object[] { Integer.toString(serial++), billDetail.getProductName(),
					billDetail.getCategory(), billDetail.getQty(), billDetail.getPrice() });
		}
	}

	private void loadCbProduct(ArrayList<Product> list) {
		cbProduct.removeAllItems();
		cbProduct.addItem("Tất cả");
		for (Product curProduct : list) {
			cbProduct.addItem(curProduct.getProductName());
		}
	}

	private BigDecimal getTotalIncome() {
		BigDecimal total = new BigDecimal(0);
		for (Bill curBill : billList) {
			total = total.add(curBill.getTotal());
		}
		return total;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableBillList.getSelectedRow();
		int empId = Integer.parseInt((String) tableBillList.getValueAt(row, 1));
		setTableBillDetail(empId);
		System.out.println("click");
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
