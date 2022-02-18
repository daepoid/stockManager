package daepoid.stockManager.service;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.search.MenuSearch;
import daepoid.stockManager.domain.recipe.MenuStatus;
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

    public List<Menu> findByOverSalesCount(int salesCount) {
        return menuRepository.findByOverSalesCount(salesCount);
    }

    public List<Menu> findByUnderSalesCount(int salesCount) {
        return menuRepository.findByUnderSalesCount(salesCount);
    }

    public List<Menu> findByMenuStatus(MenuStatus menuStatus) {
        return menuRepository.findByMenuStatus(menuStatus);
    }

    public List<Menu> findByMenuSearch(MenuSearch menuSearch) {
        return menuRepository.findByMenuSearch(menuSearch);
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
    public void changeNumberOfFoods(Long menuId, Map<Long, Integer> numberOfFoods) {
        menuRepository.changeNumberOfFoods(menuId, numberOfFoods);
    }

    @Transactional
    public void changeFoodInfo(Long menuId, Set<Recipe> foods, Map<Long, Integer> numberOfFoods) {
        menuRepository.changeFoodInfo(menuId, foods, numberOfFoods);
    }

    @Transactional
    public void changeSalesCount(Long menuId, int salesCount) {
        menuRepository.changeSalesCount(menuId, salesCount);
    }

    @Transactional
    public void changeMenuStatus(Long menuId, MenuStatus menuStatus) {
        menuRepository.changeMenuStatus(menuId, menuStatus);
    }

    @Transactional
    public void addFood(Long menuId, Recipe food, Integer numberOfFoods) {
        menuRepository.addFood(menuId, food, numberOfFoods);
    }

    @Transactional
    public void addSalesCount(Long menuId, int salesCount) {
        menuRepository.addSalesCount(menuId, salesCount);
    }

    @Transactional
    public void cancelSalesCount(Long menuId, int salesCount) {
        menuRepository.cancelSalesCount(menuId, salesCount);
    }

    //==삭제 로직==//
    @Transactional
    public void removeFood(Long menuId, Recipe food) {
        menuRepository.removeFood(menuId, food);
    }

    @Transactional
    public void removeMenu(Long menuId) {
        menuRepository.removeMenu(menuId);
    }

}
