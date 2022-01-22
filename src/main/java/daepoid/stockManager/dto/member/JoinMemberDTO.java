package daepoid.stockManager.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinMemberDTO {

    // 로그인 아이디
    @NotBlank
    private String loginId;

    // 이름
    @NotBlank
    private String name;

    // 비민번호, 로그인 시 사용
    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    // 전화번호 '01012341234' 형태로 저장됨
    @NotBlank
    private String phoneNumber;

}
