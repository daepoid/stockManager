package daepoid.stockManager.controller.management;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.dto.EditCustomerDTO;
import daepoid.stockManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/customer-management")
@RequiredArgsConstructor
public class CustomerManagementController {

    private final CustomerService customerService;

    @GetMapping("")
    public String customerListForm(Model model) {
        model.addAttribute("customers", customerService.findCustomers());
        return "customer-management/customerListForm";
    }

    @GetMapping("/{customerId}")
    public String editCustomerForm(@PathVariable Long customerId,
                                   Model model) {

        model.addAttribute("editCustomerDTO", new EditCustomerDTO(customerService.findCustomer(customerId)));
        return "customer-management/editCustomerForm";
    }

    @PostMapping("/{customerId}")
    public String editCustomer(@PathVariable Long customerId,
                               @Valid @ModelAttribute("editCustomerDTO") EditCustomerDTO editCustomerDTO,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "customer-management/editCustomerForm";
        }

        customerService.changeName(customerId, editCustomerDTO.getName());
        customerService.changeTableNumber(customerId, editCustomerDTO.getTableNumber());

        return "redirect:/customer-management";
    }


}
