package daepoid.stockManager.controller;

import daepoid.stockManager.argumentresolver.Login;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.LoginMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

//    @GetMapping("/")
//    public String home() {
//        log.info("Home Controller");
//        return "home";
//    }

    @GetMapping("/")
    public String loginHome(@Login LoginMemberDTO loginMemberDTO, Model model) {
        log.info("loginMember = {}", loginMemberDTO);

        if(loginMemberDTO == null) {
            log.info("로그인하지 않았습니다.");
            return "home";
        }

        model.addAttribute("loginMemberDTO", loginMemberDTO);
        return "loginHome";
    }
}
