package disruptor.event.inventory;

import com.lmax.disruptor.EventHandler;

public class InventoryEventHandler implements EventHandler<InventoryEvent> {
    @Override
    public void onEvent(InventoryEvent event, long sequence, boolean endOfBatch)
    {
    }
}
