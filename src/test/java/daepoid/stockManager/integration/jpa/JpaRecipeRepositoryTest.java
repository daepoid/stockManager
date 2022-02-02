//package daepoid.stockManager.integration.jpa;
//
//import daepoid.stockManager.domain.ingredient.Ingredient;
//import daepoid.stockManager.domain.item.Item;
//import daepoid.stockManager.domain.item.ItemType;
//import daepoid.stockManager.domain.item.UnitType;
//import daepoid.stockManager.domain.recipe.DishType;
//import daepoid.stockManager.domain.recipe.Recipe;
//import daepoid.stockManager.repository.jpa.JpaRecipeRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class JpaRecipeRepositoryTest {
//
//    @Autowired
//    EntityManager em;
//
//    @Autowired
//    JpaRecipeRepository recipeRepository;
//
//    @Test
//    void save() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        assertThat(recipe.getId()).isEqualTo(recipeId);
//        assertThat(em.find(Recipe.class, recipeId)).isEqualTo(recipe);
//    }
//
//    @Test
//    void findById() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        assertThat(recipeRepository.findById(recipeId)).isEqualTo(recipe);
//    }
//
//    @Test
//    void findAll() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        assertThat(recipeRepository.findAll().contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void findByName() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        assertThat(recipeRepository.findByName(name)).isEqualTo(recipe);
//    }
//
//    @Test
//    void findByPrice() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        assertThat(recipeRepository.findByPrice(price).contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void findByWeight() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//        int weight = 100;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .weight(weight)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        assertThat(recipeRepository.findByWeight(weight).contains(recipe)).isEqualTo(true);
//
//    }
//
//    @Test
//    void findByIngredient() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Item item = Item.builder()
//                .name("item")
//                .itemType(ItemType.BOTTLE)
//                .price(123)
//                .quantity(123)
//                .unitType(UnitType.g)
//                .packageCount(123)
//                .build();
//        em.persist(item);
//
//        List<Ingredient> ingredients = new ArrayList<>();
//        Ingredient ingredient = Ingredient.builder()
//                .item(item)
//                .name("name")
//                .quantity(123)
//                .unitType(UnitType.g)
//                .unitPrice(12)
//                .loss(0.0)
//                .cost(123)
//                .build();
//        em.persist(ingredient);
//
//        ingredients.add(ingredient);
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .ingredients(ingredients)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void findByDishType() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        assertThat(recipeRepository.findByDishType(dishType).contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void changeRecipeNumber() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        recipeRepository.changeRecipeNumber(recipeId, recipeNumber + recipeNumber);
//
//        assertThat(recipe.getRecipeNumber()).isEqualTo(recipeNumber + recipeNumber);
//        assertThat(recipeRepository.findById(recipeId).getRecipeNumber()).isEqualTo(recipeNumber + recipeNumber);
//    }
//
//    @Test
//    void changeName() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        recipeRepository.changeName(recipeId, name + name);
//        assertThat(recipe.getName()).isEqualTo(name + name);
//        assertThat(recipeRepository.findById(recipeId).getName()).isEqualTo(name + name);
//        assertThat(recipeRepository.findByName(name + name)).isEqualTo(recipe);
//        assertThat(recipeRepository.findByName(name)).isEqualTo(null);
//    }
//
//    @Test
//    void changePrice() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        recipeRepository.changePrice(recipeId, price * 10);
//
//        assertThat(recipe.getPrice()).isEqualTo(price * 10);
//        assertThat(recipeRepository.findById(recipeId).getPrice()).isEqualTo(price * 10);
//        assertThat(recipeRepository.findByPrice(price * 10).contains(recipe)).isEqualTo(true);
//        assertThat(recipeRepository.findByPrice(price).contains(recipe)).isEqualTo(false);
//    }
//
//    @Test
//    void changeWeight() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//        int weight = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .weight(weight)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        recipeRepository.changeWeight(recipeId, weight* 100);
//
//        assertThat(recipe.getWeight()).isEqualTo(weight * 100);
//        assertThat(recipeRepository.findById(recipeId).getWeight()).isEqualTo(weight * 100);
//        assertThat(recipeRepository.findByWeight(weight * 100).contains(recipe)).isEqualTo(true);
//
//    }
//
//    @Test
//    void changeDishType() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        recipeRepository.changeDishType(recipeId, DishType.DESSERT);
//
//        assertThat(recipe.getDishType()).isEqualTo(DishType.DESSERT);
//        assertThat(recipeRepository.findById(recipeId).getDishType()).isEqualTo(DishType.DESSERT);
//        assertThat(recipeRepository.findByDishType(DishType.BOWL).contains(recipe)).isEqualTo(false);
//        assertThat(recipeRepository.findByDishType(DishType.DESSERT).contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void changeIngredient() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .ingredients(ingredients)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        List<Ingredient> new_ingredients = new ArrayList<>();
//        Item item = Item.builder()
//                .name("name")
//                .itemType(ItemType.BOTTLE)
//                .price(123)
//                .quantity(123)
//                .unitType(UnitType.g)
//                .packageCount(123)
//                .build();
//        em.persist(item);
//
//        Ingredient ingredient = Ingredient.builder()
//                .item(item)
//                .name("name")
//                .quantity(456)
//                .unitType(UnitType.g)
//                .unitPrice(456)
//                .loss(0.0)
//                .cost(456)
//                .build();
//        em.persist(ingredient);
//
//        new_ingredients.add(ingredient);
//        recipeRepository.changeIngredient(recipeId, new_ingredients);
//
//        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(true);
//        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isEqualTo(true);
//        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void addIngredient() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .ingredients(ingredients)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        Item item = Item.builder()
//                .name("name")
//                .itemType(ItemType.BOTTLE)
//                .price(123)
//                .quantity(123)
//                .unitType(UnitType.g)
//                .packageCount(123)
//                .build();
//        em.persist(item);
//
//        Ingredient ingredient = Ingredient.builder()
//                .item(item)
//                .name("name")
//                .quantity(456)
//                .unitType(UnitType.g)
//                .unitPrice(456)
//                .loss(0.0)
//                .cost(456)
//                .build();
//        em.persist(ingredient);
//
//        recipeRepository.addIngredient(recipeId, ingredient);
//
//        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(true);
//        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isEqualTo(true);
//        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isEqualTo(true);
//    }
//
//    @Test
//    void removeIngredient() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        Item item = Item.builder()
//                .name("name")
//                .itemType(ItemType.BOTTLE)
//                .price(123)
//                .quantity(123)
//                .unitType(UnitType.g)
//                .packageCount(123)
//                .build();
//        em.persist(item);
//
//        Ingredient ingredient = Ingredient.builder()
//                .item(item)
//                .name("name")
//                .quantity(456)
//                .unitType(UnitType.g)
//                .unitPrice(456)
//                .loss(0.0)
//                .cost(456)
//                .build();
//        em.persist(ingredient);
//        ingredients.add(ingredient);
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .ingredients(ingredients)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(true);
//        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isEqualTo(true);
//        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isEqualTo(true);
//
//        recipeRepository.removeIngredient(recipeId, ingredient);
//        assertThat(recipe.getIngredients().contains(ingredient)).isEqualTo(false);
//        assertThat(recipeRepository.findById(recipeId).getIngredients().contains(ingredient)).isEqualTo(false);
//        assertThat(recipeRepository.findByIngredient(ingredient).contains(recipe)).isEqualTo(false);
//
//    }
//
//    @Test
//    void changeCost() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        Item item = Item.builder()
//                .name("name")
//                .itemType(ItemType.BOTTLE)
//                .price(123)
//                .quantity(123)
//                .unitType(UnitType.g)
//                .packageCount(123)
//                .build();
//        em.persist(item);
//
//        Ingredient ingredient = Ingredient.builder()
//                .item(item)
//                .name("name")
//                .quantity(456)
//                .unitType(UnitType.g)
//                .unitPrice(456)
//                .loss(0.0)
//                .cost(456)
//                .build();
//        em.persist(ingredient);
//        ingredients.add(ingredient);
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .ingredients(ingredients)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        assertThat(recipe.getCost()).isEqualTo(456 * 456);
//        assertThat(recipeRepository.findById(recipeId).getCost()).isEqualTo(456 * 456);
//    }
//
//    @Test
//    void updateCost() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        List<Ingredient> ingredients = new ArrayList<>();
//
//        Item item = Item.builder()
//                .name("name")
//                .itemType(ItemType.BOTTLE)
//                .price(123)
//                .quantity(123)
//                .unitType(UnitType.g)
//                .packageCount(123)
//                .build();
//        em.persist(item);
//
//        Ingredient ingredient = Ingredient.builder()
//                .item(item)
//                .name("name")
//                .quantity(456)
//                .unitType(UnitType.g)
//                .unitPrice(456)
//                .loss(0.0)
//                .cost(456)
//                .build();
//        em.persist(ingredient);
//        ingredients.add(ingredient);
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .ingredients(ingredients)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//        recipeRepository.updateCost(recipeId);
//
//        assertThat(recipe.getCost()).isEqualTo(456 * 456);
//        assertThat(recipeRepository.findById(recipeId).getCost()).isEqualTo(456 * 456);
//    }
//
//    @Test
//    void changeNotes() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        String notes = "123123123";
//        recipeRepository.changeNotes(recipeId, notes);
//
//        assertThat(recipe.getNotes()).isEqualTo(notes);
//        assertThat(recipeRepository.findById(recipeId).getNotes()).isEqualTo(notes);
//    }
//
//    @Test
//    void removeRecipe() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        recipeRepository.removeRecipe(recipe);
//        assertThat(recipeRepository.findById(recipeId)).isEqualTo(null);
//    }
//
//    @Test
//    void removeById() {
//        String recipeNumber = "1234";
//        String name = "name";
//        int price = 1234;
//        DishType dishType = DishType.BOWL;
//        double cost = 123;
//        double netIncome = 123;
//
//        Recipe recipe = Recipe.builder()
//                .recipeNumber(recipeNumber)
//                .name(name)
//                .price(price)
//                .dishType(dishType)
//                .cost(cost)
//                .netIncome(netIncome)
//                .build();
//
//        Long recipeId = recipeRepository.save(recipe);
//
//        recipeRepository.removeById(recipeId);
//        assertThat(recipeRepository.findById(recipeId)).isEqualTo(null);
//    }
//}