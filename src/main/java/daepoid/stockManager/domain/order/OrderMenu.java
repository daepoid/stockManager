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
    private Integer orderPrice;

    // 주문 수량
    @NotNull
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
