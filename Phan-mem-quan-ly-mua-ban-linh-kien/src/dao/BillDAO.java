package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

import connectDB.ConnectDB;
import entity.Bill;

public class BillDAO {
	public static BillDAO getInstance() {
		return new BillDAO();
	}

	public ArrayList<Bill> getListBill() {
		ArrayList<Bill> list = new ArrayList<Bill>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM DonHang";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int billId = rs.getInt("MaDon");
				int customerId = rs.getInt("MaKhachHang");
				Date purchaseDate = rs.getDate("NgayMua");
				int empId = rs.getInt("MaNhanVien");
				int discount = rs.getInt("GiamGia");
				BigDecimal total = rs.getBigDecimal("ThanhTien");
				list.add(new Bill(billId, customerId, purchaseDate, empId, discount, total));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int createBill(int customerId, Date purchaseDate, int empId, int discount, BigDecimal total) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO DonHang " + "VALUES (?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, customerId);
			pst.setDate(2, purchaseDate);
			pst.setInt(3, empId);
			pst.setInt(4, discount);
			pst.setBigDecimal(5, total);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBill(int customerId) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "DELETE FROM DonHang WHERE MaKhachHang = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, customerId);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
