package daepoid.stockManager.controller;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.dto.CreateItemDTO;
import daepoid.stockManager.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "items/createItemForm";
        }

        List<Item> findItem = itemService.findByName(createItemDTO.getName());
        if(findItem.size() != 0) {
            return "items/createItemForm";
        }

        itemService.saveItem(findItem.get(0));
        return "items/itemList";
    }
}
