package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.repository.jpa.JpaOrderMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderMenuService {

    private final JpaOrderMenuRepository orderMenuRepository;

    //==생성 메서드==//
    public Long saveOrderMenu(OrderMenu orderMenu) {
        Long savedId = orderMenuRepository.save(orderMenu);
        orderMenuRepository.orderFood(savedId, orderMenu.getOrderCount());
        return savedId;
    }

    //==조회 메서드==//
    public Optional<OrderMenu> findOrderMenu(Long orderMenuId) {
        return orderMenuRepository.findById(orderMenuId);
    }

    public List<OrderMenu> findOrderMenus() {
        return orderMenuRepository.findAll();
    }

    public List<OrderMenu> findOrderMenus(int maxResult) {
        return orderMenuRepository.findAll(maxResult);

    }

    public List<OrderMenu> findOrderMenus(int firstResult, int maxResult) {
        return orderMenuRepository.findAll(firstResult, maxResult);
    }

    public List<OrderMenu> findOrderMenusByOrderId(Long orderId) {
        return orderMenuRepository.findByOrderId(orderId);
    }

    public List<OrderMenu> findOrderMenusByFoodId(Long foodId) {
        return orderMenuRepository.findByFoodId(foodId);
    }

    public List<OrderMenu> findOrderMenusByOrderPrice(Double orderPrice) {
        return orderMenuRepository.findByOrderPrice(orderPrice);
    }

    public List<OrderMenu> findOrderMenusByOrderPriceGreaterThanEqual(Double threshold) {
        return orderMenuRepository.findByOrderPriceGreaterThanEqual(threshold);
    }

    public List<OrderMenu> findOrderMenusByOrderPriceLessThanEqual(Double threshold) {
        return orderMenuRepository.findByOrderPriceLessThanEqual(threshold);
    }

    public List<OrderMenu> findOrderMenusByOrderCount(Integer orderCount) {
        return orderMenuRepository.findByOrderCount(orderCount);
    }

    public List<OrderMenu> findOrderMenusByOrderCountGreaterThanEqual(Integer threshold) {
        return orderMenuRepository.findByOrderCountGreaterThanEqual(threshold);
    }

    public List<OrderMenu> findOrderMenusByOrderCountLessThanEqual(Integer threshold) {
        return orderMenuRepository.findByOrderCountLessThanEqual(threshold);
    }

    //==수정 메서드==//
    public boolean changeFood(Long orderMenuId, Food food) {
        Optional<OrderMenu> orderMenu = orderMenuRepository.findById(orderMenuId);
        if(orderMenu.isPresent()) {
            orderMenu.get().changeFood(food);
            return true;
        }
        return false;
    }

    public boolean changeOrderPrice(Long orderMenuId, Double orderPrice) {
        Optional<OrderMenu> orderMenu = orderMenuRepository.findById(orderMenuId);
        if(orderMenu.isPresent()) {
            orderMenu.get().changeOrderPrice(orderPrice);
            return true;
        }
        return false;
    }

    public boolean changeOrderCount(Long orderMenuId, Integer orderCount) {
        Optional<OrderMenu> orderMenu = orderMenuRepository.findById(orderMenuId);
        if(orderMenu.isPresent()) {
            orderMenu.get().changeOrderCount(orderCount);
            return true;
        }
        return false;
    }

    //==삭제 메서드==//
    public boolean remove(Long orderMenuId) {
        Optional<OrderMenu> orderMenu = orderMenuRepository.findById(orderMenuId);
        if(orderMenu.isPresent()) {
            orderMenuRepository.remove(orderMenuId);
            return true;
        }
        return false;
    }
}
