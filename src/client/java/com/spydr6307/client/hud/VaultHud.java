package com.spydr6307.client.hud;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultDebugData;
import com.spydr6307.client.vault.VaultDistance;
import com.spydr6307.client.vault.VaultMemory;
import com.spydr6307.client.vault.VaultState;
import com.spydr6307.client.vault.VaultWaypoint;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class VaultHud {

    public static void register() {

        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            drawContext.drawText(
                    client.textRenderer,
                    "VaultDetector: " + (ModState.isEnabled() ? "ON" : "OFF"),
                    10,
                    10,
                    0x00FF00,
                    true
            );

            drawContext.drawText(
                    client.textRenderer,
                    "Tracked Vaults: " + VaultMemory.getCount(),
                    10,
                    25,
                    0xFFAA00,
                    true
            );

            if (VaultWaypoint.hasTarget()) {

                BlockPos target = VaultWaypoint.getTargetVault();

                drawContext.drawText(
                        client.textRenderer,
                        "Target Vault",
                        10,
                        45,
                        0x00FFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "X: " + target.getX(),
                        10,
                        60,
                        0xFFFFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "Y: " + target.getY(),
                        10,
                        75,
                        0xFFFFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "Z: " + target.getZ(),
                        10,
                        90,
                        0xFFFFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "Distance: " + VaultWaypoint.getDistance(client) + " blocks",
                        10,
                        105,
                        0x55FF55,
                        true
                );
            }

            if (VaultState.isActive()) {

                BlockPos pos = VaultState.getPosition();

                drawContext.drawText(
                        client.textRenderer,
                        "Current Vault",
                        10,
                        130,
                        0xFFFF00,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        pos.getX() + " " +
                        pos.getY() + " " +
                        pos.getZ(),
                        10,
                        145,
                        0xAAAAAA,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "State: " + VaultState.getDebugInfo(),
                        10,
                        160,
                        0x55FFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "Entity: " + VaultDebugData.getLastBlockEntity(),
                        10,
                        175,
                        0xFFFFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "Scans: " + VaultDebugData.getScanCount(),
                        10,
                        190,
                        0x55FF55,
                        true
                );
                
                drawContext.drawText(
                        client.textRenderer,
                        "Direction: " +
                        com.spydr6307.client.vault.VaultDirection.getDirection(client),
                        10,
                        120,
                        0xFF55FF,
                        true
                );
            }
        });
    }
}