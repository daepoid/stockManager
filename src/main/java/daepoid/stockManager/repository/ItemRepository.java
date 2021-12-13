package daepoid.stockManager.repository;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.MemberStatus;

import java.util.List;

public interface ItemRepository {

    //==생성 로직==//
    Long save(Item item);

    //==조회 로직==//
    Item findById(Long itemId);

    List<Item> findAll();

    List<Item> findByName(String name);

    List<Item> findByItemType(ItemType itemType);

    // 일정 수량 이하인 경우에 찾아서 반환하다.
    List<Item> findByPackageCount(Integer packageCount);

    // 일정 수량 이하인 경우에 찾아서 반환한다.
    List<Item> findByQuantity(Double quantity);

    //==수정 로직==//
    void changeName(Long itemId, String name);
    void changeItemType(Long itemId, ItemType itemType);
    void changePrice(Long itemId, Integer price);
    void changePackageCount(Long itemId, Integer packageCount);
    void changeQuantity(Long itemId, Double quantity);
    void changeUnitType(Long itemId, UnitType unitType);

    //==삭제 로직==//
    void removeByItem(Item item);

    void removeById(Long id);

}
