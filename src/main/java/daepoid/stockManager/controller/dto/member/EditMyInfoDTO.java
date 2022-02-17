package daepoid.stockManager.controller.dto.member;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMyInfoDTO {

    @NotNull
    private Long id;

    // 로그인 아이디
    @NotBlank
    private String loginId;

    // 이름
    @NotBlank
    private String name;

    // 전화번호
    @NotBlank
    private String phoneNumber;

    // 비민번호, 개인정보 수정 시 확인용
    @NotBlank
    private String password;

    @NotNull
    private List<Duty> duties = new ArrayList<>();

    public EditMyInfoDTO(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.duties = member.getDuties();
    }
}
