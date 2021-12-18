package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.RecipeRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryRecipeRepository implements RecipeRepository {

    private static final Map<Long, Recipe> store = new HashMap<>();
    private Long sequence = 0L;

    //==생성 로직==//
    @Override
    public Long save(Recipe recipe) {
        recipe.changeId(++sequence);
        store.put(recipe.getId(), recipe);
        return recipe.getId();
    }

    //==조회 로직==//
    @Override
    public Optional<Recipe> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public List<Recipe> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Recipe> findByName(String name) {
        return store.values().stream()
                .filter(recipe -> recipe.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Recipe> findByPrice(Integer price) {
        return store.values().stream()
                .filter(recipe -> recipe.getPrice().equals(price))
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByUnitPrice(Double unitPrice) {
        return store.values().stream()
                .filter(recipe -> recipe.getUnitPrice().equals(unitPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByWeight(Double weight) {
        return store.values().stream()
                .filter(recipe -> recipe.getWeight().equals(weight))
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByIngredient(Ingredient ingredient) {
        return store.values().stream()
                .filter(recipe -> recipe.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByDishType(DishType dishType) {
        return store.values().stream()
                .filter(recipe -> recipe.getDishType().equals(dishType))
                .collect(Collectors.toList());
    }

    //==수정 로직==//
    @Override
    public void changeName(Long recipeId, String name) {
        store.get(recipeId).changeName(name);
    }

    @Override
    public void changePrice(Long recipeId, Integer price) {
        store.get(recipeId).changePrice(price);
    }

    @Override
    public void changeUnitPrice(Long recipeId, Double unitPrice) {
        store.get(recipeId).changeUnitPrice(unitPrice);
    }

    @Override
    public void changeWeight(Long recipeId, Double weight) {
        store.get(recipeId).changeWeight(weight);
    }

    @Override
    public void changeDishType(Long recipeId, DishType dishType) {
        store.get(recipeId).changeDishType(dishType);
    }

    @Override
    public void addIngredient(Long recipeId, Ingredient ingredient) {
        store.get(recipeId).addIngredient(ingredient);
    }

    @Override
    public void removeIngredient(Long recipeId, Ingredient ingredient) {
        store.get(recipeId).removeIngredient(ingredient);
    }

    @Override
    public void changeNotes(Long recipeId, String notes) {
        store.get(recipeId).changeNotes(notes);
    }

    //==삭제 로직==//
    @Override
    public void removeRecipe(Recipe recipe) {
        store.remove(recipe.getId());
    }

    @Override
    public void removeById(Long id) {
        store.remove(id);
    }
}