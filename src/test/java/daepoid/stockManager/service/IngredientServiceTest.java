package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IngredientServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    IngredientService ingredientService;

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

    private Recipe createRecipe() {
        String recipeRecipeNumber = "456";
        String recipeName = "recipe name";
        int price = 456;
        DishType recipeDishType = DishType.BOWL;
        double recipeCost = 456;
        double recipeNetIncome = 456;

        return Recipe.builder()
                .recipeNumber(recipeRecipeNumber)
                .name(recipeName)
                .price(price)
                .dishType(recipeDishType)
                .cost(recipeCost)
                .netIncome(recipeNetIncome)
                .build();
    }

    @Test
    void saveIngredient() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientId).isEqualTo(ingredient.getId());
    }

    @Test
    void findIngredient() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findIngredient(ingredientId)).isEqualTo(ingredient);
        assertThat(ingredientService.findIngredient(ingredientId).getId()).isEqualTo(ingredientId);
    }

    @Test
    void findIngredients() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findIngredients().contains(ingredient)).isTrue();
        assertThat(ingredientService.findIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByName() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findByName(item.getName()).contains(ingredient)).isTrue();
        assertThat(ingredientService.findByName(item.getName()).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByRecipe() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findByRecipe(recipe.getId()).contains(ingredient)).isTrue();
        assertThat(ingredientService.findByRecipe(recipe.getId()).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByUnitType() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findByUnitType(unitType).contains(ingredient)).isTrue();
        assertThat(ingredientService.findByUnitType(unitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeItem() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        String newItemName ="new item name";
        ItemType newItemItemType = ItemType.MEAT;
        int newItemPrice = 789;
        int newItemQuantity = 789;
        UnitType newItemUnitType = UnitType.g;
        int newItemPackageCount = 789;
        List<Ingredient> newItemIngredients = new ArrayList<>();
        Item newItem = Item.builder()
                .name(newItemName)
                .itemType(newItemItemType)
                .price(newItemPrice)
                .quantity(newItemQuantity)
                .unitType(newItemUnitType)
                .packageCount(newItemPackageCount)
                .ingredients(newItemIngredients)
                .build();
        em.persist(newItem);
        ingredientService.changeItem(ingredientId, newItem);

        assertThat(ingredientService.findIngredient(ingredientId).getItem()).isEqualTo(newItem);
        // item.getName()을 통해 ingredient의 name을 설정하므로 newItemName을 이용하여 찾을 수 있다.
        assertThat(ingredientService.findByName(newItemName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeName() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        String newName = "new item Name";
        ingredientService.changeName(ingredientId, newName);

        assertThat(ingredient.getItem().getName()).isEqualTo(item.getName());
        assertThat(ingredientService.findIngredient(ingredientId).getName()).isEqualTo(ingredient.getItem().getName());

//        // getName() 호출 시 item.name을 가져와 업데이트를 진행하므로 changeName()을 호출할 필요 없다.
//        assertThat(ingredient.getName()).isEqualTo(name + name);
//        assertThat(ingredientService.findIngredient(ingredientId).getName()).isEqualTo(name + name);
    }

    @Test
    void changeQuantity() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeQuantity(ingredientId, quantity * 100);

        assertThat(ingredient.getQuantity()).isEqualTo(quantity * 100);
        assertThat(ingredientService.findIngredient(ingredientId).getQuantity()).isEqualTo(quantity * 100);
    }

    @Test
    void changeUnitType() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeUnitType(ingredientId, UnitType.l);

        assertThat(ingredient.getUnitType()).isEqualTo(UnitType.l);
        assertThat(ingredientService.findIngredient(ingredientId).getUnitType()).isEqualTo(UnitType.l);
    }

    @Test
    void changeUnitPrice() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeUnitPrice(ingredientId, unitPrice * 10);

        assertThat(ingredient.getUnitPrice()).isEqualTo(unitPrice * 10);
        assertThat(ingredientService.findIngredient(ingredientId).getUnitPrice()).isEqualTo(unitPrice * 10);
    }

    @Test
    void changeLoss() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeLoss(ingredientId, loss * 10);

        assertThat(ingredient.getLoss()).isEqualTo(loss * 10);
        assertThat(ingredientService.findIngredient(ingredientId).getLoss()).isEqualTo(loss * 10);
    }

    @Test
    void changeCost() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeCost(ingredientId, cost * 10);

        assertThat(ingredientService.findIngredient(ingredientId).getCost()).isEqualTo(quantity * unitPrice);

//        // updateCost()가 자동으로 호출된다.
//        assertThat(ingredient.getCost()).isEqualTo(cost * 10);
//        assertThat(ingredientService.findIngredient(ingredientId).getCost()).isEqualTo(cost * 10);
    }

    @Test
    void updateCost() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findIngredient(ingredientId).getCost()).isEqualTo(quantity * unitPrice);

//        // getCost() 호출 시 자동으로 UpdateCost()가 동작하기 떄문에 따로 Update를 호출하지 않아도 된다.
//        ingredientService.updateCost(ingredientId);
//
//        assertThat(ingredient.getCost()).isEqualTo(cost);
//        assertThat(ingredientService.findIngredient(ingredientId).getCost()).isEqualTo(cost);
    }

    @Test
    void deleteIngredient() {
        Item item = createItem();
        em.persist(item);

        Recipe recipe = createRecipe();
        em.persist(recipe);

        int quantity = 456;
        UnitType unitType = UnitType.ml;
        double unitPrice = 45.6;
        double loss = 0.456;
        double cost = quantity * unitPrice;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.deleteIngredient(ingredientId);

        assertThat(ingredientService.findIngredient(ingredientId)).isEqualTo(null);
    }
}