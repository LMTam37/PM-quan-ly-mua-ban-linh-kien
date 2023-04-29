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
	
	public ArrayList<String> getListCategory() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Tất cả");
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM LoaiLinhKien");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TenLoai"));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
