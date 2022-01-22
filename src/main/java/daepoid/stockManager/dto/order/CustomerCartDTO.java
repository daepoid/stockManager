package daepoid.stockManager.dto.order;

import daepoid.stockManager.domain.recipe.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerCartDTO {

    private List<Menu> selectedMenus = new ArrayList<>();

    private List<Integer> numberOfSelectedMenus = new ArrayList<>();

    public CustomerCartDTO(List<Menu> selectedMenus, List<Integer> numberOfSelectedMenus) {
        this.selectedMenus = selectedMenus;
        this.numberOfSelectedMenus = numberOfSelectedMenus;
    }

}
