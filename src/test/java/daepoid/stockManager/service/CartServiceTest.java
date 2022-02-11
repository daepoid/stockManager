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

    private Menu createMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        return Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(numberOfFoods)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
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

        Menu menu = createMenu();
        em.persist(menu);

        int numberOfFood = 123;
        cartService.addMenu(cartId, menu.getId(), numberOfFood);

        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfFood);
    }

    @Test
    void addMenus() {
        Menu menu1 = createMenu();
        em.persist(menu1);

        Menu menu2 = createMenu();
        em.persist(menu2);

        int numberOfFood1 = 123;
        int numberOfFood2 = 456;
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        Long cartId = cartService.createCart(cart);

        Map<Long, Integer> newNumberOfMenus = new HashMap<>();
        newNumberOfMenus.put(menu1.getId(), numberOfFood1);
        newNumberOfMenus.put(menu2.getId(), numberOfFood2);

        cartService.addMenus(cartId, newNumberOfMenus);

        assertThat(cart.getNumberOfMenus().get(menu1.getId())).isEqualTo(numberOfFood1);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu1.getId())).isEqualTo(numberOfFood1);

        assertThat(cart.getNumberOfMenus().get(menu2.getId())).isEqualTo(numberOfFood2);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu2.getId())).isEqualTo(numberOfFood2);
    }

    @Test
    void removeMenu() {
        Menu menu = createMenu();
        em.persist(menu);

        int numberOfMenu = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), numberOfMenu);
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        Long cartId = cartService.createCart(cart);

        // menu가 존재하는 상태
        assertThat(cart.getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfMenu);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfMenu);

        // removeMenu를 통해 menu를 제거
        cartService.removeMenu(cartId, menu.getId());

        // menu가 제거된 상태
        assertThat(cart.getNumberOfMenus().get(menu.getId())).isNull();
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isNull();
    }

    @Test
    void clearCart() {
        Menu menu = createMenu();
        em.persist(menu);

        int numberOfMenu = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), numberOfMenu);

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        Long cartId = cartService.createCart(cart);

        // cart에 담긴 정보들이 있는 상태
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfMenu);

        // cart를 비운다.
        cartService.clearCart(cartId);

        // cart에 내용물이 비워져있는 상태
        assertThat(cartService.findCart(cartId).getNumberOfMenus().containsKey(menu.getId())).isFalse();
    }
}