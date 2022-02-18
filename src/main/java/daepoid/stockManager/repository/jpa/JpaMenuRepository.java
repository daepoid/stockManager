package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.domain.search.MenuSearch;
import daepoid.stockManager.domain.recipe.MenuStatus;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
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
    public Menu findById(Long menuId) {
        return em.find(Menu.class, menuId);
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
    public List<Menu> findByOverSalesCount(int salesCount) {
        return em.createQuery("select m from Menu m where m.salesCount>=:salesCount", Menu.class)
                .setParameter("salesCount", salesCount)
                .getResultList();
    }

    @Override
    public List<Menu> findByUnderSalesCount(int salesCount) {
        return em.createQuery("select m from Menu m where m.salesCount<=:salesCount", Menu.class)
                .setParameter("salesCount", salesCount)
                .getResultList();
    }

    @Override
    public List<Menu> findByMenuStatus(MenuStatus menuStatus) {
        return em.createQuery("select m from Menu m where m.menuStatus=:menuStatus", Menu.class)
                .setParameter("menuStatus", menuStatus)
                .getResultList();
    }

    @Override
    public List<Menu> findByMenuSearch(MenuSearch menuSearch) {
        String jpql = "select m from Menu m";
        if(StringUtils.hasText(menuSearch.getMenuName())) {
            jpql += " where m.name like :name";
        }

        TypedQuery<Menu> query = em.createQuery(jpql, Menu.class);

        if(StringUtils.hasText(menuSearch.getMenuName())) {
            query = query.setParameter("name", "%" + menuSearch.getMenuName() + "%");
        }
        return query.getResultList();
    }

    @Override
    public List<Menu> findOrderableMenu() {
        return em.createQuery("select m from Menu m where m.menuStatus=:menuStatus", Menu.class)
                .setParameter("menuStatus", MenuStatus.ORDERABLE)
                .getResultList();
    }

    @Override
    public Integer getNumberOfFoodByRecipeId(Long menuId, Long recipeId) {
        return em.find(Menu.class, menuId).getNumberOfFoods().get(recipeId);
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
    public void changeNumberOfFoods(Long menuId, Map<Long, Integer> numberOfFoods) {
        em.find(Menu.class, menuId).changeNumberOfFood(numberOfFoods);
    }

    @Override
    public void changeFoodInfo(Long menuId, Set<Recipe> foods, Map<Long, Integer> numberOfFoods) {
        Menu menu = em.find(Menu.class, menuId);
        menu.changeFoods(foods);
        menu.changeNumberOfFood(numberOfFoods);
    }

    @Override
    public void changeSalesCount(Long menuId, int salesCount) {
        Menu menu = em.find(Menu.class, menuId);
        menu.changeSalesCount(salesCount);
    }

    @Override
    public void changeMenuStatus(Long menuId, MenuStatus menuStatus) {
        Menu menu = em.find(Menu.class, menuId);
        menu.changeMenuStatus(menuStatus);
    }

    @Override
    public void addFood(Long menuId, Recipe food, Integer numberOfFoods) {
        em.find(Menu.class, menuId).addFood(food, numberOfFoods);
    }

    @Override
    public void addSalesCount(Long menuId, int salesCount) {
        em.find(Menu.class, menuId).addSalesCount(salesCount);
    }

    @Override
    public void cancelSalesCount(Long menuId, int salesCount) {
        em.find(Menu.class, menuId).cancelSalesCount(salesCount);
    }

    @Override
    public void removeFood(Long menuId, Recipe food) {
        em.find(Menu.class, menuId).removeFood(food);
    }

    @Override
    public void removeMenu(Long menuId) {
        em.remove(em.find(Menu.class, menuId));
    }
}
