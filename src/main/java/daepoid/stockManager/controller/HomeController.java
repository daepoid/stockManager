package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.argumentresolver.Login;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.dto.SecurityLoginMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

//    @GetMapping("/")
//    public String home() {
//        log.info("Home Controller");
//        return "home";
//    }

//    @GetMapping("/")
    public String loginHome(@Login LoginMemberDTO loginMemberDTO, Model model) {
        log.info("loginHome loginMember = {}", loginMemberDTO);

        if(loginMemberDTO.getLoginId() == null) {
            log.info("로그인하지 않았습니다.");
            return "home";
        }

        model.addAttribute("loginMemberDTO", loginMemberDTO);
        return "loginHome";
    }

    @GetMapping("/")
    public String securityLoginHome(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        log.info("loginId = {}", loginId);

        if(loginId == null) {
            log.info("로그인하지 않았습니다.");
            return "home";
        }

        model.addAttribute("loginId", loginId);
        return "loginHome";
    }
}
