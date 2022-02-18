package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderSearch;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaCustomerRepository implements CustomerRepository {

    private final EntityManager em;

    @Override
    public Long save(Customer user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public Customer findById(Long customerId) {
        return em.createQuery("select c from Customer c where c.id=:customerId", Customer.class)
                .setParameter("customerId", customerId)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public Customer findByLoginId(String loginId) {
        return em.createQuery("select c from Customer c where c.loginId=:loginId", Customer.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public Customer findByUserName(String userName) {
        return em.createQuery("select c from Customer c where c.userName=:userName", Customer.class)
                .setParameter("userName", userName)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public Customer findByTableNumber(String tableNumber) {
        return em.createQuery("select c from Customer c where c.tableNumber=:tableNumber", Customer.class)
                .setParameter("tableNumber", tableNumber)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public void changeUserName(Long userId, String userName) {
        em.find(Customer.class, userId).changeUserName(userName);
    }

    @Override
    public void changePassword(Long userId, String password) {
        em.find(Customer.class, userId).changePassword(password);
    }

    @Override
    public void changeTableNumber(Long customerId, String tableNumber) {
        em.find(Customer.class, customerId).changeTableNumber(tableNumber);
    }

    @Override
    public void changeOrders(Long customerId, List<Order> orders) {
        em.find(Customer.class, customerId).changeOrders(orders);
    }

    @Override
    public void changeCart(Long customerId, Map<Long, Integer> numberOfMenus) {
        em.find(Customer.class, customerId).changeCart(numberOfMenus);
    }

    @Override
    public void addCart(Long customerId, Long menuId, int count) {
        em.find(Customer.class, customerId).addCart(menuId, count);
    }

    @Override
    public void addOrder(Long customerId, Order order) {
        em.find(Customer.class, customerId).addOrder(order);
    }

    @Override
    public void removeCustomer(Long userId) {
        em.remove(em.find(Customer.class, userId));
    }
}
