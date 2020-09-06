

import java.util.concurrent.TimeUnit;

class MyData{
     int number=0;


    public  void addT060(){
        System.out.println("改变");
        this.number=60;
    }
}
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData=new MyData();
        new Thread(()->{
            System.err.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addT060();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+"\t number  value:"+myData.number);
        },"AAA").start();

                while(myData.number==0){
                }
            System.err.println(Thread.currentThread().getName()+"\t mission is over："+myData.number);

    }
}
