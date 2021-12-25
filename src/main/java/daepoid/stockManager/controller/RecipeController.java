package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateRecipeDTO;
import daepoid.stockManager.dto.EditRecipeDTO;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.dto.SecurityLoginMemberDTO;
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
        return "redirect:/recipes";
    }

    @GetMapping("/{recipeId}/edit")
    public String editRecipeForm(@PathVariable("recipeId") Long recipeId,
                                 Model model,
                                 HttpServletRequest request) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        model.addAttribute("editRecipeDTO", new EditRecipeDTO(recipe));

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member member = memberService.findMemberByLoginId(loginId);

        if(member.getGradeType().equals(GradeType.CEO) || member.getGradeType().equals(GradeType.MANAGER)) {
            return "recipe/authorize/editRecipeForm";
        }

        return "recipe/non-authorize/editRecipeForm";
    }

    @PostMapping("/{recipeId}/edit")
    public String editRecipe(@PathVariable("recipeId") Long recipeId,
                             @Valid @ModelAttribute("editRecipeDTO") EditRecipeDTO editRecipeDTO,
                             BindingResult bindingResult,
                             HttpServletRequest request) {

//        if(bindingResult.hasErrors()) {
//            log.info("editRecipe = {}", bindingResult);
//            Member member = memberService.findMemberByLoginId(((LoginMemberDTO) request.getSession(false).getAttribute(SessionConst.LOGIN_MEMBER)).getLoginId());
//            if(member.getGradeType().equals(GradeType.CEO) || member.getGradeType().equals(GradeType.MANAGER)) {
//                return "recipe/authorize/editRecipeForm";
//            }
//            return "recipe/non-authorize/editRecipeForm";
//        }
//
//        log.info("request.getRequestURI = {}", request.getRequestURI());

        return "redirect:/recipes";
    }

    @GetMapping("")
    public String recipeList(Model model, HttpServletRequest request) {
        model.addAttribute("recipes", recipeService.findRecipes());
        HttpSession session = request.getSession(false);
        String loginId = (String) session.getAttribute(SessionConst.SECURITY_LOGIN);
        Member member = memberService.findMemberByLoginId(loginId);

        if(member.getGradeType().equals(GradeType.CEO) || member.getGradeType().equals(GradeType.MANAGER)) {
            return "recipe/authorize/recipeList";
        }

        return "recipe/non-authorize/recipeList";
    }
}
