package com.mallppang.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long>{
	@Modifying
	@Query("UPDATE QuestionComment q SET q.delFlag = :flag WHERE q.id = :id")
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
	
	@Query("SELECT q FROM QuestionComment q WHERE q.delFlag = FALSE AND q.board.id = :boardId")
	List<QuestionComment> getList(@Param("boardId") Long boardId);
}
