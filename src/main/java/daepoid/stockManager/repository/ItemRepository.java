package daepoid.stockManager.repository;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;

import java.util.List;

public interface ItemRepository {

    Long save(Item item);

    void removeByItem(Item item);

    void removeById(Long id);

    Item findById(Long itemId);

    List<Item> findAll();

    List<Item> findByName(String name);

    List<Item> findByItemType(ItemType itemType);

    // 일정 수량 이하인 경우에 찾아서 반환하다.
    List<Item> findByPackageCount(Integer packageCount);

    // 일정 수량 이하인 경우에 찾아서 반환한다.
    List<Item> findByQuantity(Double quantity);

}
