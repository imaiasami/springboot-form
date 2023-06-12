package com.example.form.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.model.CityCode;
import com.example.form.model.GenderType;
import com.example.form.model.Member;
import com.example.form.model.MemberJoinForm;
import com.example.form.model.MemberUpdateForm;
import com.example.form.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FormController {

	/*
	 * 클라이언트 -> 컨트롤러 class(URL 요청에 대한 Handler, 파라미터 검증, 뷰 리턴) -> 서비스( 비지니스 로직 담당,
	 * 트랜젝션 처리) -> 레파지토리( DB 작업 )
	 */

	private final MemberRepository memberRepository;

	public FormController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@ModelAttribute("genderTypes")
	public GenderType[] genderType() {
		log.info("genderType 메소드 실행");
		log.info("MALE: {}", GenderType.MALE.getDescription());
		log.info("FEMALE: {}", GenderType.FEMALE.getDescription());
		return GenderType.values();
	}

	@ModelAttribute("hobbies")
	public Map<String, String> hobbies() {
		Map<String, String> hobbies = new LinkedHashMap<>();
		hobbies.put("SWIMMING", "수영");
		hobbies.put("FOOTBALL", "축구");
		hobbies.put("BASEBALL", "야구");
		hobbies.put("HIKING", "하이킹");
		return hobbies;
	}

	@ModelAttribute("cityCodes")
	public List<CityCode> cityCodes() {
		List<CityCode> cities = new ArrayList<>();
		cities.add(new CityCode("SEOUL", "서울"));
		cities.add(new CityCode("DAEJEON", "대전"));
		cities.add(new CityCode("DAEGU", "대구"));
		cities.add(new CityCode("BUSAN", "부산"));
		cities.add(new CityCode("GWANGJU", "광주"));
		cities.add(new CityCode("ULSAN", "울산"));
		cities.add(new CityCode("JEJU", "제주"));
		return cities;
	}

//	@GetMapping("/")
//	public String home() {
//		return "index";
//	}

	// 회원가입 폼 이동
	@GetMapping("joinForm")
	public String joinForm(Model model) {
		model.addAttribute("member", new Member());
		log.info("genderTypes:{}", model.getAttribute("genderTypes"));
		return "joinForm";
	}

	// 회원정보 등록
//	@ResponseBody
	@PostMapping("join")
	public String join(@Validated @ModelAttribute("member") MemberJoinForm joinForm, BindingResult result) {

		log.info("member: {}", joinForm);
		log.info("result: {}", result);

//		log.info("birth: {}", memberForm.getBirth().isAfter(LocalDate.now()));
		if (joinForm.getBirth() != null && joinForm.getBirth().isAfter(LocalDate.now())) {
			result.reject("birthError", "생년월일 오류 발생");
		}

		if (result.hasErrors()) {
			return "joinForm";
		}
		// 아이디가 4이상 20이하
//		if (member.getId() != null && member.getId().length() > 3 
//				&& member.getId().length() < 20 ) {

		// MemberJoinForm -> Member 타입 변경
//		Member member = new Member();
//		member.setId(joinForm.getId());
//		member.setPassword(joinForm.getPassword());
//		member.setName(joinForm.getName());
//		member.setGender(joinForm.getGender());
//		member.setHobbies(joinForm.getHobbies());
//		member.setCity(joinForm.getCity());

		Member savedMember = memberRepository.save(MemberJoinForm.toMember(joinForm));
//		}

		return "redirect:/";
	}

	// 전체 회원정보 검색
//	@ResponseBody
	@GetMapping({ "/", "members" })
	public String members(Model model) {
		List<Member> members = memberRepository.findAllMembers();
		model.addAttribute("members", members);
		// 뷰에 전달하는 역할 : 모델, 모델에 담아서 뷰에 전달한다.
		// addAttribute는 두가지인데, (Object), (String, Object) 후자를 쓰자.
		return "members";
		// 뷰를 리턴해준다. 뷰는 members 라고 하는 html 이다.
	}

	// 회원정보 검색 (아이디)
	@GetMapping("member")
	public String member(@RequestParam String id, Model model) {
		Member findMember = memberRepository.findById(id);
		log.info("findMember: {}", findMember);
		model.addAttribute("member", findMember);
		return "memberDetails";
	}

	// 회원정보 삭제하기
	@GetMapping("remove")
	public String remove(@RequestParam String id) {
		log.info("id: {}", id);
		memberRepository.removeById(id);
		return "redirect:/";
	}

	// 회원정보 수정 페이지로 이동
	@GetMapping("updateForm")
	public String updateForm(@RequestParam String id, Model model) {
		log.info("id: {}", id);
		Member findMember = memberRepository.findById(id);
		model.addAttribute("member", findMember);
		return "updateForm";
	}

	// 회원정보 수정
	@PostMapping("update")
	public String update(@Validated @ModelAttribute("member") MemberUpdateForm updateForm, BindingResult result) {
		log.info("update: {}", updateForm);

		// 생년월일 오류 체크
		if (updateForm.getBirth() != null && updateForm.getBirth().isAfter(LocalDate.now())) {
			result.reject("birthError", "생년월일 오류 발생");
		}

		// 패스워드가 빈 값으로 들어올 경우
		Member findMember = memberRepository.findById(updateForm.getId());
		if (updateForm.getPassword().equals("")) {
			updateForm.setPassword(findMember.getPassword());
		}

		log.info("update password: {}", updateForm);

		if (result.hasErrors()) {
			return "updateForm";
		}

		memberRepository.update(MemberUpdateForm.toMember(updateForm));
		return "redirect:/";
	}
}
