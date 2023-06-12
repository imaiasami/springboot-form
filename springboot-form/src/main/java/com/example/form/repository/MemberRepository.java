package com.example.form.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.form.model.CityCode;
import com.example.form.model.GenderType;
import com.example.form.model.Member;

@Repository
public class MemberRepository {
	private static List<Member> members = new ArrayList<>();

	public MemberRepository() {
		Member memberA = new Member("hong", "1234", "홍길동", GenderType.MALE, LocalDate.now());
		List<String> hobbies = new ArrayList<>();
		hobbies.add("SWIMMING");
		hobbies.add("SOCCER");
		memberA.setHobbies(hobbies);
		memberA.setCity("BUSAN");
		members.add(memberA);
		members.add(new Member("kim", "1234", "김개똥", GenderType.FEMALE, LocalDate.now()));
	}

	// 회원정보 등록
	public Member save(Member member) {
		member.setGender(GenderType.MALE);
		members.add(member);
		return member;
	}

	// 전체 회원정보 검색
	public List<Member> findAllMembers() {
		return members;
	}

	// 회원정보 검색(아이디)
	public Member findById(String id) {
		for (Member member : members) {
			if (member.getId().equals(id)) {
				return member;
			}
		}
		return null;
	}

	// 회원정보 삭제
	public void removeById(String id) {
		for (Member member : members) {
			if (member.getId().equals(id)) {
				members.remove(member);
				return;
			}
		}
	}

	// 회원정보 수정
	public void update(Member updateMember) {
		for (Member member : members) {
			if (member.getId().equals(updateMember.getId())) {
				member.setName(updateMember.getName());
				member.setBirth(updateMember.getBirth());
				member.setGender(updateMember.getGender());
				member.setHobbies(updateMember.getHobbies());
				member.setCity(updateMember.getCity());
				return;
			}
		}
	}
}
