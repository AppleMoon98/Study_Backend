package com.mallppang.review;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewBoard, Long>{
	@EntityGraph(attributePaths = "imageList")
	@Query("select r from ReviewBoard r where r.id = :id")
	Optional<ReviewBoard> selectOne(@Param("id") Long id);
	
	@Modifying
	@Query("update ReviewBoard r set r.delFlag = :flag where r.id = :id")
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
	
	
	@Query("SELECT r, ri, m.nickname FROM ReviewBoard r join fetch r.member m LEFT JOIN r.imageList ri ON ri.ord = 0 WHERE r.delFlag = FALSE order by r.id desc")
	Page<Object[]> selectList(Pageable pageable);
}
