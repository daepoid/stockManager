//package daepoid.stockManager.service;
//
//import daepoid.stockManager.domain.item.UnitType;
//import daepoid.stockManager.domain.recipe.DishType;
//import daepoid.stockManager.domain.ingredient.Ingredient;
//import daepoid.stockManager.domain.recipe.Recipe;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//class RecipeServiceTest {
//
//    @Autowired
//    private RecipeService recipeService;
//
//    @Test
//    void saveRecipe() {
//    }
//
//    @Test
//    void findRecipe() {
//    }
//
//    @Test
//    void findRecipes() {
//    }
//
//    @Test
//    void findRecipesByName() {
//    }
//
//    @Test
//    void findRecipeByPrice() {
//    }
//
//    @Test
//    void findRecipesByUnitPrice() {
//    }
//
//    @Test
//    void findRecipesByWeight() {
//    }
//
//    @Test
//    void findRecipesByIngredient() {
//    }
//
//    @Test
//    void findRecipesByDishType() {
//    }
//
//    @Test
//    void changeRecipeName() {
//    }
//
//    @Test
//    void changePrice() {
//    }
//
//    @Test
//    void changeUnitPrice() {
//    }
//
//    @Test
//    void changeWeight() {
//    }
//
//    @Test
//    void addIngredient() {
//        Recipe recipe = Recipe.createRecipe("name", 100, 11.1, 1000.1, DishType.BOWL, "notes");
//        recipeService.saveRecipe(recipe);
//        Ingredient ingredient = Ingredient.createIngredient("name", 100, UnitType.kg, 11.1, 0.0);
//        recipeService.addIngredient(recipe.getId(), ingredient);
//
//        System.out.println("recipe.getId() = " + recipe.getId());
//        System.out.println("recipeService.findRecipe(recipe.getId()).getIngredients() = " + recipeService.findRecipe(recipe.getId()).getIngredients().size());
//        Assertions.assertThat(recipeService.findRecipe(recipe.getId()).getIngredients().size()).isEqualTo(1);
//        Assertions.assertThat(recipeService.findRecipe(recipe.getId()).getIngredients().contains(ingredient)).isEqualTo(true);
//    }
//
//    @Test
//    void removeIngredient() {
//    }
//
//    @Test
//    void changeDishType() {
//    }
//
//    @Test
//    void changeNotes() {
//    }
//}