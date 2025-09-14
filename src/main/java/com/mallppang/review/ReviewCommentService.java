package com.mallppang.review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallppang.base.CommentService;
import com.mallppang.base.PageRequestDTO;
import com.mallppang.base.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewCommentService implements CommentService<ReviewCommentDTO>{
	private final ReviewCommentRepository commentRepository;
	private final ReviewCommentMapper mapper;	
	
	@Override
	public Long register(ReviewCommentDTO commentDTO){
		ReviewComment comment = mapper.dtoToEntity(commentDTO);
		comment.setCreateDate(LocalDateTime.now());
		ReviewComment saved = commentRepository.save(comment);
		return saved.getId();
		
	}
	


	@Override
	public PageResponseDTO<ReviewCommentDTO> getList(PageRequestDTO pageRequestDTO){
		int size = 30;
		
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1, size, Sort.by("id"));
		Page<Object> result = commentRepository.selectList(pageable);
		
		List<ReviewCommentDTO> dtoList = result.get().map(arr -> {
			ReviewComment comment = (ReviewComment) arr;
			ReviewCommentDTO dto = ReviewCommentDTO.builder().id(comment.getId()).content(comment.getContent())
					.boardId(comment.getBoard().getId()).build();
					return dto;
		}).collect(Collectors.toList());
		
		Long totalCount = result.getTotalElements();
		return PageResponseDTO.<ReviewCommentDTO>withAll().dtoList(dtoList).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();
		
	}

	@Override
	public void modify(ReviewCommentDTO dto) {
		Optional<ReviewComment> result = commentRepository.findById(dto.getId());
		ReviewComment comment = result.orElseThrow();
		comment.setContent(dto.getContent());
		commentRepository.save(comment);
	}

	@Override
	public void delete(Long id) {
		commentRepository.updateToDelete(id, true);
	}
}
