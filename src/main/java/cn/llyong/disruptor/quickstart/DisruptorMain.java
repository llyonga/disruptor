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
        /**
         * 通过RingBuffer方式发布：
         * 1、创建一个数据传输对象（Event类）
         * 2、创建一个事件工厂（实现EventFactory类）
         * 3、创建一个事件处理类/消费者（实现EventHandler类）
         * 4、创建一个事件的生产者（构造发方法传入一个RingBuffer，通过RingBuffer的publish方法发布对象）
         * 5、实例化disruptor对象，配置对应的参数
         *          disruptor对象会返回一个RingBuffer对象（也就是实际存储数据的容器对象），将获取到的RingBuffer对象传入生产者的构造器
         */

        //1、实例化disruptor对象
        OrderEventFactory orderEventFactory = new OrderEventFactory();

        int ringBufferSize = 1024 * 1024;

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



    }
}
