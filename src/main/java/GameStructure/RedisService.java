package GameStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, GameComm> redisTemplate;

    public void setValue(String key, GameComm value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public GameComm getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
