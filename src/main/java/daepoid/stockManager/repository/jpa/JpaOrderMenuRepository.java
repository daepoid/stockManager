package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.repository.OrderMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaOrderMenuRepository implements OrderMenuRepository {

    private final EntityManager em;

    //==생성 메서드==//
    @Override
    public Long save(OrderMenu orderMenu) {
        em.persist(orderMenu);
        return orderMenu.getId();
    }

    //==조회 메서드==//
    @Override
    public Optional<OrderMenu> findById(Long orderMenuId) {
        return Optional.of(em.find(OrderMenu.class, orderMenuId));
    }

    @Override
    public List<OrderMenu> findAll() {
        return em.createQuery("select om from OrderMenu om", OrderMenu.class)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findAll(int maxResult) {
        return em.createQuery("select om from OrderMenu om", OrderMenu.class)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findAll(int firstResult, int maxResult) {
        return em.createQuery("select om from OrderMenu om", OrderMenu.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderId(Long orderId) {
        return em.createQuery("select om from OrderMenu om where om.order.id = :orderId", OrderMenu.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByFoodId(Long foodId) {
        return em.createQuery("select om from OrderMenu om where om.food.id = :foodId", OrderMenu.class)
                .setParameter("foodId", foodId)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderPrice(Double orderPrice) {
        return em.createQuery("select om from OrderMenu om where om.orderPrice = :orderPrice", OrderMenu.class)
                .setParameter("orderPrice", orderPrice)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderPriceGreaterThanEqual(Double threshold) {
        return em.createQuery("select om from OrderMenu om where om.orderPrice >= :threshold", OrderMenu.class)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderPriceLessThanEqual(Double threshold) {
        return em.createQuery("select om from OrderMenu om where om.orderPrice <= :threshold", OrderMenu.class)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderCount(Integer orderCount) {
        return em.createQuery("select om from OrderMenu om where om.orderPrice = :orderCount", OrderMenu.class)
                .setParameter("orderCount", orderCount)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderCountGreaterThanEqual(Integer threshold) {
        return em.createQuery("select om from OrderMenu om where om.orderPrice >= :threshold", OrderMenu.class)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    public List<OrderMenu> findByOrderCountLessThanEqual(Integer threshold) {
        return em.createQuery("select om from OrderMenu om where om.orderPrice <= :threshold", OrderMenu.class)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    //==수정 메서드==//
    @Override
    public void changeFood(Long orderMenuId, Food food) {
        em.find(OrderMenu.class, orderMenuId).changeFood(food);
    }

    @Override
    public void changeOrderPrice(Long orderMenuId, Double orderPrice) {
        em.find(OrderMenu.class, orderMenuId).changeOrderPrice(orderPrice);
    }

    @Override
    public void changeOrderCount(Long orderMenuId, Integer orderCount) {
        em.find(OrderMenu.class, orderMenuId).changeOrderCount(orderCount);
    }

    @Override
    public void orderFood(Long orderMenuId, Integer orderCount) {
        em.find(OrderMenu.class, orderMenuId).changeOrderCount(orderCount);
//        em.find(Food.class, em.find(OrderMenu.class, orderMenuId).getFood().getId()).addSalesCount(orderCount);
    }

    //==삭제 메서드==//
    @Override
    public void remove(Long orderMenuId) {
        em.remove(em.find(OrderMenu.class, orderMenuId));
    }
}
