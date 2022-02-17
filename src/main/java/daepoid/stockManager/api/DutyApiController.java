package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.duty.*;
import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.service.DutyService;
import daepoid.stockManager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/duties")
@RequiredArgsConstructor
public class DutyApiController {

    private final DutyService dutyService;
    private final MemberService memberService;

    @GetMapping("")
    public Result dutiesV1() {

        List<Duty> duties = dutyService.findDuties();

        //엔티티 -> DTO 변환
        List<DutyDTO> collect = duties.stream()
                .map(DutyDTO::new)
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("")
    public CreateDutyResponseDTO createDutyV1(@RequestBody @Valid CreateDutyRequestDTO requestDTO) {
        Duty duty = Duty.builder()
                .name(requestDTO.getName())
                .incentive(requestDTO.getIncentive())
                .members(requestDTO.getMembers())
                .build();
        Long dutyId = dutyService.saveDuty(duty);
        return new CreateDutyResponseDTO(dutyId);
    }

    @GetMapping("/{dutyId}")
    public DutyDTO findDutyV1(@PathVariable("dutyId") Long dutyId) {
        return new DutyDTO(dutyService.findDuty(dutyId));
    }

    @PostMapping("/{dutyId}")
    public AddDutyMemberResponseDTO AddDutyMemberV1(@PathVariable("dutyId") Long dutyId,
                                                    @RequestBody @Valid AddDutyMemberRequestDTO requestDTO) {

        if(requestDTO.getMemberId() == null && requestDTO.getLoginId() == null) {
            throw new IllegalArgumentException("찾을 수 없음");
        }
        else if(requestDTO.getMemberId() != null) {
            dutyService.addMember(dutyId, memberService.findMember(requestDTO.getMemberId()));
        }
        else if(requestDTO.getLoginId() != null) {
            dutyService.addMember(dutyId, memberService.findMemberByLoginId(requestDTO.getLoginId()));
        }
        return new AddDutyMemberResponseDTO(dutyService.findDuty(dutyId));
    }

    @PutMapping("/{dutyId}")
    public UpdateDutyResponseDTO updateDutyV1(@PathVariable("dutyId") Long dutyId,
                                              @RequestBody @Valid UpdateDutyRequestDTO requestDTO) {
        Duty duty = dutyService.findDuty(dutyId);
        if(requestDTO.getName() != null) {
            dutyService.changeName(dutyId, requestDTO.getName());
        }
        if(requestDTO.getIncentive() != null) {
            dutyService.changeIncentive(dutyId, requestDTO.getIncentive());
        }
        if(requestDTO.getMembers() != null) {
            dutyService.changeMembers(dutyId, requestDTO.getMembers());
        }

        return new UpdateDutyResponseDTO(duty);
    }

    @DeleteMapping("/{dutyId}")
    public DeleteDutyResponseDTO deleteDutyV1(@PathVariable("dutyId") Long dutyId) {
        Duty duty = dutyService.findDuty(dutyId);
        dutyService.removeDuty(dutyId);
        return new DeleteDutyResponseDTO(duty.getId());
    }

    @DeleteMapping("/{dutyId}/{memberId}")
    public DeleteDutyMemberResponseDTO deleteDutyMemberV1(@PathVariable("dutyId") Long dutyId,
                                                          @PathVariable("memberId") Long memberId) {
        dutyService.removeMember(dutyId, memberService.findMember(memberId));
        return new DeleteDutyMemberResponseDTO(dutyId, memberId);
    }
}
