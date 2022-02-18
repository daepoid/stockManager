package daepoid.stockManager.controller.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginMemberDTO {

    @NotBlank
    private String loginId;

    @NotBlank
    private String Password;
}
