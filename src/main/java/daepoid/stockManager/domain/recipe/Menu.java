package daepoid.stockManager.domain.recipe;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

//    @NotNull
    private int price = 0;

    @ElementCollection
    @JoinTable(
            name="menu_number_of_food",
            joinColumns=@JoinColumn(name="recipe_id"))
    @MapKeyColumn(name="food_id")
    @Column(name="number")
    // Map<recipeId, food_count>
    private Map<Long, Integer> numberOfFoods = new HashMap<>();

    // 최근에 추가된 메뉴를 확인하기 위해
    @NotNull
    private LocalDateTime addedDate;

    // 주문이 많이 된 음식을 찾기 위해
//    @NotNull
    private int salesCount = 0;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MenuStatus menuStatus = MenuStatus.ORDERABLE;

    @Builder
    public Menu(String name, Set<Recipe> foods, int price, Map<Long, Integer> numberOfFoods,
                LocalDateTime addedDate, int salesCount, MenuStatus menuStatus) {
        this.name = name;
        this.price = price;
        this.foods = foods;
        this.numberOfFoods = numberOfFoods;
        this.addedDate = addedDate;
        this.salesCount = salesCount;
        this.menuStatus = menuStatus;
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
        for (Recipe food : foods) {
            food.getMenus().add(this);
        }
    }

    public void changeNumberOfFood(Map<Long, Integer> numberOfFoods) {
        this.numberOfFoods = numberOfFoods;
    }

    public void changeAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public void changeSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public void changeMenuStatus(MenuStatus menuStatus) {
        this.menuStatus = menuStatus;
    }

    public void updatePrice() {
        int sum = 0;
        for (Recipe food : foods) {
            Integer numberOfFoodsInt = this.numberOfFoods.get(food.getId());
            if(numberOfFoodsInt != null) {
                sum += numberOfFoodsInt * food.getPrice();
            }
        }

        this.price = sum;
    }

    public void addSalesCount(int newSalesCount) {
        this.salesCount += newSalesCount;
    }

    public void cancelSalesCount(int cancelSalesCount) {
        this.salesCount -= cancelSalesCount;
    }

    public void addFood(Recipe food, Integer numberOfFoods) {
        this.getFoods().add(food);
        this.getNumberOfFoods().put(food.getId(), numberOfFoods);
    }

    public void removeFood(Recipe food) {
        this.getFoods().remove(food);
    }

    // 메뉴 주문 취소
    public void cancelMenu(int newSalesCount) {
        for (Recipe food : foods) {
            food.cancelRecipe(newSalesCount);
        }
    }
}
