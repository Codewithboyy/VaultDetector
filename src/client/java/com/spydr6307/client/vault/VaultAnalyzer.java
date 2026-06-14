package com.spydr6307.client.vault;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VaultAnalyzer {

    public static VaultScanResult scan(World world, BlockPos pos) {

        if (world == null || pos == null) {
            return new VaultScanResult(false, "NULL");
        }

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity == null) {
            return new VaultScanResult(false, "NO_BLOCK_ENTITY");
        }

        String name = blockEntity.getClass().getName();

        VaultDebugData.incrementScans();
        VaultDebugData.setLastBlockEntity(name);

        return new VaultScanResult(true, name);
    }
}
