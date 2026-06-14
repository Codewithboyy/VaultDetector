package com.spydr6307.client.vault;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class VaultDistance {

    public static BlockPos getNearestVault(MinecraftClient client) {

        if (client.player == null) {
            return null;
        }

        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        for (BlockPos pos : VaultMemory.getVaults()) {

            double distance = client.player.getBlockPos().getSquaredDistance(pos);

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = pos;
            }
        }

        return nearest;
    }

    public static int getNearestDistance(MinecraftClient client) {

        BlockPos nearest = getNearestVault(client);

        if (nearest == null || client.player == null) {
            return -1;
        }

        return (int) Math.sqrt(
                client.player.getBlockPos().getSquaredDistance(nearest)
        );
    }
}
