package com.rohans.prime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Pretty standard spring boot servlet class. Used to start the rest-server.
 * @author Rohan Sunder
 *
 */
@ComponentScan({"com.rohans.prime"})
@SpringBootApplication
public class PrimeServerApplication extends SpringBootServletInitializer {
	
	/**
	 * Establish Jedis connection factory
	 * @return
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory connFactory = new JedisConnectionFactory();
		connFactory.setHostName("localhost");
		return connFactory;
	}

	/**
	 * Get RedisTemplate
	 * @return
	 */
	@Bean
	@Qualifier("StringRedisTemplate")
	StringRedisTemplate getStringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(this.jedisConnectionFactory());
		return template;
	}

	/**
	 * Create RedisTemplate, will use this one.
	 * @return
	 */
	@Bean
	@Qualifier("RedisTemplate")
	RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(this.jedisConnectionFactory());

		// these are required to ensure keys and values are correctly serialized
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PrimeServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PrimeServerApplication.class, args);
	}
}
