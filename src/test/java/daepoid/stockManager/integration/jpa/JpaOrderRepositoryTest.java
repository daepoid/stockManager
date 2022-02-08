package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaOrderRepository;
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
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(order.getId()).isEqualTo(orderId);
        assertThat(em.find(Order.class, orderId)).isEqualTo(order);
    }

    @Test
    void findById() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findById(orderId)).isEqualTo(order);
        assertThat(orderRepository.findById(orderId).getId()).isEqualTo(order.getId());
    }

    @Test
    void findAll() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findAll().size()).isEqualTo(1);
//        assertThat(orderRepository.findAll().stream()
//                .filter(o -> o.getId().equals(orderId))
//                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByCustomer() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByCustomer(customer).contains(order)).isTrue();
        assertThat(orderRepository.findByCustomer(customer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOrderMenu() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByOrderMenu(orderMenu).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderMenu(orderMenu).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOrderStatus() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByOrderStatus(orderStatus).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderStatus(orderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeCustomer() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByCustomer(customer).contains(order)).isTrue();
        assertThat(orderRepository.findByCustomer(customer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        Map<Long, Integer> newNumberOfMenus = new HashMap<>();
        Cart newCart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(newCart);

        String newCustomerName = "newCustomerName";
        String newCustomerPassword = "123123";
        int newCustomerTableNumber = 123123;
        List<Order> newCustomerOrders = new ArrayList<>();

        Customer newCustomer = Customer.builder()
                .name(newCustomerName)
                .password(passwordEncoder.encode(newCustomerPassword))
                .tableNumber(newCustomerTableNumber)
                .cart(newCart)
                .orders(newCustomerOrders)
                .build();
        em.persist(newCustomer);

        orderRepository.changeCustomer(orderId, newCustomer);

        assertThat(order.getCustomer()).isEqualTo(newCustomer);
        assertThat(orderRepository.findById(orderId).getCustomer()).isEqualTo(newCustomer);
        assertThat(orderRepository.findByCustomer(newCustomer).contains(order)).isTrue();
        assertThat(orderRepository.findByCustomer(newCustomer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeOrderMenus() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByOrderMenu(orderMenu).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderMenu(orderMenu).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        int newOrderMenuOrderPrice = 789789;
        int newOrderMenuOrderCount = 789789;
        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(newOrderMenuOrderPrice)
                .orderCount(newOrderMenuOrderCount)
                .build();

        List<OrderMenu> newOrderMenus = new ArrayList<>();
        newOrderMenus.add(newOrderMenu);
        orderRepository.changeOrderMenus(orderId, newOrderMenus);

        assertThat(order.getOrderMenus().contains(orderMenu)).isFalse();
        assertThat(order.getOrderMenus().contains(newOrderMenu)).isTrue();
//        assertThat(orderRepository.findById(orderId).getOrderMenus().stream()
//                .filter(om -> om.getId().equals(newOrderMenu.getId()))
//                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void addOrderMenus() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findById(orderId).getOrderMenus().contains(orderMenu)).isFalse();

        orderRepository.addOrderMenus(orderId, orderMenu);
        assertThat(order.getOrderMenus().contains(orderMenu)).isTrue();
        assertThat(orderRepository.findById(orderId).getOrderMenus().contains(orderMenu)).isTrue();
        assertThat(orderRepository.findById(orderId).getOrderMenus().stream()
                .filter(om -> om.getId().equals(orderMenu.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeOrderDate() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        LocalDateTime newOrderDateTime = LocalDateTime.now();
        orderRepository.changeOrderDate(orderId, newOrderDateTime);

        assertThat(order.getOrderDateTime()).isEqualTo(newOrderDateTime);
        assertThat(orderRepository.findById(orderId).getOrderDateTime()).isEqualTo(newOrderDateTime);
    }

    @Test
    void changeOrderStatus() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);

        OrderStatus newOrderStatus = OrderStatus.CANCELED;
        orderRepository.changeOrderStatus(orderId, newOrderStatus);

        assertThat(order.getOrderStatus()).isEqualTo(newOrderStatus);
        assertThat(orderRepository.findById(orderId).getOrderStatus()).isEqualTo(newOrderStatus);
        assertThat(orderRepository.findByOrderStatus(newOrderStatus).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderStatus(newOrderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(orderRepository.findByOrderStatus(orderStatus).contains(order)).isFalse();
        assertThat(orderRepository.findByOrderStatus(orderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void removeOrder() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        Cart cart = Cart.builder().numberOfMenus(numberOfMenus).build();
        em.persist(cart);

        String customerName = "customer";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();

        Customer customer = Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(cart)
                .orders(customerOrders)
                .build();
        em.persist(customer);

        String menuName = "menu";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 456;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 0;
        Menu menu = Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFood(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
        em.persist(menu);

        int orderMenuOrderPrice = 789;
        int orderMenuOrderCount = 789;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        em.persist(orderMenu);

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderRepository.save(order);
        assertThat(orderRepository.findById(orderId)).isEqualTo(order);
        assertThat(orderRepository.findById(orderId).getId()).isEqualTo(order.getId());

        orderRepository.removeOrder(order);

        assertThat(orderRepository.findById(orderId)).isNull();
    }
}