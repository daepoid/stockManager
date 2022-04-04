//package daepoid.stockManager.testData;
//
//import daepoid.stockManager.domain.recipe.DishType;
//import daepoid.stockManager.domain.recipe.Recipe;
//import daepoid.stockManager.service.RecipeService;
//
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//@RequiredArgsConstructor
//public class TestRecipeDataInit {
//
//    private final RecipeService recipeService;
//
//    @PostConstruct
//    public void init() {
//        recipeService.saveRecipe(Recipe.builder()
//                .recipeNumber("001")
//                .name("recipe1")
//                .price(11000)
//                .weight(10.0)
//                .dishType(DishType.BOWL)
//                .cost(0.0)
//                .netIncome(11000 - 0.0)
//                .notes("note1")
//                .imgUrl("url")
//                .build());
//
//        recipeService.saveRecipe(Recipe.builder()
//                .recipeNumber("002")
//                .name("recipe2")
//                .price(12000)
//                .weight(11.0)
//                .dishType(DishType.DESSERT)
//                .cost(0.0)
//                .netIncome(12000 - 0.0)
//                .notes("note2")
//                .imgUrl("url")
//                .build());
//
//        recipeService.saveRecipe(Recipe.builder()
//                .recipeNumber("003")
//                .name("recipe3")
//                .price(13000)
//                .weight(12.0)
//                .dishType(DishType.MAIN)
//                .cost(0.0)
//                .netIncome(13000 - 0.0)
//                .notes("note3")
//                .imgUrl("url")
//                .build());
//
//        recipeService.saveRecipe(Recipe.builder()
//                .recipeNumber("004")
//                .name("recipe4")
//                .price(14000)
//                .weight(13.0)
//                .dishType(DishType.GRAVY)
//                .cost(0.0)
//                .netIncome(14000 - 0.0)
//                .notes("note4")
//                .imgUrl("url")
//                .build());
//    }
//}
