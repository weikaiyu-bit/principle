import java.util.concurrent.atomic.AtomicInteger;

public class casDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger=new AtomicInteger(5);
        System.err.println("\t cas比较 结果为:"+atomicInteger.compareAndSet(5, 60)+"值为: \t"+atomicInteger.get());
        System.err.println("\t cas比较 结果为:"+atomicInteger.compareAndSet(5, 180)+"值为: \t"+atomicInteger.get());

        atomicInteger.getAndIncrement();


    }
}
