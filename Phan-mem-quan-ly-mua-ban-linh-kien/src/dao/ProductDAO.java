package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Product;

public class ProductDAO {
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	public ArrayList<Product> getListProduct(int categoryID) {
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("EXEC GetLinhKienByMaLoai ?");
			pst.setInt(1, categoryID);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt("MaLinhKien"), rs.getString("TenLinhKien"), rs.getString("TenLoai"),
						rs.getDate("NgaySanXuat"), rs.getString("HangSanXuat"), rs.getInt("SoLuong"),
						rs.getBigDecimal("DonGia")));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addProduct(String productName, int categoryId, Date mfg, String mfger, int qty, BigDecimal price) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO LinhKien(TenLinhKien, MaLoai,NgaySanXuat, HangSanXuat, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, productName);
			pst.setInt(2, categoryId);
			pst.setDate(3, mfg);
			pst.setString(4, mfger);
			pst.setInt(5, qty);
			pst.setBigDecimal(6, price);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateProduct(int productId, String productName, int categoryId, Date mfg, String mfger, int qty,
			BigDecimal price) {
		int rs = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"Update LinhKien set TenLinhKien = ? , MaLoai = ?, NgaySanXuat = ?,HangSanXuat = ? , SoLuong = ?, DonGia= ? where MaLinhKien = ?");
			pst.setString(1, productName);
			pst.setInt(2, categoryId);
			pst.setDate(3, mfg);
			pst.setString(4, mfger);
			pst.setInt(5, qty);
			pst.setBigDecimal(6, price);
			pst.setInt(7, productId);
			rs = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int removeProduct(int id) {
		int rs = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("DELETE LinhKien WHERE MaLinhKien = ?");
			pst.setInt(1, id);
			rs = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ArrayList<Product> getProductByName(String productName, int categoryId) {
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("GetLinhKienByMaLoaiAndName ?, ?");
			pst.setString(1, productName);
			pst.setInt(2, categoryId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt("MaLinhKien"), rs.getString("TenLinhKien"), rs.getString("TenLoai"),
						rs.getDate("NgaySanXuat"), rs.getString("HangSanXuat"), rs.getInt("SoLuong"),
						rs.getBigDecimal("DonGia")));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
