package daepoid.stockManager.domain.food;

import daepoid.stockManager.domain.users.Customer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartFood {

    @Id
    @GeneratedValue
    @Column(name = "cart_food_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private Integer count;

    @Builder
    public CartFood(Customer customer, Food food, Integer count) {
        this.customer = customer;
        this.food = food;
        this.count = count;
    }

    public void changeCount(int count) {
        this.count = count;
    }

    public void addCount(int count) {
        if(this.count != null) {
            this.count += count;
        } else {
            this.count = count;
        }
    }
}
