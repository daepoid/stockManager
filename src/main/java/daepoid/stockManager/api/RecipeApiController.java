package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.item.*;
import daepoid.stockManager.api.dto.recipe.*;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeApiController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("")
    public Result findRecipesV1() {
        List<Recipe> recipes = recipeService.findRecipes();

        //엔티티 -> DTO 변환
        List<RecipeDTO> collect = recipes.stream()
                .map(RecipeDTO::new)
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("")
    public CreateRecipeResponseDTO createRecipeV1(@RequestBody @Valid CreateRecipeRequestDTO requestDTO) {
        List<Long> ingredientIds = requestDTO.getIngredientIds();
        List<Ingredient> ingredients = new ArrayList<>();

        for (Long ingredientId : ingredientIds) {
            ingredients.add(ingredientService.findIngredient(ingredientId));
        }
        Recipe recipe = Recipe.builder()
                .recipeNumber(requestDTO.getRecipeNumber())
                .name(requestDTO.getRecipeName())
                .price(requestDTO.getPrice())
                .weight(requestDTO.getWeight())
                .dishType(requestDTO.getDishType())
                .ingredients(ingredients)
                .cost(0.0)
                .netIncome(0.0)
                .notes(requestDTO.getNotes())
                .build();

        recipe.updateCost();
        Long recipeId = recipeService.saveRecipe(recipe);
        return new CreateRecipeResponseDTO(recipeId);
    }

    @GetMapping("/{recipeId}")
    public RecipeDTO findRecipe(@PathVariable("recipeId") Long recipeId) {
        return new RecipeDTO(recipeService.findRecipe(recipeId));
    }

    @PutMapping("/{recipeId}")
    public UpdateRecipeResponseDTO updateRecipeV1(@PathVariable("recipeId") Long recipeId,
                                                  @RequestBody @Valid UpdateRecipeRequestDTO requestDTO) {

        List<Long> ingredientIds = requestDTO.getIngredientIds();
        List<Ingredient> ingredients = new ArrayList<>();

        for (Long ingredientId : ingredientIds) {
            ingredients.add(ingredientService.findIngredient(ingredientId));
        }
        recipeService.changeRecipeNumber(recipeId, requestDTO.getRecipeNumber());
        recipeService.changeRecipeName(recipeId, requestDTO.getName());
        recipeService.changePrice(recipeId, requestDTO.getPrice());
        recipeService.changeWeight(recipeId, requestDTO.getWeight());
        recipeService.changeDishType(recipeId, requestDTO.getDishType());
        recipeService.changeIngredients(recipeId, ingredients);
        recipeService.changeNotes(recipeId, requestDTO.getNotes());

        return new UpdateRecipeResponseDTO(recipeService.findRecipe(recipeId));
    }

    @PatchMapping("/{recipeId}")
    public UpdateRecipeResponseDTO updatePatchRecipeV1(@PathVariable("recipeId") Long recipeId,
                                                       @RequestBody @Valid UpdatePatchRecipeRequestDTO requestDTO) {
        if(requestDTO.getRecipeNumber() != null) {
            recipeService.changeRecipeNumber(recipeId, requestDTO.getRecipeNumber());
        }
        if(requestDTO.getName() != null) {
            recipeService.changeRecipeName(recipeId, requestDTO.getName());
        }
        if(requestDTO.getPrice() != null) {
            recipeService.changePrice(recipeId, requestDTO.getPrice());
        }
        if(requestDTO.getWeight() != null) {
            recipeService.changeWeight(recipeId, requestDTO.getWeight());
        }
        if(requestDTO.getDishType() != null) {
            recipeService.changeDishType(recipeId, requestDTO.getDishType());
        }
        if(requestDTO.getIngredientIds() != null) {
            List<Long> ingredientIds = requestDTO.getIngredientIds();
            List<Ingredient> ingredients = new ArrayList<>();

            for (Long ingredientId : ingredientIds) {
                ingredients.add(ingredientService.findIngredient(ingredientId));
            }
            recipeService.changeIngredients(recipeId, ingredients);
        }
        if(requestDTO.getNotes() != null) {
            recipeService.changeNotes(recipeId, requestDTO.getNotes());
        }

        return new UpdateRecipeResponseDTO(recipeService.findRecipe(recipeId));
    }

    @DeleteMapping("/{recipeId}")
    public DeleteRecipeResponseDTO deleteRecipeV1(@PathVariable("recipeId") Long recipeId) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        recipeService.removeRecipe(recipe);
        return new DeleteRecipeResponseDTO(recipe.getId());
    }


}
