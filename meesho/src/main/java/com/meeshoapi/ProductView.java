package com.meeshoapi;

public class ProductView {
	private String productLink;
	private String productImageLink;
	private String productName;
	private int productCost;
	
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
	
	@Override
	public String toString() {
		return "ProductView [productLink=" + productLink + ", productImageLink=" + productImageLink + ", productName="
				+ productName + ", productCost=" + productCost + "]";
	}
}
