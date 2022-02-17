package daepoid.stockManager.controller.dto.recipe;

import daepoid.stockManager.domain.recipe.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedMenuDTO {

    @NotNull
    private Long menuId;

    @NotBlank
    private String name;

    @NotNull
    private int count;

    public SelectedMenuDTO(Menu menu, Integer count) {
        this.menuId = menu.getId();
        this.name = menu.getName();
        this.count = count;
    }
}
