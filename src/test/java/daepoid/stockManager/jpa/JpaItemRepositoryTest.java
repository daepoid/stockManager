package daepoid.stockManager.jpa;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.search.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.jpa.JpaItemRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaItemRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaItemRepository itemRepository;

    @Test
    void save() {
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

        Long itemId = itemRepository.save(item);

        assertThat(item.getId()).isEqualTo(itemId);
    }

    @Test
    void findById() {
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

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findById(itemId)).isEqualTo(item);
        assertThat(itemRepository.findById(itemId).getId()).isEqualTo(item.getId());
    }

    @Test
    void findAll() {
        int size = itemRepository.findAll().size();

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

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findAll().contains(item)).isTrue();
        assertThat(itemRepository.findAll().size()).isEqualTo(size + 1);
        assertThat(itemRepository.findAll().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByName() {
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

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByName(name).contains(item)).isTrue();
        assertThat(itemRepository.findByName(name).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByItemType() {
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

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByItemType(itemType).contains(item)).isTrue();
        assertThat(itemRepository.findByItemType(itemType).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByPackageCount() {
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

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByPackageCount(packageCount).contains(item)).isTrue();
        assertThat(itemRepository.findByPackageCount(packageCount).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByUnderQuantity() {
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

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByUnderQuantity(quantity).contains(item)).isTrue();
        assertThat(itemRepository.findByUnderQuantity(quantity).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByItemSearch() {
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

        Long itemId = itemRepository.save(item);

        ItemSearch itemSearch1 = new ItemSearch(name, itemType);
        assertThat(itemRepository.findByItemSearch(itemSearch1).contains(item)).isTrue();
        assertThat(itemRepository.findByItemSearch(itemSearch1).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        ItemSearch itemSearch2 = new ItemSearch(name);
        assertThat(itemRepository.findByItemSearch(itemSearch2).contains(item)).isTrue();
        assertThat(itemRepository.findByItemSearch(itemSearch2).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        ItemSearch itemSearch3 = new ItemSearch(itemType);
        assertThat(itemRepository.findByItemSearch(itemSearch3).contains(item)).isTrue();
        assertThat(itemRepository.findByItemSearch(itemSearch3).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        ItemSearch itemSearch4 = new ItemSearch();
        assertThat(itemRepository.findByItemSearch(itemSearch4).contains(item)).isTrue();
        assertThat(itemRepository.findByItemSearch(itemSearch4).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        String wrongName = "wrong name";
        ItemSearch itemSearch5 = new ItemSearch(wrongName);
        assertThat(itemRepository.findByItemSearch(itemSearch5).contains(item)).isFalse();
        assertThat(itemRepository.findByItemSearch(itemSearch5).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        ItemType wrongItemType = ItemType.BOTTLE;
        ItemSearch itemSearch6 = new ItemSearch(wrongItemType);
        assertThat(itemRepository.findByItemSearch(itemSearch6).contains(item)).isFalse();
        assertThat(itemRepository.findByItemSearch(itemSearch6).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void changeName() {
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

        Long itemId = itemRepository.save(item);

        String newName = "new Item Name";
        itemRepository.changeName(itemId, newName);

        assertThat(item.getName()).isEqualTo(newName);
        assertThat(itemRepository.findById(itemId).getName()).isEqualTo(newName);
        assertThat(itemRepository.findByName(newName).contains(item)).isTrue();
        assertThat(itemRepository.findByName(newName).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeItemType() {
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

        Long itemId = itemRepository.save(item);

        ItemType newItemType = ItemType.POWDER;
        itemRepository.changeItemType(itemId, newItemType);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemRepository.findByItemType(itemType).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getItemType()).isEqualTo(newItemType);
        assertThat(itemRepository.findById(itemId).getItemType()).isEqualTo(newItemType);
        assertThat(itemRepository.findByItemType(newItemType).contains(item)).isTrue();
        assertThat(itemRepository.findByItemType(newItemType).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changePrice() {
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

        Long itemId = itemRepository.save(item);

        int newPrice = 123123;
        itemRepository.changePrice(itemId, newPrice);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemRepository.findAll().stream()
                .filter(i -> i.getPrice() == price && i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getPrice()).isEqualTo(newPrice);
        assertThat(itemRepository.findById(itemId).getPrice()).isEqualTo(newPrice);
    }

    @Test
    void changePackageCount() {
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

        Long itemId = itemRepository.save(item);

        int newPackageCount = 123123;
        itemRepository.changePackageCount(itemId, newPackageCount);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemRepository.findByPackageCount(packageCount).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getPackageCount()).isEqualTo(newPackageCount);
        assertThat(itemRepository.findById(itemId).getPackageCount()).isEqualTo(newPackageCount);
    }

    @Test
    void changeQuantity() {
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

        Long itemId = itemRepository.save(item);

        double newQuantity = 123.123;
        itemRepository.changeQuantity(itemId, newQuantity);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemRepository.findByUnderQuantity(quantity).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getQuantity()).isEqualTo(newQuantity);
        assertThat(itemRepository.findById(itemId).getQuantity()).isEqualTo(newQuantity);
    }

    @Test
    void changeUnitType() {
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

        Long itemId = itemRepository.save(item);

        UnitType newUnitType = UnitType.mg;
        itemRepository.changeUnitType(itemId, newUnitType);

        // 변경전의 값을 이용해서 찾는 경우 결과가 null이다.
        assertThat(itemRepository.findAll().stream()
                .filter(i -> i.getUnitType().equals(unitType) && i.getId().equals(itemId))
                .findFirst().orElse(null)).isNull();

        assertThat(item.getUnitType()).isEqualTo(newUnitType);
        assertThat(itemRepository.findById(itemId).getUnitType()).isEqualTo(newUnitType);
    }

    @Test
    void removeItem() {
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

        Long itemId = itemRepository.save(item);

        itemRepository.removeItem(item);
        assertThat(itemRepository.findById(itemId)).isNull();
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

        Long itemId = itemRepository.save(item);
        assertThat(item.getId()).isEqualTo(itemId);
        assertThat(itemRepository.findById(itemId)).isEqualTo(item);

        itemRepository.removeById(item.getId());
        assertThat(itemRepository.findById(itemId)).isNull();
    }
}