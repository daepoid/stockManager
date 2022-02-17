package daepoid.stockManager.repository;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.recipe.Menu;

import java.util.List;
import java.util.Map;

public interface CartRepository {

    Long save(Cart cart);

    Cart findById(Long cartId);

    List<Cart> findAll();

    void changeNumberOfMenus(Long cartId, Map<Long, Integer> numberOfMenus);

    void changeCustomer(Long cartId, Customer customer);

    void addMenu(Long cartId, Long menuId, int count);

    void addMenus(Long cartId, Map<Long, Integer> menus);

    void removeMenu(Long cartId, Long menuId);

    void clearCart(Long cartId);
}
