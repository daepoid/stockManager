package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Recipe;

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

        assertThat(ingredientService.findIngredient(ingredientId).getItem().getId()).isEqualTo(newItem.getId());
        // item.getName()을 통해 ingredient의 name을 설정하므로 newItemName을 이용하여 찾을 수 있다.
        // 단, getName()을 수행하지 않은 상태이면 제대로 업데이트가 이루어지지 않아 원하는 결과가 나오지 않을 수 있다.

        // 예상한대로의 결과가 나오지 않음
        assertThat(ingredientService.findByName(newItemName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();

        // 예상한대로의 결과가 나옴
        assertThat(ingredientService.findIngredients().stream()
                .filter(i -> i.getName().equals(newItemName) && i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        // getName()을 통해 갱신되어 예상한대로의 결과가 나온다.
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

        // getName() 호출 시 item.name을 가져와 갱신하므로 changeName()을 호출할 필요 없다.
        // 다만, getName()을 호출하지 않은 상태라면 예상한 대로의 결과가 나올 수 있다.
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

        // getCost() 호출 시 updateCost()가 자동으로 호출되어 quantity * unitPrice로 갱신된다.
        // getCost()를 사용하지 않으면 올바르지 않게 변경된 값을 얻을 수 있다.
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

        // getCost() 호출 시 updateCost()가 자동으로 호출되어 quantity * unitPrice로 갱신된다.
        // getCost()를 사용하지 않으면 올바르지 않게 변경된 값을 얻을 수 있다.
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

        // db에서는 삭제되고 id는 남아있음
        assertThat(ingredient.getId()).isEqualTo(ingredientId);
    }
}