package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import entity.Emp;

public class AccountDAO {
	public static AccountDAO getInstance() {
		return new AccountDAO();
	}
	
	public Emp login(String username, String password) {
		Emp emp = new Emp();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("EXEC Login ?, ?");
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				emp.setEmpId(rs.getInt("MaNhanVien"));
				emp.setEmpName(rs.getString("TenNhanVien"));
				emp.setUsername(rs.getString("TenDangNhap"));
				emp.setPassword(rs.getString("MatKhau"));
				emp.setRole(rs.getBoolean("VaiTro"));	
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return emp;
	}
}
