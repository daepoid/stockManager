package daepoid.stockManager.dto;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeDTO {

    private String recipeNumber;

    private String name;

    private Integer price;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String notes;
}
