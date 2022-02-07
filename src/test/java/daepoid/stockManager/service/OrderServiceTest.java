package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.repository.jpa.JpaOrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    JpaOrderRepository orderRepository;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void order() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        LocalDateTime orderDateTime = LocalDateTime.now();

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        Long orderId = orderService.order(customer.getId(), menu.getId(), 123, orderDateTime);

        assertThat(orderService.findOrder(orderId)).isNotNull();
    }

    @Test
    void orders() {
        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), 12);

        Cart cart = Cart.builder()
                .numberOfMenus(numberOfMenus)
                .build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        Long orderId = orderService.orders(customer.getId());

        assertThat(orderService.findByCustomer(customer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void cancelOrder() {
        int orderCount = 12;
        int totalOrderCount = 123123;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(totalOrderCount)
                .build();
        em.persist(menu);

        Map<Long, Integer> numberOfMenus = new HashMap<>();
        numberOfMenus.put(menu.getId(), totalOrderCount);

        Cart cart = Cart.builder()
                .numberOfMenus(numberOfMenus)
                .build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        Long orderId = orderService.order(customer.getId(), menu.getId(), orderCount, LocalDateTime.now());
        assertThat(menu.getOrderCount()).isEqualTo(totalOrderCount + orderCount);

        orderService.cancelOrder(orderId);
        assertThat(orderService.findOrder(orderId).getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(menu.getOrderCount()).isEqualTo(totalOrderCount + orderCount - orderCount);
    }

    @Test
    void save() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderId).isEqualTo(order.getId());
    }

    @Test
    void findOrder() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findOrder(orderId)).isEqualTo(order);
    }

    @Test
    void findOrders() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findOrders().contains(order)).isTrue();
    }

    @Test
    void findByCustomer() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByCustomer(customer).contains(order)).isTrue();
    }

    @Test
    void findByOrderMenu() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByOrderMenu(orderMenu).contains(order)).isTrue();
    }

    @Test
    void findByOrderStatus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByOrderStatus(orderStatus).contains(order)).isTrue();
    }

    @Test
    void changeCustomer() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .orders(new ArrayList<>())
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        Cart newCart = Cart.builder().build();
        em.persist(newCart);

        Customer newCustomer = Customer.builder()
                .name("newCustomer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123123)
                .cart(newCart)
                .orders(new ArrayList<>())
                .build();
        em.persist(newCustomer);
        orderService.changeCustomer(orderId, newCustomer);

        assertThat(orderService.findOrder(orderId).getCustomer()).isEqualTo(newCustomer);
    }

    @Test
    void changeOrderMenus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        List<OrderMenu> newOrderMenus = new ArrayList<>();
        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        newOrderMenus.add(newOrderMenu);
        orderService.changeOrderMenus(orderId, newOrderMenus);

        assertThat(orderService.findOrder(orderId).getOrderMenus().contains(newOrderMenu)).isEqualTo(true);
    }

    @Test
    void addOrderMenus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderService.addOrderMenus(orderId, newOrderMenu);
        assertThat(orderService.findOrder(orderId).getOrderMenus().contains(newOrderMenu)).isEqualTo(true);
    }

    @Test
    void changeOrderDate() {

        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        orderService.changeOrderDate(orderId, LocalDateTime.now());
        assertThat(orderService.findOrder(orderId).getOrderDateTime()).isNotEqualTo(orderDateTime);
    }

    @Test
    void changeOrderStatus() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        orderService.changeOrderStatus(orderId, OrderStatus.CANCELED);
        assertThat(orderService.findOrder(orderId).getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
    }

    @Test
    void removeOrder() {
        Cart cart = Cart.builder().build();
        em.persist(cart);

        Customer customer = Customer.builder()
                .name("customer")
                .password(passwordEncoder.encode("123"))
                .tableNumber(123)
                .cart(cart)
                .build();
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Menu menu = Menu.builder()
                .name("menu")
                .foods(new HashSet<>())
                .price(123)
                .numberOfFood(new HashMap<>())
                .addedDate(LocalDateTime.now())
                .orderCount(0)
                .build();
        em.persist(menu);

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(123)
                .orderCount(123)
                .build();

        orderMenus.add(orderMenu);

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        orderService.removeOrder(order);

        assertThat(orderService.findOrder(orderId)).isEqualTo(null);
    }
}