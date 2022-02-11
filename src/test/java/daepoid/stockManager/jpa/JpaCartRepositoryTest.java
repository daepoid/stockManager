package daepoid.stockManager.jpa;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaCartRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaCartRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaCartRepository cartRepository;

    @Test
    void save_성공() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        assertThat(cartId).isEqualTo(cart.getId());
        assertThat(em.find(Cart.class, cartId)).isEqualTo(cart);
    }

    @Test
    void findById_찾기성공() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        assertThat(cartRepository.findById(cart.getId())).isEqualTo(cart);
        assertThat(cartRepository.findById(cartId)).isEqualTo(cart);
    }

    @Test
    void findById_찾기실패() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        // 올바르지 않은 아이디를 이용하여 cart를 찾는다.
        Long wrongId = 123123123L;
        assertThat(cartRepository.findById(wrongId)).isNull();
    }

    @Test
    void findAll_성공() {
        int size = cartRepository.findAll().size();

        Map<Long, Integer> numberOfMenus1 = new HashMap<>();
        Cart cart1 = Cart.builder().numberOfMenus(numberOfMenus1).build();

        Long cartId1 = cartRepository.save(cart1);

        Map<Long, Integer> numberOfMenus2 = new HashMap<>();
        Cart cart2 = Cart.builder().numberOfMenus(numberOfMenus2).build();

        Long cartId2 = cartRepository.save(cart2);

        assertThat(cartRepository.findAll().size()).isEqualTo(size + 2);

        em.detach(cart1);
        assertThat(Objects.requireNonNull(cartRepository.findAll().stream()
                .filter(cart -> cart.getId().equals(cart1.getId()))
                .findAny().orElse(null)).getId())
                .isEqualTo(cart1.getId());
    }

    @Test
    void addMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(numberOfFoods)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int numberOfFood = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), numberOfFood);

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        assertThat(cart.getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfFood);
        assertThat(cartRepository.findById(cart.getId()).getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfFood);
    }

    @Test
    void addMenus() {
        String menuName1 = "menu name";
        Set<Recipe> menuFoods1 = new HashSet<>();
        int menuPrice1 = 123;
        Map<Long, Integer> numberOfFoods1 = new HashMap<>();
        LocalDateTime menuAddedDate1 = LocalDateTime.now();
        int menuSalesCount1 = 123;

        Menu menu1 = Menu.builder()
                .name(menuName1)
                .foods(menuFoods1)
                .price(menuPrice1)
                .numberOfFoods(numberOfFoods1)
                .addedDate(menuAddedDate1)
                .salesCount(menuSalesCount1)
                .build();
        em.persist(menu1);

        String menuName2 = "menu name";
        Set<Recipe> menuFoods2 = new HashSet<>();
        int menuPrice2 = 123;
        Map<Long, Integer> numberOfFoods2 = new HashMap<>();
        LocalDateTime menuAddedDate2 = LocalDateTime.now();
        int menuSalesCount2 = 123;

        Menu menu2 = Menu.builder()
                .name(menuName2)
                .foods(menuFoods2)
                .price(menuPrice2)
                .numberOfFoods(numberOfFoods2)
                .addedDate(menuAddedDate2)
                .salesCount(menuSalesCount2)
                .build();
        em.persist(menu2);

        int numberOfFood1 = 123;
        int numberOfFood2 = 456;
        Map<Long, Integer> numberOfMenus = new HashMap<>();

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        Map<Long, Integer> newNumberOfMenus = new HashMap<>();
        newNumberOfMenus.put(menu1.getId(), numberOfFood1);
        newNumberOfMenus.put(menu2.getId(), numberOfFood2);

        cartRepository.addMenus(cartId, newNumberOfMenus);

        assertThat(cart.getNumberOfMenus().get(menu1.getId())).isEqualTo(numberOfFood1);
        assertThat(cartRepository.findById(cartId).getNumberOfMenus().get(menu1.getId())).isEqualTo(numberOfFood1);

        assertThat(cart.getNumberOfMenus().get(menu2.getId())).isEqualTo(numberOfFood2);
        assertThat(cartRepository.findById(cartId).getNumberOfMenus().get(menu2.getId())).isEqualTo(numberOfFood2);
    }

    @Test
    void removeMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(numberOfFoods)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int numberOfFood = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), numberOfFood);

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        assertThat(cart.getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfFood);
        assertThat(cartRepository.findById(cart.getId()).getNumberOfMenus().get(menu.getId())).isEqualTo(numberOfFood);

        cartRepository.removeMenu(cart.getId(), menu.getId());

        assertThat(cart.getNumberOfMenus().get(menu.getId())).isEqualTo(null);
        assertThat(cartRepository.findById(cart.getId()).getNumberOfMenus().get(menu.getId())).isEqualTo(null);
    }

    @Test
    void clearCart() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(numberOfFoods)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int numberOfFood = 123;
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), numberOfFood);

        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();

        Long cartId = cartRepository.save(cart);

        cartRepository.clearCart(cart.getId());

        assertThat(cartRepository.findById(cartId).getNumberOfMenus().size()).isEqualTo(0);
        assertThat(cartRepository.findById(cartId).getNumberOfMenus().containsKey(menu.getId())).isFalse();
    }
}