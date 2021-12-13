package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
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

    @Override
    public Long save(Item item) {
        item.changeId(++sequence);
        store.put(item.getId(), item);
        return item.getId();
    }

    @Override
    public void removeByItem(Item item) {
        store.remove(item.getId(), item);
    }

    @Override
    public void removeById(Long id) {
        store.remove(id, store.get(id));
    }

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
    public List<Item> findByPackageCount(Integer packageCount) {
        return store.values()
                .stream()
                .filter(item -> item.getPackageCount() < packageCount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findByQuantity(Double quantity) {
        return store.values()
                .stream()
                .filter(item -> item.getQuantity() < quantity)
                .collect(Collectors.toList());
    }
}
