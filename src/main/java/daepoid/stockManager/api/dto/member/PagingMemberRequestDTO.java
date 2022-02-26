package daepoid.stockManager.api.dto.member;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingMemberRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
