package com.spydr6307.client.vault;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class VaultDirection {

    public static String getDirection(MinecraftClient client) {

        if (client == null ||
            client.player == null ||
            !VaultWaypoint.hasTarget()) {
            return "NONE";
        }

        BlockPos target = VaultWaypoint.getTargetVault();

        double dx = target.getX() + 0.5 - client.player.getX();
        double dz = target.getZ() + 0.5 - client.player.getZ();

        double targetYaw = Math.toDegrees(Math.atan2(-dx, dz));
        double playerYaw = client.player.getYaw();

        double diff = targetYaw - playerYaw;

        while (diff > 180) {
            diff -= 360;
        }

        while (diff < -180) {
            diff += 360;
        }

        if (diff > -45 && diff < 45) {
            return "↑ FRONT";
        }

        if (diff >= 45 && diff <= 135) {
            return "→ RIGHT";
        }

        if (diff <= -45 && diff >= -135) {
            return "← LEFT";
        }

        return "↓ BEHIND";
    }
}
