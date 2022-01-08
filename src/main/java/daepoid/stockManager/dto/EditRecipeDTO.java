package daepoid.stockManager.dto;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditRecipeDTO {

    // IngredientController에서 사용하기 위해 생성
    private Long id;

    @NotBlank
    private String recipeNumber;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    private Double weight;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String notes;

    public EditRecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.recipeNumber = recipe.getRecipeNumber();
        this.name = recipe.getName();
        this.price = recipe.getPrice();
        this.weight = recipe.getWeight();
        this.dishType = recipe.getDishType();
        this.notes = recipe.getNotes();
    }
}
