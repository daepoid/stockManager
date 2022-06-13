package daepoid.stockManager.domain.order;

import daepoid.stockManager.domain.food.Food;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class OrderMenu {

    @Id @GeneratedValue
    @Column(name="order_menu_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="food_id")
    private Food food;

    // 주문 가격
    // 주문 가격은 가격 변동이나 할인 등으로 현재 가격과 다를 수 있으므로 따로 저장한다.
    @NotNull
    @Positive
    private Double orderPrice;

    // 주문 수량
    @NotNull
    @Positive
    private Integer orderCount;

    @Builder
    public OrderMenu(Order order, Food food, Double orderPrice, Integer orderCount) {
        this.order = order;
        this.food = food;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
    }

    public void changeFood(Food food) {
        this.food.cancelSalesCount(orderCount);
        this.food = food;
        food.addSalesCount(orderCount);
    }

    public void changeOrderPrice(Double orderPrice) {
        this.order.updateOrderPriceByChangeOrderMenu(
                this.orderPrice * this.orderCount,
                orderPrice * this.orderCount
        );
        this.orderPrice = orderPrice;
    }

    public void changeOrderCount(Integer orderCount) {
        this.order.updateOrderPriceByChangeOrderMenu(
                this.orderPrice * this.orderCount,
                this.orderPrice * orderCount
        );
        this.orderCount = orderCount;
    }

    public void orderFood(Integer orderCount) {
        this.food.addSalesCount(orderCount);
    }

    public void cancel() {
        food.cancelSalesCount(orderCount);
    }

    public Double getOrderMenuPrice() {
        return this.orderPrice * this.orderCount;
    }
}
