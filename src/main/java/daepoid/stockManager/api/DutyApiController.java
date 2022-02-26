package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.duty.*;
import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.service.DutyService;
import daepoid.stockManager.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = {"직무 관리 API"})
@RequiredArgsConstructor
public class DutyApiController {

    private final DutyService dutyService;
    private final MemberService memberService;

    /**
     * 조회
     * @return
     */
    @GetMapping("/v1/duties")
    @ApiOperation(value="전체 직무 조회", notes="전체 직무 리스트를 반환")
    public Result dutiesV1() {
        List<Duty> duties = dutyService.findDuties();
        //엔티티 -> DTO 변환
        List<DutyDTO> DutyDTOs = duties.stream()
                .map(DutyDTO::new)
                .collect(Collectors.toList());
        return new Result(DutyDTOs);
    }

    @GetMapping("/v2/duties")
    @ApiOperation(value="전체 직무 조회", notes="전체 직무 리스트를 반환")
    public Result dutiesV2(@RequestBody @Valid PagingDutyRequestDTO requestDTO) {
        List<Duty> duties;

        if(requestDTO.getFirstResult() == null) {
            duties = dutyService.findDuties(requestDTO.getMaxResult());
        } else {
            duties = dutyService.findDuties(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        //엔티티 -> DTO 변환
        List<DutyDTO> DutyDTOs = duties.stream()
                .map(DutyDTO::new)
                .collect(Collectors.toList());
        return new Result(DutyDTOs);
    }

    /**
     * 등록 V1
     * @param requestDTO
     * @return
     */
    @PostMapping("/v1/duties")
    @ApiOperation(value="새로운 직무 생성", notes="새로운 직무를 생성하고 직무 아이디를 반환")
    public CreateDutyResponseDTO createDutyV1(@RequestBody @Valid CreateDutyRequestDTO requestDTO) {
        Duty duty = Duty.builder()
                .name(requestDTO.getName())
                .incentive(requestDTO.getIncentive())
                .members(requestDTO.getMembers())
                .build();
        Long dutyId = dutyService.saveDuty(duty);
        return new CreateDutyResponseDTO(dutyId);
    }

    /**
     * 단일 조회 V1
     * @param dutyId
     * @return
     */
    @GetMapping("/v1/duties/{dutyId}")
    @ApiOperation(value="직무 조회", notes="직무 아이디를 이용하여 직무를 조회하고 직무 정보를 반환")
    public DutyDTO findDutyV1(@PathVariable("dutyId") Long dutyId) {
        return new DutyDTO(dutyService.findDuty(dutyId));
    }

    /**
     * 부분 등록 V1
     * @param dutyId
     * @param requestDTO
     * @return
     */
    @PostMapping("/v1/duties/{dutyId}")
    @ApiOperation(value="직무에 직원 할당", notes="직무 아이디와 직원정보(직원 아이디 또는 직원 로그인 아이디)를 이용하여 직무에 직원을 할당")
    public AddDutyMemberResponseDTO AddDutyMemberV1(@PathVariable("dutyId") Long dutyId,
                                                    @RequestBody @Valid AddDutyMemberRequestDTO requestDTO) {

        if(requestDTO.getMemberId() != null) {
            dutyService.addMember(dutyId, memberService.findMember(requestDTO.getMemberId()));
            return new AddDutyMemberResponseDTO(dutyService.findDuty(dutyId));
        }
        else if(requestDTO.getLoginId() != null) {
            dutyService.addMember(dutyId, memberService.findMemberByLoginId(requestDTO.getLoginId()));
            return new AddDutyMemberResponseDTO(dutyService.findDuty(dutyId));
        }
        throw new IllegalArgumentException("찾을 수 없음");
    }

    /**
     * Put 수정 V1
     * @param dutyId
     * @param requestDTO
     * @return
     */
    @PutMapping("/v1/duties/{dutyId}")
    @ApiOperation(value="직무 정보 수정", notes="직무 아이디를 이용하여 직무 이름, 인센티브, 할당직원을 수정하고, 수정된 직무의 정보를 반환")
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

    /**
     * 삭제 V1
     * @param dutyId
     * @return
     */
    @DeleteMapping("/v1/duties/{dutyId}")
    @ApiOperation(value="직무 삭제", notes="직무 아이디를 이용하여 직무를 삭제하고 삭제된 직무 아이디를 반환")
    public DeleteDutyResponseDTO deleteDutyV1(@PathVariable("dutyId") Long dutyId) {
        Duty duty = dutyService.findDuty(dutyId);
        dutyService.removeDuty(dutyId);
        return new DeleteDutyResponseDTO(duty.getId());
    }

    /**
     * 직무 직원할당 해제 V1
     * @param dutyId
     * @param memberId
     * @return
     */
    @DeleteMapping("/v1/duties/{dutyId}/{memberId}")
    @ApiOperation(value="직무 할당 직원정보 삭제", notes="직무 아이디와 직원 아이디를 이용하여 해당 직원을 직무할당 해제 후 직무 아이디와 직원 아이디 반환")
    public DeleteDutyMemberResponseDTO deleteDutyMemberV1(@PathVariable("dutyId") Long dutyId,
                                                          @PathVariable("memberId") Long memberId) {
        dutyService.removeMember(dutyId, memberService.findMember(memberId));
        return new DeleteDutyMemberResponseDTO(dutyId, memberId);
    }
}
