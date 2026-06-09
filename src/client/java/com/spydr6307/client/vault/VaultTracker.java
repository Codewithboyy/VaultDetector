package com.spydr6307.client.vault;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class VaultTracker {

    public static void tick(MinecraftClient client) {

        if (client.player == null || client.world == null) {
            VaultTarget.clear();
            return;
        }

        if (!(client.crosshairTarget instanceof BlockHitResult hit)) {
            VaultTarget.clear();
            return;
        }

        BlockPos pos = hit.getBlockPos();

        if (!client.world.getBlockState(pos).isOf(Blocks.VAULT)) {
            VaultTarget.clear();
            return;
        }

        VaultTarget.set(pos);
    }
}
