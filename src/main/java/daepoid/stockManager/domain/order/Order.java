package daepoid.stockManager.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //==생성 메서드(빌더 방식)==//
    @Builder
    public Order(Customer customer, List<OrderMenu> orderMenus, LocalDateTime orderDateTime, OrderStatus orderStatus) {
        this.customer = customer;
        this.orderMenus = orderMenus;
        for (OrderMenu orderMenu : orderMenus) {
            orderMenu.changeOrder(this);
        }
        this.orderDateTime = orderDateTime;
        this.orderStatus = orderStatus;
    }

    //==연관 관계 메서드==//
    public void changeCustomer(Customer customer) {
        this.customer = customer;
        customer.getOrders().add(this);
    }

    public void changeOrderMenus(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
        for (OrderMenu orderMenu : orderMenus) {
            orderMenu.changeOrder(this);
        }
    }

    public void addOrderMenu(OrderMenu orderMenu) {
        this.orderMenus.add(orderMenu);
        orderMenu.changeOrder(this);
    }

    public void changeOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    //==비즈니스 로직==//

    /**
     * 주문 취소
     */
    public void cancel(){
        if(this.orderStatus != OrderStatus.ORDERED && this.orderStatus != OrderStatus.CANCELED) {
            throw new IllegalStateException("준비중인 음식은 취소가 불가능합니다.");
        }
        if(this.orderStatus != OrderStatus.CANCELED){
            throw new IllegalStateException("이미 취소되었습니다.");
        }
        this.changeOrderStatus(OrderStatus.CANCELED);

        for (OrderMenu orderMenu : orderMenus) {
            orderMenu.cancel();
        }
    }

    public Integer getTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getTotalPrice)
                .sum();
    }
}
