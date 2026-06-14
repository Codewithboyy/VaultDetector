package com.spydr6307.client.vault;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class VaultSelectedDistance {

    public static int getDistance(MinecraftClient client) {

        if (client == null || client.player == null) {
            return -1;
        }

        BlockPos target = VaultSelector.getSelectedVault();

        if (target == null) {
            return -1;
        }

        return (int) Math.sqrt(
                client.player.getBlockPos()
                        .getSquaredDistance(target)
        );
    }
}
