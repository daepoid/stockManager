package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {

    private final JpaIngredientRepository ingredientRepository;
    private final FoodService foodService;

    //==생성 메서드==//
    @Transactional
    public Long saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    //==조회 메서드==//
    public Optional<Ingredient> findIngredient(Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    public List<Ingredient> findIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findIngredients(int maxResult) {
        return ingredientRepository.findAll(maxResult);
    }

    public List<Ingredient> findIngredients(int firstResult, int maxResult) {
        return ingredientRepository.findAll(firstResult, maxResult);
    }

    public List<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> findByFoodId(Long foodId) {
        return ingredientRepository.findByFoodId(foodId);
    }

    //==수정 메서드==//

    @Transactional
    public boolean changeQuantity(Long ingredientId, Double quantity) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(ingredient.isPresent()) {
            ingredient.get().changeQuantity(quantity);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeUnitType(Long ingredientId, UnitType unitType) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(ingredient.isPresent()) {
            ingredient.get().changeUnitType(unitType);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeUnitPrice(Long ingredientId, Double unitPrice) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(ingredient.isPresent()) {
            ingredient.get().changeUnitPrice(unitPrice);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeLoss(Long ingredientId, Double loss) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(ingredient.isPresent()) {
            ingredient.get().changeLoss(loss);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeFood(Long ingredientId, Long foodId) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        Optional<Food> food = foodService.findFood(foodId);
        if(ingredient.isPresent() && food.isPresent()) {
            ingredient.get().changeFood(food.get());
            return true;
        }
        return false;
    }

    @Transactional
    public void removeIngredient(Long ingredientId) {
        ingredientRepository.remove(ingredientId);
    }
}
