package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.ingredient.*;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.ItemService;
import daepoid.stockManager.service.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = {"재료 관리 API"})
@RequiredArgsConstructor
public class IngredientApiController {

    private final IngredientService ingredientService;
    private final ItemService itemService;
    private final RecipeService recipeService;

    @GetMapping("/v1/ingredients")
    @ApiOperation(value="전체 재료 조회", notes="레시피에 할당된 재료들을 일괄적으로 조회")
    public Result ingredientsV1() {
        List<Ingredient> ingredients = ingredientService.findIngredients();
        //엔티티 -> DTO 변환
        List<IngredientDTO> IngredientDTOs = ingredients.stream()
                .map(IngredientDTO::new)
                .collect(Collectors.toList());
        return new Result(IngredientDTOs);
    }

    @GetMapping("/v2/ingredients")
    @ApiOperation(value="전체 재료 조회", notes="레시피에 할당된 재료들을 일괄적으로 조회")
    public Result ingredientsV2(@RequestBody @Valid PagingIngredientRequestDTO requestDTO) {
        List<Ingredient> ingredients;

        if(requestDTO.getFirstResult() == null) {
            ingredients = ingredientService.findIngredients(requestDTO.getMaxResult());
        } else {
            ingredients = ingredientService.findIngredients(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        //엔티티 -> DTO 변환
        List<IngredientDTO> IngredientDTOs = ingredients.stream()
                .map(IngredientDTO::new)
                .collect(Collectors.toList());
        return new Result(IngredientDTOs);
    }

    @PostMapping("/v1/ingredients")
    @ApiOperation(value="재료 생성", notes="재고 정보를 받아와 레시피에 들어가는 재료의 정보를 생성하고 재료의 아이디를 반환")
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

    @GetMapping("/v1/ingredients/{ingredientId}")
    @ApiOperation(value="재료 조회", notes="재료 아이디를 이용하여 재료의 정보를 반환")
    public IngredientDTO findIngredientV1(@PathVariable("ingredientId") Long ingredientId) {
        return new IngredientDTO(ingredientService.findIngredient(ingredientId));
    }

    @PutMapping("/v1/ingredients/{ingredientId}")
    @ApiOperation(value="재료 정보 수정 - PUT", notes="재료 아이디를 이용하여 재료 정보 수정")
    public UpdateIngredientResponseDTO updateIngredientV1(@PathVariable("ingredientId") Long ingredientId,
                                                          @RequestBody @Valid UpdateIngredientRequestDTO requestDTO) {
        ingredientService.changeQuantity(ingredientId, requestDTO.getQuantity());
        ingredientService.changeUnitType(ingredientId, requestDTO.getUnitType());
        ingredientService.changeUnitPrice(ingredientId, requestDTO.getUnitPrice());
        ingredientService.changeLoss(ingredientId, requestDTO.getLoss());

        return new UpdateIngredientResponseDTO(ingredientService.findIngredient(ingredientId));
    }

    @PatchMapping("/v1/ingredients/{ingredientId}")
    @ApiOperation(value="재료 정보 수정 - PATCH", notes="재료 아이디를 이용하여 재료 정보 수정")
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

    @DeleteMapping("/v1/ingredients/{ingredientId}")
    @ApiOperation(value="재료 정보 삭제", notes="재료 아이디를 이용하여 재료 정보 삭제")
    public DeleteIngredientResponseDTO deleteIngredientV1(@PathVariable("ingredientId") Long ingredientId) {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        ingredientService.deleteIngredient(ingredientId);
        return new DeleteIngredientResponseDTO(ingredient.getId());
    }
}
