package cn.llyong.disruptor.heigh.chain;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * @description:
 * @author: llyong
 * @date: 2019/9/3
 * @time: 21:03
 * @version: 1.0
 */
public class Handler2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.err.println("handler 2 : SET ID");
        event.setId(UUID.randomUUID().toString());
//        Thread.sleep(2000);
    }

}