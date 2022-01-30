package daepoid.stockManager.dto.order;

import daepoid.stockManager.domain.order.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCustomerDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int tableNumber;

    public EditCustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.tableNumber = customer.getTableNumber();
    }
}
