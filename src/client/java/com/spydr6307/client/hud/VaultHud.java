package com.spydr6307.client.hud;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultData;
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

            String status = ModState.isEnabled() ? "ON" : "OFF";

            drawContext.drawText(
                    client.textRenderer,
                    "VaultDetector: " + status,
                    10,
                    10,
                    0x00FF00,
                    true
            );

            if (VaultData.hasVault()) {

                BlockPos pos = VaultData.getCurrentVault();

                drawContext.drawText(
                        client.textRenderer,
                        "Vault: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ(),
                        10,
                        25,
                        0xFFFFFF,
                        true
                );
            }
        });
    }
}
