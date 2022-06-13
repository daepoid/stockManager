package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Menu;
import daepoid.stockManager.domain.food.FoodStatus;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional 
class MenuServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MenuService menuService;

    private Recipe createRecipe() {
        String recipeRecipeNumber = "123";
        String recipeName = "name";
        int recipePrice = 123;
        int recipeWeight = 123;
        DishType recipeDishType = DishType.BOWL;
        List<Ingredient> recipeIngredients = new ArrayList<>();
        int recipeCost = 123;
        int recipeNetIncome = 123;
        Set<Menu> recipeMenus = new HashSet<>();
        String recipeNotes = "recipe notes";
        String recipeImgUrl = "";

        return Recipe.builder()
                .recipeNumber(recipeRecipeNumber)
                .name(recipeName)
                .price(recipePrice)
                .weight(recipeWeight)
                .dishType(recipeDishType)
                .ingredients(recipeIngredients)
                .cost(recipeCost)
                .netIncome(recipeNetIncome)
                .menus(recipeMenus)
                .notes(recipeNotes)
                .imgUrl(recipeImgUrl)
                .build();
    }

    @Test
    void saveMenu() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuId).isEqualTo(menu.getId());
    }

    @Test
    void findMenu() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menu.getId()).isEqualTo(menuId);
        assertThat(menuService.findMenu(menuId)).isEqualTo(menu);
        assertThat(menuService.findMenu(menuId).getId()).isEqualTo(menuId);
    }

    @Test
    void findMenus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;
        String menuImgUrl = "";

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .imgUrl(menuImgUrl)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuService.findMenus().contains(menu)).isTrue();
        assertThat(menuService.findMenus().stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByName() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;
        String menuImgUrl = "";

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .imgUrl(menuImgUrl)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menu.getName()).isEqualTo(name);
        assertThat(menuService.findByName(name).contains(menu)).isTrue();
        assertThat(menuService.findByName(name).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByRecipe() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;
        String menuImgUrl = "";

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .imgUrl(menuImgUrl)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menu.getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(menuService.findByRecipe(recipe).contains(menu)).isTrue();
        assertThat(menuService.findByRecipe(recipe).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void getNumberOfFoodByRecipeId() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menuService.getNumberOfFoodByRecipeId(menuId, recipe.getId())).isEqualTo(numberOfFood);
    }

    @Test
    void changeName() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;
        String menuImgUrl = "";

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .imgUrl(menuImgUrl)
                .build();

        Long menuId = menuService.saveMenu(menu);

        String newName = "new name";
        menuService.changeName(menuId, newName);

        assertThat(menu.getName()).isEqualTo(newName);
        assertThat(menuService.findMenu(menuId).getName()).isEqualTo(newName);
        assertThat(menuService.findByName(newName).stream()
                .filter(m -> m.getId().equals(menuId))
                .findAny().orElse(null)).isEqualTo(menu);
    }

    @Test
    void changeFoods() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        assertThat(menu.getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(menuService.findMenu(menuId).getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();

        Set<Recipe> newFoods = new HashSet<>();
        newFoods.add(recipe);

        menuService.changeFoods(menuId, newFoods);

        assertThat(menu.getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(menuService.findMenu(menuId).getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeNumberOfFoods() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        Map<Long, Integer> newNumberOfFoods = new HashMap<>();
        int newNumberOfFood = 456;
        newNumberOfFoods.put(recipe.getId(), newNumberOfFood);
        menuService.changeNumberOfFoods(menuId, newNumberOfFoods);

        assertThat(menuService.findMenu(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
    }

    @Test
    void changeFoodInfo() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        Set<Recipe> newFoods = new HashSet<>();
        Map<Long, Integer> newNumberOfFoods = new HashMap<>();
        int newNumberOfFood = 456;
        newFoods.add(recipe);
        newNumberOfFoods.put(recipe.getId(), newNumberOfFood);
        menuService.changeFoodInfo(menuId, newFoods, newNumberOfFoods);

        assertThat(menuService.findMenu(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
    }

    @Test
    void addFood() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        String newRecipeRecipeNumber = "456";
        String newRecipeName = "456";
        int newPrice = 456;
        int newWeight = 456;
        DishType newRecipeDishType = DishType.MAIN;
        List<Ingredient> newRecipeIngredients = new ArrayList<>();
        double newRecipeCost = 45.6;
        double newRecipeNetIncome = 45.6;
        Set<Menu> newRecipeMenus = new HashSet<>();
        String newRecipeNotes = "new recipe notes";

        Recipe newRecipe = Recipe.builder()
                .recipeNumber(newRecipeRecipeNumber)
                .name(newRecipeName)
                .price(newPrice)
                .weight(newWeight)
                .dishType(newRecipeDishType)
                .ingredients(newRecipeIngredients)
                .cost(newRecipeCost)
                .netIncome(newRecipeNetIncome)
                .menus(newRecipeMenus)
                .notes(newRecipeNotes)
                .build();
        em.persist(newRecipe);

        int newNumberOfFood = 456;
        menuService.addFood(menuId, recipe, newNumberOfFood);

        assertThat(menu.getFoods().contains(recipe)).isTrue();
        assertThat(menu.getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
        assertThat(menuService.findMenu(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
    }

    @Test
    void addSalesCount() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        int newSalesCount = 456;
        menuService.addSalesCount(menuId, newSalesCount);

        assertThat(menu.getSalesCount()).isEqualTo(salesCount + newSalesCount);
        assertThat(menuService.findMenu(menuId).getSalesCount()).isEqualTo(salesCount + newSalesCount);
    }

    @Test
    void cancelSalesCount() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .build();

        Long menuId = menuService.saveMenu(menu);

        int newSalesCount = 456;
        menuService.cancelSalesCount(menuId, newSalesCount);

        assertThat(menu.getSalesCount()).isEqualTo(salesCount - newSalesCount);
        assertThat(menuService.findMenu(menuId).getSalesCount()).isEqualTo(salesCount - newSalesCount);
    }

    @Test
    void removeFood() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;
        String menuImgUrl = "";

        foods.add(recipe);

        int numberOfFood = 123;
        numberOfFoods.put(recipe.getId(), numberOfFood);

        Menu menu = Menu.builder()
                .name(name)
                .foods(foods)
                .price(price)
                .numberOfFoods(numberOfFoods)
                .addedDate(addedDate)
                .salesCount(salesCount)
                .menuStatus(foodStatus)
                .imgUrl(menuImgUrl)
                .build();

        Long menuId = menuService.saveMenu(menu);

        int newNumberOfFood = 456;
        menuService.addFood(menuId, recipe, newNumberOfFood);

        assertThat(menu.getFoods().contains(recipe)).isTrue();
        assertThat(menu.getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
        assertThat(menuService.findMenu(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);

        menuService.removeFood(menuId, recipe);

        assertThat(menuService.findByRecipe(recipe).size()).isEqualTo(0);
        assertThat(menu.getFoods().contains(recipe)).isFalse();
        assertThat(menu.getFoods().stream()
                .anyMatch(r -> r.getId().equals(recipe.getId()))).isFalse();
    }
}