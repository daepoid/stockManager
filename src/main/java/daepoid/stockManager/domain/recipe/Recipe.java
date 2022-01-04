package daepoid.stockManager.domain.recipe;

import daepoid.stockManager.domain.ingredient.Ingredient;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
//@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

//    // 레시피 번호
//    @Column(unique = true)
    private String recipeNumber;

    // 레시피 이름
    // 매장에서 사용되는 레시피가 무한하지 않고, 관리자가 등록을 하기 때문에 중복 검사를 하지 않는다.
    private String name;

    // 판매 가격
    private Integer price;

    // 요리 무게
    private Double weight;

    // 접시 유형
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    // 레시피 필요 재료
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    // 레시피 생산 단가
    private Double cost = 0.0;

    private Double netIncome = 0.0;

    // 레시피 방법 또는 주의점
    @Lob
    private String notes;

    @Builder
    public Recipe(String recipeNumber, String name, Integer price, Double weight, DishType dishType,
                  Double cost, Double netIncome, List<Ingredient> ingredients, String notes) {
        this.recipeNumber = recipeNumber;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.dishType = dishType;
        this.cost = cost;
        this.netIncome = netIncome;
        this.ingredients = ingredients;
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

    public void changePrice(Integer price) {
        this.price = price;
    }

    public void changeWeight(Double weight) {
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

    public void changeCost(Double cost) {
        this.cost = cost;
    }

    public void changeNetIncome(Double netIncome) {
        this.netIncome = netIncome;
    }

    //==연관 관계 메서드==//
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Boolean removeIngredient(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    //==서비스 로직==//
    public Double getTotalCost() {
        return ingredients.stream()
                .mapToDouble(Ingredient::getCost)
                .sum();
    }

    public void updateCost() {
        this.cost = getTotalCost();
        log.info("cost = {}", cost);
        if(price == null || cost == null) {
            netIncome = 0.0;
        } else {
            this.netIncome = this.price - this.cost;
        }
    }

//    public static Recipe createRecipe(String name, Integer price, Double unitPrice, Double weight, DishType dishType, String notes) {
//        Recipe recipe = new Recipe();
//        recipe.changeName(name);
//        recipe.changePrice(price);
//        recipe.changeUnitPrice(unitPrice);
//        recipe.changeWeight(weight);
//        recipe.changeDishType(dishType);
//        recipe.changeNotes(notes);
//        return recipe;
//    }
//
//    public static Recipe createRecipe(String name,
//                                      Integer price,
//                                      Double unitPrice,
//                                      Double weight,
//                                      DishType dishType,
//                                      String notes,
//                                      Ingredient... ingredients) {
//        Recipe recipe = new Recipe();
//        recipe.changeName(name);
//        recipe.changePrice(price);
//        recipe.changeUnitPrice(unitPrice);
//        recipe.changeWeight(weight);
//        recipe.changeDishType(dishType);
//        recipe.changeNotes(notes);
//        for (Ingredient ingredient : ingredients) {
//            recipe.addIngredient(ingredient);
//        }
//        return recipe;
//    }
}
