package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.Member;
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

//    private final LoginService loginService;
    private final MemberService memberService;

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

//    @PostMapping("/login")
//    public String loginMember(@Valid @ModelAttribute("loginMemberDTO") LoginMemberDTO loginMemberDTO,
//                              BindingResult bindingResult,
//                              @RequestParam(defaultValue = "/") String redirectURL,
//                              HttpServletRequest request) {
//
//        if(bindingResult.hasErrors()) {
//            log.info("bindingResult = {}", bindingResult);
//            return "members/loginMemberForm";
//        }
//
//        log.info("loginMemberDTO = {}, {}", loginMemberDTO.getLoginId(), loginMemberDTO.getPassword());
//
//        // 로그인 결과가 false 일때
//        if(!loginService.login(loginMemberDTO.getLoginId(), loginMemberDTO.getPassword())) {
//            log.info("loginService.login() == false");
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 확인해주세요");
//            return "members/loginMemberForm";
//        }
//
//        log.info("loginService.login() == true");
//
//        /*
//          세션이 있으면 있는 세션 반환하고 세션이 없으면 새로운 세션을 생성
//          getSession(false)일 떄 세션이 없으면 새로운 세션을 생성하지 않음
//         */
//        HttpSession session = request.getSession();
//
//        /*
//          LoginMemberDTO 에 member.getId()의 값을 저장하면 쉽게 이용가능하지만
//          해당 값이 외부로 나올경우 문제가 발생할 수 있다고 생각되어 필요할 때마다 id를 찾는다.
//         */
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMemberDTO);
//        log.info("session.getAttribute = {}", session.getAttribute(SessionConst.LOGIN_MEMBER));
//        log.info("redirectURL = {}", redirectURL);
//        return "redirect:" + redirectURL;
//    }

    @PostMapping("/login")
    public String securityLoginMember(@RequestParam(defaultValue = "/") String redirectURL,
                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute(SessionConst.SECURITY_LOGIN);
        log.info("loginId = {}", loginId);
        session.setAttribute(SessionConst.SECURITY_LOGIN, loginId);

        log.info("redirectURL = {}", redirectURL);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String getlogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
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
