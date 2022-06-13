package daepoid.stockManager.repository;

import daepoid.stockManager.domain.food.CartFood;

import java.util.List;
import java.util.Optional;

public interface CartFoodRepository {

    //=생성 메서드==//
    Long save(CartFood cartFood);

    //==조회 메서드==//
    Optional<CartFood> findById(Long cartFoodId);
    List<CartFood> findAll();
    List<CartFood> findAllByCustomer(Long customerId);
    List<CartFood> findAllByFood(Long foodId);

    //==수정 메서드==//
    void changeCount(Long cartFoodId, Integer count);

    //==삭제 메서드==//
    void remove(Long cartFoodId);
    void removeByCustomerId(Long customerId);
}
