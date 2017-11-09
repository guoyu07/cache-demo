package com.shimh.base.redis.type;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class ZSetDemo {
	public static void main(String[] args) throws Exception {
		new ZSetDemo().run();
	}
	
	public void run() throws Exception{
		Jedis conn = new Jedis("localhost");
		zsetTest(conn);
	}
	
	public void zsetTest(Jedis conn) throws Exception{
			
        conn.flushDB();
        Map<String,Double> map = new HashMap<>();
        map.put("key2",1.2);
        map.put("key3",4.0);
        map.put("key4",5.0);
        map.put("key5",0.2);
        System.out.println(conn.zadd("zset", 3,"key1"));
        System.out.println(conn.zadd("zset",map));
        System.out.println("zset中的所有元素："+conn.zrange("zset", 0, -1));
        System.out.println("zset中的所有元素："+conn.zrangeWithScores("zset", 0, -1));
        System.out.println("zset中的所有元素："+conn.zrangeByScore("zset", 0,100));
        System.out.println("zset中的所有元素："+conn.zrangeByScoreWithScores("zset", 0,100));
        System.out.println("zset中key2的分值："+conn.zscore("zset", "key2"));
        System.out.println("zset中key2的排名："+conn.zrank("zset", "key2"));
        System.out.println("删除zset中的元素key3："+conn.zrem("zset", "key3"));
        System.out.println("zset中的所有元素："+conn.zrange("zset", 0, -1));
        System.out.println("zset中元素的个数："+conn.zcard("zset"));
        System.out.println("zset中分值在1-4之间的元素的个数："+conn.zcount("zset", 1, 4));
        System.out.println("key2的分值加上5："+conn.zincrby("zset", 5, "key2"));
        System.out.println("key3的分值加上4："+conn.zincrby("zset", 4, "key3"));
        System.out.println("zset中的所有元素："+conn.zrange("zset", 0, -1));
	}
}
