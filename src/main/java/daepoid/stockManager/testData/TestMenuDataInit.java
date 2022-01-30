package daepoid.stockManager.testData;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

//@Component
@RequiredArgsConstructor
public class TestMenuDataInit {

    private final MenuService menuService;

    @PostConstruct
    public void init() {

        LocalDateTime now = LocalDateTime.now();
        menuService.saveMenu(Menu.builder()
                .name("메뉴 1")
                .price(0)
                .addedDate(now)
                .orderCount(0)
                .build());

        menuService.saveMenu(Menu.builder()
                .name("메뉴 2")
                .price(0)
                .addedDate(now)
                .orderCount(0)
                .build());

        menuService.saveMenu(Menu.builder()
                .name("메뉴 3")
                .price(0)
                .addedDate(now)
                .orderCount(0)
                .build());
    }
}
