package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaCustomerRepository implements CustomerRepository {

    private final EntityManager em;

    @Override
    public Long save(Customer customer) {
        if(customer.getId() == null) {
            em.persist(customer);
        }
        return customer.getId();
    }

    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public Customer findByName(String name) {
        return em.createQuery("select c from Customer c where c.name=:name", Customer.class)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst().orElse(null);
    }

    @Override
    public Customer findByTableNumber(int tableNumber) {
        return em.createQuery("select c from Customer c where c.tableNumber=:tableNumber", Customer.class)
                .setParameter("tableNumber", tableNumber)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public void changeId(Long customerId, Long changeId) {
        em.find(Customer.class, customerId).changeId(changeId);
    }

    @Override
    public void changeName(Long customerId, String name) {
        em.find(Customer.class, customerId).changeName(name);
    }

    @Override
    public void changeTableNumber(Long customerId, int tableNumber) {
        em.find(Customer.class, customerId).changeTableNumber(tableNumber);
    }

    @Override
    public void changeOrders(Long customerId, List<Order> orders) {
        em.find(Customer.class, customerId).changeOrders(orders);
    }

    @Override
    public void addOrder(Long customerId, Order order) {
        em.find(Customer.class, customerId).addOrder(order);
    }

    @Override
    public void removeCustomer(Customer customer) {
        em.remove(customer);
    }
}
