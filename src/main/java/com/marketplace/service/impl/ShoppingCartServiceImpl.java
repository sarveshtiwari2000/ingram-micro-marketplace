package com.marketplace.service.impl;

import com.marketplace.exception.NotEnoughProductsInStockException;
import com.marketplace.model.Product;
import com.marketplace.repository.ProductRepository;
import com.marketplace.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Marketplace Cart
 is implemented with a Map, and as a session bean
 *
 * @author Dusan
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private final ProductRepository productRepository;

	private Map<Product, Integer> products = new HashMap<>();

	@Autowired
	public ShoppingCartServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * If product is in the map just increment quantity by 1. If product is not in
	 * the map with, add it with quantity 1
	 *
	 * @param product
	 */
	@Override
	public void addProduct(Product product) {
		if (products.containsKey(product)) {
			products.replace(product, products.get(product) + 1);
		} else {
			products.put(product, 1);
		}
	}

	/**
	 * If product is in the map with quantity > 1, just decrement quantity by 1. If
	 * product is in the map with quantity 1, remove it from map
	 *
	 * @param product
	 */
	@Override
	public void removeProduct(Product product) {
		if (products.containsKey(product)) {
			if (products.get(product) > 1)
				products.replace(product, products.get(product) - 1);
			else if (products.get(product) == 1) {
				products.remove(product);
			}
		}
	}

	/**
	 * @return unmodifiable copy of the map
	 */
	@Override
	public Map<Product, Integer> getProductsInCart() {
		return Collections.unmodifiableMap(products);
	}

	/**
	 * Checkout will rollback if there is not enough of some product in stock
	 *
	 * @throws NotEnoughProductsInStockException
	 */
	@Override
	public void checkout() throws NotEnoughProductsInStockException {
		Product product;
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			// Refresh quantity for every product before checking
			product = productRepository.findOne(entry.getKey().getId());
			if (product.getQuantity() < entry.getValue())
				throw new NotEnoughProductsInStockException(product);
			entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
		}
		productRepository.save(products.keySet());
		productRepository.flush();
		products.clear();
	}

	@Override
	public BigDecimal getTotal() {

		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			// Refresh quantity for every product before checking

			if (entry.getValue() == 2) {
				BigDecimal basePrice = products.entrySet().stream()
						.map(newEntry -> newEntry.getKey().getPrice().multiply(BigDecimal.valueOf(newEntry.getValue())))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
				BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.05));
				BigDecimal finalPrice = basePrice.subtract(discount);

				return finalPrice;
			}

			if (entry.getValue() == 3) {
				BigDecimal basePrice = products.entrySet().stream()
						.map(newEntry -> newEntry.getKey().getPrice().multiply(BigDecimal.valueOf(newEntry.getValue())))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
				BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.10));
				BigDecimal finalPrice = basePrice.subtract(discount);

				return finalPrice;
			}

			if (entry.getValue() == 4) {
				BigDecimal basePrice = products.entrySet().stream()
						.map(newEntry -> newEntry.getKey().getPrice().multiply(BigDecimal.valueOf(newEntry.getValue())))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
				BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.15));
				BigDecimal finalPrice = basePrice.subtract(discount);

				return finalPrice;
			}

			if (entry.getValue() == 5) {
				BigDecimal basePrice = products.entrySet().stream()
						.map(newEntry -> newEntry.getKey().getPrice().multiply(BigDecimal.valueOf(newEntry.getValue())))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
				BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.25));
				BigDecimal finalPrice = basePrice.subtract(discount);

				return finalPrice;
			}

			if (entry.getValue() == 6) {
				BigDecimal basePrice = products.entrySet().stream()
						.map(newEntry -> newEntry.getKey().getPrice().multiply(BigDecimal.valueOf(newEntry.getValue())))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
				BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.30));
				BigDecimal finalPrice = basePrice.subtract(discount);

				return finalPrice;
			}

			if (entry.getValue() == 7) {
				BigDecimal basePrice = products.entrySet().stream()
						.map(newEntry -> newEntry.getKey().getPrice().multiply(BigDecimal.valueOf(newEntry.getValue())))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
				BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(0.35));
				BigDecimal finalPrice = basePrice.subtract(discount);

				return finalPrice;
			}

		}

		return products.entrySet().stream()
				.map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
}
