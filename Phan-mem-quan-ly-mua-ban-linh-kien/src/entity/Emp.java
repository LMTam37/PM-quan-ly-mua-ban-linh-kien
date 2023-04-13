package entity;

import java.util.Objects;

public class Emp {
	private int empId;
	private String username, empName, password;
	boolean role;

	public Emp(int empId, String username, String empName, String password, boolean role) {
		super();
		this.empId = empId;
		this.username = username;
		this.empName = empName;
		this.password = password;
		this.role = role;
	}

	public Emp(String username, String empName, String password, boolean role) {
		super();
		this.username = username;
		this.empName = empName;
		this.password = password;
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		return empId == other.empId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", username=" + username + ", empName=" + empName + ", password=" + password
				+ ", role=" + role + "]";
	}

}
