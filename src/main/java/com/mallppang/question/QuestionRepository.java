package com.mallppang.question;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@EntityGraph(attributePaths = "imageList")
	@Query("SELECT q FROM Question q WHERE q.id = :id")
	Optional<Question> selectOne(@Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Question q SET q.delFlag = :flag WHERE q.id = :id")
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
	
	@Query("SELECT q, qi FROM Question q LEFT JOIN q.imageList qi ON qi.ord = 0 WHERE q.delFlag = FALSE")
	Page<Object[]> selectList(Pageable pageable);
}
