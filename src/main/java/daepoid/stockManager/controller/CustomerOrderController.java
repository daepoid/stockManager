package daepoid.stockManager.controller;

import daepoid.stockManager.SessionConst;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.recipe.Menu;
import daepoid.stockManager.dto.order.CustomerOrderMenuDTO;
import daepoid.stockManager.dto.recipe.SelectedMenuDTO;
import daepoid.stockManager.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerOrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final MemberService memberService;
    private final MenuService menuService;
    private final CartService cartService;

    /*
    1. 메뉴 리스트가 나열되어 보임
    2. 하나를 선택하여 수량을 결정함
    3. 장바구니에 추가 버튼을 통해 장바구니에 추가한 다음 메뉴 리스트로 이동
    4. 추가가 완료된 다음 주문확인 버튼을 통해 주문을 저장
    5. 주문이 생성되면 주문 리스트에 추가됨
    6. 주문 리스트에서 주문 내용에 대해 완료 상태를 체크할 수 있음
     */

    /**
     * 메뉴 리스트
     * 메뉴 리스트를 확인할 수 있다.
     * @param model
     * @return
     */
    @GetMapping("/menus")
    public String menuListForm(Model model, HttpServletRequest request) {

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Customer customer = customerService.findByName(loginId);
        Member member = memberService.findMemberByLoginId(loginId);

        if(customer == null && member == null) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }
        if(customer != null) {
            model.addAttribute("customerId", customer.getId());
        }

        model.addAttribute("menus", menuService.findMenus());
        return "menus/menuList";
    }

    /**
     * 장바구니에 메뉴 추가
     * 고객이 메뉴를 확인하고 자신의 장바구니에 메뉴와 그 수량을 추가할 수 있다.
     * @param menuId
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/menus/{menuId}")
    public String selectedMenuForm(@PathVariable("menuId") Long menuId,
                                   HttpServletRequest request,
                                   Model model) {

        Integer count = 0;
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Customer customer = customerService.findByName(loginId);
        if(customer == null) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }
        model.addAttribute("customerId", customer.getId());

        // 해당 메뉴가 이미 고객의 장바구니에 포함이 되어있는 경우
        if(customer.getCart().getNumberOfMenus().containsKey(menuId)) {
            count = customer.getCart().getNumberOfMenus().get(menuId);
        }
        model.addAttribute("selectedMenuDTO", new SelectedMenuDTO(menuService.findMenu(menuId), count));
        return "menus/selectedMenuForm";
    }
    
    @PostMapping("/menus/{menuId}")
    public String selectedMenu(@PathVariable("menuId") Long menuId,
                               @Valid @ModelAttribute("selectedMenuDTO") SelectedMenuDTO selectedMenuDTO,
                               BindingResult bindingResult,
                               HttpServletRequest request) {

        if(bindingResult.hasErrors()) {
            return "menus/selectedMenuForm";
        }

        if(selectedMenuDTO.getCount() < 1) {
            return "menus/selectedMenuForm";
        }

        String userName = (String) request.getSession().getAttribute(SessionConst.SECURITY_LOGIN);
        if(userName == null) {
            log.error("session username 사라짐");
        }

        Customer loginCustomer = customerService.findByName(userName);
        if(loginCustomer == null) {
            log.error("사용자를 찾을 수 없음");
        }

        boolean isCreated = loginCustomer.getCart().getNumberOfMenus().containsKey(menuId);

        //==loginCustomer.getCart().addMenu()==//

        //==cartService 사용==//
        // cart는 customer가 생성되면서 동시에 같이 생성되어야 한다.
        Cart cart = loginCustomer.getCart();
        cartService.addMenu(cart.getId(), menuId, selectedMenuDTO.getCount());
        return isCreated ? "redirect:/customers/" + loginCustomer.getId() + "/order" : "redirect:/menus";
    }

    @GetMapping("/menus/popular")
    public String popularMenuForm(Model model, HttpServletRequest request) {

        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Customer customer = customerService.findByName(loginId);
        Member member = memberService.findMemberByLoginId(loginId);

        if(customer == null && member == null) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }
        if(customer != null) {
            model.addAttribute("customerId", customer.getId());
        }

        List<Menu> menuList = menuService.findMenus().stream()
                .sorted(Comparator.comparing(Menu::getOrderCount).reversed())
                .collect(Collectors.toList());
        model.addAttribute("menus", menuList);
        return "menus/popularMenuForm";
    }

    @GetMapping("/menus/new-arrivals")
    public String newArrivalsMenuForm(Model model, HttpServletRequest request) {
        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Customer customer = customerService.findByName(loginId);
        Member member = memberService.findMemberByLoginId(loginId);

        if(customer == null && member == null) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }
        if(customer != null) {
            model.addAttribute("customerId", customer.getId());
        }

        List<Menu> menuList = menuService.findMenus().stream()
                .sorted(Comparator.comparing(Menu::getAddedDate).reversed())
                .collect(Collectors.toList());
        model.addAttribute("menus", menuList);
        return "menus/newArrivalsMenuForm";
    }


    /**
     * 주문 하기
     * 장바구니를 보고 내용을 수정하고 주문을 수행할 수 있다.
     * @param customerId
     * @param model
     * @return
     */
    @GetMapping("/customers/{customerId}/order")
    public String customerCartForm(@PathVariable("customerId") Long customerId,
                                   Model model,
                                   HttpServletRequest request) {
        String userName = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        Customer loginCustomer = customerService.findByName(userName);
        if(!Objects.equals(loginCustomer.getId(), customerId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        Map<Menu, Integer> selectedMenus = new HashMap<>();

        Map<Long, Integer> numberOfMenus = customerService.findCustomer(customerId).getCart().getNumberOfMenus();
        for (Long menuId : numberOfMenus.keySet()) {
            selectedMenus.put(menuService.findMenu(menuId), numberOfMenus.get(menuId));
        }

        model.addAttribute("selectedMenus", selectedMenus);
        return "orders/customerCartForm";
    }

    @PostMapping("/customers/{customerId}/order")
    public String customerOrder(@PathVariable("customerId") Long customerId,
                                RedirectAttributes redirectAttributes) {

        Cart cart = customerService.findCustomer(customerId).getCart();
        if(cart == null || cart.getNumberOfMenus().size() < 1) {
            return "orders/customerCartForm";
        }

        orderService.orders(customerId);

        cartService.clearCart(customerService.findCustomer(customerId).getCart().getId());

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
    @GetMapping("/customers/{customerId}/orders")
    public String orderListForm(@PathVariable("customerId") Long customerId,
                                Model model,
                                HttpServletRequest request) {

        String userName = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
        if(!Objects.equals(customerService.findByName(userName).getId(), customerId)) {
            request.getSession(false).invalidate();
            return "redirect:/";
        }

        Customer customer = customerService.findCustomer(customerId);

        Integer tableNumber = customer.getTableNumber();

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
    @PostMapping("/customers/{customerId}/{menuId}/cancel")
    public String cancel(@PathVariable("customerId") Long customerId,
                         @PathVariable("menuId") Long menuId,
                         RedirectAttributes redirectAttributes) {

        Cart cart = customerService.findCustomer(customerId).getCart();
        cartService.removeMenu(cart.getId(), menuId);

        redirectAttributes.addAttribute("customerId", customerId);
        return "redirect:/customers/{customerId}/order";
    }
}
