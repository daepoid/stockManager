package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.member.*;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/api/v1/members")
    public Result membersV1() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDTO> MemberDTOs = findMembers.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
        return new Result(MemberDTOs);
    }

    @GetMapping("/api/v1/members/{memberId}")
    public MemberDTO findMemberV1(@PathVariable("memberId") Long memberId) {
        return new MemberDTO(memberService.findMember(memberId));
    }

    @PostMapping("/api/v1/members")
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

    @PutMapping("/api/v1/members/{memberId}")
    public UpdateMemberResponseDTO updateMemberV2(@PathVariable("memberId") Long memberId,
                                                  @RequestBody @Valid UpdateMemberRequestDTO requestDTO) {
        if(!requestDTO.getName().isBlank()) {
            memberService.changeUserName(memberId, requestDTO.getName());
        }
        if(requestDTO.getPhoneNumber().matches("^(01[0-1|6-9])-?(\\d{3,4})-?(\\d{4})$")) {
            memberService.changePhoneNumber(memberId, requestDTO.getPhoneNumber());
        }

        memberService.changeGradeType(memberId, requestDTO.getGradeType());
        memberService.changeMemberStatus(memberId, requestDTO.getMemberStatus());

        Member member = memberService.findMember(memberId);
        return new UpdateMemberResponseDTO(member);
    }

    @DeleteMapping("/api/v1/members/{memberId}")
    public DeleteMemberResponseDTO deleteMemberV1(@PathVariable("memberId") Long memberId,
                                                  @RequestBody @Valid DeleteMemberRequestDTO requestDTO) {
        Member member = memberService.findMember(memberId);
        if(!passwordEncoder.matches(requestDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        memberService.removeMember(memberId);
        return new DeleteMemberResponseDTO(memberId);
    }
}
