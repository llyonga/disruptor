package cn.llyong.disruptor.quickstart;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: llyong
 * @date: 2019/8/30
 * @time: 16:17
 * @version: 1.0
 */
@Data
@ToString
public class OrderEvent {

    private String orderId;
    private String name;
    private Integer num;

}
