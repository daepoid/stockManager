package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.member.*;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = {"직원 관리 API"})
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/v1/members")
    @ApiOperation(value="전체 직원 조회", notes="전체 직원 정보를 조회하고 반환")
    public Result membersV1() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDTO> MemberDTOs = findMembers.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
        return new Result(MemberDTOs);
    }

    @GetMapping("/v2/members")
    @ApiOperation(value="전체 직원 조회", notes="전체 직원 정보를 조회하고 반환")
    public Result membersV2(@RequestBody @Valid PagingMemberRequestDTO requestDTO) {
        List<Member> members;

        if(requestDTO.getFirstResult() == null) {
            members = memberService.findMembers(requestDTO.getMaxResult());
        } else {
            members = memberService.findMembers(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        //엔티티 -> DTO 변환
        List<MemberDTO> MemberDTOs = members.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
        return new Result(MemberDTOs);
    }

    @GetMapping("/v3/members")
    @ApiOperation(value="전체 직원 조회", notes="전체 직원 정보를 조회하고 반환")
    public Result membersV3(@RequestParam("firstResult") Integer firstResult,
                            @RequestParam("maxResult") Integer maxResult) {
        List<Member> members;

        if(firstResult == null) {
            members = memberService.findMembers(maxResult);
        } else {
            members = memberService.findMembers(firstResult, maxResult);
        }

        //엔티티 -> DTO 변환
        List<MemberDTO> MemberDTOs = members.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
        return new Result(MemberDTOs);
    }

    @GetMapping("/v1/members/{memberId}")
    @ApiOperation(value="직원 조회", notes="직원 아이디를 이용하여 직원 정보 조회")
    public MemberDTO findMemberV1(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findMember(memberId);
        if(member == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        return new MemberDTO(member);
    }

    @PostMapping("/v1/members")
    @ApiOperation(value="직원 추가", notes="새로운 직원의 가입 및 정보 추가")
    public CreateMemberResponseDTO saveMemberV1(@RequestBody @Valid CreateMemberRequestDTO requestDTO) {
        Member member = Member.builder()
                .loginId(requestDTO.getLoginId())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .userName(requestDTO.getName())
                .phoneNumber(requestDTO.getPhoneNumber())
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .duties(new ArrayList<>())
                .build();
        Long memberId = memberService.join(member);

        return new CreateMemberResponseDTO(memberId);
    }

    @PatchMapping("/v1/members/{memberId}")
    @ApiOperation(value="직원 정보 수정", notes="직원의 정보를 수정 후 수정한 정보를 반환")
    public UpdateMemberResponseDTO updateMemberV1(@PathVariable("memberId") Long memberId,
                                                  @RequestBody @Valid UpdateMemberRequestDTO requestDTO) {
        Member member = memberService.findMember(memberId);
        if(member == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        if(!requestDTO.getName().isBlank()) {
            memberService.changeUserName(memberId, requestDTO.getName());
        }
        if(requestDTO.getPhoneNumber().matches("^(01[0-1|6-9])-?(\\d{3,4})-?(\\d{4})$")) {
            memberService.changePhoneNumber(memberId, requestDTO.getPhoneNumber());
        }
        if(requestDTO.getGradeType() != null) {
            memberService.changeGradeType(memberId, requestDTO.getGradeType());
        }
        if(requestDTO.getMemberStatus() != null) {
            memberService.changeMemberStatus(memberId, requestDTO.getMemberStatus());
        }

        return new UpdateMemberResponseDTO(memberService.findMember(memberId));
    }

    @DeleteMapping("/v1/members/{memberId}")
    @ApiOperation(value="직원 정보 삭제", notes="직원 정보를 삭제하고 아이디를 반환한다.")
    public DeleteMemberResponseDTO deleteMemberV1(@PathVariable("memberId") Long memberId,
                                                  @RequestBody @Valid DeleteMemberRequestDTO requestDTO) {
        Member member = memberService.findMember(memberId);
        if(member == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        if(!passwordEncoder.matches(requestDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        memberService.removeMember(memberId);
        return new DeleteMemberResponseDTO(member.getId());
    }
}
