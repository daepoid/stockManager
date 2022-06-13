package daepoid.stockManager.testData;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.service.ItemService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestItemDataInit {

    private final ItemService itemService;

    @PostConstruct
    public void init() {
        itemService.saveItem(
                Item.builder()
                        .name("stock item 1")
                        .itemType(ItemType.MEAT)
                        .price(1000)
                        .quantity(110.0)
                        .unitType(UnitType.kg)
                        .packageCount(110)
                        .countryOfOrigin("서울")
                        .notice("Notice")
                        .build()
        );

        itemService.saveItem(
                Item.builder()
                        .name("stock item 2")
                        .itemType(ItemType.BOTTLE)
                        .price(2000)
                        .quantity(220.0)
                        .unitType(UnitType.l)
                        .packageCount(220)
                        .countryOfOrigin("서울")
                        .notice("Notice")
                        .build()
        );

        itemService.saveItem(
                Item.builder()
                        .name("stock item 3")
                        .itemType(ItemType.POWDER)
                        .price(3000)
                        .quantity(330.0)
                        .unitType(UnitType.g)
                        .packageCount(330)
                        .countryOfOrigin("서울")
                        .notice("Notice")
                        .build()
        );

        itemService.saveItem(
                Item.builder()
                        .name("stock item 4")
                        .itemType(ItemType.SPICE)
                        .price(4000)
                        .quantity(440.0)
                        .unitType(UnitType.ml)
                        .packageCount(440)
                        .countryOfOrigin("서울")
                        .notice("Notice")
                        .build()
        );

        itemService.saveItem(
                Item.builder()
                        .name("stock item 5")
                        .itemType(ItemType.VEGETABLE)
                        .price(5000)
                        .quantity(550.0)
                        .unitType(UnitType.mg)
                        .packageCount(550)
                        .countryOfOrigin("서울")
                        .notice("Notice")
                        .build()
        );
    }
}
