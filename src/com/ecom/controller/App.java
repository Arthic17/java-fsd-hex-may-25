package com.ecom.controller;

import java.util.Scanner;

import com.ecom.model.Customer;
import com.ecom.model.Product;
//import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;

public class App {
	public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    PurchaseService purchaseService=new PurchaseService();
    ProductService productService=new ProductService();
    Product product=new Product();
    Customer customer=new Customer();
    while(true) {
		  System.out.println("********************MAIN MENU****************");
		  System.out.println("1. Add Product");
		  System.out.println("2. Get Product by Category");
		  System.out.println("3. Add Purchase Details");
          System.out.println("0. To Exit");
	      System.out.println("********************-------------****************");
	      int input  = sc.nextInt(); 
	      if(input==0) {
		  System.out.println("Exiting...Thank you");
		  break;
	      }
	switch(input) {
	case 1:
		System.out.println("Enter the Product Name"); 
		sc.nextLine();
		product.setTitle(sc.nextLine());
		System.out.println("Enter Price");
		product.setPrice(sc.nextDouble());
		sc.nextLine();
		System.out.println("Enter Description");
		product.setDescription(sc.nextLine());
		System.out.println("Enter Category Id");
		int categoryId=sc.nextInt();
		sc.nextLine();
		try {
		productService.insertProduct(product,categoryId);
		System.out.println("Product added in DB");
	}
		catch(InvalidIdException | InvalidInputException e) {
			 System.out.println(e.getMessage());
		}
		break;
		
	case 2:
		System.out.println("Enter Category ID: ");
		 categoryId=sc.nextInt();
		 try {
		 productService.getByCategoryId(categoryId)
		     .stream()
		     .forEach(c->{
		    	 System.out.println(c.getId()+ "\t"
		    			 + c.getTitle()+ "\t"
		    			 +c.getPrice()+ "\t"
		    			 +c.getDescription()+ "\t"
		    			 +c.getCategory().getName());
		     });
		 }catch(InvalidIdException e){
			 System.out.println(e.getMessage());
		 }
		break;	


	case 3:
		System.out.println("Enter Customer ID: ");
		int customer_id = sc.nextInt();
		System.out.println("Enter Product ID:");
		int product_id = sc.nextInt();
		
		try {
			purchaseService.insert(customer_id,product_id,sc);
			System.out.println("Purchase Details added");
		} catch (InvalidIdException e) {
			 System.out.println(e.getMessage());
		}catch(IllegalArgumentException e) {
			System.out.println("Coupon code is Invalid!!");
		}
		break; 
	default:
		System.out.println("Invaid Input!!!");
	}
	
	}
    sc.close();
}
}
