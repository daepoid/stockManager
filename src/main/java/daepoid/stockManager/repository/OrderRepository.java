package daepoid.stockManager.repository;

import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.search.ManagerOrderSearch;
import daepoid.stockManager.domain.users.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    //==생성 로직==//
    Long save(Order order);

    //==조회 로직==//
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findAll(int maxResult);
    List<Order> findAll(int firstResult, int maxResult);

    List<Order> findByCustomer(Long customerId);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByOrderDateTimeAfterThanEqual(LocalDateTime orderDateTime);
    List<Order> findByOrderDateTimeBeforeThanEqual(LocalDateTime orderDateTime);
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByManagerOrderSearch(ManagerOrderSearch orderSearch);
    List<Order> findByTotalOrderPriceGreaterThanEqual(Double totalOrderPrice);
    List<Order> findByTotalOrderPriceLessThanEqual(Double totalOrderPrice);

    //==수정 로직==//
    void changeCustomer(Long orderId, Customer customer);
    void changeOrderStatus(Long orderId, OrderStatus orderStatus);
    void changeTotalOrderPrice(Long orderId, Double totalOrderPrice);

    //==삭제 로직==//
    void remove(Long orderId);
}
