package daepoid.stockManager.api.dto.ingredient;

import lombok.Data;

@Data
public class IngredientIdResponseDTO {

    private Long ingredientId;

    public IngredientIdResponseDTO(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
