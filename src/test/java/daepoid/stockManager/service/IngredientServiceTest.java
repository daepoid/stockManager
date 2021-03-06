package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.food.DishType;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        String recipeImgUrl = "";

        return Recipe.builder()
                .recipeNumber(recipeRecipeNumber)
                .name(recipeName)
                .price(price)
                .dishType(recipeDishType)
                .cost(recipeCost)
                .netIncome(recipeNetIncome)
                .imgUrl(recipeImgUrl)
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

        assertThat(ingredientService.findIngredient(ingredientId).getItem().getId()).isEqualTo(newItem.getId());
        // item.getName()??? ?????? ingredient??? name??? ??????????????? newItemName??? ???????????? ?????? ??? ??????.
        // ???, getName()??? ???????????? ?????? ???????????? ????????? ??????????????? ??????????????? ?????? ????????? ????????? ????????? ?????? ??? ??????.

        // ?????????????????? ????????? ????????? ??????
        assertThat(ingredientService.findByName(newItemName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();

        // ?????????????????? ????????? ??????
        assertThat(ingredientService.findIngredients().stream()
                .filter(i -> i.getName().equals(newItemName) && i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        // getName()??? ?????? ???????????? ?????????????????? ????????? ?????????.
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

        // getName() ?????? ??? item.name??? ????????? ??????????????? changeName()??? ????????? ?????? ??????.
        // ??????, getName()??? ???????????? ?????? ???????????? ????????? ????????? ????????? ?????? ??? ??????.
        assertThat(ingredientService.findByName(newName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(ingredient.getName()).isNotEqualTo(newName);
        assertThat(ingredientService.findIngredient(ingredientId).getName()).isNotEqualTo(newName);

        assertThat(ingredient.getItem().getName()).isEqualTo(item.getName());
        assertThat(ingredientService.findIngredient(ingredientId).getName()).isEqualTo(ingredient.getItem().getName());
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

        int newQuantity = 789;
        ingredientService.changeQuantity(ingredientId, newQuantity);

        assertThat(ingredient.getQuantity()).isEqualTo(newQuantity);
        assertThat(ingredientService.findIngredient(ingredientId).getQuantity()).isEqualTo(newQuantity);
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

        UnitType newUnitType = UnitType.l;
        ingredientService.changeUnitType(ingredientId, newUnitType);

        assertThat(ingredient.getUnitType()).isEqualTo(newUnitType);
        assertThat(ingredientService.findIngredient(ingredientId).getUnitType()).isEqualTo(newUnitType);
        assertThat(ingredientService.findByUnitType(newUnitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(ingredientService.findByUnitType(unitType).contains(ingredient)).isFalse();
        assertThat(ingredientService.findByUnitType(unitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();
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

        double newUnitPrice = 78.9;
        ingredientService.changeUnitPrice(ingredientId, newUnitPrice);

        assertThat(ingredient.getUnitPrice()).isEqualTo(newUnitPrice);
        assertThat(ingredientService.findIngredient(ingredientId).getUnitPrice()).isEqualTo(newUnitPrice);
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

        double newLoss = 7.89;
        ingredientService.changeLoss(ingredientId, newLoss);

        assertThat(ingredient.getLoss()).isEqualTo(newLoss);
        assertThat(ingredientService.findIngredient(ingredientId).getLoss()).isEqualTo(newLoss);
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

        double newCost = 7.89;
        ingredientService.changeCost(ingredientId, newCost);

        // getCost() ?????? ??? updateCost()??? ???????????? ???????????? quantity * unitPrice??? ????????????.
        // getCost()??? ???????????? ????????? ???????????? ?????? ????????? ?????? ?????? ??? ??????.
        assertThat(em.createQuery("select i from Ingredient i where i.cost=:newCost", Ingredient.class)
                .setParameter("newCost", newCost)
                .getResultList().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(ingredient.getCost()).isNotEqualTo(newCost);
        assertThat(ingredientService.findIngredient(ingredientId).getCost()).isNotEqualTo(newCost);

        assertThat(ingredientService.findIngredient(ingredientId).getCost()).isEqualTo(quantity * unitPrice);


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

        double newCost = 7.89;
        ingredientService.changeCost(ingredientId, newCost);

        // getCost() ?????? ??? updateCost()??? ???????????? ???????????? quantity * unitPrice??? ????????????.
        // getCost()??? ???????????? ????????? ???????????? ?????? ????????? ?????? ?????? ??? ??????.
        assertThat(em.createQuery("select i from Ingredient i where i.cost=:newCost", Ingredient.class)
                .setParameter("newCost", newCost)
                .getResultList().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        ingredientService.updateCost(ingredientId);

        assertThat(em.createQuery("select i from Ingredient i where i.cost=:newCost", Ingredient.class)
                .setParameter("newCost", newCost)
                .getResultList().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();
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

        assertThat(ingredientService.findIngredient(ingredientId)).isNull();

        // db????????? ???????????? id??? ????????????
        assertThat(ingredient.getId()).isEqualTo(ingredientId);
    }
}