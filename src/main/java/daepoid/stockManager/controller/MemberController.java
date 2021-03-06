package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.users.GradeType;
import daepoid.stockManager.domain.users.Member;
import daepoid.stockManager.domain.search.MemberSearch;
import daepoid.stockManager.domain.users.MemberStatus;
import daepoid.stockManager.controller.dto.member.EditMemberDTO;
import daepoid.stockManager.controller.dto.member.JoinMemberDTO;
import daepoid.stockManager.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 직원 리스트
     * @param model
     * @return
     */
    @GetMapping("")
    public String memberList(@ModelAttribute("memberSearch") MemberSearch memberSearch,
                             Model model) {
        model.addAttribute("members", memberService.findByMemberSearch(memberSearch));
        return "members/memberList";
    }

    /**
     * 직원 회원 가입
     * @param joinMemberDTO
     * @return
     */
    @GetMapping("/new")
    public String joinMemberForm(@ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO) {
        return "members/joinMemberForm";
    }

    @PostMapping("/new")
    public String joinMember(@Valid @ModelAttribute("joinMemberDTO") JoinMemberDTO joinMemberDTO,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "members/joinMemberForm";
        }

        // 패스워드와 패스워드 확인이 동일하지 않음
        if(!joinMemberDTO.getPassword().equals(joinMemberDTO.getPasswordCheck())) {
            return "members/joinMemberForm";
        }

        // 이미 동일한 로그인 아이디가 존재하는 경우
        if(memberService.findMemberByLoginId(joinMemberDTO.getLoginId()).isPresent()) {
            return "members/joinMemberForm";
        }

        if(!joinMemberDTO.getPhoneNumber().matches("^(01[0-1|6-9])-?(\\d{3,4})-?(\\d{4})$")) {
            return "members/joinMemberForm";
        }

        Member member = Member.builder()
                .loginId(joinMemberDTO.getLoginId())
                .userName(joinMemberDTO.getName())
                .password(passwordEncoder.encode(joinMemberDTO.getPassword()))
                .phoneNumber(joinMemberDTO.getPhoneNumber())
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();

        Long memberId = memberService.join(member);
        log.info("회원가입 성공 / 아이디: {} / 이름: {} / 전화번호: {}", member.getLoginId(), member.getUserName(), member.getPhoneNumber());
        return "redirect:/";
    }


    @ResponseBody
    @PostMapping("/new/idCheck")
    public boolean idCheck(String loginId) {
        return memberService.findMemberByLoginId(loginId) != null;
    }


    @ResponseBody
    @PostMapping("/new/pwCheck")
    public boolean pwCheck(String password, String passwordCheck) {
        if(password == null || password.equals("")){
            return false;
        }
        return Objects.requireNonNull(password).equals(passwordCheck);
    }

    @ResponseBody
    @PostMapping("/new/phoneCheck")
    public boolean phoneCheck(String phoneNumber) {
        return phoneNumber.matches("^(01[0-1|6-9])-?(\\d{3,4})-?(\\d{4})$");
    }

    /**
     * 직원 개인정보 변경 - 관리자 권한
     * @param memberId
     * @param model
     * @return
     */
    @GetMapping("/{memberId}/edit")
    public String editMemberByAdminForm(@PathVariable("memberId") Long memberId,
                                        Model model,
                                        HttpServletRequest request) {

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Optional<Member> member = memberService.findMemberByLoginId(loginId);

        if (member.isPresent() && member.get().getGradeType().equals(GradeType.CEO) || member.get().getId().equals(memberId)) {
            model.addAttribute("editMemberDTO", new EditMemberDTO(member.get()));
            return "members/editMemberForm";
        }

        request.getSession(false).invalidate();
        return "redirect:/";
    }

    @PostMapping("/{memberId}/edit")
    public String editMemberByAdmin(@PathVariable("memberId") Long memberId,
                                    @Valid @ModelAttribute("editMemberDTO") EditMemberDTO editMemberDTO,
                                    Model model,
                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/editMemberForm";
        }

        // 어차피 SecurityConfig 부분에서 관리자인지 체크해야한다.
        // 왜냐? 관리자는 비밀번호 없이 회원의 정보를 변경할 수 있도록 만든다.
        // 모든 변경기록이 로그로 남아야하고, 로그는 파일로 따로 저장되어야 한다.
        Optional<Member> member = memberService.findMember(memberId);
        log.info("관리자 페이지에서 {}님 정보 수정 \n{} => {}\n{} => {}\n{} => {}\n{} => {}",
                member.get().getUserName(),
                member.get().getUserName(), editMemberDTO.getUserName(),
                member.get().getPhoneNumber(), editMemberDTO.getPhoneNumber(),
                member.get().getGradeType(), editMemberDTO.getGradeType(),
                member.get().getMemberStatus(), editMemberDTO.getMemberStatus());

        memberService.changeUserName(memberId, editMemberDTO.getUserName());
        memberService.changePhoneNumber(memberId, editMemberDTO.getPhoneNumber());
        memberService.changeGradeType(memberId, editMemberDTO.getGradeType());
        memberService.changeMemberStatus(memberId, editMemberDTO.getMemberStatus());
        return "redirect:/members";
    }
}
