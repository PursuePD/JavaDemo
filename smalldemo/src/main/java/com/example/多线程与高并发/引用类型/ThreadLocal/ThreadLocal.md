## ThreadLocal
- ThreadLocal是什么
- ThreadLocal怎么用
- ThreadLocal源码分析
- ThreadLocal内存泄漏问题

#### ThreadLocal是什么
> 从名字我们就可以看到ThreadLocal叫做线程变量，意思是ThreadLocal中填充的变量属于当前线程，该变量对其他线程而言是隔离的。
> ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。

ThreadLocal和Synchronized都是为了解决多线程中相同变量的访问冲突问题，不同的点是：
- ThreadLocal是通过每个线程单独一份存储空间，牺牲空间来解决冲突，并且相比于Synchronized，ThreadLocal具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问到想要的值。
- Synchronized是通过线程等待，牺牲时间来解决访问冲突。

```java
public class Test01_ThreadLocal {
    static  ThreadLocal<Person> tl = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(()->{
	    try {
		TimeUnit.SECONDS.sleep(2);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println(tl.get()); // null 拿不到
	}).start();
	new Thread(()->{
	    try {
		TimeUnit.SECONDS.sleep(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    tl.set(new Person());
	}).start();
    }
    static class Person{
        String name = "zhangsan";
    }
}
```
#### ThreadLocal怎么用
使用场景：
- 在进行对象跨层传递的时候，使用ThreadLocal可以避免多次传递，打破层次间的约束。
- 线程间数据隔离
- 进行事务操作，用于存储线程事务信息。
- 数据库连接，Session会话管理。


#### ThreadLocal内存泄漏问题



#### 转载/引用
[1]面试官：知道ThreadLocal嘛？谈谈你对它的理解？https://baijiahao.baidu.com/s?id=1653790035315010634&wfr=spider&for=pc