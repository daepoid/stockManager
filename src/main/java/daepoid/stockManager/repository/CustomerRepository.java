package daepoid.stockManager.repository;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    //==생성 로직==//
    Long save(Customer customer);

    //==조회 로직==//
    Customer findById(Long id);
    List<Customer> findAll();

    Customer findByName(String name);
    Customer findByTableNumber(int tableNumber);

    //==수정 로직==//
    void changeId(Long customerId, Long changeId);
    void changeName(Long customerId, String name);
    void changeTableNumber(Long customerId, int tableNumber);
    void changeOrders(Long customerId, List<Order> orders);
    void addOrder(Long customerId, Order order);

    //==삭제 로직==//
    void removeCustomer(Customer customer);
}
