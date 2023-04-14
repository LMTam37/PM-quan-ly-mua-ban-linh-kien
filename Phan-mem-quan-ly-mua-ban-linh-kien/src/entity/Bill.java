package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Bill {
	private int billId, discount;
	private String customerName, empName;
	private Date purchaseDate;
	private BigDecimal total;

	public Bill(int billId, String customerName, Date purchaseDate, String empName, int discount, BigDecimal total) {
		super();
		this.billId = billId;
		this.customerName = customerName;
		this.purchaseDate = purchaseDate;
		this.empName = empName;
		this.discount = discount;
		this.total = total;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
