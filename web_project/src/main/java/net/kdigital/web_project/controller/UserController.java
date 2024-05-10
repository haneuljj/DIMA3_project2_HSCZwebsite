package net.kdigital.web_project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.api.OpenApiManager;
import net.kdigital.web_project.dto.CustomerDTO;
import net.kdigital.web_project.dto.CustomerItemDTO;
import net.kdigital.web_project.service.CustomerItemService;
import net.kdigital.web_project.service.CustomerService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	public final CustomerService customerService;
	public final CustomerItemService customerItemService;
	private final OpenApiManager openApiManager;

	// 회원가입 화면 요청
	@GetMapping("/user/join")
	public String join() {
		return "/user/join";
	}

	// 회원 저장
	@PostMapping("/user/joinProc")
	public String joinProc(@ModelAttribute CustomerDTO customerDTO, @ModelAttribute CustomerItemDTO customerItemDTO) {

		log.info("============{}", customerDTO);
		customerService.joinProc(customerDTO);
		customerItemService.insertItem(customerItemDTO);
		log.info("============가입됨");
		return "redirect:/";
	}

	// 로그인 화면 요청
	@GetMapping("/user/login")
	public String login(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "errMessage", required = false) String errMessage) {

		// 이전 URL 정보 가져오기
		String preURL = request.getHeader("Referer");
		if (preURL != null && preURL.contains("/login")) {
			request.getSession().setAttribute("preURL", preURL);
		}
		
		model.addAttribute("error", error);
		model.addAttribute("errMessage", errMessage);

		return "/user/login";
	}

	// 관세사부호 확인
	@PostMapping("/user/ccaCheck")
	@ResponseBody
	public List<String> ccaCheck(@RequestParam(name = "ccaNum") int ccaNum, Model model) {

		List<String> ccaCheckList = openApiManager.CCAOpenApi(Integer.toString(ccaNum));

		log.info("==========={}", ccaCheckList);

		model.addAttribute("ccaCheckList", ccaCheckList);

		return ccaCheckList;
	}

	// 아이디 중복확인
	@PostMapping("/user/confirmId")
	@ResponseBody
	public boolean confirmId(@RequestParam(name = "userId") String userId) {
		boolean confirmId = customerService.findByUserId(userId);

		return !confirmId;
	}

}
