package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

public class VaultTarget {

    private static BlockPos position;

    public static void set(BlockPos pos) {
        position = pos;
    }

    public static BlockPos get() {
        return position;
    }

    public static boolean exists() {
        return position != null;
    }

    public static void clear() {
        position = null;
    }
}
