package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaMenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMenuRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaMenuRepository menuRepository;

    @Test
    void save() {
        String name = "menu";
        Set<Recipe> foods = new HashSet<>();
        int price = 1000;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(new HashSet<>())
                .price(price)
                .numberOfFood(new HashMap<>())
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);

        assertThat(menuId).isEqualTo(menu.getId());
    }

    @Test
    void findById() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(new HashSet<>())
                .price(price)
                .numberOfFood(new HashMap<>())
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);

        assertThat(menu.getId()).isEqualTo(menuId);
        assertThat(menuRepository.findById(menuId)).isEqualTo(menu);
    }

    @Test
    void findAll() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(new HashSet<>())
                .price(price)
                .numberOfFood(new HashMap<>())
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);
        
        assertThat(menuRepository.findAll().contains(menu)).isEqualTo(true);
    }

    @Test
    void findByName() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(new HashSet<>())
                .price(price)
                .numberOfFood(new HashMap<>())
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);

        assertThat(menuRepository.findByName(name).contains(menu)).isEqualTo(true);
    }

    @Test
    void findByRecipe() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .weight(123)
                .dishType(DishType.BOWL)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        Set<Recipe> foods = new HashSet<>();
        foods.add(recipe);

        Map<Long, Integer> numberOfFood = new HashMap<>();
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .foods(foods)
                .numberOfFood(numberOfFood)
                .build();

        Long menuId = menuRepository.save(menu);
        
        assertThat(menuRepository.findByRecipe(recipe).contains(menu)).isEqualTo(true);
    }

    @Test
    void getNumberOfFoodByRecipeId() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .weight(123)
                .dishType(DishType.BOWL)
                .cost(123)
                .netIncome(123)
                .build();

        Set<Recipe> foods = new HashSet<>();
        foods.add(recipe);

        Map<Long, Integer> numberOfFood = new HashMap<>();
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .foods(foods)
                .numberOfFood(numberOfFood)
                .build();

        Long menuId = menuRepository.save(menu);

        Integer numberOfFoodByRecipeId = menuRepository.getNumberOfFoodByRecipeId(menuId, recipe.getId());
        assertThat(numberOfFoodByRecipeId).isEqualTo(123);
    }

    @Test
    void changeName() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .foods(new HashSet<>())
                .price(price)
                .numberOfFood(new HashMap<>())
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);

        menuRepository.changeName(menuId, name + name);
        assertThat(menu.getName()).isEqualTo(name + name);
        assertThat(menuRepository.findById(menuId).getName()).isEqualTo(name + name);
        assertThat(menuRepository.findByName(name + name).contains(menu)).isEqualTo(true);
    }

    @Test
    void changeFoods() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .weight(123)
                .dishType(DishType.BOWL)
                .cost(123)
                .netIncome(123)
                .build();

        Set<Recipe> foods = new HashSet<>();
        foods.add(recipe);

        Map<Long, Integer> numberOfFood = new HashMap<>();
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .numberOfFood(numberOfFood)
                .build();

        Long menuId = menuRepository.save(menu);

        menuRepository.changeFoods(menuId, foods);
        assertThat(menu.getFoods().contains(recipe)).isEqualTo(true);
    }

    @Test
    void changeNumberOfFood() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .weight(123)
                .dishType(DishType.BOWL)
                .cost(123)
                .netIncome(123)
                .build();

        Set<Recipe> foods = new HashSet<>();
        foods.add(recipe);

        Map<Long, Integer> numberOfFood = new HashMap<>();
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .foods(foods)
                .build();

        Long menuId = menuRepository.save(menu);

        menuRepository.changeNumberOfFood(menuId, numberOfFood);
        assertThat(menu.getNumberOfFood().get(recipe.getId())).isEqualTo(numberOfFood.get(recipe.getId()));
    }

    @Test
    void addFood() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Set<Recipe> foods = new HashSet<>();
        Map<Long, Integer> numberOfFood = new HashMap<>();

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .foods(foods)
                .numberOfFood(numberOfFood)
                .build();

        Long menuId = menuRepository.save(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .weight(123)
                .dishType(DishType.BOWL)
                .cost(123)
                .netIncome(123)
                .build();

        menuRepository.addFood(menuId, recipe, 123);
        assertThat(menu.getFoods().contains(recipe)).isEqualTo(true);
        assertThat(menu.getNumberOfFood().get(recipe.getId())).isEqualTo(123);
    }

    @Test
    void addOrderCount() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);
        menuRepository.addSalesCount(menuId, 123);
        assertThat(menu.getSalesCount()).isEqualTo(123);
        assertThat(menuRepository.findById(menuId).getSalesCount()).isEqualTo(123);
    }

    @Test
    void cancelOrderCount() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .build();

        Long menuId = menuRepository.save(menu);
        menuRepository.cancelSalesCount(menuId, 123);
        assertThat(menu.getSalesCount()).isEqualTo(-123);
        assertThat(menuRepository.findById(menuId).getSalesCount()).isEqualTo(-123);
    }

    @Test
    void removeFood() {
        String name = "menu";
        int price = 1000;
        LocalDateTime addedDate = LocalDateTime.now();
        int orderCount = 0;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .weight(123)
                .dishType(DishType.BOWL)
                .cost(123)
                .netIncome(123)
                .build();

        Set<Recipe> foods = new HashSet<>();
        foods.add(recipe);

        Map<Long, Integer> numberOfFood = new HashMap<>();
        numberOfFood.put(recipe.getId(), 123);

        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .addedDate(addedDate)
                .salesCount(orderCount)
                .foods(foods)
                .numberOfFood(numberOfFood)
                .build();

        menuRepository.save(menu);

        menuRepository.removeFood(menu.getId(), recipe);
        assertThat(menu.getFoods().contains(recipe)).isEqualTo(false);
    }
}