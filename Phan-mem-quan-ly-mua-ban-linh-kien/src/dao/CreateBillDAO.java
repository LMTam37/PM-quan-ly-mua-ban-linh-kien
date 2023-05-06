package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import connectDB.ConnectDB;
import entity.Bill;
import entity.BillDetail;
import entity.Product;

public class CreateBillDAO {
	public static CreateBillDAO getInstance() {
		return new CreateBillDAO();
	}

	public int createBill() {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("EXEC addNewBill");
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int getNewBill() {
		int billId = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("EXEC UpdateBillDate");
			billId = pst.executeUpdate();
			pst = con.prepareStatement("SELECT dbo.GetNewBill() AS MaDon");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				billId = rs.getInt("MaDon");
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return billId;
	}

	public int payBill(int billId, int empId, int discount, String customerName, String phoneNumber,
			String address) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("EXEC PayBill ?, ?, ?, ?, ?, ?");
			pst.setInt(1, billId);
			pst.setInt(2, empId);
			pst.setInt(3, discount);
			pst.setString(4, customerName);
			pst.setString(5, phoneNumber);
			pst.setString(6, address);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int payBillDetail(int billId, BillDetail billDetail) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("EXEC PayBillDetail ?, ?, ?");
			pst.setInt(1, billId);
			pst.setInt(2, billDetail.getProductId());
			pst.setInt(3, billDetail.getQty());
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
