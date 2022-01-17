package daepoid.stockManager.service;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final JpaMenuRepository menuRepository;

    //==생성 로직==//
    @Transactional
    public Long saveMenu(Menu menu) {
        menuRepository.save(menu);
        return menu.getId();
    }


    //==조회 로직==//
    public Menu findMenu(Long menuId) {
        return menuRepository.findById(menuId);
    }

    public List<Menu> findMenus() {
        return menuRepository.findAll();
    }

    public List<Menu> findByName(String name) {
        return menuRepository.findByName(name);
    }

    public List<Menu> findByRecipe(Recipe recipe) {
        return menuRepository.findByRecipe(recipe);
    }

    public Integer getNumberOfFoodByRecipeId(Long menuId, Long recipeId) {
        return menuRepository.getNumberOfFoodByRecipeId(menuId, recipeId);
    }

    //==수정 로직==//
    @Transactional
    public void changeName(Long menuId, String name) {
        menuRepository.changeName(menuId, name);
    }

    @Transactional
    public void changeFoods(Long menuId, Set<Recipe> foods) {
        menuRepository.changeFoods(menuId, foods);
    }

    @Transactional
    public void addFood(Long menuId, Recipe food, Integer numberOfFood) {
        menuRepository.addFood(menuId, food, numberOfFood);
    }

    @Transactional
    public void changeNumberOfFood(Long menuId, Map<Long, Integer> numberOfFood) {
        menuRepository.changeNumberOfFood(menuId, numberOfFood);
    }

    //==삭제 로직==//
    @Transactional
    public void removeFood(Long menuId, Recipe food) {
        menuRepository.removeFood(menuId, food);
    }

}
