package daepoid.stockManager.service;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.jpa.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final JpaItemRepository itemRepository;

    //==생성 로직==//
    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    //==조회 로직==//
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
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

    public List<Item> findByPackageCount(Integer packageCount) {
        return itemRepository.findByPackageCount(packageCount);
    }

    public List<Item> findByQuantity(Double quantity) {
        return itemRepository.findByQuantity(quantity);
    }

    //==수정 로직==//
    @Transactional
    public void changeInfo(Long itemId, String name, ItemType itemType) {
        itemRepository.changeName(itemId, name);
        itemRepository.changeItemType(itemId, itemType);
    }

    @Transactional
    public void changePrice(Long itemId, Integer price) {
        itemRepository.changePrice(itemId, price);
    }

    @Transactional
    public void changePackageCount(Long itemId, Integer packageCount) {
        itemRepository.changePackageCount(itemId, packageCount);
    }
    
    @Transactional
    public void changeQuantity(Long itemId, Double quantity, UnitType unitType) {
        itemRepository.changeQuantity(itemId, quantity);
        itemRepository.changeUnitType(itemId, unitType);
    }

    //==삭제 로직==//
}
