package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.controller.dto.CartFoodsDTO;
import daepoid.stockManager.controller.dto.order.CustomerOrderDTO;
import daepoid.stockManager.domain.food.CartFood;
import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.search.CustomerOrderSearch;
import daepoid.stockManager.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/customers/{customerId}")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final FoodService foodService;
    private final OrderService orderService;

    private final CartFoodService cartFoodService;
    private final LoginUtilService loginUtilService;

    /**
     * 주문 하기
     * 장바구니를 보고 내용을 수정하고 주문을 수행할 수 있다.
     * @param customerId
     * @param model
     * @return
     */
    @GetMapping("/order")
    public String customerCartForm(@PathVariable("customerId") Long customerId,
                                   Model model,
                                   HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Optional<Customer> customer = customerService.findCustomerByLoginId(loginId);
        if(customer.isEmpty() || customer.get().getId().equals(customerId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        List<CartFood> cartFoods = cartFoodService.findCartFoodsByCustomer(customerId);
        List<CartFoodsDTO> cartFoodsDTOs = cartFoods.stream()
                .map(cf -> new CartFoodsDTO(
                        cf.getId(),
                        cf.getFood().getId(),
                        cf.getFood().getFoodName(),
                        cf.getFood().getFoodPrice(),
                        cf.getCount()))
                .collect(Collectors.toList());
        model.addAttribute("cartFoodsDTOs", cartFoodsDTOs);
        return "orders/customerCartForm";
    }

    @PostMapping("/order")
    public String customerOrder(@PathVariable("customerId") Long customerId,
                                RedirectAttributes redirectAttributes) {

        if(orderService.orders(customerId) != null) {
            return "orders/customerCartForm";
        }

        redirectAttributes.addAttribute("customerId", customerId);
        return "redirect:/customers/{customerId}/orders";
    }

    /**
     * 주문 내역
     * 고객이 자신의 주문 내역을 볼 수 있다.
     * @param customerId
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String orderListForm(@PathVariable("customerId") Long customerId,
                                @ModelAttribute("orderSearch") CustomerOrderSearch orderSearch,
                                Model model,
                                HttpServletRequest request) {

        if(!loginUtilService.customerLoginCheck(request, customerId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        List<Order> orders = orderService.findOrdersByCustomerId(customerId);
        List<CustomerOrderDTO> customerOrderDTOs = orders.stream().map(CustomerOrderDTO::new).collect(Collectors.toList());

        model.addAttribute("customerOrderDTOs", customerOrderDTOs);
        return "orders/customerOrderListForm";
    }

//    /**
//     * 장바구니에 담긴 메뉴 취소
//     * 고객이 자신의 장바구니에 담긴 메뉴를 취소할 수 있다.
//     * @param customerId
//     * @param menuId
//     * @param redirectAttributes
//     * @return
//     */
//    @PostMapping("/{foodId}/cancel")
//    public String cancel(@PathVariable("customerId") Long customerId,
//                         @PathVariable("foodId") Long menuId,
//                         RedirectAttributes redirectAttributes) {
//
//
//
//        Customer customer = customerService.findCustomer(customerId);
//        customer.getCart().removeCart(menuId);
//
//        redirectAttributes.addAttribute("customerId", customerId);
//        return "redirect:/customers/{customerId}/order";
//    }
}
