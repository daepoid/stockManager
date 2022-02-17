package daepoid.stockManager.controller.management;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.controller.dto.order.CreateCustomerDTO;
import daepoid.stockManager.controller.dto.order.EditCustomerDTO;
import daepoid.stockManager.service.CartService;
import daepoid.stockManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/customer-management")
@RequiredArgsConstructor
public class CustomerManagementController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;

    @GetMapping("")
    public String customerListForm(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }

        model.addAttribute("customers", customerService.findCustomers());
        return "customer-management/customerListForm";
    }

    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("createCustomerDTO", new CreateCustomerDTO());
        return "customer-management/createCustomerForm";
    }

    @PostMapping("/new")
    public String createCustomer(@Valid @ModelAttribute("createCustomerDTO") CreateCustomerDTO createCustomerDTO,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "customer-management/createCustomerForm";
        }

        if(customerService.findByName(createCustomerDTO.getName()) != null) {
            return "customer-management/createCustomerForm";
        }

        if(!createCustomerDTO.getPassword().equals(createCustomerDTO.getPasswordCheck())) {
            return "customer-management/createCustomerForm";
        }

        Long cartId = cartService.createCart(Cart.builder().build());

        Customer customer = Customer.builder()
                .name(createCustomerDTO.getName())
                .password(passwordEncoder.encode(createCustomerDTO.getPassword()))
                .tableNumber(createCustomerDTO.getTableNumber())
                .cart(cartService.findCart(cartId))
                .build();

        customerService.saveCustomer(customer);
        return "redirect:/customer-management";
    }

    @GetMapping("/{customerId}")
    public String editCustomerForm(@PathVariable Long customerId,
                                   Model model,
                                   HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }

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
