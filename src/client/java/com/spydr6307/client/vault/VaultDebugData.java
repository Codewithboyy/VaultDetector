package com.spydr6307.client.vault;

public class VaultDebugData {

    private static int scanCount = 0;
    private static String lastBlockEntity = "NONE";

    public static void incrementScans() {
        scanCount++;
    }

    public static int getScanCount() {
        return scanCount;
    }

    public static void setLastBlockEntity(String value) {
        lastBlockEntity = value;
    }

    public static String getLastBlockEntity() {
        return lastBlockEntity;
    }

    public static void reset() {
        scanCount = 0;
        lastBlockEntity = "NONE";
    }
}
