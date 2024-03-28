package disruptor.event.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gridgo.bean.BElement;
import io.gridgo.core.support.RoutingContext;
import model.Inventory;

import java.util.Map;

public class InventoryEvent {
    private RoutingContext rc;
    private Inventory inventoryInstance;

    public InventoryEvent() {
    }

    public InventoryEvent(RoutingContext rc) {
        this.rc = rc;

        BElement body = rc.getMessage().body();
        String bodyString = null;
        if (body.isValue()) {
            bodyString = body.asValue().getString();
        } else if (body.isObject()) {
            bodyString = body.asObject().toString();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.inventoryInstance = objectMapper.readValue(bodyString, Inventory.class);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    public Inventory getInventoryInstance() {
        return inventoryInstance;
    }

    public RoutingContext getRc() {
        return rc;
    }
}
