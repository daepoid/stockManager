package daepoid.stockManager.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearch {

    private String name;

    private ItemType itemType;

    public ItemSearch(String name) {
        this.name = name;
    }

    public ItemSearch(ItemType itemType) {
        this.itemType = itemType;
    }
}
