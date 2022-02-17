package daepoid.stockManager.domain.order;

import daepoid.stockManager.domain.StoreUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("CUSTOMER")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Customer extends StoreUser {

    @NotNull
    @Column(unique=true)
    private String tableNumber;

    @NotNull
    @OneToOne(fetch=FetchType.LAZY, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Embedded
    private ShoppingCart shoppingCart;

    @Builder
    public Customer(String loginId, String password, String userName,
                    String tableNumber, Cart cart, List<Order> orders) {
        this.changeLoginId(loginId);
        this.changePassword(password);
        this.changeUserName(userName);
        this.tableNumber = tableNumber;
        this.cart = cart;
        this.orders = orders;
    }

    public void changeTableNumber(String tableNumber) {
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
        cart.changeCustomer(this);
    }
}
