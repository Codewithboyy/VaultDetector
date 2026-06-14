package com.spydr6307.client.vault;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class VaultMemory {

    private static final List<BlockPos> vaults = new ArrayList<>();

    public static void add(BlockPos pos) {

        if (pos == null) {
            return;
        }

        if (!vaults.contains(pos)) {
            vaults.add(pos.toImmutable());
            VaultStorage.save();
        }
    }

    public static List<BlockPos> getVaults() {
        return vaults;
    }

    public static int getCount() {
        return vaults.size();
    }

    public static void clear() {
        vaults.clear();
        VaultStorage.save();
    }

    public static void load(List<BlockPos> positions) {
        vaults.clear();
        vaults.addAll(positions);
    }
}