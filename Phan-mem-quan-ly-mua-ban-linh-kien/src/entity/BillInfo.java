package entity;

import java.math.BigDecimal;

public class BillInfo {
	private int billInfoId, billId, productId, qty;
	private BigDecimal price;

	public BillInfo(int billInfoId, int billId, int productId, int qty, BigDecimal price) {
		super();
		this.billInfoId = billInfoId;
		this.billId = billId;
		this.productId = productId;
		this.qty = qty;
		this.price = price;
	}

	public int getBillInfoId() {
		return billInfoId;
	}

	public void setBillInfoId(int billInfoId) {
		this.billInfoId = billInfoId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
