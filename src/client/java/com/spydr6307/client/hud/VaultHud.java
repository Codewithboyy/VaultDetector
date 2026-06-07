package com.spydr6307.client.hud;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultState;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

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

            if (VaultState.isActive()) {

                var pos = VaultState.getPosition();

                drawContext.drawText(
                        client.textRenderer,
                        "Vault Active",
                        10,
                        25,
                        0xFFFFFF,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "Pos: " + pos.getX() + " " + pos.getY() + " " + pos.getZ(),
                        10,
                        40,
                        0xAAAAAA,
                        true
                );

                drawContext.drawText(
                        client.textRenderer,
                        "State: " + VaultState.getDebugInfo(),
                        10,
                        55,
                        0xFFFF00,
                        true
                );
            }
        });
    }
}