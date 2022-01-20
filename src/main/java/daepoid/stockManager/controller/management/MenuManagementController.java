package daepoid.stockManager.controller.management;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.dto.AddRecipeMenuDTO;
import daepoid.stockManager.dto.CreateMenuDTO;
import daepoid.stockManager.dto.EditMenuDTO;
import daepoid.stockManager.service.MenuService;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/menu-management")
@RequiredArgsConstructor
public class MenuManagementController {

    private final MenuService menuService;
    private final RecipeService recipeService;

    @GetMapping("")
    public String menuListForm(Model model) {
        model.addAttribute("menus", menuService.findMenus());
        return "menu-management/menuList";
    }

    @GetMapping("/new")
    public String createMenuForm(@ModelAttribute("createMenuDTO") CreateMenuDTO createMenuDTO) {
        return "menu-management/createMenuForm";
    }

    @PostMapping("/new")
    public String createMenu(@Valid @ModelAttribute("createMenuDTO") CreateMenuDTO createMenuDTO,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "menu-management/createMenuForm";
        }

        // menu 생성
        Menu menu = Menu.builder()
                .name(createMenuDTO.getName())
                .build();

        Long savedId = menuService.saveMenu(menu);
        return "redirect:/menu-management";
    }

    @GetMapping("/{menuId}")
    public String editMenuForm(@PathVariable Long menuId, Model model) {
        model.addAttribute("editMenuDTO", new EditMenuDTO(menuService.findMenu(menuId)));
        return "menu-management/editMenuForm";
    }

    @PostMapping("/{menuId}")
    public String editMenu(@PathVariable Long menuId,
                           @Valid @ModelAttribute("editMenuDTO") EditMenuDTO editMenuDTO,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("recipeList", recipeService.findRecipes());
            return "menu-management/editMenuForm";
        }

        // menu 수정

        redirectAttributes.addAttribute("menuId", menuId);
        return "redirect:/menu-management/{menuId}";
    }

    @GetMapping("/{menuId}/new")
    public String editMenuAddRecipeForm(@PathVariable Long menuId,
                                        Model model) {

        model.addAttribute("addRecipeMenuDTO", new AddRecipeMenuDTO(menuService.findMenu(menuId)));
        model.addAttribute("recipes", recipeService.findRecipes());
        return "menu-management/addRecipeMenuForm";
    }

    @PostMapping("/{menuId}/new")
    public String editMenuAddRecipe(@PathVariable Long menuId,
                                    @Valid @ModelAttribute("addRecipeMenuDTO") AddRecipeMenuDTO addRecipeMenuDTO,
                                    BindingResult bindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("recipes", recipeService.findRecipes());
            return "menu-management/addRecipeMenuForm";
        }

        menuService.addFood(menuId, recipeService.findRecipe(addRecipeMenuDTO.getRecipeId()), addRecipeMenuDTO.getNumberOfFood());

        redirectAttributes.addAttribute("menuId", menuId);
        return "redirect:/menu-management/{menuId}";
    }

    @PostMapping("/{menuId}/{recipeId}/cancel")
    public String cancel(@PathVariable Long menuId,
                         @PathVariable Long recipeId,
                         RedirectAttributes redirectAttributes) {
        // remove
        menuService.removeFood(menuId, recipeService.findRecipe(recipeId));

        redirectAttributes.addAttribute("menuId", menuId);
        return "redirect:/menu-management/{menuId}";
    }
}
