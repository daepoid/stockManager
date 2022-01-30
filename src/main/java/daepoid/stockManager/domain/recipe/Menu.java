package daepoid.stockManager.domain.recipe;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue
    @Column(name="menu_id")
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(
            name="recipe_foods",
            joinColumns=@JoinColumn(name="menu_id"),
            inverseJoinColumns=@JoinColumn(name="recipe_id")
    )
    private Set<Recipe> foods = new HashSet<>();

    @NotNull
    private int price;

    @ElementCollection
    @JoinTable(
            name="menu_number_of_food",
            joinColumns=@JoinColumn(name="recipe_id"))
    @MapKeyColumn(name="food_id")
    @Column(name="number")
    // Map<recipeId, food_count>
    private Map<Long, Integer> numberOfFood = new HashMap<>();

    // 최근에 추가된 메뉴를 확인하기 위해
    @NotNull
    private LocalDateTime addedDate;

    // 주문이 많이 된 음식을 찾기 위해
    @NotNull
    private int orderCount = 0;

    @Builder
    public Menu(String name, Set<Recipe> foods, int price,
                Map<Long, Integer> numberOfFood, LocalDateTime addedDate, int orderCount) {
        this.name = name;
        this.price = price;
        this.foods = foods;
        this.numberOfFood = numberOfFood;
        this.addedDate = addedDate;
        this.orderCount = orderCount;
    }

    public Integer getPrice() {
        updatePrice();
        return this.price;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(Integer price) {
        this.price = price;
    }

    public void changeFoods(Set<Recipe> foods) {
        this.foods = foods;
    }

    public void changeNumberOfFood(Map<Long, Integer> numberOfFood) {
        this.numberOfFood = numberOfFood;
    }

    public void changeAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public void changeOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void updatePrice() {
        int sum = 0;
        for (Recipe food : foods) {
            Integer numberOfFoodInt = this.numberOfFood.get(food.getId());
            if(numberOfFoodInt != null) {
                sum += numberOfFoodInt * food.getPrice();
            }
        }

        this.price = sum;
    }

    public void addOrderCount(int newOrderCount) {
        this.orderCount += newOrderCount;
    }

    public void cancelOrderCount(int cancelOrderCount) {
        this.orderCount -= cancelOrderCount;
    }

    public void addFood(Recipe food, Integer numberOfFood) {
        this.getFoods().add(food);
        this.getNumberOfFood().put(food.getId(), numberOfFood);
    }

    public void removeFood(Recipe food) {
        this.getFoods().remove(food);
    }

    // 메뉴 주문 취소
    public void cancelMenu(int orderCount) {
        for (Recipe food : foods) {
            food.cancelRecipe(orderCount);
        }
    }

}
