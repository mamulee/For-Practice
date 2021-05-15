package com.review.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private int realEnd;
	private boolean next, prev;
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;

		this.endPage = (int) (Math.ceil(cri.getPageNum() / (cri.getAmount() * 1.0))) * cri.getAmount();
		this.startPage = endPage - cri.getAmount() + 1;
		
		this.realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEnd;
	}
}
