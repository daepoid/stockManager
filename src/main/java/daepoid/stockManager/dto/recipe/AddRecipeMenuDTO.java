package daepoid.stockManager.dto.recipe;

import daepoid.stockManager.domain.recipe.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRecipeMenuDTO {

    private String menuName;

    private String recipeName;

    @NotNull
    private Long recipeId;

    @NotNull
    private Integer numberOfFood;

    public AddRecipeMenuDTO(Menu menu) {
        menuName = menu.getName();
    }
}
