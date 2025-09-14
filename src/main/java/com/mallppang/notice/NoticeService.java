package com.mallppang.notice;

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
public class NoticeService implements BaseService<NoticeDTO> {
	private final NoticeRepository noticeRepository;
	private final NoticeMapper mapper;

	@Override
	public Long register(NoticeDTO noticeDTO) {
		NoticeBoard notice = mapper.dtoToEntity(noticeDTO);
		notice.setCreateDate(LocalDateTime.now());
		NoticeBoard result = noticeRepository.save(notice);
		return result.getId();
	}

	@Override
	public NoticeDTO get(Long id) {
		Optional<NoticeBoard> result = noticeRepository.selectOne(id);
		NoticeBoard notice = result.orElseThrow();
		NoticeDTO noticeDTO = mapper.entityToDTO(notice);
		return noticeDTO;
	}

	@Override
	public void modify(NoticeDTO noticeDTO) {
		Optional<NoticeBoard> result = noticeRepository.findById(noticeDTO.getId());
		NoticeBoard notice = result.orElseThrow();

		notice.setTitle(noticeDTO.getTitle());
		notice.setContent(noticeDTO.getContent());
		notice.clearImageList();

		List<String> uploadFileNames = noticeDTO.getUploadFileNames();

		if (uploadFileNames != null && uploadFileNames.size() > 0)
			uploadFileNames.stream().forEach(uploadName -> notice.addImageString(uploadName));

		noticeRepository.save(notice);
	}

	@Override
	public void delete(Long id) {
		noticeRepository.updateToDelete(id, true);
	}

	@Override
	public PageResponseDTO<NoticeDTO> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("id").descending());
		Page<Object[]> result = noticeRepository.selectList(pageable);
		List<NoticeDTO> dtoList = result.get().map(arr -> {
			NoticeBoard notice = (NoticeBoard) arr[0];
			BoardImage noticeImage = (BoardImage) arr[1];

			NoticeDTO noticeDTO = NoticeDTO.builder().id(notice.getId()).title(notice.getTitle())
					.content(notice.getContent()).createDate(notice.getCreateDate()).build();

			if (noticeImage != null)
				noticeDTO.setUploadFileNames(List.of(noticeImage.getFileName()));

			return noticeDTO;
		}).collect(Collectors.toList());

		Long totalCount = result.getTotalElements();

		return PageResponseDTO.<NoticeDTO>withAll().dtoList(dtoList).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();
	}
}
