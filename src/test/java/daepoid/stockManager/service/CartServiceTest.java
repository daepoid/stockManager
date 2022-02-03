package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.recipe.Menu;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaCartRepository cartRepository;

    @Autowired
    CartService cartService;

    @Autowired
    JpaMenuRepository menuRepository;

    @Test
    void createCart() {
        Cart cart = Cart.builder().build();
        Long cartId = cartService.createCart(cart);

        assertThat(cart.getId()).isEqualTo(cartId);
    }

    @Test
    void findCart() {
        Cart cart = Cart.builder().build();
        Long cartId = cartService.createCart(cart);

        assertThat(cartService.findCart(cartId)).isEqualTo(cart);
    }

    @Test
    void findCarts() {
        Cart cart = Cart.builder().build();
        Long cartId = cartService.createCart(cart);

        assertThat(cartService.findCarts().contains(cart)).isEqualTo(true);
    }

    @Test
    void addMenu() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder()
                .numberOfMenus(numberOfMenus)
                .build();
        Long cartId = cartService.createCart(cart);

        int price = 10000;
        int orderCount = 0;
        int count = 123;
        Map<Long, Integer> new_numberOfMenus = new HashMap<>();

        Menu menu = Menu.builder()
                .name("menu")
                .price(price)
                .addedDate(LocalDateTime.now())
                .orderCount(orderCount)
                .numberOfFood(new_numberOfMenus)
                .build();
        menuRepository.save(menu);

        cartService.addMenu(cartId, menu.getId(), count);

        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(count);
    }

    @Test
    void addMenus() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder()
                .numberOfMenus(numberOfMenus)
                .build();
        Long cartId = cartService.createCart(cart);

        int price = 10000;
        int orderCount = 0;
        int count = 123;
        Map<Long, Integer> new_numberOfMenus = new HashMap<>();

        Menu menu = Menu.builder()
                .name("menu")
                .price(price)
                .addedDate(LocalDateTime.now())
                .orderCount(orderCount)
                .numberOfFood(new_numberOfMenus)
                .build();
        menuRepository.save(menu);

        new_numberOfMenus.put(menu.getId(), count);
        cartService.addMenus(cartId, new_numberOfMenus);

        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(count);
    }

    @Test
    void removeMenu() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder()
                .numberOfMenus(numberOfMenus)
                .build();
        Long cartId = cartService.createCart(cart);

        int price = 10000;
        int orderCount = 0;
        int count = 123;
        Map<Long, Integer> new_numberOfMenus = new HashMap<>();

        Menu menu = Menu.builder()
                .name("menu")
                .price(price)
                .addedDate(LocalDateTime.now())
                .orderCount(orderCount)
                .numberOfFood(new_numberOfMenus)
                .build();
        menuRepository.save(menu);
        cartService.addMenu(cartId, menu.getId(), count);

        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(count);

        cartService.removeMenu(cartId, menu.getId());
        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(null);
    }

    @Test
    void clearCart() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder()
                .numberOfMenus(numberOfMenus)
                .build();
        Long cartId = cartService.createCart(cart);

        int price = 10000;
        int orderCount = 0;
        int count = 123;
        Map<Long, Integer> new_numberOfMenus = new HashMap<>();

        Menu menu = Menu.builder()
                .name("menu")
                .price(price)
                .addedDate(LocalDateTime.now())
                .orderCount(orderCount)
                .numberOfFood(new_numberOfMenus)
                .build();
        menuRepository.save(menu);
        cartService.addMenu(cartId, menu.getId(), count);

        assertThat(cartService.findCart(cartId).getNumberOfMenus().get(menu.getId())).isEqualTo(count);

        cartService.clearCart(cartId);
        assertThat(cartService.findCart(cartId).getNumberOfMenus().containsKey(menu.getId())).isEqualTo(false);
    }
}