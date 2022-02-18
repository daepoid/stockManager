package daepoid.stockManager.controller;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.controller.dto.item.CreateItemDTO;
import daepoid.stockManager.controller.dto.item.EditItemDTO;
import daepoid.stockManager.domain.item.ItemSearch;
import daepoid.stockManager.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 재고 품목 리스트
     * @param model
     * @return
     */
    @GetMapping("")
    public String itemList(@ModelAttribute("itemSearch") ItemSearch itemSearch,
                           Model model) {
        model.addAttribute("itemList", itemService.findByItemSearch(itemSearch));
//        model.addAttribute("itemList", itemService.findItems());
        return "items/itemList";
    }

    /**
     * 재고 품목 생성
     * @param createItemDTO
     * @return
     */
    @GetMapping("/new")
    public String createItemForm(@ModelAttribute("createItemDTO") CreateItemDTO createItemDTO) {
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String createItemForm(@Valid @ModelAttribute("createItemDTO") CreateItemDTO createItemDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "items/createItemForm";
        }

        if (itemService.findByName(createItemDTO.getName()).size() > 0) {
            return "items/createItemForm";
        }

        Long itemId = itemService.saveItem(Item.builder()
                .name(createItemDTO.getName())
                .itemType(createItemDTO.getItemType())
                .price(createItemDTO.getPrice())
                .quantity(createItemDTO.getQuantity())
                .unitType(createItemDTO.getUnitType())
                .packageCount(createItemDTO.getPackageCount())
                .build());

        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/items";
    }

    /**
     * 재고 품목 수정 및 재고 품목 정보 보기
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/edit")
    public String editItemForm(@PathVariable("itemId") Long itemId, Model model) {
        model.addAttribute("editItemDTO", new EditItemDTO(itemService.findItem(itemId)));
        return "items/editItemForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long itemId,
                           @Valid @ModelAttribute("editItemDTO") EditItemDTO editItemDTO,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "items/editItemForm";
        }

        // 수정
        itemService.changeName(itemId, editItemDTO.getName());
        itemService.changeItemType(itemId, editItemDTO.getItemType());
        itemService.changePrice(itemId, editItemDTO.getPrice());
        itemService.changePackageCount(itemId, editItemDTO.getPackageCount());
        itemService.changeQuantity(itemId, editItemDTO.getQuantity());
        itemService.changeUnitType(itemId, editItemDTO.getUnitType());
        return "redirect:/items";
    }
}
