package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaRecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipeServiceTest {

    @Autowired
    RecipeService recipeService;

    @Autowired
    JpaRecipeRepository recipeRepository;

    @Autowired
    EntityManager em;

    @Test
    void saveRecipe() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipe.getId()).isEqualTo(recipeId);
    }

    @Test
    void findRecipe() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipe(recipeId)).isEqualTo(recipe);
    }

    @Test
    void findRecipes() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipeService.findRecipes().contains(recipe)).isEqualTo(true);
    }

    @Test
    void findRecipeByName() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);
        assertThat(recipeService.findRecipeByName(name)).isEqualTo(recipe);
    }

    @Test
    void findRecipesByPrice() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);
        assertThat(recipeService.findRecipesByPrice(price).contains(recipe)).isEqualTo(true);
    }

    @Test
    void findRecipesByWeight() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);
        assertThat(recipeService.findRecipesByWeight(weight).contains(recipe)).isEqualTo(true);
    }

    @Test
    void findRecipesByIngredient() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(123)
                .quantity(123)
                .unitType(UnitType.l)
                .packageCount(123)
                .build();
        em.persist(item);

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name("ingredient")
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(12.0)
                .loss(0.0)
                .cost(123)
                .build();
        em.persist(ingredient);
        ingredients.add(ingredient);

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);
        assertThat(recipeService.findRecipesByIngredient(ingredient).contains(recipe)).isEqualTo(true);
    }

    @Test
    void findRecipesByDishType() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);
        assertThat(recipeService.findRecipesByDishType(dishType).contains(recipe)).isEqualTo(true);

    }

    @Test
    void changeRecipeNumber() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        String newRecipeNumber = "123123123";
        recipeService.changeRecipeNumber(recipeId, newRecipeNumber);
        assertThat(recipe.getRecipeNumber()).isEqualTo(newRecipeNumber);
    }

    @Test
    void changeRecipeName() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        String newName = "newRecipe";
        recipeService.changeRecipeName(recipeId, newName);
        assertThat(recipe.getName()).isEqualTo(newName);
    }

    @Test
    void changePrice() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);
        int newPrice = 123123;
        recipeService.changePrice(recipeId, newPrice);
        assertThat(recipe.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void changeWeight() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        double newWeight = 123123;
        recipeService.changeWeight(recipeId, newWeight);
        assertThat(recipe.getWeight()).isEqualTo(newWeight);
    }

    @Test
    void changeCost() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(123)
                .quantity(123)
                .unitType(UnitType.l)
                .packageCount(123)
                .build();
        em.persist(item);

        int quantity = 123;
        double unitPrice = 12.0;
        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name("ingredient")
                .quantity(quantity)
                .unitType(UnitType.kg)
                .unitPrice(unitPrice)
                .loss(0.0)
                .cost(123)
                .build();
        em.persist(ingredient);
        ingredients.add(ingredient);

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        assertThat(recipe.getCost()).isEqualTo(quantity * unitPrice);

//        // getCost()에서 자동으로 updateCost()가 수행된다.
//        double newCost = 123123;
//        recipeService.changeCost(recipeId, newCost);
//        assertThat(recipe.getCost()).isEqualTo(newCost);
    }

    @Test
    void changeIngredient() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        List<Ingredient> newIngredients = new ArrayList<>();
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(123)
                .quantity(123)
                .unitType(UnitType.l)
                .packageCount(123)
                .build();
        em.persist(item);

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name("ingredient")
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(12)
                .loss(0.0)
                .cost(123)
                .build();
        em.persist(ingredient);
        newIngredients.add(ingredient);

        recipeService.changeIngredients(recipeId, newIngredients);
        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(true);
    }

    @Test
    void addIngredient() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(123)
                .quantity(123)
                .unitType(UnitType.l)
                .packageCount(123)
                .build();
        em.persist(item);

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name("ingredient")
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(12)
                .loss(0.0)
                .cost(123)
                .build();
        em.persist(ingredient);

        recipeService.addIngredient(recipeId, ingredient);
        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(true);
    }

    @Test
    void removeIngredient() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(123)
                .quantity(123)
                .unitType(UnitType.l)
                .packageCount(123)
                .build();
        em.persist(item);

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name("ingredient")
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(12)
                .loss(0.0)
                .cost(123)
                .build();
        em.persist(ingredient);

        recipeService.addIngredient(recipeId, ingredient);
        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(true);

        recipeService.removeIngredient(recipeId, ingredient);
        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(false);
    }

    @Test
    void changeDishType() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        DishType newDishType = DishType.DESSERT;
        recipeService.changeDishType(recipeId, newDishType);
        assertThat(recipe.getDishType()).isEqualTo(newDishType);
    }

    @Test
    void updateCost() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(123)
                .quantity(123)
                .unitType(UnitType.l)
                .packageCount(123)
                .build();
        em.persist(item);

        int quantity = 123;
        double unitPrice = 12.0;
        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name("ingredient")
                .quantity(quantity)
                .unitType(UnitType.kg)
                .unitPrice(unitPrice)
                .loss(0.0)
                .cost(123)
                .build();
        em.persist(ingredient);
        ingredients.add(ingredient);

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        recipeService.updateCost(recipeId);
        assertThat(recipe.getCost()).isEqualTo(quantity * unitPrice);
    }

    @Test
    void changeNotes() {
        String recipeNumber = "123123";
        String name = "recipe";
        int price = 123;
        double weight = 12.3;
        DishType dishType = DishType.BASKET;
        List<Ingredient> ingredients = new ArrayList<>();
        double cost = 123;
        double netIncome = 123;
        Set<Menu> menus = new HashSet<>();

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
                .build();

        Long recipeId = recipeService.saveRecipe(recipe);

        String notes = "new Notes";
        recipeService.changeNotes(recipeId, notes);
        assertThat(recipe.getNotes()).isEqualTo(notes);
    }
}