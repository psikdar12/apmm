package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.example.exception.InvalidProductException;
import com.example.model.Product;

public class ShoppingService {

	private Map<Integer, Product> cart = new HashMap<>();

	public void addItem(Product product) {
		int pid = product.getId();
		if (cart.containsKey(pid)) {
			Product existingProduct = cart.get(pid);	
			existingProduct.setQty(existingProduct.getQty()+1);
		} else {
			cart.put(pid, product);

		}
	}
	
	
	public int countItems() {
		int count = 0;
		Collection<Product> products = cart.values();		
		for(Product product : products) {
			count = count + product.getQty();
		}
		return count;
	}
	
	
	public Collection<Product> cartDetails() {
		return cart.values();		
	}
	
	
	public void removeItem(int pid) throws InvalidProductException{
		if(!cart.containsKey(pid)) {
			throw new InvalidProductException("Product with id : "+pid+" was never added to cart.");
		}
		Product product = cart.get(pid);
		if(product.getQty() == 1){
			cart.remove(pid);
		}else {
			product.setQty(product.getQty()-1);
		}
	}
	
	
	public double totalPrice() {
		double total = 0.0;
		Collection<Product> products = cart.values();		
		for(Product product : products) {
			double individualPrice =  product.getQty() * product.getPrice();
			total = total + individualPrice;
		}
		return total;
	}
	
	
	public String placeOrder() {
		//DB calls
		try {
			TimeUnit.SECONDS.sleep(6);
		}catch(Exception e) {
			
		}
		return "O"+System.nanoTime();
	}
	
	public void emptyCart() {
		cart.clear();
	}
	
	

}
