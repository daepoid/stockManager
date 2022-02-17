package daepoid.stockManager.controller.dto.order;

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
    private String userName;

    @NotNull
    private String tableNumber;

    public EditCustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.userName = customer.getUserName();
        this.tableNumber = customer.getTableNumber();
    }
}
