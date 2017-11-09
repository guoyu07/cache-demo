package com.shimh.base.redis.type;

import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;

public class ListDemo {
	
	public static void main(String[] args) throws Exception {
		new ListDemo().run();
	}
	
	public void run() throws Exception{
		Jedis conn = new Jedis("localhost");
		listTest(conn);
	}
	
	public void listTest(Jedis conn) throws Exception{
			
	    conn.flushDB();
	    System.out.println("===========添加一个list===========");
	    conn.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
	    conn.lpush("collections", "HashSet");
	    conn.lpush("collections", "TreeSet");
	    conn.lpush("collections", "TreeMap");
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));//-1代表倒数第一个元素，-2代表倒数第二个元素
	    System.out.println("collections区间0-3的元素："+conn.lrange("collections",0,3));
	    System.out.println("===============================");
	    // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
	    System.out.println("删除指定元素个数："+conn.lrem("collections", 2, "HashMap"));
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));
	    System.out.println("删除下表0-3区间之外的元素："+conn.ltrim("collections", 0, 3));
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));
	    System.out.println("collections列表出栈（左端）："+conn.lpop("collections"));
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));
	    System.out.println("collections添加元素，从列表右端，与lpush相对应："+conn.rpush("collections", "EnumMap"));
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));
	    System.out.println("collections列表出栈（右端）："+conn.rpop("collections"));
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));
	    System.out.println("修改collections指定下标1的内容："+conn.lset("collections", 1, "LinkedArrayList"));
	    System.out.println("collections的内容："+conn.lrange("collections", 0, -1));
	    System.out.println("===============================");
	    System.out.println("collections的长度："+conn.llen("collections"));
	    System.out.println("获取collections下标为2的元素："+conn.lindex("collections", 2));
	    System.out.println("===============================");
	    conn.lpush("sortedList", "3","6","2","0","7","4");
	    System.out.println("sortedList排序前："+conn.lrange("sortedList", 0, -1));
	    System.out.println(conn.sort("sortedList"));
	    System.out.println("sortedList排序后："+conn.lrange("sortedList", 0, -1));
	    System.out.println("=================阻塞式==============");
	    //conn.blpop(timeout, key)
	    //conn.rpoplpush(srckey, dstkey)
	    
	    
	}
	
	
}
