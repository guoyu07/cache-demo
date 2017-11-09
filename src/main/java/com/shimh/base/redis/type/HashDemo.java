package com.shimh.base.redis.type;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class HashDemo {
	public static void main(String[] args) throws Exception {
		new HashDemo().run();
	}
	
	public void run() throws Exception{
		Jedis conn = new Jedis("localhost");
		hashTest(conn);
	}
	
	public void hashTest(Jedis conn) throws Exception{
			
        conn.flushDB();
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        conn.hmset("hash",map);
        conn.hset("hash", "key5", "value5");
        System.out.println("散列hash的所有键值对为："+conn.hgetAll("hash"));//return Map<String,String>
        System.out.println("散列hash的所有键为："+conn.hkeys("hash"));//return Set<String>
        System.out.println("散列hash的所有值为："+conn.hvals("hash"));//return List<String>
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6："+conn.hincrBy("hash", "key6", 6));
        System.out.println("散列hash的所有键值对为："+conn.hgetAll("hash"));
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6："+conn.hincrBy("hash", "key6", 3));
        System.out.println("散列hash的所有键值对为："+conn.hgetAll("hash"));
        System.out.println("删除一个或者多个键值对："+conn.hdel("hash", "key2"));
        System.out.println("散列hash的所有键值对为："+conn.hgetAll("hash"));
        System.out.println("散列hash中键值对的个数："+conn.hlen("hash"));
        System.out.println("判断hash中是否存在key2："+conn.hexists("hash","key2"));
        System.out.println("判断hash中是否存在key3："+conn.hexists("hash","key3"));
        System.out.println("获取hash中的值："+conn.hmget("hash","key3"));
        System.out.println("获取hash中的值："+conn.hmget("hash","key3","key4"));
	    
	}
}
