package daepoid.stockManager.api.dto.recipe;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Menu;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateRecipeResponseDTO {

    private Long recipeId;

    private String recipeNumber;

    // 레시피 이름
    private String name;

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
    @NotNull
    private double cost = 0.0;

    @NotNull
    private double netIncome = 0.0;

    // 레시피 방법 또는 주의점
    @Lob
    private String notes = "";

    public UpdateRecipeResponseDTO(Recipe recipe) {
        this.recipeId = recipe.getId();
        this.recipeNumber = recipe.getRecipeNumber();
        this.name = recipe.getName();
        this.price = recipe.getPrice();
        this.weight = recipe.getWeight();
        this.dishType = recipe.getDishType();

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
