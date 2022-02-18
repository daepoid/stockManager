package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.MenuStatus;
import daepoid.stockManager.domain.recipe.Recipe;

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
class RecipeServiceTest {

    @Autowired
    RecipeService recipeService;

    @Autowired
    EntityManager em;

    private Item createItem() {
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        return Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
    }

    private Ingredient createIngredient(Item item) {
        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        return Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();
    }

    private Menu createMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 789;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 789;
        MenuStatus menuMenuStatus = MenuStatus.ORDERABLE;

        return Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .menuStatus(menuMenuStatus)
                .build();
    }

    @Test
    void saveRecipe() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        int cost = 123;
        int netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "recipe notes";

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipe.getId()).isEqualTo(recipeId);
    }

    @Test
    void findRecipe() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        int cost = 123;
        int netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "recipe notes";

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipe(recipeId)).isEqualTo(recipe);
    }

    @Test
    void findRecipes() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        int cost = 123;
        int netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "recipe notes";

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipes().contains(recipe)).isTrue();
        assertThat(recipeService.findRecipes().stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findRecipeByRecipeNumber() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipeByRecipeNumber(recipeNumber)).isEqualTo(recipe);
        assertThat(recipeService.findRecipeByRecipeNumber(recipeNumber).getId()).isEqualTo(recipeId);
    }

    @Test
    void findRecipeByName() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipeByName(name)).isEqualTo(recipe);
        assertThat(recipeService.findRecipeByName(name).getId()).isEqualTo(recipeId);
    }

    @Test
    void findRecipesByPrice() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipesByPrice(price).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByPrice(price).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
        assertThat(recipeService.findRecipesByPrice(price).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isTrue();
    }

    @Test
    void findRecipesByWeight() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipesByWeight(weight).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByWeight(weight).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findRecipesByIngredient() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipesByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByIngredient(ingredient).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
        assertThat(recipeService.findRecipesByIngredient(ingredient).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isTrue();
    }

    @Test
    void findRecipesByDishType() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipesByDishType(dishType).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByDishType(dishType).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
        assertThat(recipeService.findRecipesByDishType(dishType).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isTrue();
    }

    @Test
    void changeRecipeNumber() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        String newRecipeNumber = "456";
        recipeService.changeRecipeNumber(recipeId, newRecipeNumber);

        assertThat(recipe.getRecipeNumber()).isEqualTo(newRecipeNumber);
        assertThat(recipeService.findRecipe(recipeId).getRecipeNumber()).isEqualTo(newRecipeNumber);
        assertThat(recipeService.findRecipeByRecipeNumber(recipeNumber)).isNull();
        assertThat(recipeService.findRecipeByRecipeNumber(newRecipeNumber).getId()).isEqualTo(recipeId);
    }

    @Test
    void changeRecipeName() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        String newName = "new recipe name";
        recipeService.changeRecipeName(recipeId, newName);

        assertThat(recipe.getName()).isEqualTo(newName);
        assertThat(recipeService.findRecipe(recipeId).getName()).isEqualTo(newName);
        assertThat(recipeService.findRecipeByName(newName).getId()).isEqualTo(recipeId);
        assertThat(recipeService.findRecipeByName(name)).isNull();
    }

    @Test
    void changePrice() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        int newPrice = 456;
        recipeService.changePrice(recipeId, newPrice);

        assertThat(recipe.getPrice()).isEqualTo(newPrice);
        assertThat(recipeService.findRecipe(recipeId).getPrice()).isEqualTo(newPrice);

        assertThat(recipeService.findRecipesByPrice(newPrice).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByPrice(newPrice).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeService.findRecipesByPrice(price).contains(recipe)).isFalse();
        assertThat(recipeService.findRecipesByPrice(price).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNull();

        assertThat(recipeService.findRecipes().stream()
                .anyMatch(r -> r.getPrice() == newPrice && r.getId().equals(recipeId))).isTrue();
        assertThat(recipeService.findRecipesByPrice(newPrice).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isTrue();
    }

    @Test
    void changeWeight() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        double newWeight = 456;
        recipeService.changeWeight(recipeId, newWeight);

        assertThat(recipe.getWeight()).isEqualTo(newWeight);
        assertThat(recipeService.findRecipe(recipeId).getWeight()).isEqualTo(newWeight);
        assertThat(recipeService.findRecipesByWeight(newWeight).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByWeight(newWeight).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeDishType() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        DishType newDishType = DishType.DESSERT;
        recipeService.changeDishType(recipeId, newDishType);

        assertThat(recipe.getDishType()).isEqualTo(newDishType);

        assertThat(recipeService.findRecipe(recipeId).getDishType()).isEqualTo(newDishType);
        assertThat(recipeService.findRecipesByDishType(dishType).contains(recipe)).isFalse();
        assertThat(recipeService.findRecipesByDishType(dishType).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNull();
        assertThat(recipeService.findRecipesByDishType(dishType).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isFalse();

        assertThat(recipeService.findRecipesByDishType(newDishType).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByDishType(newDishType).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
        assertThat(recipeService.findRecipesByDishType(newDishType).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isTrue();
    }

    @Test
    void changeIngredient() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

//        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        List<Ingredient> newIngredients = new ArrayList<>();
        newIngredients.add(ingredient);

        assertThat(recipeService.findRecipe(recipeId).getIngredients().contains(ingredient)).isFalse();
        assertThat(recipeService.findRecipe(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNull();
        assertThat(recipeService.findRecipesByIngredient(ingredient).stream()
                .anyMatch(r -> r.getId().equals(recipeId))).isFalse();

        recipeService.changeIngredients(recipeId, newIngredients);
        assertThat(recipe.getIngredients().stream()
                .anyMatch(i -> i.getId().equals(ingredient.getId()))).isTrue();
        assertThat(recipeService.findRecipe(recipeId).getIngredients().contains(ingredient)).isTrue();
        assertThat(recipeService.findRecipe(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void addIngredient() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

//        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipe.getIngredients().stream()
                .anyMatch(i -> i.getId().equals(ingredient.getId()))).isFalse();

        recipeService.addIngredient(recipeId, ingredient);

        assertThat(recipeService.findRecipe(recipeId).getIngredients().contains(ingredient)).isTrue();
        assertThat(recipeService.findRecipe(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeService.findRecipesByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByIngredient(ingredient).stream()
                .filter(r -> r.hasIngredient(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void removeIngredient() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipe.getIngredients().contains(ingredient)).isTrue();

        assertThat(recipeService.findRecipe(recipeId).getIngredients().contains(ingredient)).isTrue();
        assertThat(recipeService.findRecipe(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeService.findRecipesByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeService.findRecipesByIngredient(ingredient).stream()
                .filter(r -> r.hasIngredient(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        recipeService.removeIngredient(recipeId, ingredient);

        assertThat(recipe.getIngredients().contains(ingredient)).isFalse();

        assertThat(recipeService.findRecipe(recipeId).getIngredients().contains(ingredient)).isFalse();
        assertThat(recipeService.findRecipe(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNull();

        assertThat(recipeService.findRecipesByIngredient(ingredient).contains(recipe)).isFalse();
        assertThat(recipeService.findRecipesByIngredient(ingredient).stream()
                .filter(r -> r.hasIngredient(ingredient.getId()))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void changeCost() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        // changeCost()를 호출해도 getCost()를 통해 자동으로 updateCost()가 수행된다.
        // 그로인해 정상적인 계산값이 저장되어 호출된다.
        double newCost = 456456;
        recipeService.changeCost(recipeId, newCost);

        double realCost = 456 * 45.6;
        assertThat(recipe.getCost()).isNotEqualTo(newCost);
        assertThat(recipe.getCost()).isEqualTo(realCost);
        assertThat(recipeService.findRecipe(recipeId).getCost()).isEqualTo(realCost);
    }

    @Test
    void updateCost() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        // changeCost()를 호출해도 getCost()를 통해 자동으로 updateCost()가 수행된다.
        // 그로인해 정상적인 계산값이 저장되어 호출된다.
        recipeService.updateCost(recipeId);

        double realCost = 456 * 45.6;
        assertThat(recipe.getCost()).isEqualTo(realCost);
        assertThat(recipeService.findRecipe(recipeId).getCost()).isEqualTo(realCost);
    }

    @Test
    void changeNotes() {
        Item item = createItem();
        em.persist(item);

        Ingredient ingredient = createIngredient(item);
        em.persist(ingredient);

        Menu menu = createMenu();
        em.persist(menu);

        String recipeNumber = "123";
        String name = "name";
        int price = 123;
        int weight = 123;
        DishType dishType = DishType.BOWL;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();
        String notes = "notes";

        ingredients.add(ingredient);
        menus.add(menu);

        Recipe recipe = Recipe.builder()
                .recipeNumber(recipeNumber)
                .name(name)
                .price(price)
                .weight(weight)
                .dishType(dishType)
                .ingredients(ingredients)
                .cost(cost)
                .netIncome(netIncome)
                .menus(menus)
                .notes(notes)
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        String newNotes = "123123123";
        recipeService.changeNotes(recipeId, newNotes);

        assertThat(recipe.getNotes()).isEqualTo(newNotes);
        assertThat(recipeService.findRecipe(recipeId).getNotes()).isEqualTo(newNotes);
    }
}