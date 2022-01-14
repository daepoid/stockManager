package daepoid.stockManager.domain.recipe;

import lombok.*;

import javax.persistence.*;
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
    
    private String name;

    @ManyToMany
    @JoinTable(
            name="recipe_foods",
            joinColumns=@JoinColumn(name="menu_id"),
            inverseJoinColumns=@JoinColumn(name="recipe_id")
    )
    private Set<Recipe> foods = new HashSet<>();

    private Double price;

    @ElementCollection
    @JoinTable(
            name="menu_number_of_food",
            joinColumns=@JoinColumn(name="recipe_id"))
    @MapKeyColumn(name="food_id")
    @Column(name="number")
    // Map<recipeId, food_count>
    private Map<Long, Integer> numberOfFood = new HashMap<>();

    @Builder
    public Menu(String name, Double price, Set<Recipe> foods, Map<Long, Integer> numberOfFood) {
        this.name = name;
        this.price = price;
        this.foods = foods;
        this.numberOfFood = numberOfFood;
    }

    public Double getPrice() {
        updatePrice();
        return this.price;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(Double price) {
        this.price = price;
    }

    public void changeFoods(Set<Recipe> foods) {
        this.foods = foods;
    }

    public void changeNumberOfFood(Map<Long, Integer> numberOfFood) {
        this.numberOfFood = numberOfFood;
    }

    public void updatePrice() {

        Double sum = 0.0;
        for (Recipe food : foods) {
            Integer numberOfFoodInt = this.numberOfFood.get(food.getId());
            if(numberOfFoodInt != null) {
                sum += numberOfFoodInt * food.getPrice();
            }
        }

        this.price = sum;
//
//        this.price = foods.stream()
//                .mapToDouble(recipe -> recipe.getPrice() * numberOfFood.get(recipe.getId()))
//                .sum();
    }

    public void addFood(Recipe food, Integer numberOfFood) {
        this.getFoods().add(food);
        this.getNumberOfFood().put(food.getId(), numberOfFood);
    }

    public void removeFood(Recipe food) {
        this.getFoods().remove(food);
    }
}
