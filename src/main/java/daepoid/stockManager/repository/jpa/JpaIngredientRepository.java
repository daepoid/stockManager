package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.search.IngredientSearch;
import daepoid.stockManager.repository.IngredientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaIngredientRepository implements IngredientRepository {

    private final EntityManager em;

    @Override
    public Long save(Ingredient ingredient) {
        em.persist(ingredient);
        return ingredient.getId();
    }

    @Override
    public Optional<Ingredient> findById(Long ingredientId) {
        return Optional.of(em.find(Ingredient.class, ingredientId));
    }

    @Override
    public List<Ingredient> findAll() {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .getResultList();
    }

    @Override
    public List<Ingredient> findAll(int maxResult) {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<Ingredient> findAll(int firstResult, int maxResult) {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByName(String name) {
        return em.createQuery("select i from Ingredient i where i.name = :name", Ingredient.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByItemId(Long itemId) {
        return em.createQuery("select i from Ingredient i where i.item.id = :itemId", Ingredient.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByFoodId(Long foodId) {
        return em.createQuery("select i from Ingredient i where i.food.id = :foodId", Ingredient.class)
                .setParameter("foodId", foodId)
                .getResultList();
    }

    @Override
    public List<Ingredient> findByIngredientSearch(IngredientSearch ingredientSearch) {
        // language=JPAQL
        String jpql = "select i From Ingredient i";

        boolean isFirstCondition = true;

        // 직원 이름 검색
        if (StringUtils.hasText(ingredientSearch.getIngredientName())) {
            jpql += " where";
            isFirstCondition = false;
            jpql += " i.item.name like :ingredientName";
        }

        if (StringUtils.hasText(ingredientSearch.getRecipeName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " i.recipe.name like :recipeName";
        }
        TypedQuery<Ingredient> query = em.createQuery(jpql, Ingredient.class).setMaxResults(1000); //최대 1000건
        if (StringUtils.hasText(ingredientSearch.getIngredientName())) {
            query = query.setParameter("ingredientName", "%" + ingredientSearch.getIngredientName() + "%");
        }
        if (StringUtils.hasText(ingredientSearch.getRecipeName())) {
            query = query.setParameter("recipeName", "%" + ingredientSearch.getRecipeName() + "%");
        }
        return query.getResultList();
    }

    @Override
    public void changeQuantity(Long ingredientId, Double quantity) {
        em.find(Ingredient.class, ingredientId).changeQuantity(quantity);
    }

    @Override
    public void changeUnitType(Long ingredientId, UnitType unitType) {
        em.find(Ingredient.class, ingredientId).changeUnitType(unitType);
    }

    @Override
    public void changeUnitPrice(Long ingredientId, Double unitPrice) {
        em.find(Ingredient.class, ingredientId).changeUnitPrice(unitPrice);
    }

    @Override
    public void changeLoss(Long ingredientId, Double loss) {
        em.find(Ingredient.class, ingredientId).changeLoss(loss);
    }

    @Override
    public void changeFood(Long ingredientId, Food food) {
        em.find(Ingredient.class, ingredientId).changeFood(food);
    }

    @Override
    public void remove(Long ingredientId) {
        em.remove(em.find(Ingredient.class, ingredientId));
    }
}
