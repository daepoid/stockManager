package daepoid.stockManager.api.dto.member;

import lombok.Data;

@Data
public class DeleteMemberResponseDTO {

    private Long memberId;

    public DeleteMemberResponseDTO(Long memberId) {
        this.memberId = memberId;
    }
}
