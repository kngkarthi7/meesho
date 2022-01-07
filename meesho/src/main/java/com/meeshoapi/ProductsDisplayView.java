package com.meeshoapi;
import java.util.ArrayList;
import java.util.List;

public class ProductsDisplayView {

	private List<ProductView> products = new ArrayList<ProductView>();
	
	public List<ProductView> getProducts() {
		return products;
	}

	public void setProducts(List<ProductView> products) {
		this.products = products;
	}
}
