package daepoid.stockManager.service;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void saveItem_findItem() throws Exception {
        // given
        String name = "water";
        ItemType itemType = ItemType.BOTTLE;
        Integer price = 100;
        Integer packageCount = 100;
        Double quantity = 100.0;
        UnitType unitType = UnitType.g;

        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
        itemService.saveItem(item);

        // when
        Item findItem = itemService.findItem(item.getId());

        // then
        Assertions.assertThat(findItem).isEqualTo(findItem);
    }

    @Test
    public void findItems() throws Exception {
        // given
        String name = "water";
        ItemType itemType = ItemType.BOTTLE;
        Integer price = 100;
        Integer packageCount = 100;
        Double quantity = 100.0;
        UnitType unitType = UnitType.g;

        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
        itemService.saveItem(item);

        // when
        List<Item> findItems = itemService.findItems();

        // then
        Assertions.assertThat(findItems.contains(item)).isEqualTo(true);
        Assertions.assertThat(findItems.size()).isEqualTo(1);
    }

    @Test
    public void findByName() throws Exception {
        // given
        String name = "water";
        ItemType itemType = ItemType.BOTTLE;
        Integer price = 100;
        Integer packageCount = 100;
        Double quantity = 100.0;
        UnitType unitType = UnitType.g;

        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
        itemService.saveItem(item);

        // when
        List<Item> findItems = itemService.findByName(name);

        // then
        Assertions.assertThat(findItems.contains(item)).isEqualTo(true);
        Assertions.assertThat(findItems.size()).isEqualTo(1);
    }

    @Test
    public void findByItemType() throws Exception {
        // given
        String name = "water";
        ItemType itemType = ItemType.BOTTLE;
        Integer price = 100;
        Integer packageCount = 100;
        Double quantity = 100.0;
        UnitType unitType = UnitType.g;

        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
        itemService.saveItem(item);

        // when
        List<Item> findItems = itemService.findByItemType(itemType);

        // then
        Assertions.assertThat(findItems.contains(item)).isEqualTo(true);
        Assertions.assertThat(findItems.size()).isEqualTo(1);
    }

    @Test
    public void findByQuantity() throws Exception {
        // given
        String name = "water";
        ItemType itemType = ItemType.BOTTLE;
        Integer price = 100;
        Integer packageCount = 100;
        Double quantity = 100.0;
        UnitType unitType = UnitType.g;

        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
        itemService.saveItem(item);

        // when
        List<Item> findItems = itemService.findByQuantity(quantity + 1.0);

        // then
        Assertions.assertThat(findItems.contains(item)).isEqualTo(true);
        Assertions.assertThat(findItems.size()).isEqualTo(1);
    }
}