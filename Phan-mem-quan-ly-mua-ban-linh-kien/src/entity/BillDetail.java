package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class BillDetail {
	private int billDetailId, billId, productId, qty;
	private String productName, category;
	private BigDecimal price;

	public BillDetail(int billDetailId, int billId, String productName, String category, int qty, BigDecimal price) {
		super();
		this.billDetailId = billDetailId;
		this.billId = billId;
		this.productName = productName;
		this.category = category;
		this.qty = qty;
		this.price = price;
	}

	public BillDetail(int productId, String productName, String category, int qty, BigDecimal price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.qty = qty;
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(billId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillDetail other = (BillDetail) obj;
		return billId == other.billId && productId == other.productId;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
