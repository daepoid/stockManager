package daepoid.stockManager.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="customer_id")
    private Long id;

    private String name;

    private String password;

    private Integer tableNumber;

    @OneToOne(fetch=FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy="customer")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Customer(String name, String password, Integer tableNumber, Cart cart, List<Order> orders) {
        this.name = name;
        this.password = password;
        this.tableNumber = tableNumber;
        this.cart = cart;
        this.orders = orders;
    }

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void changeOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void changeCart(Cart cart) {
        this.cart = cart;
    }
}