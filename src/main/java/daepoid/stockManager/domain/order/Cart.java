package daepoid.stockManager.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @ElementCollection
    @CollectionTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "cart_id"),
            foreignKey = @ForeignKey(name = "customer_cart_fk")
    )
    private Map<Long, Integer> numberOfMenus = new HashMap<>();

    public void changeNumberOfMenus(Map<Long, Integer> numberOfMenus) {
        this.numberOfMenus = numberOfMenus;
    }

    public void addCart(Long menuId, Integer count) {
        this.numberOfMenus.put(menuId, count);
    }

    public void addCart(Map<Long, Integer> menus) {
        this.numberOfMenus.putAll(menus);
    }

    public void removeCart(Long menuId, Integer count) {
        this.numberOfMenus.remove(menuId, count);
    }

    public void removeCart(Long menuId) {
        this.numberOfMenus.remove(menuId);
    }

    public void clearCart() {
        numberOfMenus.clear();
    }
}
