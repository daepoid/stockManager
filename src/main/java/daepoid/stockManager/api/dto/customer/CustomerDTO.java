package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.domain.order.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDTO {

    private Long customerId;

    private String userName;

    private String tableNumber;

    public CustomerDTO(Customer customer) {
        this.customerId = customer.getId();
        this.userName = customer.getUserName();
        this.tableNumber = customer.getTableNumber();
    }

    public CustomerDTO() {

    }
}
