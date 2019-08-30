package cn.llyong.disruptor.quickstart;

import com.lmax.disruptor.EventHandler;

/**
 * @description:
 * @author: llyong
 * @date: 2019/8/30
 * @time: 16:19
 * @version: 1.0
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        System.out.println(String.format("消费者接收数据=> %s", orderEvent.toString()));
        System.out.println(l);
        System.out.println(b);
    }
}
