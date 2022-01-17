package daepoid.stockManager.testData;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestCustomerDataInit {

    private final CustomerService customerService;

    @PostConstruct
    public void init() {

        customerService.saveCustomer(Customer.builder()
                .name("1번 테이블")
                .tableNumber(1)
                .build());

        customerService.saveCustomer(Customer.builder()
                .name("2번 테이블")
                .tableNumber(2)
                .build());

        customerService.saveCustomer(Customer.builder()
                .name("3번 테이블")
                .tableNumber(3)
                .build());

    }

}
