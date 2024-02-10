package org.jsp.javachallenge.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.javachallenge.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private EntityManager entityManager;

	public Product createProduct(Product product) {
		EntityTransaction transaction = entityManager.getTransaction();
		entityManager.persist(product);
		transaction.begin();
		transaction.commit();
		return product;
	}

	public Product readProduct(int id) {
		return entityManager.find(Product.class, id);
	}

	public Product updateProduct(Product product) {
		Product dbProduct = readProduct(product.getProductId());
		if (product != null) {
			dbProduct.setName(product.getName());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setPrice(product.getPrice());
			dbProduct.setQuantity(product.getQuantity());
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}

	public boolean deleteProduct(int id) {
		Product product = readProduct(id);
		if (product != null) {
			entityManager.remove(product);
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}
}
