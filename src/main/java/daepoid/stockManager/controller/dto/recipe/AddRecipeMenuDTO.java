package daepoid.stockManager.controller.dto.recipe;

import daepoid.stockManager.domain.recipe.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRecipeMenuDTO {

    @NotBlank
    private String menuName;

    @NotNull
    private Long recipeId;

    @NotNull
    private int numberOfFoods;

    public AddRecipeMenuDTO(Menu menu) {
        menuName = menu.getName();
    }
}
