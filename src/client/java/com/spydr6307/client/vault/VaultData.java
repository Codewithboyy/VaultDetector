package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

public class VaultData {

    private static BlockPos currentVault;

    public static void setCurrentVault(BlockPos pos) {
        currentVault = pos;
    }

    public static BlockPos getCurrentVault() {
        return currentVault;
    }

    public static boolean hasVault() {
        return currentVault != null;
    }

    public static void clear() {
        currentVault = null;
    }
}