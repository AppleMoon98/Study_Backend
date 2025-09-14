package com.mallppang.freeboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FreeCommentRepository extends JpaRepository<FreeComment, Long>{
	@Modifying
	@Query("UPDATE FreeComment f SET f.delFlag = :flag WHERE f.id = :id")
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
	
	@Query("SELECT f FROM FreeComment f WHERE f.delFlag = FALSE AND f.board.id = :boardId")
	List<FreeComment> getList(@Param("boardId") Long boardId);
}
