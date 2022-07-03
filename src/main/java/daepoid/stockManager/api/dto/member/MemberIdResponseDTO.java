package daepoid.stockManager.api.dto.member;

import lombok.Data;

@Data
public class MemberIdResponseDTO {

    private Long id;

    public MemberIdResponseDTO(Long id) {
        this.id = id;
    }
}
