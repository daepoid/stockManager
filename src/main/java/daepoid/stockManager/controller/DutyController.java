package daepoid.stockManager.controller;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.controller.dto.duty.CreateDutyDTO;
import daepoid.stockManager.controller.dto.duty.CreateDutyMemberDTO;
import daepoid.stockManager.controller.dto.duty.EditDutyDTO;
import daepoid.stockManager.domain.search.DutySearch;
import daepoid.stockManager.service.DutyService;
import daepoid.stockManager.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/duties")
@RequiredArgsConstructor
public class DutyController {

    private final DutyService dutyService;
    private final MemberService memberService;

    /**
     * 직무 리스트 - 직무 전체 보기
     * @param model
     * @return
     */
    @GetMapping("")
    public String dutyListForm(@ModelAttribute("dutySearch") DutySearch dutySearch,
                               Model model) {
        model.addAttribute("duties", dutyService.findByDutySearch(dutySearch));
//        model.addAttribute("duties", dutyService.findDuties());
        return "duties/dutyList";
    }

    /**
     * 직무 리스트에서 새로운 직무 유형 추가하기
     * @param createDutyDTO
     * @return
     */
    @GetMapping("/new")
    public String createDutyForm(@ModelAttribute("createDutyDTO") CreateDutyDTO createDutyDTO) {
        return "duties/createDutyForm";
    }

    @PostMapping("/new")
    public String createDuty(@Valid @ModelAttribute("createDutyDTO") CreateDutyDTO createDutyDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "duties/createDutyForm";
        }

        Duty duty = Duty.builder()
                .name(createDutyDTO.getName())
                .incentive(createDutyDTO.getIncentive())
                .build();

        dutyService.saveDuty(duty);
        return "redirect:/duties";
    }

    /**
     * 직무 리스트에서 직무 정보 수정 메뉴로 이동
     * @param dutyId
     * @param model
     * @return
     */
    @GetMapping("/{dutyId}")
    public String editDutyForm(@PathVariable("dutyId") Long dutyId, Model model) {
        model.addAttribute("editDutyDTO", new EditDutyDTO(dutyService.findDuty(dutyId)));
        return "duties/editDutyForm";
    }

    @PostMapping("/{dutyId}")
    public String editDuty(@PathVariable("dutyId") Long dutyId,
                           @ModelAttribute("editDutyDTO") EditDutyDTO editDutyDTO,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "duties/editDutyForm";
        }

        dutyService.changeName(dutyId, editDutyDTO.getName());
        dutyService.changeIncentive(dutyId, editDutyDTO.getIncentive());
        return "redirect:/duties";
    }

    /**
     * 직무 리스트에서 [보기] 버튼을 통해 직무 할당자 리스트에 직무 할당자를 추가
     * @param dutyId
     * @param model
     * @return
     */
    @GetMapping("/{dutyId}/new")
    public String addDutyMemberForm(@PathVariable("dutyId") Long dutyId,
                                    Model model) {
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("createDutyMemberDTO", new CreateDutyMemberDTO(dutyService.findDuty(dutyId).getName()));
        return "duties/addDutyMemberForm";
    }

    @PostMapping("/{dutyId}/new")
    public String addDutyMember(@PathVariable("dutyId") Long dutyId,
                                @Valid @ModelAttribute("createDutyMemberDTO") CreateDutyMemberDTO createDutyMemberDTO,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("members", memberService.findMembers());
            return "duties/addDutyMemberForm";
        }

        dutyService.addMember(dutyId, memberService.findMember(createDutyMemberDTO.getMemberId()));

        redirectAttributes.addAttribute("dutyId", dutyId);
        return "redirect:/duties/{dutyId}";
    }

    /**
     * 직무 리스트에서 [보기] 버튼을 통해 직무 할당자 리스트에 직무 할당자를 제거
     * @param dutyId
     * @return
     */
    @PostMapping("/{dutyId}/{memberId}/cancel")
    public String deleteDutyMember(@PathVariable("dutyId") Long dutyId,
                                   @PathVariable("memberId") Long memberId,
                                   RedirectAttributes redirectAttributes) {
        dutyService.removeMember(dutyId, memberService.findMember(memberId));
        redirectAttributes.addAttribute("dutyId", dutyId);
        return "redirect:/duties/{dutyId}";
    }
}
