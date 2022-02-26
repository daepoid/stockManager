package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.order.CreateOrderRequestDTO;
import daepoid.stockManager.api.dto.order.CreateOrderResponseDTO;
import daepoid.stockManager.api.dto.order.OrderDTO;
import daepoid.stockManager.api.dto.order.PagingOrderRequestDTO;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
@Api(tags = {"주문 관리 API"})
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/v1/orders")
    @ApiOperation(value="전체 주문 조회", notes="전체 주문을 조회하여 반환")
    public List<Order> ordersV1() {
        List<Order> orders = orderService.findOrders();
        for (Order order : orders) {
            order.getCustomer().getUserName(); //Lazy 강제 초기화
            List<OrderMenu> orderMenus = order.getOrderMenus();
            for (OrderMenu orderMenu : orderMenus) {
                orderMenu.getMenu().getName(); //Lazy 강제 초기화
            }
        }
        return orders;
    }

    @GetMapping("/v2/orders")
    @ApiOperation(value="전체 주문 조회", notes="전체 주문을 조회하여 반환")
    public List<OrderDTO> ordersV2() {
        return orderService.findOrders().stream()
                .map(OrderDTO::new)
                .collect(toList());
    }

    @GetMapping("/v3/orders")
    @ApiOperation(value="전체 주문 조회", notes="전체 주문을 조회하여 반환")
    public List<OrderDTO> ordersV3(@RequestBody @Valid PagingOrderRequestDTO requestDTO) {
        List<Order> orders;

        if(requestDTO.getFirstResult() == null) {
            orders = orderService.findOrders(requestDTO.getMaxResult());
        } else {
            orders = orderService.findOrders(requestDTO.getFirstResult(), requestDTO.getMaxResult());
        }

        return orders.stream()
                .map(OrderDTO::new)
                .collect(toList());
    }

    @PostMapping("/v1/orders")
    @ApiOperation(value="주문 생성", notes="주문 생성 후 아이디를 반환")
    public CreateOrderResponseDTO createOrderV1(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {

        Long orderId = orderService.order(requestDTO.getCustomerId(), requestDTO.getMenuId(), requestDTO.getOrderCount(), LocalDateTime.now());

        return new CreateOrderResponseDTO(orderId);
    }
}
