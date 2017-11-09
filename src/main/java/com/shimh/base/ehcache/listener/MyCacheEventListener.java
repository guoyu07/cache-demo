package com.shimh.base.ehcache.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
/*
 * Cache级别的监听器  其能监听到Cache中元素的添加、删除、更新等
 * 
 * l  notifyElementRemoved方法会在往Cache中移除单个元素时被调用，即在调用Cache的remove方法之后被调用。
 *	l  notifyElementPut方法会在往Cache中添加元素时被调用。调用Cache的put方法添加元素时会被阻塞，直到对应的notifyElementPut方法返回之后。
 *	l  notifyElementUpdated方法，当往Cache中put一个已经存在的元素时就会触发CacheEventListener的notifyElementUpdated方法，此时put操作也会处于阻塞状态，直到notifyElementUpdated方法执行完毕。
　＊	l  notifyElementExpired方法，当Ehcache检测到Cache中有元素已经过期的时候将调用notifyElementExpired方法。
　＊　l  notifyElementEvicted方法将会在元素被驱除的时候调用。
　＊　l  notifyRemoveAll方法将在调用Cache的removeAll方法之后被调用。
	dispose方法用于释放资源。
 * 
 */
public class MyCacheEventListener implements CacheEventListener {  
	   
	   @Override  
	   public void notifyElementRemoved(Ehcache cache, Element element)  
	         throws CacheException {  
	      System.out.println("removed");  
	   }  
	   
	   @Override  
	   public void notifyElementPut(Ehcache cache, Element element)  
	         throws CacheException {  
		  System.out.println(element);
	      System.out.println("put");  
	   }  
	   
	   @Override  
	   public void notifyElementUpdated(Ehcache cache, Element element)  
	         throws CacheException {  
	      System.out.println("updated");  
	   }  
	   
	   @Override  
	   public void notifyElementExpired(Ehcache cache, Element element) {  
	      System.out.println("expired");  
	   }  
	   
	   @Override  
	   public void notifyElementEvicted(Ehcache cache, Element element) {  
	      System.out.println("evicted");  
	   }  
	   
	   @Override  
	   public void notifyRemoveAll(Ehcache cache) {  
	      System.out.println("removeAll");  
	   }  
	   
	   @Override  
	   public void dispose() {  
	   
	   }  
	    
	   public Object clone() throws CloneNotSupportedException {  
	      throw new CloneNotSupportedException();  
	   }  
	   
	}  