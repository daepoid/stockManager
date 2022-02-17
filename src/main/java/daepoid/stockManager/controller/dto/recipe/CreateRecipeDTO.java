package daepoid.stockManager.controller.dto.recipe;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeDTO {

    @NotBlank
    private String recipeNumber;

    @NotBlank
    private String name;

    @NotNull
    private int price;

    private double weight;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String notes;
}
