package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaIngredientRepository implements IngredientRepository {

    private final EntityManager em;

    @Override
    public Long saveIngredient(Ingredient ingredient) {
        em.persist(ingredient);
        return ingredient.getId();
    }

    @Override
    public Ingredient findIngredient(Long ingredientId) {
        return em.find(Ingredient.class, ingredientId);
    }

    @Override
    public List<Ingredient> findIngredients() {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .getResultList();
    }
}
