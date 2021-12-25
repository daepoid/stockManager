package daepoid.stockManager.controller;


import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.dto.CreateIngredientDTO;
import daepoid.stockManager.dto.LoginMemberDTO;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.MemberService;
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
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final MemberService memberService;

    @GetMapping("/recipes/{recipeId}/ingredients")
    public String ingredientsList(@PathVariable("recipeId") Long recipeId, Model model, HttpServletRequest request) {
        model.addAttribute("ingredients", ingredientService.findIngredientsByRecipe(recipeId));
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Member member = memberService.findMemberByLoginId(loginId);

        if(member.getGradeType().equals(GradeType.CEO) || member.getGradeType().equals(GradeType.MANAGER)) {
            return "ingredients/authorize/ingredientList";
        }
        return "ingredients/non-authorize/ingredientList";
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

        Ingredient ingredient = Ingredient.createIngredient(
                createIngredientDTO.getName(),
                createIngredientDTO.getQuantity(),
                createIngredientDTO.getUnitType(),
                createIngredientDTO.getUnitPrice(),
                createIngredientDTO.getLoss()
        );
        ingredient.changeRecipe(recipeService.findRecipe(recipeId));

        Long ingredientId = ingredientService.saveIngredient(ingredient);

        List<Ingredient> ingredients = ingredientService.findIngredientsByRecipe(recipeId);
        Recipe recipe = recipeService.findRecipe(recipeId);
        recipe.changeIngredients(ingredients);

        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}/edit";
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
