package daepoid.stockManager.api.dto.recipe;

import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Menu;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateRecipeRequestDTO {

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

    // 레시피 방법 또는 주의점
    private String notes = "";

}
