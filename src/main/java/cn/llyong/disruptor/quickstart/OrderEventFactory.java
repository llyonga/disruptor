package cn.llyong.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @description:
 * @author: llyong
 * @date: 2019/8/30
 * @time: 16:23
 * @version: 1.0
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        //返回一个event实例对象
        return new OrderEvent();
    }
}
