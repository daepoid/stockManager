package daepoid.stockManager.api.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateMemberRequestDTO {

    @NotBlank
    private String loginId;

    // 비민번호, 로그인 시 사용
    @NotBlank
    private String password;

    // 이름
    @NotBlank
    private String userName;

    // 전화번호 '01012341234' 형태로 저장됨
    @NotBlank
    private String phoneNumber;
}
