package com.spydr6307.client.hud;

import com.spydr6307.client.vault.VaultTracker;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class VaultHud {

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {

            if (!VaultTracker.isLookingAtVault()) {
                return;
            }

            drawContext.drawText(
                    drawContext.getMatrices(),
                    net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                    "VAULT DETECTED",
                    10,
                    10,
                    0x00FF00,
                    true
            );
        });
    }
}
