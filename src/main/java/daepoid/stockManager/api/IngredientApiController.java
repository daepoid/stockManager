package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.ingredient.*;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.ItemService;
import daepoid.stockManager.service.RecipeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientApiController {

    private final IngredientService ingredientService;
    private final ItemService itemService;
    private final RecipeService recipeService;

    @GetMapping("")
    public Result ingredientsV1() {

        List<Ingredient> ingredients = ingredientService.findIngredients();

        //엔티티 -> DTO 변환
        List<IngredientDTO> collect = ingredients.stream()
                .map(IngredientDTO::new)
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("")
    public CreateIngredientResponseDTO createIngredientV1(@RequestBody @Valid CreateIngredientRequestDTO requestDTO) {
        Item item = itemService.findItem(requestDTO.getItemId());
        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(requestDTO.getQuantity())
                .unitType(requestDTO.getUnitType())
                .unitPrice(requestDTO.getUnitPrice())
                .loss(requestDTO.getLoss())
                .cost(requestDTO.getCost())
                .recipe(recipeService.findRecipe(requestDTO.getRecipeId()))
                .build();
        ingredient.updateCost();
        ingredient.updateName();
        Long ingredientId = ingredientService.saveIngredient(ingredient);
        return new CreateIngredientResponseDTO(ingredientId);
    }

    @GetMapping("/{ingredientId}")
    public IngredientDTO findIngredientV1(@PathVariable("ingredientId") Long ingredientId) {
        return new IngredientDTO(ingredientService.findIngredient(ingredientId));
    }

    @PutMapping("/{ingredientId}")
    public UpdateIngredientResponseDTO updateIngredientV1(@PathVariable("ingredientId") Long ingredientId,
                                                    @RequestBody @Valid UpdateIngredientRequestDTO requestDTO) {

        ingredientService.changeQuantity(ingredientId, requestDTO.getQuantity());
        ingredientService.changeUnitType(ingredientId, requestDTO.getUnitType());
        ingredientService.changeUnitPrice(ingredientId, requestDTO.getUnitPrice());
        ingredientService.changeLoss(ingredientId, requestDTO.getLoss());

        return new UpdateIngredientResponseDTO(ingredientService.findIngredient(ingredientId));
    }

    @PatchMapping("/{ingredientId}")
    public UpdateIngredientResponseDTO updatePatchIngredientV1(@PathVariable("ingredientId") Long ingredientId,
                                                         @RequestBody @Valid UpdatePatchIngredientRequestDTO requestDTO) {

        if(requestDTO.getQuantity() != null) {
            ingredientService.changeQuantity(ingredientId, requestDTO.getQuantity());
        }
        if(requestDTO.getUnitType() != null) {
            ingredientService.changeUnitType(ingredientId, requestDTO.getUnitType());
        }
        if(requestDTO.getUnitPrice() != null) {
            ingredientService.changeUnitPrice(ingredientId, requestDTO.getUnitPrice());
        }
        if(requestDTO.getLoss() != null) {
            ingredientService.changeLoss(ingredientId, requestDTO.getLoss());
        }

        return new UpdateIngredientResponseDTO(ingredientService.findIngredient(ingredientId));
    }

    @DeleteMapping("/{ingredientId}")
    public DeleteIngredientResponseDTO deleteIngredientV1(@PathVariable("ingredientId") Long ingredientId) {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        ingredientService.deleteIngredient(ingredientId);
        return new DeleteIngredientResponseDTO(ingredient.getId());
    }
}
