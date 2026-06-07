package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

public class VaultState {

    private static boolean active = false;
    private static BlockPos position = null;

    private static String debugInfo = "NONE";

    public static void setActive(boolean value) {
        active = value;
    }

    public static boolean isActive() {
        return active;
    }

    public static void setPosition(BlockPos pos) {
        position = pos;
    }

    public static BlockPos getPosition() {
        return position;
    }

    public static void setDebugInfo(String info) {
        debugInfo = info;
    }

    public static String getDebugInfo() {
        return debugInfo;
    }

    public static void reset() {
        active = false;
        position = null;
        debugInfo = "NONE";
    }
}