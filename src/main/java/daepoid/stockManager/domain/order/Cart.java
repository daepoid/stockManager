package daepoid.stockManager.domain.order;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    @Column(name="cart_id")
    private Long id;

    @ElementCollection
    @JoinTable(
            name="cart_number_of_menu",
            joinColumns=@JoinColumn(name="cart_id"))
    @MapKeyColumn(name="menu_id")
    @Column(name="number")
    private Map<Long, Integer> numberOfMenus = new HashMap<>();

    @OneToOne(mappedBy="cart", fetch = FetchType.LAZY)
    private Customer customer;

    @Builder
    public Cart(Map<Long, Integer> numberOfMenus, Customer customer) {
        this.numberOfMenus = numberOfMenus;
        this.customer = customer;
    }

    public void addMenu(Long menuId, Integer count) {
        this.numberOfMenus.put(menuId, count);
    }

    public void addMenus(Map<Long, Integer> menus) {
        this.numberOfMenus.putAll(menus);
    }

    public void clearCart() {
        numberOfMenus.clear();
    }
}
