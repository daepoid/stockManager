package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.menu.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.MenuStatus;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.service.MenuService;
import daepoid.stockManager.service.RecipeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;
    private final RecipeService recipeService;

    @GetMapping("")
    public Result findMenusV1() {
        List<Menu> menus = menuService.findMenus();
        //엔티티 -> DTO 변환
        List<MenuDTO> MenuDTOs = menus.stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(MenuDTOs);
    }

    @PostMapping("")
    public CreateMenuResponseDTO createMenuV1(@RequestBody @Valid CreateMenuRequestDTO requestDTO) {
        recipeService.findRecipes();
        Set<Recipe> foods = new HashSet<>();

        for (Long recipeId : requestDTO.getNumberOfFoods().keySet()) {
            foods.add(recipeService.findRecipe(recipeId));
        }
        Menu menu = Menu.builder()
                .name(requestDTO.getMenuName())
                .foods(foods)
                .price(0)
                .numberOfFoods(requestDTO.getNumberOfFoods())
                .addedDate(LocalDateTime.now())
                .salesCount(requestDTO.getSalesCount())
                .menuStatus(requestDTO.getMenuStatus())
                .build();
        menu.updatePrice();
        Long menuId = menuService.saveMenu(menu);
        return new CreateMenuResponseDTO(menuId);
    }

    @GetMapping("/popular")
    public Result popularMenusV1() {
        List<Menu> menus = menuService.findMenus();
        //엔티티 -> DTO 변환
        List<MenuDTO> collect = menus.stream()
                .sorted(Comparator.comparing(Menu::getSalesCount).reversed())
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/new-arrival")
    public Result newArrivalMenusV1() {
        List<Menu> menus = menuService.findMenus();
        //엔티티 -> DTO 변환
        List<MenuDTO> collect = menus.stream()
                .sorted(Comparator.comparing(Menu::getAddedDate).reversed())
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/{menuId}")
    public MenuDTO findMenuV1(@PathVariable("menuId") Long menuId) {
        return new MenuDTO(menuService.findMenu(menuId));
    }

    @PutMapping("/{menuId}")
    public UpdateMenuResponseDTO updateMenuV1(@PathVariable("menuId") Long menuId,
                                              @RequestBody @Valid UpdateMenuRequestDTO requestDTO) {
        Map<Long, Integer> numberOfFoods = requestDTO.getNumberOfFoods();
        Set<Recipe> foods = new HashSet<>();
        for (Long recipeId : numberOfFoods.keySet()) {
            foods.add(recipeService.findRecipe(recipeId));
        }

        menuService.changeName(menuId, requestDTO.getMenuName());
        menuService.changeFoods(menuId, foods);
        menuService.changeNumberOfFoods(menuId, numberOfFoods);
        menuService.changeSalesCount(menuId, requestDTO.getSalesCount());
        menuService.changeMenuStatus(menuId,  requestDTO.getMenuStatus());

        return new UpdateMenuResponseDTO(menuService.findMenu(menuId));
    }

    @PatchMapping("/{menuId}")
    public UpdateMenuResponseDTO updatePatchMenuV1(@PathVariable("menuId") Long menuId,
                                                   @RequestBody @Valid UpdatePatchMenuRequestDTO requestDTO) {
        if(requestDTO.getNumberOfFoods() != null) {
            Map<Long, Integer> numberOfFoods = requestDTO.getNumberOfFoods();
            Set<Recipe> foods = new HashSet<>();
            for (Long recipeId : numberOfFoods.keySet()) {
                foods.add(recipeService.findRecipe(recipeId));
            }
            menuService.changeFoods(menuId, foods);
            menuService.changeNumberOfFoods(menuId, numberOfFoods);
        }

        if(requestDTO.getMenuName() != null) {
            menuService.changeName(menuId, requestDTO.getMenuName());
        }

        if(requestDTO.getSalesCount() != null) {
            menuService.changeSalesCount(menuId, requestDTO.getSalesCount());
        }

        if(requestDTO.getMenuStatus() != null) {
            menuService.changeMenuStatus(menuId, requestDTO.getMenuStatus());
        }

        return new UpdateMenuResponseDTO(menuService.findMenu(menuId));
    }

    @DeleteMapping("/{menuId}")
    public DeleteMenuResponseDTO deleteMenuV1(@PathVariable("menuId") Long menuId,
                                              @RequestBody @Valid DeleteMenuRequestDTO requestDTO) {
        if(requestDTO.getMenuStatus().equals(MenuStatus.ORDERABLE)) {
            throw new IllegalArgumentException("잘못된 상태");
        }
        menuService.changeMenuStatus(menuId, requestDTO.getMenuStatus());
        return new DeleteMenuResponseDTO(menuId);
    }
}
