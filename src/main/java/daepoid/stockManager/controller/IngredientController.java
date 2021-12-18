package daepoid.stockManager.controller;


import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateIngredientDTO;
import daepoid.stockManager.dto.CreateItemDTO;
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

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @GetMapping("/recipes/{recipeId}/ingredients")
    public String ingredientsList(@PathVariable("recipeId") Long recipeId, Model model) {
        model.addAttribute("ingredients", ingredientService.findIngredients());
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
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "ingredients/createIngredientForm";
        }
        log.info("recipeId = {}", recipeId);
        recipeService.addIngredient(recipeId, Ingredient.createIngredient(
                createIngredientDTO.getName(),
                createIngredientDTO.getQuantity(),
                createIngredientDTO.getUnitType(),
                createIngredientDTO.getUnitPrice(),
                createIngredientDTO.getLoss()
        ));
        log.info("recipeService = {}", recipeService.findRecipe(recipeId).getIngredients());
        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}/ingredients";
    }

    // ingredient는 recipe에 종속적이다. 따라서 저장된 ingredient를
    @GetMapping("/recipes/{recipeId}/ingredients/{ingredientId}/edit")
    public String editIngredientsForm(@PathVariable("recipeId") Long recipeId,
                                      @PathVariable("ingredientId") Long ingredientId,
                                      Model model) {

        return "ingredients/editIngredientForm";
    }

    @PostMapping("/recipes/{recipeId}/ingredients/{ingredientId}/edit")
    public String editIngredients(@PathVariable("recipeId") Long recipeId,
                                  @PathVariable("ingredientId") Long ingredientId,
                                  RedirectAttributes redirectAttributes
                                  ) {
        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}/ingredients";
    }

}
