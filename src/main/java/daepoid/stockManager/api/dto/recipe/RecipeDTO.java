package daepoid.stockManager.api.dto.recipe;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeDTO {

    private Long recipeId;

    // 레시피 번호
    private String recipeNumber;

    // 레시피 이름
    private String recipeName;

    // 판매 가격
    private int price;

    // 요리 무게
    private double weight;

    // 접시 유형
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    // 레시피 필요 재료
    private List<Long> ingredientIds = new ArrayList<>();

    // 레시피 생산 단가
    private double cost = 0.0;

    private double netIncome = 0.0;

    // 레시피 방법 또는 주의점
    @Lob
    private String notes;

    public RecipeDTO(Recipe recipe) {
        this.recipeId = recipe.getId();
        this.recipeNumber = recipe.getRecipeNumber();
        this.recipeName = recipe.getName();
        this.price = recipe.getPrice();
        this.weight = recipe.getWeight();
        this.dishType = recipe.getDishType();

//        this.ingredients = recipe.getIngredients();

        List<Long> ingredientIds = new ArrayList<>();
        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            ingredientIds.add(ingredient.getId());
        }
        this.ingredientIds = ingredientIds;

        this.cost = recipe.getCost();
        this.netIncome = recipe.getNetIncome();
        this.notes = recipe.getNotes();
    }
}
