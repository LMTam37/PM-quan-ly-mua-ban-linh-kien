package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Emp;

public class EmpManageDAO {
	public static EmpManageDAO getInstance() {
		return new EmpManageDAO();
	}

	public ArrayList<Emp> getListEmp() {
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt("MaNhanVien");
				String username = rs.getString("TenDangNhap");
				String empName = rs.getString("TenNhanVien");
				String password = rs.getString("MatKhau");
				boolean role = rs.getBoolean("VaiTro");
				list.add(new Emp(empId, username, empName, password, role));
			}
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int createEmp(String empUsername, String empName, String password, boolean role) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO NhanVien " + "VALUES (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, empName);
			pst.setString(2, empUsername);
			pst.setString(3, password);
			pst.setBoolean(4, role);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmp(String username) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "DELETE FROM NhanVien WHERE TenDangNhap = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateEmpName(int empId, String newEmpname) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "UPDATE NhanVien SET TenNhanVien = ? WHERE MaNhanVien = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newEmpname);
			pst.setInt(2, empId);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateEmpUsername(int empId, String newUsername) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "UPDATE NhanVien SET TenDangNhap = ? WHERE MaNhanVien = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newUsername);
			pst.setInt(2, empId);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateEmpPassword(int empId, String newPassword) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "UPDATE NhanVien SET MatKhau = ? WHERE MaNhanVien = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newPassword);
			pst.setInt(2, empId);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
