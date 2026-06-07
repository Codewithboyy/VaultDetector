package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

public class VaultController {

    private static BlockPos trackedVault;
    private static boolean isVaultLookedAt;

    public static void tick(boolean lookingAtVault, BlockPos pos) {

        isVaultLookedAt = lookingAtVault;

        if (lookingAtVault && pos != null) {
            trackedVault = pos;
        } else if (!lookingAtVault) {
            trackedVault = null;
        }
    }

    public static boolean isLookingAtVault() {
        return isVaultLookedAt;
    }

    public static BlockPos getTrackedVault() {
        return trackedVault;
    }

    public static boolean hasVault() {
        return trackedVault != null;
    }
}
