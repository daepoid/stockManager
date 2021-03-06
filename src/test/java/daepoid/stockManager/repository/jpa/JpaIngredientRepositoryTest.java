package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.food.DishType;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaIngredientRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaIngredientRepository ingredientRepository;

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
                .imgUrl("")
                .build();
    }

    @Test
    public void saveIngredient() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        // then
        assertThat(ingredientId).isEqualTo(ingredient.getId());
    }

    @Test
    public void findById() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        // then
        assertThat(ingredientRepository.findById(ingredientId)).isEqualTo(ingredient);
        assertThat(ingredientRepository.findById(ingredientId).getId()).isEqualTo(ingredientId);
    }

    @Test
    public void findAll() throws Exception {
        // given
        int size = ingredientRepository.findAll().size();

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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        // then
        assertThat(ingredientRepository.findByName(item.getName()).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByName(item.getName()).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByRecipe() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        // then
        assertThat(ingredientRepository.findByRecipeId(recipe.getId()).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByRecipeId(recipe.getId()).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByUnitType() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);
        // then
        assertThat(ingredientRepository.findByUnitType(unitType).contains(ingredient)).isTrue();
        assertThat(ingredientRepository.findByUnitType(unitType).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void changeItem() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

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

        ingredientRepository.changeItem(ingredientId, newItem);

        // then
        assertThat(ingredientRepository.findById(ingredientId).getItem().getId()).isEqualTo(newItem.getId());
        // item.getName()??? ?????? ingredient??? name??? ??????????????? newItemName??? ???????????? ?????? ??? ??????.
        // ???, getName()??? ???????????? ?????? ???????????? ????????? ??????????????? ??????????????? ?????? ????????? ????????? ????????? ?????? ??? ??????.

        // ?????????????????? ????????? ????????? ??????
        assertThat(ingredientRepository.findByName(newItemName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();

        // ?????????????????? ????????? ??????
        assertThat(ingredientRepository.findAll().stream()
                .filter(i -> i.getName().equals(newItemName) && i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        // getName()??? ?????? ???????????? ?????????????????? ????????? ?????????.
        assertThat(ingredientRepository.findByName(newItemName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void changeName() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        String newName = "newIngredientName";
        ingredientRepository.changeName(ingredientId, newName);

        // then
        // getName() ?????? ??? item.name??? ????????? ??????????????? changeName()??? ????????? ?????? ??????.
        // ??????, getName()??? ???????????? ?????? ???????????? ????????? ????????? ????????? ?????? ??? ??????.
        assertThat(ingredientRepository.findByName(newName).stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(ingredient.getName()).isNotEqualTo(newName);
        assertThat(ingredientRepository.findById(ingredientId).getName()).isNotEqualTo(newName);

        assertThat(ingredient.getItem().getName()).isEqualTo(item.getName());
        assertThat(ingredientRepository.findById(ingredientId).getName()).isEqualTo(ingredient.getItem().getName());
    }

    @Test
    public void changeQuantity() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        int newQuantity = 456456;
        ingredientRepository.changeQuantity(ingredientId, newQuantity);

        // then
        assertThat(ingredient.getQuantity()).isEqualTo(newQuantity);
        assertThat(ingredientRepository.findById(ingredientId).getQuantity()).isEqualTo(newQuantity);
    }

    @Test
    public void changeUnitType() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        double newUnitPrice = 456.456;
        ingredientRepository.changeUnitPrice(ingredientId, newUnitPrice);

        // then
        assertThat(ingredient.getUnitPrice()).isEqualTo(newUnitPrice);
        assertThat(ingredientRepository.findById(ingredientId).getUnitPrice()).isEqualTo(newUnitPrice);
    }

    @Test
    public void changeLoss() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        double newLoss = 45.6;
        ingredientRepository.changeLoss(ingredientId, newLoss);

        // then
        assertThat(ingredient.getLoss()).isEqualTo(newLoss);
        assertThat(ingredientRepository.findById(ingredientId).getLoss()).isEqualTo(newLoss);
    }

    @Test
    public void changeCost() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        double newCost = 456456456;
        ingredientRepository.changeCost(ingredientId, newCost);

        // then
        // getCost() ?????? ??? updateCost()??? ???????????? ???????????? quantity * unitPrice??? ????????????.
        // getCost()??? ???????????? ????????? ???????????? ?????? ????????? ?????? ?????? ??? ??????.
        assertThat(em.createQuery("select i from Ingredient i where i.cost=:newCost", Ingredient.class)
                .setParameter("newCost", newCost)
                .getResultList().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(ingredient.getCost()).isNotEqualTo(newCost);
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isNotEqualTo(newCost);

        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(quantity * unitPrice);
    }

    @Test
    public void updateCost() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        double newCost = 456456456;
        ingredientRepository.changeCost(ingredientId, newCost);

        // then
        // getCost() ?????? ??? updateCost()??? ???????????? ???????????? quantity * unitPrice??? ????????????.
        // getCost()??? ???????????? ????????? ???????????? ?????? ????????? ?????? ?????? ??? ??????.
        assertThat(em.createQuery("select i from Ingredient i where i.cost=:newCost", Ingredient.class)
                .setParameter("newCost", newCost)
                .getResultList().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNotNull();

        ingredientRepository.updateCost(ingredientId);

        assertThat(em.createQuery("select i from Ingredient i where i.cost=:newCost", Ingredient.class)
                .setParameter("newCost", newCost)
                .getResultList().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    public void deleteIngredient() throws Exception {
        // given
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

        // when
        Long ingredientId = ingredientRepository.save(ingredient);

        ingredientRepository.remove(ingredientId);

        // then
        assertThat(ingredientRepository.findById(ingredientId)).isNull();

        // db????????? ???????????? id??? ????????????
        assertThat(ingredient.getId()).isEqualTo(ingredientId);
    }
}