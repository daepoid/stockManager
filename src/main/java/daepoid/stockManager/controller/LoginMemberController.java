package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.users.Member;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/my-info")
@RequiredArgsConstructor
public class LoginMemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String myInfoForward(Model model, HttpServletRequest request) {

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId == null) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        Optional<Member> member = memberService.findMemberByLoginId(loginId);
        if(member.isEmpty()) {
            log.info("My Info Error loginId = {}", loginId);
            request.getSession(false).invalidate();
            return "redirect:/login";
        }

        model.addAttribute("editMyInfoDTO", new EditMyInfoDTO(member.get()));
        return "members/editMyInfoForm";
    }

    @PostMapping("")
    public String editMyInfo(@Valid @ModelAttribute("editMyInfoDTO") EditMyInfoDTO editMyInfoDTO,
                             BindingResult bindingResult,
                             Model model,
                             HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId == null) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        Optional<Member> member = memberService.findMemberByLoginId(loginId);
        if(member.isEmpty()) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("editMyInfoDTO", new EditMyInfoDTO(member.get()));
            return "members/editMyInfoForm";
        }

        if(!passwordEncoder.matches(editMyInfoDTO.getPassword(), member.get().getPassword())) {
            model.addAttribute("editMyInfoDTO", new EditMyInfoDTO(member.get()));
            log.info("?????? ?????? ??????");
            bindingResult.reject("passwordInvalid", "??????????????? ???????????????.");
            return "members/editMyInfoForm";
        }

        if(!member.get().getLoginId().equals(editMyInfoDTO.getLoginId())) {
            if(memberService.findMemberByLoginId(editMyInfoDTO.getLoginId()).isPresent()) {
                log.info("????????? ?????????");
                return "members/editMyInfoForm";
            }
        }

        memberService.changeLoginId(member.get().getId(), editMyInfoDTO.getLoginId());
        memberService.changeUserName(member.get().getId(), editMyInfoDTO.getUserName());
        memberService.changePhoneNumber(member.get().getId(), editMyInfoDTO.getPhoneNumber());

        log.info("{}?????? ????????? ?????????????????????. => ??????: {} / ????????????: {} -> ??????: {} / ????????????: {}",
                member.get().getLoginId(),
                member.get().getUserName(), member.get().getPhoneNumber(),
                editMyInfoDTO.getUserName(), editMyInfoDTO.getPhoneNumber());
        request.getSession(false).invalidate();
        return "redirect:/";
    }

    /**
     * ?????? ???????????? ?????? - ???????????? ??????
     * @param editMyPasswordDTO
     * @return
     */
    @GetMapping("/{loginId}/passwordChange")
    public String editMyPasswordForm(@PathVariable("loginId") String loginId,
                                     @ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO,
                                     HttpServletRequest request) {

        String sessionId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(!sessionId.equals(loginId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        return "members/editMyPasswordForm";
    }

    @PostMapping("/{loginId}/passwordChange")
    public String editMyPassword(@PathVariable("loginId") String loginId,
                                 @Valid @ModelAttribute("editMyPasswordDTO") EditMyPasswordDTO editMyPasswordDTO,
                                 BindingResult bindingResult,
                                 HttpServletRequest request) {

        Optional<Member> member = memberService.findMemberByLoginId(loginId);
        if(bindingResult.hasErrors() || member.isEmpty()) {
            request.getSession(false).invalidate();
            return "members/editMyPasswordForm";
        }

        // ????????? ???????????? ???????????? ??????
        if(!passwordEncoder.matches(editMyPasswordDTO.getPassword(), member.get().getPassword())) {
            return "members/editMyPasswordForm";
        }
        
        // ????????? ???????????? ??????
        if(!editMyPasswordDTO.getNewPassword().equals(editMyPasswordDTO.getNewPasswordConfirm())) {
            return "members/editMyPasswordForm";
        }

        memberService.changePassword(member.get().getId(), passwordEncoder.encode(editMyPasswordDTO.getNewPassword()));
        return "redirect:/myInfo";
    }
}
