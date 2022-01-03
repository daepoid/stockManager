package daepoid.stockManager.dto;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeDTO {

    private Long id;

    private Long recipeNumber;

    private String name;

    private Integer price;

    private Double unitPrice;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    // Ingredient
    private List<Ingredient> ingredients = new ArrayList<>();

    private String notes;

    public CreateRecipeDTO(Recipe recipe) {

    }
}
