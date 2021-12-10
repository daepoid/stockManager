package daepoid.stockManager.service;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findItem(Long itemId) {
        return itemRepository.findItem(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public List<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findByItemType(ItemType itemType) {
        return itemRepository.findByItemType(itemType);
    }

    public List<Item> findByQuantity(Double quantity) {
        return itemRepository.findByQuantity(quantity);
    }
}
