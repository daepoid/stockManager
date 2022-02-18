package daepoid.stockManager.jpa;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.MenuStatus;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.jpa.JpaOrderRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaOrderRepositoryTest {

    @Autowired
    JpaOrderRepository orderRepository;

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
        MenuStatus menuMenuStatus = MenuStatus.ORDERABLE;

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
                .menuStatus(menuMenuStatus)
                .build();
    }

    private Customer createCustomer() {
        String loginId = "customer";
        String customerName = "name";
        String customerPassword = "123";
        String customerTableNumber = "123";
        List<Order> customerOrders = new ArrayList<>();
        return Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(customerPassword))
                .userName(customerName)
                .tableNumber(customerTableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(customerOrders)
                .build();
    }

    @Test
    void save() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(order.getId()).isEqualTo(orderId);
        assertThat(em.find(Order.class, orderId)).isEqualTo(order);
    }

    @Test
    void findById() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findById(orderId)).isEqualTo(order);
        assertThat(orderRepository.findById(orderId).getId()).isEqualTo(order.getId());
    }

    @Test
    void findAll() {
        int size = orderRepository.findAll().size();

        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findAll().contains(order)).isTrue();
        assertThat(orderRepository.findAll().size()).isEqualTo(size + 1);
        assertThat(orderRepository.findAll().stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByCustomer() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByCustomer(customer.getId()).contains(order)).isTrue();
        assertThat(orderRepository.findByCustomer(customer.getId()).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOrderMenu() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByOrderMenu(orderMenu).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderMenu(orderMenu).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByOrderStatus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByOrderStatus(orderStatus).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderStatus(orderStatus).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeCustomer() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByCustomer(customer.getId()).contains(order)).isTrue();
        assertThat(orderRepository.findByCustomer(customer.getId()).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        String newLoginId = "customer";
        String newCustomerPassword = "456";
        String newCustomerName = "new customer name";
        String newCustomerTableNumber = "456";

        List<Order> newCustomerOrders = new ArrayList<>();
        Customer newCustomer = Customer.builder()
                .loginId(newLoginId)
                .password(passwordEncoder.encode(newCustomerPassword))
                .userName(newCustomerName)
                .tableNumber(newCustomerTableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(newCustomerOrders)
                .build();
        em.persist(newCustomer);

        assertThat(em.find(Customer.class, newCustomer.getId())).isEqualTo(newCustomer);

        orderRepository.changeCustomer(orderId, newCustomer);

        assertThat(order.getCustomer()).isEqualTo(newCustomer);
        assertThat(orderRepository.findById(orderId).getCustomer()).isEqualTo(newCustomer);
        assertThat(orderRepository.findByCustomer(newCustomer.getId()).contains(order)).isTrue();
        assertThat(orderRepository.findByCustomer(newCustomer.getId()).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeOrderMenus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findByOrderMenu(orderMenu).contains(order)).isTrue();
        assertThat(orderRepository.findByOrderMenu(orderMenu).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst().orElse(null)).isNotNull();

        List<OrderMenu> newOrderMenus = new ArrayList<>();
        int newOrderMenuOrderPrice = 456;
        int newOrderMenuOrderCount = 456;
        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(newOrderMenuOrderPrice)
                .orderCount(newOrderMenuOrderCount)
                .build();
        newOrderMenus.add(newOrderMenu);
        orderRepository.changeOrderMenus(orderId, newOrderMenus);

        assertThat(order.getOrderMenus().contains(orderMenu)).isFalse();
        assertThat(order.getOrderMenus().contains(newOrderMenu)).isTrue();
    }

    @Test
    void addOrderMenus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findById(orderId).getOrderMenus().contains(orderMenu)).isTrue();

        int newOrderMenuOrderPrice = 123;
        int newOrderMenuOrderCount = 123;
        OrderMenu newOrderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(newOrderMenuOrderPrice)
                .orderCount(newOrderMenuOrderCount)
                .build();
        orderRepository.addOrderMenus(orderId, newOrderMenu);

        assertThat(order.getOrderMenus().contains(orderMenu)).isTrue();
        assertThat(orderRepository.findById(orderId).getOrderMenus().contains(orderMenu)).isTrue();
        assertThat(orderRepository.findById(orderId).getOrderMenus().stream()
                .filter(om -> om.getId().equals(orderMenu.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeOrderDate() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        LocalDateTime newOrderDateTime = LocalDateTime.now();
        orderRepository.changeOrderDate(orderId, newOrderDateTime);

        assertThat(order.getOrderDateTime()).isEqualTo(newOrderDateTime);
        assertThat(orderRepository.findById(orderId).getOrderDateTime()).isEqualTo(newOrderDateTime);
    }

    @Test
    void changeOrderStatus() {
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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
        Recipe recipe = createRecipe();
        em.persist(recipe);

        Menu menu = createMenu(recipe);
        em.persist(menu);

        Customer customer = createCustomer();
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

        Long orderId = orderRepository.save(order);

        assertThat(orderRepository.findById(orderId)).isEqualTo(order);
        assertThat(orderRepository.findById(orderId).getId()).isEqualTo(order.getId());

        orderRepository.removeOrder(order);

        assertThat(orderRepository.findById(orderId)).isNull();
    }
}