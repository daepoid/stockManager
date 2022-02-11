package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
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
    public Ingredient findById(Long ingredientId) {
        return em.find(Ingredient.class, ingredientId);
    }

    @Override
    public List<Ingredient> findAll() {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByRecipe(Long recipeId) {
        return em.createQuery("select i from Ingredient i where i.recipe.id = :recipeId", Ingredient.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByName(String name) {
        return em.createQuery("select i from Ingredient i where i.name = :name", Ingredient.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByUnitType(UnitType unitType) {
        return em.createQuery("select i from Ingredient i where i.unitType = :unitType", Ingredient.class)
                .setParameter("unitType", unitType)
                .getResultList();
    }

    @Override
    public void changeItem(Long ingredientId, Item item) {
        em.find(Ingredient.class, ingredientId).changeItem(item);
    }

    @Override
    public void changeName(Long ingredientId, String name) {
        em.find(Ingredient.class, ingredientId).changeName(name);
    }

    @Override
    public void changeQuantity(Long ingredientId, int quantity) {
        em.find(Ingredient.class, ingredientId).changeQuantity(quantity);
    }

    @Override
    public void changeUnitType(Long ingredientId, UnitType unitType) {
        em.find(Ingredient.class, ingredientId).changeUnitType(unitType);

    }

    @Override
    public void changeUnitPrice(Long ingredientId, double unitPrice) {
        em.find(Ingredient.class, ingredientId).changeUnitPrice(unitPrice);
    }

    @Override
    public void changeLoss(Long ingredientId, double loss) {
        em.find(Ingredient.class, ingredientId).changeLoss(loss);
    }

    @Override
    public void changeCost(Long ingredientId, double cost) {
        em.find(Ingredient.class, ingredientId).changeCost(cost);
    }

    @Override
    public void updateCost(Long ingredientId) {
        em.find(Ingredient.class, ingredientId).updateCost();
    }

    @Override
    public void deleteIngredient(Long ingredientId) {
        em.remove(em.find(Ingredient.class, ingredientId));
    }
}
