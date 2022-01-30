package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.repository.jpa.JpaCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class CartService {

    private final JpaCartRepository cartRepository;

    @Transactional
    public Long createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findCart(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public List<Cart> findCarts() {
        return cartRepository.findAll();
    }

    @Transactional
    public void addMenu(Long cartId, Long menuId, int count) {
        cartRepository.addMenu(cartId, menuId, count);
    }

    @Transactional
    public void addMenus(Long cartId, Map<Long, Integer> menus) {
        cartRepository.addMenus(cartId, menus);
    }

    @Transactional
    public void removeMenu(Long cartId, Long menuId) {
        cartRepository.removeMenu(cartId, menuId);
    }

    @Transactional
    public void clearCart(Long cartId) {
        cartRepository.clearCart(cartId);
    }
}
