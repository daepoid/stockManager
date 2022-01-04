package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaRecipeRepository implements RecipeRepository {

    private final EntityManager em;

    //==생성 로직==//
    @Override
    public Long save(Recipe recipe) {
        em.persist(recipe);
        return recipe.getId();
    }

    //==조회 로직==//
    @Override
    public Optional<Recipe> findById(Long id) {
        return Optional.of(em.find(Recipe.class, id));
    }

    @Override
    public List<Recipe> findAll() {
        return em.createQuery("select r from Recipe r", Recipe.class)
                .getResultList();
    }

    @Override
    public Optional<Recipe> findByName(String name) {
        return em.createQuery("select r from Recipe r where r.name = :name", Recipe.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    public List<Recipe> findByPrice(Integer price) {
        return em.createQuery("select r from Recipe r where r.price = :price", Recipe.class)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    public List<Recipe> findByWeight(Double weight) {
        return em.createQuery("select r from Recipe r where r.weight = :weight", Recipe.class)
                .setParameter("weight", weight)
                .getResultList();
    }

    @Override
    public List<Recipe> findByIngredient(Ingredient ingredient) {
        return em.createQuery("select r from Recipe r", Recipe.class)
                .getResultList().stream()
                .filter(recipe -> recipe.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByDishType(DishType dishType) {
        return em.createQuery("select r from Recipe r where r.dishType = :dishType", Recipe.class)
                .setParameter("dishType", dishType)
                .getResultList();
    }

    //==수정 로직==//
    @Override
    public void changeRecipeNumber(Long recipeId, String recipeNumber) {
        em.find(Recipe.class, recipeId)
                .changeRecipeNumber(recipeNumber);
    }

    @Override
    public void changeName(Long recipeId, String name) {
        em.find(Recipe.class, recipeId)
                .changeName(name);
    }

    @Override
    public void changePrice(Long recipeId, Integer price) {
        em.find(Recipe.class, recipeId)
                .changePrice(price);
    }

    @Override
    public void changeWeight(Long recipeId, Double weight) {
        em.find(Recipe.class, recipeId)
                .changeWeight(weight);
    }

    @Override
    public void changeDishType(Long recipeId, DishType dishType) {
        em.find(Recipe.class, recipeId)
                .changeDishType(dishType);
    }

    @Override
    public void changeIngredient(Long recipeId, List<Ingredient> ingredients) {
        em.find(Recipe.class, recipeId)
                .changeIngredients(ingredients);
    }

    @Override
    public boolean addIngredient(Long recipeId, Ingredient ingredient) {
        Recipe recipe = em.find(Recipe.class, recipeId);
        recipe.addIngredient(ingredient);
        return true;
    }

    @Override
    public boolean removeIngredient(Long recipeId, Ingredient ingredient) {
        Recipe recipe = em.find(Recipe.class, recipeId);
        if(recipe != null) {
            recipe.removeIngredient(ingredient);
            return true;
        }
        return false;
    }

    @Override
    public void updateCost(Long recipeId) {
        em.find(Recipe.class, recipeId)
                .updateCost();
    }

    @Override
    public void changeNotes(Long recipeId, String notes) {
        em.find(Recipe.class, recipeId)
                .changeNotes(notes);
    }

    //==삭제 로직==//
    @Override
    public void removeRecipe(Recipe recipe) {
        em.remove(recipe);
    }

    @Override
    public void removeById(Long id) {
        em.remove(em.find(Recipe.class, id));
    }
}
