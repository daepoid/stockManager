package daepoid.stockManager.controller.dto.recipe;

import daepoid.stockManager.domain.food.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedMenuDTO {

    @NotNull
    private Long menuId;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Integer count;

    private String imgUrl;

    private String menuInfo;

    public SelectedMenuDTO(Menu menu, Integer count) {
        this.menuId = menu.getId();
        this.name = menu.getName();
        this.imgUrl = menu.getImgUrl();
        this.menuInfo = menu.getMenuInfo();
        this.count = count;
    }
}
