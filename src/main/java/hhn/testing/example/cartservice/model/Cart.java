package hhn.testing.example.cartservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@RedisHash("cart")
public class Cart {
    @Id
    private String id;
    private BigDecimal value = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private String attribute1;
}