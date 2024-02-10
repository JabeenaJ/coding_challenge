package org.jsp.javachallenge.controller;

import java.util.Scanner;

import org.jsp.javachallenge.ProductConfig;
import org.jsp.javachallenge.dao.ProductDao;
import org.jsp.javachallenge.dto.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductController {
	static ProductDao productDao;
	static Scanner sc = new Scanner(System.in);
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
		productDao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.Create Product");
		System.out.println("2.Read Product");
		System.out.println("3.Update Product");
		System.out.println("4.Delete Product");
		switch (sc.nextInt()) {
		case 1:
			createProduct();
			break;
		case 2:
			readProduct();
			break;
		case 3:
			updateProduct();
			break;
		case 4:
			deleteProduct();
			break;
		default:
			System.err.println("Invalid Input");
		}
	}

	public static void createProduct() {
		System.out.println("Enter Product name,description,price and quantity");
		Product product = new Product();
		product.setName(sc.next());
		product.setDescription(sc.next());
		product.setPrice(sc.nextDouble());
		product.setQuantity(sc.nextInt());
		product = productDao.createProduct(product);
		if (product != null) {
			product.getProductId();
			product.getName();
			product.getDescription();
			product.getPrice();
			product.getQuantity();
			System.out.println("Product Created successfully");
		} else {
			System.err.println("Product Not creation failed");
		}
	}

	public static void readProduct() {
		System.out.println("Enter Product ID");
		int id = sc.nextInt();
		Product product = new Product();
		product = productDao.readProduct(id);
		if (product != null) {
			System.out.println("Product Id:" + product.getProductId());
			System.out.println("Product Name:" + product.getName());
			System.out.println("Description:" + product.getDescription());
			System.out.println("Price:" + product.getPrice());
			System.out.println("Quantity:" + product.getQuantity());
		} else {
			System.err.println("Product Not Found");
		}
	}

	public static void updateProduct() {
		System.out.println("Enter Product Id to update");
		int id = sc.nextInt();
		System.out.println("Enter Product name,description,price and quantity");
		Product product = new Product();
		product.setProductId(id);
		product.setName(sc.next());
		product.setDescription(sc.next());
		product.setPrice(sc.nextDouble());
		product.setQuantity(sc.nextInt());
		product = productDao.updateProduct(product);
		if (product != null) {
			System.out.println("Product updated successfully");
		} else {
			System.out.println("Product updation Failed");
		}
	}

	public static void deleteProduct() {
		System.out.println("Enter Product ID");
		int id = sc.nextInt();
		boolean product = productDao.deleteProduct(id);
		if (product == true) {
			System.out.println("Product deleted successfully");
		} else {
			System.err.println("Product deletion Failed");
		}
	}
}
