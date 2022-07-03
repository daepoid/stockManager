//package daepoid.stockManager.controller;
//
//import daepoid.stockManager.SessionConst;
//import daepoid.stockManager.domain.food.CartFood;
//import daepoid.stockManager.domain.users.Member;
//import daepoid.stockManager.domain.users.Customer;
//import daepoid.stockManager.controller.dto.recipe.SelectedMenuDTO;
//import daepoid.stockManager.domain.search.FoodSearch;
//import daepoid.stockManager.service.*;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Controller
//@RequestMapping("/foods")
//@RequiredArgsConstructor()
//public class FoodsController {
//
//    private final CustomerService customerService;
//    private final MemberService memberService;
//    private final FoodService foodService;
//    private final CartFoodService cartFoodService;
//
//    /*
//    1. 메뉴 리스트가 나열되어 보임
//    2. 하나를 선택하여 수량을 결정함
//    3. 장바구니에 추가 버튼을 통해 장바구니에 추가한 다음 메뉴 리스트로 이동
//    4. 추가가 완료된 다음 주문확인 버튼을 통해 주문을 저장
//    5. 주문이 생성되면 주문 리스트에 추가됨
//    6. 주문 리스트에서 주문 내용에 대해 완료 상태를 체크할 수 있음
//     */
//
//    /**
//     * 메뉴 리스트
//     * 메뉴 리스트를 확인할 수 있다.
//     * @param model
//     * @return
//     */
//    @GetMapping("")
//    public String menuListForm(@ModelAttribute("foodSearch") FoodSearch foodSearch,
//                               Model model, HttpServletRequest request) {
//
//        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
//        if(loginId == null) {
//            request.getSession(false).invalidate();
//            return "redirect:/";
//        }
//
//        Optional<Customer> customer = customerService.findCustomerByLoginId(loginId);
//        Optional<Member> member = memberService.findMemberByLoginId(loginId);
//
//        if(customer.isPresent()) {
//            model.addAttribute("customerId", customer.get().getId());
//            return "menus/menuList";
//        }
//        if(member.isPresent()) {
//            model.addAttribute("menus", foodService.findFoodsByFoodSearch(foodSearch));
//            return "menus/menuList";
//        }
//
//        request.getSession(false).invalidate();
//        return "redirect:/";
//    }
//
//    /**
//     * 장바구니에 메뉴 추가
//     * 고객이 메뉴를 확인하고 자신의 장바구니에 메뉴와 그 수량을 추가할 수 있다.
//     * @param foodId
//     * @param request
//     * @param model
//     * @return
//     */
//    @GetMapping("/foods/{foodId}")
//    public String selectedMenuForm(@PathVariable("foodId") Long foodId,
//                                   HttpServletRequest request,
//                                   Model model) {
//
//        Integer count = 0;
//        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
//        if(loginId == null) {
//            request.getSession(false).invalidate();
//            return "redirect:/";
//        }
//
//        Optional<Customer> customer = customerService.findCustomerByLoginId(loginId);
//        if(customer.isEmpty()) {
//            request.getSession(false).invalidate();
//            return "redirect:/";
//        }
//        model.addAttribute("customerId", customer.get().getId());
//
//        // 해당 메뉴가 이미 고객의 장바구니에 포함이 되어있는 경우
//        List<CartFood> cartFoods = cartFoodService.findCartFoodsByCustomer(customer.get().getId());
//        Optional<CartFood> cartFood = cartFoods.stream().filter(cf -> cf.getFood().getId().equals(foodId)).findFirst();
//        if(cartFood.isPresent()) {
//            cartFood.get().addCount();
//        }
//
//        if(customer.getCart().getNumberOfMenus().containsKey(menuId)) {
//            count = customer.getCart().getNumberOfMenus().get(menuId);
//        }
//        model.addAttribute("selectedMenuDTO", new SelectedMenuDTO(menuService.findMenu(menuId), count));
//        return "menus/selectedMenuForm";
//    }
//
//    @PostMapping("/menus/{menuId}")
//    public String selectedMenu(@PathVariable("menuId") Long menuId,
//                               @Valid @ModelAttribute("selectedMenuDTO") SelectedMenuDTO selectedMenuDTO,
//                               BindingResult bindingResult,
//                               HttpServletRequest request) {
//
//        if(bindingResult.hasErrors()) {
//            return "menus/selectedMenuForm";
//        }
//
//        if(selectedMenuDTO.getCount() < 1) {
//            return "menus/selectedMenuForm";
//        }
//
//        String loginId = (String) request.getSession().getAttribute(SessionConst.SECURITY_LOGIN);
//        Customer customer = customerService.findCustomerByLoginId(loginId);
//        if(customer == null) {
//            log.error("사용자를 찾을 수 없음");
//            request.getSession(false).invalidate();
//            return "redirect:/";
//        }
//
//        boolean alreadyCreated = customer.getCart().getNumberOfMenus().containsKey(menuId);
//
//        customerService.addCart(customer.getId(), selectedMenuDTO.getMenuId(), selectedMenuDTO.getCount());
//
//        return alreadyCreated ? "redirect:/customers/" + customer.getId() + "/order" : "redirect:/menus";
//    }
//
//    @GetMapping("/menus/popular")
//    public String popularMenuForm(Model model, HttpServletRequest request) {
//
//        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
//        Customer customer = customerService.findCustomerByLoginId(loginId);
//        Member member = memberService.findMemberByLoginId(loginId);
//
//        if(customer == null && member == null) {
//            request.getSession(false).invalidate();
//            return "redirect:/";
//        }
//        if(customer != null) {
//            model.addAttribute("customerId", customer.getId());
//        }
//
//        List<Menu> menuList = menuService.findMenus().stream()
//                .sorted(Comparator.comparing(Menu::getSalesCount).reversed())
//                .collect(Collectors.toList());
//        model.addAttribute("menus", menuList);
//        return "menus/popularMenuForm";
//    }
//
//    @GetMapping("/menus/new-arrivals")
//    public String newArrivalsMenuForm(Model model, HttpServletRequest request) {
//        String loginId = (String) request.getSession(false).getAttribute(SessionConst.SECURITY_LOGIN);
//        Customer customer = customerService.findCustomerByLoginId(loginId);
//        Member member = memberService.findMemberByLoginId(loginId);
//
//        if(customer == null && member == null) {
//            request.getSession(false).invalidate();
//            return "redirect:/";
//        }
//        if(customer != null) {
//            model.addAttribute("customerId", customer.getId());
//        }
//
//        List<Menu> menuList = menuService.findMenus().stream()
//                .sorted(Comparator.comparing(Menu::getAddedDate).reversed())
//                .collect(Collectors.toList());
//        model.addAttribute("menus", menuList);
//        return "menus/newArrivalsMenuForm";
//    }
//}
