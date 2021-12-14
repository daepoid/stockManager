package daepoid.stockManager.controller;

import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.dto.JoinMemberDTO;
import daepoid.stockManager.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     * @param joinMemberDTO
     * @return
     */
    @GetMapping("/new")
    public String joinMemberForm(@ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO) {
        return "members/joinMemberForm";
    }

    @PostMapping("/new")
    public String joinMember(@Valid @ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO, BindingResult bindingResult) {
//    public String joinMember(@ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO) {
//        if(bindingResult.hasErrors()) {
//            log.info("login Error = {}", bindingResult);
//            return "members/memberJoinForm";
//        }

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
        return "redirect:/";
    }

    /**
     * 회원 관리
     */
    @GetMapping("")
    public String memberList(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
