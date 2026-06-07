package com.spydr6307.client.hud;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultController;
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

            if (VaultController.hasVault()) {

                BlockPos pos = VaultController.getTrackedVault();

                drawContext.drawText(
                        client.textRenderer,
                        "Vault @ " + pos.getX() + " " + pos.getY() + " " + pos.getZ(),
                        10,
                        25,
                        0xFFFFFF,
                        true
                );
            }
        });
    }
}