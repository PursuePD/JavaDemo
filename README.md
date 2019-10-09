# JavaDemo
我的java演示代码 用于练习和记一些小demo

## 其中包含
### [1. LearnData 学习资料](https://github.com/PursuePD/JavaDemo/tree/master/LearnData)
## 项目
### [1. mail](https://github.com/PursuePD/JavaDemo/tree/master/mail)
> * 邮件的发送,附带文件的邮件发送
> * Zip文件压缩并加密
> * 定时任务  Scheduled
>  * 定时任务  Scheduled
> * 事件监听  
>  * 事件监听需要  `触发器`->`事件源`->`监听器`  
>    * 触发器【触发消息】EventServiceImpl 需要实现ApplicationContextAware 
>    * 事件源【传递消息】MessageEvent 需要继承ApplicationEvent 
>    * 监听器【处理事件】MessageEventListener 需要实现ApplicationListener<`事件源`> 
>    * 异步 需要监听器方法加@Async 主类@EnableAsync

### [2. mongodb](https://github.com/PursuePD/JavaDemo/tree/master/mongodb)
> * mongodb的使用示例
### [3. smalldemo](https://github.com/PursuePD/JavaDemo/tree/master/smalldemo)
> * 很多小的用例目前包括：
>     * [设计模式](/smalldemo/src/main/java/com/example/设计模式/designPattern.md)
>     * 线程
>       * 另起线程实现异步操作 Thread\AsynchronizationThread.java
>     * 压缩文件，加密压缩文件 ZipFile\ZipUtil.java
>     * Java8特性 java8.Lambda\Lambda.java
>     * 排序 EasySort
>     * 时间轮
>     * Java特性 Stream
### [4. delayedTask](https://github.com/PursuePD/JavaDemo/tree/master/delayedTask)
> * 秒级定时任务讨论分析
>     * 时间轮
>     * Redis方案1：有序集合实现
>     * Redis方案2：键空间通知
>     * RabbitMQ

### [5. SpringStatemachineDemo](https://github.com/PursuePD/JavaDemo/tree/master/SpringStatemachineDemo)
> * SpringStateMachine Demo
>     * 状态机demo
>     * [参考文档](https://my.oschina.net/u/173343/blog/3049036)


