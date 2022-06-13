package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.CartFood;
import daepoid.stockManager.repository.jpa.JpaCartFoodRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartFoodService {

    private final JpaCartFoodRepository cartFoodRepository;

    //=생성 메서드==//
    public Long save(CartFood cartFood) {
        return cartFoodRepository.save(cartFood);
    }

    //==조회 메서드==//
    public Optional<CartFood> findCartFood(Long cartFoodId) {
        return cartFoodRepository.findById(cartFoodId);
    }

    public List<CartFood> findCartFoods() {
        return cartFoodRepository.findAll();
    }

    public List<CartFood> findCartFoodsByCustomer(Long customerId) {
        return cartFoodRepository.findAllByCustomer(customerId);
    }

    public List<CartFood> findCartFoodsByFood(Long foodId) {
        return cartFoodRepository.findAllByFood(foodId);
    }

    //==수정 메서드==//
    public boolean changeCount(Long cartFoodId, Integer count) {
        Optional<CartFood> cartFood = cartFoodRepository.findById(cartFoodId);
        if(cartFood.isPresent()) {
            cartFood.get().changeCount(count);
            return true;
        }
        return false;
    }

    //==삭제 메서드==//
    public boolean remove(Long cartFoodId) {
        Optional<CartFood> cartFood = cartFoodRepository.findById(cartFoodId);
        if(cartFood.isPresent()) {
            cartFoodRepository.remove(cartFoodId);
            return true;
        }
        return false;
    }

    public void removeByCustomerId(Long customerId) {
        cartFoodRepository.removeByCustomerId(customerId);
    }
}
