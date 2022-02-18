package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.customer.*;
import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerApiController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 조회 V1
     * @return
     */
    @GetMapping("")
    public Result findCustomersV1() {
        List<Customer> customers = customerService.findCustomers();
        //엔티티 -> DTO 변환
        List<CustomerDTO> CustomerDTOs = customers.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
        return new Result(CustomerDTOs);
    }

    /**
     * 등록 V1
     * @param requestDTO
     * @return
     */
    @PostMapping("")
    public CreateCustomerResponseDTO saveCustomerV1(@RequestBody @Valid CreateCustomerRequestDTO requestDTO) {
        Cart cart = requestDTO.getCart();
        if(cart == null) {
            cart = new Cart(new HashMap<>());
        }

        List<Order> orders = requestDTO.getOrders();
        if(orders == null) {
            orders = new ArrayList<>();
        }

        Customer customer = Customer.builder()
                .userName(requestDTO.getName())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .tableNumber(requestDTO.getTableNumber())
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        return new CreateCustomerResponseDTO(customerId);
    }

    /**
     * 단일 조회 V1
     * @param customerId
     * @return
     */
    @GetMapping("/{customerId}")
    public CustomerDTO findCustomerV1(@PathVariable("customerId") Long customerId) {
        return new CustomerDTO(customerService.findCustomer(customerId));
    }

    /**
     * Patch 수정 V1
     * @param customerId
     * @param requestDTO
     * @return
     */
    @PatchMapping("/{customerId}")
    public UpdateCustomerResponseDTO updateMemberV2(@PathVariable("customerId") Long customerId,
                                                    @RequestBody @Valid UpdateCustomerRequestDTO requestDTO) {

        if(!requestDTO.getName().isBlank()) {
            customerService.changeUserName(customerId, requestDTO.getName());
        }

        if(requestDTO.getTableNumber() != null) {
           customerService.changeTableNumber(customerId, requestDTO.getTableNumber());
        }

        if(requestDTO.getCart() != null) {
            customerService.changeCart(customerId, requestDTO.getCart().getNumberOfMenus());
        }

        if(requestDTO.getOrders() != null) {
            customerService.changeOrders(customerId, requestDTO.getOrders());
        }

        return new UpdateCustomerResponseDTO(customerService.findCustomer(customerId));
    }

    /**
     * 삭제 V1
     * @param customerId
     * @param requestDTO
     * @return
     */
    @DeleteMapping("/{customerId}")
    public DeleteCustomerResponseDTO deleteCustomerV1(@PathVariable("customerId") Long customerId,
                                                      @RequestBody @Valid DeleteCustomerRequestDTO requestDTO) {
        Customer customer = customerService.findCustomer(customerId);
        if(!passwordEncoder.matches(requestDTO.getPassword(), customer.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        customerService.removeCustomer(customerId);
        return new DeleteCustomerResponseDTO(customerId);
    }
}
