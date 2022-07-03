package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;

import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.domain.users.Member;
import daepoid.stockManager.service.CustomerService;
import daepoid.stockManager.service.FoodService;
import daepoid.stockManager.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final CustomerService customerService;
    private final FoodService foodService;

    /**
     * 스프링 시큐리티 이용시 보여줄 홈 화면
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/")
    public String newHome(Model model, HttpServletRequest request) {

        model.addAttribute("foods", foodService.findFoods());

        if(request.getSession(false) == null) {
            request.getSession();
            return "home";
        }

        // 세션이 있지만 로그인 정보가 없는 경우
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId == null) {
            return "home";
        }

        Optional<Member> loginMember = memberService.findMemberByLoginId(loginId);
        if(loginMember.isPresent()) {
            log.info("직원 {}님 로그인", loginMember.get().getUserName());
            model.addAttribute("memberId", loginMember.get().getId());
            return "loginHome";
        }
        Optional<Customer> loginCustomer = customerService.findCustomerByLoginId(loginId);
        if(loginCustomer.isPresent()) {
            log.info("손님 {}님 로그인", loginId);
            model.addAttribute("customerId", loginCustomer.get().getId());
            return "loginHome";
        }
        request.getSession().invalidate();
        return "home";
    }
}
