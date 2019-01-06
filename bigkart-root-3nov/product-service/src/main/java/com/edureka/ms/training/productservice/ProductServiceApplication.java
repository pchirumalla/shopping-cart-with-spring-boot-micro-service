package com.edureka.ms.training.productservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.edureka.ms.training.productservice.model.Product;
import com.edureka.ms.training.productservice.repository.ProductRepository;

@SpringBootApplication

public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Bean
    public InitializingBean seedDB(ProductRepository productRepository) throws Exception
    {
    	return() ->{
    		productRepository.deleteAll();
			List<Product> curlingIronProductList = new ArrayList<Product>();
			String name = "PARWIN CURLING IRON";//
			String description = "PRO 5 in 1 Curling Iron Wand Set with 5 Interchangeable Diamond Tourmaline Ceramic Curl Iron Barrels-No Cool Tip & Travel Bag";
			curlingIronProductList.add(new Product(1, name, description, BigDecimal.valueOf(19.99)));
			name = "SULTRA CURL";
			description = "Sultra The Seductress Curl, Wave & Straight Iron";
			curlingIronProductList.add(new Product(2, name, description, BigDecimal.valueOf(14.99)));
			productRepository.saveAll(curlingIronProductList);
			
			List<Product> flatIronProductList = new ArrayList<Product>();
			name = "Tourmaline Ceramic Flat Iron";
			description = "Professional Hair Straightener, Flat Iron for Hair Styling: 2 in 1 Tourmaline Ceramic Flat Iron for All Hair Types with Rotating Adjustable Temperature and Salon High Heat 250℉-450℉, 1 Inch, Gold ";
			flatIronProductList.add(new Product(4, name, description, BigDecimal.valueOf(11.99)));
			name= "LANPRO Flat Iron";
			description = "LANPRO Hair Straightener, Upgraded Flat Iron Straightening Iron for All Hair Types Styling 2 in 1 Tourmaline Ceramic Curling Iron W/Rotating Adjustable...   ";
			flatIronProductList.add(new Product(5, name, description, BigDecimal.valueOf(29.99)));
			productRepository.saveAll(flatIronProductList);
		};
	}
}
