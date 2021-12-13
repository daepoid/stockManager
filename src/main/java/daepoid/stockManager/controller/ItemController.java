package daepoid.stockManager.controller;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.dto.CreateItemDTO;
import daepoid.stockManager.dto.EditItemDTO;
import daepoid.stockManager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createItemForm(@ModelAttribute("createItemDTO") CreateItemDTO createItemDTO) {
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String createItemForm(@Valid @ModelAttribute("createItemDTO") CreateItemDTO createItemDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "items/createItemForm";
        }

        if (itemService.findByName(createItemDTO.getName()).size() > 0) {
            return "items/createItemForm";
        }

        Item item = Item.createItem(
                createItemDTO.getName(),
                createItemDTO.getItemType(),
                createItemDTO.getPrice(),
                createItemDTO.getPackageCount(),
                createItemDTO.getQuantity(),
                createItemDTO.getUnitType());
        itemService.saveItem(item);

        redirectAttributes.addAttribute("itemId", item.getId());
//        return "redirect:/items/{itemId}";
        return "redirect:/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editItemForm(@PathVariable("itemId") Long itemId,
                               Model model,
                               HttpServletRequest request) {

        model.addAttribute("editItemDTO", new EditItemDTO(itemService.findItem(itemId)));
        return "items/editItemForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long itemId,
                           @Valid @ModelAttribute("editItemDTO") EditItemDTO editItemDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "items/editItemForm";
        }

        // 수정
        itemService.changeInfo(itemId, editItemDTO.getName(), editItemDTO.getItemType());
        itemService.changePrice(itemId, editItemDTO.getPrice());
        itemService.changePackageCount(itemId, editItemDTO.getPackageCount());
        itemService.changeQuantity(itemId, editItemDTO.getQuantity(), editItemDTO.getUnitType());

//        redirectAttributes.addAttribute("itemId", itemId);
//        return "redirect:/items/{itemId}/edit";
        return "redirect:/items";
    }

    @GetMapping("")
    public String itemList(Model model) {
        model.addAttribute("itemList", itemService.findItems());
        return "items/itemList";
    }
}
