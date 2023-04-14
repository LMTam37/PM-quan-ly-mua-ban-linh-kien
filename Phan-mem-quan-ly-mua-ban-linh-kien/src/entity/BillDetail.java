package entity;

import java.math.BigDecimal;

public class BillDetail {
	private int billDetailId, billId, qty;
	private String productName;
	private BigDecimal price;

	public BillDetail(int billDetailId, int billId, String productName, int qty, BigDecimal price) {
		super();
		this.billDetailId = billDetailId;
		this.billId = billId;
		this.qty = qty;
		this.productName = productName;
		this.price = price;
	}

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
