package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.DishType;
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
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Recipe createRecipe() {
        String recipeRecipeNumber = "456";
        String recipeName = "name";
        int recipePrice = 456;
        int recipeWeight = 456;
        DishType recipeDishType = DishType.BASKET;
        List<Ingredient> recipeIngredients = new ArrayList<>();
        int recipeCost = 456;
        int recipeNetIncome = 456;
        Set<Menu> recipeMenus = new HashSet<>();
        String recipeNotes = "recipe notes";

        return Recipe.builder()
                .recipeNumber(recipeRecipeNumber)
                .name(recipeName)
                .price(recipePrice)
                .weight(recipeWeight)
                .dishType(recipeDishType)
                .ingredients(recipeIngredients)
                .cost(recipeCost)
                .netIncome(recipeNetIncome)
                .menus(recipeMenus)
                .notes(recipeNotes)
                .build();
    }

    private Menu createMenu(Recipe recipe) {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 789;
        Map<Long, Integer> menuNumberOfFood = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 789;

        menuFoods.add(recipe);

        int numberOfMenu = 789;
        menuNumberOfFood.put(recipe.getId(), numberOfMenu);

        return Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(menuNumberOfFood)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .build();
    }

    private Cart createCart(Menu menu) {
        Map<Long, Integer> cartNumberOfMenus = new HashMap<>();
        int cartOrderCount = 123;
        cartNumberOfMenus.put(menu.getId(), cartOrderCount);
        return Cart.builder().numberOfMenus(cartNumberOfMenus).build();
    }

    private Customer createCustomer(Cart customerCart) {
        String customerName = "name";
        String customerPassword = "123";
        int customerTableNumber = 123;
        List<Order> customerOrders = new ArrayList<>();
        return Customer.builder()
                .name(customerName)
                .password(passwordEncoder.encode(customerPassword))
                .tableNumber(customerTableNumber)
                .cart(customerCart)
                .orders(customerOrders)
                .build();
    }

    @Test
    void order() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        LocalDateTime orderDateTime = LocalDateTime.now();
        int orderCount = 123;
        Long orderId = orderService.order(customer.getId(), menu.getId(), orderCount, orderDateTime);

        assertThat(orderService.findOrder(orderId)).isNotNull();
    }

    @Test
    void orders() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        Long orderId = orderService.orders(customer.getId());

        assertThat(orderService.findOrder(orderId).getCustomer().getId()).isEqualTo(customer.getId());

        assertThat(orderService.findByCustomer(customer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void cancelOrder() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        // 확인을 위해 빼 놓음
        int salesCount = menu.getSalesCount();

        LocalDateTime orderDateTime = LocalDateTime.now();
        int orderCount = 123;
        Long orderId = orderService.order(customer.getId(), menu.getId(), orderCount, orderDateTime);
        assertThat(menu.getSalesCount()).isEqualTo(salesCount + orderCount);

        // 주문 취소
        orderService.cancelOrder(orderId);

        assertThat(orderService.findOrder(orderId).getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(menu.getSalesCount()).isEqualTo(salesCount);
    }

    @Test
    void save() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

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
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

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
        int size = orderService.findOrders().size();

        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findOrders().contains(order)).isTrue();
        assertThat(orderService.findOrders().size()).isEqualTo(size + 1);
        assertThat(orderService.findOrders().stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByCustomer() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByCustomer(customer).contains(order)).isTrue();
        assertThat(orderService.findByCustomer(customer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOrderMenu() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByOrderMenu(orderMenu).contains(order)).isTrue();
        assertThat(orderService.findByOrderMenu(orderMenu).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOrderStatus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByOrderStatus(orderStatus).contains(order)).isTrue();
        assertThat(orderService.findByOrderStatus(orderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeCustomer() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findOrder(orderId)).isEqualTo(order);
        assertThat(orderService.findOrder(orderId).getCustomer().getId()).isEqualTo(customer.getId());

        Cart newCart = createCart(menu);
        em.persist(newCart);

        String newCustomerName = "new customer name";
        String newCustomerPassword = "456";
        int newCustomerTableNumber = 456;
        List<Order> newCustomerOrders = new ArrayList<>();
        Customer newCustomer = Customer.builder()
                .name(newCustomerName)
                .password(passwordEncoder.encode(newCustomerPassword))
                .tableNumber(newCustomerTableNumber)
                .cart(newCart)
                .orders(newCustomerOrders)
                .build();
        em.persist(newCustomer);
        orderService.changeCustomer(orderId, newCustomer);

        assertThat(order.getCustomer()).isEqualTo(newCustomer);
        assertThat(orderService.findOrder(orderId).getCustomer().getId()).isEqualTo(newCustomer.getId());
        assertThat(orderService.findByCustomer(newCustomer).contains(order)).isTrue();
        assertThat(orderService.findByCustomer(newCustomer).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeOrderMenus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findByOrderMenu(orderMenu).contains(order)).isTrue();
        assertThat(orderService.findByOrderMenu(orderMenu).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        List<OrderMenu> newOrderMenus = new ArrayList<>();
        int newOrderMenuOrderPrice = 123;
        int newOrderMenuOrderCount = 123;
        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(newOrderMenuOrderPrice)
                .orderCount(newOrderMenuOrderCount)
                .build();
        newOrderMenus.add(newOrderMenu);
        orderService.changeOrderMenus(orderId, newOrderMenus);

        assertThat(orderService.findOrder(orderId).getOrderMenus().contains(newOrderMenu)).isTrue();
    }

    @Test
    void addOrderMenus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        int newOrderMenuOrderPrice = 123;
        int newOrderMenuOrderCount = 123;
        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(newOrderMenuOrderPrice)
                .orderCount(newOrderMenuOrderCount)
                .build();
        orderService.addOrderMenus(orderId, newOrderMenu);

        assertThat(orderService.findOrder(orderId).getOrderMenus().contains(newOrderMenu)).isTrue();
        assertThat(orderService.findOrder(orderId).getOrderMenus().contains(orderMenu)).isTrue();
        assertThat(orderService.findOrder(orderId).getOrderMenus().stream()
                .filter(om -> om.getId().equals(orderMenu.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeOrderDate() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        LocalDateTime newOrderDateTime = LocalDateTime.now();
        orderService.changeOrderDate(orderId, newOrderDateTime);

        assertThat(order.getOrderDateTime()).isEqualTo(newOrderDateTime);

        // 실행 시간이 거의 비슷하여 isNotEqualTo(orderDateTime)으로 비교할 경우 실패가 나올 수 있다.
        assertThat(orderService.findOrder(orderId).getOrderDateTime()).isEqualTo(newOrderDateTime);
    }

    @Test
    void changeOrderStatus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        OrderStatus newOrderStatus = OrderStatus.CANCELED;
        orderService.changeOrderStatus(orderId, newOrderStatus);

        assertThat(order.getOrderStatus()).isEqualTo(newOrderStatus);
        assertThat(orderService.findOrder(orderId).getOrderStatus()).isEqualTo(newOrderStatus);
        assertThat(orderService.findByOrderStatus(newOrderStatus).contains(order)).isTrue();
        assertThat(orderService.findByOrderStatus(newOrderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(orderService.findByOrderStatus(orderStatus).contains(order)).isFalse();
        assertThat(orderService.findByOrderStatus(orderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void removeOrder() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Cart cart = createCart(menu);
        em.persist(cart);

        Customer customer = createCustomer(cart);
        em.persist(customer);

        List<OrderMenu> orderMenus = new ArrayList<>();
        int orderMenuOrderPrice = 123;
        int orderMenuOrderCount = 123;
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(orderMenuOrderPrice)
                .orderCount(orderMenuOrderCount)
                .build();
        orderMenus.add(orderMenu);

        LocalDateTime orderDateTime = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDERED;

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(orderStatus)
                .build();

        Long orderId = orderService.save(order);

        assertThat(orderService.findOrder(orderId)).isEqualTo(order);
        assertThat(orderService.findOrder(orderId).getId()).isEqualTo(order.getId());

        orderService.removeOrder(order);

        assertThat(orderService.findOrder(orderId)).isNull();
    }
}