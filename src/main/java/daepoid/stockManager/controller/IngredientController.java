package daepoid.stockManager.controller;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.item.CreateIngredientDTO;
import daepoid.stockManager.dto.item.EditIngredientDTO;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.ItemService;
import daepoid.stockManager.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipes/{recipeId}/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final ItemService itemService;

    /**
     * 재료 리스트
     * @param recipeId
     * @param model
     * @param request
     * @return
     */
    @GetMapping("")
    public String ingredientsList(@PathVariable("recipeId") Long recipeId, Model model, HttpServletRequest request) {
        model.addAttribute("ingredients", ingredientService.findByRecipe(recipeId));
        return "ingredients/ingredientList";
    }

    /**
     * 레시피 재료 정보 생성
     * @param recipeId
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String createIngredientsForm(@PathVariable("recipeId") Long recipeId, Model model) {
        model.addAttribute("createIngredientDTO", new CreateIngredientDTO());
        model.addAttribute("items", itemService.findItems());
        return "ingredients/createIngredientForm";
    }

    @PostMapping("/new")
    public String createIngredients(@PathVariable("recipeId") Long recipeId,
                                    @Valid @ModelAttribute("createIngredientDTO") CreateIngredientDTO createIngredientDTO,
                                    BindingResult bindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/createIngredientForm";
        }

        // 레시피에 같은 재료가 들어가는 것으로 선택된 경우 문제가 있다고 판단한다.
        Recipe recipe = recipeService.findRecipe(recipeId);
        Item item = itemService.findItem(createIngredientDTO.getItemId());
        if(recipe.hasIngredient(item.getName())) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/createIngredientForm";
        }

        Ingredient ingredient = Ingredient.builder()
                .item(item)
                .name(item.getName())
                .quantity(createIngredientDTO.getQuantity())
                .unitType(createIngredientDTO.getUnitType())
                .unitPrice(createIngredientDTO.getUnitPrice())
                .loss(createIngredientDTO.getLoss())
                .cost(createIngredientDTO.getQuantity() * createIngredientDTO.getUnitPrice())
                .recipe(recipe)
                .build();
        ingredientService.saveIngredient(ingredient);

        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}";
    }

    /**
     * 레시피 재료 정보 수정 및 레시피 재료 정보 보기
     * @param recipeId
     * @param ingredientId
     * @param model
     * @return
     */
    @GetMapping("/{ingredientId}")
    public String editIngredientsForm(@PathVariable("recipeId") Long recipeId,
                                      @PathVariable("ingredientId") Long ingredientId,
                                      Model model) {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        model.addAttribute("editIngredientDTO", new EditIngredientDTO(ingredient));
        model.addAttribute("items", itemService.findItems());
        return "ingredients/editIngredientForm";
    }

    @PostMapping("/{ingredientId}")
    public String editIngredients(@PathVariable("recipeId") Long recipeId,
                                  @PathVariable("ingredientId") Long ingredientId,
                                  @Valid @ModelAttribute("editIngredientDTO") EditIngredientDTO editIngredientDTO,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/editIngredientForm";
        }

        Item item = itemService.findItem(editIngredientDTO.getItemId());

        // 수정 시 변경하기 위해 선택한 재고(editIngredientDTO.getItem())가
        // 이미 존재하는 경우 변경할 수 없어야 한다.
        Ingredient duplicateIngredient = recipeService.findRecipe(recipeId).getIngredients().stream()
                .filter(ingredient -> ingredient.getItem().equals(item) && !ingredient.getId().equals(ingredientId))
                .findAny()
                .orElse(null);

        if(duplicateIngredient != null) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/editIngredientForm";
        }

        ingredientService.changeItem(ingredientId, item);
        ingredientService.changeName(ingredientId, item.getName());

        ingredientService.changeQuantity(ingredientId, editIngredientDTO.getQuantity());
        ingredientService.changeUnitType(ingredientId, editIngredientDTO.getUnitType());
        ingredientService.changeUnitPrice(ingredientId, editIngredientDTO.getUnitPrice());
        ingredientService.changeLoss(ingredientId, editIngredientDTO.getLoss());
        ingredientService.updateCost(ingredientId);

        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}";
    }
}
