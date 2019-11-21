package hhn.testing.example.cartservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hhn.testing.example.cartservice.controller.CartService;
import hhn.testing.example.cartservice.model.Cart;
import hhn.testing.example.cartservice.repository.CartRepository;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CartserviceApplicationTests {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartService cartService;

	private Cart cart = new Cart();

	@Before
	public void setUp() {
		cart.setId("test");
		cartRepository.save(cart);
	}

	@Test
	public void createCart_cartIsCreated() {
		Cart newCart = cartService.createCart();
		assertThat(newCart.getId()).isNotEqualTo(cart.getId());
	}

	@Test
	public void getAllCarts_returnTwoCarts() {
		cartRepository.save(new Cart());
		assertThat(cartService.getAllCarts()).hasSize(2);
	}

	@Test
	public void applyDiscount_DiscountIsFive() {
		Cart newCart = cartService.discountCart(cart.getId());
		assertThat(newCart.getDiscount()).isEqualTo(BigDecimal.TEN);
		assertThat(cart.getId()).isEqualTo(newCart.getId());
	}	

	@After
	public void cleanUp() {
		cartRepository.deleteAll();
	}

}
