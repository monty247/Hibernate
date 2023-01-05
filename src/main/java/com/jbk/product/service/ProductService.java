package com.jbk.product.service;

import java.util.List;

import com.jbk.product.dao.ProductDao;
import com.jbk.product.entity.Product;

public class ProductService {
	private ProductDao dao=new ProductDao();
	
	public String saveProduct(Product product) {
		String msg=dao.saveProduct(product);
		return msg;
}
	
	public Product getProductById(int productId) {
		Product  product=dao.getProductById(productId);
		return product;
	}
	
	public  String deleteProductById(int productId) {
	;
		return dao.deleteProductById(productId);
	}
	
	public List<Product> getAll(){
		List<Product> list=	dao.getAll();
		return list;
		 
		
	}

	public List<Product> getCriteriaOfProducts() {
		List<Product> list=dao.getCriteriaOfProducts();
		return list;
	}
	
	public List<Product> restrictProduct(){
		List<Product> list= dao.getResProduct();
		return list;
	}

	public double projectionProduct() {
		return dao.projectionProduct();		
		
	}

 public String updateProduct(Product product) {
	return dao.updateProduct(product);
		
		
	}

}

//	}
	

 