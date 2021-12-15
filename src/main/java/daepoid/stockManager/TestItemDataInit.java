package daepoid.stockManager;

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
        itemService.saveItem(Item.createItem("item1", ItemType.BOTTLE, 1000, 110, 99.0, UnitType.l));
        itemService.saveItem(Item.createItem("item2", ItemType.BOTTLE, 2000, 210, 88.0, UnitType.ml));
        itemService.saveItem(Item.createItem("item3", ItemType.POWDER, 3000, 310, 77.0, UnitType.kg));
        itemService.saveItem(Item.createItem("item4", ItemType.POWDER, 4000, 410, 66.0, UnitType.g));
        itemService.saveItem(Item.createItem("item5", ItemType.SPICE, 5000, 510, 55.0, UnitType.g));
        itemService.saveItem(Item.createItem("item6", ItemType.SPICE, 6000, 610, 44.0, UnitType.mg));
        itemService.saveItem(Item.createItem("item7", ItemType.VEGETABLE, 7000, 710, 33.0, UnitType.kg));
        itemService.saveItem(Item.createItem("item8", ItemType.VEGETABLE, 8000, 810, 22.0, UnitType.kg));
        itemService.saveItem(Item.createItem("item9", ItemType.MEAT, 9000, 910, 11.0, UnitType.kg));
        itemService.saveItem(Item.createItem("item10", ItemType.MEAT, 10000, 1010, 10.0, UnitType.kg));
    }
}
