package cn.llyong.disruptor.heigh.multi;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-09-04
 * @time: 10:36 上午
 * @version: 1.0
 */
@Data
public class Order {

    private String id;
    private String name;
    private double price;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"price\":")
                .append(price);
        sb.append('}');
        return sb.toString();
    }
}
