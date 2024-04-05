package disruptor.event.inventory;

import com.lmax.disruptor.EventHandler;

public class ClearingEventHandler implements EventHandler<InventoryEvent> {
    @Override
    public void onEvent(InventoryEvent event, long sequence, boolean endOfBatch) {
        event.clear();
    }
}
