package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateRecipeDTO;
import daepoid.stockManager.dto.EditRecipeDTO;
import daepoid.stockManager.service.MemberService;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final MemberService memberService;

    @GetMapping("/new")
    public String createRecipeForm(@ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO) {
        log.info("Create Recipe Form");
        return "recipe/createRecipeForm";
    }

    @PostMapping("/new")
    public String createRecipe(@Valid @ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("login Error = {}", bindingResult);
            return "recipe/createRecipeForm";
        }

        // Recipe 생성
        Recipe recipe = Recipe.builder()
                .recipeNumber(createRecipeDTO.getRecipeNumber())
                .name(createRecipeDTO.getName())
                .price(createRecipeDTO.getPrice())
                .weight(createRecipeDTO.getWeight())
                .dishType(createRecipeDTO.getDishType())
                .cost(0.0)
                .netIncome(createRecipeDTO.getPrice() - 0.0)
//                .ingredients()
                .notes(createRecipeDTO.getNotes())
                .build();

        // Recipe RecipeService를 통해 DB에 등록
        recipeService.saveRecipe(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/{recipeId}/edit")
    public String editRecipeForm(@PathVariable("recipeId") Long recipeId, Model model) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        model.addAttribute("editRecipeDTO", new EditRecipeDTO(recipe));
        return "recipe/editRecipeForm";
    }

    @PostMapping("/{recipeId}/edit")
    public String editRecipe(@PathVariable("recipeId") Long recipeId,
                             @Valid @ModelAttribute("editRecipeDTO") EditRecipeDTO editRecipeDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "recipe/editRecipeForm";
        }

        recipeService.changeRecipeNumber(recipeId, editRecipeDTO.getRecipeNumber());
        recipeService.changeRecipeName(recipeId, editRecipeDTO.getName());
        recipeService.changePrice(recipeId, editRecipeDTO.getPrice());
        recipeService.changeWeight(recipeId, editRecipeDTO.getWeight());
        recipeService.changeDishType(recipeId, editRecipeDTO.getDishType());
        recipeService.changeNotes(recipeId, editRecipeDTO.getNotes());

        return "redirect:/recipes";
    }

    @GetMapping("")
    public String recipeList(Model model) {
        model.addAttribute("recipes", recipeService.findRecipes());
        return "recipe/recipeList";
    }
}
