package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Product {
	private int productId;
	private String productName;
	private Date mfg;
	private String mfger;
	private int qty;
	private BigDecimal price;

	public Product(int productId, String productName, Date mfg, String mfger, int qty, BigDecimal price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.mfg = mfg;
		this.mfger = mfger;
		this.qty = qty;
		this.price = price;
	}

	public Product(String productName, Date mfg, String mfger, int qty, BigDecimal price) {
		super();
		this.productName = productName;
		this.mfg = mfg;
		this.mfger = mfger;
		this.qty = qty;
		this.price = price;
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

	public Date getMfg() {
		return mfg;
	}

	public void setMfg(Date mfg) {
		this.mfg = mfg;
	}

	public String getMfger() {
		return mfger;
	}

	public void setMfger(String mfger) {
		this.mfger = mfger;
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
