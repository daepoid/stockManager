package daepoid.stockManager.domain.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearch {

    private String name;

    private DishType dishType;
}
