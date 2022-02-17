package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.item.*;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("")
    public Result findItemsV1() {

        List<Item> items = itemService.findItems();

        //엔티티 -> DTO 변환
        List<ItemDTO> collect = items.stream()
                .map(ItemDTO::new)
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("")
    public CreateItemResponseDTO createItemV1(@RequestBody @Valid CreateItemRequestDTO requestDTO) {
        Item item = Item.builder()
                .name(requestDTO.getName())
                .itemType(requestDTO.getItemType())
                .price(requestDTO.getPrice())
                .quantity(requestDTO.getQuantity())
                .unitType(requestDTO.getUnitType())
                .packageCount(requestDTO.getPackageCount())
                .ingredients(new ArrayList<>())
                .build();

        Long itemId = itemService.saveItem(item);
        return new CreateItemResponseDTO(itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDTO findItemV1(@PathVariable("itemId") Long itemId) {
        return new ItemDTO(itemService.findItem(itemId));
    }

    @PutMapping("/{itemId}")
    public UpdateItemResponseDTO updateItemV1(@PathVariable("itemId") Long itemId,
                                              @RequestBody @Valid UpdateItemRequestDTO requestDTO) {

        itemService.changeName(itemId, requestDTO.getName());
        itemService.changeItemType(itemId, requestDTO.getItemType());
        itemService.changePrice(itemId, requestDTO.getPrice());
        itemService.changeQuantity(itemId, requestDTO.getQuantity());
        itemService.changeUnitType(itemId, requestDTO.getUnitType());
        itemService.changePackageCount(itemId, requestDTO.getPackageCount());

        return new UpdateItemResponseDTO(itemService.findItem(itemId));
    }

    @PatchMapping("/{itemId}")
    public UpdateItemResponseDTO updatePatchItemV1(@PathVariable("itemId") Long itemId,
                                                   @RequestBody @Valid UpdatePatchItemRequestDTO requestDTO) {

        if(requestDTO.getName() != null) {
            itemService.changeName(itemId, requestDTO.getName());
        }
        if(requestDTO.getItemType() != null) {
            itemService.changeItemType(itemId, requestDTO.getItemType());
        }
        if(requestDTO.getPrice() != null) {
            itemService.changePrice(itemId, requestDTO.getPrice());
        }
        if(requestDTO.getQuantity() != null) {
            itemService.changeQuantity(itemId, requestDTO.getQuantity());
        }
        if(requestDTO.getUnitType() != null) {
            itemService.changeUnitType(itemId, requestDTO.getUnitType());
        }
        if(requestDTO.getPackageCount() != null) {
            itemService.changePackageCount(itemId, requestDTO.getPackageCount());
        }

        return new UpdateItemResponseDTO(itemService.findItem(itemId));
    }

    @DeleteMapping("/{itemId}")
    public DeleteItemResponseDTO deleteItemV1(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findItem(itemId);
        itemService.removeItem(item);
        return new DeleteItemResponseDTO(item.getId());
    }
}
