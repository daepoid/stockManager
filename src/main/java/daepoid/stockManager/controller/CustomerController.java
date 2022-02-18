package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.CustomerOrderSearch;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.controller.dto.order.CustomerOrderMenuDTO;
import daepoid.stockManager.service.CustomerService;
import daepoid.stockManager.service.MenuService;
import daepoid.stockManager.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/customers/{customerId}")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final MenuService menuService;
    private final OrderService orderService;

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
        Customer loginCustomer = customerService.findCustomerByLoginId(loginId);
        if(!Objects.equals(loginCustomer.getId(), customerId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        Map<Menu, Integer> selectedMenus = new HashMap<>();

        Map<Long, Integer> numberOfMenus = customerService.findCustomer(customerId).getCart().getNumberOfMenus();
        log.info("nnumberOfMenus = {}", numberOfMenus);
        for (Long menuId : numberOfMenus.keySet()) {
            selectedMenus.put(menuService.findMenu(menuId), numberOfMenus.get(menuId));
        }

        model.addAttribute("selectedMenus", selectedMenus);
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

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(!Objects.equals(customerService.findCustomerByLoginId(loginId).getId(), customerId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        Customer customer = customerService.findCustomer(customerId);

        String tableNumber = customer.getTableNumber();

        List<CustomerOrderMenuDTO> customerOrderMenuDTOs = new ArrayList<>();

        List<Order> customerOrders = customer.getOrders();
        for (Order customerOrder : customerOrders) {
            for (OrderMenu orderMenu : customerOrder.getOrderMenus()) {
                customerOrderMenuDTOs.add(new CustomerOrderMenuDTO(orderMenu));
            }
        }

        model.addAttribute("orderMenuDTOs", customerOrderMenuDTOs);
        model.addAttribute("tableNumber", tableNumber);
        return "orders/customerOrderListForm";
    }

    /**
     * 장바구니에 담긴 메뉴 취소
     * 고객이 자신의 장바구니에 담긴 메뉴를 취소할 수 있다.
     * @param customerId
     * @param menuId
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/{menuId}/cancel")
    public String cancel(@PathVariable("customerId") Long customerId,
                         @PathVariable("menuId") Long menuId,
                         RedirectAttributes redirectAttributes) {

        Customer customer = customerService.findCustomer(customerId);
        customer.getCart().removeCart(menuId);

        redirectAttributes.addAttribute("customerId", customerId);
        return "redirect:/customers/{customerId}/order";
    }
}
