package daepoid.stockManager.dto;

import daepoid.stockManager.domain.order.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCustomerDTO {

    private Long id;

    private String name;

    private Integer tableNumber;

    public EditCustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.tableNumber = customer.getTableNumber();
    }
}
