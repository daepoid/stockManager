package daepoid.stockManager.testData;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.service.CartService;
import daepoid.stockManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class TestCustomerDataInit {

    private final CustomerService customerService;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        Long cart1Id = cartService.createCart(Cart.builder().numberOfMenus(new HashMap<>()).build());
        customerService.saveCustomer(Customer.builder()
                .loginId("table001")
                .userName("table name")
                .password(passwordEncoder.encode("123"))
                .tableNumber("1")
                .cart(cartService.findCart(cart1Id))
                .build());

        Long cart2Id = cartService.createCart(Cart.builder().numberOfMenus(new HashMap<>()).build());
        customerService.saveCustomer(Customer.builder()
                .loginId("table002")
                .userName("table name")
                .password(passwordEncoder.encode("123"))
                .tableNumber("2")
                .cart(cartService.findCart(cart2Id))
                .build());

        Long cart3Id = cartService.createCart(Cart.builder().numberOfMenus(new HashMap<>()).build());
        customerService.saveCustomer(Customer.builder()
                .loginId("table003")
                .userName("table name")
                .password(passwordEncoder.encode("123"))
                .tableNumber("3")
                .cart(cartService.findCart(cart3Id))
                .build());
    }

}
