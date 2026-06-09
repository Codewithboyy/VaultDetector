package com.spydr6307.client.vault;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class VaultReader {

    public static String readVaultState(World world, BlockPos pos) {

        VaultScanResult result = VaultAnalyzer.scan(world, pos);

        if (!result.isVaultFound()) {
            return result.getBlockEntityName();
        }

        return result.getBlockEntityName();
    }
}
