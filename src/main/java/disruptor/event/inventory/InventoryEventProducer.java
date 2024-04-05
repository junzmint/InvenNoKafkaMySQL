package disruptor.event.inventory;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import io.gridgo.core.support.RoutingContext;

public class InventoryEventProducer {
    private static final EventTranslatorOneArg<InventoryEvent, RoutingContext> TRANSLATOR =
            new EventTranslatorOneArg<InventoryEvent, RoutingContext>() {
                @Override
                public void translateTo(InventoryEvent event, long sequence, RoutingContext rc) {
                    event.setRc(rc);
                }
            };
    private final RingBuffer<InventoryEvent> ringBuffer;

    public InventoryEventProducer(RingBuffer<InventoryEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(RoutingContext rc) {
        ringBuffer.publishEvent(TRANSLATOR, rc);
    }
}
