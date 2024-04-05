package disruptor.event.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmax.disruptor.EventHandler;
import io.gridgo.bean.BElement;
import io.gridgo.framework.support.Message;
import model.Inventory;
import service.Main;

public class InventoryEventHandler implements EventHandler<InventoryEvent> {
    @Override
    public void onEvent(InventoryEvent event, long sequence, boolean endOfBatch) {
        var msg = event.getRc().getMessage();
        var deferred = event.getRc().getDeferred();

        BElement body = msg.body();
        String bodyString = null;
        if (body.isValue()) {
            bodyString = body.asValue().getString();
        } else if (body.isObject()) {
            bodyString = body.asObject().toString();
        }

        if (bodyString != null && bodyString.startsWith("\uFEFF")) {
            bodyString = bodyString.substring(1);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Inventory inventory = objectMapper.readValue(bodyString, Inventory.class);
            // using the same request as response
            deferred.resolve(Message.ofAny(Main.handler.handleTransaction(inventory)));
            System.out.println(inventory);
        } catch (Exception e) {
            e.printStackTrace();
            // using the same request as response
            deferred.resolve(Message.ofAny("Json serializer error"));
        }
    }
}
