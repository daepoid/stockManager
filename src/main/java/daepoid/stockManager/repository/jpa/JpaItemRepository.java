package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaItemRepository implements ItemRepository {

    private final EntityManager em;

    //==생성 로직==//
    @Override
    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    //==조회 로직==//
    @Override
    public Item findById(Long itemId) {
        return em.find(Item.class, itemId);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name", Item.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Item> findByItemType(ItemType itemType) {
        return em.createQuery("select i from Item i where i.itemType = :itemType", Item.class)
                .setParameter("itemType", itemType)
                .getResultList();
    }

    @Override
    public List<Item> findByPackageCount(int packageCount) {
        return em.createQuery("select i from Item i where i.packageCount <= :packageCount", Item.class)
                .setParameter("packageCount", packageCount)
                .getResultList();
    }

    // 일정 수량 이하인 경우에 찾아서 반환한다.
    @Override
    public List<Item> findByUnderQuantity(double quantity) {
        return em.createQuery("select  i from Item i where i.quantity <= :quantity", Item.class)
                .setParameter("quantity", quantity)
                .getResultList();

    }

    @Override
    public List<Item> findByItemSearch(ItemSearch itemSearch) {
        // language=JPAQL
        String jpql = "select i From Item i";

        boolean isFirstCondition = true;
        // 재고 타입 검색
        if (itemSearch.getItemType() != null) {
            jpql += " where i.itemType = :itemType";
            isFirstCondition = false;
        }

        // 재고 이름 검색
        if (StringUtils.hasText(itemSearch.getName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " i.name like :name";
        }
        TypedQuery<Item> query = em.createQuery(jpql, Item.class).setMaxResults(1000); //최대 1000건

        if (itemSearch.getItemType() != null) {
            query = query.setParameter("itemType", itemSearch.getItemType());
        }
        if (StringUtils.hasText(itemSearch.getName())) {
            query = query.setParameter("name", "%" + itemSearch.getName() + "%");
        }
        return query.getResultList();
    }

    //==수정 로직==//
    @Override
    public void changeName(Long itemId, String name) {
        Item item = em.find(Item.class, itemId);
        item.changeName(name);
    }

    @Override
    public void changeItemType(Long itemId, ItemType itemType) {
        Item item = em.find(Item.class, itemId);
        item.changeItemType(itemType);
    }

    @Override
    public void changePrice(Long itemId, int price) {
        Item item = em.find(Item.class, itemId);
        item.changePrice(price);
    }

    @Override
    public void changePackageCount(Long itemId, int packageCount) {
        Item item = em.find(Item.class, itemId);
        item.changePackageCount(packageCount);
    }

    @Override
    public void changeQuantity(Long itemId, double quantity) {
        Item item = em.find(Item.class, itemId);
        item.changeQuantity(quantity);
    }

    @Override
    public void changeUnitType(Long itemId, UnitType unitType) {
        Item item = em.find(Item.class, itemId);
        item.changeUnitType(unitType);
    }

    //==삭제 로직==//
    @Override
    public void removeItem(Item item) {
        em.remove(item);
    }

    @Override
    public void removeById(Long id) {
        Item item = em.find(Item.class, id);
        em.remove(item);
    }
}
