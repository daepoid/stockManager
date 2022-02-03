package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.repository.CartRepository;
import daepoid.stockManager.repository.jpa.JpaCartRepository;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaCustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void saveCustomer() {
        String name = "name";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(em.find(Customer.class, customerId)).isEqualTo(customer);
    }

    @Test
    void findCustomer() {
        String name = "name";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(customerService.findCustomer(customerId)).isEqualTo(customer);
    }

    @Test
    void findCustomers() {
        String name = "name";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(customerService.findCustomers().contains(customer)).isEqualTo(true);
    }

    @Test
    void findByName() {
        String name = "name";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(customerService.findByName(name)).isEqualTo(customer);
    }

    @Test
    void changeName() {
        String name = "name";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        customerService.changeName(customerId, name + name);
        assertThat(customerService.findCustomer(customerId).getName()).isEqualTo(name + name);
        assertThat(customer.getName()).isEqualTo(name + name);
    }

    @Test
    void changeTableNumber() {
    }

    @Test
    void changeOrders() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void removeCustomer() {
    }
}