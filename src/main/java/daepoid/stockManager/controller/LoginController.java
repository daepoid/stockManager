package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.service.LoginService;
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

    private final LoginService loginService;

    /**
     * 회원 로그인
     * @param loginMemberDTO
     * @return
     */
    @GetMapping("/login")
    public String loginMemberForm(@ModelAttribute("loginMemberDTO") LoginMemberDTO loginMemberDTO) {
        log.info("loginMemberForm 실행");
        return "members/loginMemberForm";
    }

    @PostMapping("/login")
    public String loginMember(@Valid @ModelAttribute("loginMemberDTO") LoginMemberDTO loginMemberDTO,
                              BindingResult bindingResult,
                              @RequestParam(defaultValue = "/") String redirectURL,
                              HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/loginMemberForm";
        }

        if(loginService.login(loginMemberDTO.getLoginId(), loginMemberDTO.getPassword()) == null) {
            log.info("로그인 실패 loginMember");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 확인해주세요");
            return "members/loginMemberForm";
        }

        log.info("로그인 성공 loginMember");

        // 세션 처리

        // 세션이 있으면 있는 세션 반환하고 세션이 없으면 새로운 세션을 생성
        // getSession(false)일 떄 세션이 없으면 새로운 세션을 생성하지 않음
        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMemberDTO);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
