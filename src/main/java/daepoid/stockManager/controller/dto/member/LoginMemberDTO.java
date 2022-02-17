package daepoid.stockManager.controller.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginMemberDTO {

    @NotBlank
    private String loginId;

    @NotBlank
    private String Password;
}
