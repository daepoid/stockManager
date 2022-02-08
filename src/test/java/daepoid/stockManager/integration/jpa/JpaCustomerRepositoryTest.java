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
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
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
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
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
        Cart cart1 = Cart.builder().build();
        em.persist(cart1);

        String name1 = "customer1";
        String password1 = "123";
        int tableNumber1 = 1231;
        List<Order> orders1 = new ArrayList<>();

        Customer customer1 = Customer.builder()
                .name(name1)
                .password(passwordEncoder.encode(password1))
                .tableNumber(tableNumber1)
                .cart(cart1)
                .orders(orders1)
                .build();

        Cart cart2 = Cart.builder().build();
        em.persist(cart2);

        String name2 = "customer2";
        String password2 = "123";
        int tableNumber2 = 1232;
        List<Order> orders2 = new ArrayList<>();

        Customer customer2 = Customer.builder()
                .name(name2)
                .password(passwordEncoder.encode(password2))
                .tableNumber(tableNumber2)
                .cart(cart2)
                .orders(orders2)
                .build();

        // when
        int size = customerRepository.findAll().size();
        Long customer1Id = customerRepository.save(customer1);
        Long customer2Id = customerRepository.save(customer2);

        // then

        // customer1은 영속상태이므로 contains를 통해 찾을 수 있음
        assertThat(customerRepository.findAll().contains(customer1)).isTrue();
        assertThat(customerRepository.findAll().stream()
                .filter(c -> c.getId().equals(customer1Id))
                .findFirst().orElse(null)).isNotNull();

        // customer2는 준영속상태이므로 contains를 통해 찾을 수 없다.
        em.detach(customer2);
        assertThat(customerRepository.findAll().contains(customer2)).isFalse();
        assertThat(customerRepository.findAll().stream()
                .filter(c -> c.getId().equals(customer2Id))
                .findFirst().orElse(null)).isNotNull();

        assertThat(customerRepository.findAll().size()).isEqualTo(size + 2);
    }

    @Test
    public void findByName() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        // then
        assertThat(customerRepository.findByName(name)).isEqualTo(customer);
    }

    @Test
    public void changeName() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        String newName = "newCustomerName";
        customerRepository.changeName(customerId, newName);
    
        // then
        assertThat(customerRepository.findById(customerId).getName()).isEqualTo(newName); // 영속성
        assertThat(customerRepository.findByName(newName).getId()).isEqualTo(customer.getId());
    }

    @Test
    public void changeTableNumber() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        int newTableNumber = 456;
        customerRepository.changeTableNumber(customerId, newTableNumber);

        // then
        assertThat(customerRepository.findById(customerId).getTableNumber()).isEqualTo(newTableNumber);
        assertThat(customerRepository.findByTableNumber(newTableNumber).getId()).isEqualTo(customer.getId());
    }

    @Test
    public void changeOrders() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        em.persist(cart);

        List<OrderMenu> orderMenus = new ArrayList<>();
        OrderMenu orderMenu = OrderMenu.builder().build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();
        em.persist(order);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isFalse();
        assertThat(customerRepository.findById(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNull();

        List<Order> newOrders = new ArrayList<>();
        newOrders.add(order);
        customerRepository.changeOrders(customerId, newOrders);

        // then
        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isTrue();
        assertThat(customerRepository.findById(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();
    }
    
    @Test
    public void addOrder() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Order order = Order.builder()
                .orderMenus(new ArrayList<>())
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();
        em.persist(order);

        // when
        Long customerId = customerRepository.save(customer);
        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isFalse();
        assertThat(customerRepository.findById(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNull();

        customerRepository.addOrder(customerId, order);

        // then
        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isTrue();
        assertThat(customerRepository.findById(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void removeCustomer() throws Exception {
        // given
        Cart cart = Cart.builder().build();
        em.persist(cart);

        String name = "customer";
        String password = "123";
        int tableNumber = 123;
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        assertThat(customerRepository.findById(customerId)).isNotNull();

        customerRepository.removeCustomer(customer);
    
        // then
        assertThat(customerRepository.findById(customerId)).isNull();
    }
}