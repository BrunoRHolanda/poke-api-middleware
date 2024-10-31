package com.poke.api.middleware.infrastructure.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Configuration class for setting up Redis caching in the application.
 * This class enables caching and defines the default cache configuration
 * for Redis, including time-to-live (TTL) and serialization settings.
 */
@Configuration
@EnableCaching
public class RedisConfiguration {

    /**
     * Configures the default settings for Redis cache.
     * The cache entries have a time-to-live of 2 hours, null values are not cached,
     * and values are serialized using the {@link GenericJackson2JsonRedisSerializer}.
     *
     * @return a configured instance of {@link RedisCacheConfiguration}
     */
    @Bean
    public RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                                      .entryTtl(Duration.ofMinutes(120)) // 2 hours
                                      .disableCachingNullValues()
                                      .serializeValuesWith(RedisSerializationContext.SerializationPair
                                              .fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
