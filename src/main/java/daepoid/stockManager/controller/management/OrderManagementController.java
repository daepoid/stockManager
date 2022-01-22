package daepoid.stockManager.controller.management;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.dto.order.CreateOrderDTO;
import daepoid.stockManager.dto.order.EditOrderDTO;
import daepoid.stockManager.dto.member.OrderInfoDTO;
import daepoid.stockManager.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public String orderList(Model model,
                            HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }
        model.addAttribute("orders", orderService.findOrders());
        return "order-management/orderList";
    }

    @GetMapping("/order")
    public String createOrderForm(@ModelAttribute("createOrderDTO") CreateOrderDTO createOrderDTO,
                                  Model model,
                                  HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }
        model.addAttribute("customers", customerService.findCustomers());
        model.addAttribute("menus", menuService.findMenus());
        return "order-management/createOrderForm";
    }

    @PostMapping("/order")
    public String createOrder(@Valid @ModelAttribute("createOrderDTO") CreateOrderDTO createOrderDTO,
                              BindingResult bindingResult,
                              Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.findCustomers());
            model.addAttribute("menus", menuService.findMenus());
            return "order-management/createOrderForm";
        }

        Customer customer = customerService.findCustomer(createOrderDTO.getCustomerId());
        if(customer == null) {
            return "order-management/createOrderForm";
        }

        Long cartId = customer.getCart().getId();

        cartService.addMenu(cartId, createOrderDTO.getMenuId(), createOrderDTO.getCount());

        orderService.order(createOrderDTO.getCustomerId(), createOrderDTO.getMenuId(), createOrderDTO.getCount(), LocalDateTime.now());
        return "redirect:/order-management";
    }

    @GetMapping("/{orderId}")
    public String orderInfoForm(@PathVariable("orderId") Long orderId,
                                Model model,
                                HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }

        List<EditOrderDTO> editOrderDTOs = new ArrayList<>();
        List<OrderMenu> orderMenus = orderService.findOrder(orderId).getOrderMenus();
        for (OrderMenu orderMenu : orderMenus) {
            editOrderDTOs.add(new EditOrderDTO(orderMenu));
        }

        model.addAttribute("editOrderDTOs", editOrderDTOs);
        return "order-management/orderInfoForm";
    }

    @GetMapping("/{orderId}/edit")
    public String editOrderStatusForm(@PathVariable("orderId") Long orderId,
                                      Model model,
                                      HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }

        Order order = orderService.findOrder(orderId);

        model.addAttribute("orderInfoDTO", new OrderInfoDTO(order));
        model.addAttribute("orderMenus", order.getOrderMenus());
        return "order-management/editOrderStatusForm";
    }

    @PostMapping("/{orderId}/edit")
    public String editOrderStatus(@PathVariable("orderId") Long orderId,
                                  @Valid @ModelAttribute("orderInfoDTO") OrderInfoDTO orderInfoDTO,
                                  BindingResult bindingResult,
                                  Model model,
                                  HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(loginId != null) {
            model.addAttribute("loginId", loginId);
        }

        if(bindingResult.hasErrors()) {
            return "order-management/editOrderStatusForm";
        }

        orderService.changeOrderStatus(orderId, orderInfoDTO.getOrderStatus());
        return "redirect:/order-management";
    }
}
