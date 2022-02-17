package daepoid.stockManager.controller;

import daepoid.stockManager.controller.dto.member.LoginMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class LoginController {

    /**
     * 로그인 페이지
     * @param loginMemberDTO
     * @return
     */
    @GetMapping("/login")
    public String loginMemberForm(@ModelAttribute("loginMemberDTO") LoginMemberDTO loginMemberDTO) {
        return "members/loginMemberForm";
    }
}