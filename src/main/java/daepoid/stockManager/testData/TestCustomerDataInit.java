package daepoid.stockManager.testData;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class TestCustomerDataInit {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        customerService.saveCustomer(
                Customer.builder()
                        .loginId("table001")
                        .password(passwordEncoder.encode("123"))
                        .userName("customer name or table name1")
                        .gradeType(GradeType.CUSTOMER)
                        .tableNumber("1")
                        .expirationTime(LocalDateTime.now().plusMinutes(30))
                        .build()
        );

        customerService.saveCustomer(
                Customer.builder()
                        .loginId("table002")
                        .password(passwordEncoder.encode("123"))
                        .userName("customer name or table name2")
                        .gradeType(GradeType.CUSTOMER)
                        .tableNumber("2")
                        .expirationTime(LocalDateTime.now().plusMinutes(30))
                        .build()
        );

        customerService.saveCustomer(
                Customer.builder()
                        .loginId("table003")
                        .password(passwordEncoder.encode("123"))
                        .userName("customer name or table name3")
                        .gradeType(GradeType.CUSTOMER)
                        .tableNumber("3")
                        .expirationTime(LocalDateTime.now().plusMinutes(30))
                        .build()
        );
    }

}
