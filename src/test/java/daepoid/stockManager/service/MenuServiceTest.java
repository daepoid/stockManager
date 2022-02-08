package daepoid.stockManager.service;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaMenuRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional 
class MenuServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MenuService menuService;

    @Autowired
    JpaMenuRepository menuRepository;

    @Test
    void saveMenu() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuId).isEqualTo(menu.getId());
    }

    @Test
    void findMenu() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuService.findMenu(menuId)).isEqualTo(menu);
    }

    @Test
    void findMenus() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuService.findMenus().contains(menu)).isTrue();
    }

    @Test
    void findByName() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuService.findByName(name).contains(menu)).isTrue();
        assertThat(Objects.requireNonNull(menuService.findByName(name).stream()
                        .filter(m -> m.getName().equals(name))
                        .findFirst().orElse(null))
                .getId()).isEqualTo(menu.getId());
    }

    @Test
    void findByRecipe() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("name")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        foods.add(recipe);
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);
        assertThat(menuService.findByRecipe(recipe).contains(menu)).isTrue();
    }

    @Test
    void getNumberOfFoodByRecipeId() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("name")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        foods.add(recipe);
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuService.getNumberOfFoodByRecipeId(menuId, recipe.getId())).isEqualTo(123);
    }

    @Test
    void changeName() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);
        menuService.changeName(menuId, name + name);

        assertThat(menuService.findMenu(menuId).getName()).isEqualTo(name + name);
        assertThat(menuService.findByName(name + name).stream()
                .filter(m -> m.getId().equals(menuId))
                .findAny().orElse(null)).isEqualTo(menu);
    }

    @Test
    void changeFoods() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        Set<Recipe> newFoods = new HashSet<>();

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("name")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        newFoods.add(recipe);

        menuService.changeFoods(menuId, newFoods);

        assertThat(menuService.findMenu(menuId).getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                        .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeNumberOfFood() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        numberOfFood.put(menuId, 123);

        menuService.changeNumberOfFood(menuId, numberOfFood);
        assertThat(menuService.findMenu(menuId).getNumberOfFood().get(menuId)).isEqualTo(123);
    }

    @Test
    void changeFoodInfo() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        Set<Recipe> newFoods = new HashSet<>();
        Map<Long, Integer> newNumberOfFood = new HashMap<>();

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("name")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        newFoods.add(recipe);
        newNumberOfFood.put(recipe.getId(), 123);

        menuService.changeFoodInfo(menuId, newFoods, newNumberOfFood);

        assertThat(menuService.findMenu(menuId).getNumberOfFood().get(recipe.getId())).isEqualTo(123);
    }

    @Test
    void addFood() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .numberOfFood(numberOfFood)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("name")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        menuService.addFood(menuId, recipe, 123);
        assertThat(menuService.findMenu(menuId).getNumberOfFood().get(recipe.getId())).isEqualTo(123);
    }

    @Test
    void removeFood() {
        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFood(numberOfFood)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuService.saveMenu(menu);


        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("name")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        menuService.removeFood(menuId, recipe);
        assertThat(menuService.findByRecipe(recipe).size()).isEqualTo(0);
    }
}