package daepoid.stockManager.api;

import daepoid.stockManager.api.dto.Result;
import daepoid.stockManager.api.dto.cart.*;
import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.service.CartService;
import daepoid.stockManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final CustomerService customerService;

    @GetMapping("")
    public Result cartsV1() {

        List<Cart> carts = cartService.findCarts();

        //엔티티 -> DTO 변환
        List<CartDTO> collect = carts.stream()
                .map(CartDTO::new)
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("")
    public CreateCartResponseDTO createCartV1(@RequestBody @Valid CreateCartRequestDTO requestDTO) {
        Customer customer = customerService.findCustomer(requestDTO.getCustomerId());
        Cart cart = Cart.builder()
                .numberOfMenus(requestDTO.getNumberOfMenus())
                .customer(customer)
                .build();
        Long cartId = cartService.createCart(cart);
        return new CreateCartResponseDTO(cartId);
    }

    @GetMapping("/{cartId}")
    public CartDTO findCart(@PathVariable("cartId") Long cartId) {
        return new CartDTO(cartService.findCart(cartId));
    }

    @PostMapping("/{cartId}")
    public AddCartResponseDTO AddCartMenuV1(@PathVariable("cartId") Long cartId,
                                            @RequestBody @Valid AddCartRequestDTO requestDTO) {
        Cart cart = cartService.findCart(cartId);

        cartService.addMenus(cartId, requestDTO.getNumberOfMenus());

        return new AddCartResponseDTO(cart);
    }

    @PutMapping("/{cartId}")
    public UpdateCartResponseDTO updateCartV1(@PathVariable("cartId") Long cartId,
                                              @RequestBody @Valid UpdateCartRequestDTO requestDTO) {
        Cart cart = cartService.findCart(cartId);
        cartService.changeNumberOfMenus(cartId, requestDTO.getNumberOfMenus());
        return new UpdateCartResponseDTO(cart);
    }

    @DeleteMapping("/{cartId}")
    public ClearCartResponseDTO deleteCartV1(@PathVariable("cartId") Long cartId) {
        Cart cart = cartService.findCart(cartId);
        cartService.clearCart(cartId);
        return new ClearCartResponseDTO(cart.getId());
    }
}