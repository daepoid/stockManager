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
    private Long id;

    private String name;

    private Integer price;

    private Double unitPrice;

    private Double weight;

    // Ingredient
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    @Lob
    private String notes;
}
