package daepoid.stockManager.dto.recipe;

import daepoid.stockManager.domain.recipe.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedMenuDTO {

    private Long menuId;

    private String name;

    private Integer count;

    public SelectedMenuDTO(Menu menu, Integer count) {
        this.menuId = menu.getId();
        this.name = menu.getName();
        this.count = count;
    }
}
