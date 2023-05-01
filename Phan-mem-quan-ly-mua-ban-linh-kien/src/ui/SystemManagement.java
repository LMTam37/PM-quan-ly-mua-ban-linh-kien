package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import entity.Emp;
import ui.panelView.PnBill;
import ui.panelView.PnCategory;
import ui.panelView.PnCustomer;
import ui.panelView.PnEmp;
import ui.panelView.PnProduct;

public class SystemManagement extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PnBill pnBill;
	private PnProduct pnProduct;
	private PnCategory pnCategory;
	private PnEmp pnEmp;
	private PnCustomer pnCustomer;
	private Emp curAccount;

	public SystemManagement(Emp account) {
		setBackground(new Color(255, 255, 255));
		setTitle("Quản trị hệ thống");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1400, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		curAccount = account;
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tbManage = new JTabbedPane();

		pnBill = new PnBill(account);
		pnProduct = new PnProduct(account);
		pnCategory = new PnCategory(account);
		pnEmp = new PnEmp(account);
		pnCustomer = new PnCustomer(account);
		tbManage.addTab("Hóa đơn", null, pnBill, "Thông tin hóa đơn");
		tbManage.addTab("Linh kiện", null, pnProduct, "Thông tin linh kiện");
		tbManage.addTab("Loại linh kiện", null, pnCategory, "Thông tin loại linh kiện");
		tbManage.addTab("Nhân viên", null, pnEmp, "Thông tin nhân viên");
		tbManage.addTab("Khách hàng", null, pnCustomer, "Thông tin khách hàng");
		pnBill.getBtnBack().addActionListener(this);
		pnProduct.getBtnBack().addActionListener(this);
		pnCategory.getBtnBack().addActionListener(this);
		pnEmp.getBtnBack().addActionListener(this);
		pnCustomer.getBtnBack().addActionListener(this);

		getContentPane().add(tbManage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(pnBill.getBtnBack()) || o.equals(pnProduct.getBtnBack()) || o.equals(pnCategory.getBtnBack())
				|| o.equals(pnEmp.getBtnBack()) || o.equals(pnCustomer.getBtnBack())) {
			setVisible(false);
			new Feature_UI(curAccount).setVisible(true);
		}
	}
}
