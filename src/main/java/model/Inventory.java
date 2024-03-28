package model;

import java.util.Map;

public class Inventory {
    private String type;
    private String orderUuid;
    private Map<String, Integer> skuList;

    // Constructors, getters, and setters

    public Inventory() {
    }

    public Inventory(String type, String orderUuid, Map<String, Integer> skuList) {
        this.type = type;
        this.orderUuid = orderUuid;
        this.skuList = skuList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public Map<String, Integer> getSkuList() {
        return skuList;
    }

    public void setSkuList(Map<String, Integer> skuList) {
        this.skuList = skuList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "type='" + type + '\'' +
                ", orderUuid='" + orderUuid + '\'' +
                ", skuList=" + skuList +
                '}';
    }
}

