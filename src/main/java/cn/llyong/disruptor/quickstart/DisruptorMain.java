package cn.llyong.disruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: llyong
 * @date: 2019/8/30
 * @time: 16:22
 * @version: 1.0
 */
public class DisruptorMain {

    public static void main(String[] args) {

        //1、实例化disruptor对象
        OrderEventFactory orderEventFactory = new OrderEventFactory();

        int ringBufferSize = 1024 * 1024;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //新版本中丢弃了该构造方法
//        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderEventFactory, ringBufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(
                orderEventFactory,
                ringBufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        //2、添加消费者的监听
        disruptor.handleEventsWith(new OrderEventHandler());

        //3、启动disruptor
        disruptor.start();

        //4、获取实际存储数据的容器：RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (int i = 1; i <= 100; i++) {
            byteBuffer.putLong(0, i);
            producer.sendData(byteBuffer);
        }

        disruptor.shutdown();
        executor.shutdown();



    }
}
