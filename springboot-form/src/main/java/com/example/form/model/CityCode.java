package com.example.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityCode {

	private String code;
	private String displayName;

//	public CityCode(String code, String displayName) {
//		this.code = code;
//		this.displayName = displayName;
//	}

}
