package daepoid.stockManager.api.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteMemberRequestDTO {

    @NotBlank
    private String password;
}
