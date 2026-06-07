package com.spydr6307.client.event;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultData;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;

public class ClientEvents {

    private static long lastMessage = 0;

    public static void register() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null || client.world == null) {
                VaultData.clear();
                return;
            }

            if (!ModState.isEnabled()) {
                VaultData.clear();
                return;
            }

            if (!(client.crosshairTarget instanceof BlockHitResult hit)) {
                VaultData.clear();
                return;
            }

            if (client.world.getBlockState(hit.getBlockPos()).isOf(Blocks.VAULT)) {

                VaultData.setCurrentVault(hit.getBlockPos());

                long now = System.currentTimeMillis();

                if (now - lastMessage > 1000) {

                    client.player.sendMessage(
                            Text.literal(
                                    "Vault Detected: "
                                            + hit.getBlockPos().getX()
                                            + ", "
                                            + hit.getBlockPos().getY()
                                            + ", "
                                            + hit.getBlockPos().getZ()
                            ),
                            true
                    );

                    lastMessage = now;
                }

            } else {
                VaultData.clear();
            }
        });
    }
}