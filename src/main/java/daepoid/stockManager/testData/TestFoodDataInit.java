package daepoid.stockManager.testData;

import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.FoodStatus;

import daepoid.stockManager.domain.food.FoodType;
import daepoid.stockManager.service.FoodService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestFoodDataInit {

    private final FoodService foodService;

    @PostConstruct
    public void init() {

        LocalDateTime now = LocalDateTime.now();
        foodService.saveFood(
                Food.builder()
                        .addedDate(now)
                        .salesCount(0)
                        .foodStatus(FoodStatus.ORDERABLE)
                        .foodName("음식 1")
                        .foodPrice(0.0)
                        .dishType(DishType.BOWL)
                        .foodType(FoodType.DESSERT)
                        .foodInfo("food info")
                        .imgUrl("url")
                        .recipe("How to make food : ")
                        .notice("Note")
                        .foodCost(0.0)
                        .income(0.0)
                        .build()
        );

        foodService.saveFood(
                Food.builder()
                        .addedDate(now)
                        .salesCount(0)
                        .foodStatus(FoodStatus.ORDERABLE)
                        .foodName("음식 2")
                        .foodPrice(0.0)
                        .dishType(DishType.MAIN)
                        .foodType(FoodType.MAIN)
                        .foodInfo("food info")
                        .imgUrl("url")
                        .recipe("How to make food : ")
                        .notice("Note")
                        .foodCost(0.0)
                        .income(0.0)
                        .build()
        );

        foodService.saveFood(
                Food.builder()
                        .addedDate(now)
                        .salesCount(0)
                        .foodStatus(FoodStatus.ORDERABLE)
                        .foodName("음식 3")
                        .foodPrice(0.0)
                        .dishType(DishType.GRAVY)
                        .foodType(FoodType.SAUCE)
                        .foodInfo("food info")
                        .imgUrl("url")
                        .recipe("How to make food : ")
                        .notice("Note")
                        .foodCost(0.0)
                        .income(0.0)
                        .build()
        );

        foodService.saveFood(
                Food.builder()
                        .addedDate(now)
                        .salesCount(0)
                        .foodStatus(FoodStatus.ORDERABLE)
                        .foodName("음식 4")
                        .foodPrice(0.0)
                        .dishType(DishType.MEAT)
                        .foodType(FoodType.MEAT)
                        .foodInfo("food info")
                        .imgUrl("url")
                        .recipe("How to make food : ")
                        .notice("Note")
                        .foodCost(0.0)
                        .income(0.0)
                        .build()
        );

        foodService.saveFood(
                Food.builder()
                        .addedDate(now)
                        .salesCount(0)
                        .foodStatus(FoodStatus.ORDERABLE)
                        .foodName("음식 5")
                        .foodPrice(0.0)
                        .dishType(DishType.PLATE)
                        .foodType(FoodType.PLATTER)
                        .foodInfo("food info")
                        .imgUrl("url")
                        .recipe("How to make food : ")
                        .notice("Note")
                        .foodCost(0.0)
                        .income(0.0)
                        .build()
        );
    }
}
