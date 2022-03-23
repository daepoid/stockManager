package daepoid.stockManager.controller;

import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.controller.dto.recipe.CreateRecipeDTO;
import daepoid.stockManager.controller.dto.recipe.EditRecipeDTO;
import daepoid.stockManager.domain.search.RecipeSearch;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;

@Slf4j
@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    /**
     * 레시피 리스트
     * @param model
     * @return
     */
    @GetMapping("")
    public String recipeList(@ModelAttribute("recipeSearch")RecipeSearch recipeSearch,
                             Model model) {
        model.addAttribute("recipes", recipeService.findByRecipeSearch(recipeSearch));
//        model.addAttribute("recipes", recipeService.findRecipes());
        return "recipes/recipeList";
    }

    /**
     * 레시피 생성
     * @param createRecipeDTO
     * @return
     */
    @GetMapping("/new")
    public String createRecipeForm(@ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO) {
        return "recipes/createRecipeForm";
    }

    @PostMapping("/new")
    public String createRecipe(@Valid @ModelAttribute("createRecipeDTO") CreateRecipeDTO createRecipeDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "recipes/createRecipeForm";
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

        recipeService.saveRecipe(recipe);
        return "redirect:/recipes";
    }

    /**
     * 레시피 수정 및 레시피 정보 보기
     * @param recipeId
     * @param model
     * @return
     */
    @GetMapping("/{recipeId}")
    public String editRecipeForm(@PathVariable("recipeId") Long recipeId, Model model) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        recipeService.updateCost(recipeId);
        model.addAttribute("editRecipeDTO", new EditRecipeDTO(recipe));
        return "recipes/editRecipeForm";
    }

    @PostMapping("/{recipeId}")
    public String editRecipe(@PathVariable("recipeId") Long recipeId,
                             @Valid @ModelAttribute("editRecipeDTO") EditRecipeDTO editRecipeDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "recipes/editRecipeForm";
        }

        recipeService.changeRecipeNumber(recipeId, editRecipeDTO.getRecipeNumber());
        recipeService.changeRecipeName(recipeId, editRecipeDTO.getName());
        recipeService.changePrice(recipeId, editRecipeDTO.getPrice());
        recipeService.changeWeight(recipeId, editRecipeDTO.getWeight());
        recipeService.changeDishType(recipeId, editRecipeDTO.getDishType());
        recipeService.changeNotes(recipeId, editRecipeDTO.getNotes());
        recipeService.updateCost(recipeId);

        return "redirect:/recipes";
    }

    @GetMapping("/{recipeId}/image")
    public String editRecipeImageForm(@PathVariable("recipeId") Long recipeId, Model model) {
        return "recipes/editRecipeImageForm";
    }

    @PostMapping("/{recipeId}/image")
    public String editRecipeImage(@PathVariable("recipeId") Long recipeId,
                                  @RequestParam("file") MultipartFile file) {

        // 파일 저장
        try {
            String originalFilename = file.getOriginalFilename();
            String filename = originalFilename;
            /* 실행되는 위치의 'file' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }

            String filePath = savePath + "\\" + filename;
            file.transferTo(new File(filePath));

            log.info("=========================================");
            log.info("filePath = {}", filePath);
            log.info("=========================================");
            recipeService.changeImgUrl(recipeId, filePath);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return "redirect:/recipes";
    }

    @PostMapping("/{recipeId}/{ingredientId}/cancel")
    public String cancel(@PathVariable("recipeId") Long recipeId,
                         @PathVariable("ingredientId") Long ingredientId,
                         RedirectAttributes redirectAttributes) {

        ingredientService.deleteIngredient(ingredientId);
        redirectAttributes.addAttribute("recipeId", recipeId);
        return "redirect:/recipes/{recipeId}";
    }
}
