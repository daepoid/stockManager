package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.dto.*;
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

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

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
        if(memberService.findMemberByLoginId(joinMemberDTO.getLoginId()) != null) {
            return "members/joinMemberForm";
        }

        Member member = Member.builder()
                .loginId(joinMemberDTO.getLoginId())
                .name(joinMemberDTO.getName())
                .password(passwordEncoder.encode(joinMemberDTO.getPassword()))
                .phoneNumber(joinMemberDTO.getPhoneNumber())
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();

        Long memberId = memberService.join(member);
        log.info("회원가입 성공 / 아이디: {} / 이름: {} / 전화번호: {}", member.getLoginId(), member.getName(), member.getPhoneNumber());
        return "redirect:/";
    }

    @GetMapping("/myInfo")
    public String editMyInfoForm(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member loginMember = memberService.findMemberByLoginId(loginId);
        model.addAttribute("editMyInfoDTO", new EditMyInfoDTO(loginMember));
        return "members/editMyInfoForm";
    }

    @PostMapping("/myInfo")
    public String editMyInfo(@Valid @ModelAttribute("editMyInfoDTO") EditMyInfoDTO editMyInfoDTO,
                             BindingResult bindingResult,
                             HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            return "members/editMyInfoForm";
        }

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member loginMember = memberService.findMemberByLoginId(loginId);
        if(!passwordEncoder.matches(editMyInfoDTO.getPassword(), loginMember.getPassword())) {
            log.info("정보 수정 실패");
            bindingResult.reject("passwordInvalid", "비밀번호가 틀렸습니다.");
            return "members/editMyInfoForm";
        }

        memberService.changeName(loginMember.getId(), editMyInfoDTO.getName());
        memberService.changePhoneNumber(loginMember.getId(), editMyInfoDTO.getPhoneNumber());
        log.info("{}님이 정보를 수정하였습니다. => 이름: {} / 전화번호: {} -> 이름: {} / 전화번호: {}",
                loginMember.getLoginId(),
                loginMember.getName(), loginMember.getPhoneNumber(),
                editMyInfoDTO.getName(), editMyInfoDTO.getPhoneNumber());
        
        return "redirect:/";
    }

    @GetMapping("/myInfo/passwordChange")
    public String editMyPasswordForm(@ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO) {
        return "members/editMyPasswordForm";
    }

    @PostMapping("/myInfo/passwordChange")
    public String editMyPassword(@Valid @ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO,
                                 BindingResult bindingResult,
                                 HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "members/editMyPasswordForm";
        }
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member loginMember = memberService.findMemberByLoginId(loginId);
        if(!passwordEncoder.matches(editMyPasswordDTO.getPassword(), loginMember.getPassword())) {
            return "members/editMyPasswordForm";
        }

        // 새로운 비밀번호가 조건을 만족하지 못함

        if(!editMyPasswordDTO.getNewPassword().equals(editMyPasswordDTO.getNewPasswordConfirm())) {
            // 입력한 비밀번호와 비밀번호 확인이 다르다.
            return "members/editMyPasswordForm";
        }
        memberService.changePassword(loginMember.getId(), passwordEncoder.encode(editMyPasswordDTO.getNewPassword()));
        return "redirect:/members/myInfo";
    }

    @GetMapping("/{memberId}/edit")
    public String editMemberByAdminForm(@PathVariable("memberId") Long memberId,
                                        Model model) {
        model.addAttribute("editMemberDTO", new EditMemberDTO(memberService.findMember(memberId)));

        // 권한 등급이 관리자인 경우, 사용자인 경우, 권한이 없는 경우
        // 각각에 맞는 editMemberForm 로 넘겨야한다.

        return "members/editMemberForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMemberByAdmin(@PathVariable("memberId") Long memberId,
                                    @Valid @ModelAttribute("editMemberDTO") EditMemberDTO editMemberDTO,
                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/editMemberForm";
        }

        // 어차피 SecurityConfig 부분에서 관리자인지 체크해야한다.
        // 왜냐? 관리자는 비밀번호 없이 회원의 정보를 변경할 수 있도록 만든다.
        // 모든 변경기록이 로그로 남아야하고, 로그는 파일로 따로 저장되어야 한다.

        Member member = memberService.findMember(memberId);
        log.info("관리자 페이지에서 {}님 정보 수정 \n{} => {}\n{} => {}\n{} => {}\n{} => {}",
                member.getName(),
                member.getName(), editMemberDTO.getName(),
                member.getPhoneNumber(), editMemberDTO.getPhoneNumber(),
                member.getGradeType(), editMemberDTO.getGradeType(),
                member.getMemberStatus(), editMemberDTO.getMemberStatus());

        memberService.changeName(memberId, editMemberDTO.getName());
        memberService.changePhoneNumber(memberId, editMemberDTO.getPhoneNumber());
        memberService.changeGradeType(memberId, editMemberDTO.getGradeType());
        memberService.changeMemberStatus(memberId, editMemberDTO.getMemberStatus());
        return "redirect:/members";
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
