package daepoid.stockManager.repository;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.search.ManagerOrderSearch;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {

    //==생성 로직==//
    Long save(Order order);

    //==조회 로직==//
    Order findById(Long id);
    List<Order> findAll();
    List<Order> findAll(int maxResult);
    List<Order> findAll(int firstResult, int maxResult);

    List<Order> findByCustomer(Long customerId);
    List<Order> findByOrderMenu(OrderMenu orderMenu);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByManagerOrderSearch(ManagerOrderSearch orderSearch);

    //==수정 로직==//
    void changeCustomer(Long orderId, Customer customer);

    void changeOrderMenus(Long orderId, List<OrderMenu> orderMenus);
    void addOrderMenus(Long orderId, OrderMenu orderMenu);

    void changeOrderDate(Long orderId, LocalDateTime orderDateTime);
    void changeOrderStatus(Long orderId, OrderStatus orderStatus);

    //==삭제 로직==//
    void removeOrder(Order order);
}
