package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.menu.*;
import daepoid.stockManager.domain.food.Menu;
import daepoid.stockManager.domain.food.FoodStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = {"메뉴 관리 API"})
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;
    private final RecipeService recipeService;

    @GetMapping("/v1/menus")
    @ApiOperation(value="전체 메뉴 조회", notes="전체 메뉴 정보를 조회하고 반환")
    public Result findMenusV1() {
        List<Menu> menus = menuService.findMenus();
        //엔티티 -> DTO 변환
        List<MenuDTO> MenuDTOs = menus.stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(MenuDTOs);
    }

    @GetMapping("/v2/menus")
    @ApiOperation(value="전체 메뉴 조회", notes="전체 메뉴 정보를 조회하고 반환")
    public Result findMenusV2(@RequestBody @Valid PagingMenuRequestDTO requestDTO) {
        List<Menu> menus;

        if(requestDTO.getFirstResult() == null) {
            menus = menuService.findMenus(requestDTO.getMaxResult());
        } else {
            menus = menuService.findMenus(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        //엔티티 -> DTO 변환
        List<MenuDTO> MenuDTOs = menus.stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(MenuDTOs);
    }

    @GetMapping("/v3/menus")
    @ApiOperation(value="전체 메뉴 조회", notes="전체 메뉴 정보를 조회하고 반환")
    public Result findMenusV3(@RequestParam("firstResult") Integer firstResult,
                              @RequestParam("maxResult") Integer maxResult) {
        List<Menu> menus;

        if(firstResult == null) {
            menus = menuService.findMenus(maxResult);
        } else {
            menus = menuService.findMenus(firstResult, maxResult);
        }

        //엔티티 -> DTO 변환
        List<MenuDTO> MenuDTOs = menus.stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(MenuDTOs);
    }

    @PostMapping("/v1/menus")
    @ApiOperation(value="메뉴 생성", notes="새로운 메뉴를 생성하고 메뉴 아이디를 반환")
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
                .menuStatus(requestDTO.getFoodStatus())
                .build();
        menu.updatePrice();
        Long menuId = menuService.saveMenu(menu);
        return new CreateMenuResponseDTO(menuId);
    }

    @GetMapping("/v1/menus/popular")
    @ApiOperation(value="인기 메뉴 조회", notes="메뉴 누적 주문 수량이 많은 순으로 메뉴의 정보를 반환")
    public Result popularMenusV1() {
        List<Menu> menus = menuService.findMenus();
        //엔티티 -> DTO 변환
        List<MenuDTO> collect = menus.stream()
                .sorted(Comparator.comparing(Menu::getSalesCount).reversed())
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/v1/menus/new-arrival")
    @ApiOperation(value="신메뉴 조회", notes="메뉴 추가 시간이 최근 순으로 메뉴 정보를 반환")
    public Result newArrivalMenusV1() {
        List<Menu> menus = menuService.findMenus();
        //엔티티 -> DTO 변환
        List<MenuDTO> collect = menus.stream()
                .sorted(Comparator.comparing(Menu::getAddedDate).reversed())
                .map(MenuDTO::new)
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/v1/menus/{menuId}")
    @ApiOperation(value="메뉴 조회", notes="메뉴의 아이디를 이용하여 메뉴 정보를 조회")
    public MenuDTO findMenuV1(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.findMenu(menuId);
        if(menu == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        return new MenuDTO(menu);
    }

    @PutMapping("/v1/menus/{menuId}")
    @ApiOperation(value="메뉴 수정", notes="메뉴의 정보를 수정하고 수정한 메뉴의 정보를 반환")
    public UpdateMenuResponseDTO updateMenuV1(@PathVariable("menuId") Long menuId,
                                              @RequestBody @Valid UpdateMenuRequestDTO requestDTO) {
        Menu menu = menuService.findMenu(menuId);
        if(menu == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        Map<Long, Integer> numberOfFoods = requestDTO.getNumberOfFoods();
        Set<Recipe> foods = new HashSet<>();
        for (Long recipeId : numberOfFoods.keySet()) {
            foods.add(recipeService.findRecipe(recipeId));
        }

        menuService.changeName(menuId, requestDTO.getMenuName());
        menuService.changeFoods(menuId, foods);
        menuService.changeNumberOfFoods(menuId, numberOfFoods);
        menuService.changeSalesCount(menuId, requestDTO.getSalesCount());
        menuService.changeMenuStatus(menuId,  requestDTO.getFoodStatus());

        return new UpdateMenuResponseDTO(menuService.findMenu(menuId));
    }

    @PatchMapping("/v1/menus/{menuId}")
    @ApiOperation(value="메뉴 수정", notes="메뉴의 정보를 수정하고 수정한 메뉴의 정보를 반환")
    public UpdateMenuResponseDTO updatePatchMenuV1(@PathVariable("menuId") Long menuId,
                                                   @RequestBody @Valid UpdatePatchMenuRequestDTO requestDTO) {
        Menu menu = menuService.findMenu(menuId);
        if(menu == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

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

        if(requestDTO.getFoodStatus() != null) {
            menuService.changeMenuStatus(menuId, requestDTO.getFoodStatus());
        }

        return new UpdateMenuResponseDTO(menuService.findMenu(menuId));
    }

    @DeleteMapping("/v1/menus/{menuId}")
    @ApiOperation(value="메뉴 삭제", notes="메뉴의 아이디와 삭제 유형을 이용하여 DB에서 삭제하거나 판매하지 않는 상태로 만든다.")
    public DeleteMenuResponseDTO deleteMenuV1(@PathVariable("menuId") Long menuId,
                                              @RequestBody @Valid DeleteMenuRequestDTO requestDTO) {
        Menu menu = menuService.findMenu(menuId);
        if(menu == null) {
            throw new IllegalArgumentException("잘못된 아이디");
        }

        if(requestDTO.getFoodStatus().equals(FoodStatus.ORDERABLE)) {
            throw new IllegalArgumentException("잘못된 상태");
        }

        menuService.changeMenuStatus(menuId, requestDTO.getFoodStatus());
        return new DeleteMenuResponseDTO(menu.getId());
    }
}
