//package daepoid.stockManager.repository;
//
//import daepoid.stockManager.domain.item.Item;
//import daepoid.stockManager.domain.item.ItemType;
//import daepoid.stockManager.domain.item.UnitType;
//import daepoid.stockManager.repository.jpa.JpaItemRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//class ItemRepositoryTest {
//
//    @Autowired
//    private JpaItemRepository itemRepository;
//
//    @Test
//    public void saveItem_findItem_성공() throws Exception {
//        // given
//        String name = "water";
//        ItemType itemType = ItemType.BOTTLE;
//        Integer price = 100;
//        Integer packageCount = 100;
//        Double quantity = 100.0;
//        UnitType unitType = UnitType.g;
//
//        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
//        itemRepository.save(item);
//
//        // when
//        Item findItem = itemRepository.findById(item.getId());
//
//        // then
//        Assertions.assertThat(findItem).isEqualTo(item);
//    }
//
//    @Test
//    public void findAll_성공() throws Exception {
//        // given
//        String name = "water";
//        ItemType itemType = ItemType.BOTTLE;
//        Integer price = 100;
//        Integer packageCount = 100;
//        Double quantity = 100.0;
//        UnitType unitType = UnitType.g;
//
//        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
//        itemRepository.save(item);
//
//        // when
//        List<Item> items = itemRepository.findAll();
//
//        // then
//        Assertions.assertThat(items.size()).isEqualTo(1);
//
//    }
//
//    @Test
//    public void findByName_성공() throws Exception {
//        // given
//        String name = "water";
//        ItemType itemType = ItemType.BOTTLE;
//        Integer price = 100;
//        Integer packageCount = 100;
//        Double quantity = 100.0;
//        UnitType unitType = UnitType.g;
//
//        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
//        itemRepository.save(item);
//
//        // when
//        List<Item> findItem = itemRepository.findByName(name);
//
//        // then
//        Assertions.assertThat(findItem.contains(item)).isEqualTo(true);
//
//    }
//
//    @Test
//    public void findByItemType_성공() throws Exception {
//        // given
//        String name = "water";
//        ItemType itemType = ItemType.BOTTLE;
//        Integer price = 100;
//        Integer packageCount = 100;
//        Double quantity = 100.0;
//        UnitType unitType = UnitType.g;
//
//        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
//        itemRepository.save(item);
//
//        // when
//        List<Item> findItem = itemRepository.findByItemType(itemType);
//
//        // then
//        Assertions.assertThat(findItem.contains(item)).isEqualTo(true);
//
//    }
//
//    @Test
//    public void findByQuantity_성공() throws Exception {
//        // given
//        String name = "water";
//        ItemType itemType = ItemType.BOTTLE;
//        Integer price = 100;
//        Integer packageCount = 100;
//        Double quantity = 100.0;
//        UnitType unitType = UnitType.g;
//
//        Item item = Item.createItem(name, itemType, price, packageCount, quantity, unitType);
//        itemRepository.save(item);
//
//        // when
//        List<Item> findItem = itemRepository.findByQuantity(quantity + 1.0);
//
//        // then
//        Assertions.assertThat(findItem.contains(item)).isEqualTo(true);
//    }
//}