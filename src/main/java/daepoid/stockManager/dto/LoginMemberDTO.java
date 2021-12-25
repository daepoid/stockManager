package daepoid.stockManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class LoginMemberDTO {

//    로그인 아이디를 전화번호로 사용하는 방식
//    private String phoneNumber;

    // 로그인을 아이디를 이용해서 진행하는 방식
    @NotNull
    private String loginId;

    @NotNull
    private String Password;
}
