package daepoid.stockManager.repository;

import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.FoodType;
import daepoid.stockManager.domain.search.FoodSearch;
import daepoid.stockManager.domain.food.FoodStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FoodRepository {

    //==생성 로직==//
    Long save(Food food);

    //==조회 로직==//
    Optional<Food> findById(Long foodId);
    List<Food> findAll();
    List<Food> findAll(int maxResult);
    List<Food> findAll(int firstResult, int maxResult);

    List<Food> findAllByRecentAddedDate(LocalDateTime addedDate);
    List<Food> findAllBySalesCountLessThanEqual(Integer salesCount);
    List<Food> findAllBySalesCountGreaterThanEqual(Integer salesCount);
    List<Food> findAllByFoodStatus(FoodStatus foodStatus);
    List<Food> findOrderableMenu();

    //==고객 제공 정보 관련 조회 메서드==//
    List<Food> findAllByFoodName(String foodName);
    List<Food> findAllLikeFoodName(String foodName);
    List<Food> findAllByDishType(DishType dishType);
    List<Food> findAllByFoodType(FoodType foodType);

    //==관리자 제공 정보 조회 메서드==//
    List<Food> findAllByFoodCostLessThanEqual(Double foodCost);
    List<Food> findAllByFoodCostGreaterThanEqual(Double foodCost);
    List<Food> findAllByIncomeLessThanEqual(Double threshold);
    List<Food> findAllByIncomeGreaterThanEqual(Double threshold);

    List<Food> findAllFoodOrderByIncome(boolean orderOption);

    //==검색 조회 메서드
    List<Food> findAllByFoodSearch(FoodSearch foodSearch);

    //==기본 정보 수정 메서드==//
    void changeSalesCount(Long foodId, Integer salesCount);
    void changeFoodStatus(Long foodId, FoodStatus foodStatus);

    //==고객 제공 정보 수정 메서드==//
    void changeFoodName(Long foodId, String foodName);
    void changeFoodPrice(Long foodId, Double foodPrice);
    void changeFoodInfo(Long foodId, String foodInfo);
    void changeImgUrl(Long foodId, String imgUrl);

    //==직원 제공 정보 수정 메서드==//
    void changeRecipe(Long foodId, String recipe);

    void changeNotice(Long foodId, String notice);

    //==관리자 제공 정보 수정 메서드==//
    void changeFoodCost(Long foodId, Double foodCost);

    //==삭제 로직==//
    void remove(Long foodId);
}
