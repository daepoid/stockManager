package daepoid.stockManager.repository;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.MenuStatus;
import daepoid.stockManager.domain.recipe.Recipe;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MenuRepository {

    //==생성 로직==//
    Long save(Menu menu);

    //==조회 로직==//
    Menu findById(Long menuId);
    List<Menu> findAll();

    List<Menu> findByName(String name);
    List<Menu> findByRecipe(Recipe recipe);
    List<Menu> findByOverSalesCount(int salesCount);
    List<Menu> findByUnderSalesCount(int salesCount);
    List<Menu> findByMenuStatus(MenuStatus menuStatus);

    Integer getNumberOfFoodByRecipeId(Long menuId, Long recipeId);

    //==수정 로직==//
    void changeName(Long menuId, String name);
    void changeFoods(Long menuId, Set<Recipe> foods);
    void changeNumberOfFoods(Long menuId, Map<Long, Integer> numberOfFoods);
    void changeFoodInfo(Long menuId, Set<Recipe> foods, Map<Long, Integer> numberOfFoods);
    void changeSalesCount(Long menuId, int salesCount);
    void changeMenuStatus(Long menuId, MenuStatus menuStatus);

    void addFood(Long menuId, Recipe food, Integer numberOfFoods);

    void addSalesCount(Long menuId, int salesCount);
    void cancelSalesCount(Long menuId, int salesCount);

    //==삭제 로직==//
    void removeFood(Long menuId, Recipe food);
    void removeMenu(Long menuId);
}
