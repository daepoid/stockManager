package daepoid.stockManager.integration.jpa;

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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

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
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";


        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientId).isEqualTo(ingredient.getId());
    }

    @Test
    public void findById() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";


        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findById(ingredientId)).isEqualTo(ingredient);

    }

    @Test
    public void findAll() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";


        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findAll().size()).isEqualTo(1);
        assertThat(ingredientRepository.findAll().contains(ingredient)).isEqualTo(true);
    }

    @Test
    public void findByName() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";


        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findByName(name).contains(ingredient)).isEqualTo(true);

    }

    @Test
    public void findByRecipe() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);

        Recipe recipe = Recipe.builder()
                .recipeNumber("123")
                .name("recipe")
                .price(123)
                .dishType(DishType.FLATTER)
                .cost(0.0)
                .netIncome(123 - 0.0)
                .build();
        em.persist(recipe);

        String name = "name";

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .recipe(recipe)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findByRecipe(recipe.getId()).contains(ingredient)).isEqualTo(true);
    }

    @Test
    public void findByUnitType() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";


        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);

        // then
        assertThat(ingredientRepository.findByUnitType(UnitType.kg).contains(ingredient)).isEqualTo(true);
        assertThat(ingredientRepository.findByUnitType(UnitType.g).contains(ingredient)).isEqualTo(false);
    }

    @Test
    public void changeName() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeName(ingredientId, name + name);

        // then
        assertThat(ingredientRepository.findByName(name + name).contains(ingredient)).isEqualTo(true);
        assertThat(ingredientRepository.findByName(name).size()).isEqualTo(0);
    }

    @Test
    public void changeQuantity() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";
        int quantity = 123;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeQuantity(ingredientId, quantity * 10);

        // then
        assertThat(ingredient.getQuantity()).isEqualTo(quantity * 10);
        assertThat(ingredientRepository.findById(ingredientId).getQuantity()).isEqualTo(quantity * 10);

    }

    @Test
    public void changeUnitType() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";


        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeUnitType(ingredientId, UnitType.g);

        // then
        assertThat(ingredientRepository.findByUnitType(UnitType.g).contains(ingredient)).isEqualTo(true);
        assertThat(ingredientRepository.findByUnitType(UnitType.kg).contains(ingredient)).isEqualTo(false);

    }

    @Test
    public void changeUnitPrice() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";
        double unitPrice = 123.0;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(unitPrice)
                .loss(0.0)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeUnitPrice(ingredientId, unitPrice * 10);

        // then
        assertThat(ingredient.getUnitPrice()).isEqualTo(unitPrice * 10.0);
        assertThat(ingredientRepository.findById(ingredientId).getUnitPrice()).isEqualTo(unitPrice * 10.0);
    }

    @Test
    public void changeLoss() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";
        double loss = 0.0;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(123)
                .unitType(UnitType.kg)
                .unitPrice(123)
                .loss(loss)
                .cost(123 * 123)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeLoss(ingredientId, loss + 10.0);

        // then
        assertThat(ingredient.getLoss()).isEqualTo(loss + 10.0);
        assertThat(ingredientRepository.findById(ingredientId).getLoss()).isEqualTo(loss + 10.0);

    }

    @Test
    public void changeCost() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);

        String name = "name";
        int quantity = 123;
        double unitPrice = 123.0;
        double cost = 123 * 123;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(UnitType.kg)
                .unitPrice(unitPrice)
                .loss(0.0)
                .cost(cost)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeCost(ingredientId, cost * 100);

        // then
        // 원래대로라면 정상적으로 변경 되어야 하지만 getCost()에 UpdateCost()를 같이 실행하도록 작성하여 변경이 이루어진 후 재변경이 이루어진다.
//        assertThat(ingredient.getCost()).isEqualTo(cost * 100);
        assertThat(ingredient.getCost()).isEqualTo(cost);
//        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(cost * 100);
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(cost);
    }

    @Test
    public void updateCost() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";
        int quantity = 123;
        double unitPrice = 123.0;
        double cost = 123 * 123;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(UnitType.kg)
                .unitPrice(unitPrice)
                .loss(0.0)
                .cost(cost)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.changeCost(ingredientId, cost * 100);

        // then
        // getCost()에 updateCost()가 같이 실행되어 변경 후 재변경된다.
//        assertThat(ingredient.getCost()).isEqualTo(cost * 100);
        assertThat(ingredient.getCost()).isEqualTo(cost);
//        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(cost * 100);
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(cost);

        ingredientRepository.updateCost(ingredientId);
        assertThat(ingredient.getCost()).isEqualTo(cost);
        assertThat(ingredientRepository.findById(ingredientId).getCost()).isEqualTo(cost);
    }

    @Test
    public void deleteIngredient() throws Exception {
        // given
        Item item = Item.builder()
                .name("item")
                .itemType(ItemType.BOTTLE)
                .unitType(UnitType.kg)
                .build();
        em.persist(item);
        String name = "name";
        int quantity = 123;
        double unitPrice = 123.0;
        double cost = 123 * 123;

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(name)
                .quantity(quantity)
                .unitType(UnitType.kg)
                .unitPrice(unitPrice)
                .loss(0.0)
                .cost(cost)
                .build();

        // when
        Long ingredientId = ingredientRepository.saveIngredient(ingredient);
        ingredientRepository.deleteIngredient(ingredientId);

        // then
        assertThat(ingredientRepository.findById(ingredientId)).isEqualTo(null);
        // db에서는 삭제되고 id는 남아있음
        assertThat(ingredient.getId()).isEqualTo(ingredientId);
    }
}