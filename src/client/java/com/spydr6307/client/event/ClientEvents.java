package com.spydr6307.client.event;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultController;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class ClientEvents {

    public static void register() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null || client.world == null) {
                VaultController.tick(false, null);
                return;
            }

            if (!ModState.isEnabled()) {
                VaultController.tick(false, null);
                return;
            }

            boolean lookingAtVault = false;
            BlockPos pos = null;

            if (client.crosshairTarget instanceof BlockHitResult hit) {

                pos = hit.getBlockPos();

                if (client.world.getBlockState(pos).isOf(Blocks.VAULT)) {
                    lookingAtVault = true;
                }
            }

            VaultController.tick(lookingAtVault, pos);
        });
    }
}