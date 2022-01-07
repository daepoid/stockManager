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
    public String editMyInfo(@ModelAttribute("editMyInfoDTO") EditMyInfoDTO editMyInfoDTO,
                             HttpServletRequest request) {

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member loginMember = memberService.findMemberByLoginId(loginId);
        if(!passwordEncoder.matches(editMyInfoDTO.getPassword(), loginMember.getPassword())) {
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
    public String editMyPassword(@ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO,
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
    public String editMemberForm(@PathVariable("memberId") Long memberId,
                                 Model model) {
        model.addAttribute("editMemberDTO", new EditMemberDTO(memberService.findMember(memberId)));

        // 권한 등급이 관리자인 경우, 사용자인 경우, 권한이 없는 경우
        // 각각에 맞는 editMemberForm 로 넘겨야한다.

        return "members/editMemberForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(@PathVariable("memberId") Long memberId,
                             @Valid @ModelAttribute("editMemberDTO") EditMemberDTO editMemberDTO,
                             BindingResult bindingResult,
                             HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/editMemberForm";
        }

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member loginMember = memberService.findMemberByLoginId(loginId);
        log.info("loginMember = {}", loginMember);
        if(!loginMember.getGradeType().equals(GradeType.CEO) && !loginMember.getGradeType().equals(GradeType.MANAGER)) {
            Member member = memberService.findMember(memberId);
            if(editMemberDTO.getPassword() == null){
                return "members/editMemberForm";
            }
            if(!member.getPassword().equals(passwordEncoder.encode(editMemberDTO.getPassword()))) {
                return "members/editMemberForm";
            }
        }

        // 관리자는 비밀번호 없이 변경할 수 있어야한다.
        // 사용자는 비밀번호를 입력하여 변경하고, 자신의 이름과 전화번호 또는 비밀번호를 변경할 수 있다.
        // 모든 변경기록이 로그로 남아야하고, 로그는 파일로 따로 저장되어야 한다.

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
