
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    //定义初始参数，volatile关键字使用为重点
    volatile int number = 0;

    //更改初始参数
    public void changeNumber() {
        this.number = 60;
    }


    public  void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger=new AtomicInteger();
    public void  atomic(){
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 10000; j++) {
                    myData.addPlusPlus();
                    myData.atomic();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.err.println("线程执行完毕.number的值：" + myData.number);
        System.err.println("线程执行完毕.number的值atomic：" + myData.atomicInteger);
        //验证volatile的不保证原子性
//        plus(myData);


        //验证 volatile 的可见性，在 int number前面加上volatile即可保证其他线程立马接收到最新的共享变量
//        volatiles(myData);
    }

    /**
     * 验证线程的不可见性
     */
    private static void volatiles(MyData myData) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始化线程，初始参数为：" + myData.number);
            //AAA线程暂停三秒后
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //AAA线程更改初始化参数为60
            myData.changeNumber();
            System.out.println(Thread.currentThread().getName() + "线程更改初始化参数为：" + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
            //第二个线程是main线程，在number=0时则一直陷入死循环，当number=60时则打印主线程语句。
        }
        System.out.println(Thread.currentThread().getName() + "主线程当前参数：" + myData.number);
    }

    private static void plus(MyData myData) {

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.err.println("线程执行完毕.number的值number：" + myData.number);
        System.err.println("线程执行完毕.number的值atomic：" + myData.atomicInteger);
    }
}
