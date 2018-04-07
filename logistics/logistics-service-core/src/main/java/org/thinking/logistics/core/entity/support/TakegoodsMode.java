package org.thinking.logistics.core.entity.support;

public enum TakegoodsMode {
    NONE("0", 0),
    SELF_SERVICE("1", 1),
    SELF_SERVICE_INVENTORYUP("2", 2),
    CONSIGNMENT("3", 3),
    INNER_CITY_DELIVERY("4", 4),
    OUTER_CITY_DELIVERY("5", 5),
    FLITTING("6", 6),
    GREEN_CHANNEL("7", 7),
    SELF_SERVICE_2_DELIVERY("8", 8),
    RETAIL_CHAINS("9", 9);

    TakegoodsMode(String name, int ordinal) {
    }
}