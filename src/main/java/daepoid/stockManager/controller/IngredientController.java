package daepoid.stockManager.controller;


import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateIngredientDTO;
import daepoid.stockManager.dto.EditIngredientDTO;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/recipes/{recipeId}/ingredients")
    public String ingredientsList(@PathVariable("recipeId") Long recipeId, Model model, HttpServletRequest request) {
        model.addAttribute("ingredients", ingredientService.findByRecipe(recipeId));
        return "ingredients/ingredientList";
    }

    @GetMapping("/recipes/{recipeId}/ingredients/new")
    public String createIngredientsForm(@PathVariable("recipeId") Long recipeId,
                                        @ModelAttribute("createIngredientDTO") CreateIngredientDTO createIngredientDTO) {
        return "ingredients/createIngredientForm";
    }

    @PostMapping("/recipes/{recipeId}/ingredients/new")
    public String createIngredients(@PathVariable("recipeId") Long recipeId,
                                    @Valid @ModelAttribute("createIngredientDTO") CreateIngredientDTO createIngredientDTO,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "ingredients/createIngredientForm";
        }

        Recipe recipe = recipeService.findRecipe(recipeId);
        Ingredient ingredient = Ingredient.builder()
                .name(createIngredientDTO.getName())
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
        return "redirect:/recipes/{recipeId}/edit";
    }

    // ingredient는 recipe에 종속적이다. 따라서 저장된 ingredient를
    @GetMapping("/recipes/{recipeId}/ingredients/{ingredientId}/edit")
    public String editIngredientsForm(@PathVariable("recipeId") Long recipeId,
                                      @PathVariable("ingredientId") Long ingredientId,
                                      Model model) {

        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        model.addAttribute("editIngredientDTO", new EditIngredientDTO(ingredient));
        return "ingredients/editIngredientForm";
    }

    @PostMapping("/recipes/{recipeId}/ingredients/{ingredientId}/edit")
    public String editIngredients(@PathVariable("recipeId") Long recipeId,
                                  @PathVariable("ingredientId") Long ingredientId,
                                  @Valid @ModelAttribute("editIngredientDTO") EditIngredientDTO editIngredientDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "ingredients/editIngredientForm";
        }

        log.info("editIngredientDTO = {}", editIngredientDTO);

//        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        ingredientService.changeName(ingredientId, editIngredientDTO.getName());
        ingredientService.changeQuantity(ingredientId, editIngredientDTO.getQuantity());
        ingredientService.changeUnitType(ingredientId, editIngredientDTO.getUnitType());
        ingredientService.changeUnitPrice(ingredientId, editIngredientDTO.getUnitPrice());
        ingredientService.changeLoss(ingredientId, editIngredientDTO.getLoss());
        ingredientService.updateCost(ingredientId);

        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}/ingredients";
    }

}
