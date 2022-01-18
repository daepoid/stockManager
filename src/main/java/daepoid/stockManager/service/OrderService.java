package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.repository.CustomerRepository;
import daepoid.stockManager.repository.OrderRepository;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;
import daepoid.stockManager.repository.jpa.JpaMenuRepository;
import daepoid.stockManager.repository.jpa.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class OrderService {

    private final JpaOrderRepository orderRepository;
    private final JpaCustomerRepository customerRepository;
    private final JpaMenuRepository menuRepository;

    //==비즈니스 로직==//
    @Transactional
    public Long order(Long customerId, Long menuId, Integer count) {
        // 엔티티 조회
        Customer customer = customerRepository.findById(customerId);
        Menu menu = menuRepository.findById(menuId);

        // 주문 메뉴 단일 생성
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(menu.getPrice())
                .orderCount(count)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);

        // 주문 생성
        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void orders(Long customerId) {
        // 엔티티 조회
        Customer customer = customerRepository.findById(customerId);
        Map<Long, Integer> numberOfMenus = customer.getCart().getNumberOfMenus();

        for (Long menuId : numberOfMenus.keySet()) {
            Menu menu = menuRepository.findById(menuId);

            // 주문 메뉴 단일 생성
            OrderMenu orderMenu = OrderMenu.builder()
                    .menu(menu)
                    .orderPrice(menu.getPrice())
                    .orderCount(numberOfMenus.get(menuId))
                    .build();

            List<OrderMenu> orderMenus = new ArrayList<>();
            orderMenus.add(orderMenu);

            // 주문 생성
            Order order = Order.builder()
                    .customer(customer)
                    .orderMenus(orderMenus)
                    .orderDateTime(LocalDateTime.now())
                    .orderStatus(OrderStatus.ORDERED)
                    .build();

            // 주문 저장
            orderRepository.save(order);
        }
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }


    //==생성 로직==//
    @Transactional
    public Long save(Order order) {
        return orderRepository.save(order);
    }

    //==조회 로직==//
    public Order findOrder(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> findByOrderMenu(OrderMenu orderMenu) {
        return orderRepository.findByOrderMenu(orderMenu);
    }

    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    //==수정 로직==//
    @Transactional
    public void changeCustomer(Long orderId, Customer customer) {
        orderRepository.changeCustomer(orderId, customer);
    }

    @Transactional
    public void changeOrderMenus(Long orderId, List<OrderMenu> orderMenus) {
        orderRepository.changeOrderMenus(orderId, orderMenus);
    }

    @Transactional
    public void addOrderMenus(Long orderId, OrderMenu orderMenu) {
        orderRepository.addOrderMenus(orderId, orderMenu);
    }

    @Transactional
    public void changeOrderDate(Long orderId, LocalDateTime orderDateTime) {
        orderRepository.changeOrderDate(orderId, orderDateTime);
    }

    @Transactional
    public void changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        orderRepository.changeOrderStatus(orderId, orderStatus);
    }

    //==삭제 로직==//
    @Transactional
    public void removeOrder(Order order) {
        orderRepository.removeOrder(order);
    }
}
