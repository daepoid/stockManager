package daepoid.stockManager.domain.food;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "food_id")
    private Long id;

    @NotNull
    private LocalDateTime addedDate;

    @NotNull
    @PositiveOrZero
    private Integer salesCount = 0;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus = FoodStatus.ORDERABLE;

    //==고객 제공 정보==//
    @NotBlank
    private String foodName;

    private Double foodPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Lob
    private String foodInfo;

    private String imgUrl;

    //==직원 제공 정보==//
    @Lob
    private String recipe;

    @Lob
    private String notice;

    //==관리자 제공 정보==//
    @NotNull
    @Positive
    private Double foodCost = 0.0;

    @NotNull
    @PositiveOrZero
    private Double income = 0.0;

    @Builder
    public Food(LocalDateTime addedDate, Integer salesCount, FoodStatus foodStatus,
                String foodName, DishType dishType, FoodType foodType,
                Double foodPrice, String foodInfo, String imgUrl,
                String recipe, String notice,
                Double foodCost, Double income) {
        this.addedDate = addedDate;
        this.salesCount = salesCount;
        this.foodStatus = foodStatus;

        this.foodName = foodName;
        this.dishType = dishType;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
        this.foodInfo = foodInfo;
        this.imgUrl = imgUrl;

        this.recipe = recipe;
        this.notice = notice;

        this.foodCost = foodCost;
        this.income = income;
    }

    //==기본 정보 수정 메서드==//
    public void addSalesCount() {
        this.salesCount++;
    }

    public void addSalesCount(Integer count) {
        this.salesCount += count;
    }

    public void cancelSalesCount() {
        this.salesCount--;
    }

    public void cancelSalesCount(Integer count) {
        this.salesCount -= count;
    }

    public void changeSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public void changeFoodStatus(FoodStatus foodStatus) {
        this.foodStatus = foodStatus;
    }

    //==고객 제공 정보 수정 메서드==//

    public void changeFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void changeFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
        updateIncome(foodPrice, this.foodCost);
    }

    public void changeFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    public void changeImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    //==직원 제공 정보 수정 메서드==//
    public void changeRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void changeNotice(String notice) {
        this.notice = notice;
    }

    //==관리자 제공 정보 수정 메서드==//
    public void changeFoodCost(Double foodCost) {
        this.foodCost = foodCost;
        updateIncome(this.foodPrice, foodCost);
    }

    public void increaseFoodCost(Double foodCost) {
        this.foodCost += foodCost;
    }

    public void reduceFoodCost(Double foodCost) {
        this.foodCost -= foodCost;
    }

    public void updateIncome(Double foodPrice, Double foodCost) {
        this.income = foodPrice - foodCost;
    }
}
