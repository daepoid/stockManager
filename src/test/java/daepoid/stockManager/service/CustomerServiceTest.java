package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.*;
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

import java.time.LocalDateTime;
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

        assertThat(customerService.findCustomers().contains(customer)).isTrue();
        assertThat(customerService.findCustomers().stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst().orElse(null)).isNotNull();
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

        assertThat(customerService.findByName(name).getId()).isEqualTo(customerId);
        assertThat(customerService.findByName(name).getId()).isEqualTo(customer.getId());
    }

    @Test
    void findByTableNumber() {
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

        assertThat(customerService.findByTableNumber(tableNumber)).isEqualTo(customer);
        assertThat(customerService.findByTableNumber(tableNumber).getId()).isEqualTo(customerId);
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

        String newName = "new name";
        customerService.changeName(customerId, newName);

        assertThat(customer.getName()).isEqualTo(newName);
        assertThat(customerService.findCustomer(customerId).getName()).isEqualTo(newName);
        assertThat(customerService.findByName(newName).getId()).isEqualTo(customerId);
    }

    @Test
    void changeTableNumber() {
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

        int newTableNumber = 456;
        customerService.changeTableNumber(customerId, newTableNumber);

        assertThat(customer.getTableNumber()).isEqualTo(newTableNumber);
        assertThat(customerService.findCustomer(customerId).getTableNumber()).isEqualTo(newTableNumber);
        assertThat(customerService.findByTableNumber(newTableNumber).getId()).isEqualTo(customerId);
    }

    @Test
    void changeOrders() {
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

        List<OrderMenu> orderMenus = new ArrayList<>();
        OrderMenu orderMenu = OrderMenu.builder().build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();
        em.persist(order);

        List<Order> newOrders = new ArrayList<>();
        newOrders.add(order);

        customerService.changeOrders(customerId, newOrders);

        assertThat(customer.getOrders().contains(order)).isTrue();
        assertThat(customer.getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(customerService.findCustomer(customerId).getOrders().contains(order)).isTrue();
        assertThat(customerService.findCustomer(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void addOrder() {
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

        assertThat(customer.getOrders().size()).isEqualTo(0);

        List<OrderMenu> orderMenus = new ArrayList<>();
        OrderMenu orderMenu = OrderMenu.builder().build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();
        em.persist(order);

        customerService.addOrder(customerId, order);

        assertThat(customer.getOrders().contains(order)).isTrue();
        assertThat(customer.getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(customerService.findCustomer(customerId).getOrders().contains(order)).isTrue();
        assertThat(customerService.findCustomer(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void removeCustomer() {
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

        customerService.removeCustomer(customer);

        assertThat(customerService.findCustomer(customerId)).isNull();
    }
}