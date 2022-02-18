package daepoid.stockManager.controller.dto.recipe;

import daepoid.stockManager.domain.recipe.DishType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeDTO {

    @NotBlank
    private String recipeNumber;

    @NotBlank
    private String name;

    @NotNull
    private int price;

    private double weight;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String notes;
}
