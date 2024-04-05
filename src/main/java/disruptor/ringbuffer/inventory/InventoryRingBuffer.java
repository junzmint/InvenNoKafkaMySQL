package disruptor.ringbuffer.inventory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import disruptor.event.inventory.*;

public class InventoryRingBuffer {
    public static InventoryEventProducer inventoryEventProducer() {
        InventoryEventFactory factory = new InventoryEventFactory();

        int bufferSize = 2048;
        Disruptor<InventoryEvent> disruptor =
                new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(new InventoryEventHandler()).then(new ClearingEventHandler());
        disruptor.start();

        RingBuffer<InventoryEvent> ringBuffer = disruptor.getRingBuffer();
        InventoryEventProducer producer = new InventoryEventProducer(ringBuffer);

        return producer;
    }
}
