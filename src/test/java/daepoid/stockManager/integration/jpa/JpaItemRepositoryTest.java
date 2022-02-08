package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemSearch;
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
    void findByQuantity() {
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

        assertThat(itemRepository.findByQuantity(quantity).contains(item)).isTrue();
        assertThat(itemRepository.findByQuantity(quantity).stream()
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

        assertThat(itemRepository.findByItemSearch(new ItemSearch()).contains(item)).isEqualTo(true);
        assertThat(itemRepository.findByItemSearch(new ItemSearch(name, itemType)).contains(item)).isEqualTo(true);
        assertThat(itemRepository.findByItemSearch(new ItemSearch(name)).contains(item)).isEqualTo(true);
        assertThat(itemRepository.findByItemSearch(new ItemSearch(itemType)).contains(item)).isEqualTo(true);

        assertThat(itemRepository.findByItemSearch(new ItemSearch()).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(itemRepository.findByItemSearch(new ItemSearch(name, itemType)).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(itemRepository.findByItemSearch(new ItemSearch(name)).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(itemRepository.findByItemSearch(new ItemSearch(itemType)).stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null)).isNotNull();
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
        assertThat(itemRepository.findById(itemId)).isEqualTo(null);
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