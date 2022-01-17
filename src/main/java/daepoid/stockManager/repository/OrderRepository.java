package daepoid.stockManager.repository;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {

    //==생성 로직==//
    Long save(Order order);

    //==조회 로직==//
    Order findById(Long id);
    List<Order> findAll();

    List<Order> findByCustomer(Customer customer);
    List<Order> findByOrderMenu(OrderMenu orderMenu);
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    //==수정 로직==//
    void changeCustomer(Long orderId, Customer customer);

    void changeOrderMenus(Long orderId, List<OrderMenu> orderMenus);
    void addOrderMenus(Long orderId, OrderMenu orderMenu);

    void changeOrderDate(Long orderId, LocalDateTime orderDateTime);
    void changeOrderStatus(Long orderId, OrderStatus orderStatus);

    //==삭제 로직==//
    void removeOrder(Order order);
}
