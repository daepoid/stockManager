package daepoid.stockManager.repository;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface MenuRepository {

    //==생성 로직==//
    Long save(Menu menu);

    //==조회 로직==//
    Menu findById(Long menuId);
    List<Menu> findAll();

    List<Menu> findByName(String name);
    List<Menu> findByRecipe(Recipe recipe);

    Integer getNumberOfFoodByRecipeId(Long menuId, Long recipeId);

    //==수정 로직==//
    void changeName(Long menuId, String name);
    void changeFoods(Long menuId, Set<Recipe> foods);
    void changeNumberOfFood(Long menuId, Map<Long, Integer> numberOfFood);
    void changeFoodInfo(Long menuId, Set<Recipe> foods, Map<Long, Integer> numberOfFood);


    void addFood(Long menuId, Recipe food, Integer numberOfFood);

    void addOrderCount(Long menuId, int newOrderCount);

    void cancelOrderCount(Long menuId, int cancelOrderCount);

    //==삭제 로직==//
    void removeFood(Long menuId, Recipe food);
}
