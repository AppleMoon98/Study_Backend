package com.mallppang.freeboard;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FreeRepository extends JpaRepository<FreeBoard, Long> {
    @EntityGraph(attributePaths = "imageList")
    @Query("SELECT f FROM FreeBoard f WHERE f.id = :id")
    Optional<FreeBoard> selectOne(@Param("id") Long id);
    
    @Modifying
    @Query("UPDATE FreeBoard f SET f.delFlag = :flag WHERE f.id = :id")
    void updateToDelete(@Param("id") Long id, @Param("flag") boolean flag);
    
    @Query("SELECT f, fi FROM FreeBoard f LEFT JOIN f.imageList fi ON fi.ord = 0 WHERE f.delFlag = FALSE")
    Page<Object[]> selectList(Pageable pageable);
}
