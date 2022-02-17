package daepoid.stockManager.repository;

import daepoid.stockManager.domain.StoreUser;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerRepository {

    //==생성 로직==//
    Long save(Customer user);

    //==조회 로직==//
    Customer findById(Long id);
    List<Customer> findAll();
    Customer findByLoginId(String loginId);
    Customer findByUserName(String userName);

    //==생성 로직==//

    //==조회 로직==//
    Customer findByTableNumber(String tableNumber);

    //==수정 로직==//
    void changeUserName(Long userId, String userName);
    void changePassword(Long userId, String password);
    void changeTableNumber(Long customerId, String tableNumber);
    void changeOrders(Long customerId, List<Order> orders);
    void addOrder(Long customerId, Order order);
    void changeCart(Long customerId, Map<Long, Integer> numberOfMenus);

    //==삭제 로직==//
    void removeCustomer(Long userId);
}
