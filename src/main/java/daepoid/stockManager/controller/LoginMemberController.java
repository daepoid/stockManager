package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.controller.dto.member.EditMyInfoDTO;
import daepoid.stockManager.controller.dto.member.EditMyPasswordDTO;
import daepoid.stockManager.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/myInfo")
@RequiredArgsConstructor
public class LoginMemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String myInfoRedirect(HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId == null) {
            request.getSession(false).invalidate();
            return "redirect:/login";
        }

        redirectAttributes.addAttribute("loginId", loginId);
        return "redirect:/myInfo/{loginId}";
    }

    /**
     * 직원 개인정보 변경
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/{loginId}")
    public String editMyInfoForm(@PathVariable("loginId") String loginId,
                                 Model model,
                                 HttpServletRequest request) {
        String sessionId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);

        if(!sessionId.equals(loginId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }
        model.addAttribute("editMyInfoDTO", new EditMyInfoDTO(memberService.findMemberByLoginId(sessionId)));
        return "members/editMyInfoForm";
    }

    @PostMapping("/{loginId}")
    public String editMyInfo(@PathVariable("loginId") String loginId,
                             @Valid @ModelAttribute("editMyInfoDTO") EditMyInfoDTO editMyInfoDTO,
                             BindingResult bindingResult,
                             Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("editMyInfoDTO", new EditMyInfoDTO(memberService.findMemberByLoginId(loginId)));
            return "members/editMyInfoForm";
        }

        Member loginMember = memberService.findMemberByLoginId(loginId);
        if(!passwordEncoder.matches(editMyInfoDTO.getPassword(), loginMember.getPassword())) {
            log.info("정보 수정 실패");
            bindingResult.reject("passwordInvalid", "비밀번호가 틀렸습니다.");
            return "members/editMyInfoForm";
        }

        memberService.changeUserName(loginMember.getId(), editMyInfoDTO.getUserName());
        memberService.changePhoneNumber(loginMember.getId(), editMyInfoDTO.getPhoneNumber());
        log.info("{}님이 정보를 수정하였습니다. => 이름: {} / 전화번호: {} -> 이름: {} / 전화번호: {}",
                loginMember.getLoginId(),
                loginMember.getUserName(), loginMember.getPhoneNumber(),
                editMyInfoDTO.getUserName(), editMyInfoDTO.getPhoneNumber());

        return "redirect:/";
    }

    /**
     * 직원 개인정보 변경 - 비밀번호 변경
     * @param editMyPasswordDTO
     * @return
     */
    @GetMapping("/{loginId}/passwordChange")
    public String editMyPasswordForm(@PathVariable("loginId") String loginId,
                                     @ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO,
                                     HttpServletRequest request) {

        String sessionId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(!sessionId.equals(loginId)) {
            return "redirect:/";
        }

        return "members/editMyPasswordForm";
    }

    @PostMapping("/{loginId}/passwordChange")
    public String editMyPassword(@PathVariable("loginId") String loginId,
                                 @Valid @ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        Member loginMember = memberService.findMemberByLoginId(loginId);

        if(bindingResult.hasErrors()) {
            return "members/editMyPasswordForm";
        }

        if(!passwordEncoder.matches(editMyPasswordDTO.getPassword(), loginMember.getPassword())) {
            return "members/editMyPasswordForm";
        }

        // 새로운 비밀번호가 조건을 만족하지 못함

        if(!editMyPasswordDTO.getNewPassword().equals(editMyPasswordDTO.getNewPasswordConfirm())) {
            // 입력한 비밀번호와 비밀번호 확인이 다르다.
            return "members/editMyPasswordForm";
        }
        memberService.changePassword(loginMember.getId(), passwordEncoder.encode(editMyPasswordDTO.getNewPassword()));
        redirectAttributes.addAttribute("loginId", loginId);
        return "redirect:/myInfo/{loginId}";
    }
}
