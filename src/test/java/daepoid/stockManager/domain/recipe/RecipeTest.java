package daepoid.stockManager.domain.recipe;

import daepoid.stockManager.domain.item.UnitType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeTest {

    @Autowired
    private EntityManager em;

    @Test
    void changeId() {
    }

    @Test
    void changeName() {
    }

    @Test
    void changePrice() {
    }

    @Test
    void changeUnitPrice() {
    }

    @Test
    void changeWeight() {
    }

    @Test
    void addIngredient() {
        Recipe recipe = new Recipe();
        em.persist(recipe);
        recipe.addIngredient(Ingredient.createIngredient("name", 100, UnitType.kg, 10.1, 0.0));
        Assertions.assertThat(recipe.getIngredients().size()).isEqualTo(1);
    }

    @Test
    void removeIngredient() {
    }

    @Test
    void changeDishType() {
    }

    @Test
    void changeNotes() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void createRecipe() {
    }

    @Test
    void testCreateRecipe() {
    }

    @Test
    void getId() {
    }

    @Test
    void getName() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void getUnitPrice() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void getDishType() {
    }

    @Test
    void getIngredients() {
    }

    @Test
    void getNotes() {
    }
}