package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.FoodStatus;
import daepoid.stockManager.domain.food.FoodType;
import daepoid.stockManager.domain.search.FoodSearch;
import daepoid.stockManager.repository.jpa.JpaFoodRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodService {

    private final JpaFoodRepository foodRepository;

    //==생성 로직==//
    @Transactional
    public Long saveFood(Food food) {
        return foodRepository.save(food);
    }

    //==조회 로직==//
    public Optional<Food> findFood(Long foodId) {
        return foodRepository.findById(foodId);
    }

    public List<Food> findFoods() {
        return foodRepository.findAll();
    }

    public List<Food> findFoods(int maxResult) {
        return foodRepository.findAll(maxResult);
    }

    public List<Food> findFoods(int firstResult, int maxResult) {
        return foodRepository.findAll(firstResult, maxResult);
    }

    public List<Food> findFoodsByRecentAddedDate(LocalDateTime addedDate) {
        return foodRepository.findAllByRecentAddedDate(addedDate);
    }

    public List<Food> findFoodsBySalesCountLessThanEqual(Integer salesCount) {
        return foodRepository.findAllBySalesCountLessThanEqual(salesCount);
    }

    public List<Food> findFoodsBySalesCountGreaterThanEqual(Integer salesCount) {
        return foodRepository.findAllBySalesCountGreaterThanEqual(salesCount);
    }

    public List<Food> findFoodsByFoodStatus(FoodStatus foodStatus) {
        return foodRepository.findAllByFoodStatus(foodStatus);
    }

    public List<Food> findFoodsOrderableMenu() {
        return foodRepository.findOrderableMenu();
    }

    //==고객 제공 정보 관련 조회 메서드==//
    public List<Food> findFoodsByFoodName(String foodName) {
        return foodRepository.findAllByFoodName(foodName);
    }

    public List<Food> findFoodsLikeFoodName(String foodName) {
        return foodRepository.findAllLikeFoodName(foodName);
    }

    public List<Food> findFoodsByDishType(DishType dishType) {
        return foodRepository.findAllByDishType(dishType);
    }

    public List<Food> findFoodsByFoodType(FoodType foodType) {
        return foodRepository.findAllByFoodType(foodType);
    }

    //==관리자 제공 정보 조회 메서드==//
    public List<Food> findFoodsByFoodCostLessThanEqual(Double threshold) {
        return foodRepository.findAllByFoodCostLessThanEqual(threshold);
    }

    public List<Food> findFoodsByFoodCostGreaterThanEqual(Double threshold) {
        return foodRepository.findAllByFoodCostGreaterThanEqual(threshold);
    }

    public List<Food> findFoodsByIncomeLessThanEqual(Double threshold) {
        return foodRepository.findAllByIncomeLessThanEqual(threshold);
    }

    public List<Food> findFoodsByIncomeGreaterThanEqual(Double threshold) {
        return foodRepository.findAllByIncomeGreaterThanEqual(threshold);
    }

    public List<Food> findFoodsOrderByIncome(boolean orderOption) {
        return foodRepository.findAllFoodOrderByIncome(orderOption);
    }

    //==검색 조회 메서드
    public List<Food> findFoodsByFoodSearch(FoodSearch foodSearch) {
        return foodRepository.findAllByFoodSearch(foodSearch);
    }

    //==기본 정보 수정 메서드==//
    @Transactional
    public boolean changeSalesCount(Long foodId, Integer salesCount) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeSalesCount(salesCount);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeFoodStatus(Long foodId, FoodStatus foodStatus) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeFoodStatus(foodStatus);
            return true;
        }
        return false;
    }

    //==고객 제공 정보 수정 메서드==//
    @Transactional
    public boolean changeFoodName(Long foodId, String foodName) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeFoodName(foodName);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeFoodPrice(Long foodId, Double foodPrice) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeFoodPrice(foodPrice);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeFoodInfo(Long foodId, String foodInfo) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeFoodInfo(foodInfo);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeImgUrl(Long foodId, String imgUrl) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeImgUrl(imgUrl);
            return true;
        }
        return false;
    }

    //==직원 제공 정보 수정 메서드==//
    @Transactional
    public boolean changeRecipe(Long foodId, String recipe) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeRecipe(recipe);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeNotice(Long foodId, String notice) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeNotice(notice);
            return true;
        }
        return false;
    }

    //==관리자 제공 정보 수정 메서드==//
    @Transactional
    public boolean changeFoodCost(Long foodId, Double foodCost) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            food.get().changeFoodCost(foodCost);
            return true;
        }
        return false;
    }

    //==삭제 로직==//
    @Transactional
    public boolean remove(Long foodId) {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()) {
            foodRepository.remove(foodId);
            return true;
        }
        return false;
    }
}
