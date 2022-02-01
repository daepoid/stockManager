package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaOrderRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaOrderRepository orderRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void save() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(order.getId()).isEqualTo(orderId);
        assertThat(em.find(Order.class, orderId)).isEqualTo(order);
    }

    @Test
    void findById() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findById(orderId)).isEqualTo(order);
        assertThat(orderRepository.findById(orderId).getId()).isEqualTo(order.getId());
    }

    @Test
    void findAll() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Set<Recipe> foods = new HashSet<>();
        Map<Long, Integer> numberOfFood = new HashMap<>();

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .foods(foods)
                .numberOfFood(numberOfFood)
                .build();
        em.persist(menu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build()
        );

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findAll().contains(order)).isEqualTo(true);
    }

    @Test
    void findByCustomer() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        assertThat(orderRepository.findByCustomer(customer).contains(order)).isEqualTo(true);
    }

    @Test
    void findByOrderMenu() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        assertThat(orderRepository.findByOrderMenu(orderMenu).contains(order)).isEqualTo(true);
    }

    @Test
    void findByOrderStatus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        assertThat(orderRepository.findByOrderStatus(OrderStatus.ORDERED).contains(order)).isEqualTo(true);
    }

    @Test
    void changeCustomer() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer1")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Cart new_cart = Cart.builder().build();
        em.persist(new_cart);

        List<Order> orders = new ArrayList<>();
        Customer new_customer = Customer.builder()
                .name("customer2")
                .password(passwordEncoder.encode("123"))
                .tableNumber(112)
                .cart(new_cart)
                .orders(orders)
                .build();
        em.persist(new_customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);

        orderRepository.changeCustomer(orderId, new_customer);

        assertThat(order.getCustomer()).isEqualTo(new_customer);
        assertThat(orderRepository.findById(orderId).getCustomer()).isEqualTo(new_customer);
    }

    @Test
    void changeOrderMenus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);

        List<OrderMenu> new_orderMenus = new ArrayList<>();
        new_orderMenus.add(orderMenu);
        orderRepository.changeOrderMenus(orderId, new_orderMenus);

        assertThat(order.getOrderMenus().contains(orderMenu)).isEqualTo(true);
    }

    @Test
    void addOrderMenus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        assertThat(orderRepository.findById(orderId).getOrderMenus().contains(orderMenu)).isEqualTo(false);

        orderRepository.addOrderMenus(orderId, orderMenu);
        assertThat(order.getOrderMenus().contains(orderMenu)).isEqualTo(true);
        assertThat(orderRepository.findById(orderId).getOrderMenus().contains(orderMenu)).isEqualTo(true);
    }

    @Test
    void changeOrderDate() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        LocalDateTime changeTime = LocalDateTime.now();
        orderRepository.changeOrderDate(orderId, changeTime);

        assertThat(orderRepository.findById(orderId).getOrderDateTime()).isEqualTo(changeTime);
        assertThat(order.getOrderDateTime()).isEqualTo(changeTime);
    }

    @Test
    void changeOrderStatus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        orderRepository.changeOrderStatus(orderId, OrderStatus.CANCELED);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(orderRepository.findById(orderId).getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(orderRepository.findByOrderStatus(OrderStatus.CANCELED).contains(order)).isEqualTo(true);
        assertThat(orderRepository.findByOrderStatus(OrderStatus.ORDERED).contains(order)).isEqualTo(false);
    }

    @Test
    void removeOrder() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(111)
                .cart(cart)
                .build();
        em.persist(customer);

        Menu menu = Menu.builder()
                .name("menu")
                .price(222)
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(333)
                .orderCount(333)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        Long orderId = orderRepository.save(order);
        orderRepository.removeOrder(order);

        assertThat(orderRepository.findById(orderId)).isEqualTo(null);
    }
}