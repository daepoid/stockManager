package daepoid.stockManager.controller;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.controller.dto.item.CreateIngredientDTO;
import daepoid.stockManager.controller.dto.item.EditIngredientDTO;
import daepoid.stockManager.service.FoodService;
import daepoid.stockManager.service.IngredientService;
import daepoid.stockManager.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/foods/{foodId}/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final FoodService foodService;
    private final IngredientService ingredientService;
    private final ItemService itemService;

    /**
     * 재료 리스트
     * @param foodId
     * @param model
     * @param request
     * @return
     */
    @GetMapping("")
    public String ingredientsList(@PathVariable("foodId") Long foodId, Model model, HttpServletRequest request) {
        model.addAttribute("ingredients", ingredientService.findByFoodId(foodId));
        return "ingredients/ingredientList";
    }

    /**
     * 레시피 재료 정보 생성
     * @param foodId
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String createIngredientsForm(@PathVariable("foodId") Long foodId, Model model) {
        model.addAttribute("createIngredientDTO", new CreateIngredientDTO());
        model.addAttribute("items", itemService.findItems());
        return "ingredients/createIngredientForm";
    }

    @PostMapping("/new")
    public String createIngredients(@PathVariable("foodId") Long foodId,
                                    @Valid @ModelAttribute("createIngredientDTO") CreateIngredientDTO createIngredientDTO,
                                    BindingResult bindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/createIngredientForm";
        }

        // 레시피에 같은 재료가 들어가는 것으로 선택된 경우 문제가 있다고 판단한다.
        Optional<Food> food = foodService.findFood(foodId);
        Optional<Item> item = itemService.findItem(createIngredientDTO.getItemId());

        if(food.isEmpty() || item.isEmpty()) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/createIngredientForm";
        }

        Ingredient ingredient = Ingredient.builder()
                .item(item.get())
                .name(item.get().getName())
                .quantity(createIngredientDTO.getQuantity())
                .unitType(createIngredientDTO.getUnitType())
                .unitPrice(createIngredientDTO.getUnitPrice())
                .loss(createIngredientDTO.getLoss())
                .food(food.get())
                .build();
        ingredientService.saveIngredient(ingredient);

        redirectAttributes.addAttribute("foodId", foodId);
        return "redirect:/foods/{foodId}";
    }

    /**
     * 레시피 재료 정보 수정 및 레시피 재료 정보 보기
     * @param foodId
     * @param ingredientId
     * @param model
     * @return
     */
    @GetMapping("/{ingredientId}")
    public String editIngredientsForm(@PathVariable("foodId") Long foodId,
                                      @PathVariable("ingredientId") Long ingredientId,
                                      Model model,
                                      HttpServletRequest request) {

        Optional<Ingredient> ingredient = ingredientService.findIngredient(ingredientId);
        if(ingredient.isEmpty()) {
            request.getSession(false).invalidate();
            return "home";
        }

        model.addAttribute("editIngredientDTO", new EditIngredientDTO(ingredient.get()));
        model.addAttribute("items", itemService.findItems());
        return "ingredients/editIngredientForm";
    }

    @PostMapping("/{ingredientId}")
    public String editIngredients(@PathVariable("foodId") Long foodId,
                                  @PathVariable("ingredientId") Long ingredientId,
                                  @Valid @ModelAttribute("editIngredientDTO") EditIngredientDTO editIngredientDTO,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/editIngredientForm";
        }

        Optional<Item> item = itemService.findItem(editIngredientDTO.getItemId());
        if(item.isEmpty()) {
            model.addAttribute("items", itemService.findItems());
            return "ingredients/editIngredientForm";
        }

        ingredientService.changeQuantity(ingredientId, editIngredientDTO.getQuantity());
        ingredientService.changeUnitType(ingredientId, editIngredientDTO.getUnitType());

        redirectAttributes.addAttribute("foodId", foodId);
        return "redirect:/foods/{foodId}";
    }
}
