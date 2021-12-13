package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public List<Item> findByPackageCount(Integer packageCount) {
        return em.createQuery("select i from Item i where i.packageCount < :packageCount", Item.class)
                .setParameter("packageCount", packageCount)
                .getResultList();
    }

    // 일정 수량 이하인 경우에 찾아서 반환한다.
    @Override
    public List<Item> findByQuantity(Double quantity) {
        return em.createQuery("select  i from Item i where i.quantity < :quantity", Item.class)
                .setParameter("quantity", quantity)
                .getResultList();

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
    public void changePrice(Long itemId, Integer price) {
        Item item = em.find(Item.class, itemId);
        item.changePrice(price);
    }

    @Override
    public void changePackageCount(Long itemId, Integer packageCount) {
        Item item = em.find(Item.class, itemId);
        item.changePackageCount(packageCount);
    }

    @Override
    public void changeQuantity(Long itemId, Double quantity) {
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
    public void removeByItem(Item item) {
        em.remove(item);
    }

    @Override
    public void removeById(Long id) {
        Item item = em.find(Item.class, id);
        em.remove(item);
    }
}
