package daepoid.stockManager.api.dto.recipe;

import lombok.Data;

@Data
public class CreateRecipeResponseDTO {

    private Long recipeId;

    public CreateRecipeResponseDTO(Long recipeId) {
        this.recipeId = recipeId;
    }
}
