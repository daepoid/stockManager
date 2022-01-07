package daepoid.stockManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinMemberDTO {

    // 로그인 아이디
    @NotNull
    private String loginId;

    // 이름
    @NotNull
    private String name;

    // 비민번호, 로그인 시 사용
    @NotNull
    private String password;

    @NotNull
    private String passwordCheck;

    // 전화번호 '01012341234' 형태로 저장됨
    @NotNull
    private String phoneNumber;

}
