package com.example.多线程与高并发.join;

/**
 * @author: 小崔
 * @create: 2020-07-10 17:10
 * @Description:
 */
public class ThreadJoinTest1 {
    public static void main(String [] args) throws InterruptedException {
	ThreadJoinTest t1 = new ThreadJoinTest("小明");
	ThreadJoinTest t2 = new ThreadJoinTest("小东");
	t1.start();
	/**join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
	 * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
	 */
	t1.join();
	t2.start();
    }

}
class ThreadJoinTest extends Thread{
    public ThreadJoinTest(String name){
	super(name);
    }
    @Override
    public void run(){
	for(int i=0;i<1000;i++){
	    System.out.println(this.getName() + ":" + i);
	}
    }
}
