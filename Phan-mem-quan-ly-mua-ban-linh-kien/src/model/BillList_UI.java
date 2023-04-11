package model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Color;
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
import java.awt.Button;

public class BillList_UI extends JFrame implements ActionListener{

	private JPanel pnTitle, pnStatistic,pnBillList, pnBillDetail ;
	private JLabel lblTitle,lblToDate, lblDateFrom;
	private JTable tableBillList, tableBillDetail;
    private DefaultTableModel modelBillList, modelBillDetail;
    private JTextField txtFromDate, txtToDate;
    private JButton btnStatistic,btnBack;

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
		pnStatistic.setBorder(new TitledBorder(null, "Th\u1ED1ng k\u1EBF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnStatistic.setBackground(Color.WHITE);
		pnStatistic.setBounds(0, 50, 1386, 40);
		getContentPane().add(pnStatistic);
		
		 lblDateFrom = new JLabel("Từ ngày");
		lblDateFrom.setBounds(58, 16, 74, 13);
		pnStatistic.add(lblDateFrom);
		
		txtFromDate = new JTextField();
		txtFromDate.setEditable(false);
		txtFromDate.setColumns(10);
		txtFromDate.setBounds(142, 13, 96, 19);
		pnStatistic.add(txtFromDate);
		
		txtToDate = new JTextField();
		txtToDate.setEditable(false);
		txtToDate.setColumns(10);
		txtToDate.setBounds(364, 13, 96, 19);
		pnStatistic.add(txtToDate);
		
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
		pnBillList.setBorder(new TitledBorder(null, "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBillList.setBounds(0, 95, 1386, 299);
		getContentPane().add(pnBillList);
		pnBillList.setLayout(null);
		
		String []billListColumnName = {"STT", "Mã hóa đơn", "Ngày mua", "Tên nhân viên", "Giảm giá", "Thành tiền"};
		modelBillList = new DefaultTableModel(billListColumnName,0);
		tableBillList = new JTable(modelBillList);
		
		JScrollPane spBillList = new JScrollPane(tableBillList , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spBillList.setBounds(10, 20, 1376, 259);
		spBillList.getViewport().setBackground(Color.WHITE);
		pnBillList.add(spBillList);
		
		spBillList.setViewportView(tableBillList);
		
		pnBillDetail = new JPanel();
		pnBillDetail.setBackground(new Color(255, 255, 255));
		pnBillDetail.setLayout(null);
		pnBillDetail.setBorder(new TitledBorder(null, "Chi tiết hoa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBillDetail.setBounds(0, 404, 1386, 259);
		getContentPane().add(pnBillDetail);
		
		String []billDetailColumnName = {"STT","Mã linh kiện","Số lượng","Đơn giá"};
		modelBillDetail = new DefaultTableModel(billDetailColumnName, 0);
		tableBillDetail = new JTable(modelBillDetail);
		JScrollPane spBillDetail = new JScrollPane(tableBillDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spBillDetail.setBounds(10, 23, 1366, 226);
		spBillDetail.getViewport().setBackground(Color.WHITE);
		pnBillDetail.add(spBillDetail);
		
		btnStatistic.addActionListener(this);
		btnBack.addActionListener(this);
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
