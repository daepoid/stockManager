package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;
import daepoid.stockManager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaOrderRepository implements OrderRepository {

    private final EntityManager em;

    //==생성 로직==//
    @Override
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    //==조회 로직==//
    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    @Override
    public List<Order> findByCustomer(Long customerId) {
        return em.createQuery("select o from Order o where o.customer.id=:customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<Order> findByOrderMenu(OrderMenu orderMenu) {
        List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();
        return orders.stream()
                .filter(order -> order.getOrderMenus().contains(orderMenu))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return em.createQuery("select o from Order o where  o.orderStatus=:orderStatus", Order.class)
                .setParameter("orderStatus", orderStatus)
                .getResultList();
    }

    //==수정 로직==//
    @Override
    public void changeCustomer(Long orderId, Customer customer) {
        em.find(Order.class, orderId).changeCustomer(customer);
    }

    @Override
    public void changeOrderMenus(Long orderId, List<OrderMenu> orderMenus) {
        em.find(Order.class, orderId)
                .changeOrderMenus(orderMenus);
    }

    @Override
    public void addOrderMenus(Long orderId, OrderMenu orderMenu) {
        em.find(Order.class, orderId)
                .addOrderMenu(orderMenu);
    }

    @Override
    public void changeOrderDate(Long orderId, LocalDateTime orderDateTime) {
        em.find(Order.class, orderId)
                .changeOrderDateTime(orderDateTime);
    }

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        em.find(Order.class, orderId)
                .changeOrderStatus(orderStatus);
    }

    //==삭제 로직==//
    @Override
    public void removeOrder(Order order) {
        em.remove(order);
    }
}
