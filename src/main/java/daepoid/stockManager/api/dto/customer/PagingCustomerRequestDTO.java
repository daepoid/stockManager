package daepoid.stockManager.api.dto.customer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PagingCustomerRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
