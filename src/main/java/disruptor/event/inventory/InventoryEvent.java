package disruptor.event.inventory;

import io.gridgo.core.support.RoutingContext;

public class InventoryEvent {
    private RoutingContext rc;

    public InventoryEvent() {
    }

    public InventoryEvent(RoutingContext rc) {
        this.rc = rc;
    }

    public RoutingContext getRc() {
        return rc;
    }

    public void setRc(RoutingContext rc) {
        this.rc = rc;
    }

    void clear() {
        rc = null;
    }
}
