package daepoid.stockManager.domain.recipe;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

    @Column(unique = true)
    private Long recipeNumber;

    // 레시피 이름
    private String name;

    // 판매 가격
    private Integer price;


    private Double unitPrice;

    // 요리 무게
    private Double weight;

    // 접시 유형
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    // 레시피 필요 재료
    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();

    // 레시피 생산 단가
    private Double cost = 0.0;

    private Double netIncome = 0.0;

    @Lob
    private String notes;

    // unique 조건일 때 같은 걸로 바꾸면 어떻게 되는가?
    public void changeRecipeNumber(Long recipeNumber) {
        this.recipeNumber = recipeNumber;
    }

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(Integer price) {
        this.price = price;
    }

    public void changeUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void changeWeight(Double weight) {
        this.weight = weight;
    }

    public void changeIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Boolean removeIngredient(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    public void changeDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void changeNotes(String notes) {
        this.notes = notes;
    }


    //==서비스 로직==//
    public Double getTotalPrice() {
        return ingredients.stream()
                .mapToDouble(Ingredient::getPortionPrice)
                .sum();
    }

    public static Recipe createRecipe(String name, Integer price, Double unitPrice, Double weight, DishType dishType, String notes) {
        Recipe recipe = new Recipe();
        recipe.changeName(name);
        recipe.changePrice(price);
        recipe.changeUnitPrice(unitPrice);
        recipe.changeWeight(weight);
        recipe.changeDishType(dishType);
        recipe.changeNotes(notes);
        return recipe;
    }

    public static Recipe createRecipe(String name,
                                      Integer price,
                                      Double unitPrice,
                                      Double weight,
                                      DishType dishType,
                                      String notes,
                                      Ingredient... ingredients) {
        Recipe recipe = new Recipe();
        recipe.changeName(name);
        recipe.changePrice(price);
        recipe.changeUnitPrice(unitPrice);
        recipe.changeWeight(weight);
        recipe.changeDishType(dishType);
        recipe.changeNotes(notes);
        for (Ingredient ingredient : ingredients) {
            recipe.addIngredient(ingredient);
        }
        return recipe;
    }
}
