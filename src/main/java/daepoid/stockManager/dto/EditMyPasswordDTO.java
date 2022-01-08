package daepoid.stockManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMyPasswordDTO {

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String newPasswordConfirm;
}
