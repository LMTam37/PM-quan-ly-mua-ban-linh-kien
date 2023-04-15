package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
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

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.toedter.calendar.JDateChooser;

import dao.BillListDAO;
import entity.Bill;
import entity.BillDetail;

public class BillList_UI extends JFrame implements ActionListener, MouseListener {

	private JPanel pnTitle, pnStatistic, pnBillList, pnBillDetail;
	private JLabel lblTitle, lblToDate, lblDateFrom;
	private JTable tableBillList, tableBillDetail;
	private DefaultTableModel modelBillList, modelBillDetail;
	private JDateChooser dcFromDate, dcToDate;
	private JButton btnStatistic, btnBack;
	private ArrayList<Bill> billList;
	private ArrayList<BillDetail> billDetailList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillList_UI frame = new BillList_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillList_UI() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0, 128, 255));
		pnTitle.setBounds(0, 0, 1386, 40);
		getContentPane().add(pnTitle);

		lblTitle = new JLabel("Quán lý hóa đơn");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 10));
		pnTitle.add(lblTitle);

		pnStatistic = new JPanel();
		pnStatistic.setLayout(null);
		pnStatistic.setBorder(
				new TitledBorder(null, "Thống kê", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnStatistic.setBackground(Color.WHITE);
		pnStatistic.setBounds(0, 50, 1386, 40);
		getContentPane().add(pnStatistic);

		lblDateFrom = new JLabel("Từ ngày");
		lblDateFrom.setBounds(58, 16, 74, 13);
		pnStatistic.add(lblDateFrom);

		dcFromDate = new JDateChooser();
		dcFromDate.setBounds(142, 13, 96, 19);
		pnStatistic.add(dcFromDate);

		dcToDate = new JDateChooser();
		dcToDate.setBounds(364, 13, 96, 19);
		pnStatistic.add(dcToDate);

		lblToDate = new JLabel("Đến ngày");
		lblToDate.setBounds(281, 16, 74, 13);
		pnStatistic.add(lblToDate);

		btnStatistic = new JButton("Thống kế");
		btnStatistic.setBounds(499, 13, 102, 21);
		pnStatistic.add(btnStatistic);

		btnBack = new JButton("Thoát");
		btnBack.setBounds(634, 13, 102, 21);
		pnStatistic.add(btnBack);

		pnBillList = new JPanel();
		pnBillList.setBackground(new Color(255, 255, 255));
		pnBillList.setBorder(
				new TitledBorder(null, "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBillList.setBounds(0, 95, 1386, 299);
		getContentPane().add(pnBillList);
		pnBillList.setLayout(null);

		String[] billListColumnName = { "STT", "Mã hóa đơn", "Tên khách hàng", "Ngày mua", "Tên nhân viên", "Giảm giá",
				"Thành tiền" };
		modelBillList = new DefaultTableModel(billListColumnName, 0);
		tableBillList = new JTable(modelBillList);

		JScrollPane spBillList = new JScrollPane(tableBillList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spBillList.setBounds(10, 20, 1376, 259);
		spBillList.getViewport().setBackground(Color.WHITE);
		pnBillList.add(spBillList);

		spBillList.setViewportView(tableBillList);

		pnBillDetail = new JPanel();
		pnBillDetail.setBackground(new Color(255, 255, 255));
		pnBillDetail.setLayout(null);
		pnBillDetail.setBorder(
				new TitledBorder(null, "Chi tiết hoa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBillDetail.setBounds(0, 404, 1386, 259);
		getContentPane().add(pnBillDetail);

		String[] billDetailColumnName = { "STT", "Tên linh kiện", "Số lượng", "Đơn giá" };
		modelBillDetail = new DefaultTableModel(billDetailColumnName, 0);
		tableBillDetail = new JTable(modelBillDetail);
		JScrollPane spBillDetail = new JScrollPane(tableBillDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spBillDetail.setBounds(10, 23, 1366, 226);
		spBillDetail.getViewport().setBackground(Color.WHITE);
		pnBillDetail.add(spBillDetail);

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
			new Feature_UI().setVisible(true);
		}else if(o.equals(btnStatistic)) {
			if(dcFromDate.getDate() == null || dcToDate.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Hãy chọn ngày thống kế");
			}else {				
				billList = BillListDAO.getInstance().getListBillByDate(new Date(dcFromDate.getDate().getTime()), new Date(dcToDate.getDate().getTime()));
				modelBillList.getDataVector().removeAllElements();
				modelBillList.fireTableDataChanged();
				loadTableBill(billList);
			}
		}
	}

	private void setTableBillList() {
		billList = BillListDAO.getInstance().getListBill();
		modelBillList.getDataVector().removeAllElements();
		modelBillList.fireTableDataChanged();
		loadTableBill(billList);
	}

	private void loadTableBill(ArrayList<Bill> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#,###.##");
		int serial = 0;
		for (Bill bill : list) {
			modelBillList.addRow(new Object[] { Integer.toString(serial++), Integer.toString(bill.getBillId()),
					bill.getCustomerName(), sdf.format(bill.getPurchaseDate()), bill.getEmpName(), bill.getDiscount(),
					df.format(bill.getTotal()) });
		}
	}

	private void setTableBillDetail(int billId) {
		billDetailList = BillListDAO.getInstance().getListBillDetail(billId);
		modelBillDetail.getDataVector().removeAllElements();
		modelBillDetail.fireTableDataChanged();
		loadTableBillDetail(billDetailList);
	}

	private void loadTableBillDetail(ArrayList<BillDetail> list) {
		int serial = 0;
		for (BillDetail billDetail : list) {
			modelBillDetail.addRow(new Object[] { Integer.toString(serial++), billDetail.getProductName(),
					billDetail.getQty(), billDetail.getPrice() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableBillList.getSelectedRow();
		int empId = Integer.parseInt((String) tableBillList.getValueAt(row, 1));
		setTableBillDetail(empId);
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