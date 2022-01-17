package daepoid.stockManager.domain.order;

import daepoid.stockManager.domain.recipe.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class OrderMenu {

    @Id @GeneratedValue
    @Column(name="order_menu_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="menu_id")
    private Menu menu;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private Integer orderPrice;

    private Integer orderCount;

    @Builder
    public OrderMenu(Menu menu, Order order, Integer orderPrice, Integer orderCount) {
        this.menu = menu;
        this.order = order;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
    }

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeMenu(Menu menu) {
        this.menu = menu;
    }

    public void changeOrder(Order order) {
        this.order = order;
    }

    public void changeOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void changeCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public void cancel() {
        getMenu().cancelMenu(orderCount);
    }

    public Integer getTotalPrice() {
        return getOrderPrice() * getOrderCount();
    }
}
