package com.example.springtest.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description redis配置
 * @Author xuzhongxin
 * @create: 2019-04-03 14:28
 * @Version 1.0
 * 如有代码侵权，公司保留一切法律追究权利
 **/
@Configuration
@EnableCaching
public class RedisConfig {
	/**
	 * 注入 RedisConnectionFactory
	 */
	private final RedisConnectionFactory redisConnectionFactory;

	@Autowired
	public RedisConfig(RedisConnectionFactory redisConnectionFactory) {
		this.redisConnectionFactory = redisConnectionFactory;
	}

	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return RedisTemplate
	 */
	@Bean
	public RedisTemplate<String, Object> functionDomainRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式
	 *
	 * @param redisTemplate redis
	 * @param factory       工厂
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setConnectionFactory(factory);

	}

	/**
	 * 实例化 HashOperations 对象,可以使用 Hash 类型操作
	 *
	 * @param redisTemplate redis
	 * @return HashOperations
	 */
	@Bean
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	/**
	 * 实例化 ValueOperations 对象,可以使用 String 操作
	 *
	 * @param redisTemplate redis
	 * @return ValueOperations
	 */
	@Bean
	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	/**
	 * 实例化 ListOperations 对象,可以使用 List 操作
	 *
	 * @param redisTemplate redis
	 * @return ListOperations
	 */
	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	/**
	 * 实例化 SetOperations 对象,可以使用 Set 操作
	 *
	 * @param redisTemplate redis
	 * @return SetOperations
	 */
	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	/**
	 * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
	 *
	 * @param redisTemplate redis
	 * @return ZSetOperations
	 */
	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}
}
