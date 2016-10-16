package com.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	ACTIVE("Active"), UNACTIVE("Unactive"), DELETED("Deleted");

	private String value;
}