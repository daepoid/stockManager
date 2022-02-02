package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.repository.CustomerRepository;
import daepoid.stockManager.repository.jpa.JpaCartRepository;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaCustomerRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaCustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JpaCartRepository cartRepository;

    static class DTO {
        Long customerId;

        Customer customer;

        DTO(Long customerId, Customer customer) {
            this.customerId = customerId;
            this.customer = customer;
        }
    }

    @Test
    public void save_성공() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);

        Customer customer = Customer.builder()
                .name("customer1")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();


        // when
        Long customerId = customerRepository.save(customer);

        // then
        assertThat(customerId).isEqualTo(customer.getId());
        assertThat(em.find(Customer.class, customerId)).isEqualTo(customer);
    }

    @Test
    public void findById() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);

        Customer customer = Customer.builder()
                .name("customer1")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        Customer findCustomer = customerRepository.findById(customerId);

        // then
        assertThat(findCustomer).isEqualTo(customer);
        assertThat(findCustomer).isEqualTo(em.find(Customer.class, customerId));
    }

    @Test
    public void findAll() throws Exception {
        // given
        int size = customerRepository.findAll().size();

        Cart cart1 = Cart.builder().build();
        Long cart1Id = cartRepository.save(cart1);
        Customer customer1 = Customer.builder()
                .name("customer1")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart1)
                .build();

        Cart cart2 = Cart.builder().build();
        Long cartId2 = cartRepository.save(cart2);
        Customer customer2 = Customer.builder()
                .name("customer2")
                .password(passwordEncoder.encode("123"))
                .tableNumber(222)
                .cart(cart2)
                .build();

        // when
        Long customer1Id = customerRepository.save(customer1);
        Long customer2Id = customerRepository.save(customer2);

        // then
        assertThat(customerRepository.findAll().size()).isEqualTo(size + 2);
        assertThat(customerRepository.findAll().contains(customer1)).isEqualTo(true);
        assertThat(customerRepository.findAll().contains(customer2)).isEqualTo(true);
    }

    @Test
    public void findByName() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);

        String customerName = "customer1";
        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        // then
        assertThat(customerRepository.findByName(customerName)).isEqualTo(customer);
    }

    @Test
    public void changeId() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);
        Long changeId = 123123L;

        String customerName = "customer1";
        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        customerRepository.changeId(customerId, changeId);

        // then
        assertThat(customerRepository.findById(customerId)).isEqualTo(null);
        assertThat(customerRepository.findById(changeId)).isEqualTo(customer);
    }

    @Test
    public void changeName() throws Exception {
        // given
        String changedName = "customer123";
        String customerName = "customer1";
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        customerRepository.changeName(customerId, changedName);
    
        // then
        assertThat(customerRepository.findById(customerId).getName()).isEqualTo(changedName);
        assertThat(customerRepository.findByName(changedName)).isEqualTo(customer);
    }

    @Test
    public void changeTableNumber() throws Exception {
        // given
        int tableNumber = 123;
        String customerName = "customer1";
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode("123"))
                .tableNumber(tableNumber)
                .cart(cart)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        customerRepository.changeTableNumber(customerId, tableNumber + tableNumber);

        // then
        assertThat(customerRepository.findById(customerId).getTableNumber()).isEqualTo(tableNumber + tableNumber);
        assertThat(customerRepository.findByTableNumber(tableNumber + tableNumber)).isEqualTo(customer);
    }

    @Test
    public void changeOrders() throws Exception {
        // given
        int tableNumber = 123;
        String customerName = "customer1";
        Cart cart = Cart.builder().build();
        Long cartId = cartRepository.save(cart);

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode("123"))
                .tableNumber(tableNumber)
                .cart(cart)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        OrderMenu orderMenu = OrderMenu.builder().build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        // when
        Long customerId = customerRepository.save(customer);
        customerRepository.changeOrders(customerId, orders);

        // then
        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isEqualTo(true);
    }
    
    @Test
    public void addOrder() throws Exception {
        // given
        
        // when
    
        // then
        
    }

    @Test
    public void removeCustomer() throws Exception {
        // given
        
        // when
    
        // then
        
    }
}