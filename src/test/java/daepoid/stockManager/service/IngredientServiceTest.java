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
    JpaIngredientRepository ingredientRepository;

    @Autowired
    IngredientService ingredientService;

    @Test
    void saveIngredient() {

        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredient.getId()).isEqualTo(ingredientId);
    }

    @Test
    void findIngredient() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findIngredient(ingredientId)).isEqualTo(ingredient);
    }

    @Test
    void findIngredients() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findIngredients().contains(ingredient)).isTrue();
    }

    @Test
    void findByName() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findByName(name).contains(ingredient)).isTrue();
    }

    @Test
    void findByRecipe() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("123")
                .price(123)
                .dishType(DishType.BASKET)
                .cost(123)
                .netIncome(123)
                .build();
        em.persist(recipe);

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .recipe(recipe)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);
        assertThat(ingredientService.findByRecipe(recipe.getId()).contains(ingredient)).isTrue();
    }

    @Test
    void findByUnitType() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        assertThat(ingredientService.findByUnitType(unitType).contains(ingredient)).isTrue();
    }

    @Test
    void changeItem() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .ingredients(new ArrayList<>())
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        Item newItem = Item.builder()
                .name("newItem")
                .itemType(ItemType.MEAT)
                .price(5678)
                .quantity(5678)
                .unitType(UnitType.g)
                .packageCount(5678)
                .ingredients(new ArrayList<>())
                .build();
        em.persist(newItem);

        ingredientService.changeItem(ingredientId, newItem);

        assertThat(ingredientService.findIngredient(ingredientId).getItem()).isEqualTo(newItem);
    }

    @Test
    void changeName() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeName(ingredientId, name + name);

        assertThat(ingredient.getItem().getName()).isEqualTo(item.getName());
        assertThat(ingredientService.findIngredient(ingredientId).getName()).isEqualTo(ingredient.getItem().getName());

//        // getName() 호출 시 item.name을 가져와 업데이트를 진행하므로 changeName()을 호출할 필요 없다.
//        assertThat(ingredient.getName()).isEqualTo(name + name);
//        assertThat(ingredientService.findIngredient(ingredientId).getName()).isEqualTo(name + name);
    }

    @Test
    void changeQuantity() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeQuantity(ingredientId, quantity * 100);

        assertThat(ingredient.getQuantity()).isEqualTo(quantity * 100);
        assertThat(ingredientService.findIngredient(ingredientId).getQuantity()).isEqualTo(quantity * 100);
    }

    @Test
    void changeUnitType() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeUnitType(ingredientId, UnitType.l);

        assertThat(ingredient.getUnitType()).isEqualTo(UnitType.l);
        assertThat(ingredientService.findIngredient(ingredientId).getUnitType()).isEqualTo(UnitType.l);
    }

    @Test
    void changeUnitPrice() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeUnitPrice(ingredientId, unitPrice * 10);

        assertThat(ingredient.getUnitPrice()).isEqualTo(unitPrice * 10);
        assertThat(ingredientService.findIngredient(ingredientId).getUnitPrice()).isEqualTo(unitPrice * 10);
    }

    @Test
    void changeLoss() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.changeLoss(ingredientId, loss * 10);

        assertThat(ingredient.getLoss()).isEqualTo(loss * 10);
        assertThat(ingredientService.findIngredient(ingredientId).getLoss()).isEqualTo(loss * 10);
    }

    @Test
    void changeCost() {
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
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
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
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
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .price(1234)
                .quantity(1234)
                .unitType(UnitType.kg)
                .packageCount(1234)
                .build();
        em.persist(item);

        String name = "ingredient";
        int quantity = 12;
        UnitType unitType = UnitType.kg;
        double unitPrice = 12;
        double loss = 12;
        double cost = 12;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(unitType)
                .unitPrice(unitPrice)
                .loss(loss)
                .cost(cost)
                .build();

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        ingredientService.deleteIngredient(ingredientId);

        assertThat(ingredientService.findIngredient(ingredientId)).isEqualTo(null);
    }
}