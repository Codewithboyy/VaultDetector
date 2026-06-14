package com.spydr6307.client.event;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultMemory;
import com.spydr6307.client.vault.VaultReader;
import com.spydr6307.client.vault.VaultState;
import com.spydr6307.client.vault.VaultWaypoint;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class ClientEvents {

    public static void register() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            VaultWaypoint.update(client);

            if (client.player == null || client.world == null) {
                VaultState.reset();
                return;
            }

            if (!ModState.isEnabled()) {
                VaultState.reset();
                return;
            }

            if (!(client.crosshairTarget instanceof BlockHitResult hit)) {
                VaultState.reset();
                return;
            }

            BlockPos pos = hit.getBlockPos();

            if (!client.world.getBlockState(pos).isOf(Blocks.VAULT)) {
                VaultState.reset();
                return;
            }

            VaultMemory.add(pos);

            VaultState.setActive(true);
            VaultState.setPosition(pos);

            String state =
                    VaultReader.readVaultState(
                            client.world,
                            pos
                    );

            VaultState.setDebugInfo(state);
        });
    }
}