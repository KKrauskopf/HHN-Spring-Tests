package hhn.testing.example.cartservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hhn.testing.example.cartservice.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
}