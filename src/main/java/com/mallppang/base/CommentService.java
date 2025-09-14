package com.mallppang.base;

import java.util.List;

public interface CommentService <D> {
	List<D> getList(Long boardId);
	Long register(D dto, Long boardId);	// 글 등록
	void modify(D dto);	// 글 수정
	void delete(Long id); // 글 삭제
}
