package daepoid.stockManager.repository;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.recipe.Menu;

import java.util.List;
import java.util.Map;

public interface CartRepository {

    Long save(Cart cart);

    Cart findById(Long cartId);

    List<Cart> findAll();

    void addMenu(Long cartId, Long menuId, Integer count);

    void addMenus(Long cartId, Map<Long, Integer> menus);

    void removeMenu(Long cartId, Long menuId);
}
