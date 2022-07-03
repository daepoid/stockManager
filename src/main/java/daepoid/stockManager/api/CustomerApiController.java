//package daepoid.stockManager.api;
//
//import daepoid.stockManager.api.dto.Result;
//import daepoid.stockManager.api.dto.customer.*;
//import daepoid.stockManager.domain.users.Customer;
//import daepoid.stockManager.domain.order.Order;
//import daepoid.stockManager.service.CustomerService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api")
//@Api(tags = {"고객(테이블) 관리 API"})
//@RequiredArgsConstructor
//public class CustomerApiController {
//
//    private final CustomerService customerService;
//    private final PasswordEncoder passwordEncoder;
//
//    /**
//     * 조회
//     * @return
//     */
//    @GetMapping("/v1/customers")
//    @ApiOperation(value="전체 고객 조회", notes="전체 고객에 대한 리스트 반환")
//    public Result findCustomersV1() {
//        List<Customer> customers = customerService.findCustomers();
//        //엔티티 -> DTO 변환
//        List<CustomerDTO> CustomerDTOs = customers.stream()
//                .map(CustomerDTO::new)
//                .collect(Collectors.toList());
//        return new Result(CustomerDTOs);
//    }
//
//    @GetMapping("/v2/customers")
//    @ApiOperation(value="전체 고객 조회", notes="전체 고객에 대한 리스트 반환")
//    public Result findCustomersV2(@RequestBody @Valid PagingCustomerRequestDTO requestDTO) {
//        List<Customer> customers;
//
//        if(requestDTO.getFirstResult() == null) {
//            customers = customerService.findCustomers(requestDTO.getMaxResult());
//        } else {
//            customers = customerService.findCustomers(requestDTO.getFirstResult(), requestDTO.getMaxResult());
//        }
//
//        //엔티티 -> DTO 변환
//        List<CustomerDTO> CustomerDTOs = customers.stream()
//                .map(CustomerDTO::new)
//                .collect(Collectors.toList());
//        return new Result(CustomerDTOs);
//    }
//
//    @GetMapping("/v3/customers")
//    @ApiOperation(value="전체 고객 조회", notes="전체 고객에 대한 리스트 반환")
//    public Result findCustomersV3(@RequestParam("firstResult") Integer firstResult,
//                                  @RequestParam("maxResult") Integer maxResult) {
//        List<Customer> customers;
//
//        if(firstResult == null) {
//            customers = customerService.findCustomers(maxResult);
//        } else {
//            customers = customerService.findCustomers(firstResult, maxResult);
//        }
//
//        //엔티티 -> DTO 변환
//        List<CustomerDTO> CustomerDTOs = customers.stream()
//                .map(CustomerDTO::new)
//                .collect(Collectors.toList());
//        return new Result(CustomerDTOs);
//    }
//
//    /**
//     * 등록 V1
//     * @param requestDTO
//     * @return
//     */
//    @PostMapping("/v1/customers")
//    @ApiOperation(value="고객 가입", notes="관리자 권한으로 고객 생성")
//    public CustomerIdResponseDTO saveCustomerV1(@RequestBody @Valid CreateCustomerRequestDTO requestDTO) {
//        Cart cart = requestDTO.getCart();
//        if(cart == null) {
//            cart = new Cart(new HashMap<>());
//        }
//
//        List<Order> orders = requestDTO.getOrders();
//        if(orders == null) {
//            orders = new ArrayList<>();
//        }
//
//        Customer customer = Customer.builder()
//                .userName(requestDTO.getUserName())
//                .password(passwordEncoder.encode(requestDTO.getPassword()))
//                .tableNumber(requestDTO.getTableNumber())
//                .cart(cart)
//                .orders(orders)
//                .build();
//
//        Long customerId = customerService.saveCustomer(customer);
//
//        return new CustomerIdResponseDTO(customerId);
//    }
//
//    /**
//     * 단일 조회 V1
//     * @param customerId
//     * @return
//     */
//    @GetMapping("/v1/customers/{customerId}")
//    @ApiOperation(value="고객 조회", notes="고객 정보 반환")
//    public CustomerDTO findCustomerV1(@PathVariable("customerId") Long customerId) {
//        try {
//            Customer customer = customerService.findCustomer(customerId);
//            return new CustomerDTO(customerService.findCustomer(customerId));
//        } catch(Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 아이디", e);
//        }
//
//
//    }
//
//    /**
//     * Patch 수정 V1
//     * @param customerId
//     * @param requestDTO
//     * @return
//     */
//    @PatchMapping("/v1/customers/{customerId}")
//    @ApiOperation(value="고객 정보 수정", notes="고객 정보 수정 - 이름, 테이블 번호, 장바구니, 주문내역")
//    public UpdateCustomerResponseDTO updateMemberV1(@PathVariable("customerId") Long customerId,
//                                                    @RequestBody @Valid UpdateCustomerRequestDTO requestDTO) {
//        if(customerService.findCustomer(customerId) == null) {
//            throw new IllegalArgumentException("잘못된 아이디");
//        }
//
//        if(!requestDTO.getUserName().isBlank()) {
//            customerService.changeUserName(customerId, requestDTO.getUserName());
//        }
//
//        if(requestDTO.getTableNumber() != null) {
//           customerService.changeTableNumber(customerId, requestDTO.getTableNumber());
//        }
//
//        if(requestDTO.getCart() != null) {
//            customerService.changeCart(customerId, requestDTO.getCart().getNumberOfMenus());
//        }
//
//        if(requestDTO.getOrders() != null) {
//            customerService.changeOrders(customerId, requestDTO.getOrders());
//        }
//
//        return new UpdateCustomerResponseDTO(customerService.findCustomer(customerId));
//    }
//
//    /**
//     * 삭제 V1
//     * @param customerId
//     * @param requestDTO
//     * @return
//     */
//    @DeleteMapping("/v1/customers/{customerId}")
//    @ApiOperation(value="고객 삭제", notes="고객 삭제 - 고객 비밀번호 필요")
//    public DeleteCustomerResponseDTO deleteCustomerV1(@PathVariable("customerId") Long customerId,
//                                                      @RequestBody @Valid DeleteCustomerRequestDTO requestDTO) {
//        Customer customer = customerService.findCustomer(customerId);
//        if(customer == null) {
//            throw new IllegalArgumentException("잘못된 아이디");
//        }
//
//        if(!passwordEncoder.matches(requestDTO.getPassword(), customer.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//
//        customerService.removeCustomer(customerId);
//        return new DeleteCustomerResponseDTO(customer.getId());
//    }
//}
