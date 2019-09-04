package cn.llyong.disruptor.heigh.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-09-04
 * @time: 2:17 下午
 * @version: 1.0
 */
public class Consumer implements WorkHandler<Order> {

    private String consumerId;

    private static AtomicInteger count = new AtomicInteger(0);

    private Random random = new Random();

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(Order event) throws Exception {
        Thread.sleep(1 * random.nextInt(5));
        System.err.println("当前消费者: " + this.consumerId + ", 消费信息ID: " + event.getId());
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }
}
