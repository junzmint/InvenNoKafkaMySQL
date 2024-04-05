package transactionshandler;

import cache.LocalCache;
import model.Inventory;

import java.util.Map;

public class TransactionsHandling {
    private LocalCache cache;

    public TransactionsHandling() {
    }

    public TransactionsHandling(LocalCache cache) {
        this.cache = cache;
    }

    public void setCache(LocalCache cache) {
        this.cache = cache;
    }

    public String handleTransaction(Inventory inventory) {
        for (Map.Entry<String, Integer> entry : inventory.getSkuList().entrySet()) {
            String skuId = entry.getKey();
            Integer quantity = entry.getValue();
            int stockQuantity = this.cache.get(skuId);
            if (stockQuantity == LocalCache.NEW_SKU) {
                return "NO_SKU: " + skuId;
            }
            if (stockQuantity + quantity < 0) {
                return "OUT_OF_QUANTITY";
            }
            this.cache.update(skuId, stockQuantity + quantity);
        }
        return "RESERVED";
    }
}
