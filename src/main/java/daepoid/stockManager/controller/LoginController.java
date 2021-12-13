package daepoid.stockManager.controller;

import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    /**
     * 회원 로그인
     * @param loginMemberDTO
     * @return
     */
    @GetMapping("/login")
    public String loginMemberForm(@ModelAttribute("memberLoginDTO") LoginMemberDTO loginMemberDTO) {
        return "members/loginMemberForm";
    }

    @PostMapping("/login")
    public String loginMember(@Valid @ModelAttribute("memberLoginDTO") LoginMemberDTO loginMemberDTO,
                              BindingResult bindingResult,
                              @RequestParam(defaultValue="/") String redirectURL,
                              HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "members/loginMemberForm";
        }

        Member loginMember = memberService.login(loginMemberDTO.getLoginId(), loginMemberDTO.getPassword());
        if(loginMember == null) {
            return "members/loginMemberForm";
        }

        log.info("로그인 성공 = {}", loginMember);

        // 세션 처리


        return "redirect:" + redirectURL;
    }

}
