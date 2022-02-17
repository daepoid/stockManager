package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.member.CreateMemberResponseDTO;
import daepoid.stockManager.api.dto.order.CreateOrderRequestDTO;
import daepoid.stockManager.api.dto.order.CreateOrderResponseDTO;
import daepoid.stockManager.api.dto.order.OrderDTO;
import daepoid.stockManager.controller.dto.order.CreateOrderDTO;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.service.CustomerService;
import daepoid.stockManager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * V1. 엔티티 직접 노출
 * - 엔티티가 변하면 API 스펙이 변한다.
 * - 트랜잭션 안에서 지연 로딩 필요
 * - 양방향 연관관계 문제
 *
 * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
 * - 트랜잭션 안에서 지연 로딩 필요
 * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용O)
 * - 페이징 시에는 N 부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경 가능)
 *
 * V4. JPA에서 DTO로 바로 조회, 컬렉션 N 조회 (1 + N Query)
 * - 페이징 가능
 * V5. JPA에서 DTO로 바로 조회, 컬렉션 1 조회 최적화 버전 (1 + 1 Query)
 * - 페이징 가능
 * V6. JPA에서 DTO로 바로 조회, 플랫 데이터(1Query) (1 Query)
 * - 페이징 불가능...
 *
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final CustomerService customerService;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/v1/orders")
    public List<Order> ordersV1() {
        List<Order> orders = orderService.findOrders();
        for (Order order : orders) {
            order.getCustomer().getName(); //Lazy 강제 초기화
            List<OrderMenu> orderMenus = order.getOrderMenus();
            for (OrderMenu orderMenu : orderMenus) {
                orderMenu.getMenu().getName(); //Lazy 강제 초기화
            }
        }
        return orders;
    }

    @GetMapping("/v2/orders")
    public List<OrderDTO> ordersV2() {
        return orderService.findOrders().stream()
                .map(OrderDTO::new)
                .collect(toList());
    }

    @PostMapping("/v1/orders")
    public CreateOrderResponseDTO createOrderV1(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {

        Long orderId = orderService.order(requestDTO.getCustomerId(), requestDTO.getMenuId(), requestDTO.getOrderCount(), LocalDateTime.now());

        return new CreateOrderResponseDTO(orderId);
    }
}
