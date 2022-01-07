package daepoid.stockManager.controller;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateIngredientDTO;
import daepoid.stockManager.dto.EditIngredientDTO;
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

    @GetMapping("")
    public String ingredientsList(@PathVariable("recipeId") Long recipeId, Model model, HttpServletRequest request) {
        model.addAttribute("ingredients", ingredientService.findByRecipe(recipeId));
        return "ingredients/ingredientList";
    }

    @GetMapping("/new")
    public String createIngredientsForm(@PathVariable("recipeId") Long recipeId,
                                        @ModelAttribute("createIngredientDTO") CreateIngredientDTO createIngredientDTO,
                                        Model model) {
        model.addAttribute("items", itemService.findItems());
        return "ingredients/createIngredientForm";
    }

    @PostMapping("/new")
    public String createIngredients(@PathVariable("recipeId") Long recipeId,
                                    @ModelAttribute("createIngredientDTO") CreateIngredientDTO createIngredientDTO,
                                    RedirectAttributes redirectAttributes) {

        Recipe recipe = recipeService.findRecipe(recipeId);
        Item item = itemService.findItem(createIngredientDTO.getItemId());
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

        recipeService.updateCost(recipe.getId());
        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}/ingredients";
    }

    @GetMapping("/{ingredientId}/edit")
    public String editIngredientsForm(@PathVariable("recipeId") Long recipeId,
                                      @PathVariable("ingredientId") Long ingredientId,
                                      Model model,
                                      HttpServletRequest request) {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        model.addAttribute("editIngredientDTO", new EditIngredientDTO(ingredient));
        model.addAttribute("items", itemService.findItems());
        return "ingredients/editIngredientForm";
    }

    @PostMapping("/{ingredientId}/edit")
    public String editIngredients(@PathVariable("recipeId") Long recipeId,
                                  @PathVariable("ingredientId") Long ingredientId,
                                  @Valid @ModelAttribute("editIngredientDTO") EditIngredientDTO editIngredientDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "ingredients/editIngredientForm";
        }

        Item item = itemService.findItem(editIngredientDTO.getItemId());
        ingredientService.changeItem(ingredientId, item);
        ingredientService.changeName(ingredientId, item.getName());
        ingredientService.changeQuantity(ingredientId, editIngredientDTO.getQuantity());
        ingredientService.changeUnitType(ingredientId, editIngredientDTO.getUnitType());
        ingredientService.changeUnitPrice(ingredientId, editIngredientDTO.getUnitPrice());
        ingredientService.changeLoss(ingredientId, editIngredientDTO.getLoss());
        ingredientService.updateCost(ingredientId);

        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}/ingredients";
    }
}
