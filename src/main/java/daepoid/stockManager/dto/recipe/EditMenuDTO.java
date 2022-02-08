package daepoid.stockManager.dto.recipe;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMenuDTO {

    @NotNull
    private Long menuId;

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @NotNull
    private int orderCount;

    @NotNull
    private LocalDateTime addedDate;

    private Map<Recipe, Integer> foodMap = new HashMap<>();

//    private Set<Recipe> foods = new HashSet<>();
//
//    private Map<Long, Integer> numberOfFood = new HashMap<>();

    public EditMenuDTO(Menu menu) {
        this.menuId = menu.getId();
        this.name = menu.getName();

        this.orderCount = menu.getSalesCount();
        this.addedDate = menu.getAddedDate();

        double sum = 0.0;

        Map<Recipe, Integer> food_info = new HashMap<>();
        Set<Recipe> foods = menu.getFoods();
        for (Recipe food : foods) {
            Integer numberOfFoodInt = menu.getNumberOfFood().get(food.getId());
            if(numberOfFoodInt != null) {
                food_info.put(food, numberOfFoodInt);
                sum += numberOfFoodInt * food.getPrice();
            }
        }
        foodMap = food_info;

//        this.foods = menu.getFoods();
//        this.numberOfFood = menu.getNumberOfFood();
        this.price = sum;
    }

}
