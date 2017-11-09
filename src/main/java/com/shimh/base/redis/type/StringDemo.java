package com.shimh.base.redis.type;

import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;

public class StringDemo {
	
	public static void main(String[] args) throws Exception {
		new StringDemo().run();
	}
	
	public void run() throws Exception{
		Jedis conn = new Jedis("localhost");
		stringTest(conn);
		numberTest(conn);
	}
	
	public void stringTest(Jedis conn) throws Exception{
			
		  conn.flushDB();
		  System.out.println("===========增加数据===========");
		  System.out.println(conn.set("key1","value1"));
		  System.out.println(conn.set("key2","value2"));
		  System.out.println(conn.set("key3", "value3"));
		  System.out.println("删除键key2:"+conn.del("key2"));
		  System.out.println("获取键key2:"+conn.get("key2"));
		  System.out.println("修改key1:"+conn.set("key1", "value1Changed"));
		  System.out.println("获取key1的值："+conn.get("key1"));
		  System.out.println("在key3后面加入值："+conn.append("key3", "End"));
		  System.out.println("key3的值："+conn.get("key3"));
		  System.out.println("增加多个键值对："+conn.mset("key01","value01","key02","value02","key03","value03"));
		  System.out.println("获取多个键值对："+conn.mget("key01","key02","key03"));
		  System.out.println("获取多个键值对："+conn.mget("key01","key02","key03","key04"));
		  System.out.println("删除多个键值对："+conn.del(new String[]{"key01","key02"}));
		  System.out.println("获取多个键值对："+conn.mget("key01","key02","key03"));
		 
		      System.out.println("===========新增键值对防止覆盖原先值==============");
		  System.out.println(conn.setnx("key1", "value1"));
		  System.out.println(conn.setnx("key2", "value2"));
		  System.out.println(conn.setnx("key2", "value2-new"));
		  System.out.println(conn.get("key1"));
		  System.out.println(conn.get("key2"));
		 
		      System.out.println("===========新增键值对并设置有效时间=============");
		  System.out.println(conn.setex("key3", 2, "value3"));
		  System.out.println(conn.get("key3"));
		  TimeUnit.SECONDS.sleep(3);
		  System.out.println(conn.get("key3"));
		 
		      System.out.println("===========获取原值，更新为新值==========");//GETSET is an atomic set this value and return the old value command.
		  System.out.println(conn.getSet("key2", "key2GetSet"));
		  System.out.println(conn.get("key2"));
		 
		      System.out.println("===========字符串操作==========");
		  System.out.println("获得key2的值的字串："+conn.getrange("key2", 2, 4));
		  System.out.println(conn.setrange("key2", 7, "123"));
		  System.out.println("key2GetSet setRange后 =" + conn.get("key2"));

	}
	
	public void numberTest(Jedis conn){
		 conn.flushDB();
       conn.set("key1", "1");
       conn.set("key2", "2");
       conn.set("key3", "2.3");
       System.out.println("key1的值："+conn.get("key1"));
       System.out.println("key2的值："+conn.get("key2"));
       System.out.println("key1的值加1："+conn.incr("key1"));
       System.out.println("获取key1的值："+conn.get("key1"));
       System.out.println("key2的值减1："+conn.decr("key2"));
       System.out.println("获取key2的值："+conn.get("key2"));
       System.out.println("将key1的值加上整数5："+conn.incrBy("key1", 5));
       System.out.println("获取key1的值："+conn.get("key1"));
       System.out.println("将key2的值减去整数5："+conn.decrBy("key2", 5));
       System.out.println("获取key2的值："+conn.get("key2"));
       //2.6 后支持
       System.out.println("将key3："+conn.incrByFloat("key3", 0.7));
	}
}
