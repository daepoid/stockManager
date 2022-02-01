package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemSearch;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(item.getId()).isEqualTo(itemId);
        assertThat(item).isEqualTo(em.find(Item.class, itemId));
    }

    @Test
    void findById() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findById(itemId)).isEqualTo(item);
    }

    @Test
    void findAll() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findAll().contains(item)).isEqualTo(true);
        assertThat(itemRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void findByName() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByName(name).contains(item)).isEqualTo(true);
    }

    @Test
    void findByItemType() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByItemType(itemType).contains(item)).isEqualTo(true);
    }

    @Test
    void findByPackageCount() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByPackageCount(packageCount).contains(item)).isEqualTo(true);
    }

    @Test
    void findByQuantity() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByQuantity(quantity).contains(item)).isEqualTo(true);
    }

    @Test
    void findByItemSearch() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        assertThat(itemRepository.findByItemSearch(new ItemSearch()).contains(item)).isEqualTo(true);
        assertThat(itemRepository.findByItemSearch(new ItemSearch(name, itemType)).contains(item)).isEqualTo(true);
        assertThat(itemRepository.findByItemSearch(new ItemSearch(name)).contains(item)).isEqualTo(true);
        assertThat(itemRepository.findByItemSearch(new ItemSearch(itemType)).contains(item)).isEqualTo(true);
    }

    @Test
    void changeName() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        itemRepository.changeName(itemId, name + name);
        assertThat(item.getName()).isEqualTo(name + name);
        assertThat(itemRepository.findById(itemId).getName()).isEqualTo(name + name);
        assertThat(itemRepository.findByName(name + name).contains(item)).isEqualTo(true);
    }

    @Test
    void changeItemType() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        itemRepository.changeItemType(itemId, ItemType.POWDER);
        assertThat(item.getItemType()).isEqualTo(ItemType.POWDER);
        assertThat(itemRepository.findById(itemId).getItemType()).isEqualTo(ItemType.POWDER);
        assertThat(itemRepository.findByItemType(ItemType.POWDER).contains(item)).isEqualTo(true);
    }

    @Test
    void changePrice() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        itemRepository.changePrice(itemId, price * 100);
        assertThat(item.getPrice()).isEqualTo(price * 100);
        assertThat(itemRepository.findById(itemId).getPrice()).isEqualTo(price * 100);
    }

    @Test
    void changePackageCount() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        itemRepository.changePackageCount(itemId, packageCount * 100);

        assertThat(item.getPackageCount()).isEqualTo(packageCount * 100);
        assertThat(itemRepository.findById(itemId).getPackageCount()).isEqualTo(packageCount * 100);
    }

    @Test
    void changeQuantity() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        itemRepository.changeQuantity(itemId, quantity * 2);
        assertThat(item.getQuantity()).isEqualTo(quantity * 2);
        assertThat(itemRepository.findById(itemId).getQuantity()).isEqualTo(quantity * 2);
    }

    @Test
    void changeUnitType() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);
        itemRepository.changeUnitType(itemId, UnitType.mg);
        assertThat(item.getUnitType()).isEqualTo(UnitType.mg);
        assertThat(itemRepository.findById(itemId).getUnitType()).isEqualTo(UnitType.mg);
    }

    @Test
    void removeItem() {
        String name = "item name";
        ItemType itemType = ItemType.MEAT;
        int price = 123;
        double quantity = 123.1;
        UnitType unitType = UnitType.g;
        int packageCount = 123;

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
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

        Item item = Item.builder()
                .name(name)
                .itemType(itemType)
                .price(price)
                .quantity(quantity)
                .unitType(unitType)
                .packageCount(packageCount)
                .build();

        Long itemId = itemRepository.save(item);

        itemRepository.removeById(item.getId());
        assertThat(itemRepository.findById(itemId)).isEqualTo(null);
    }
}