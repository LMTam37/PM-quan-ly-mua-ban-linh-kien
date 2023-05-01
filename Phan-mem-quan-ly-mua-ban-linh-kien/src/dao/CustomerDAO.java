package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Customer;

public class CustomerDAO {
	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}

	public ArrayList<Customer> getListCustomer() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM KhachHang";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("MaKhachHang");
				String name = rs.getString("TenKhachHang");
				String phoneNumber = rs.getString("SoDienThoai");
				String address = rs.getString("DiaChi");
				list.add(new Customer(id, name, phoneNumber, address));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Customer> getListCustomerBySearch(String name) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM KhachHang WHERE TenKhachHang LIKE ?");
			pst.setString(1, "%" + name + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Customer(rs.getInt("MaKhachHang"), rs.getString("TenKhachHang"),
						rs.getString("SoDienThoai"), rs.getString("DiaChi")));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int createCustomer(String name, String phoneNumber, String address) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO KhachHang VALUES (?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, phoneNumber);
			pst.setString(3, address);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCustomer(int id, String name, String phoneNumber, String address) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"UPDATE KhachHang SET TenKhachHang = ?, SoDienThoai = ?, DiaChi = ? WHERE MaKhachHang = ?");
			pst.setString(1, name);
			pst.setString(2, phoneNumber);
			pst.setString(3, address);
			pst.setInt(4, id);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCustomer(int id) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
