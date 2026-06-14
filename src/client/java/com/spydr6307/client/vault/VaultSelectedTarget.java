package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

public class VaultSelectedTarget {

    public static BlockPos get() {
        return VaultSelector.getSelectedVault();
    }

    public static boolean exists() {
        return get() != null;
    }
}
