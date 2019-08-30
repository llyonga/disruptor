package cn.llyong.disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-08-30
 * @time: 4:46 下午
 * @version: 1.0
 */
public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer byteBuffer) {
        //1、在生产者发送消息的时候，首先需要充我们的ringBuffer中获取导一个可用的序号
        long seq = ringBuffer.next();
        try {
            //2、根据这个序号，找到具体的"OrderEvent"元素，注意：此时获取的OrderEvent对象是一个没有赋值属性值的空对象
            OrderEvent orderEvent = ringBuffer.get(seq);
            //3、进行实际的赋值处理
            orderEvent.setOrderId(byteBuffer.getLong(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4、提交发布操作
            ringBuffer.publish(seq);
        }

    }
}
