package daepoid.stockManager.domain.order;

import daepoid.stockManager.domain.users.Customer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

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

    @NotNull
    private LocalDateTime orderDateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotNull
    @Positive
    private Double totalOrderPrice;

    //==생성 메서드(빌더 방식)==//
    @Builder
    public Order(Customer customer, LocalDateTime orderDateTime, OrderStatus orderStatus) {
        this.customer = customer;
        this.orderDateTime = orderDateTime;
        this.orderStatus = orderStatus;
    }

    //==연관 관계 메서드==//
    public void changeCustomer(Customer customer) {
        this.customer = customer;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void changeTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    //==비즈니스 로직==//
    public void updateOrderPriceByChangeOrderMenu(Double beforeOrderMenuPrice,
                                                  Double newOrderMenuPrice) {
        this.totalOrderPrice -= beforeOrderMenuPrice;
        this.totalOrderPrice += newOrderMenuPrice;
    }

    /**
     * 주문 취소
     */
    public void cancel(){
        if(this.orderStatus == OrderStatus.COOKING) {
            throw new IllegalStateException("준비중인 음식은 취소가 불가능합니다.");
        }
        if(this.orderStatus == OrderStatus.CANCELED){
            throw new IllegalStateException("이미 취소되었습니다.");
        }
        this.changeOrderStatus(OrderStatus.CANCELED);
    }
}
