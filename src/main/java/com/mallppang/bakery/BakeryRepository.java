package com.mallppang.bakery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BakeryRepository extends JpaRepository<Bakery, Long>{
	Optional<Bakery> findTopByNameOrderByIdAsc(String name);
}
