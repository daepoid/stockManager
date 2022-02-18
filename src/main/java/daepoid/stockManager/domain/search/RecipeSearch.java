package daepoid.stockManager.domain.search;

import daepoid.stockManager.domain.recipe.DishType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearch {

    private String name;

    private DishType dishType;
}
