package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.item.*;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = {"재고 관리 API"})
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/v1/items")
    @ApiOperation(value="전체 재고 조회", notes="전체 재고 정보를 조회하고 반환")
    public Result findItemsV1() {
        List<Item> items = itemService.findItems();
        //엔티티 -> DTO 변환
        List<ItemDTO> ItemDTOs = items.stream()
                .map(ItemDTO::new)
                .collect(Collectors.toList());
        return new Result(ItemDTOs);
    }

    @GetMapping("/v2/items")
    @ApiOperation(value="전체 재고 조회", notes="전체 재고 정보를 조회하고 반환")
    public Result findItemsV2(@RequestBody @Valid PagingItemRequestDTO requestDTO) {
        List<Item> items;

        if(requestDTO.getFirstResult() == null) {
            items = itemService.findItems(requestDTO.getMaxResult());
        } else {
            items = itemService.findItems(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        //엔티티 -> DTO 변환
        List<ItemDTO> ItemDTOs = items.stream()
                .map(ItemDTO::new)
                .collect(Collectors.toList());
        return new Result(ItemDTOs);
    }

    @PostMapping("/v1/items")
    @ApiOperation(value="재고 품목 생성", notes="새로운 재고 품목을 생성하고 아이디를 반환")
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

    @GetMapping("/v1/items/{itemId}")
    @ApiOperation(value="재고 조회", notes="아이디를 이용하여 재고 품목의 정보를 조회")
    public ItemDTO findItemV1(@PathVariable("itemId") Long itemId) {
        return new ItemDTO(itemService.findItem(itemId));
    }

    @PutMapping("/v1/items/{itemId}")
    @ApiOperation(value="재고 수정", notes="재고의 정보를 수정하고 수정된 정보를 반환")
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

    @PatchMapping("/v1/items/{itemId}")
    @ApiOperation(value="재고 수정", notes="재고의 정보를 수정하고 수정된 정보를 반환")
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

    @DeleteMapping("/v1/items/{itemId}")
    @ApiOperation(value="재고 품목 삭제", notes="재고 품목 자체를 삭제하고 아이디를 반환")
    public DeleteItemResponseDTO deleteItemV1(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findItem(itemId);
        itemService.removeItem(item);
        return new DeleteItemResponseDTO(item.getId());
    }
}
