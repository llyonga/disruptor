**disruptor的主要编程部件** 

1. Disruptor:用于控制整个消费者-生产者模型的处理器 
2. RingBuffer:用于存放数据 
3. EventHandler:一个用于处理事件的接口（可以当做生产者，也可以当做消费者）。 
4. EventFactory:事件工厂类。 
5. WaitStrategy:用于实现事件处理等待RingBuffer游标策略的接口。
6. SequeueBarrier:队列屏障，用于处理访问RingBuffer的序列。 
7. 用于运行disruptor的线程或者线程池。

**disruptor编程主要的编程流程** 

1. 定义事件 
2. 定义事件工厂 
3. 定义事件处理类 
4. 定义事件处理的线程或者线程池 
5. 指定等待策略 
6. 通过disruptor处理器组装生产者和消费者 
7. 发布事件 
8. 关闭disruptor业务逻辑处理器 