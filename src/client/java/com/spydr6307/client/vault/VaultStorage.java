package com.spydr6307.client.vault;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class VaultStorage {

    private static File getFile() {

        return new File(
                MinecraftClient.getInstance().runDirectory,
                "vaultdetector_vaults.txt"
        );
    }

    public static void save() {

        try {

            FileWriter writer = new FileWriter(getFile());

            for (BlockPos pos : VaultMemory.getVaults()) {

                writer.write(
                        pos.getX() + "," +
                        pos.getY() + "," +
                        pos.getZ()
                );

                writer.write("\n");
            }

            writer.close();

        } catch (Exception ignored) {
        }
    }

    public static void load() {

        try {

            File file = getFile();

            if (!file.exists()) {
                return;
            }

            List<BlockPos> positions = new ArrayList<>();

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] split = line.split(",");

                if (split.length != 3) {
                    continue;
                }

                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                int z = Integer.parseInt(split[2]);

                positions.add(new BlockPos(x, y, z));
            }

            reader.close();

            VaultMemory.load(positions);

        } catch (Exception ignored) {
        }
    }
}
