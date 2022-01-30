package daepoid.stockManager.testData;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.service.CartService;
import daepoid.stockManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
@RequiredArgsConstructor
public class TestCustomerDataInit {

    private final CustomerService customerService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        Long cart1Id = cartService.createCart(Cart.builder().build());
        customerService.saveCustomer(Customer.builder()
                .name("table001")
                .password(passwordEncoder.encode("123"))
                .tableNumber(1)
                .cart(cartService.findCart(cart1Id))
                .build());

        Long cart2Id = cartService.createCart(Cart.builder().build());
        customerService.saveCustomer(Customer.builder()
                .name("table002")
                .password(passwordEncoder.encode("123"))
                .tableNumber(2)
                .cart(cartService.findCart(cart2Id))
                .build());

        Long cart3Id = cartService.createCart(Cart.builder().build());
        customerService.saveCustomer(Customer.builder()
                .name("table003")
                .password(passwordEncoder.encode("123"))
                .tableNumber(3)
                .cart(cartService.findCart(cart3Id))
                .build());
    }

}
