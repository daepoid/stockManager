package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.food.DishType;
import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.FoodType;
import daepoid.stockManager.domain.search.FoodSearch;
import daepoid.stockManager.domain.food.FoodStatus;
import daepoid.stockManager.repository.FoodRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaFoodRepository implements FoodRepository {

    private final EntityManager em;

    @Override
    public Long save(Food food) {
        em.persist(food);
        return food.getId();
    }

    @Override
    public Optional<Food> findById(Long foodId) {
        return Optional.of(em.find(Food.class, foodId));
    }

    @Override
    public List<Food> findAll() {
        return em.createQuery("select f from Food f", Food.class)
                .getResultList();
    }

    @Override
    public List<Food> findAll(int maxResult) {
        return em.createQuery("select f from Food f", Food.class)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<Food> findAll(int firstResult, int maxResult) {
        return em.createQuery("select f from Food f", Food.class)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<Food> findAllByRecentAddedDate(LocalDateTime addedDate) {
        return em.createQuery("select f from Food f where f.addedDate <= :addedDate", Food.class)
                .setParameter("addedDate", LocalDateTime.now().minusDays(31))
                .getResultList();
    }

    @Override
    public List<Food> findAllBySalesCountLessThanEqual(Integer salesCount) {
        return em.createQuery("select f from Food f where f.salesCount <= :salesCount", Food.class)
                .setParameter("salesCount", salesCount)
                .getResultList();
    }

    @Override
    public List<Food> findAllBySalesCountGreaterThanEqual(Integer salesCount) {
        return em.createQuery("select f from Food f where f.salesCount >= :salesCount", Food.class)
                .setParameter("salesCount", salesCount)
                .getResultList();
    }

    @Override
    public List<Food> findAllByFoodStatus(FoodStatus foodStatus) {
        return em.createQuery("select f from Food f where f.foodStatus=:foodStatus", Food.class)
                .setParameter("foodStatus", foodStatus)
                .getResultList();
    }

    @Override
    public List<Food> findAllByFoodName(String foodName) {
        return em.createQuery("select f from Food f where f.foodName=:foodName", Food.class)
                .setParameter("foodName", foodName)
                .getResultList();
    }

    @Override
    public List<Food> findAllLikeFoodName(String foodName) {
        return em.createQuery("select f from Food f where f.foodName like :foodName", Food.class)
                .setParameter("foodName", "%" + foodName + "%")
                .getResultList();
    }

    @Override
    public List<Food> findAllByDishType(DishType dishType) {
        return em.createQuery("select f from Food f where f.dishType=:dishType", Food.class)
                .setParameter("dishType", dishType)
                .getResultList();
    }

    @Override
    public List<Food> findAllByFoodType(FoodType foodType) {
        return em.createQuery("select f from Food f where f.foodType=:foodType", Food.class)
                .setParameter("foodType", foodType)
                .getResultList();
    }

    @Override
    public List<Food> findAllByFoodCostLessThanEqual(Double foodCost) {
        return em.createQuery("select f from Food f where f.foodCost <= :foodCost", Food.class)
                .setParameter("foodCost", foodCost)
                .getResultList();
    }

    @Override
    public List<Food> findAllByFoodCostGreaterThanEqual(Double foodCost) {
        return em.createQuery("select f from Food f where f.foodCost >= :foodCost", Food.class)
                .setParameter("foodCost", foodCost)
                .getResultList();
    }

    @Override
    public List<Food> findAllByIncomeLessThanEqual(Double threshold) {
        return em.createQuery("select f from Food f where f.foodPrice - f.foodCost <= :threshold", Food.class)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    public List<Food> findAllByIncomeGreaterThanEqual(Double threshold) {
        return em.createQuery("select f from Food f where f.foodPrice - f.foodCost >= :threshold", Food.class)
                .setParameter("threshold", threshold)
                .getResultList();
    }

    @Override
    public List<Food> findAllFoodOrderByIncome(boolean orderOption) {
        String opt = orderOption ? "ASC" : "DESC";
        return em.createQuery("select f from Food f order by f.income " + opt, Food.class)
                .getResultList();
    }

    @Override
    public List<Food> findAllByFoodSearch(FoodSearch foodSearch) {
        String jpql = "select f from Food f";
        if(StringUtils.hasText(foodSearch.getFoodName())) {
            jpql += " where f.name like :name";
        }

        TypedQuery<Food> query = em.createQuery(jpql, Food.class);

        if(StringUtils.hasText(foodSearch.getFoodName())) {
            query = query.setParameter("name", "%" + foodSearch.getFoodName() + "%");
        }
        return query.getResultList();
    }

    @Override
    public List<Food> findOrderableMenu() {
        return em.createQuery("select f from Food f where f.foodStatus=:foodStatus", Food.class)
                .setParameter("foodStatus", FoodStatus.ORDERABLE)
                .getResultList();
    }

    @Override
    public void changeSalesCount(Long foodId, Integer salesCount) {
        em.find(Food.class, foodId).changeSalesCount(salesCount);
    }

    @Override
    public void changeFoodStatus(Long foodId, FoodStatus foodStatus) {
        em.find(Food.class, foodId).changeFoodStatus(foodStatus);
    }

    @Override
    public void changeFoodName(Long foodId, String foodName) {
        em.find(Food.class, foodId).changeFoodName(foodName);
    }

    @Override
    public void changeFoodPrice(Long foodId, Double foodPrice) {
        em.find(Food.class, foodId).changeFoodPrice(foodPrice);
    }

    @Override
    public void changeFoodInfo(Long foodId, String foodInfo) {
        em.find(Food.class, foodId).changeFoodInfo(foodInfo);
    }

    @Override
    public void changeImgUrl(Long foodId, String imgUrl) {
        em.find(Food.class, foodId).changeImgUrl(imgUrl);
    }

    @Override
    public void changeRecipe(Long foodId, String recipe) {
        em.find(Food.class, foodId).changeRecipe(recipe);
    }

    @Override
    public void changeNotice(Long foodId, String notice) {
        em.find(Food.class, foodId).changeNotice(notice);
    }

    @Override
    public void changeFoodCost(Long foodId, Double foodCost) {
        em.find(Food.class, foodId).changeFoodCost(foodCost);
    }

    @Override
    public void remove(Long foodId) {
        em.remove(em.find(Food.class, foodId));
    }
}
