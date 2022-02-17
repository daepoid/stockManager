package daepoid.stockManager.api.dto.recipe;

import lombok.Data;

@Data
public class DeleteRecipeResponseDTO {

    private Long recipeId;

    public DeleteRecipeResponseDTO(Long recipeId) {
        this.recipeId = recipeId;
    }
}
