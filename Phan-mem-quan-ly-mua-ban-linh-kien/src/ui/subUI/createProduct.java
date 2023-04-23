package ui.subUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;

import dao.ProductDAO;
import entity.Product;
import ui.Product_UI;
import javax.swing.JComboBox;

public class createProduct extends JDialog implements ActionListener {
	private JLabel lblProductName, lblCategory, lblMFG, lblMFGer, lblQty, lblPrice;
	private JTextField txtProductName, txtMFGer;
	private JComboBox cbCategory;
	private JButton btnCreate, btnCancel;
	private JDateChooser dcMFG;
	private JSpinner qtySpinner;
	private JSpinner priceSpinner;
	private int id;
	private int flag = 0;

	public createProduct(int productId, String productName, java.util.Date mfg, String mfger, int qty,
			BigDecimal price) {
		createGui();
		id = productId;
		txtProductName.setText(productName);
		dcMFG.setDate(mfg);
		txtMFGer.setText(mfger);
		qtySpinner.setValue(qty);
		priceSpinner.setValue(price);
		flag = 1;
	}

	public createProduct() {
		createGui();
	}

	private void createGui() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Tạo tài khoản");
		setSize(350, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel pnMain = new JPanel();
		getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.setLayout(null);

		lblProductName = new JLabel("Tên linh kiện");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProductName.setBounds(10, 30, 148, 13);
		pnMain.add(lblProductName);
		lblMFG = new JLabel("Ngày sản xuất");
		lblMFG.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMFG.setBounds(10, 70, 148, 13);
		pnMain.add(lblMFG);

		lblCategory = new JLabel("Loại");
		lblCategory.setBounds(10, 110, 148, 13);
		pnMain.add(lblCategory);

		lblMFGer = new JLabel("Hãng sản xuất");
		lblMFGer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMFGer.setBounds(10, 150, 148, 13);
		pnMain.add(lblMFGer);

		lblQty = new JLabel("Số lượng");
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQty.setBounds(10, 190, 148, 13);
		pnMain.add(lblQty);

		lblPrice = new JLabel("Giá (VND)");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(10, 230, 148, 13);
		pnMain.add(lblPrice);

		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtProductName.setBounds(168, 27, 158, 19);
		pnMain.add(txtProductName);
		txtProductName.setColumns(10);

		cbCategory = new JComboBox();
		cbCategory.addItem("CPU");
		cbCategory.addItem("RAM");
		cbCategory.addItem("VGA");
		cbCategory.addItem("Mainboard");
		cbCategory.addItem("Nguồn máy tính");
		cbCategory.addItem("Ổ cứng");
		cbCategory.setBounds(168, 107, 158, 21);
		pnMain.add(cbCategory);

		dcMFG = new JDateChooser();
		dcMFG.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dcMFG.setBounds(168, 67, 158, 19);
		pnMain.add(dcMFG);

		txtMFGer = new JTextField();
		txtMFGer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMFGer.setBounds(168, 147, 158, 19);
		pnMain.add(txtMFGer);
		txtMFGer.setColumns(10);

		qtySpinner = new JSpinner();
		qtySpinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		qtySpinner.setBounds(168, 187, 158, 20);
		pnMain.add(qtySpinner);

		btnCreate = new JButton("Tạo");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCreate.setBounds(50, 256, 93, 21);
		pnMain.add(btnCreate);

		btnCancel = new JButton("Hủy");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancel.setBounds(200, 256, 93, 21);
		pnMain.add(btnCancel);

		priceSpinner = new JSpinner();
		priceSpinner.setModel(new SpinnerNumberModel(0d, 0d, null, 0.01d));
		priceSpinner.setBounds(168, 227, 158, 20);
		pnMain.add(priceSpinner);

		btnCreate.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreate)) {
			if (isEmpty(txtProductName) || isEmpty(txtMFGer)) {
				JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin");
			} else {
				if (flag == 0) {
					ProductDAO.getInstance().addProduct(txtProductName.getText(), cbCategory.getSelectedIndex() + 1,
							new Date(dcMFG.getDate().getTime()), txtMFGer.getText(), (Integer) qtySpinner.getValue(),
							new BigDecimal(priceSpinner.getValue().toString()));
					JOptionPane.showMessageDialog(null, "Tạo linh kiện thành công");
				} else {
					ProductDAO.getInstance().updateProduct(id, txtProductName.getText(),
							cbCategory.getSelectedIndex() + 1, new Date(dcMFG.getDate().getTime()), txtMFGer.getText(),
							(Integer) qtySpinner.getValue(), 
							new BigDecimal(priceSpinner.getValue().toString()));
				}
				this.setVisible(false);
			}
		} else if (o.equals(btnCancel)) {
			this.setVisible(false);
		}
	}

	private boolean isEmpty(JTextField txt) {
		if (txt.getText().trim().equals("")) {
			txt.requestFocus();
			return true;
		}
		return false;
	}
}
