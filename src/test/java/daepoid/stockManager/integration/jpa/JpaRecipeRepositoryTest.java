package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaRecipeRepository;
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
class JpaRecipeRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaRecipeRepository recipeRepository;

    private Item createItem() {
        String ItemName = "item";
        ItemType itemItemType = ItemType.BOTTLE;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.kg;
        int itemPackageCount = 123;
        List<Ingredient> itemIngredients = new ArrayList<>();

        return Item.builder()
                .name(ItemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(itemIngredients)
                .build();
    }

    private Ingredient createIngredient(Item item) {
        int ingredientQuantity = 456;
        UnitType ingredientUnitType = UnitType.g;
        double ingredientUnitPrice = 456;
        double ingredientLoss = 4.56;
        double ingredientCost = 456;
        return Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(ingredientQuantity)
                .unitType(ingredientUnitType)
                .unitPrice(ingredientUnitPrice)
                .loss(ingredientLoss)
                .cost(ingredientCost)
                .build();
    }

    private Menu createMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 789;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 789;
        return Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
    }

    @Test
    void save() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipe.getId()).isEqualTo(recipeId);
        assertThat(em.find(Recipe.class, recipeId)).isEqualTo(recipe);
    }

    @Test
    void findById() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findById(recipeId)).isEqualTo(recipe);
        assertThat(recipeRepository.findById(recipeId).getId()).isEqualTo(recipeId);
    }

    @Test
    void findAll() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findAll().contains(recipe)).isTrue();
        assertThat(recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByRecipeNumber() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findByRecipeNumber(recipeNumber)).isEqualTo(recipe);
        assertThat(recipeRepository.findByRecipeNumber(recipeNumber).getId()).isEqualTo(recipeId);
    }

    @Test
    void findByName() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findByName(name)).isEqualTo(recipe);
        assertThat(recipeRepository.findByName(name).getId()).isEqualTo(recipeId);
    }

    @Test
    void findByPrice() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findByPrice(price).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByPrice(price).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByWeight() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findByWeight(weight).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByWeight(weight).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByIngredient() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByIngredient(ingredient).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByDishType() {
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipeRepository.findByDishType(dishType).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByDishType(dishType).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
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

        Long recipeId = recipeRepository.save(recipe);

        String newRecipeNumber = "123123";
        recipeRepository.changeRecipeNumber(recipeId, newRecipeNumber);

        assertThat(recipe.getRecipeNumber()).isEqualTo(newRecipeNumber);
        assertThat(recipeRepository.findById(recipeId).getRecipeNumber()).isEqualTo(newRecipeNumber);
        assertThat(recipeRepository.findByRecipeNumber(recipeNumber)).isNull();
        assertThat(recipeRepository.findByRecipeNumber(newRecipeNumber).getId()).isEqualTo(recipeId);
    }

    @Test
    void changeName() {
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

        Long recipeId = recipeRepository.save(recipe);

        String newName = "new name";
        recipeRepository.changeName(recipeId, newName);

        assertThat(recipe.getName()).isEqualTo(newName);
        assertThat(recipeRepository.findById(recipeId).getName()).isEqualTo(newName);
        assertThat(recipeRepository.findByName(newName).getId()).isEqualTo(recipeId);
        assertThat(recipeRepository.findByName(name)).isNull();
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

        Long recipeId = recipeRepository.save(recipe);

        int newPrice = 456;
        recipeRepository.changePrice(recipeId, newPrice);

        assertThat(recipe.getPrice()).isEqualTo(newPrice);
        assertThat(recipeRepository.findById(recipeId).getPrice()).isEqualTo(newPrice);

        assertThat(recipeRepository.findByPrice(newPrice).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByPrice(newPrice).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeRepository.findByPrice(price).contains(recipe)).isFalse();
        assertThat(recipeRepository.findByPrice(price).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNull();
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

        Long recipeId = recipeRepository.save(recipe);

        int newWeight = 456;
        recipeRepository.changeWeight(recipeId, newWeight);

        assertThat(recipe.getWeight()).isEqualTo(newWeight);
        assertThat(recipeRepository.findById(recipeId).getWeight()).isEqualTo(newWeight);
        assertThat(recipeRepository.findByWeight(newWeight).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByWeight(newWeight).stream()
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

        Long recipeId = recipeRepository.save(recipe);
        DishType newDishType = DishType.DESSERT;
        recipeRepository.changeDishType(recipeId, newDishType);

        assertThat(recipe.getDishType()).isEqualTo(newDishType);
        assertThat(recipeRepository.findById(recipeId).getDishType()).isEqualTo(newDishType);
        assertThat(recipeRepository.findByDishType(dishType).contains(recipe)).isFalse();
        assertThat(recipeRepository.findByDishType(dishType).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNull();
        assertThat(recipeRepository.findByDishType(newDishType).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByDishType(newDishType).stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElse(null)).isNotNull();
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

        Long recipeId = recipeRepository.save(recipe);

        List<Ingredient> newIngredients = new ArrayList<>();
        newIngredients.add(ingredient);
        recipeRepository.changeIngredient(recipeId, newIngredients);

        assertThat(recipe.getIngredients().contains(ingredient)).isTrue();

        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isTrue();
        assertThat(recipeRepository.findById(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByIngredient(ingredient).stream()
                .filter(r -> r.hasIngredient(ingredient.getId()))
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

        Long recipeId = recipeRepository.save(recipe);

        recipeRepository.addIngredient(recipeId, ingredient);

        assertThat(recipe.getIngredients().contains(ingredient)).isTrue();

        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isTrue();
        assertThat(recipeRepository.findById(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByIngredient(ingredient).stream()
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

        Long recipeId = recipeRepository.save(recipe);

        assertThat(recipe.getIngredients().contains(ingredient)).isTrue();

        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isTrue();
        assertThat(recipeRepository.findById(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isTrue();
        assertThat(recipeRepository.findByIngredient(ingredient).stream()
                .filter(r -> r.hasIngredient(ingredient.getId()))
                .findFirst().orElse(null)).isNotNull();

        recipeRepository.removeIngredient(recipeId, ingredient);
        assertThat(recipe.getIngredients().contains(ingredient)).isFalse();

        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isFalse();
        assertThat(recipeRepository.findById(recipeId).getIngredients().stream()
                .filter(i -> i.getId().equals(ingredient.getId()))
                .findFirst().orElse(null)).isNull();

        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isFalse();
        assertThat(recipeRepository.findByIngredient(ingredient).stream()
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

        Long recipeId = recipeRepository.save(recipe);

        // changeCost()를 호출해도 getCost()를 통해 자동으로 updateCost()가 수행된다.
        // 그로인해 정상적인 계산값이 저장되어 호출된다.
        double newCost = 456456;
        recipeRepository.changeCost(recipeId, newCost);

        double realCost = 456 * 456;
        assertThat(recipe.getCost()).isNotEqualTo(newCost);
        assertThat(recipe.getCost()).isEqualTo(realCost);
        assertThat(recipeRepository.findById(recipeId).getCost()).isEqualTo(realCost);
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

        Long recipeId = recipeRepository.save(recipe);

        // changeCost()를 호출해도 getCost()를 통해 자동으로 updateCost()가 수행된다.
        // 그로인해 정상적인 계산값이 저장되어 호출된다.
        recipeRepository.updateCost(recipeId);

        double realCost = 456 * 456;
        assertThat(recipe.getCost()).isEqualTo(realCost);
        assertThat(recipeRepository.findById(recipeId).getCost()).isEqualTo(realCost);
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

        Long recipeId = recipeRepository.save(recipe);

        String newNotes = "123123123";
        recipeRepository.changeNotes(recipeId, newNotes);

        assertThat(recipe.getNotes()).isEqualTo(newNotes);
        assertThat(recipeRepository.findById(recipeId).getNotes()).isEqualTo(newNotes);
    }

    @Test
    void removeRecipe() {
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

        Long recipeId = recipeRepository.save(recipe);

        recipeRepository.removeRecipe(recipe);
        assertThat(recipeRepository.findById(recipeId)).isNull();
    }

    @Test
    void removeById() {
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

        Long recipeId = recipeRepository.save(recipe);

        recipeRepository.removeById(recipeId);
        assertThat(recipeRepository.findById(recipeId)).isNull();
    }
}