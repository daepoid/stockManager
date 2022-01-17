package daepoid.stockManager.testData;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestMenuDataInit {

    private final MenuService menuService;

    @PostConstruct
    public void init() {

        menuService.saveMenu(Menu.builder()
                .name("메뉴 1")
                .build());

        menuService.saveMenu(Menu.builder()
                .name("메뉴 2")
                .build());

        menuService.saveMenu(Menu.builder()
                .name("메뉴 3")
                .build());
    }
}
