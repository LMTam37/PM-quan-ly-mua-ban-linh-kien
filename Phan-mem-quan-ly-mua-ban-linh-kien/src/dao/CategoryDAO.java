package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Category;

public class CategoryDAO {
	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}

	public ArrayList<Category> getListCategory() {
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM LoaiLinhKien");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getInt("MaLoai"), rs.getString("TenLoai")));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Category> getCategoryByName(String name) {
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM LoaiLinhKien WHERE TenLoai LIKE ?");
			pst.setString(1, "%" + name + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getInt("MaLoai"), rs.getString("TenLoai")));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public int addCategory(String name) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO LoaiLinhKien(TenLoai) VALUES (?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCategory(int id, String name) {
		int rs = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(
					"Update LoaiLinhKien set TenLoai = ? where MaLoai = ?");
			pst.setString(1, name);
			pst.setInt(2, id);
			rs = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int removeCategory(int id) {
		int rs = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("DELETE LoaiLinhKien WHERE MaLoai = ?");
			pst.setInt(1, id);
			rs = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
