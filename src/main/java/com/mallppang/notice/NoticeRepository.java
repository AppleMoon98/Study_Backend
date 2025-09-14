package com.mallppang.notice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<NoticeBoard, Long> {
	// React식 관계맺기
	@EntityGraph(attributePaths = "imageList")
	@Query("SELECT n FROM NoticeBoard n WHERE n.id = :id")
	Optional<NoticeBoard> selectOne(@Param("id") Long id);

	@Modifying // Insert Update Delete
	@Query("UPDATE NoticeBoard n SET n.delFlag = :flag where n.id = :id") // jpql
	void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);

	// 썸네일 없어도 출력하기 위해서 쿼리문 변경
	@Query("SELECT p, pi FROM NoticeBoard p LEFT JOIN p.imageList pi ON pi.ord = 0 WHERE p.delFlag = FALSE")
	Page<Object[]> selectList(Pageable pageable);
}
