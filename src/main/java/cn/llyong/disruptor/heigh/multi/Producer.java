package cn.llyong.disruptor.heigh.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * @description: 
 * @author: lvyong
 * @date: 2019/9/4 
 * @time: 2:21 下午
 * @version: 1.0
 */
public class Producer {
	
	private RingBuffer<Order> ringBuffer;
	
	public Producer(RingBuffer<Order> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void sendData(String uuid) {
		long sequence = ringBuffer.next();
		try {
			Order order = ringBuffer.get(sequence);
			order.setId(uuid);
		} finally {
			ringBuffer.publish(sequence);
		}
	}

}