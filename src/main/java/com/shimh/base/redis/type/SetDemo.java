package com.shimh.base.redis.type;

import redis.clients.jedis.Jedis;

public class SetDemo {
	public static void main(String[] args) throws Exception {
		new SetDemo().run();
	}
	
	public void run() throws Exception{
		Jedis conn = new Jedis("localhost");
		setTest(conn);
	}
	
	public void setTest(Jedis conn) throws Exception{
			
	      conn.flushDB();
	      System.out.println("============向集合中添加元素============");
	      System.out.println(conn.sadd("eleSet", "e1","e2","e4","e3","e0","e8","e7","e5"));
	      System.out.println(conn.sadd("eleSet", "e6"));
	      System.out.println(conn.sadd("eleSet", "e6"));
	      System.out.println("eleSet的所有元素为："+conn.smembers("eleSet"));
	      System.out.println("删除一个元素e0："+conn.srem("eleSet", "e0"));
	      System.out.println("eleSet的所有元素为："+conn.smembers("eleSet"));
	      System.out.println("删除两个元素e7和e6："+conn.srem("eleSet", "e7","e6"));
	      System.out.println("eleSet的所有元素为："+conn.smembers("eleSet"));
	      System.out.println("随机的移除集合中的一个元素："+conn.spop("eleSet"));
	      System.out.println("随机的移除集合中的一个元素："+conn.spop("eleSet"));
	      System.out.println("eleSet的所有元素为："+conn.smembers("eleSet"));
	      System.out.println("eleSet中包含元素的个数："+conn.scard("eleSet"));
	      System.out.println("e3是否在eleSet中："+conn.sismember("eleSet", "e3"));
	      System.out.println("e1是否在eleSet中："+conn.sismember("eleSet", "e1"));
	      System.out.println("e1是否在eleSet中："+conn.sismember("eleSet", "e5"));
	      System.out.println("=================================");
	      System.out.println(conn.sadd("eleSet1", "e1","e2","e4","e3","e0","e8","e7","e5"));
	      System.out.println(conn.sadd("eleSet2", "e1","e2","e4","e3","e0","e8"));
	      System.out.println("将eleSet1中删除e1并存入eleSet3中："+conn.smove("eleSet1", "eleSet3", "e1"));
	      System.out.println("将eleSet1中删除e2并存入eleSet3中："+conn.smove("eleSet1", "eleSet3", "e2"));
	      System.out.println("eleSet1中的元素："+conn.smembers("eleSet1"));
	      System.out.println("eleSet3中的元素："+conn.smembers("eleSet3"));
	      System.out.println("============集合运算=================");
	      System.out.println("eleSet1中的元素："+conn.smembers("eleSet1"));
	      System.out.println("eleSet2中的元素："+conn.smembers("eleSet2"));
	      System.out.println("eleSet1和eleSet2的交集:"+conn.sinter("eleSet1","eleSet2"));
	      System.out.println("eleSet1和eleSet2的并集:"+conn.sunion("eleSet1","eleSet2"));
	      System.out.println("eleSet1和eleSet2的差集:"+conn.sdiff("eleSet1","eleSet2"));//eleSet1中有，eleSet2中没有
	    
	}
}
