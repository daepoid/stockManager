package daepoid.stockManager.repository;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Item findItem(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name", Item.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Item> findByItemType(ItemType itemType) {
        return em.createQuery("select i from Item i where i.itemType = :itemType", Item.class)
                .setParameter("itemType", itemType)
                .getResultList();
    }

    // 일정 수량 이하인 경우에 찾아서 반환한다.
    public List<Item> findByQuantity(Double quantity) {
        return em.createQuery("select  i from Item i where i.quantity < :quantity", Item.class)
                .setParameter("quantity", quantity)
                .getResultList();

    }
}
