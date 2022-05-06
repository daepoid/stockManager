package daepoid.stockManager.testData;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
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
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        customerService.saveCustomer(Customer.builder()
                .loginId("table001")
                .userName("customer name or table name1")
                .password(passwordEncoder.encode("123"))
                .gradeType(GradeType.CUSTOMER)
                .tableNumber("1")
                .cart(new Cart(new HashMap<>()))
                .build());

        customerService.saveCustomer(Customer.builder()
                .loginId("table002")
                .userName("customer name or table name2")
                .password(passwordEncoder.encode("123"))
                .gradeType(GradeType.CUSTOMER)
                .tableNumber("2")
                .cart(new Cart(new HashMap<>()))
                .build());

        customerService.saveCustomer(Customer.builder()
                .loginId("table003")
                .userName("customer name or table name3")
                .password(passwordEncoder.encode("123"))
                .gradeType(GradeType.CUSTOMER)
                .tableNumber("3")
                .cart(new Cart(new HashMap<>()))
                .build());
    }

}
