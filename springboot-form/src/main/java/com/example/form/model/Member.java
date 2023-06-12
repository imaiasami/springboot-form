package com.example.form.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Member {
	private String id;					// 회원 아이디
	private String password;			// 패스워드
	private String name;				// 이름
	private GenderType gender;			// 성별
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;			// 생년월일
	private List<String> hobbies;		// 취미
	private String city;				// 도시
	
	public Member(String id, String password, String name, GenderType gender, LocalDate birth) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
	}
	
}
