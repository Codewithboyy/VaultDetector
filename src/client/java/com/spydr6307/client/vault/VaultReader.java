package com.spydr6307.client.vault;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class VaultReader {

    public static String readVaultState(World world, BlockPos pos) {

        if (world == null || pos == null) {
            return "NULL_WORLD_OR_POS";
        }

        BlockEntity be = world.getBlockEntity(pos);

        if (be == null) {
            return "NO_BLOCK_ENTITY";
        }

        // Safe debug fallback (works across mappings/versions)
        String className = be.getClass().getName();

        if (className.toLowerCase().contains("vault")) {
            return "VAULT_BE: " + className;
        }

        return "BE: " + className;
    }
}
