package com.example.form.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberJoinForm {
	@Size(min = 4, max = 20)
	private String id;					// 회원 아이디
	
	@Size(min = 4, max = 20)
	private String password;			// 패스워드
	
	@NotBlank
	private String name;				// 이름
	
	@NotNull
	private GenderType gender;			// 성별
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;			// 생년월일
	
	private List<String> hobbies;		// 취미
	private String city;				// 도시
	
	public MemberJoinForm(String id, String password, String name, GenderType gender, LocalDate birth) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
	}
	
	public static Member toMember(MemberJoinForm joinForm) {
		Member member = new Member();
		member.setId(joinForm.getId());
		member.setPassword(joinForm.getPassword());
		member.setName(joinForm.getName());
		member.setBirth(joinForm.getBirth());
		member.setGender(joinForm.getGender());
		member.setHobbies(joinForm.getHobbies());
		member.setCity(joinForm.getCity());
		return member;
	}
}













