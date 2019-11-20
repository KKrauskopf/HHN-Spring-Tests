package hhn.testing.example.cartservice.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "application.cart.redis", name = "host")
public class RedisStandalone {
    @Value("${application.cart.redis.host}")
    private String redisHostName;
    @Value("${application.cart.redis.port}")
    private int redisPort;
    @Value("${application.cart.redis.secret}")
    private String secret;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHostName);
        redisStandaloneConfiguration.setPort(redisPort);
        if(!StringUtils.isEmpty(secret)) {
            redisStandaloneConfiguration.setPassword(secret);
        }
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        checkConnection(lettuceConnectionFactory);
        return lettuceConnectionFactory;
    }

    private void checkConnection(LettuceConnectionFactory lettuceConnectionFactory) {
        try {
            log.info("Checking connection to {}:{}", redisHostName, redisPort);
            RedisConnection connection = lettuceConnectionFactory.getConnection();
            connection.close();
            log.info("Successfully connected to {}:{}", redisHostName, redisPort);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
