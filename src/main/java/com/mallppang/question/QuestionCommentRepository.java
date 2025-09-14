package com.mallppang.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long>{
	@Modifying
	@Query("UPDATE QuestionComment f SET f.delFlag = :flag WHERE f.id = :id")
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
	
	@Query("SELECT f FROM QuestionComment f WHERE f.delFlag = FALSE")
	Page<Object> selectList(Pageable pageable);
}
