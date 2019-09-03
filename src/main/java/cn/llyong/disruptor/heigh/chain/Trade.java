package cn.llyong.disruptor.heigh.chain;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:  相当一个Event类
 * @author: llyong
 * @date: 2019/8/31
 * @time: 21:34
 * @version: 1.0
 */

@Data
@ToString
public class Trade {

    private String id;
    private String name;
    private double price;
    private AtomicInteger count = new AtomicInteger(0);
}
