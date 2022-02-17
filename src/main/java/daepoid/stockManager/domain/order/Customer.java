package daepoid.stockManager.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    
    // final ?
    @NotBlank
    @Column(unique=true)
    private String name;

    @NotBlank
    private String password;

    @NotNull
    @Column(unique=true)
    // final ?
    // recipeNumber처럼 String 타입으로 생성해야 하는가?
    private int tableNumber;

    @NotNull
    @OneToOne(fetch=FetchType.LAZY, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Customer(String name, String password, int tableNumber, Cart cart, List<Order> orders) {
        this.name = name;
        this.password = password;
        this.tableNumber = tableNumber;
        this.cart = cart;
        this.orders = orders;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeTableNumber(int tableNumber) {
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
