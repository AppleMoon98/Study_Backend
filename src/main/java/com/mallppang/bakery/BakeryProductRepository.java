package com.mallppang.bakery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BakeryProductRepository extends JpaRepository<BakeryProduct, Long>{
	@Query("SELECT p FROM BakeryProduct p WHERE p.bakery.id = :bokeryId")
	List<BakeryProduct> getList(@Param("bokeryId") Long bokeryId);
}
