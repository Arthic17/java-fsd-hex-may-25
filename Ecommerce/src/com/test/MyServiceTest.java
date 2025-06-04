package com.test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.ecom.dao.CategoryDao;
import com.ecom.dao.impl.CategoryDaoImpl;
import com.ecom.dao.impl.ProductDaoImpl;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class MyServiceTest {
 ProductService productService;
 PurchaseService purchaseService;

 
 @BeforeEach
 public void init() {
	 productService=new ProductService();
	 purchaseService=new PurchaseService();
	
 }
 
 @Test
 public void insertProductTest() throws InvalidIdException{
	 Product product=new Product();
	 product.setTitle("IPhone");
	 product.setPrice(50000);
	 
//	 1 : Exception fails 
	 assertDoesNotThrow(()->{
		 productService.insertProduct(product, 2);
	 });
	 
//	 2 : Empty Product name
	 product.setTitle(null);
	 InvalidInputException input= assertThrows(InvalidInputException.class,()->{
		productService.insertProduct(product,2);
		
	 });
	 assertEquals("Product name cannot be empty".toLowerCase(),input.getMessage().toLowerCase());
	
	 
//	3 : Invalid Category id
	 product.setTitle("Lenova");
	 InvalidIdException in=assertThrows(InvalidIdException.class,()->{
		 productService.insertProduct(product,9999);
	 });
	 assertEquals("ID given is Invalid".toLowerCase(),in.getMessage().toLowerCase());
 }
 
 
 @Test
public void getByIdTest(){
	 Category category = new Category(1, "Mobiles");
//	 1 : Failed Exception
	 assertDoesNotThrow(()->{
		 List<Product> product=productService.getByCategoryId(1);
		 assertNotNull(product);
	 });
	 
	 
	 
//	 2 : Invalid ID
	 InvalidIdException in=assertThrows(InvalidIdException.class,()->{
		 productService.getByCategoryId(999);
	 });
	 assertEquals("Invalid Category ID : No products found".toLowerCase(),in.getMessage().toLowerCase());
 }
 
 
 
 @Test
 public void insertPurchaseTest() {
	 
//	 1: With Coupon - Failed Exception
	  assertDoesNotThrow(()->{
		  purchaseService.insert(1, 7654189, new Scanner("YES\nMEGADEAL\n"));
	  });
	  
	  
//	  2: Without coupon - Failed Exception
	  assertDoesNotThrow(()->{
		  purchaseService.insert(2, 7654189, new Scanner("NO\n"));
	  });
	  
	  
//	  3: Invalid Customer ID
	  InvalidIdException in=assertThrows(InvalidIdException.class,()->{
			 purchaseService.insert(999,7654189,new Scanner("NO\n"));
		 });
		 assertEquals("ID given is Invalid".toLowerCase(),in.getMessage().toLowerCase());
	  
//	 4 : Invalid Product ID
		     in=assertThrows(InvalidIdException.class,()->{
				 purchaseService.insert(1,74189,new Scanner("NO\n"));
			 });
			 assertEquals("ID given is Invalid".toLowerCase(),in.getMessage().toLowerCase());
		 
 }
 
 
 
 
}