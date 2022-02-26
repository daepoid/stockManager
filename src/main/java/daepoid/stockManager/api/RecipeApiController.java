package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.recipe.*;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = {"레시피 관리 API"})
@RequiredArgsConstructor
public class RecipeApiController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/v1/recipes")
    @ApiOperation(value="전체 레시피 조회", notes="전체 레시피 정보를 반환")
    public Result findRecipesV1() {
        List<Recipe> recipes = recipeService.findRecipes();
        //엔티티 -> DTO 변환
        List<RecipeDTO> RecipeDTOs = recipes.stream()
                .map(RecipeDTO::new)
                .collect(Collectors.toList());
        return new Result(RecipeDTOs);
    }

    @GetMapping("/v2/recipes")
    @ApiOperation(value="전체 레시피 조회", notes="전체 레시피 정보를 반환")
    public Result findRecipesV2(@RequestBody @Valid PagingRecipeRequestDTO requestDTO) {
        List<Recipe> recipes;

        if(requestDTO.getFirstResult() == null) {
            recipes = recipeService.findRecipes(requestDTO.getMaxResult());
        } else {
            recipes = recipeService.findRecipes(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        //엔티티 -> DTO 변환
        List<RecipeDTO> RecipeDTOs = recipes.stream()
                .map(RecipeDTO::new)
                .collect(Collectors.toList());
        return new Result(RecipeDTOs);
    }

    @GetMapping("/v3/recipes")
    @ApiOperation(value="전체 레시피 조회", notes="전체 레시피 정보를 반환")
    public Result findRecipesV3(@RequestParam("firstResult") Integer firstResult,
                                @RequestParam("maxResult") Integer maxResult) {
        List<Recipe> recipes;

        if(firstResult == null) {
            recipes = recipeService.findRecipes(maxResult);
        } else {
            recipes = recipeService.findRecipes(firstResult, maxResult);
        }

        //엔티티 -> DTO 변환
        List<RecipeDTO> RecipeDTOs = recipes.stream()
                .map(RecipeDTO::new)
                .collect(Collectors.toList());
        return new Result(RecipeDTOs);
    }

    @PostMapping("/v1/recipes")
    @ApiOperation(value="레시피 생성", notes="새로운 레시피 생성 후 레시피 아이디를 반환")
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

    @GetMapping("/v1/recipes/{recipeId}")
    @ApiOperation(value="레시피 조회", notes="레시피 아이디로 레시피를 조회하여 반환")
    public RecipeDTO findRecipeV1(@PathVariable("recipeId") Long recipeId) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        if(recipe == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        return new RecipeDTO(recipe);
    }

    @PutMapping("/v1/recipes/{recipeId}")
    @ApiOperation(value="레시피 수정", notes="레시피 정보를 수정하고 수정된 레시피의 정보를 반환")
    public UpdateRecipeResponseDTO updateRecipeV1(@PathVariable("recipeId") Long recipeId,
                                                  @RequestBody @Valid UpdateRecipeRequestDTO requestDTO) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        if(recipe == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

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

    @PatchMapping("/v1/recipes/{recipeId}")
    @ApiOperation(value="레시피 수정", notes="레시피 정보를 수정하고 수정된 레시피의 정보를 반환")
    public UpdateRecipeResponseDTO updatePatchRecipeV1(@PathVariable("recipeId") Long recipeId,
                                                       @RequestBody @Valid UpdatePatchRecipeRequestDTO requestDTO) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        if(recipe == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

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

    @DeleteMapping("/v1/recipes/{recipeId}")
    @ApiOperation(value="레시피 삭제", notes="레시피의 아이디로 레시피의 정보를 삭제")
    public DeleteRecipeResponseDTO deleteRecipeV1(@PathVariable("recipeId") Long recipeId) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        if(recipe == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        recipeService.removeRecipe(recipe);
        return new DeleteRecipeResponseDTO(recipe.getId());
    }
}
