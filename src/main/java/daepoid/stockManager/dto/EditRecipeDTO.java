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
public class EditRecipeDTO {

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

    public EditRecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.price = recipe.getPrice();
        this.unitPrice = recipe.getUnitPrice();
        this.weight = recipe.getWeight();
        this.dishType = recipe.getDishType();
        this.notes = recipe.getNotes();
        this.ingredients = recipe.getIngredients();
    }
}
