package com.review.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Criteria {
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1, 5);
	}
}
