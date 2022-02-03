package daepoid.stockManager.service;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.jpa.JpaItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    JpaItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    void saveItem() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(item.getId()).isEqualTo(itemId);
    }

    @Test
    void findItem() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findItem(itemId)).isEqualTo(item);
    }

    @Test
    void findItems() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findItems().contains(item)).isTrue();
    }

    @Test
    void findByName() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByName(name).contains(item)).isTrue();
    }

    @Test
    void findByItemType() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByItemType(itemType).contains(item)).isTrue();

    }

    @Test
    void findByPackageCount() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByPackageCount(packageCount).contains(item)).isTrue();
    }

    @Test
    void findByQuantity() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByQuantity(quantity).contains(item)).isTrue();
    }

    @Test
    void findByItemSearch() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        ItemSearch itemSearch1 = new ItemSearch(name, itemType);
        ItemSearch itemSearch2 = new ItemSearch(name);
        ItemSearch itemSearch3 = new ItemSearch(itemType);
        ItemSearch itemSearch4 = new ItemSearch();
        assertThat(itemService.findByItemSearch(itemSearch1).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch2).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch3).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch4).contains(item)).isTrue();

        ItemSearch itemSearch5 = new ItemSearch(name + name);
        ItemSearch itemSearch6 = new ItemSearch(ItemType.BOTTLE);
        assertThat(itemService.findByItemSearch(itemSearch5).contains(item)).isFalse();
        assertThat(itemService.findByItemSearch(itemSearch6).contains(item)).isFalse();
    }

    @Test
    void changeName() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.changeName(itemId, name + name);
        assertThat(itemService.findItem(itemId).getName()).isEqualTo(name + name);
        assertThat(itemService.findByName(name + name).contains(item)).isTrue();
    }

    @Test
    void changeItemType() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.changeItemType(itemId, ItemType.BOTTLE);
        assertThat(item.getItemType()).isEqualTo(ItemType.BOTTLE);
        assertThat(itemService.findItem(itemId).getItemType()).isEqualTo(ItemType.BOTTLE);
    }

    @Test
    void changePrice() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.changePrice(itemId, price * 100);
        assertThat(item.getPrice()).isEqualTo(price * 100);
        assertThat(itemService.findItem(itemId).getPrice()).isEqualTo(price * 100);
    }

    @Test
    void changePackageCount() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.changePackageCount(itemId, packageCount * 100);
        assertThat(item.getPackageCount()).isEqualTo(packageCount * 100);
        assertThat(itemService.findItem(itemId).getPackageCount()).isEqualTo(packageCount * 100);
    }

    @Test
    void changeQuantity() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.changeQuantity(itemId, quantity * 100);
        assertThat(item.getQuantity()).isEqualTo(quantity * 100);
        assertThat(itemService.findItem(itemId).getQuantity()).isEqualTo(quantity * 100);
    }

    @Test
    void changeUnitType() {
        String name = "item";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123;
        UnitType unitType = UnitType.kg;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.changeUnitType(itemId, UnitType.ml);
        assertThat(item.getUnitType()).isEqualTo(UnitType.ml);
        assertThat(itemService.findItem(itemId).getUnitType()).isEqualTo(UnitType.ml);
    }
}