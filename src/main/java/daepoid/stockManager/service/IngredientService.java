package daepoid.stockManager.service;

import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

    private final JpaIngredientRepository ingredientRepository;

    //==생성 메서드==//
    public Long saveIngredient(Ingredient ingredient) {
        return ingredientRepository.saveIngredient(ingredient);
    }

    //==조회 메서드==//
    public Ingredient findIngredient(Long ingredientId) {
        return ingredientRepository.findIngredient(ingredientId);
    }

    public List<Ingredient> findIngredients() {
        return ingredientRepository.findIngredients();
    }
}
