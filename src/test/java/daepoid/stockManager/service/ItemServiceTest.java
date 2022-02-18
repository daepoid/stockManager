package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.search.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ItemService itemService;

    @Test
    void saveItem() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(item.getId()).isEqualTo(itemId);
    }

    @Test
    void findItem() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findItem(itemId)).isEqualTo(item);
        assertThat(itemService.findItem(itemId).getId()).isEqualTo(item.getId());
    }

    @Test
    void findItems() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findItems().contains(item)).isTrue();
        assertThat(itemService.findItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByName() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByName(name).contains(item)).isTrue();
        assertThat(itemService.findByName(name).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByItemType() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByItemType(itemType).contains(item)).isTrue();
        assertThat(itemService.findByItemType(itemType).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByPackageCount() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByPackageCount(packageCount).contains(item)).isTrue();
        assertThat(itemService.findByPackageCount(packageCount).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByUnderQuantity() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        assertThat(itemService.findByUnderQuantity(quantity).contains(item)).isTrue();
        assertThat(itemService.findByUnderQuantity(quantity).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByItemSearch() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        ItemSearch itemSearch1 = new ItemSearch(name, itemType);
        assertThat(itemService.findByItemSearch(itemSearch1).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch1).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        ItemSearch itemSearch2 = new ItemSearch(name);
        assertThat(itemService.findByItemSearch(itemSearch2).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch2).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        ItemSearch itemSearch3 = new ItemSearch(itemType);
        assertThat(itemService.findByItemSearch(itemSearch3).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch3).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        ItemSearch itemSearch4 = new ItemSearch();
        assertThat(itemService.findByItemSearch(itemSearch4).contains(item)).isTrue();
        assertThat(itemService.findByItemSearch(itemSearch4).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        String wrongName = "wrong name";
        ItemSearch itemSearch5 = new ItemSearch(wrongName);
        assertThat(itemService.findByItemSearch(itemSearch5).contains(item)).isFalse();
        assertThat(itemService.findByItemSearch(itemSearch5).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        ItemType wrongItemType = ItemType.BOTTLE;
        ItemSearch itemSearch6 = new ItemSearch(wrongItemType);
        assertThat(itemService.findByItemSearch(itemSearch6).contains(item)).isFalse();
        assertThat(itemService.findByItemSearch(itemSearch6).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void changeName() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        String newName = "new item name";
        itemService.changeName(itemId, newName);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemService.findByName(name).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(itemService.findItem(itemId).getName()).isEqualTo(newName);
        assertThat(itemService.findByName(newName).contains(item)).isTrue();
        assertThat(itemService.findByName(newName).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeItemType() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        ItemType newItemType = ItemType.BOTTLE;
        itemService.changeItemType(itemId, newItemType);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemService.findByItemType(itemType).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getItemType()).isEqualTo(newItemType);
        assertThat(itemService.findItem(itemId).getItemType()).isEqualTo(newItemType);
        assertThat(itemService.findByItemType(newItemType).contains(item)).isTrue();
        assertThat(itemService.findByItemType(newItemType).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changePrice() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        int newPrice = 456;
        itemService.changePrice(itemId, newPrice);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemService.findItems().stream()
                .filter(i -> i.getPrice() == price && i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getPrice()).isEqualTo(newPrice);
        assertThat(itemService.findItem(itemId).getPrice()).isEqualTo(newPrice);
    }

    @Test
    void changePackageCount() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        int newPackageCount = 456;
        itemService.changePackageCount(itemId, newPackageCount);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemService.findByPackageCount(packageCount).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getPackageCount()).isEqualTo(newPackageCount);
        assertThat(itemService.findItem(itemId).getPackageCount()).isEqualTo(newPackageCount);
    }

    @Test
    void changeQuantity() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        double newQuantity = 45.6;
        itemService.changeQuantity(itemId, newQuantity);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemService.findByUnderQuantity(quantity).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getQuantity()).isEqualTo(newQuantity);
        assertThat(itemService.findItem(itemId).getQuantity()).isEqualTo(newQuantity);
    }

    @Test
    void changeUnitType() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        UnitType changeNewUnitType = UnitType.ml;
        itemService.changeUnitType(itemId, changeNewUnitType);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemService.findItems().stream()
                .filter(i -> i.getUnitType().equals(unitType) && i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getUnitType()).isEqualTo(changeNewUnitType);
        assertThat(itemService.findItem(itemId).getUnitType()).isEqualTo(changeNewUnitType);
    }

    @Test
    void removeItem() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 12.3;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);

        itemService.removeItem(item);
        assertThat(itemService.findItem(itemId)).isNull();
    }

    @Test
    void removeById() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;
        List<Ingredient> ingredients = new ArrayList<>();

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .ingredients(ingredients)
                .build();

        Long itemId = itemService.saveItem(item);
        assertThat(item.getId()).isEqualTo(itemId);
        assertThat(itemService.findItem(itemId)).isEqualTo(item);

        itemService.removeById(item.getId());
        assertThat(itemService.findItem(itemId)).isNull();
    }
}