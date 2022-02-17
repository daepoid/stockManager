package daepoid.stockManager.controller.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long menuId;

    @NotNull
    private Integer count;
}
