package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryIngredientRepository implements IngredientRepository {

    private static final Map<Long, Ingredient> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Long saveIngredient(Ingredient ingredient) {
        ingredient.changeId(++sequence);
        store.put(ingredient.getId(), ingredient);
        return ingredient.getId();
    }

    @Override
    public Ingredient findIngredient(Long ingredientId) {
        return store.get(ingredientId);
    }

    @Override
    public List<Ingredient> findIngredients() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Ingredient> findIngredientsByRecipe(Long recipeId) {
        return store.values().stream()
                .filter(ingredient -> ingredient.getRecipe().getId().equals(recipeId))
                .collect(Collectors.toList());
    }
}
