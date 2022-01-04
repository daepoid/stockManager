package daepoid.stockManager.service;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaRecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final JpaRecipeRepository recipeRepository;

    //==생성 로직==//
    @Transactional
    public Long saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    //==조회 로직==//
    public Recipe findRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId).orElse(null);
    }

    public List<Recipe> findRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe findRecipesByName(String name) {
        return recipeRepository.findByName(name).orElse(null);
    }

    public List<Recipe> findRecipeByPrice(Integer price) {
        return recipeRepository.findByPrice(price);
    }

    public List<Recipe> findRecipesByWeight(Double weight) {
        return recipeRepository.findByWeight(weight);
    }

    public List<Ingredient> findIngredientsByRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        return recipe.getIngredients();
    }

    public List<Recipe> findRecipesByIngredient(Ingredient ingredient) {
        return recipeRepository.findByIngredient(ingredient);
    }

    public List<Recipe> findRecipesByDishType(DishType dishType) {
        return recipeRepository.findByDishType(dishType);
    }


    //==수정 로직==//
    @Transactional
    public void changeRecipeNumber(Long recipeId, String recipeNumber) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if(recipe != null) {
            recipe.changeRecipeNumber(recipeNumber);
        }
    }

    @Transactional
    public void changeRecipeName(Long recipeId, String name) {
        recipeRepository.changeName(recipeId, name);
    }

    @Transactional
    public void changePrice(Long recipeId, Integer price) {
        recipeRepository.changePrice(recipeId, price);
    }

    @Transactional
    public void changeWeight(Long recipeId, Double weight) {
        recipeRepository.changeWeight(recipeId, weight);
    }

    @Transactional
    public void changeIngredient(Long recipeId, List<Ingredient> ingredients) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.changeIngredients(ingredients);
    }

    @Transactional
    public void addIngredient(Long recipeId, Ingredient ingredient) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.getIngredients().add(ingredient);
//        recipeRepository.addIngredient(recipeId, ingredient);
    }

    @Transactional
    public void removeIngredient(Long recipeId, Ingredient ingredient) {
        recipeRepository.removeIngredient(recipeId, ingredient);
    }

    @Transactional
    public void changeDishType(Long recipeId, DishType dishType) {
        recipeRepository.changeDishType(recipeId, dishType);
    }

    @Transactional
    public void updateCost(Long recipeId) {
        recipeRepository.updateCost(recipeId);
//        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
//        if(recipe != null) {
//            recipe.updateCost();
//        }
    }

    @Transactional
    public void changeNotes(Long recipeId, String notes) {
        recipeRepository.changeNotes(recipeId, notes);
    }

    //==삭제 로직==//

}
