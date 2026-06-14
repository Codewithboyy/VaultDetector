package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

import java.util.List;

public class VaultSelector {

    private static int selectedIndex = 0;

    public static void next() {

        List<BlockPos> vaults = VaultMemory.getVaults();

        if (vaults.isEmpty()) {
            selectedIndex = 0;
            return;
        }

        selectedIndex++;

        if (selectedIndex >= vaults.size()) {
            selectedIndex = 0;
        }
    }

    public static void previous() {

        List<BlockPos> vaults = VaultMemory.getVaults();

        if (vaults.isEmpty()) {
            selectedIndex = 0;
            return;
        }

        selectedIndex--;

        if (selectedIndex < 0) {
            selectedIndex = vaults.size() - 1;
        }
    }

    public static BlockPos getSelectedVault() {

        List<BlockPos> vaults = VaultMemory.getVaults();

        if (vaults.isEmpty()) {
            return null;
        }

        if (selectedIndex >= vaults.size()) {
            selectedIndex = 0;
        }

        return vaults.get(selectedIndex);
    }

    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static int getTotalVaults() {
        return VaultMemory.getCount();
    }

    public static void reset() {
        selectedIndex = 0;
    }
}
