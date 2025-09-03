package com.example.mall;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.mall.domain.Product;
import com.example.mall.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository productRepository;
	
//	@Test
//	public void testInsert() {
//		
//		for(int i=0 ; i<10 ; i++) {
//			Product product = Product.builder()
//					.pname("상품 : " + i)
//					.price(100*i)
//					.pdesc("상품 설명: " + i)
//					.build();
//			product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE1.jpg");
//			product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE2.jpg");
//			
//			productRepository.save(product);
//			
//			log.info("-".repeat(40));
//		}
//	}
	
//	@Test
//	@Transactional
//	public void textRead() {
//		
//		Long pno = 1L;
//		
//	Optional<Product> result = productRepository.findById(pno);
//	Product product = result.orElseThrow();
//	
//	log.info("-".repeat(30));
//	log.info(product);
//	log.info(product.getImageList());
//	log.info("-".repeat(30));
//	
//	
//	}
//	
//	@Test
//	public void testRead1() {
//		
//		Long pno = 1L;
//		
//		Optional<Product> result = productRepository.selectOne(pno);
//		
//		Product product = result.orElseThrow();
//		
//		log.info("-".repeat(50));
//		log.info(product);
//		log.info(product.getImageList());
//		
//	}
//	
//	@Commit
//	@Transactional
//	@Test
//	public void testDelete() {
//		
//		Long pno = 2L;
//		
//		productRepository.updateToDelete(pno, true);
//	}
//	
//	@Test
//	public void testUpdate() {
//		Long pno = 10L;
//		
//		Product product = productRepository.selectOne(pno).get();
//		
//		product.setPname("10번 상품");
//		product.setPdesc("10번 상품 설명입니다");
//		product.setPrice(5000);
//		
//		product.clearList();
//		
//		product.addImageString(UUID.randomUUID().toString()+ "_" + "NEWIMAGE1.jpg");
//		product.addImageString(UUID.randomUUID().toString()+ "_" + "NEWIMAGE2.jpg");
//		product.addImageString(UUID.randomUUID().toString()+ "_" + "NEWIMAGE3.jpg");
//		
//		productRepository.save(product);
//	}
	
}
