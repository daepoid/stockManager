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

    private Integer tableNumber;

    @OneToMany(mappedBy="customer")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Customer(String name, Integer tableNumber, List<Order> orders) {
        this.name = name;
        this.tableNumber = tableNumber;
        this.orders = orders;
    }

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeName(String name) {
        this.name = name;
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
}
