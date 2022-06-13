package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.domain.search.CustomerSearch;
import daepoid.stockManager.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Optional<Customer> findById(Long customerId) {
        return Optional.of(em.find(Customer.class, customerId));
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public List<Customer> findAll(int maxResult) {
        return em.createQuery("select c from Customer c", Customer.class)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<Customer> findAll(int firstResult, int maxResult) {
        return em.createQuery("select c from Customer c", Customer.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public Optional<Customer> findByLoginId(String loginId) {
        return em.createQuery("select c from Customer c where c.loginId=:loginId", Customer.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    public Optional<Customer> findByUserName(String userName) {
        return em.createQuery("select c from Customer c where c.userName=:userName", Customer.class)
                .setParameter("userName", userName)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    public Optional<Customer> findByTableNumber(String tableNumber) {
        return em.createQuery("select c from Customer c where c.tableNumber=:tableNumber", Customer.class)
                .setParameter("tableNumber", tableNumber)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    public List<Customer> findCustomerByCustomerSearch(CustomerSearch customerSearch) {
        String jpql = "select c from Customer c";

        if(StringUtils.hasText(customerSearch.getName())) {
            jpql += " where c.userName like :name";
        }

        TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);

        if(StringUtils.hasText(customerSearch.getName())) {
            query = query.setParameter("name", "%" + customerSearch.getName() + "%");
        }
        return query.getResultList();
    }

    @Override
    public void changePassword(Long userId, String password) {
        em.find(Customer.class, userId).changePassword(password);
    }

    @Override
    public void changeUserName(Long userId, String userName) {
        em.find(Customer.class, userId).changeUserName(userName);
    }

    @Override
    public void changeGradeType(Long userId, GradeType gradeType) {
        em.find(Customer.class, userId).changeGradeType(gradeType);
    }

    @Override
    public void changeTableNumber(Long customerId, String tableNumber) {
        em.find(Customer.class, customerId).changeTableNumber(tableNumber);
    }

    @Override
    public void changeExpirationTime(Long customerId, LocalDateTime expirationTime) {
        em.find(Customer.class, customerId).changeExpirationTime(expirationTime);
    }

    @Override
    public void remove(Long userId) {
        em.remove(em.find(Customer.class, userId));
    }
}
