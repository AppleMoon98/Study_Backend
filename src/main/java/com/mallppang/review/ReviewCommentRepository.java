package com.mallppang.review;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long>{
	
	
	@Modifying
	@Query("update ReviewComment r set r.delFlag = :flag where r.id = :id")
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
	
	@Query("SELECT r FROM ReviewComment r WHERE delFlag = FALSE")
	Page<Object> selectList(Pageable pageable);
	
	
	
}
