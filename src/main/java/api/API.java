package api;

import disruptor.event.inventory.InventoryEventProducer;
import disruptor.ringbuffer.inventory.InventoryRingBuffer;
import io.gridgo.core.GridgoContext;
import io.gridgo.core.impl.DefaultGridgoContextBuilder;
import io.gridgo.core.support.RoutingContext;

public class API {
    private static final String VERTX_URL = "vertx:http://127.0.0.1:8080/?format=string";

    private static final String APPLICATION_NAME = "application";

    private static final String GATEWAY_NAME = "myGateway";

    private static final InventoryEventProducer producer = InventoryRingBuffer.inventoryEventProducer();
    
    public static void Api() {
        var context = new DefaultGridgoContextBuilder().setName(APPLICATION_NAME).build();

        context.openGateway(GATEWAY_NAME) //
                .attachConnector(VERTX_URL) // attach a web server connector
                .subscribe(API::handleMessages); // subscribe for incoming messages

        context.start();

        Runtime.getRuntime().addShutdownHook(new Thread(context::stop));
    }

    private static void handleMessages(RoutingContext rc, GridgoContext gc) {
        producer.onData(rc);
    }
}
