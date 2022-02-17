package daepoid.stockManager.api.dto.ingredient;

import lombok.Data;

@Data
public class CreateIngredientResponseDTO {

    private Long ingredientId;

    public CreateIngredientResponseDTO(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
