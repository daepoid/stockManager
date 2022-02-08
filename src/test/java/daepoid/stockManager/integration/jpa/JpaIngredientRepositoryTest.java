package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaIngredientRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaIngredientRepository ingredientRepository;


    @Test
    public void saveIngredient() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientId).isEqualTo(ingredient.getId());
    }

    @Test
    public void findById() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findById(ingredientId)).isEqualTo(ingredient);
    }

    @Test
    public void findAll() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        int size = ingredientRepository.findAll().size();
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findAll().size()).isEqualTo(size + 1);
        assertThat(ingredientRepository.findAll().contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findAll().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByName() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findByName(item.getName()).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByName(item.getName()).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByRecipe() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

        String recipeRecipeNumber = "Drink-789";
        String recipeName = "recipe";
        int recipePrice = 789;
        int recipeWeight = 789;
        DishType recipeDishType = DishType.BOWL;
        List<Ingredient> recipeIngredients = new ArrayList<>();
        double recipeCost = 789;
        double recipeNetIncome = recipePrice - recipeCost;
        Set<Menu> recipeMenus = new HashSet<>();
        String recipeNotes = "notes";
        Recipe recipe = Recipe.builder()
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
                .build();
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

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findByRecipe(recipe.getId()).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByRecipe(recipe.getId()).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByUnitType() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findByUnitType(unitType).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByUnitType(unitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void changeName() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        String newName = "newIngredientName";
        ingredientRepository.changeName(ingredientId, newName);

        // then
        // 이름을 변경해도 getName() 수행시에 updateCost()가 같이 수행된다.
        assertThat(ingredient.getName()).isEqualTo(item.getName());
        assertThat(ingredientRepository.findByName(newName).contains(ingredient)).isFalse();
        assertThat(ingredientRepository.findByName(newName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    public void changeQuantity() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        int newQuantity = 456456;
        ingredientRepository.changeQuantity(ingredientId, newQuantity);

        // then
        assertThat(ingredient.getQuantity()).isEqualTo(newQuantity);
        assertThat(ingredientRepository.findById(ingredientId).getQuantity()).isEqualTo(newQuantity);
    }

    @Test
    public void changeUnitType() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        UnitType newUnitType = UnitType.kg;
        ingredientRepository.changeUnitType(ingredientId, newUnitType);

        // then
        assertThat(ingredientRepository.findByUnitType(newUnitType).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByUnitType(newUnitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(ingredientRepository.findByUnitType(unitType).contains(ingredient)).isFalse();
        assertThat(ingredientRepository.findByUnitType(unitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    public void changeUnitPrice() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        double newUnitPrice = 456.456;
        ingredientRepository.changeUnitPrice(ingredientId, newUnitPrice);

        // then
        assertThat(ingredient.getUnitPrice()).isEqualTo(newUnitPrice);
        assertThat(ingredientRepository.findById(ingredientId).getUnitPrice()).isEqualTo(newUnitPrice);
    }

    @Test
    public void changeLoss() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        double newLoss = 45.6;
        ingredientRepository.changeLoss(ingredientId, newLoss);

        // then
        assertThat(ingredient.getLoss()).isEqualTo(newLoss);
        assertThat(ingredientRepository.findById(ingredientId).getLoss()).isEqualTo(newLoss);
    }

    @Test
    public void changeCost() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        double newCost = 456456456;
        ingredientRepository.changeCost(ingredientId, newCost);

        // then
        // getCost() 수행시에 updateCost()가 자동으로 동작하여 올바른 값으로 갱신된다.
        assertThat(ingredient.getCost()).isEqualTo(ingredient.getQuantity() * ingredient.getUnitPrice());
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isNotEqualTo(newCost);
    }

    @Test
    public void updateCost() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        double newCost = 456456456;
        ingredientRepository.changeCost(ingredientId, newCost);

        // then
        // getCost() 수행시에 updateCost()가 자동으로 동작하여 올바른 값으로 갱신된다.
        assertThat(ingredient.getCost()).isEqualTo(ingredient.getQuantity() * ingredient.getUnitPrice());
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isNotEqualTo(newCost);

        ingredientRepository.updateCost(ingredientId);
        assertThat(ingredient.getCost()).isEqualTo(cost);
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(cost);
    }

    @Test
    public void deleteIngredient() throws Exception {
        // given
        String itemName = "item";
        ItemType itemItemType = ItemType.MEAT;
        int itemPrice = 123;
        int itemQuantity = 123;
        UnitType itemUnitType = UnitType.l;
        int itemPackageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(itemName)
                .itemType(itemItemType)
                .price(itemPrice)
                .quantity(itemQuantity)
                .unitType(itemUnitType)
                .packageCount(itemPackageCount)
                .ingredients(ingredients)
                .build();
        em.persist(item);

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
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        ingredientRepository.deleteIngredient(ingredientId);

        // then
        assertThat(ingredientRepository.findById(ingredientId)).isNull();

        // db에서는 삭제되고 id는 남아있음
        assertThat(ingredient.getId()).isEqualTo(ingredientId);
    }
}