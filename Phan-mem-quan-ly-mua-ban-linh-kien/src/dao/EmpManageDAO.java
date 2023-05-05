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

	public ArrayList<Emp> getListEmpBySearch(String name) {
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM NhanVien WHERE TenNhanVien LIKE ?");
			pst.setString(1, "%" + name + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Emp(rs.getInt("MaNhanVien"), rs.getString("TenDangNhap"), rs.getString("TenNhanVien"),
						rs.getString("MatKhau"), rs.getBoolean("VaiTro")));
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

	public int deleteEmp(int empId) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("DELETE FROM NhanVien WHERE MaNhanVien = ?");
			pst.setInt(1, empId);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateEmp(int empId,String username, String empName, String password, boolean role) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("UPDATE NhanVien SET TenNhanVien = ?, TenDangNhap = ?, MatKhau = ?, VaiTro = ? WHERE MaNhanVien = ?");
			pst.setString(1, empName);
			pst.setString(2, username);
			pst.setString(3, password);
			pst.setBoolean(4, role);
			pst.setInt(5, empId);
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
			PreparedStatement pst = con.prepareStatement("UPDATE NhanVien SET MatKhau = ? WHERE MaNhanVien = ?");
			pst.setString(1, newPassword);
			pst.setInt(2, empId);
			result = pst.executeUpdate();
			ConnectDB.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isExistsUsername(String username) {
		int result = 0;
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT dbo.isExistsUsername(?)");
			pst.setString(1, username);
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(result == 1) {
			return true;
		}
		return false;
	}
}
