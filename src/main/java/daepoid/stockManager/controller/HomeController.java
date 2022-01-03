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

    @GetMapping("/")
    public String securityLoginHome(Model model, HttpServletRequest request) {
        // 세션이 없는 경우
        if(request.getSession(false) == null) {
            request.getSession();
            return "home";
        }

        // 세션이 있지만 로그인 정보가 없는 경우
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);

        if(loginId == null) {
            return "home";
        }

        log.info("{}님 로그인", loginId);
        model.addAttribute("loginId", loginId);
        return "loginHome";
    }
}
