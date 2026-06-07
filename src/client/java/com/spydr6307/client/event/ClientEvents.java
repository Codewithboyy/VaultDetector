package com.spydr6307.client.event;

import com.spydr6307.client.vault.VaultTracker;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.util.hit.BlockHitResult;

public class ClientEvents {

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null || client.world == null) {
                return;
            }

            VaultTracker.setLookingAtVault(false);

            if (!(client.crosshairTarget instanceof BlockHitResult hit)) {
                return;
            }

            if (client.world.getBlockState(hit.getBlockPos()).isOf(Blocks.VAULT)) {
                VaultTracker.setLookingAtVault(true);
            }
        });
    }
}