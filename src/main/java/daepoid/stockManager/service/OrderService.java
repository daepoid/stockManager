package daepoid.stockManager.service;

import daepoid.stockManager.domain.food.CartFood;
import daepoid.stockManager.domain.order.*;
import daepoid.stockManager.domain.search.ManagerOrderSearch;
import daepoid.stockManager.domain.users.Customer;
import daepoid.stockManager.repository.jpa.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class OrderService {

    private final JpaOrderRepository orderRepository;
    private final OrderMenuService orderMenuService;
    private final CustomerService customerService;
    private final CartFoodService cartFoodService;

    //==비즈니스 로직==//
    @Transactional
    public Long orders(Long customerId) {

        // 엔티티 조회
        Optional<Customer> customer = customerService.findCustomer(customerId);
        if(customer.isEmpty()) {
            return null;
        }

        List<CartFood> cartFoods = cartFoodService.findCartFoodsByCustomer(customerId);

        // 주문 생성
        Order order = Order.builder()
                .customer(customer.get())
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        for (CartFood cartFood : cartFoods) {
            OrderMenu orderMenu = OrderMenu.builder()
                    .order(order)
                    .food(cartFood.getFood())
                    .orderPrice(cartFood.getFood().getFoodPrice())
                    .orderCount(cartFood.getCount())
                    .build();
            orderMenuService.saveOrderMenu(orderMenu);
        }

        // 주문 환료 후 장바구니 비우기
        cartFoodService.removeByCustomerId(customerId);
        return order.getId();
    }

    @Transactional
    public boolean cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            List<OrderMenu> orderMenus = orderMenuService.findOrderMenusByOrderId(orderId);
            for (OrderMenu orderMenu : orderMenus) {
                orderMenu.cancel();
            }
            order.get().cancel();
            return true;
        }
        return false;
    }


    //==생성 로직==//
    @Transactional
    public Long save(Order order) {
        return orderRepository.save(order);
    }

    //==조회 로직==//
    public Optional<Order> findOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findOrders(int maxResult) {
        return orderRepository.findAll(maxResult);
    }

    public List<Order> findOrders(int firstResult, int maxResult) {
        return orderRepository.findAll(firstResult, maxResult);
    }

    public List<Order> findOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomer(customerId);
    }

    public List<Order> findOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    public List<Order> findOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> findOrdersByManagerOrderSearch(ManagerOrderSearch orderSearch) {
        return orderRepository.findByManagerOrderSearch(orderSearch);
    }

    //==수정 로직==//
    @Transactional
    public boolean changeCustomer(Long orderId, Customer customer) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            order.get().changeCustomer(customer);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            order.get().changeOrderStatus(orderStatus);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeTotalOrderPrice(Long orderId, Double totalOrderPrice) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            order.get().changeTotalOrderPrice(totalOrderPrice);
            return true;
        }
        return false;
    }

    //==삭제 로직==//
    @Transactional
    public boolean removeOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            orderRepository.remove(orderId);
            return true;
        }
        return false;
    }
}
