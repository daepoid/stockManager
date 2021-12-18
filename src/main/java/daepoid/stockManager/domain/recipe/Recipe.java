package daepoid.stockManager.domain.recipe;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

    private String name;

    private Integer price;

    private Double unitPrice;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    // Ingredient
    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();

    @Lob
    private String notes;

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
