package com.example;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.exception.InvalidProductException;
import com.example.model.Product;

public class ShoppingServiceTest{
	

	ShoppingService shoppingService = null;
	
	@Before
	public void init() {
		//System.out.println("Inside init()");
		shoppingService = new ShoppingService();
	}
	
	@After
	public void clean() {
		//System.out.println("Inside clean()");
		shoppingService = null;
	}
	

	@Test(timeout=8000)
	public void placeOrderShouldTakeLessThenFiveSecondsOnlyForSLA() {
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		Product watch = new Product(101, "Rolex Watch", 800000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);
	
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		
		shoppingService.placeOrder();
		
	}	
	
	
	
	@Test
	public void emptyingCartShouldZeroizeCart() {
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		Product watch = new Product(101, "Rolex Watch", 800000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);
	
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		
		shoppingService.emptyCart();
		
		assertTrue(shoppingService.countItems() == 0);
		
	}
	
	
	@Test
	public void placeOrderShouldSaveOrderInDBWithOrderNumber() {
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		Product watch = new Product(101, "Rolex Watch", 800000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);
	
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
				
		assertNotNull(shoppingService.placeOrder());
		assertTrue(shoppingService.placeOrder().length() > 10);
		assertTrue(shoppingService.placeOrder().startsWith("O"));
	
	}
	
	
	@Test
	public void emptyCartPriceShouldBeNil() {
	
		//ShoppingService shoppingService = new ShoppingService();
		assertEquals(0.0, shoppingService.totalPrice(),1);
	
	}
	
	@Test
	public void cartPriceShouldBeAccurate() {
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		Product watch = new Product(101, "Rolex Watch", 800000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);
	
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		shoppingService.addItem(mobile);
		
		assertEquals(953000.00, shoppingService.totalPrice(),2);
	
	}
	
	
	
	
	
	@Test(expected=InvalidProductException.class)
	public void removingNonExistantItemFromCartShouldYieldException() throws InvalidProductException{
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		Product watch = new Product(101, "Rolex Watch", 800000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);
	
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);

		shoppingService.removeItem(102);
	}
	
	
	@Test
	public void removingItemCompletelyFromCartShouldRemoveItFromCart(){
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		
		try {
			int beforeCartSize = shoppingService.countItems();
			shoppingService.removeItem(100);
			shoppingService.removeItem(100);
			int afterCartSize = shoppingService.countItems();
			assertEquals(beforeCartSize,(beforeCartSize-afterCartSize));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Test
	public void removingItemFromCartShouldReduceSizeByOne(){
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		
		try {
			int beforeCartSize = shoppingService.countItems();
			shoppingService.removeItem(100);
			int afterCartSize = shoppingService.countItems();
			assertEquals(1,(beforeCartSize-afterCartSize));
		} catch (InvalidProductException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void addingLaptopInCartShouldAddLaptopOnly(){
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		
		Collection<Product> products = shoppingService.cartDetails();
		for(Product product : products) {
			assertEquals(100,product.getId());
			assertTrue(product.getName().equals("HP Laptop"));
		}

	}

	
	@Test
	public void addingDuplicateItemShouldIncreaseSameItemSize(){
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		
		Collection<Product> products = shoppingService.cartDetails();
		for(Product product : products) {
			assertEquals(2,product.getQty());
		}
		
	}
	
	@Test
	public void addingMultipleItemsShouldIncreaseCartSize(){
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		Product watch = new Product(101, "Rolex Watch", 800000.00, 1);
		Product mobile = new Product(102, "Samsung Mobile", 75000.00, 1);
		
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		shoppingService.addItem(laptop);
		shoppingService.addItem(watch);
		shoppingService.addItem(mobile);
		shoppingService.addItem(mobile);
		
		assertEquals(5,shoppingService.countItems());
	}

	@Test
	public void addingItemInCartShouldMakeCartSizeToBeOne(){
	
		Product laptop = new Product(100, "HP Laptop", 1500.00, 1);
		//ShoppingService shoppingService = new ShoppingService();
		shoppingService.addItem(laptop);
		assertEquals(1,shoppingService.countItems());
	}
	
	
	@Test
	public void emptyCartShouldHaveZeroItems(){
		//ShoppingService shoppingService = new ShoppingService();
		assertEquals(0,shoppingService.countItems());
	}
	
	@Test
	public void cartDetailsAreNilIfEmptyCart(){
		//ShoppingService shoppingService = new ShoppingService();
		assertNotNull(shoppingService.cartDetails());
		assertEquals(0,shoppingService.cartDetails().size());
	}



}