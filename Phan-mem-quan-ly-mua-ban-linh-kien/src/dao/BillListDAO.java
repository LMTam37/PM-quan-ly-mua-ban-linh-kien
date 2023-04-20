package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Bill;
import entity.BillDetail;

public class BillListDAO {
	public static BillListDAO getInstance() {
		return new BillListDAO();
	}

	public ArrayList<Bill> getListBill() {
		ArrayList<Bill> list = new ArrayList<Bill>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HoaDonView";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int billId = rs.getInt("MaDon");
				String customerName = rs.getString("TenKhachHang");
				Date purchaseDate = rs.getDate("NgayMua");
				String empName = rs.getNString("TenNhanVien");
				int discount = rs.getInt("GiamGia");
				BigDecimal total = rs.getBigDecimal("ThanhTien");
				list.add(new Bill(billId, customerName, purchaseDate, empName, discount, total));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Bill> getListBillByDate(Date fromDate, Date toDate) {
		ArrayList<Bill> list = new ArrayList<Bill>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "EXEC GetHoaDonTheoNgay ?,?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, fromDate);
			pst.setDate(2, toDate);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int billId = rs.getInt("MaDon");
				String customerName = rs.getString("TenKhachHang");
				Date purchaseDate = rs.getDate("NgayMua");
				String empName = rs.getNString("TenNhanVien");
				int discount = rs.getInt("GiamGia");
				BigDecimal total = rs.getBigDecimal("ThanhTien");
				list.add(new Bill(billId, customerName, purchaseDate, empName, discount, total));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<BillDetail> getListBillDetail(int billId) {
		ArrayList<BillDetail> list = new ArrayList<BillDetail>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "EXEC GetChiTietHoaDon ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, billId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int billDetailId = rs.getInt("MaChiTietDon");
				String productName = rs.getNString("TenLinhKien");
				String category = rs.getString("TenLoai");
				int qty = rs.getInt("SoLuong");
				BigDecimal price = rs.getBigDecimal("DonGia");
				list.add(new BillDetail(billDetailId, billId, productName,category, qty, price));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
