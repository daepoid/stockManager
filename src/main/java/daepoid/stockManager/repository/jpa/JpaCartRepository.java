package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaCartRepository implements CartRepository {

    private final EntityManager em;

    @Override
    public Long save(Cart cart) {
        em.persist(cart);
        return cart.getId();
    }

    @Override
    public Cart findById(Long cartId) {
        return em.find(Cart.class, cartId);
    }

    @Override
    public List<Cart> findAll() {
        return em.createQuery("select c from Cart c", Cart.class)
                .getResultList();
    }

    @Override
    public void addMenu(Long cartId, Long menuId, int count) {
        Map<Long, Integer> numberOfMenus = em.find(Cart.class, cartId).getNumberOfMenus();
        numberOfMenus.put(menuId, count);
    }

    @Override
    public void addMenus(Long cartId, Map<Long, Integer> menus) {
        Map<Long, Integer> numberOfMenus = em.find(Cart.class, cartId).getNumberOfMenus();

        for (Long menuId : menus.keySet()) {
            numberOfMenus.put(menuId, menus.get(menuId));
        }
    }

    @Override
    public void removeMenu(Long cartId, Long menuId) {
        Map<Long, Integer> numberOfMenus = em.find(Cart.class, cartId).getNumberOfMenus();
        numberOfMenus.remove(menuId);
    }

    @Override
    public void clearCart(Long cartId) {
        em.find(Cart.class, cartId).getNumberOfMenus().clear();
    }
}
