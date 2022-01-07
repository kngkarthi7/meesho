package com.meeshoapi;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoView {
	
	private String productImageLink;
	private int productCost;
	private String productDescription;
	private List<String> sizesAvailable = new ArrayList<String>();
	private List<SimilarProductsView> similarProducts = new ArrayList<SimilarProductsView>();
	
	public String getProductImageLink() {
		return productImageLink;
	}
	public void setProductImageLink(String productImageLink) {
		this.productImageLink = productImageLink;
	}
	public int getProductCost() {
		return productCost;
	}
	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public List<String> getSizesAvailable() {
		return sizesAvailable;
	}
	public void setSizesAvailable(List<String> sizesAvailable) {
		this.sizesAvailable = sizesAvailable;
	}
	public List<SimilarProductsView> getSimilarProducts() {
		return similarProducts;
	}
	public void setSimilarProducts(List<SimilarProductsView> similarProducts) {
		this.similarProducts = similarProducts;
	}

}
