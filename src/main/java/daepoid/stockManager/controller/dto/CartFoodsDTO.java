package daepoid.stockManager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartFoodsDTO {

    private Long cartFoodId;
    private Long foodId;
    private String foodName;
    private Double foodPrice;
    private Integer count;
}
