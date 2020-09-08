public class SingletonDemoTow {

    private  static SingletonDemoTow instance=null;

    private  SingletonDemoTow(){
        System.out.println(Thread.currentThread().getName()+"\t"+"构造方法");
    }
    private static synchronized SingletonDemoTow singletonDemoTow(){
        if(null==instance){
            System.out.println("?????");
            return new SingletonDemoTow();
        }
        return null;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                SingletonDemoTow.singletonDemoTow();
            },String.valueOf(i)).start();
        }
    }
}
