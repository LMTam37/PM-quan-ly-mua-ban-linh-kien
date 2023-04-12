package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Bill {
	private int billId, customerId, empId, discount;
	private Date purchaseDate;
	private BigDecimal total;

	public Bill(int billId, int customerId, Date purchaseDate, int empId, int discount, BigDecimal total) {
		super();
		this.billId = billId;
		this.customerId = customerId;
		this.empId = empId;
		this.purchaseDate = purchaseDate;
		this.discount = discount;
		this.total = total;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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
