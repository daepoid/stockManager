package daepoid.stockManager.controller.management;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.controller.dto.recipe.AddRecipeMenuDTO;
import daepoid.stockManager.controller.dto.recipe.CreateMenuDTO;
import daepoid.stockManager.controller.dto.recipe.EditMenuDTO;
import daepoid.stockManager.service.MenuService;
import daepoid.stockManager.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/menu-management")
@RequiredArgsConstructor
public class MenuManagementController {

    private final MenuService menuService;
    private final RecipeService recipeService;

    @GetMapping("")
    public String menuListForm(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }
        model.addAttribute("menus", menuService.findMenus());
        return "menu-management/menuList";
    }

    @GetMapping("/new")
    public String createMenuForm(@ModelAttribute("createMenuDTO") CreateMenuDTO createMenuDTO,
                                 HttpServletRequest request,
                                 Model model) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }
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
                .name(createMenuDTO.getMenuName())
                .price(0)
                .addedDate(LocalDateTime.now())
                .salesCount(0)
                .build();

        Long savedId = menuService.saveMenu(menu);
        return "redirect:/menu-management";
    }

    @GetMapping("/{menuId}")
    public String editMenuForm(@PathVariable Long menuId, Model model,
                               HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }
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
                                        Model model,
                                        HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }

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

        menuService.addFood(menuId, recipeService.findRecipe(addRecipeMenuDTO.getRecipeId()), addRecipeMenuDTO.getNumberOfFoods());

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
