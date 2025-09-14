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

import com.mallppang.base.BaseService;
import com.mallppang.base.BoardImage;
import com.mallppang.base.PageRequestDTO;
import com.mallppang.base.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewService implements BaseService<ReviewDTO> {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper mapper;

	@Override
	public PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("id").descending());

		Page<Object[]> result = reviewRepository.selectList(pageable);
		List<ReviewDTO> dtoList = result.get().map(arr -> {
			ReviewBoard reviewBoard = (ReviewBoard) arr[0];
			BoardImage boardImage = (BoardImage) arr[1];

			ReviewDTO reviewDTO = ReviewDTO.builder().id(reviewBoard.getId()).title(reviewBoard.getTitle())
					.content(reviewBoard.getContent()).createDate(reviewBoard.getCreateDate()).build();

			if (boardImage != null)
				reviewDTO.setUploadFileNames(List.of(boardImage.getFileName()));

			return reviewDTO;
		}).collect(Collectors.toList());

		Long totalCount = result.getTotalElements();
		return PageResponseDTO.<ReviewDTO>withAll().dtoList(dtoList).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();
	}

	@Override
	public Long register(ReviewDTO reviewDTO) {
		ReviewBoard reviewBoard = mapper.dtoToEntity(reviewDTO);
		reviewBoard.setCreateDate(LocalDateTime.now());
		ReviewBoard result = reviewRepository.save(reviewBoard);
		return result.getId();
	}

	@Override
	public ReviewDTO get(Long id) {
		Optional<ReviewBoard> result = reviewRepository.selectOne(id);
		ReviewBoard reviewBoard = result.orElseThrow();
		ReviewDTO reviewDTO = mapper.entityToDTO(reviewBoard);
		return reviewDTO;
	}
	
		@Override
	public void modify(ReviewDTO reviewDTO) {
		Optional<ReviewBoard> result = reviewRepository.findById(reviewDTO.getId());
		ReviewBoard free = result.orElseThrow();

		free.setTitle(reviewDTO.getTitle());
		free.setContent(reviewDTO.getContent());
		free.clearImageList();

		List<String> uploadFileNames = reviewDTO.getUploadFileNames();

		if (uploadFileNames != null && uploadFileNames.size() > 0)
			uploadFileNames.stream().forEach(uploadName -> free.addImageString(uploadName));

		reviewRepository.save(free);
	}


	public void delete(Long id) {
		reviewRepository.updateToDelete(id, true);
	}
}
