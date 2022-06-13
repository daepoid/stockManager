package daepoid.stockManager.repository;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.search.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    //==생성 로직==//
    Long save(Item item);

    //==조회 로직==//
    Optional<Item> findById(Long itemId);
    List<Item> findAll();
    List<Item> findAll(int maxResult);
    List<Item> findAll(int firstResult, int maxResult);
    List<Item> findByName(String name);
    List<Item> findByItemType(ItemType itemType);
    List<Item> findByPackageCountLessThanEqual(Integer packageCount);
    List<Item> findByQuantityLessThanEqual(Double quantity);
    List<Item> findByItemSearch(ItemSearch itemSearch);

    //==수정 로직==//
    void changeName(Long itemId, String name);
    void changePrice(Long itemId, Integer price);
    void changeQuantity(Long itemId, Double quantity);
    void changeUnitType(Long itemId, UnitType unitType);
    void changePackageCount(Long itemId, Integer packageCount);
    void changeCountryOfOrigin(Long itemId, String countryOfOrigin);
    void changeNotice(Long itemId, String notice);

    //==삭제 로직==//
    void remove(Long itemId);
}
