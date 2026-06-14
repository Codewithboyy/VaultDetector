package com.spydr6307.client.vault;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class VaultWaypoint {

    private static BlockPos targetVault;

    public static void update(MinecraftClient client) {

        if (client == null || client.player == null) {
            targetVault = null;
            return;
        }

        BlockPos nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        for (BlockPos pos : VaultMemory.getVaults()) {

            double distance =
                    client.player.getBlockPos()
                            .getSquaredDistance(pos);

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = pos;
            }
        }

        targetVault = nearest;
    }

    public static BlockPos getTargetVault() {
        return targetVault;
    }

    public static boolean hasTarget() {
        return targetVault != null;
    }

    public static int getDistance(MinecraftClient client) {

        if (client == null ||
            client.player == null ||
            targetVault == null) {
            return -1;
        }

        return (int) Math.sqrt(
                client.player.getBlockPos()
                        .getSquaredDistance(targetVault)
        );
    }
}
