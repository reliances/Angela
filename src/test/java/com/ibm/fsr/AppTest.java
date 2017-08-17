package com.ibm.fsr;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Title:AppTest     
 * @Description:服务排程
 * @Auth:LiangRui   
 * @CreateTime:2017年3月10日 上午10:17:08       
 * @version V1.0
 */
public class AppTest {
	
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void main(String[] args)throws InterruptedException, ExecutionException {
    //1 创建一个具有排程功能的线程池
    ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
    //2 创建一个任务
    Runnable task1 = new Runnable() {
      public void run() {
        System.out.println("执行一次任务");
      }
    };
    //3 延时5秒后每秒执行一次task1;并返回一个Future 对象(通过Future对象可了解任务执行情况)
    final ScheduledFuture future1 = service.scheduleAtFixedRate(task1, 5, 1, TimeUnit.SECONDS);
    //4 生成一个可执行任务(该任务执行完毕可以返回结果 或者 抛出异常；而Runnable接口的run方法则不行)
    Callable task2 = new Callable() {
        public String call() {
        	future1.cancel(true);
        	return "任务取消!";
        }
    };
    //5 延时10秒后执行task2;并返回一个Future 对象(通过Future对象可了解任务执行情况)
    ScheduledFuture future2 = service.schedule(task2, 10, TimeUnit.SECONDS);
    //6 打印task2执行的结果
    System.out.println(future2.get());
    //7 关闭线程池
    service.shutdown();
  }
  
  
}