package daepoid.stockManager.api.dto.menu;

import lombok.Data;

@Data
public class CreateMenuResponseDTO {

    private Long menuId;

    public CreateMenuResponseDTO(Long menuId) {
        this.menuId = menuId;
    }
}
