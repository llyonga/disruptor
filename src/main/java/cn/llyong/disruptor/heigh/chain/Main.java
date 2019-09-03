package cn.llyong.disruptor.heigh.chain;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: llyong
 * @date: 2019/8/31
 * @time: 21:34
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //构建一个线程池，用于提交任务
        ExecutorService executor1 = Executors.newFixedThreadPool(1);
        ExecutorService executor2 = Executors.newFixedThreadPool(5);

        //1、构建Disruptor
        Disruptor<Trade> disruptor = new Disruptor<Trade>(
                new EventFactory<Trade>() {
                    @Override
                    public Trade newInstance() {
                        return new Trade();
                    }
                },
                1024 * 1024,
                executor2,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
        );

        //2、把消费者设置到Disruptor中

        //2.1 串行操作：
        /**
         disruptor
         .handleEventsWith(new Handler1())
         .handleEventsWith(new Handler2())
         .handleEventsWith(new Handler3());
         */


        //2.2 并行操作: 可以有两种方式去进行
        //1 handleEventsWith方法 添加多个handler实现即可
        //2 handleEventsWith方法 分别进行调用
        /**
         disruptor.handleEventsWith(new Handler1(), new Handler2(), new Handler3());
         //		disruptor.handleEventsWith(new Handler2());
         //		disruptor.handleEventsWith(new Handler3());
         */


        //2.3 菱形操作 (一)
        /**
         disruptor.handleEventsWith(new Handler1(), new Handler2())
         .handleEventsWith(new Handler3());
         */

        //2.3 菱形操作 (二)
        /**
         EventHandlerGroup<Trade> ehGroup = disruptor.handleEventsWith(new Handler1(), new Handler2());
         ehGroup.then(new Handler3());
         */

        //2.4 六边形操作
        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();
        disruptor.handleEventsWith(h1, h4);
        disruptor.after(h1).handleEventsWith(h2);
        disruptor.after(h4).handleEventsWith(h5);
        disruptor.after(h2, h5).handleEventsWith(h3);


        //3、启动disruptor
        RingBuffer<Trade> ringBuffer = disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);

        executor1.submit(new TradePublish(latch, disruptor));

        latch.await();

        disruptor.shutdown();
        executor1.shutdown();
        executor2.shutdown();

    }
}
