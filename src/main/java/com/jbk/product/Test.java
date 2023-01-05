package com.jbk.product;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.jbk.product.entity.Product;
import com.jbk.product.service.ProductService;
import com.jbk.product.utility.ProductUtility;

public class Test {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		ProductService service =new ProductService();
		int choice;
		char ch = 0;
		
		do {
			System.out.println(".....controller.........");
			System.out.println("press 1 for save product");
			System.out.println("press 2 for get product");
			System.out.println("press 3 for delete product");
			System.out.println("press 4 for getAll product");
			System.out.println("press 5 for criteria of product");
			System.out.println("press 6 for Restrictions...");
			System.out.println("press 7 for projections!!!");
			System.out.println("press 8 for update product");
			choice=scanner.nextInt();
			
			switch (choice) {
			case 1:{
				Product product = ProductUtility.prepareProductData(scanner);
				String msg=	service.saveProduct(product);
				System.out.println(msg);
				break;
			}
			case 2:{
				System.out.println("Enter productId");
				int productId=scanner.nextInt();
		
			Product product= service.getProductById(productId);
			if(product!=null) {
				System.out.println(product);
			}else {
				System.out.println("product not found...");
			}
				break;
				
			}
			case 3:{
				System.out.println("Enter productId");
				int productId=scanner.nextInt();
				String msg= service.deleteProductById(productId);
				System.out.println(msg);
				break;
			}
			case 4:{
				List<Product> list=service.getAll();
				if(!list.isEmpty()) {
					for (Product product : list) {
						System.out.println(product);
					}
					}
					else 
						System.out.println("not found");
				break;
				}
			
			case 5:{
				List<Product> list=service.getCriteriaOfProducts();
					if(!list.isEmpty()) {
						for (Product product : list) {
							System.out.println(product);
							
						}
					}
					else {
						System.out.println("product not found");
					}
				break;
			}
			case 6:{
				List<Product> list=service.restrictProduct();
				if(!list.isEmpty()) {
					for (Product product : list) {
						System.out.println(product);
						
					}
				}
				else {
					System.out.println("product not found");
				
					
				}break;

			}
			case 7:{
				double sum=service.projectionProduct();
				
					System.out.println(sum);
					break;
						}
					
			case 8:{
				Product product = ProductUtility.prepareProductData(scanner);
				String msg=service.updateProduct(product);
				System.out.println(msg);
				break;
			}
				
			
			default:{
				System.out.println("invalid choice");
				break;
			}
			}
			
			System.out.println("do you want continue y/n");
			ch=scanner.next().charAt(0);
			
		} while (ch=='y'||ch=='Y');
	
	
	}
	}
