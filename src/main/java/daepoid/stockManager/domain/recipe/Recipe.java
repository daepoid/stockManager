package daepoid.stockManager.domain.recipe;

import daepoid.stockManager.domain.ingredient.Ingredient;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "recipe_id")
    private Long id;

//    // 레시피 번호
//    @Column(unique = true)
    @NotBlank
    private String recipeNumber;

    // 레시피 이름
    // 매장에서 사용되는 레시피가 무한하지 않고, 관리자가 등록을 하기 때문에 중복 검사를 하지 않는다.
    @NotBlank
    private String name;

    // 판매 가격
    @NotNull
    private int price;

    // 요리 무게
    private double weight;

    // 접시 유형
    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    // 레시피 필요 재료
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    // 레시피 생산 단가
    @NotNull
    private double cost = 0.0;

    @NotNull
    private double netIncome = 0.0;

    @ManyToMany(mappedBy="foods", cascade=CascadeType.ALL)
    private Set<Menu> menus = new HashSet<>();

    // 레시피 방법 또는 주의점
    @Lob
    private String notes;

    @Builder
    public Recipe(String recipeNumber, String name, int price, double weight, DishType dishType,
                  double cost, double netIncome, List<Ingredient> ingredients, Set<Menu> menus, String notes) {
        this.recipeNumber = recipeNumber;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.dishType = dishType;
        this.cost = cost;
        this.netIncome = netIncome;
        this.ingredients = ingredients;
        this.menus = menus;
        this.notes = notes;
    }

    //==개발 로직==//
    public void changeId(Long id) {
        this.id = id;
    }

    //==비즈니스 로직 (setter 제거)==//
    // unique 조건일 때 같은 걸로 바꾸면 어떻게 되는가?
    public void changeRecipeNumber(String recipeNumber) {
        this.recipeNumber = recipeNumber;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeWeight(double weight) {
        this.weight = weight;
    }

    public void changeIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void changeDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void changeNotes(String notes) {
        this.notes = notes;
    }

    public void changeCost(double cost) {
        this.cost = cost;
    }

    public void changeNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }

    //==연관 관계 메서드==//
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.changeRecipe(this);
    }

    public Boolean removeIngredient(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    //==서비스 로직==//
    public double getCost() {
        this.cost = ingredients.stream()
                .mapToDouble(Ingredient::getCost)
                .sum();
        return this.cost;
    }

    public void updateCost() {
        this.netIncome = this.price - getCost();
    }

    public boolean hasIngredient(String itemName) {
        Ingredient find = ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(itemName))
                .findAny()
                .orElse(null);
        return find != null;
    }

    public boolean hasIngredient(Long ingredientId) {
        Ingredient find = ingredients.stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findAny().orElse(null);
        return find != null;
    }

    // 주문 취소시 재고 복원
    public void cancelRecipe(int orderCount) {

    }
}
