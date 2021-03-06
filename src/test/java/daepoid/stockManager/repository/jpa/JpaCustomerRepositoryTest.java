package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.users.GradeType;
import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.food.Menu;
import daepoid.stockManager.domain.food.FoodStatus;
import daepoid.stockManager.domain.users.Customer;

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
class JpaCustomerRepositoryTest {

    @Autowired
    JpaCustomerRepository customerRepository;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Menu createMenu() {
        String menuName = "menu name";
        Set<Recipe> menuFoods = new HashSet<>();
        int menuPrice = 123;
        Map<Long, Integer> numberOfFoods = new HashMap<>();
        LocalDateTime menuAddedDate = LocalDateTime.now();
        int menuSalesCount = 123;
        FoodStatus menuFoodStatus = FoodStatus.ORDERABLE;

        return Menu.builder()
                .name(menuName)
                .foods(menuFoods)
                .price(menuPrice)
                .numberOfFoods(numberOfFoods)
                .addedDate(menuAddedDate)
                .salesCount(menuSalesCount)
                .menuStatus(menuFoodStatus)
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
    public void save_??????() throws Exception {
        // given

        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
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
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
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
        String loginId1 = "customer1";
        String password1 = "1231";
        String userName1 = "customer1";
        String tableNumber1 = "1231";
        List<Order> orders1 = new ArrayList<>();

        Customer customer1 = Customer.builder()
                .loginId(loginId1)
                .password(passwordEncoder.encode(password1))
                .userName(userName1)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber1)
                .cart(new Cart(new HashMap<>()))
                .orders(orders1)
                .build();

        String loginId2 = "customer2";
        String password2 = "1232";
        String userName2 = "customer2";
        String tableNumber2 = "1232";
        List<Order> orders2 = new ArrayList<>();

        Customer customer2 = Customer.builder()
                .loginId(loginId2)
                .password(passwordEncoder.encode(password2))
                .userName(userName2)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber2)
                .cart(new Cart(new HashMap<>()))
                .orders(orders2)
                .build();

        // when
        int size = customerRepository.findAll().size();
        Long customer1Id = customerRepository.save(customer1);
        Long customer2Id = customerRepository.save(customer2);

        // then

        // customer1??? ????????????????????? contains??? ?????? ?????? ??? ??????
        assertThat(customerRepository.findAll().contains(customer1)).isTrue();
        assertThat(customerRepository.findAll().stream()
                .filter(c -> c.getId().equals(customer1Id))
                .findFirst().orElse(null)).isNotNull();

        // customer2??? ???????????????????????? contains??? ?????? ?????? ??? ??????.
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
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        // then
        assertThat(customerRepository.findByUserName(userName)).isEqualTo(customer);
    }

    @Test
    void findByTableNumber() {
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        Long customerId = customerRepository.save(customer);

        assertThat(customerRepository.findByTableNumber(tableNumber)).isEqualTo(customer);
        assertThat(customerRepository.findByTableNumber(tableNumber).getId()).isEqualTo(customerId);
    }

    @Test
    public void changeName() throws Exception {
        // given
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        String newName = "new customer Name";
        customerRepository.changeUserName(customerId, newName);
    
        // then
        assertThat(customerRepository.findById(customerId).getUserName()).isEqualTo(newName); // ?????????
        assertThat(customerRepository.findByUserName(newName).getId()).isEqualTo(customer.getId());
    }

    @Test
    public void changeTableNumber() throws Exception {
        // given
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);
        String newTableNumber = "456";
        customerRepository.changeTableNumber(customerId, newTableNumber);

        // then
        assertThat(customerRepository.findById(customerId).getTableNumber()).isEqualTo(newTableNumber);
        assertThat(customerRepository.findByTableNumber(newTableNumber).getId()).isEqualTo(customer.getId());
    }

    @Test
    public void changeOrders() throws Exception {
        // given
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        Menu menu = createMenu();
        em.persist(menu);

        OrderMenu orderMenu = createOrderMenu(menu);

        Order order = createOrder(orderMenu);
        em.persist(order);

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
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        Menu menu = createMenu();
        em.persist(menu);

        OrderMenu orderMenu = createOrderMenu(menu);

        Order order = createOrder(orderMenu);
        em.persist(order);

        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isFalse();
        assertThat(customerRepository.findById(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNull();

        customerRepository.addOrder(customerId, order);

        // then
        assertThat(customer.getOrders().contains(order)).isTrue();
        assertThat(customer.getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(customerRepository.findById(customerId).getOrders().contains(order)).isTrue();
        assertThat(customerRepository.findById(customerId).getOrders().stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void removeCustomer() throws Exception {
        // given
        String loginId = "customer";
        String password = "123";
        String userName = "customer";
        String tableNumber = "123";
        List<Order> orders = new ArrayList<>();

        Customer customer = Customer.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .gradeType(GradeType.CUSTOMER)
                .tableNumber(tableNumber)
                .cart(new Cart(new HashMap<>()))
                .orders(orders)
                .build();

        // when
        Long customerId = customerRepository.save(customer);

        assertThat(customerRepository.findById(customerId)).isNotNull();

        customerRepository.remove(customerId);
    
        // then
        assertThat(customerRepository.findById(customerId)).isNull();
    }
}