package com.example.mall.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mall.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	// React식 관계맺기
	@EntityGraph(attributePaths = "imageList")
	@Query("SELECT p FROM Product p WHERE p.pno = :pno")
	Optional<Product> selectOne(@Param("pno") Long pno);
	
	@Modifying    // Insert Update Delete
	@Query("UPDATE Product p SET p.delFlag = :flag where p.pno = :pno")    // jpql
	void updateToDelete(@Param("pno") Long pno, @Param("flag") boolean flag);
	
	@Query("SELECT p, pi FROM Product p LEFT JOIN p.imageList pi WHERE pi.ord = 0 AND p.delFlag = FALSE")
	Page<Object[]> selectList(Pageable pageable);
}
