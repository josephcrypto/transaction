package dv16888.com.transaction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@ComponentScan("dv16888.com.transaction")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

//    @Value("${spring.redis.database}")
//    private int database;
//
//    @Value("${spring.redis.password}")
//    private String password;

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
//        configuration.setDatabase(database);
//        configuration.setPassword(RedisPassword.of(password));

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
        return new JedisConnectionFactory(configuration, jedisClientConfiguration.build());
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisSerializer stringSerializer = new StringRedisSerializer();
        final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(stringSerializer);
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        return template;
    }
}
