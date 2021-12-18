package daepoid.stockManager.controller;

import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateRecipeDTO;
import daepoid.stockManager.dto.EditRecipeDTO;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/new")
    public String createRecipeForm(@ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO) {
        return "recipe/createRecipeForm";
    }

    @PostMapping("/new")
    public String createRecipe(@Valid @ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("login Error = {}", bindingResult);
            return "recipe/createRecipeForm";
        }
        return "redirect:/recipes";
    }

    @GetMapping("/{recipeId}/edit")
    public String editRecipeForm(@PathVariable("recipeId") Long recipeId,
                                 Model model) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        log.info("recipe = {}", recipe);
        model.addAttribute("editRecipeDTO", new EditRecipeDTO(recipe));
        return "recipe/editRecipeForm";
    }

    @PostMapping("/{recipeId}/edit")
    public String editRecipe(@PathVariable("recipeId") Long recipeId,
                             @Valid @ModelAttribute("editRecipeDTO") EditRecipeDTO editRecipeDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("editRecipe = {}", bindingResult);
            return "recipe/editRecipeForm";
        }

        return "redirect:/recipes";
    }

    @GetMapping("")
    public String recipeList(Model model) {
        model.addAttribute("recipes", recipeService.findRecipes());
        return "recipe/recipeList";
    }
}
