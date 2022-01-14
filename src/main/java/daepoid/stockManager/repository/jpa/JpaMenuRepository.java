package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaMenuRepository implements MenuRepository {

    private final EntityManager em;

    @Override
    public Long save(Menu menu) {
        em.persist(menu);
        return menu.getId();
    }

    @Override
    public Optional<Menu> findById(Long menuId) {
        return Optional.of(em.find(Menu.class, menuId));
    }

    @Override
    public List<Menu> findAll() {
        return em.createQuery("select m from Menu m", Menu.class)
                .getResultList();
    }

    @Override
    public List<Menu> findByName(String name) {
        return em.createQuery("select m from Menu m where m.name = :name", Menu.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Menu> findByRecipe(Recipe recipe) {
        return em.createQuery("select m from Menu m", Menu.class)
                .getResultList().stream()
                .filter(menu -> menu.getFoods().contains(recipe))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfFoodByRecipeId(Long menuId, Long recipeId) {
        return em.find(Menu.class, menuId).getNumberOfFood().get(recipeId);
    }

    @Override
    public void changeName(Long menuId, String name) {
        em.find(Menu.class, menuId).changeName(name);
    }

    @Override
    public void changeFoods(Long menuId, Set<Recipe> foods) {
        em.find(Menu.class, menuId).changeFoods(foods);
    }

    @Override
    public void changeNumberOfFood(Long menuId, Map<Long, Integer> numberOfFood) {
        em.find(Menu.class, menuId).changeNumberOfFood(numberOfFood);
    }

    @Override
    public void addFood(Long menuId, Recipe food, Integer numberOfFood) {
        em.find(Menu.class, menuId).addFood(food, numberOfFood);
    }

    @Override
    public void removeFood(Long menuId, Recipe food) {
        em.find(Menu.class, menuId).removeFood(food);
    }
}
