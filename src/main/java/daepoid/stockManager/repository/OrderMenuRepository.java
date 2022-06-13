package daepoid.stockManager.repository;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.order.OrderMenu;

import java.util.List;
import java.util.Optional;

public interface OrderMenuRepository {

    //==생성 로직==//
    Long save(OrderMenu orderMenu);

    //==조회 로직==//
    Optional<OrderMenu> findById(Long id);
    List<OrderMenu> findAll();
    List<OrderMenu> findAll(int maxResult);
    List<OrderMenu> findAll(int firstResult, int maxResult);

    List<OrderMenu> findByOrderId(Long orderId);
    List<OrderMenu> findByFoodId(Long foodId);
    List<OrderMenu> findByOrderPrice(Double orderPrice);
    List<OrderMenu> findByOrderPriceGreaterThanEqual(Double threshold);
    List<OrderMenu> findByOrderPriceLessThanEqual(Double threshold);
    List<OrderMenu> findByOrderCount(Integer orderCount);
    List<OrderMenu> findByOrderCountGreaterThanEqual(Integer threshold);
    List<OrderMenu> findByOrderCountLessThanEqual(Integer threshold);

    //==수정 로직==//
    void changeFood(Long orderMenuId, Food food);
    void changeOrderPrice(Long orderMenuId, Double orderPrice);
    void changeOrderCount(Long orderMenuId, Integer orderCount);
    void orderFood(Long orderMenuId, Integer orderCount);

    //==삭제 로직==//
    void remove(Long orderMenuId);
}
