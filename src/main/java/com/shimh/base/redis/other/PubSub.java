package com.shimh.base.redis.other;

import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PubSub {
	public static void main(String[] args) {
		new PubSub().run();
	}
	public void run(){
		SubListener sl = new SubListener();
		testSubscribe(sl);
		testPub();
		
		//10秒后取消订阅
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sl.unsubscribe("channel1");
	}
	private void testPub() {

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Jedis conn = new Jedis("localhost");
				
				while(true){
					conn.publish("channel1", "hello !");
					try {
						TimeUnit.MILLISECONDS.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				
				
			}
		}).start();
		
	}
	private void testSubscribe(SubListener sl) {
		Jedis conn = new Jedis("localhost");
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Jedis conn = new Jedis("localhost");
				
				//阻塞
				conn.subscribe(sl, "channel1");
				
			}
		}).start();
	}
}

class SubListener extends JedisPubSub{

	@Override
	public void onMessage(String channel, String message) {
		System.out.println("channel is =" + channel +"--message is =" +message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("subscribe channel is =" + channel +"--subscribedChannels is =" + subscribedChannels);
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("unsubscribe channel is =" + channel +"--subscribedChannels is =" +subscribedChannels);
	}


}