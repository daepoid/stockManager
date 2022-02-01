package daepoid.stockManager.service;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;
import daepoid.stockManager.repository.jpa.JpaRecipeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
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
        return recipeRepository.findById(recipeId);
    }

    public List<Recipe> findRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe findRecipesByName(String name) {
        return recipeRepository.findByName(name);
    }

    public List<Recipe> findRecipeByPrice(int price) {
        return recipeRepository.findByPrice(price);
    }

    public List<Recipe> findRecipesByWeight(double weight) {
        return recipeRepository.findByWeight(weight);
    }

    public List<Ingredient> findIngredientsByRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId);
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
        Recipe recipe = recipeRepository.findById(recipeId);
        if(recipe != null) {
            recipe.changeRecipeNumber(recipeNumber);
        }
    }

    @Transactional
    public void changeRecipeName(Long recipeId, String name) {
        recipeRepository.changeName(recipeId, name);
    }

    @Transactional
    public void changePrice(Long recipeId, int price) {
        recipeRepository.changePrice(recipeId, price);
    }

    @Transactional
    public void changeWeight(Long recipeId, double weight) {
        recipeRepository.changeWeight(recipeId, weight);
    }

    @Transactional
    public void changeCost(Long recipeId, double cost) {
        recipeRepository.changeCost(recipeId, cost);
    }

    @Transactional
    public void changeIngredient(Long recipeId, List<Ingredient> ingredients) {
        Recipe recipe = recipeRepository.findById(recipeId);
        recipe.changeIngredients(ingredients);
    }

    @Transactional
    public void addIngredient(Long recipeId, Ingredient ingredient) {
        Recipe recipe = recipeRepository.findById(recipeId);
        recipe.getIngredients().add(ingredient);
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
        double sum = recipeRepository.findById(recipeId)
                .getIngredients().stream()
                .mapToDouble(Ingredient::getCost)
                .sum();
        recipeRepository.changeCost(recipeId, sum);
    }

    @Transactional
    public void changeNotes(Long recipeId, String notes) {
        recipeRepository.changeNotes(recipeId, notes);
    }

    //==삭제 로직==//
}
