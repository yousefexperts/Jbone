package cn.jbone.sm.gateway.config;

import cn.jbone.cas.common.JboneToken;
import cn.jbone.sm.gateway.filters.TokenFilter;
import cn.jbone.sm.gateway.filters.UserInfoFilter;
import cn.jbone.sm.gateway.token.TokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class GateWayConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(18000L);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public TokenFilter tokenFilter(TokenRepository tokenRepository){
        return new TokenFilter(tokenRepository);
    }

    @Bean
    public UserInfoFilter userInfoFilter(TokenRepository tokenRepository){
        return new UserInfoFilter(tokenRepository);
    }


    @Bean
    public TokenRepository tokenRepository(RedisTemplate<String, JboneToken> redisTemplate){
        return new TokenRepository(redisTemplate);
    }

    @Bean
    public RedisTemplate<String, JboneToken> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, JboneToken> template = new RedisTemplate();
        StringRedisSerializer string = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdk = new JdkSerializationRedisSerializer();
        template.setKeySerializer(string);
        template.setValueSerializer(jdk);
        template.setHashValueSerializer(jdk);
        template.setHashKeySerializer(string);
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}
