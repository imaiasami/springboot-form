package com.example.form.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberUpdateForm {
	private String id;					// 회원 아이디
	
	private String password;			// 패스워드
	
	@NotBlank
	private String name;				// 이름
	
	@NotNull
	private GenderType gender;			// 성별
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;			// 생년월일
	
	private List<String> hobbies;		// 취미
	private String city;				// 도시
	
	public MemberUpdateForm(String id, String password, String name, GenderType gender, LocalDate birth) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
	}
	
	public static Member toMember(MemberUpdateForm updateForm) {
		Member member = new Member();
		member.setId(updateForm.getId());
		member.setPassword(updateForm.getPassword());
		member.setName(updateForm.getName());
		member.setBirth(updateForm.getBirth());
		member.setHobbies(updateForm.getHobbies());
		member.setCity(updateForm.getCity());
		return member;
	}
}









