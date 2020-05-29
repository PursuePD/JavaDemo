## 引用类型
- 强引用(StrongReference)与垃圾回收
- 软引用(SoftReference)与缓存
- 弱引用(WeakReference)与ThreadLocal
- 虚引用(PhantomReference)与直接内存管理


#### 强引用(StrongReference)与垃圾回收
强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。例如
```java
Object strongReference = new Object();
```
当内存空间不足时，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。
如果强引用对象不使用时，需要弱化从而使GC能够回收，如下：
```java
strongReference = null;
```
#### 软引用(SoftReference)与缓存
- 如果一个对象只具有软引用，则内存空间充足时，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。
- 软引用可用来实现内存敏感的高速缓存。

```java
public class Test01_SoftReference {
    //启动时配置-Xmx20M
    public static void main(String[] args) {
	SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
	System.out.println(m.get()); //[B@deb6432
	System.gc();
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(m.get()); //[B@deb6432
	byte[] b = new byte[1024*1024*11];
	System.out.println(m.get()); //null
    }
}
```
应用场景：
> 浏览器的后退按钮。按后退时，这个后退时显示的网页内容是重新进行请求还是从缓存中取出呢？这就要看具体的实现策略了。
> - 如果一个网页在浏览结束时就进行内容的回收，则按后退查看前面浏览过的页面时，需要重新构建；
> - 如果将浏览过的网页存储到内存中会造成内存的大量浪费，甚至会造成内存溢出。
```
    // 获取浏览器对象进行浏览
    Browser browser = new Browser();
    // 从后台程序加载浏览页面
    BrowserPage page = browser.getPage();
    // 将浏览完毕的页面置为软引用
    SoftReference softReference = new SoftReference(page);
    // 回退或者再次浏览此页面时
    if(softReference.get() != null) {
        // 内存充足，还没有被回收器回收，直接获取缓存
        page = softReference.get();
    } else {
        // 内存不足，软引用的对象已经回收
        page = browser.getPage();
        // 重新构建软引用
        softReference = new SoftReference(page);
    }
```

#### 弱引用(WeakReference)与ThreadLocal
- 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。
- 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
- 由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
- **JVM首先将软引用中的对象引用置为null，然后通知垃圾回收器进行回收**
```java
public class Test01_WeakReference {
    public static void main(String[] args) {
	WeakReference<M> m = new WeakReference<>(new M());
	System.out.println(m.get()); //M@deb6432
    //M strong = m.get();//加入这个会让弱引用变成强引用
	System.gc(); // m.finalize();
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(m.get()); //null
    }
}
```
#### 虚引用(PhantomReference)与直接内存管理
- 虚引用顾名思义，就是形同虚设。与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
- 应用场景
  - **虚引用**主要用来**跟踪对象**被垃圾回收器**回收**的活动。
  - 实现对**直接内存**的管理。（通过引用队列实现）
- 虚引用必须和引用队列(ReferenceQueue)联合使用。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。

程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要进行垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。

#### 总结
Java中4种引用的级别和强度由高到低依次为：强引用 > 软引用 > 弱引用 > 虚引用


#### 引用
[1]Java基础篇 - 强引用、弱引用、软引用和虚引用 https://blog.csdn.net/baidu_22254181/article/details/82555485