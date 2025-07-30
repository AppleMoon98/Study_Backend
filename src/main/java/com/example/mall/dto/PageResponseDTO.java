package com.example.mall.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
	private List<E> dtoList;
	private List<Integer> pageNumList;
	private PageRequestDTO pageRequestDTO;

	private boolean prev, next;
	private int totalCount;
	private int prevPage, nextPage;
	private int totalPage;
	private int current;

	@Builder(builderMethodName = "withAll")
	public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {
		this.dtoList = dtoList;
		this.pageRequestDTO = pageRequestDTO;
		this.totalCount = (int) totalCount;

		int end = (pageRequestDTO.getPage() - 1) / 10 * 10 + 1;
		int start = end - 9;
		int last = (int) (Math.ceil(totalCount / (double) pageRequestDTO.getSize()));
		end = Math.min(end, last);

		this.prev = start > 1;
		this.next = totalCount > (long) end * pageRequestDTO.getSize();

		this.pageNumList = IntStream.range(start, end).boxed().collect(Collectors.toList());

		if (prev)
			this.prevPage = start - 1;
		if (next)
			this.nextPage = end + 1;

		this.totalPage = last;
		this.current = pageRequestDTO.getPage();
	}

}
