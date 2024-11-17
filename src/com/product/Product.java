package com.product;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.object.ProductObject;

public interface Product {
	boolean addProduct(ProductObject item);
	boolean editProduct(ProductObject item);
	boolean deleteProduct(ProductObject item);
	
	ArrayList<ResultSet> getProduct(ProductObject similar, int at, byte total);
	ResultSet getProduct(int id);
	ResultSet getProduct(String username, String userpass);
	ArrayList<ProductObject> searchProductByName(String productName);
}
