package daepoid.stockManager.service;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.search.ManagerOrderSearch;
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
    public Long order(Long customerId, Long menuId, int orderCount, LocalDateTime orderDateTime) {
        // 엔티티 조회
        Customer customer = customerRepository.findById(customerId);
        Menu menu = menuRepository.findById(menuId);

        // 주문 메뉴 단일 생성
        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .orderPrice(menu.getPrice())
                .orderCount(orderCount)
                .build();

        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        menuRepository.addSalesCount(menuId, orderCount);

        // 주문 생성
        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(OrderStatus.ORDERED)
                .build();

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public Long orders(Long customerId) {

        // 엔티티 조회
        Customer customer = customerRepository.findById(customerId);
        if(customer == null) {
            return null;
        }

        Cart cart = customer.getCart();
        if(cart == null || cart.getNumberOfMenus().size() < 1) {
            return null;
        }

        Map<Long, Integer> numberOfMenus = cart.getNumberOfMenus();
        LocalDateTime orderDateTime = LocalDateTime.now();

        List<OrderMenu> orderMenus = new ArrayList<>();

        for (Long menuId : numberOfMenus.keySet()) {
            Menu menu = menuRepository.findById(menuId);

            // 주문 메뉴 단일 생성
            orderMenus.add(
                    OrderMenu.builder()
                            .menu(menu)
                            .orderPrice(menu.getPrice())
                            .orderCount(numberOfMenus.get(menuId))
                            .build()
            );
        }

        // 주문 생성 및 저장

        Order order = Order.builder()
                .customer(customer)
                .orderMenus(orderMenus)
                .orderDateTime(orderDateTime)
                .orderStatus(OrderStatus.ORDERED)
                .build();
        Long orderId = orderRepository.save(order);

        // 주문 환료 후 장바구니 비우기
        cart.clearCart();

        // 주문 수량 카운팅
        for (OrderMenu orderMenu : orderMenus) {
            menuRepository.addSalesCount(orderMenu.getMenu().getId(), orderMenu.getOrderCount());
        }

        return orderId;
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        List<OrderMenu> orderMenus = order.getOrderMenus();
        for (OrderMenu orderMenu : orderMenus) {
            menuRepository.cancelSalesCount(orderMenu.getMenu().getId(), orderMenu.getOrderCount());
        }
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

    public List<Order> findOrders(int maxResult) {
        return orderRepository.findAll(maxResult);
    }

    public List<Order> findOrders(int firstResult, int maxResult) {
        return orderRepository.findAll(firstResult, maxResult);
    }

    public List<Order> findByCustomer(Long customerId) {
        return orderRepository.findByCustomer(customerId);
    }

    public List<Order> findByOrderMenu(OrderMenu orderMenu) {
        return orderRepository.findByOrderMenu(orderMenu);
    }

    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    public List<Order> findByManagerOrderSearch(ManagerOrderSearch orderSearch) {
        return orderRepository.findByManagerOrderSearch(orderSearch);
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
