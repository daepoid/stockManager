package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Cart createCart() {
        Map<Long, Integer> numberOfMenus = new HashMap<>();
        return Cart.builder().numberOfMenus(numberOfMenus).build();
    }

    private Menu createMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;

        return Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(numberOfFoods)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
    }

    private OrderMenu createOrderMenu(Menu menu) {
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        return OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
    }

    private Order createOrder(OrderMenu orderOrderMenu) {
        List<OrderMenu> orderOrderMenus = new ArrayList<>();
        LocalDateTime orderOrderDateTime = LocalDateTime.now();
        OrderStatus orderOrderStatus = OrderStatus.ORDERED;

        orderOrderMenus.add(orderOrderMenu);

        return Order.builder()
                .orderMenus(orderOrderMenus)
                .orderDateTime(orderOrderDateTime)
                .orderStatus(orderOrderStatus)
                .build();
    }

    @Test
    void saveCustomer() {
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
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
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
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
        Cart cart = createCart();
        em.persist(cart);

        String name = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(name)
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
    void findByUserName() {
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(customerService.findCustomerByUserName(userName).getId()).isEqualTo(customerId);
        assertThat(customerService.findCustomerByUserName(userName).getId()).isEqualTo(customer.getId());
    }

    @Test
    void findByTableNumber() {
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(customerService.findCustomerByTableNumber(tableNumber)).isEqualTo(customer);
        assertThat(customerService.findCustomerByTableNumber(tableNumber).getId()).isEqualTo(customerId);
    }

    @Test
    void changeName() {
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        String newName = "new customer name";
        customerService.changeUserName(customerId, newName);

        assertThat(customer.getUserName()).isEqualTo(newName);
        assertThat(customerService.findCustomer(customerId).getUserName()).isEqualTo(newName);
        assertThat(customerService.findCustomerByUserName(newName).getId()).isEqualTo(customerId);
    }

    @Test
    void changeTableNumber() {
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        String newTableNumber = "456";
        customerService.changeTableNumber(customerId, newTableNumber);

        assertThat(customer.getTableNumber()).isEqualTo(newTableNumber);
        assertThat(customerService.findCustomer(customerId).getTableNumber()).isEqualTo(newTableNumber);
        assertThat(customerService.findCustomerByTableNumber(newTableNumber).getId()).isEqualTo(customerId);
    }

    @Test
    void changeOrders() {
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        Menu menu = createMenu();
        em.persist(menu);

        OrderMenu orderMenu = createOrderMenu(menu);

        Order order = createOrder(orderMenu);
        em.persist(order);

        assertThat(customerService.findCustomer(customerId).getOrders().contains(order)).isFalse();
        assertThat(customerService.findCustomer(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNull();

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
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        Menu menu = createMenu();
        em.persist(menu);

        OrderMenu orderMenu = createOrderMenu(menu);

        Order order = createOrder(orderMenu);
        em.persist(order);

        assertThat(customerService.findCustomer(customerId).getOrders().contains(order)).isFalse();
        assertThat(customerService.findCustomer(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNull();

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
        Cart cart = createCart();
        em.persist(cart);

        String userName = "customer";
        String password = "123";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(cart)
                .orders(orders)
                .build();

        Long customerId = customerService.saveCustomer(customer);

        assertThat(customerService.findCustomer(customerId)).isEqualTo(customer);

        customerService.removeCustomer(customerId);

        assertThat(customerService.findCustomer(customerId)).isNull();
    }
}