package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.JoinMemberDTO;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.dto.SecurityLoginMemberDTO;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;
import daepoid.stockManager.service.LoginService;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/sign-up")
    public String signUpMemberForm(@ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO) {
        return "members/joinMemberForm";
    }

    @PostMapping("/sign-up")
    public String signUpMember(@Valid @ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("login Error = {}", bindingResult);
            return "members/joinMemberForm";
        }

        // 패스워드와 패스워드 확인이 동일하지 않음
        if(!joinMemberDTO.getPassword().equals(joinMemberDTO.getPasswordCheck())) {
            return "members/joinMemberForm";
        }

        Member findMember = memberService.findMemberByLoginId(joinMemberDTO.getLoginId());
        if(findMember != null) {
            return "members/joinMemberForm";
        }

        // 회원 가입은 자유롭게 가능하나 인증을 해주는 방법이 있어야 한다.
        Member member = Member.createMember(joinMemberDTO.getLoginId(), joinMemberDTO.getName(), joinMemberDTO.getPassword(), joinMemberDTO.getPhoneNumber());

        Long memberId = memberService.join(member);
        log.info("memberId = {}", memberId);
        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginMemberForm(@ModelAttribute("loginMemberDTO") LoginMemberDTO loginMemberDTO) {
        return "members/loginMemberForm";
    }

    @PostMapping("/login")
    public String securityLoginMember(@ModelAttribute("loginMemberDTO") LoginMemberDTO loginMemberDTO,
                                      BindingResult bindingResult,
                                      @RequestParam(defaultValue = "/") String redirectURL,
                                      HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "members/loginMemberForm";
        }

        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute(SessionConst.SECURITY_LOGIN);
        log.info("loginId = {}", loginId);
        session.setAttribute(SessionConst.SECURITY_LOGIN, loginId);

        log.info("redirectURL = {}", redirectURL);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}