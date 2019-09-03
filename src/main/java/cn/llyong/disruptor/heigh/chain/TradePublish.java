package cn.llyong.disruptor.heigh.chain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: llyong
 * @date: 2019/8/31
 * @time: 21:34
 * @version: 1.0
 */
public class TradePublish implements Runnable {

    private Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    public TradePublish(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.latch = latch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        TradeEventTranslator eventTranslator = new TradeEventTranslator();
        //disruptor的提交任务的方式
//        disruptor.publishEvent(eventTranslator);


        //多次提交
        for (int i = 0; i < 10; i++) {
            disruptor.publishEvent(eventTranslator);
        }
        latch.countDown();
    }
}


class TradeEventTranslator implements EventTranslator<Trade> {

    private Random random = new Random();

    @Override
    public void translateTo(Trade event, long sequence) {
        this.generateTrade(event);
    }

    private void generateTrade(Trade event) {
        event.setPrice(random.nextDouble() * 999);
    }
}