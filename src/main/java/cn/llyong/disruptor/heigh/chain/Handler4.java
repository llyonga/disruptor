package cn.llyong.disruptor.heigh.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @description:
 * @author: llyong
 * @date: 2019/9/3
 * @time: 21:03
 * @version: 1.0
 */
public class Handler4 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.err.println("handler 4 : SET PRICE");
        Thread.sleep(1000);
        event.setPrice(17.0);
    }

}

