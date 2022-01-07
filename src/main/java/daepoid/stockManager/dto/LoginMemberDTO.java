package daepoid.stockManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class LoginMemberDTO {

    @NotNull
    private String loginId;

    @NotNull
    private String Password;
}
