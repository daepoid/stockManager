package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Menu;
import daepoid.stockManager.domain.food.FoodStatus;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaFoodRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaFoodRepository menuRepository;

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
        String imgUrl = "";

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
                .imgUrl(imgUrl)
                .build();
    }

    @Test
    void save() {
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

        Long menuId = menuRepository.save(menu);

        assertThat(menuId).isEqualTo(menu.getId());
    }

    @Test
    void findById() {
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

        Long menuId = menuRepository.save(menu);

        assertThat(menu.getId()).isEqualTo(menuId);
        assertThat(menuRepository.findById(menuId)).isEqualTo(menu);
        assertThat(menuRepository.findById(menuId).getId()).isEqualTo(menu.getId());
    }

    @Test
    void findAll() {
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

        Long menuId = menuRepository.save(menu);
        
        assertThat(menuRepository.findAll().contains(menu)).isTrue();
        assertThat(menuRepository.findAll().stream()
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

        Long menuId = menuRepository.save(menu);

        assertThat(menu.getName()).isEqualTo(name);
        assertThat(menuRepository.findByName(name).contains(menu)).isTrue();
        assertThat(menuRepository.findByName(name).stream()
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

        Long menuId = menuRepository.save(menu);

        assertThat(menu.getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(menuRepository.findByRecipe(recipe).contains(menu)).isTrue();
        assertThat(menuRepository.findByRecipe(recipe).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOverSalesCount() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 123;
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

        Long menuId = menuRepository.save(menu);

        assertThat(menuRepository.findByOverSalesCount(salesCount - 1).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(menuRepository.findByOverSalesCount(salesCount).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(menuRepository.findByOverSalesCount(salesCount + 1).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void findByUnderSalesCount() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 123;
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

        Long menuId = menuRepository.save(menu);

        assertThat(menuRepository.findByUnderSalesCount(salesCount - 1).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNull();

        assertThat(menuRepository.findByUnderSalesCount(salesCount).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(menuRepository.findByUnderSalesCount(salesCount + 1).stream()
                .filter(m -> m.getId().equals(menuId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByMenuStatus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        String name = "name";
        Set<Recipe> foods = new HashSet<>();
        int price = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime addedDate = LocalDateTime.now();
        int salesCount = 0;
        FoodStatus foodStatus = FoodStatus.ORDERABLE;
        FoodStatus otherFoodStatus = FoodStatus.CLOSED;

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

        Long menuId = menuRepository.save(menu);

        assertThat(menuRepository.findByMenuStatus(foodStatus).stream()
                .anyMatch(m -> m.getId().equals(menuId))).isTrue();
        assertThat(menuRepository.findByMenuStatus(otherFoodStatus).stream()
                .anyMatch(m -> m.getId().equals(menuId))).isFalse();
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

        Long menuId = menuRepository.save(menu);

        assertThat(menuRepository.getNumberOfFoodByRecipeId(menuId, recipe.getId())).isEqualTo(numberOfFood);
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
        String imgUrl = "";

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
                .imgUrl(imgUrl)
                .build();

        Long menuId = menuRepository.save(menu);

        String newName = "new name";
        menuRepository.changeName(menuId, newName);

        assertThat(menu.getName()).isEqualTo(newName);
        assertThat(menuRepository.findById(menuId).getName()).isEqualTo(newName);
        assertThat(menuRepository.findByName(newName).stream()
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

        Long menuId = menuRepository.save(menu);

        assertThat(menu.getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(menuRepository.findById(menuId).getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();

        Set<Recipe> newFoods = new HashSet<>();
        newFoods.add(recipe);

        menuRepository.changeFoods(menuId, newFoods);

        assertThat(menu.getFoods().stream()
                .filter(r -> r.getId().equals(recipe.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(menuRepository.findById(menuId).getFoods().stream()
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

        Long menuId = menuRepository.save(menu);

        Map<Long, Integer> newNumberOfFoods = new HashMap<>();
        int newNumberOfFood = 456;
        newNumberOfFoods.put(recipe.getId(), newNumberOfFood);
        menuRepository.changeNumberOfFoods(menuId, newNumberOfFoods);

        assertThat(menuRepository.findById(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
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

        Long menuId = menuRepository.save(menu);

        Set<Recipe> newFoods = new HashSet<>();
        Map<Long, Integer> newNumberOfFoods = new HashMap<>();
        int newNumberOfFood = 456;
        newFoods.add(recipe);
        newNumberOfFoods.put(recipe.getId(), newNumberOfFood);
        menuRepository.changeFoodInfo(menuId, newFoods, newNumberOfFoods);

        assertThat(menu.getNumberOfFoods().containsKey(recipe.getId())).isTrue();
        assertThat(menuRepository.findById(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
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

        Long menuId = menuRepository.save(menu);

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
        menuRepository.addFood(menuId, recipe, newNumberOfFood);
        assertThat(menu.getFoods().contains(recipe)).isTrue();
        assertThat(menu.getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
        assertThat(menuRepository.findById(menuId).getNumberOfFoods().get(recipe.getId())).isEqualTo(newNumberOfFood);
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

        Long menuId = menuRepository.save(menu);

        menuRepository.addSalesCount(menuId, 123);
        assertThat(menu.getSalesCount()).isEqualTo(123);
        assertThat(menuRepository.findById(menuId).getSalesCount()).isEqualTo(123);
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

        Long menuId = menuRepository.save(menu);

        menuRepository.cancelSalesCount(menuId, 123);
        assertThat(menu.getSalesCount()).isEqualTo(-123);
        assertThat(menuRepository.findById(menuId).getSalesCount()).isEqualTo(-123);
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

        Long menuId = menuRepository.save(menu);

        menuRepository.remove(menu.getId(), recipe);
        assertThat(menu.getFoods().contains(recipe)).isFalse();
        assertThat(menu.getFoods().stream()
                .anyMatch(r -> r.getId().equals(recipe.getId()))).isFalse();
    }
}