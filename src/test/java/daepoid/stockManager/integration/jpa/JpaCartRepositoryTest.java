package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.repository.jpa.JpaCartRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Objects;

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
        Cart cart = Cart.builder().build();

        Long savedId = cartRepository.save(cart);

        assertThat(savedId).isEqualTo(cart.getId());
        assertThat(em.find(Cart.class, savedId)).isEqualTo(cart);
    }

    @Test
    void findById_찾기성공() {
        Cart cart = Cart.builder().build();
        Long savedId = cartRepository.save(cart);

        assertThat(cartRepository.findById(savedId)).isEqualTo(cart);
    }

    @Test
    void findById_찾기실패() {
        Cart cart = Cart.builder().build();
        Long savedId = cartRepository.save(cart);

        Cart findCart = cartRepository.findById(savedId + 1L);
        assertThat(findCart).isEqualTo(null);
    }

    @Test
    void findAll_성공() {
        int size = cartRepository.findAll().size();

        Cart cart1 = Cart.builder().build();
        cartRepository.save(cart1);
        Cart cart2 = Cart.builder().build();
        cartRepository.save(cart2);
        assertThat(cartRepository.findAll().size()).isEqualTo(size + 2);

        em.detach(cart1);
        assertThat(Objects.requireNonNull(cartRepository.findAll().stream()
                .filter(cart -> cart.getId().equals(cart1.getId()))
                .findAny().orElse(null)).getId())
                .isEqualTo(cart1.getId());
    }

    @Test
    void addMenu() {

    }

    @Test
    void addMenus() {
    }

    @Test
    void removeMenu() {
    }

    @Test
    void clearCart() {
    }
}