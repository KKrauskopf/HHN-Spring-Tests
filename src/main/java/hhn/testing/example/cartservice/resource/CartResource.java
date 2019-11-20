package hhn.testing.example.cartservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hhn.testing.example.cartservice.controller.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Api(value = "cartservice")
public class CartResource {

    private final CartService cartService;

    @ApiOperation(value = "Get cart", response = String.class)
    @GetMapping
    public String getCart() {
        return cartService.getAllCarts().toString();
    }

    @ApiOperation(value = "Create cart", response = String.class)
    @PostMapping
    public String createCart() {
        return cartService.createCart().toString();
    }

    @ApiOperation(value = "Recalculate cart", response = String.class)
    @PatchMapping
    public String recalculateCart(@RequestBody String id) {
        return cartService.recalculateCart(id).toString();
    }
}