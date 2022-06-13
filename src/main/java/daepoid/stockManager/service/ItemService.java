package daepoid.stockManager.service;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.search.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.jpa.JpaItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Item> findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public List<Item> findItems(int maxResult) {
        return itemRepository.findAll(maxResult);
    }

    public List<Item> findItems(int firstResult, int maxResult) {
        return itemRepository.findAll(firstResult, maxResult);
    }

    public List<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findByItemType(ItemType itemType) {
        return itemRepository.findByItemType(itemType);
    }

    public List<Item> findByPackageCountLessThanEqual(Integer packageCount) {
        return itemRepository.findByPackageCountLessThanEqual(packageCount);
    }

    public List<Item> findByQuantityLessThanEqual(Double quantity) {
        return itemRepository.findByQuantityLessThanEqual(quantity);
    }

    public List<Item> findByUnderQuantity(Double quantity) {
        return itemRepository.findByQuantityLessThanEqual(quantity);
    }

    public List<Item> findByItemSearch(ItemSearch itemSearch) {
        return itemRepository.findByItemSearch(itemSearch);
    }

    //==수정 로직==//
    @Transactional
    public boolean changeName(Long itemId, String name) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changeName(name);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changePrice(Long itemId, Integer price) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changePrice(price);
            return true;
        }
        return false;
    }
    
    @Transactional
    public boolean changeQuantity(Long itemId, Double quantity) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changeQuantity(quantity);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeUnitType(Long itemId, UnitType unitType) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changeUnitType(unitType);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changePackageCount(Long itemId, Integer packageCount) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changePackageCount(packageCount);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeCountryOfOrigin(Long itemId, String countryOfOrigin) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changeCountryOfOrigin(countryOfOrigin);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeNotice(Long itemId, String notice) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().changeNotice(notice);
            return true;
        }
        return false;
    }

    //==삭제 로직==//
    @Transactional
    public boolean removeItem(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()) {
            itemRepository.remove(itemId);
            return true;
        }
        return false;
    }
}
