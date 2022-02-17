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

//    @Data
//    @AllArgsConstructor
//    static class Result<T> {
//        private T data;
//    }

    /**
     * 조회 V2: 응답 값으로 엔티티가 아닌 별도의 DTO를 반환한다.
     */
    @GetMapping("/api/v1/members")
    public Result membersV1() {

        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDTO> collect = findMembers.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @GetMapping("/api/v1/members/{memberId}")
    public MemberDTO findMemberV1(@PathVariable("memberId") Long memberId) {
        return new MemberDTO(memberService.findMember(memberId));
    }

    /**
     * 등록 V1: 요청 값으로 CreateMemberRequestDTO DTO를 파라미터로 받는다.
     * 이점:
     * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     *   - 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty 등등)
     *   - 실무에서는 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 모든 요청 요구사항을 담기는 어렵다.
     * - 엔티티가 변경되면 API 스펙이 변한다.
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponseDTO saveMemberV1(@RequestBody @Valid CreateMemberRequestDTO requestDTO) {

        Member member = Member.builder()
                .loginId(requestDTO.getLoginId())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .name(requestDTO.getName())
                .phoneNumber(requestDTO.getPhoneNumber())
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .duties(new ArrayList<>())
                .build();
        Long memberId = memberService.join(member);

        return new CreateMemberResponseDTO(memberId);
    }

    /**
     * 수정 API
     */
    @PutMapping("/api/v1/members/{memberId}")
    public UpdateMemberResponseDTO updateMemberV2(@PathVariable("memberId") Long memberId,
                                                  @RequestBody @Valid UpdateMemberRequestDTO requestDTO) {

        if(!requestDTO.getName().isBlank()) {
            memberService.changeName(memberId, requestDTO.getName());
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
        memberService.removeMember(member);
        return new DeleteMemberResponseDTO(memberId);
    }
}
