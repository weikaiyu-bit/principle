public class SingletonDemo {

    private static volatile SingletonDemo  instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "我是构造方法");
    }


    private static  SingletonDemo singletonDemo() {
        if (null == instance) {
            //双端检锁机制
            synchronized (SingletonDemo.class) {
                if (null == instance) {
                    /**
                     * 当多线程 new SingletonDemo();对象时底层做了三步操作，
                     * memory=allocate（）；//1.分配对象内存空间
                     * instance（memory）；//2.初始化对象
                     * instance=memory；/3.设置instance指向刚分配的内存地址，此时instance！=nul
                     * 但是此时由于指令重排 步骤1 2 3 变成了1 3 2  导致instance！=nul 却没有这个对象，就会导致错误
                     */
                    instance = new SingletonDemo();
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
//        System.out.println(SingletonDemo.singletonDemo()==SingletonDemo.singletonDemo());
//        System.out.println(SingletonDemo.singletonDemo()==SingletonDemo.singletonDemo());
//        System.out.println(SingletonDemo.singletonDemo()==SingletonDemo.singletonDemo());

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SingletonDemo.singletonDemo();
            }, String.valueOf(i)).start();
        }
    }
}
