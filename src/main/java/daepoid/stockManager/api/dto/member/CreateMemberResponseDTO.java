package daepoid.stockManager.api.dto.member;

import lombok.Data;

@Data
public class CreateMemberResponseDTO {

    private Long id;

    public CreateMemberResponseDTO(Long id) {
        this.id = id;
    }
}
