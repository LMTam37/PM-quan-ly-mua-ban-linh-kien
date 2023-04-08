package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LinhKien_UI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel pnBorder, pnNorth, pnCenter, pnSouth;
	JLabel lblMaSP, lblTenSP, lblNgaySX, lblTinhTrang, lblSoLuong, lblDonGia, lblNhaSX, lblTieuDe, lblTim;
	JComboBox cbTinhTrang;
	JTextField txtMaSP, txtTenSP, txtNgaySX, txtSoLuong, txtDonGia, txtNhaSX, txtTim;
	JButton timBtn, themBtn, xoaTrangBtn, xoaBtn, luuBtn;
	DefaultTableModel model;
	JTable table;

	public LinhKien_UI() {
		setTitle("Thong Tin San Pham");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		createGui();
	}

	public void createGui() {
		add(pnBorder = new JPanel());
		pnBorder.setLayout(new BorderLayout());
		// North
		pnBorder.add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.add(lblTieuDe = new JLabel("THÔNG TIN SẢN PHẨM"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
		lblTieuDe.setForeground(Color.BLACK);
		pnNorth.setBackground(Color.decode("#14FFEC"));
		// Center
		pnBorder.add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thong Tin San Pham"));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		pnCenter.add(b);
		pnCenter.add(Box.createVerticalStrut(10));
		b1.add(lblMaSP = new JLabel("Mã Sản Phẩm:"));
		lblMaSP.setPreferredSize(new Dimension(100, 20));
		b1.add(txtMaSP = new JTextField(""));
		b1.add(lblTenSP = new JLabel("Tên Linh Kiện:"));
		b1.add(txtTenSP = new JTextField(""));
		b2.add(lblNhaSX = new JLabel("Nhà Sản Xuất:"));
		lblNhaSX.setPreferredSize(lblMaSP.getPreferredSize());
		b2.add(txtNhaSX = new JTextField(""));
		b2.add(lblDonGia = new JLabel("Đơn Giá:"));
		b2.add(txtDonGia = new JTextField(""));
		b2.add(lblTinhTrang = new JLabel("Tình Trạng:"));
		lblTinhTrang.setPreferredSize(lblMaSP.getPreferredSize());
		b2.add(cbTinhTrang = new JComboBox());
		cbTinhTrang.addItem("Còn hàng");
		cbTinhTrang.addItem("Hết hàng");
		b3.add(lblSoLuong = new JLabel("Số Lượng:"));
		lblSoLuong.setPreferredSize(lblMaSP.getPreferredSize());
		b3.add(txtSoLuong = new JTextField(""));
		b3.add(lblNhaSX = new JLabel("Ngày Sản Xuất:"));
		b3.add(txtNhaSX = new JTextField(""));
		// Table
		createTable();
		// South
		pnBorder.add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		pnSouth.add(lblTim = new JLabel("Nhập mã sản phẩm cần tìm:"));
		pnSouth.add(txtTim = new JTextField(20));
		pnSouth.add(timBtn = new JButton("Tìm"));
		pnSouth.add(themBtn = new JButton("Thêm"));
		pnSouth.add(xoaTrangBtn = new JButton("Xóa trắng"));
		pnSouth.add(xoaBtn = new JButton("Xóa"));
		pnSouth.add(luuBtn = new JButton("Lưu"));
		//	JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//		pnBorder.add(split, BorderLayout.SOUTH);
//		split.add(pnFind = new JPanel());
//		pnFind.add(lblFind = new JLabel("Tim Theo Ma Xe:"));
//		pnFind.add(txtFind = new JTextField(10));
//		pnFind.add(btLocTheoMaXe = new JButton("Loc Theo Ma Xe"));
		
		timBtn.addActionListener(this);
		themBtn.addActionListener(this);
		xoaTrangBtn.addActionListener(this);
		xoaBtn.addActionListener(this);
		luuBtn.addActionListener(this);
	}

	public static void main(String[] args) {
		new LinhKien_UI().setVisible(true);
	}

  
	public void createTable() {
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã Sản Phẩm");
		model.addColumn("Tên Sản Phẩm");
		model.addColumn("Nhà Sản Xuất");
		model.addColumn("Đơn Giá");
		model.addColumn("Tình Trạng");
		model.addColumn("Số Lượng");
		model.addColumn("Ngày Sản Xuất");
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnCenter.add(sp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
