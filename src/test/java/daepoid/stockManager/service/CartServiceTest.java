package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.CartRepository;
import daepoid.stockManager.repository.MenuRepository;
import daepoid.stockManager.repository.jpa.JpaCartRepository;
import daepoid.stockManager.repository.jpa.JpaMenuRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    CartService cartService;

    @Test
    void createCart() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartService.createCart(cart);

        assertThat(cart.getId()).isEqualTo(cartId);
    }

    @Test
    void findCart() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartService.createCart(cart);

        assertThat(cartService.findCart(cartId)).isEqualTo(cart);
        assertThat(cartService.findCart(cartId).getId()).isEqualTo(cartId);
    }

    @Test
    void findCarts() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartService.createCart(cart);

        assertThat(cartService.findCarts().contains(cart)).isTrue();
        assertThat(cartService.findCarts().stream()
                .filter(c -> c.getId().equals(cartId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void addMenu() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartService.createCart(cart);

        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(numberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderCount = 123;
        cartService.addMenu(cartId, menu.getId(), orderCount);

        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(orderCount);
    }

    @Test
    void addMenus() {
        String menuName1 = "menu name";
        Set<Recipe> menuFoods1 = new HashSet<>();
        int menuPrice1 = 123;
        Map<Long, Integer> numberOfFood1 = new HashMap<>();
        LocalDateTime menuAddedDate1 = LocalDateTime.now();
        int menuSalesCount1 = 123;

        Menu menu1 = Menu.builder()
                .name(menuName1)
                .foods(menuFoods1)
                .price(menuPrice1)
                .numberOfFood(numberOfFood1)
                .addedDate(menuAddedDate1)
                .salesCount(menuSalesCount1)
                .build();
        em.persist(menu1);

        String menuName2 = "menu name";
        Set<Recipe> menuFoods2 = new HashSet<>();
        int menuPrice2 = 123;
        Map<Long, Integer> numberOfFood2 = new HashMap<>();
        LocalDateTime menuAddedDate2 = LocalDateTime.now();
        int menuSalesCount2 = 123;

        Menu menu2 = Menu.builder()
                .name(menuName2)
                .foods(menuFoods2)
                .price(menuPrice2)
                .numberOfFood(numberOfFood2)
                .addedDate(menuAddedDate2)
                .salesCount(menuSalesCount2)
                .build();
        em.persist(menu2);

        int orderCount1 = 123;
        int orderCount2 = 456;
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        Long cartId = cartService.createCart(cart);

        Map<Long, Integer> newNumberOfMenus = new HashMap<>();
        newNumberOfMenus.put(menu1.getId(), orderCount1);
        newNumberOfMenus.put(menu2.getId(), orderCount2);

        cartService.addMenus(cartId, newNumberOfMenus);

        assertThat(cart.getNumberOfMenus().get(menu1.getId())).isEqualTo(orderCount1);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu1.getId())).isEqualTo(orderCount1);

        assertThat(cart.getNumberOfMenus().get(menu2.getId())).isEqualTo(orderCount2);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu2.getId())).isEqualTo(orderCount2);
    }

    @Test
    void removeMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(numberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderCount = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), orderCount);

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        Long cartId = cartService.createCart(cart);

        // menu가 존재하는 상태
        assertThat(cart.getNumberOfMenus().get(menu.getId())).isEqualTo(orderCount);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(orderCount);

        // removeMenu를 통해 menu를 제거
        cartService.removeMenu(cartId, menu.getId());

        // menu가 제거된 상태
        assertThat(cart.getNumberOfMenus().get(menu.getId())).isNull();
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isNull();
    }

    @Test
    void clearCart() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(numberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderCount = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), orderCount);

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        Long cartId = cartService.createCart(cart);

        // cart에 담긴 정보들이 있는 상태
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(orderCount);

        // cart를 비운다.
        cartService.clearCart(cartId);

        // cart에 내용물이 비워져있는 상태
        assertThat(cartService.findCart(cartId).getNumberOfMenus().containsKey(menu.getId())).isFalse();
    }
}