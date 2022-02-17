package daepoid.stockManager.api.dto.cart;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class AddCartRequestDTO {

    private Long menuId;

    private Integer count;

    private Map<Long, Integer> numberOfMenus;
}
