package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateRecipeDTO;
import daepoid.stockManager.dto.EditRecipeDTO;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.service.MemberService;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        log.info("recipe ingredients = {}", recipe.getIngredients());
        log.info("recipe ingredients size = {}", recipe.getIngredients().size());
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
    public String recipeList(Model model, HttpServletRequest request) {
        model.addAttribute("recipes", recipeService.findRecipes());

        LoginMemberDTO loginMemberDTO = (LoginMemberDTO) request.getSession(false).getAttribute(SessionConst.LOGIN_MEMBER);
        Member member = memberService.findMemberByLoginId(loginMemberDTO.getLoginId());
        if(member.getGradeType().equals(GradeType.CEO) || member.getGradeType().equals(GradeType.MANAGER)) {
            return "recipe/adminRecipeList";
        }
        return "recipe/recipeList";
//        return "recipe/adminRecipeList";
    }
}
