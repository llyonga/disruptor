package cn.llyong.disruptor.quickstart;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

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

        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderEventFactory, ringBufferSize, executor, ProducerType.SINGLE, );

    }
}
