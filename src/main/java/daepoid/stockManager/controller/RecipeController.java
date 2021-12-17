package daepoid.stockManager.controller;

import daepoid.stockManager.dto.CreateRecipeDTO;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipe")
    public String createRecipeForm(@ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO) {
        return "recipe/createRecipeForm";
    }

    @PostMapping("/recipe")
    public String createRecipe(@Valid @ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("login Error = {}", bindingResult);
            return "recipe/createRecipeForm";
        }

        return "redirect:/recipes";
    }

    @GetMapping("/recipes")
    public String recipeList(Model model) {
        model.addAttribute("recipes", recipeService.findRecipes());
        return "recipe/recipeList";
    }
}
