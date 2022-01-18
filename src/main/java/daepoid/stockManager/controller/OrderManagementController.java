package daepoid.stockManager.controller;

import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderStatus;
import daepoid.stockManager.dto.EditOrderDTO;
import daepoid.stockManager.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/order-management")
@RequiredArgsConstructor
public class OrderManagementController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final MenuService menuService;
    private final CartService cartService;

    @GetMapping("")
    public String orderList(Model model) {
        model.addAttribute("orders", orderService.findOrders());
        return "order-management/orderList";
    }

    @GetMapping("/order")
    public String createOrderForm(Model model) {
        model.addAttribute("customers", customerService.findCustomers());
        model.addAttribute("menus", menuService.findMenus());
        return "order-management/createOrderForm";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam("customerId") Long customerId,
                              @RequestParam("menuId") Long menuId,
                              @RequestParam("count") Integer count,
                              Model model) {

        Long cartId = customerService.findCustomer(customerId)
                .getCart().getId();

        cartService.addMenu(cartId, menuId, count);

        Order order = Order.builder()
                .customer(customerService.findCustomer(customerId))
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        orderService.order(customerId, menuId, count);
        return "redirect:/order-management";
    }

    @GetMapping("/{orderId}")
    public String editOrderForm(@PathVariable("orderId") Long orderId,
                                @ModelAttribute("editOrderDTO") EditOrderDTO editOrderDTO,
                                Model model) {

        return "order-management/editOrderForm";
    }

    @PostMapping("/{orderId}")
    public String editOrder(@PathVariable("orderId") Long orderId,
                            @Valid @ModelAttribute("editOrderDTO") EditOrderDTO editOrderDTO,
                            BindingResult bindingResult,
                            Model model) {

        if(bindingResult.hasErrors()) {
            return "order-management/editOrderForm";
        }

        return "redirect:/order-management";
    }


}
