package com.meeshoapi;

public class ProductView {
	private String productLink;
	private String productImageLink;
	private String productName;
	private int productCost = 0;

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public String getProductImageLink() {
		return productImageLink;
	}

	public void setProductImageLink(String productImageLink) {
		this.productImageLink = productImageLink;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}

	public boolean isValid() {
		if ((this.getProductLink() != null && this.getProductLink().trim().equals("")) || (this.getProductImageLink() != null && this.getProductImageLink().trim().equals(""))
				|| (this.getProductName() != null && this.getProductName().trim().equals("")) || this.getProductCost() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProductView [productLink=" + productLink + ", productImageLink=" + productImageLink + ", productName="
				+ productName + ", productCost=" + productCost + "]";
	}
}
