package daepoid.stockManager.api.dto.ingredient;

import lombok.Data;

@Data
public class DeleteIngredientResponseDTO {

    private Long ingredientId;

    public DeleteIngredientResponseDTO(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
