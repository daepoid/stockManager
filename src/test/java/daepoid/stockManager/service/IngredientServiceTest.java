package daepoid.stockManager.service;

import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.repository.IngredientRepository;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IngredientServiceTest {

    @Autowired
    private JpaIngredientRepository ingredientRepository;

    @Test
    void saveIngredient() {
        Ingredient ingredient = Ingredient.createIngredient("name", 1000, UnitType.kg, 10.1, 0.0);
        ingredientRepository.saveIngredient(ingredient);

        System.out.println("ingredient = " + ingredient);
        Assertions.assertThat(ingredient).isEqualTo(ingredientRepository.findIngredient(ingredient.getId()));
    }

    @Test
    void findIngredient() {
    }

    @Test
    void findIngredients() {
    }
}