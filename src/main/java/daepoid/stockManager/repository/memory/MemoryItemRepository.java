package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final HashMap<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    //==생성 로직==//
    @Override
    public Long save(Item item) {
        item.changeId(++sequence);
        store.put(item.getId(), item);
        return item.getId();
    }

    //==조회 로직==//
    @Override
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Item> findByName(String name) {
        return store.values()
                .stream()
                .filter(item -> item.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findByItemType(ItemType itemType) {
        return store.values()
                .stream()
                .filter(item -> item.getItemType().equals(itemType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findByPackageCount(int packageCount) {
        return store.values()
                .stream()
                .filter(item -> item.getPackageCount() < packageCount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findByQuantity(double quantity) {
        return store.values()
                .stream()
                .filter(item -> item.getQuantity() < quantity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findByItemSearch(ItemSearch itemSearch) {
        return null;
    }

    //==수정 로직==//
    @Override
    public void changeName(Long itemId, String name) {
        store.get(itemId).changeName(name);
    }

    @Override
    public void changeItemType(Long itemId, ItemType itemType) {
        store.get(itemId).changeItemType(itemType);
    }

    @Override
    public void changePrice(Long itemId, int price) {
        store.get(itemId).changePrice(price);
    }

    @Override
    public void changePackageCount(Long itemId, int packageCount) {
        store.get(itemId).changePackageCount(packageCount);
    }

    @Override
    public void changeQuantity(Long itemId, double quantity) {
        store.get(itemId).changeQuantity(quantity);
    }

    @Override
    public void changeUnitType(Long itemId, UnitType unitType) {
        store.get(itemId).changeUnitType(unitType);
    }

    //==삭제 로직==//
    @Override
    public void removeByItem(Item item) {
        store.remove(item.getId(), item);
    }

    @Override
    public void removeById(Long id) {
        store.remove(id, store.get(id));
    }
}
