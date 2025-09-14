package com.mallppang.base;

public interface BaseService<D> {
	PageResponseDTO<D> getList(PageRequestDTO pageRequestDTO);
	Long register(D dto);	// 글 등록
	D get(Long id);	// 글 조회
	void modify(D dto);	// 글 수정
	void delete(Long id); // 글 삭제
}
