package daepoid.stockManager.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    private Long customerId;

    private Long menuId;

    private Integer count;

}
