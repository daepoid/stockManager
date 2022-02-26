package daepoid.stockManager.api.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingCustomerRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
