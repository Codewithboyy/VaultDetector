package com.spydr6307.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.vault.VaultBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class VaultDetectorClient implements ClientModInitializer {
    private static final int MAX_RAYCAST_DISTANCE = 6; // Standard interaction range
    private static boolean featureEnabled = true;
    private static final Set<String> TARGET_ITEMS = new HashSet<>(); // e.g., "minecraft:heavy_core"

    private static KeyBinding toggleKey;

    static {
        TARGET_ITEMS.add("minecraft:heavy_core"); // Default target
        // Add more: TARGET_ITEMS.add("minecraft:trident");
    }

    @Override
    public void onInitializeClient() {
        // Register keybind (V key by default)
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.vaultdetector.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.vaultdetector.general"
        ));

        // Main tick logic
        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);

        // Optional rendering
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            // TODO: Draw highlight box around target vault if needed
        });
    }

    private void onClientTick(MinecraftClient client) {
        if (client.world == null || client.player == null || !featureEnabled) return;

        // Toggle feature with key
        while (toggleKey.wasPressed()) {
            featureEnabled = !featureEnabled;
            client.player.sendMessage(Text.literal("§6[VaultDetector] " + (featureEnabled ? "§aENABLED" : "§cDISABLED")), false);
        }

        if (client.player.age % 2 != 0) return; // Run every other tick for performance

        HitResult hit = client.crosshairTarget;
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hit;
        BlockPos pos = blockHit.getBlockPos();

        BlockEntity blockEntity = client.world.getBlockEntity(pos);
        if (!(blockEntity instanceof VaultBlockEntity vaultBE)) return;

        // Read current displayed reward
        ItemStack displayItem = vaultBE.getClientData().getDisplayItem();
        if (displayItem.isEmpty()) return;

        String itemId = Registries.ITEM.getId(displayItem.getItem()).toString();

        if (TARGET_ITEMS.contains(itemId)) {
            // Optimal timing: Trigger interaction (right-click)
            client.player.sendMessage(Text.literal("§a[VaultDetector] Target detected! Interacting..."), false);
            
            // Simulate use (press use key)
            if (client.interactionManager != null) {
                client.doItemUse(); // This triggers the vault interaction if holding key
            }
        }
    }

    // Utility to add target items via command later
    public static void addTargetItem(String itemId) {
        TARGET_ITEMS.add(itemId);
    }
}
