package com.shimh.base.redis.other;

import java.util.List;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class TransactionDemo {
	public static void main(String[] args) throws Exception {
		new TransactionDemo().run();
	}
	
	public void run() throws Exception{
		Jedis conn = new Jedis("localhost");
		//transactionTest(conn);
		//discardTest(conn);
		watchTest(conn);
	}
	
	/*
	 * DISCARD命令可以用来中止事务运行。在这种情况下，不会执行事务中的任何命令，并且会将Redis连接恢复为正常状态。
	 */
	private void discardTest(Jedis conn) {
		
		conn.set("discard", "1");
		conn.incr("discard");
		
		Transaction transaction = conn.multi();

		transaction.incr("discard");

		transaction.discard();
		
		//不能加
		//List<Object> results = transaction.exec();
		
        System.out.println(conn.get("discard"));
		
	}

	public void transactionTest(Jedis conn) throws Exception{
			
		Transaction transaction = conn.multi();

		for(int i=0;i<20;i++){
			
			transaction.set("k"+i, "value" +i);
			transaction.get("k"+i);
		}
		
		List<Object> results = transaction.exec();
		
		//[OK, value0, OK, value1, OK, value2, OK, value3, OK, value4, OK, value5, OK, value6, OK, value7, OK, value8, OK, value9, OK, value10, OK, value11, OK, value12, OK, value13, OK, value14, OK, value15, OK, value16, OK, value17, OK, value18, OK, value19]
        System.out.println(results);
	    
	}
	//会取消事务操作
	public void watchTest(Jedis conn) throws Exception{
		
		conn.set("watch", "123");
		conn.set("watch2", "123");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				conn.watch("watch");
				Transaction transaction = conn.multi();
				transaction.set("watch2", "456");
				
					try {
						TimeUnit.MILLISECONDS.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
					transaction.set("watch", "456");
				List<Object> results = transaction.exec();
				
		        System.out.println(results);
				
			}
		}).start();
		
		Jedis conn2 = new Jedis("localhost");
		TimeUnit.MILLISECONDS.sleep(2000);
		conn2.set("watch", "789");
		
		TimeUnit.MILLISECONDS.sleep(6000);
		
		 System.out.println(conn.get("watch"));
		 System.out.println(conn.get("watch2"));
		 //null
		 //789
		 //123
	}
	
	
}

