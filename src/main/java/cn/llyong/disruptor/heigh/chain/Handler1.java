package cn.llyong.disruptor.heigh.chain;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @description:
 * @author: llyong
 * @date: 2019/9/3
 * @time: 21:01
 * @version: 1.0
 */
public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {

    /**
     * EventHandler
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    /**
     * WorkHandler
     * @param event
     * @throws Exception
     */
    @Override
    public void onEvent(Trade event) throws Exception {
        System.err.println("handler 1 : SET NAME");
        event.setName("H1");
        Thread.sleep(1000);
    }
}
