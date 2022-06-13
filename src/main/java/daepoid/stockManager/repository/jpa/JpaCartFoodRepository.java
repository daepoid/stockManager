package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.food.CartFood;
import daepoid.stockManager.repository.CartFoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaCartFoodRepository implements CartFoodRepository {

    private final EntityManager em;

    @Override
    public Long save(CartFood cartFood) {
        em.persist(cartFood);
        return cartFood.getId();
    }

    @Override
    public Optional<CartFood> findById(Long cartFoodId) {
        return Optional.of(em.find(CartFood.class, cartFoodId));
    }

    @Override
    public List<CartFood> findAll() {
        return em.createQuery("select cf from CartFood cf", CartFood.class)
                .getResultList();
    }

    @Override
    public List<CartFood> findAllByCustomer(Long customerId) {
        return em.createQuery("select cf from CartFood cf where cf.customer.id = :customerId", CartFood.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<CartFood> findAllByFood(Long foodId) {
        return em.createQuery("select cf from CartFood cf where cf.food.id = :foodId", CartFood.class)
                .setParameter("foodId", foodId)
                .getResultList();
    }

    @Override
    public void changeCount(Long cartFoodId, Integer count) {
        em.find(CartFood.class, cartFoodId).changeCount(count);
    }

    @Override
    public void remove(Long cartFoodId) {
        em.remove(em.find(CartFood.class, cartFoodId));
    }

    @Override
    public void removeByCustomerId(Long customerId) {
        em.createQuery("delete from CartFood cf where cf.customer.id = :customerId")
                .setParameter("customerId", customerId);
    }
}
