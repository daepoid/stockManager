package daepoid.stockManager.controller;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.dto.CreateDutyDTO;
import daepoid.stockManager.dto.EditDutyDTO;
import daepoid.stockManager.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/duties")
@RequiredArgsConstructor
public class DutyController {

    private final DutyService dutyService;

    @GetMapping("")
    public String dutyListForm(Model model) {
        model.addAttribute("duties", dutyService.findDuties());
        return "duties/dutyList";
    }

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

    @GetMapping("/{dutyId}/edit")
    public String editDutyForm(@PathVariable("dutyId") Long dutyId, Model model) {
        model.addAttribute("editDutyDTO", new EditDutyDTO(dutyService.findDuty(dutyId)));
        return "duties/editDutyForm";
    }

    @PostMapping("/{dutyId}/edit")
    public String editDuty(@PathVariable("dutyId") Long dutyId,
                           @ModelAttribute("editDutyDTO") EditDutyDTO editDutyDTO,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "duties/editDutyForm";
        }

        dutyService.changeName(dutyId, editDutyDTO.getName());
        dutyService.changeIncentive(dutyId, editDutyDTO.getIncentive());
        dutyService.changeMembers(dutyId, editDutyDTO.getMembers());

        return "redirect:/duties";
    }
}
