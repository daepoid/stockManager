package daepoid.stockManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMyPasswordDTO {

    private String password;

    private String newPassword;

    private String newPasswordConfirm;
}
