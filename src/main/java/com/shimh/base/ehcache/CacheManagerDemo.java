package com.shimh.base.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.MemoryUnit;
/*
 * 
 * test1 test2 test3  
 * 			Ehcache首先会到类根路径下寻找一个叫ehcache.xml的配置文件来配置CacheManager，
 * 				如果没有找到该文件，则会加载CacheManager的默认配置ehcache-failsafe.xml文件，这个文件是在ehcache.jar里面的
 * 
 * test4
 * 			 Configuration是用来指定CacheManager配置信息的，其它通过不同的方式所指定的构造参数最终都会转化为一个对应的Configuration对象，
 * 				然后再利用该Configuration对象初始化CacheManager
 * 
 * 
 */
public class CacheManagerDemo {
	
	public static void main(String[] args) {
		//test1();
		//test4();
		test5();
	}
	
	/*
	 * 在CacheManager内部一共定义有五个create()方法，分别对应于CacheManager的五个newInstance()方法，
	 * 而每一个newInstance()方法又对应于CacheManager对应的构造方法。
	 * 在调用时Ehcache会先判断CacheManager内部持有的singleton是否为空，非空则直接返回singleton，
	 * 否则将返回对应参数的newInstance()返回的实例对象并赋值给singleton。
	 */
	private static void test1(){
		CacheManager cacheManager = CacheManager.create();
		
	   //以config对应的配置创建CacheManager单例  
	   //Configuration config = ...;//以某种方式获取的Configuration对象  
	   //cacheManager = CacheManager.create(config);  
	    
	   //以configurationFileName对应的xml文件定义的配置创建CacheManager单例  
	   //String configurationFileName = ...;//xml配置文件对应的文件名称，包含路径  
	   //cacheManager = CacheManager.create(configurationFileName);  
	    
	   //以is对应的配置信息创建CacheManager单例  
	   //InputStream is = ...; //以某种方式获取到的Xml配置信息对应的输入流  
	   //cacheManager = CacheManager.create(is);  
	    
	   //以URL对应的配置信息创建CacheManager单例  
	   //URL url = ...;  //以某种方式获取到的Xml配置信息对应的URL  
	   //cacheManager = CacheManager.create(url); 
		
		System.out.println(cacheManager.getActiveConfigurationText());  
	}
	/*
	 *  在CacheManager内部一共定义有五个newInstance()方法，分别对应于CacheManager的五个构造方法。
	 *  在调用newInstance()方法时，Ehcache会查看CacheManager内部是否保存有曾经新建的且同名的CacheManager，
	 *  如果有则返回该对象，否则构建一个新的CacheManager对象进行返回。所以newInstance()方法并不一定会产生一个新的对象。
	 */
	private static void test2(){
		CacheManager cacheManager = CacheManager.newInstance();
		
	   //以config对应的配置创建CacheManager  
	   //Configuration config = ...;//以某种方式获取的Configuration对象  
	   //cacheManager = CacheManager.newInstance(config);  
	    
	   //以configurationFileName对应的xml文件定义的配置创建CacheManager  
	   //String configurationFileName = ...;//xml配置文件对应的文件名称，包含路径  
	   //cacheManager = CacheManager.newInstance(configurationFileName);  
	    
	   //以is对应的配置信息创建CacheManager  
	   //InputStream is = ...; //以某种方式获取到的Xml配置信息对应的输入流  
	   //cacheManager = CacheManager.newInstance(is);  
	    
	   //以URL对应的配置信息创建CacheManager  
	   //URL url = ...;  //以某种方式获取到的Xml配置信息对应的URL  
	   //cacheManager = CacheManager.newInstance(url); 
		
		System.out.println(cacheManager.getActiveConfigurationText());  
	}
	
	
	private static void test3(){
		CacheManager cacheManager = new CacheManager();
		System.out.println(cacheManager.getActiveConfigurationText());  
	}
	
	private static void test4(){
	   //新建一个CacheManager的配置信息  
	   Configuration configuration = new Configuration();  
	   //新建一个缓存的配置信息  
	   CacheConfiguration cacheConfiguration = new CacheConfiguration().name("test");  
	   //指定当前缓存的最大堆内存值为100M  
	   cacheConfiguration.maxBytesLocalHeap(100, MemoryUnit.MEGABYTES);  
	   //添加一个cache  
	   configuration.addCache(cacheConfiguration);
	   
	   configuration.dynamicConfig(false);  //不允许动态修改配置信息  
	   CacheManager cacheManager = new CacheManager(configuration);  
	   
	   System.out.println(cacheManager.getActiveConfigurationText());  
	   
	}
	
	private static void test5(){
		CacheManager c1 = new CacheManager();
		CacheManager c2 = CacheManager.create();
		CacheManager c3 = CacheManager.newInstance();
		
		System.out.println(c1);  
		System.out.println(c2);  
		System.out.println(c3);  
		
		//---------------
		CacheManager c11 = CacheManager.create();
		//CacheManager c21 = new CacheManager();   如果存在则抛 Exception
		CacheManager c31 = CacheManager.newInstance();
		
		System.out.println(c11);  
		//System.out.println(c21);  
		System.out.println(c31);  
		
		
		
		
		
		
		
	}
}
