package daepoid.stockManager.domain.order;

import daepoid.stockManager.domain.recipe.Menu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class OrderMenu {

    @Id @GeneratedValue
    @Column(name="order_menu_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="menu_id")
    private Menu menu;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    // 주문 당시 가격
    @NotNull
    private int orderPrice;

    // 주문 수량
    @NotNull
    private int orderCount;

    @Builder
    public OrderMenu(Menu menu, Order order, int orderPrice, int orderCount) {
        this.menu = menu;
        this.order = order;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
    }

    public void changeMenu(Menu menu) {
        this.menu = menu;
    }

    public void changeOrder(Order order) {
        this.order = order;
//        order.getOrderMenus().add(this);
    }

    public void changeOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void changeOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void cancel() {
        getMenu().cancelMenu(orderCount);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getOrderCount();
    }
}
